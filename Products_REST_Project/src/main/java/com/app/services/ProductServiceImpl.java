package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.CategoryDao;
import com.app.dao.ProductDao;
import com.app.dto.ApiResponse;
import com.app.dto.ProductDTO;
import com.app.dto.UpdateProductPriceDTO;
import com.app.entities.Category;
import com.app.entities.Products;
import com.app.exceptions.ResourceNotFoundException;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ProductDao prodDao;
	@Autowired
	private CategoryDao catDao;

	@Override
	public ProductDTO addProduct(Integer catId, ProductDTO productDTO) {
		Category category = catDao.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid category id!"));
		Products product = mapper.map(productDTO, Products.class);
		product.setCategory(category);
		return mapper.map(prodDao.save(product), ProductDTO.class);
	}

	@Override
	public List<ProductDTO> getProductsByCategory(String category) {
		Category c = catDao.findByName(category);
		return prodDao.findAllByCategory(c).stream().map(p -> mapper.map(p, ProductDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse deleteProductById(Integer id) {
		Products product = prodDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id does not exist."));
		prodDao.delete(product);
		return new ApiResponse("Product deleted successfully.");
	}

	@Override
	public ApiResponse updateProductPrice(UpdateProductPriceDTO upDto) {
		Products product = prodDao.findById(upDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Id does not exist"));
		product.setPrice(upDto.getPrice());
		return new ApiResponse("Price updated successfully.");
	}
}
