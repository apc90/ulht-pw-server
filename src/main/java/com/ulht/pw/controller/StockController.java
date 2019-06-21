package com.ulht.pw.controller;

import com.ulht.pw.controller.rest.errors.InvalidCreateException;
import com.ulht.pw.controller.rest.errors.InvalidUpdateException;
import com.ulht.pw.dto.product.StockDTO;
import com.ulht.pw.service.StockService;
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
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {

	private static final String ENTITY_NAME = "stockEntity";

	private final StockService stockService;

	@GetMapping("/{id}")
	public ResponseEntity<StockDTO> searchStockById(@PathVariable(value = "id") Long stockId) {
		log.debug("REST request to get Stock with Id : {}", stockId);
		return ResponseEntity.ok().body(stockService.searchStockById(stockId));
	}

	@GetMapping("/list")
	public ResponseEntity<List<StockDTO>> getAllStocks() {
		log.debug("REST request to get all Stocks");
		return ResponseEntity.ok().body(stockService.findAllStocks());
	}

	@PostMapping("/save")
	public ResponseEntity<StockDTO> createStock(@Valid @RequestBody StockDTO stock) throws URISyntaxException {
		log.debug("REST request to save Stock : {}", stock);
		if (!stock.isNew()) {
			throw new InvalidCreateException(ENTITY_NAME);
		}

		StockDTO result = stockService.createStock(stock);
		return ResponseEntity.created(new URI("/api/stock/" + result.getId())).body(result);
	}

	@PutMapping("/update")
	public ResponseEntity<StockDTO> updateStock(@Valid @RequestBody StockDTO stock) throws URISyntaxException {
		log.debug("REST request to save StockEntity : {}", stock);
		if (stock.isNew()) {
			throw new InvalidUpdateException(ENTITY_NAME);
		}

		StockDTO result = stockService.updateStock(stock);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteStock(@PathVariable(value = "id") Long id) {
		log.debug("REST request to delete Stock : {}", id);
		stockService.deleteStockById(id);
		return ResponseEntity.ok().build();
	}

}
