package com.Chamados.SalesBud.demo.view.menuView.impl;

import com.Chamados.SalesBud.demo.bean.DTO.UsuarioDto;
import com.Chamados.SalesBud.demo.controllers.ChamadoController;
import com.Chamados.SalesBud.demo.view.chamadoView.ChamadoView;
import com.Chamados.SalesBud.demo.view.colorSelector.ColorClassSelector;
import com.Chamados.SalesBud.demo.view.menuView.MenuView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuViewImpl implements MenuView {

@Autowired
ChamadoView chamadoView;

    @Autowired
    ChamadoController chamadoController;

    @Override
    public void telaOpcoes(UsuarioDto usuarioLogado) {
        Scanner sc = new Scanner(System.in);
        boolean valido = true;
        System.out.println(ColorClassSelector.ANSI_RED+"---------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println();
        while (valido) {
            System.out.println(ColorClassSelector.ANSI_PURPLE+"SISTEMA HELPER SALESBUD");
            System.out.println("Bem vindo "+usuarioLogado.userName());
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("Oque é preciso Hoje?");
            System.out.println("1 - Registrar chamado");
            System.out.println("2 - Atualizar chamado");
            System.out.println("3 - Gerar CSV");
            System.out.println("10 - Sair");
            int opcao = sc.nextInt();

            switch (opcao){
                case 10:
                    valido = false;
                    break;
                case 1:
                    chamadoView.cadastroChamadoView(usuarioLogado);
                    break;
                case 3:
                    chamadoController.gerarCsv();
                    break;
            }
        }

        System.exit(0);
    }
}
