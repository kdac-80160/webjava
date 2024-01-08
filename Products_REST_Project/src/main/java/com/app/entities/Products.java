package com.app.entities;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="products")
@Getter
@Setter
@ToString
public class Products extends BaseEntity {
	@Column(length = 30, unique = true)
	private String name;
	
	@Column(length = 100)
	private String description;
	
	@ManyToOne
	@JoinColumn(name="cat_id")
	private Category category;
	
	private double price;
	
	@Column(name = "available_stock")
	private int availableStock;
	
	@Column(name="expiry_date")
	private LocalDate expiryDate;
	
	public String getCatName()
	{
		return category.getName();
	}
	
}
