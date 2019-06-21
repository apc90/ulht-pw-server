package com.ulht.pw.dto.sales;

import com.ulht.pw.dto.BaseDTO;
import com.ulht.pw.dto.client.ClientDTO;
import com.ulht.pw.dto.product.ProductDTO;
import com.ulht.pw.dto.user.UserDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SalesDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private UserDTO user;
	private ClientDTO client;
	private List<ProductDTO> products = new ArrayList<>();
	private int qty;
}
