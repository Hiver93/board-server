package com.example.demo;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.example.demo.domain.badword.BadwordTrie;

public class Tmp {

	@Test
	public void test() {
		BadwordTrie t = new BadwordTrie(Set.of("bad"));
		System.out.println(t.maskBadword("badcontentbad", '*'));
	}
}
