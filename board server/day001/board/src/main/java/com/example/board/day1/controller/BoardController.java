package com.example.board.day1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.day1.dto.Board;
import com.example.board.day1.service.BoardService;

@RestController
@RequestMapping("/boards")
@CrossOrigin(origins = "*")
public class BoardController {
	
	private final BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@PostMapping
	public void createBoard(Board board) {
		System.out.println(board);
		this.boardService.createBoard(board);
	}
	
	@GetMapping
	public List<Board> getBoardList(){
		return this.boardService.getBoardList();
	}
}
