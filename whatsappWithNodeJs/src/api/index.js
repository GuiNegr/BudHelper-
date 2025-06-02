const wppconnect = require('@wppconnect-team/wppconnect');
const amqp = require('amqplib');


// Isso é essencial para conseguir separar as conversas
const usuario = {};

wppconnect.create({
    session: 'Bot',
    catchQR: (base64Qr, asciiQR, attempts, urlCode) => {
        console.log('QRCode URL:', urlCode);
    },
    statusFind: (statusSession) => {
        console.log('Status da sessão:', statusSession);
    },
    headless: true,
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
        if (!message.isGroupMsg) {
            tratamentoDeMensagem(message, client);
        }

    });
}



//Para caso eu esqueça oque aconteceu aqui, eu estou tentando uma chamada "recursiva", fica sempre observando as mensagem que chega
//o usuario global armazena a instancia que preciso para verificar em que etapa cada usuario está, assim 
function tratamentoDeMensagem(message, client) {
    const from = message.from;
    const texto = message.body.trim();

    if (!usuario[from]) {
        usuario[from] = { etapa: 0, assunto: null };
    }

    const estado = usuario[from];
    console.log(message)

    switch (estado.etapa) {
        case 0:
            if (texto.toLowerCase() === "help") {
                const menu = `Olá, escolha uma opção:\n1 - Suporte Técnico\n2 - Atendimento Humano`;
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
            if (["1", "2"].includes(texto)) {
                estado.assunto = texto;

                if (texto === "1") {
                    client.sendText(from, "Você escolheu Suporte Técnico. Por favor, informe seu email:")
                        .then(() => {
                            estado.etapa = 2;
                        })
                        .catch(console.error);
                } else if (texto === "2") {
                    client.sendText(from, "Aguarde, um atendente humano vai entrar em contato.")
                        .then(() => {
                            estado.etapa = 0;
                            estado.assunto = null;
                        })
                        .catch(console.error);
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
            usuario[from].empresa = texto;
            client.sendText(from, "Obrigado! Estarei enviando as informações para o time de suporte.")
                .then(() => {
                    console.log("Atendimento concluído:", usuario[from]);
                    estado.etapa = 0;
                    estado.assunto = null;
                    enviarAoBudHelper(usuario[from])
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




///Mensageria do RabbitMq

async function enviarAoBudHelper(problema) {
    //amqp é a porta 5672, é um codigo de protocolo feito para comunicação de mensagerias 
    const connection = await amqp.connect('amqp://localhost')
    //criando a conexão com meu canal de comunicação da mensageria 
    const channel = await connection.createChannel();
    const queue = 'usuariosJson'
    await channel.assertQueue(queue, { durable: true });
    channel.sendToQueue(queue, Buffer.from(JSON.stringify(problema)))

    console.log("Enviado ao bud helper")

    await channel.close();
    await connection.close();
}

