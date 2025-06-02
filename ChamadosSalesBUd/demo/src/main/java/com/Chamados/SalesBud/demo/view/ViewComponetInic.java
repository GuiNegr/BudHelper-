package com.Chamados.SalesBud.demo.view;

import com.Chamados.SalesBud.demo.view.empresaView.EmpresaView;
import com.Chamados.SalesBud.demo.view.usuarioView.impl.UsuarioViewImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ViewComponetInic implements CommandLineRunner {

    @Autowired
    EmpresaView empresaView;
    @Autowired
    private UsuarioViewImpl usuarioViewImpl;


    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> usuarioViewImpl.telaLogin()).start();    }
}
