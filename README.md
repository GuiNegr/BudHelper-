# BudHelper

## Oque ele é?
  - BudHelper é um auxiliador em terminal para registrar chamados(bom por enquanto) a ideia aqui é criar um sistema funcional para ajuda de quem (assim como eu) trabalha na aré de suporte.
    algumas empresas não tem o JIRA e outros gerenciadores de chamados, e acabam fazendo via whatsApp

## Como posso usar?
  - a ideia é você iniciar uma imagem dockerizada do mysql e outra do ubuntu, crie uma docker network dentro das duas, compile o codigo, gere o jar e de um cp  no ubuntu, e o resto é o que bud faz
- -
  # como faço isso?
    ### Instancie o MySQl no docker
   No terminal ->
                docker pull mysql:latest
                docker run --name (aqui coloque o nome de sua imagem) -e MYSQL_ROOT_PASSWORD=s(senha do seu bd) -d -p 3306:3306 mysql:latest
                docker exec -it (o nome da imagem) mysql -u root -p

    ### instanciar o ubuntu no docker
    no terminal ->
                  docker pull ubuntu
                  docker run -it --name (nome da imagem) ubuntu bash
                  docker start -ai (nome da imagem)
  dentro do ambiente ->
  sudo yum install openjdk-17-jdk(ou qualquer outro para instalar o java 17)              

    ### Nesse momento é muito importante criar uma Rede Docker para o jar reconheça o banco instalado no ambiente mysql
    docker network create (nome da rede)
    docker run --name (noem do seu mysql) --network (nome da sua rede) -e MYSQL_ROOT_PASSWORD=(senha do mysql) -d mysql:latest
    docker run -it --name (nome da imagem ubuntu) --network (nome da sua rede) ubuntu bash




    
    ### Oque ele faz?
     - Na Versão atual? registra chamados e gera um csv para utilização de bi posteriormente
