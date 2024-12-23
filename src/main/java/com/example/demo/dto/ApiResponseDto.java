package com.example.demo.dto;

/**
 * 응답 시 간단히 메시지 전달을 위한 DTO
 */
public class ApiResponseDto {
	private String message;

	public ApiResponseDto(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}
