package com.Chamados.SalesBud.demo.services.implementacao;

import com.Chamados.SalesBud.demo.bean.DTO.EmpresaDto;
import com.Chamados.SalesBud.demo.bean.entity.Empresa;
import com.Chamados.SalesBud.demo.repository.EmpresaRepository;
import com.Chamados.SalesBud.demo.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    @Override
    public List<EmpresaDto> listarEmpresas() {
        return empresaRepository.findAllByOrderByEmpresaNomeAsc().stream()
                .map(EmpresaDto::new)
                .collect(Collectors.toList());
    }
}
