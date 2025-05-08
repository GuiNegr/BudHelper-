package com.Chamados.SalesBud.demo.bean.DTO;

import com.Chamados.SalesBud.demo.bean.entity.ChamadosBud;
import com.Chamados.SalesBud.demo.bean.entity.Empresa;
import com.Chamados.SalesBud.demo.bean.entity.Usuario;
import com.Chamados.SalesBud.demo.bean.enunPackage.ClassificaChamado;
import com.Chamados.SalesBud.demo.bean.enunPackage.StatusChamado;

import java.sql.Time;
import java.util.Date;

public record ChamadosBudDTO (Long idChamados,
                              Date dataChamado,
                              Time horaChamado,
                              StatusChamado statusChamado,
                              String campoObsChamado,
                              ClassificaChamado classificaChamado,
                              Empresa empresaChamado, Usuario usuarioChamado) {
    public ChamadosBudDTO(ChamadosBud chamadoEntity){
        this(chamadoEntity.getIdChamados(),
                chamadoEntity.getDataChamado(),
                chamadoEntity.getHoraChamado(),
                chamadoEntity.getStatusChamado(),
                chamadoEntity.getCampoObsChamado(),
                chamadoEntity.getClassificaChamado(),
                chamadoEntity.getEmpresaChamado(),
                chamadoEntity.getUsuarioChamado());
    }

}
