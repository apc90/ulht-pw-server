package com.ulht.pw.repository;

import java.util.Optional;

import com.ulht.pw.domain.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ulht.pw.domain.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

	Optional<ClientEntity> findById(Long clientId);
	Optional<ClientEntity> findByNif(Long clientNif);
}
