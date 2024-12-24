package com.example.demo.dto;

public class CommentsDto {
    private Long commentId; // 댓글 ID 추가
    private Long boardId; // 댓글을 추가할 게시글 ID
    private String c_content; // 댓글 내용

    // 기본 생성자
    public CommentsDto() {
    }

    // 매개변수를 받는 생성자
    public CommentsDto(Long commentId, String c_content) {
        this.commentId = commentId;
        this.c_content = c_content;
    }

    // Getter와 Setter
    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getc_Content() {
        return c_content;
    }

    public void setc_content(String c_content) {
        this.c_content = c_content;
    }
}
