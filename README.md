# BudHelper

## Oque ele é?
  - BudHelper é um auxiliador em terminal para registrar chamados(bom por enquanto) a ideia aqui é criar um sistema funcional para ajuda de quem (assim como eu) trabalha na aréa de suporte.
    
    algumas empresas não tem o JIRA e outros gerenciadores de chamados, e acabam fazendo via whatsApp. com isso em mente integrei um chatbot via whatsapp para poder registrar os chamados.
    

    cuidados! Ele usa processamento multithread já que uma instancia da thread serve para gerenciador do budHelper e outra para registrar as chamadas via rabbitMQ
    porém não sei dizer o quanto otimizado ele é

## Como posso usar?
  - a ideia é você iniciar uma imagem dockerizada do mysql, ubuntu e RabbitMQ(ele que está fazendo a transferencia de informações para o back eo chat), crie uma docker network e acople as 3 , compile o codigo, gere o jar e de um cp  no ubuntu, e o resto é o que bud faz

# como faço isso?
### Instancie o MySQl no docker

No terminal ->
docker pull mysql:latest
  
docker run --name (aqui coloque o nome de sua imagem) -e MYSQL_ROOT_PASSWORD=s(senha do seu bd) -d -p 3306:3306 mysql:latest
  
docker exec -it (o nome da imagem) mysql -u root -p

### instanciar o ubuntu no docker

no terminal ->

docker pull ubunt
  
docker run -it --name (nome da imagem) ubuntu bash  

docker start -ai (nome da imagem)  

dentro do ambiente ->  

sudo yum install openjdk-17-jdk(ou qualquer outro para instalar o java 17)

### Instanciar o RabbitMQ
aqui eu acabo sempre usando o codigo que está no doc deles.

docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management


             

### Nesse momento é muito importante criar uma Rede Docker para o jar reconheça o banco instalado no ambiente mysql
  
docker network create (nome da rede)  
  
docker run --name (noem do seu mysql) --network (nome da sua rede) -e MYSQL_ROOT_PASSWORD=(senha do mysql) -d mysql:latest

docker run -it --name (nome da imagem ubuntu) --network (nome da sua rede) ubuntu bash

ou docker network connect (nome da network) (nome do conteiner)




### Oque ele faz?
- Na Versão atual? registra chamados e gera um csv para utilização de bi posteriormente
- e também registra os chamados chegados via whatsapp
