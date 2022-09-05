package com.geradorvendas.crm.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VendedorComVendas extends Vendedor {

  public VendedorComVendas(Vendedor vendedor) {
    this.setId(vendedor.getId());
    this.setNome(vendedor.getNome());
  }

  private Long numeroDeVendas = 0L;

  private Double mediaDeVendasDiaria = 0D;

}