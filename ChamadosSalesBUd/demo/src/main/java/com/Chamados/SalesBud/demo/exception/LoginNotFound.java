package com.Chamados.SalesBud.demo.exception;

public class LoginNotFound extends RuntimeException {
    public LoginNotFound() {
        super("Usuario não encontrado!");
    }
}
