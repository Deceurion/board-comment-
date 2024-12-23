package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comments {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId; // 댓글 고유 ID
    
    private String c_content;
    
    @ManyToOne
    @JoinColumn(name = "board_id") // 외래키 컬럼 지정
    private Board board; // 상위 게시글 참조

    // 기본 생성자
    public Comments() {
    }

    // 매개변수를 받는 생성자
    public Comments(String c_content, Board board) {
        this.c_content = c_content;
        this.board = board;
    }

    public Long getCommentId() {
        return this.commentId;
    }

    public String getCContent() {
        return this.c_content;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
