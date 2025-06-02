package com.Chamados.SalesBud.demo.middleware;

import com.Chamados.SalesBud.demo.config.RabbitMqConfig;
import com.Chamados.SalesBud.demo.services.ChamadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WhatsAppBot {


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ChamadoService chamadoService;

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
    public void receberMensagem(String mensagemJson) {
        new Thread(() -> {
            try {
                Map<String, Object> dados = objectMapper.readValue(mensagemJson, Map.class);
                chamadoService.chamadosViaRabbitMQ(dados);
            } catch (Exception e) {
                System.err.println("Erro ao processar mensagem: " + e.getMessage());
            }
        }).start();
    }
}

