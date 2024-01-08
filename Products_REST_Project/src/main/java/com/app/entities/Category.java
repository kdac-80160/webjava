package com.app.entities;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "category")
@Getter
@Setter
@ToString
public class Category extends BaseEntity {
	@Column(length = 30, unique = true)
	private String name;
	
	@Column(length=100)
	private String description;
}
