package com.Chamados.SalesBud.demo.controllers;

import com.Chamados.SalesBud.demo.bean.entity.Usuario;
import com.Chamados.SalesBud.demo.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioServices usuarioServices;

    public Optional<Usuario> cadastrarUsuario(String username, String senha) {
        return usuarioServices.cadastro(username, senha);
    }

    public Optional<Usuario> logarUsuario(String username, String senha) {
        return usuarioServices.login(username, senha);
    }

}
