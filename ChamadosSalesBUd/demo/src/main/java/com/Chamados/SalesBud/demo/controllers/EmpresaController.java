package com.Chamados.SalesBud.demo.controllers;

import com.Chamados.SalesBud.demo.bean.DTO.EmpresaDto;
import com.Chamados.SalesBud.demo.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;


    public List<EmpresaDto> listandoEmpresas(){
        return empresaService.listarEmpresas();
    }
}
