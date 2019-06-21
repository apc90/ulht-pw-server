package com.ulht.pw.repository;

import com.ulht.pw.domain.ProductEntity;
import com.ulht.pw.domain.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalesRepository extends JpaRepository<SalesEntity, Long> {

	Optional<SalesEntity> findById(Long salesId);
}
