package com.ulht.pw.dto.product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import com.ulht.pw.dto.BaseDTO;

import com.ulht.pw.dto.sales.SalesDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String productName;
	private String productCode;
	private String desription;
	private double price;
	private int quantity;
	private LocalDate expireDate;
	private String brand;
	private List<ProductPrecautionsDTO> productPrecautions = new ArrayList<>();
	private List<SalesDTO> sales = new ArrayList<>();
}
