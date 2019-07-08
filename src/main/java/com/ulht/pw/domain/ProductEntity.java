package com.ulht.pw.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String productName;
	private String productCode;
	private String desription;
	private double price;
	private int quantity;
	private LocalDate expireDate;
	private String brand;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProductPrecautionsEntity> productPrecautions = new HashSet<>();

	@ManyToMany(mappedBy = "products")
	private Set<SalesEntity> salesEntities = new HashSet<>();

}
