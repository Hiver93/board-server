package com.example.demo.domain.badword;

import java.util.HashMap;
import java.util.Map;

public class Node {

	private Map<Character, Node> nodes = new HashMap<>();
	private String pattern;
	
	public void addChar(char ch) {
		if(!nodes.containsKey(ch)) {
			nodes.put(ch, new Node());
		}
	}
	
	public boolean isExists(char ch) {
		return nodes.containsKey(ch);
	}
		
	public Node getNode(char ch) {
		return this.nodes.get(ch);
	}
	
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	public boolean hasPattern() {
		return this.pattern != null;
	}
	
	public String getPattern() {
		return this.pattern;
	}
}
