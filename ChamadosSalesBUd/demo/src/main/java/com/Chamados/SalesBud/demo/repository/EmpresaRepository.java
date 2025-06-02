package com.Chamados.SalesBud.demo.repository;

import com.Chamados.SalesBud.demo.bean.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    List<Empresa> findAllByOrderByEmpresaNomeAsc();
    @Query("SELECT e FROM Empresa e WHERE LOWER(e.empresaNome) LIKE LOWER(CONCAT('%', :nome, '%')) ORDER BY e.idEmpresa ASC")
    Optional<Empresa> buscarPrimeiroPorNomeAproximado(@Param("nome") String nome);
}
