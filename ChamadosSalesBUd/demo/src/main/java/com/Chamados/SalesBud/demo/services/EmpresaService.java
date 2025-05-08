package com.Chamados.SalesBud.demo.services;

import com.Chamados.SalesBud.demo.bean.DTO.EmpresaDto;
import com.Chamados.SalesBud.demo.bean.entity.Empresa;

import java.util.List;

public interface EmpresaService {

    List<EmpresaDto> listarEmpresas();
}
