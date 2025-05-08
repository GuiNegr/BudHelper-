package com.Chamados.SalesBud.demo.services;

import com.Chamados.SalesBud.demo.bean.DTO.UsuarioDto;
import com.Chamados.SalesBud.demo.bean.entity.Usuario;
import com.Chamados.SalesBud.demo.exception.AlreadyExistsLogin;
import com.Chamados.SalesBud.demo.exception.LoginNotFound;

import java.util.Optional;

public interface UsuarioServices {
    Optional<Usuario> login(String name, String senha) throws LoginNotFound;
    Optional<Usuario> cadastro(String name, String senha) throws AlreadyExistsLogin;
}
