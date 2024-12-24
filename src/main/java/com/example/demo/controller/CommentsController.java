package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.service.CommentsService;
import com.example.demo.dto.ApiResponseDto;
import com.example.demo.dto.CommentsDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/boards")
public class CommentsController {
    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    //댓글 생성
    @PostMapping("/{boardId}")
    public ResponseEntity<ApiResponseDto> createComments(@PathVariable("boardId") Long boardId, @RequestBody CommentsDto commentsDto) {
        commentsDto.setBoardId(boardId);
        ApiResponseDto responseDto = commentsService.createComment(commentsDto);
        return ResponseEntity.ok().body(responseDto);
    }
    
    // 댓글 조회
    @GetMapping("/{boardId}/comments/{commentId}") // 게시글 ID와 댓글 ID로 조회
    public ResponseEntity<CommentsDto> getComments(@PathVariable("boardId") Long boardId, @PathVariable("commentId") Long commentId) {
    	CommentsDto responseDto = commentsService.getComments(boardId, commentId);
        return ResponseEntity.ok().body(responseDto);
    }
    
    // 댓글 삭제
    @DeleteMapping("/{boardId}/comments/{commentId}")
    public ResponseEntity<ApiResponseDto> deleteComments(@PathVariable("boardId") Long boardId, @PathVariable("commentId") Long commentId) {
    	ApiResponseDto responseDto = commentsService.deleteComments(boardId, commentId);
    	return ResponseEntity.ok().body(responseDto);
    }
    
    //댓글 수정
    @PutMapping("/{boardId}/comments/{commentId}")
    public ResponseEntity<CommentsDto> updateComments(@PathVariable("boardId") Long boardId, @PathVariable("commentId") Long commentId, @RequestBody CommentsDto commentsDto) {
    	CommentsDto responseDto = commentsService.updateComments (boardId, commentId, commentsDto);
    	return ResponseEntity.ok().body(responseDto);		
    }
    
    //댓글 모두 조회
    @GetMapping("/comments")
    public ResponseEntity<List<CommentsDto>> getAllComments() {
    	List<CommentsDto> responseDtoList = commentsService.getAllComments();
    	return ResponseEntity.ok().body(responseDtoList);
    }
    

}
