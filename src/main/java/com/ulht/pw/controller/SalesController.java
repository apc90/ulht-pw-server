package com.ulht.pw.controller;

import com.ulht.pw.controller.rest.errors.InvalidCreateException;
import com.ulht.pw.controller.rest.errors.InvalidUpdateException;
import com.ulht.pw.dto.sales.SalesDTO;
import com.ulht.pw.service.SalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SalesController {

	private static final String ENTITY_NAME = "salesEntity";

	private final SalesService salesService;

	@GetMapping("/{id}")
	public ResponseEntity<SalesDTO> searchSalesById(@PathVariable(value = "id") Long salesId) {
		log.debug("REST request to get Sales with Id : {}", salesId);
		return ResponseEntity.ok().body(salesService.searchSalesById(salesId));
	}

	@GetMapping("/list")
	public ResponseEntity<List<SalesDTO>> getAllSaless() {
		log.debug("REST request to get all Saless");
		return ResponseEntity.ok().body(salesService.findAllSaless());
	}

	@PostMapping("/save")
	public ResponseEntity<SalesDTO> createSales(@Valid @RequestBody SalesDTO sales) throws URISyntaxException {
		log.debug("REST request to save Sales : {}", sales);
		if (!sales.isNew()) {
			throw new InvalidCreateException(ENTITY_NAME);
		}

		SalesDTO result = salesService.createSales(sales);
		return ResponseEntity.created(new URI("/api/sales/" + result.getId())).body(result);
	}

	@PutMapping("/update")
	public ResponseEntity<SalesDTO> updateSales(@Valid @RequestBody SalesDTO sales) throws URISyntaxException {
		log.debug("REST request to save SalesEntity : {}", sales);
		if (sales.isNew()) {
			throw new InvalidUpdateException(ENTITY_NAME);
		}

		SalesDTO result = salesService.updateSales(sales);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteSales(@PathVariable(value = "id") Long id) {
		log.debug("REST request to delete Sales : {}", id);
		salesService.deleteSalesById(id);
		return ResponseEntity.ok().build();
	}

}
