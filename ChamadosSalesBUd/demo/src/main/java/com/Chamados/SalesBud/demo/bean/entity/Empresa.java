package com.Chamados.SalesBud.demo.bean.entity;

import com.Chamados.SalesBud.demo.bean.DTO.EmpresaDto;
import com.Chamados.SalesBud.demo.bean.enunPackage.CategEmpresa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idEmpresa;

    @Column(nullable = false, name = "EMPR_EMPRESANAME",unique = true)
    String empresaNome;

    @Column(nullable = false, name = "EMPR_EMPRESATIPO")
    CategEmpresa empresaTipo;


    public Empresa(EmpresaDto empresaDto){
        this.idEmpresa = empresaDto.idEmpresa();
        this.empresaNome = empresaDto.empresaNome();
        this.empresaTipo = empresaDto.empresaCateg();
    }
}
