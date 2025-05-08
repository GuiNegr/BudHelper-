package com.Chamados.SalesBud.demo.view.empresaView.impl;

import com.Chamados.SalesBud.demo.bean.DTO.EmpresaDto;
import com.Chamados.SalesBud.demo.controllers.EmpresaController;

import com.Chamados.SalesBud.demo.services.EmpresaService;
import com.Chamados.SalesBud.demo.view.empresaView.EmpresaView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class EmpresaViewImpl implements EmpresaView {


    @Autowired
    EmpresaService empresaService;

    @Override
    public void listarEmpresas() {
        List<EmpresaDto> empresas = empresaService.listarEmpresas();
        System.out.println("Empresas Cadastradas");
        for (EmpresaDto empresa : empresas) {
            System.out.println(empresa.idEmpresa() + " - Nome: "+empresa.empresaNome() +"; Categoria: "+empresa.empresaCateg());
        }
    }
}
