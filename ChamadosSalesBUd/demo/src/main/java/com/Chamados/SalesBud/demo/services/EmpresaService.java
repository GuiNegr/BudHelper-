package com.Chamados.SalesBud.demo.services;

import com.Chamados.SalesBud.demo.bean.DTO.EmpresaDto;
import com.Chamados.SalesBud.demo.bean.entity.Empresa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmpresaService {

    List<EmpresaDto> listarEmpresas();


}
