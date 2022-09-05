package com.geradorvendas.crm.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geradorvendas.crm.model.VendedorComVendas;
import com.geradorvendas.crm.repository.VendaRepository;
import com.geradorvendas.crm.repository.VendedorRepository;

@Service
public class VendedorService {

  @Autowired
  private VendaRepository vendasRepository;
  @Autowired
  private VendedorRepository vendedorRepository;

  public Optional<VendedorComVendas> getVendedorComVendasPorPeriodo(Long id, LocalDate de, LocalDate ate) {
    
    ate = ate.plusDays(1); // adiciona um dia a mais pois as verificações tratam como exclusivo

    // Obtém vendedor e verifica se houve sucesso
    var vendedor = vendedorRepository.findById(id);

    if (vendedor.isEmpty()) {
      return Optional.empty();
    }

    var vendedorComVendas = new VendedorComVendas(vendedor.get());

    // Obtém contagem de vendas do vendedor para um determinado periodo
    var vendasPorPeriodo = vendasRepository.countByVendedorIdAndDataBetween(id, de, ate);

    // Calcula diferenteça de dias entre as datas
    var periodInDays = de.until(ate, ChronoUnit.DAYS);
    
    // Calcula média de vendas por dia entre as datas
    var mediaDeVendasDiaria = (double) vendasPorPeriodo / (double) periodInDays;
    
    // Popula os campos faltantes do vendedor
    vendedorComVendas.setNumeroDeVendas(vendasPorPeriodo);

    vendedorComVendas.setMediaDeVendasDiaria(mediaDeVendasDiaria);

    return Optional.of(vendedorComVendas);
  }

}
