package com.example.board.day1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.board.day1.dto.Board;
import com.example.board.day1.repository.BoardRepository;

@Service
public class BoardService {
	
	private BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public void createBoard(Board board) {
		this.boardRepository.save(board);
	}

	public List<Board> getBoardList() {
		return this.boardRepository.findAll();
	}
}
