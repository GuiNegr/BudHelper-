package com.Chamados.SalesBud.demo.services;

import com.Chamados.SalesBud.demo.bean.DTO.EmpresaDto;
import com.Chamados.SalesBud.demo.bean.DTO.UsuarioDto;
import com.Chamados.SalesBud.demo.bean.entity.ChamadosBud;
import com.Chamados.SalesBud.demo.bean.enunPackage.ClassificaChamado;

import java.util.List;
import java.util.Optional;

public interface ChamadoService {

    public boolean registrarChamado(int idChamado, int status, String descricao, UsuarioDto usuario);
    public List<ChamadosBud> listarChamados(int idEmpresa);
    public List<ChamadosBud> listarChamados();

    public void fecharChamdo(int idChamado);
    public void exportarCsvBDCompleto();
    public void exportarCsvSoComUmaEmpresa(int idEmpresa);
}
