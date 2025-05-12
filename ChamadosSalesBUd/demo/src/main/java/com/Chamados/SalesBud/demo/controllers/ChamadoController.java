package com.Chamados.SalesBud.demo.controllers;


import com.Chamados.SalesBud.demo.bean.DTO.UsuarioDto;
import com.Chamados.SalesBud.demo.bean.entity.ChamadosBud;
import com.Chamados.SalesBud.demo.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ChamadoController {

    @Autowired
    ChamadoService chamadoService;

    public void cadastroChamado(int idChamado, int status, String descricao, UsuarioDto usuarioLogado) {
        chamadoService.registrarChamado(idChamado, status, descricao, usuarioLogado);
    }

    public void fecharChamdo(int idChamado) {
        chamadoService.fecharChamdo(idChamado);
    }

    public List<ChamadosBud> listarChamadosPorEmpresa(int idEmpresa) {
       return chamadoService.listarChamados(idEmpresa);
    }

    public List<ChamadosBud> listarChamados() {
        return chamadoService.listarChamados();
    }

    public void gerarCsv(){
        chamadoService.exportarCsvBDCompleto();
    }

    public void gerarCsvPorEmpresa(int idEmpresa){chamadoService.exportarCsvSoComUmaEmpresa(idEmpresa);}
}
