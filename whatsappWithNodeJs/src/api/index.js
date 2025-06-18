const wppconnect = require('@wppconnect-team/wppconnect');
const amqp = require('amqplib');


// Armazena dados dos usuários por número
const usuario = {};

wppconnect.create({
    session: 'GuilhermeBot',
    catchQR: (base64Qr, asciiQR, attempts, urlCode) => {
        console.log('QRCode URL:', urlCode);
    },
    statusFind: (statusSession) => {
        console.log('Status da sessão:', statusSession);
    },
    headless: true,
  
    browserArgs: [
    '--no-sandbox',
    '--disable-setuid-sandbox',
    '--disable-dev-shm-usage',
    '--disable-accelerated-2d-canvas',
    '--no-first-run',
    '--no-zygote',
    '--single-process',
    '--disable-gpu'
  ],

})
    .then((client) => {
        if (!client || typeof client.onMessage !== 'function') {
            console.error('Client inválido:', client);
            return;
        }
        start(client);
    })
    .catch((error) => {
        console.error('Erro ao criar cliente:', error);
    });

function start(client) {
    console.log('Chat iniciado!');
  
client.onMessage((message) => {
  const botJid = `5511915343297@c.us`;

if (message.mentionedJidList && message.mentionedJidList.includes(botJid)) {
   if (message.quotedMsg) {
        alimentadoAbase(message.quotedMsg,client)
        } else {
        alimentadoAbase(message.body,client)
        }
}

if(message.body.includes("Help")){
    alimentadoAbase(message.body,client)

}
if(message.body.includes("budy")){
    utilizandIa(message.body, message.from,client)
}
    routerfunctionByIa(message.body)
});
}


function helperForStorageInformation(message,client,skipChat){
  const from = message.from;
    const texto = message.body.trim();

    if (!usuario[from]) {
        usuario[from] = { etapa: 0, assunto: null };
    } 
  
     const estado = usuario[from];


    if(skipChat === true){
      estado.etapa = 5
    }

    console.log(message)

    switch (estado.etapa) {
        case 0:
            if (texto.includes("help")) {
                const menu = `Olá, escolha uma opção:\n1 - Suporte Técnico\n2 \n3 - Pergunte a IA`;
                client.sendText(from, menu)
                    .then(() => {
                        estado.etapa = 1;
                    })
                    .catch(console.error);
            } else {
                client.sendText(from, `Envie "Help" para ver o menu.`).catch(console.error);
            }
            break;

        case 1:
            if (["1","3"].includes(texto)) {
                estado.assunto = texto;

                if (texto === "1") {
                    client.sendText(from, "Você escolheu Suporte Técnico. Por favor, informe seu email:")
                        .then(() => {
                            estado.etapa = 2;
                        })
                        .catch(console.error);
                } 
                else if (texto === "3"){
                        client.sendText(from, "Olá! eu sou o budHelper, em que posso te ajudar no momento?").then(()=>{
                            estado.etapa = 5;
                        }).catch(console.error)
                }
            } else {
                client.sendText(from, "Opção inválida. Por favor, digite 1 ou 2.").catch(console.error);
            }
            break;

        case 2:
            usuario[from].email = texto;
            client.sendText(from, "Obrigado! Agora, por favor, descreva o problema:")
                .then(() => {
                    estado.etapa = 3;
                })
                .catch(console.error);
            break;

        case 3:
            usuario[from].queixa = texto;
            client.sendText(from, "Obrigado pelo relato! Qual o nome da sua empresa?")
                .then(() => {
                    estado.etapa = 4;
                })
                .catch(console.error);
            break;

        case 4:
      
            client.sendText(from, "Obrigado! Estarei enviando as informações para o time de suporte.")
                .then(() => {
                    console.log("Atendimento concluído:", usuario[from]);
                    estado.etapa = 0;
                    estado.assunto = null;
                    tratamentoDeMensagem(usuario[from])
                })
                .catch(console.error);
            break;

            case 5:
             usuario[from].empresa = texto;
            client.sendText(from, "Olá, nossa Ia vai verificar a mensagem mencionada. ")
                .then(() => {
                    console.log("Atendimento concluído:", usuario[from]);
                    estado.etapa = 0;
                    estado.assunto = null;
                    utilizandIa(message.body, message.from,client)
                })
                .catch(console.error);
            break;


        default:
            client.sendText(from, "Não entendi. Por favor, envie 'Help' para começar.")
                .then(() => {
                    estado.etapa = 0;
                    estado.assunto = null;

                })
                .catch(console.error);
            break;
    }
}



