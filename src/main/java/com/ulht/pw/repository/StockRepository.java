package com.ulht.pw.repository;

import com.ulht.pw.domain.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {

	Optional<StockEntity> findById(Long stockId);
}
