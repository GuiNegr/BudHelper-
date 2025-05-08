package com.Chamados.SalesBud.demo.bean.entity;

import com.Chamados.SalesBud.demo.bean.enunPackage.ClassificaChamado;
import com.Chamados.SalesBud.demo.bean.enunPackage.StatusChamado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ChamadosBud {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idChamados;

    @Column(nullable = false, name="CHAMAD_DATA")
    Date dataChamado;
    @Column(nullable = false, name="CHAMAD_HORA")
    Time horaChamado;
    @Column(nullable = false, name="CHAMAD_STATUS")
    StatusChamado statusChamado;
    @Column(nullable = false, name="CHAMAD_CAMPO_OBS", length = 355)
    String campoObsChamado;
    @Column(nullable = false, name="CHAMAD_CLASS")
    ClassificaChamado classificaChamado;


    @ManyToOne
    Empresa empresaChamado;

    @ManyToOne
    Usuario usuarioChamado;


    public ChamadosBud(Date dataChamado,Time horaChamado, StatusChamado statusChamado, String campoObsChamado, ClassificaChamado classificaChamado, Empresa empresaChamado, Usuario usuarioChamado) {
        this.dataChamado = dataChamado;
        this.horaChamado = horaChamado;
        this.statusChamado = statusChamado;
        this.campoObsChamado = campoObsChamado;
        this.classificaChamado = classificaChamado;
        this.empresaChamado = empresaChamado;
        this.usuarioChamado = usuarioChamado;

    }
}
