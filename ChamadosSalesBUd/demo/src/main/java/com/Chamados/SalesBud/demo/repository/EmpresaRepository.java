package com.Chamados.SalesBud.demo.repository;

import com.Chamados.SalesBud.demo.bean.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
