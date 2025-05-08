package com.Chamados.SalesBud.demo.controllers;


import com.Chamados.SalesBud.demo.bean.DTO.UsuarioDto;
import com.Chamados.SalesBud.demo.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ChamadoController {

    @Autowired
    ChamadoService chamadoService;

    public void cadastroChamado(int idChamado, int status, String descricao, UsuarioDto usuarioLogado) {
        chamadoService.registrarChamado(idChamado, status, descricao, usuarioLogado);
    }

    public void gerarCsv(){
        chamadoService.exportarCsvBDCompleto();
    }
}
