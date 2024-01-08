package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
	
	@JsonProperty(value="product_id",access = Access.READ_ONLY)
	private Integer id;
	
	@NotBlank
	private String name;
	
	private String description;
	
	@Range(min = 0, max = 499)
	private double price;
	
	private int availableStock;
	
	@Future
	private LocalDate expiryDate;
	
	@JsonProperty(access = Access.READ_ONLY)
	private String catName;
}
