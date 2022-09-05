package com.geradorvendas.crm.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geradorvendas.crm.model.Vendedor;
import com.geradorvendas.crm.model.VendedorComVendas;
import com.geradorvendas.crm.repository.VendedorRepository;
import com.geradorvendas.crm.services.VendedorService;

@RestController
@RequestMapping("vendedor")
public class VendedorController {

  @Autowired
  private VendedorService vendedorService;
  @Autowired
  private VendedorRepository vendedorRepository;

  // getAll
  // getById
  // create

  @GetMapping
	public List<Vendedor> getAll() {
		return vendedorRepository.findAll();
	}

  // @GetMapping("{id}")
	// public Vendedor getById(@PathVariable Long id) {
	// 	return vendedorRepository
  //     .findById(id)
  //     .orElseThrow();
	// }

  @GetMapping("{id}") // /vendedor/3?de=2022-09-01&ate=2022-09-01
  public VendedorComVendas getByIdComVendasPorPeriodo(
    @PathVariable Long id,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date de,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date ate
  ) throws Exception {

    if (de == null) {
      de = new Date();
    }

    if (ate == null) {
      ate = new Date();
    }

    if (ate.before(de)) {
      throw new Exception("Data \"até\" não pode ser anterior à data \"de\".");
    }

    var localDe = de
      .toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDate();

    var localAte = ate
      .toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDate();

		return vendedorService
      .getVendedorComVendasPorPeriodo(id, localDe, localAte)
      .orElseThrow();
	}

	@PostMapping
	public Vendedor create(@RequestBody Vendedor vendedor) {
		return vendedorRepository.save(vendedor);
	}

}
