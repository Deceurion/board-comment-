package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId; // 기본 키 이름 변경

    private String title;
    private String content;

    // 기본 생성자
    public Board() {
    }

    // 매개변수를 받는 생성자
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Long getBoardId() {
        return this.boardId; // getter 메서드 수정
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // setId 메서드도 수정
    public void setId(Long boardId) {
        this.boardId = boardId;
    }
}
