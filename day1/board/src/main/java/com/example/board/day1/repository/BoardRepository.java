package com.example.board.day1.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.board.day1.dto.Board;

@Repository
public class BoardRepository {
	Map<Long, Board> boards = new HashMap<>();
	
	public void save(Board board) {
		board.setId(boards.size()+1);
		this.boards.put(board.getId(), board);
	}

	public List<Board> findAll() {
		List<Board> boardList = new ArrayList<>();
		for(var entry : boards.entrySet()) {
			boardList.add(entry.getValue());
		}
		return boardList;
	}

}
