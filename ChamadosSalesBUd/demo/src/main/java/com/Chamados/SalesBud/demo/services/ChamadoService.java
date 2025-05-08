package com.Chamados.SalesBud.demo.services;

import com.Chamados.SalesBud.demo.bean.DTO.EmpresaDto;
import com.Chamados.SalesBud.demo.bean.DTO.UsuarioDto;
import com.Chamados.SalesBud.demo.bean.entity.ChamadosBud;
import com.Chamados.SalesBud.demo.bean.enunPackage.ClassificaChamado;

import java.util.Optional;

public interface ChamadoService {

    public boolean registrarChamado(int idChamado, int status, String descricao, UsuarioDto usuario);

    public void exportarCsvBDCompleto();
}
