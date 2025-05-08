package com.Chamados.SalesBud.demo.view.chamadoView.impl;


import com.Chamados.SalesBud.demo.bean.DTO.UsuarioDto;
import com.Chamados.SalesBud.demo.bean.entity.ChamadosBud;
import com.Chamados.SalesBud.demo.controllers.ChamadoController;
import com.Chamados.SalesBud.demo.services.ChamadoService;
import com.Chamados.SalesBud.demo.view.chamadoView.ChamadoView;
import com.Chamados.SalesBud.demo.view.colorSelector.ColorClassSelector;
import com.Chamados.SalesBud.demo.view.empresaView.EmpresaView;
import com.Chamados.SalesBud.demo.view.menuView.MenuView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Scanner;

@Component
public class ChamadoViewImpl implements ChamadoView {


    @Autowired
    EmpresaView empresaView;


    @Autowired
    ChamadoService chamadoService;




    @Override
    public void cadastroChamadoView(UsuarioDto usuarioLogado) {
        Scanner sc = new Scanner(System.in);
        empresaView.listarEmpresas();
        System.out.println(ColorClassSelector.ANSI_PURPLE+ "-------------------------------------------------------------------------");
        System.out.println("Digite o ID da empresa que irá criar o chamado:");
        int id = sc.nextInt();

        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println("Qual é a importancia do chamado?");
        System.out.println("1 - Grave");
        System.out.println("2 - Nao Grave");
        int op = sc.nextInt();

        Scanner scLimpo = new Scanner(System.in);
        System.out.println("Digite doque se trata o chamado:");
        String campoObs = scLimpo.nextLine();

        boolean chamadosBud = chamadoService.registrarChamado(id, op, campoObs,usuarioLogado);
        System.out.println("Chamado cadastrado!");


    }

}
