package com.example.demo.service;

import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dto.ApiResponseDto;
import com.example.demo.dto.BoardResponseDto;
import com.example.demo.repository.BoardRepository;
import com.example.demo.dto.BoardForm;
import com.example.demo.domain.Board;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;


@Transactional
@Service
public class BoardService {
	private final BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

    public ApiResponseDto createBoard(BoardForm boardForm) {
        // 제목과 내용을 모두 포함하는 Board 객체 생성
        Board board = new Board(boardForm.getTitle(), boardForm.getContent());
        
        // Board 객체를 저장
        boardRepository.save(board);
        
        return new ApiResponseDto("생성 성공");
    }
    
    public BoardResponseDto getBoard(Long boardId) {
        
    	Optional<Board> optionalBoard = boardRepository.findById(boardId);
        
        Board board = optionalBoard.orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
 
        return new BoardResponseDto(board.getboardId(), board.getTitle(), board.getContent());
    }
    
    public ApiResponseDto deleteBoard(Long boardId) {
        try {
        	
        	boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
        	
        }catch(IllegalArgumentException e) {
        	
        	return new ApiResponseDto(e.getMessage());
        }
         
        boardRepository.deleteById(boardId);
        return new ApiResponseDto("게시글 삭제 완료");
    }
    
    public BoardResponseDto updateBoard(Long boardId, BoardForm boardForm) {
        
    	Optional<Board> optionalBoard = boardRepository.findById(boardId);
    	Board board = optionalBoard.orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
    	board.update(boardForm.getTitle(),boardForm.getContent());
        return new BoardResponseDto(board.getboardId(), board.getTitle(), board.getContent());
    }
    
    public List<BoardResponseDto> getAllBoard() {
    	
    	List<Board> boardList = boardRepository.findAll();
    	
    	List<BoardResponseDto> dtoList = new ArrayList<>();
    	
    	for(Board board : boardList) {
    		
    		dtoList.add(new BoardResponseDto(board.getboardId(), board.getTitle(), board.getContent()));
    		
    	}      
        return dtoList;
    	
    }
}
