package com.geradorvendas.crm.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geradorvendas.crm.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long>{

  Long countByVendedorIdAndDataBetween(Long vendedorId, LocalDate from, LocalDate to);

}
