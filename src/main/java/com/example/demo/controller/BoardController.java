package com.example.demo.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.stereotype.Controller;
import com.example.demo.service.BoardService;
import com.example.demo.dto.BoardForm;
import com.example.demo.domain.Board;
import com.example.demo.dto.BoardResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/boards") // 공통 URL 패턴 설정
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    
    // 게시글 작성 폼으로 이동
    @GetMapping("/new")
    public String createForm() {
        return "boards/createBoardForm"; // 게시글 작성 폼
    }
    
    //게시글 내용 추가
    @PostMapping("/new")
    public String create(BoardForm form) {
        Board board = new Board(form.getTitle(), form.getContent()); // 내용 추가
        boardService.createBoard(board); // Board 객체를 서비스로 전달
        return "redirect:/"; // 게시글 목록으로 리다이렉션
    }
    
    //게시글 제목 조회
    @GetMapping("/boardsList")
    public String list(Model model) {
        List<BoardResponseDto> boardList = boardService.getAllBoards(); // 게시글 목록 조회
        model.addAttribute("boards", boardList); // 모델에 추가
        return "boards/boardsList"; // 게시글 목록 페이지
    }
    
    //게시글 내용 조회
    @GetMapping("/{id}")
    public String viewBoard(@PathVariable("id") Long id, Model model) {
        BoardResponseDto board = boardService.getBoard(id); // 게시글 정보 조회
        model.addAttribute("board", board); // 모델에 게시글 추가
        return "boards/viewBoard"; // 게시글 상세 페이지로 이동
    }
    
    // 게시글 수정
    @GetMapping("/boardUpdate/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        BoardResponseDto board = boardService.getBoard(id); // 게시글 정보 조회
        model.addAttribute("board", board); // 모델에 게시글 추가
        return "boards/updateBoard"; // 수정 페이지로 이동
    }
    
    @PostMapping("/boardUpdate/{id}") // POST 요청으로 수정 처리
    public String updateBoard(@PathVariable("id") Long id, @ModelAttribute BoardForm boardForm) {
        boardService.updateBoard(id, boardForm); // 게시글 수정
        return "redirect:/boards/" + id; // 수정된 게시글의 상세 페이지로 리다이렉트
    }
        
    // 게시글 삭제
    @PostMapping("/boardDelete/{id}") // POST 요청으로 변경
    public String deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return "redirect:/boards/boardsList"; // 삭제 후 목록 페이지로 리다이렉트
    }
    
}
