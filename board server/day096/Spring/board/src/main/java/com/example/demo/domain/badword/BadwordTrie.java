package com.example.demo.domain.badword;

import java.util.Collection;

public class BadwordTrie {

	private Node root = new Node();
	
	public BadwordTrie(Collection<String> badwords) {
		for(String pattern : badwords) {
			this.addPattern(pattern);
		}
	}
	
	private void addPattern(String pattern) {
		Node cur = root;
		for(int i = 0; i < pattern.length(); ++i) {
			char ch = pattern.charAt(i);
			if(!cur.isExists(ch)) {
				cur.addChar(ch);
			}
			cur = cur.getNode(ch);
		}
		cur.setPattern(pattern);
	}
	
	public String maskBadword(String str, char replacement) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while(i < str.length()) {
			int len = this.findPatternLength(str, i);
			for(int j = 0; j < len; ++j) {
				sb.append(replacement);
			}
			if(len == 0) {
				sb.append(str.charAt(i));
				i++;
			}
			else {
				i += len;
			}
		}
		return sb.toString();
	}
	
	private int findPatternLength(String str, int startIdx) {
		int i = startIdx;
		Node cur = root;
		while(i < str.length() && cur.isExists(str.charAt(i))) {
			if(cur.hasPattern()) {
				return cur.getPattern().length();
			}
			cur = cur.getNode(str.charAt(i++));
		}
		if(cur.hasPattern()) {
			return cur.getPattern().length();
		}
		return 0;
	}
}
