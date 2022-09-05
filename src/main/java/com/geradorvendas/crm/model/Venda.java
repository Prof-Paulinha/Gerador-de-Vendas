package com.geradorvendas.crm.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Venda {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private LocalDate data = LocalDate.now();

  @Column(nullable = false)
  private Double valor;

  @Column(nullable = false)
  private Long vendedorId;

  @Column(nullable = false)
  private String vendedorNome;

}