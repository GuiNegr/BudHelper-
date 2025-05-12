package com.Chamados.SalesBud.demo.view.usuarioView.impl;

import com.Chamados.SalesBud.demo.bean.DTO.UsuarioDto;
import com.Chamados.SalesBud.demo.bean.entity.Usuario;
import com.Chamados.SalesBud.demo.controllers.UsuarioController;
import com.Chamados.SalesBud.demo.exception.AlreadyExistsLogin;
import com.Chamados.SalesBud.demo.exception.LoginNotFound;
import com.Chamados.SalesBud.demo.services.UsuarioServices;
import com.Chamados.SalesBud.demo.view.colorSelector.ColorClassSelector;
import com.Chamados.SalesBud.demo.view.menuView.MenuView;
import com.Chamados.SalesBud.demo.view.usuarioView.UsuarioView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;


@Component
public class UsuarioViewImpl implements UsuarioView {


    @Autowired
    MenuView menuView;

    @Autowired
    UsuarioServices usuarioServices;

    @Autowired
    UsuarioController usuarioController;


    @Override
    public void cadastrarUsuario() {
        Scanner scanner = new Scanner(System.in);
        boolean valido = false;
        Optional<Usuario> usuario = null;
        System.out.println(ColorClassSelector.ANSI_BLUE + "-------- BEM VINDO A CRIAÇÃO DE USUARIO -------- ");
        System.out.print(ColorClassSelector.ANSI_YELLOW + "Por favor digite seu username: ");
        String username = scanner.nextLine();

        String senha = null;
        while (!valido) {
            String senha1, senha2;

            if (System.console() != null) {
                senha1 = new String(System.console().readPassword("Por favor, digite sua senha: "));
                senha2 = new String(System.console().readPassword("Digite novamente sua senha: "));
            } else {
                System.out.print("Por favor, digite sua senha: ");
                senha1 = scanner.nextLine();
                System.out.print("Digite novamente sua senha: ");
                senha2 = scanner.nextLine();
            }

            if (senha1.equals(senha2)) {
                senha = senha1;
                valido = true;
            } else {
                System.out.println(ColorClassSelector.ANSI_RED + "Senhas diferentes!");
                System.out.println("Por favor, repita o processo.");
                System.out.println("-------------------------------------------------");
            }
        }


        try {
            usuario = usuarioController.cadastrarUsuario(username, senha);
        } catch (AlreadyExistsLogin e) {
            System.out.println(e.getMessage());
            cadastrarUsuario();
        }

        if (usuario.isPresent()) {
            UsuarioDto usuarioLogado = new UsuarioDto(usuario.get());
            menuView.telaOpcoes(usuarioLogado);
        }

    }

    @Override
    public void telaLogin() {
        System.out.println(ColorClassSelector.ANSI_PURPLE + "--------------------Bem Vindo ao SalesBud Chamados Manager!-----------------------");
        System.out.println(ColorClassSelector.ANSI_YELLOW + "Sim estamos usando terminal porque quem criou isso tem preguiça de fazer FrontEnd");
        System.out.println(ColorClassSelector.ANSI_YELLOW + "Vamos Começar!");
        System.out.println(ColorClassSelector.ANSI_PURPLE + "------------------------------------------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        Optional<Usuario> usuario = null;
        boolean valido = false;

        String senha = null;
        String username = null;
        while (!valido) {
            System.out.print(ColorClassSelector.ANSI_RESET + "Por favor digite seu username: ");
            username = scanner.nextLine();


            senha = new String(System.console().readPassword("Por favor, digite sua senha: "));


            try {
                usuario = usuarioController.logarUsuario(username, senha);
            } catch (LoginNotFound e) {
                System.out.println(e.getMessage());
                int op = -1;
                while (op != 1 && op != 2) {
                    System.out.println("Deseja cadastrar usuário?");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Já possuo Login");

                    if (scanner.hasNextInt()) {
                        op = scanner.nextInt();
                        if (op == 1) {
                            cadastrarUsuario();
                        } else if (op == 2) {
                            telaLogin();
                        } else {
                            System.out.println("Opção inexistente");
                        }
                    } else {
                        System.out.println("Entrada inválida. Digite um número.");
                        scanner.next();
                    }
                }
            }
            if(usuario.isPresent()){
                UsuarioDto usuarioDto = new UsuarioDto(usuario.get());
                menuView.telaOpcoes(usuarioDto);
            }

        }


    }

}
