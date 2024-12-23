package com.example.demo.dto;

/**
 * 게시글 정보를 저장하고 전달하기 위한 DTO
 */
public class BoardResponseDto {
	private Long boardId;
	private String title;
	private String content;

	public BoardResponseDto(Long boardId, String title,String content) {
		this.boardId = boardId;
		this.title = title;
		this.content = content;
	}

	public Long getId() {
		return this.boardId;
	}

	public String getTitle() {
		return this.title;
	}
	
	public String getContent() {
		return this.content;
	}
}
