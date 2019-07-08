package com.ulht.pw.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private ClientEntity client;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "product_sales",
			joinColumns = @JoinColumn(name = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "sales_id")
	)
	private List<ProductEntity> products = new ArrayList<>();

	public void addProduct (ProductEntity p){
		products.add(p);
	}
	@Override
	public String toString(){
		return "Sale " + this.getId();
	}



}
