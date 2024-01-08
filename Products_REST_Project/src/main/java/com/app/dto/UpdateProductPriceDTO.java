package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
public class UpdateProductPriceDTO {
	
	private Integer id;
	
	@JsonProperty(value="newPrice")
	private double price;
}
