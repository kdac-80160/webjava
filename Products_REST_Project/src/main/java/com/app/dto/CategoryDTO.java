package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.*;

@Getter
@Setter
public class CategoryDTO {

	@JsonProperty(value="categoryId",access = Access.READ_ONLY)
	private Integer id;
	
	private String name;
	
	private String description;
}
