package com.Chamados.SalesBud.demo.bean.entity;


import com.Chamados.SalesBud.demo.bean.DTO.UsuarioDto;
import com.Chamados.SalesBud.demo.bean.enunPackage.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idUser;

    @Column(name = "USR_USERNAME",nullable = false, unique = true)
    String userName;
    @Column(name = "USR_PASSWORD",nullable = false)
    String password;
    @Column(name = "USR_STATUS",nullable = false)
    UserStatus status;



    public Usuario(UsuarioDto usuarioDto){
        this.userName = usuarioDto.userName();
        this.password = usuarioDto.password();
        this.status = usuarioDto.userStatus();
        this.idUser = usuarioDto.idUser();
    }

    public Usuario(String userName, String password, UserStatus status) {
        this.userName = userName;
        this.password = password;
        this.status = status;
    }
}
