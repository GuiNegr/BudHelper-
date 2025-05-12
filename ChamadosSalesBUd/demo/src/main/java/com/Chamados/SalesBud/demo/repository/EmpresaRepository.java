package com.Chamados.SalesBud.demo.repository;

import com.Chamados.SalesBud.demo.bean.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    List<Empresa> findAllByOrderByEmpresaNomeAsc();

}
