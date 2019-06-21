package com.ulht.pw.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stock")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StockEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private int quantity;
	private boolean commissioned;

	@OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProductEntity> products = new HashSet<>();



}
