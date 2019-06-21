package com.ulht.pw.service;

import com.ulht.pw.controller.rest.errors.ResourceNotFoundException;
import com.ulht.pw.domain.SalesEntity;
import com.ulht.pw.dto.sales.SalesDTO;
import com.ulht.pw.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class SalesService {

	private static final String DOMAIN_NAME = "SalesEntity";

	private final SalesRepository salesRepository;

	private final MapperFacade mapper;

	public SalesDTO searchSalesById(Long salesId) {
		SalesEntity sales = salesRepository.findById(salesId)
				.orElseThrow(() -> new ResourceNotFoundException(DOMAIN_NAME, "id", salesId));
		return mapper.map(sales, SalesDTO.class);
	}

	public List<SalesDTO> findAllSaless() {
		return mapper.mapAsList(salesRepository.findAll(), SalesDTO.class);
	}

	@Transactional
	public SalesDTO createSales(SalesDTO sales) {
		SalesEntity salesEntity = handleSalesSave(sales);
		return mapper.map(salesRepository.save(salesEntity), SalesDTO.class);
	}

	@Transactional
	public SalesDTO updateSales(SalesDTO sales) {
		SalesEntity salesEntity = handleSalesSave(sales);
		return mapper.map(salesRepository.save(salesEntity), SalesDTO.class);
	}

	private SalesEntity handleSalesSave(SalesDTO sales) {
		SalesEntity salesEntity = mapper.map(sales, SalesEntity.class);
		salesEntity.getProducts().forEach(p -> p.addSales(salesEntity));
		return salesEntity;
	}

	@Transactional
	public void deleteSalesById(Long salesId) {
		salesRepository.findById(salesId).ifPresent(sales -> {
			salesRepository.delete(sales);
			log.debug("Deleted Sales: {}", sales);
		});
	}
}
