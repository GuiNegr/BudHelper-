package com.Chamados.SalesBud.demo.exception;

public class EmpresaNotFound  extends RuntimeException{
    public EmpresaNotFound(String s) {
        super("Empresa não encontrada!");
    }
}
