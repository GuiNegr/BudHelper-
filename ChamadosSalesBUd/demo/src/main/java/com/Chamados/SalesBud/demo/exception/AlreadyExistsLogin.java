package com.Chamados.SalesBud.demo.exception;

public class AlreadyExistsLogin extends RuntimeException {
  public AlreadyExistsLogin() {
    super("Usuario Já cadastrado!");
  }
}
