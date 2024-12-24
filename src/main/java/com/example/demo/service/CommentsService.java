package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dto.ApiResponseDto;
import com.example.demo.dto.BoardForm;
import com.example.demo.dto.CommentsDto;
import com.example.demo.repository.CommentsRepository;
import com.example.demo.repository.BoardRepository;
import com.example.demo.domain .Comments;
import com.example.demo.domain .Board;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
public class CommentsService {
    
    private final CommentsRepository commentsRepository;
    private final BoardRepository boardRepository;

    @Autowired
    public CommentsService(CommentsRepository commentsRepository, BoardRepository boardRepository) {
        this.commentsRepository = commentsRepository;
        this.boardRepository = boardRepository;
    }

    public ApiResponseDto createComment(CommentsDto commentsDto) {
        // 게시글 조회
        Board board = boardRepository.findById(commentsDto.getBoardId())
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        // 댓글 생성
        Comments comments = new Comments(commentsDto.getc_Content(), board);
        commentsRepository.save(comments);
        
        return new ApiResponseDto("댓글 생성 성공");
    }

    public CommentsDto getComments(Long boardId, Long commentId) {
        // 댓글 조회
        Comments comments = commentsRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 없습니다."));
        
        // 게시글 ID와 댓글의 게시글 ID가 일치하는지 확인
        if (!comments.getBoard().getboardId().equals(boardId)) {
            throw new IllegalArgumentException("이 댓글은 해당 게시글에 속하지 않습니다.");
        }

        return new CommentsDto(comments.getCommentId(), comments.getc_content(), boardId);
    }
    
    public ApiResponseDto deleteComments(Long boardId, Long commentId) {
    	
    	Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
    	
    	
    	Comments comments = commentsRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 없습니다."));
        
        // 게시글 ID와 댓글의 게시글 ID가 일치하는지 확인
        if (!comments.getBoard().getboardId().equals(boardId)) {
            throw new IllegalArgumentException("이 댓글은 해당 게시글에 속하지 않습니다.");
        }
        commentsRepository.deleteById(commentId);
        return new ApiResponseDto("댓글 삭제 성공");
    }
    
    public CommentsDto updateComments(Long boardId, Long commentId, CommentsDto commentsDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        
        Comments comments = commentsRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 없습니다."));
        
        // 게시글 ID와 댓글의 게시글 ID가 일치하는지 확인
        if (!comments.getBoard().getboardId().equals(boardId)) {
            throw new IllegalArgumentException("이 댓글은 해당 게시글에 속하지 않습니다.");
        }
        
        // 댓글 내용 업데이트
        comments.setc_content(commentsDto.getc_Content());
        commentsRepository.save(comments);
        
        return new CommentsDto(comments.getCommentId(), comments.getc_content(), boardId);
    }
 
    public List<CommentsDto> getAllComments() {
    	
    	List<Comments> commentsList = commentsRepository.findAll();
    	
    	List<CommentsDto> dtoLits = new ArrayList<>();
    	
    	for (Comments comments : commentsList) {
    		
    		Long boardId = comments.getBoard() != null ? comments.getBoard().getboardId() : null;
    		dtoLits.add(new CommentsDto(comments.getCommentId(), comments.getc_content(), boardId));
    	}
    	
    	return dtoLits;
    }
}
