package com.Chamados.SalesBud.demo.bean.DTO;

import com.Chamados.SalesBud.demo.bean.entity.Empresa;
import com.Chamados.SalesBud.demo.bean.enunPackage.CategEmpresa;

public record EmpresaDto (Long idEmpresa,
                          String empresaNome,
                          CategEmpresa empresaCateg){

    public EmpresaDto(Empresa empresa){
        this(empresa.getIdEmpresa(),empresa.getEmpresaNome(),empresa.getEmpresaTipo());
    }
}
