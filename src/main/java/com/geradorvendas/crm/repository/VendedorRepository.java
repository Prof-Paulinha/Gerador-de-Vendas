package com.geradorvendas.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geradorvendas.crm.model.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long>{

}
