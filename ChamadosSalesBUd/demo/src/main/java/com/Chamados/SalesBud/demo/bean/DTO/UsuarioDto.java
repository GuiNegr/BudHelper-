package com.Chamados.SalesBud.demo.bean.DTO;

import com.Chamados.SalesBud.demo.bean.entity.Usuario;
import com.Chamados.SalesBud.demo.bean.enunPackage.UserStatus;

public record UsuarioDto(Long idUser,
                         String userName,
                         String password,
                         UserStatus userStatus) {

    public UsuarioDto(String userName, String password) {
        this(0L,userName,password,UserStatus.ATIVO);
    }

    public UsuarioDto(Usuario usuarioEntity){
        this(usuarioEntity.getIdUser(),usuarioEntity.getUserName(),usuarioEntity.getPassword(),usuarioEntity.getStatus());
    }
}
