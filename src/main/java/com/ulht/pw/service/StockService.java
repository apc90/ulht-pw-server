package com.ulht.pw.service;

import com.ulht.pw.controller.rest.errors.ResourceNotFoundException;
import com.ulht.pw.domain.StockEntity;
import com.ulht.pw.dto.product.StockDTO;
import com.ulht.pw.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class StockService {

	private static final String DOMAIN_NAME = "StockEntity";

	private final StockRepository stockRepository;

	private final MapperFacade mapper;

	public StockDTO searchStockById(Long stockId) {
		StockEntity stock = stockRepository.findById(stockId)
				.orElseThrow(() -> new ResourceNotFoundException(DOMAIN_NAME, "id", stockId));
		return mapper.map(stock, StockDTO.class);
	}

	public List<StockDTO> findAllStocks() {
		return mapper.mapAsList(stockRepository.findAll(), StockDTO.class);
	}

	@Transactional
	public StockDTO createStock(StockDTO stock) {
		StockEntity stockEntity = handleStockSave(stock);
		return mapper.map(stockRepository.save(stockEntity), StockDTO.class);
	}

	@Transactional
	public StockDTO updateStock(StockDTO stock) {
		StockEntity stockEntity = handleStockSave(stock);
		return mapper.map(stockRepository.save(stockEntity), StockDTO.class);
	}

	private StockEntity handleStockSave(StockDTO stock) {
		StockEntity stockEntity = mapper.map(stock, StockEntity.class);
		stockEntity.getProducts().forEach(p -> p.setStock(stockEntity));
		return stockEntity;
	}

	@Transactional
	public void deleteStockById(Long stockId) {
		stockRepository.findById(stockId).ifPresent(stock -> {
			stockRepository.delete(stock);
			log.debug("Deleted Stock: {}", stock);
		});
	}
}
