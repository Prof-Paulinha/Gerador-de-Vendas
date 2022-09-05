package com.geradorvendas.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geradorvendas.crm.model.Venda;
import com.geradorvendas.crm.repository.VendaRepository;

@RestController
@RequestMapping("venda")
public class VendaController {

  @Autowired
  private VendaRepository vendaRepository;

  @GetMapping
	public List<Venda> getAll() {
		return vendaRepository.findAll();
	}

  @PostMapping
	public Venda create(@RequestBody Venda venda) {
    vendaRepository.save(venda);
    return venda;
  }

}
