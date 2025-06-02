package com.Chamados.SalesBud.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    //importante aqui estar com o nome do objeto que virá do js
    public static final String QUEUE_NAME = "usuariosJson";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }
}