package com.sarthak.lruCache;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
	
	private String key;
	private int value;
	private Node prev;
	private Node next;
	
	public Node(String key, int value) {
		this.key = key;
		this.value = value;
	}
}
