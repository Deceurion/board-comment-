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

    // 게시글 생성
    public ApiResponseDto createBoard(Board board) {
        boardRepository.save(board);
        return new ApiResponseDto("생성 성공");
    }

    // 특정 게시글 조회
    public BoardResponseDto getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
        return new BoardResponseDto(board.getBoardId(), board.getTitle(), board.getContent());
    }

    // 게시글 삭제
    public ApiResponseDto deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
        return new ApiResponseDto("게시글 삭제 완료");
    }


    // 게시글 수정
    public BoardResponseDto updateBoard(Long boardId, BoardForm boardForm) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
        board.update(boardForm.getTitle(), boardForm.getContent());
        return new BoardResponseDto(board.getBoardId(), board.getTitle(), board.getContent());
    }

    // 모든 게시글 조회
    public List<BoardResponseDto> getAllBoards() {
        List<Board> boardList = boardRepository.findAll();
        System.out.println("게시글 수: " + boardList.size()); // 게시글 수 로그
        List<BoardResponseDto> dtoList = new ArrayList<>();

        for (Board board : boardList) {
            dtoList.add(new BoardResponseDto(board.getBoardId(), board.getTitle(), board.getContent()));
        }
        return dtoList;
    }

}

