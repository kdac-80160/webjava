package com.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDTO;
import com.app.dto.UpdateProductPriceDTO;
import com.app.services.ProductService;
import com.sun.istack.NotNull;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService prodService;
	
	@PostMapping("/{catId}")
	public ProductDTO addProduct(@PathVariable @Validated @NotNull Integer catId, @RequestBody @Valid ProductDTO productDTO)
	{
		return prodService.addProduct(catId, productDTO);
	}
	
	@GetMapping("/{catName}")
	public List<ProductDTO> getProductsByCategory(@PathVariable String catName)
	{
		return prodService.getProductsByCategory(catName);
	}
	
	@DeleteMapping("/{prodId}")
	public ApiResponse deleteProductById(@PathVariable Integer prodId)
	{
		return prodService.deleteProductById(prodId);
	}
	
	@PutMapping("/")
	public ApiResponse updateProductPrice(@RequestBody UpdateProductPriceDTO upDto)
	{
		return prodService.updateProductPrice(upDto);
	}
}
