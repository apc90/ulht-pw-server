package com.ulht.pw.dto.product;

import com.ulht.pw.dto.BaseDTO;
import com.ulht.pw.dto.client.ClientDTO;
import com.ulht.pw.dto.user.UserDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StockDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private List<ProductDTO> products = new ArrayList<>();
	private int qty;
	private boolean commissioned;
}
