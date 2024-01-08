package com.app.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
public class ApiResponse {
	private LocalDateTime stamp;
	private String message;
	
	public ApiResponse(String message) {
		this.stamp = LocalDateTime.now();
		this.message = message;
	}
}
