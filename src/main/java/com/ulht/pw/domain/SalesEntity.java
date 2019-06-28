package com.ulht.pw.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SalesEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private int quantity;
	private double total;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", nullable = false)
	private ClientEntity client;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	@ManyToMany(mappedBy = "sales")
	private Set<ProductEntity> products = new HashSet<>();

	public void addProduct (ProductEntity p){
		products.add(p);
	}



}
