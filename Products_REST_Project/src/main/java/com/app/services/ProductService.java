package com.app.services;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDTO;
import com.app.dto.UpdateProductPriceDTO;

public interface ProductService {
	ProductDTO addProduct(Integer catId,ProductDTO productDTO);

	List<ProductDTO> getProductsByCategory(String category);

	ApiResponse deleteProductById(Integer id);

	ApiResponse updateProductPrice(UpdateProductPriceDTO upDto);
}
