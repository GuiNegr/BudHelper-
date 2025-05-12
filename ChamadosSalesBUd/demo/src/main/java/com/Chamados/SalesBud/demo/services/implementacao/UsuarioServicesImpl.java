package com.Chamados.SalesBud.demo.services.implementacao;

import com.Chamados.SalesBud.demo.bean.DTO.UsuarioDto;
import com.Chamados.SalesBud.demo.bean.entity.Usuario;
import com.Chamados.SalesBud.demo.bean.enunPackage.UserStatus;
import com.Chamados.SalesBud.demo.exception.AlreadyExistsLogin;
import com.Chamados.SalesBud.demo.exception.LoginNotFound;
import com.Chamados.SalesBud.demo.repository.UsuarioRepository;
import com.Chamados.SalesBud.demo.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UsuarioServicesImpl implements UsuarioServices {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> login(String name, String senha) throws LoginNotFound {
        Optional<Usuario> usuario = usuarioRepository.findByUserName(name);

        if (usuario.isPresent()) {
           if(!usuario.get().getPassword().equals(senha)){
               throw new LoginNotFound();
           }
        }else {
            throw new LoginNotFound();
        }
        return usuario;
    }

    @Override
    public Optional<Usuario> cadastro(String name, String senha) throws AlreadyExistsLogin {
        Optional<Usuario> existing = usuarioRepository.findByUserName(name);
        if (existing.isEmpty()) {
            Usuario usuario = new Usuario(name, senha, UserStatus.ATIVO);
            Usuario saved = usuarioRepository.save(usuario);
            return Optional.of(saved);
        }
        throw new AlreadyExistsLogin();
    }
}
