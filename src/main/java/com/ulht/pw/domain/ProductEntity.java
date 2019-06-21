package com.ulht.pw.domain;

import java.time.LocalDate;
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
	private LocalDate expireDate;
	private String brand;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProductPrecautionsEntity> productPrecautions = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_id", nullable = false)
	private StockEntity stock;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "product_sales", joinColumns = {
			@JoinColumn(name = "sales_id", referencedColumnName = "id") }, inverseJoinColumns = {
			@JoinColumn(name = "product_id", referencedColumnName = "id") })

	@BatchSize(size = 20)
	private Set<SalesEntity> sales = new HashSet<>();

	public void addSales(SalesEntity s){
		sales.add(s);
	}

}