function tratamentoDeMensagem(texto, client) {
    console.log(texto)
    const problema = {
        usuario: "Usuario Comenta: ",
        duvida: texto.email +" "+ texto.queixa
    }
    enviarAoBudHelper(problema)
    
}

function alimentadoAbase(texto, client) {
    const problema = {
        usuario: "Usuario Comenta: ",
        duvida: texto
    }
    enviarAoBudHelper(problema)
    
}


///Mensageria do RabbitMq

async function enviarAoBudHelper(problema) {
    //amqp é a porta 5672, é um codigo de protocolo feito para comunicação de mensagerias 
    const connection = await amqp.connect('amqp://localhost')
    //criando a conexão com meu canal de comunicação da mensageria 
    const channel = await connection.createChannel();
    const queue = 'usuariosJson';
    await channel.assertQueue(queue, { durable: true });
    channel.sendToQueue(queue, Buffer.from(JSON.stringify(problema)))

    console.log("Enviado ao bud helper")

    await channel.close();
    await connection.close();
}


///NÃO ESTÁ SENDO UTILIZADO AINDA
async function utilizandIa(mensagem,from,client) {
     const response = await fetch("http://192.168.15.143:11434/api/generate", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      model: "gemma3:1b",
      prompt: mensagem,
      stream: true
    })
  });

  const reader = response.body.getReader();
  const decoder = new TextDecoder("utf-8");
  let fullText = "";

  while (true) {
    const { value, done } = await reader.read();
    if (done) break;

    const chunk = decoder.decode(value, { stream: true });
    const lines = chunk.split("\n").filter(line => line.trim());

    for (const line of lines) {
      try {
        const json = JSON.parse(line);
        fullText += json.response;
      } catch (e) {
        console.error("Erro ao fazer parse do JSON:", e);
      }
    }
  }


  try {
    await client.sendText(from, fullText);
    console.log("Resposta da IA enviada com sucesso:", fullText);
  } catch (erro) {
    console.error("Erro ao enviar resposta:", erro);
  }

  console.log("Resposta completa:", fullText);
}




async function routerfunctionByIa(text){
    const resp = await fetch("http://192.168.15.143:11434/api/generate",{
        method: "POST", 
        headers: {'Content-Type' : 'application/json'},
        body: JSON.stringify({
            model : "gemma3:1b",
            prompt: "Dentre as categorias (chamado, ia responde, atendimento humano) preciso que analise a mensagem enviada via whatsapp de um cliente e me ajude a decidir o tratamento, o passo é simples estarei anexando uma mensagem encaminhada do whatsapp ao fim desse prompt utilizando os dois caracteres ->, por tanto pode haver nomes de pessoas ou numeros, peço que desponsidere e apenas se atente caso apareça algo sobre salesbud ou algum nome de crm e em seguida voce vai analisar e me retornar apenas uma palavra sendo ela a categoria que posso utilizar para resolução da mensagem, chamado seria quando é um problema que necessita a atenção humana para resolver algum problema no sistema e responde ia quando for algo que pode ser encaixado como uma duvida sobre o funcionamento de um  sistema -> "+text,
            stream: true
        })
    })

const reader = resp.body.getReader();
  const decoder = new TextDecoder("utf-8");
  let fullText = "";

  while (true) {
    const { value, done } = await reader.read();
    if (done) break;

    const chunk = decoder.decode(value, { stream: true });
    const lines = chunk.split("\n").filter(line => line.trim());

    for (const line of lines) {
      try {
        const json = JSON.parse(line);
        fullText += json.response;
      } catch (e) {
        console.error("Erro ao fazer parse do JSON:", e);
      }
    }
  }
  
   return fullText;
}