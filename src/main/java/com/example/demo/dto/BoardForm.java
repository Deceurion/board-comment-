package com.example.demo.dto;

/**
 * 게시글 이름을 저장 하고 전달하는 DTO
 */
public class BoardForm {
	private String title;
	private String content;
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
