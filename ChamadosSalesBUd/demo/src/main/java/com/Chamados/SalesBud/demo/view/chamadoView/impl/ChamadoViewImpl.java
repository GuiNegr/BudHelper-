package com.Chamados.SalesBud.demo.view.chamadoView.impl;


import com.Chamados.SalesBud.demo.bean.DTO.UsuarioDto;
import com.Chamados.SalesBud.demo.bean.entity.ChamadosBud;
import com.Chamados.SalesBud.demo.controllers.ChamadoController;
import com.Chamados.SalesBud.demo.services.ChamadoService;
import com.Chamados.SalesBud.demo.view.chamadoView.ChamadoView;
import com.Chamados.SalesBud.demo.view.colorSelector.ColorClassSelector;
import com.Chamados.SalesBud.demo.view.empresaView.EmpresaView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Scanner;

@Component
public class ChamadoViewImpl implements ChamadoView {


    @Autowired
    EmpresaView empresaView;


    @Autowired
    ChamadoService chamadoService;




    @Autowired
    ChamadoController chamadoController;




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

        chamadoController.cadastroChamado(id, op, campoObs,usuarioLogado);
        System.out.println("Chamado cadastrado!");


    }

    @Override
    public void fecharChamadoView() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o ID da chamado:");
        int id = scanner.nextInt();
        chamadoController.fecharChamdo(id);
        System.out.println("Chamado com status alterado! agora está resolvido =D");
        System.out.println("----------------------------------------------------------------");

    }

    @Override
    public void listarChamdosPorEmpresa() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ColorClassSelector.ANSI_RED+ "empresas listadas-----------------------------------");
        empresaView.listarEmpresas();
        System.out.println("------------------------------------------------------------------------");
        System.out.println(ColorClassSelector.ANSI_BLUE+"informe o id que quer checar");
        int idEmpresa = scanner.nextInt();
        List<ChamadosBud> chamadosBudList =chamadoController.listarChamadosPorEmpresa(idEmpresa);


        for (ChamadosBud chamadosBud : chamadosBudList) {
            System.out.println(ColorClassSelector.ANSI_PURPLE+"-------------------------------------------------------------------------------------------");
            System.out.println(chamadosBud.getIdChamados()+ " - Empresa: "+chamadosBud.getEmpresaChamado().getEmpresaNome() +"; Status: "+chamadosBud.getStatusChamado() );
            System.out.println("Desc: "+chamadosBud.getCampoObsChamado());
            System.out.println("------------------------------------------------------------------------------------------");
        }

    }

    @Override
    public void menuExportCsvEmpresa() {
        Scanner sc = new Scanner(System.in);
        empresaView.listarEmpresas();
        System.out.println("-----------------------------------------------------");
        System.out.println("Qual empresa gostaria de exportar?");
        int id = sc.nextInt();

        chamadoController.gerarCsvPorEmpresa(id);
        System.out.println("CSV Gerado com sucesso!");
    }

    @Override
    public void listarChamadosAllView() {

        List<ChamadosBud> chamadosBudList = chamadoController.listarChamados();


        for (ChamadosBud chamadosBud : chamadosBudList) {
            System.out.println(ColorClassSelector.ANSI_PURPLE+"-------------------------------------------------------------------------------------------");
            System.out.println(chamadosBud.getIdChamados()+ " - Empresa: "+chamadosBud.getEmpresaChamado().getEmpresaNome() +"; Status: "+chamadosBud.getStatusChamado() );
            System.out.println("Desc: "+chamadosBud.getCampoObsChamado());
            System.out.println("------------------------------------------------------------------------------------------");
        }
    }

}
