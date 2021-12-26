package com.sarthak.lruCache;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
	
	String key;
	int value;
	Node prev;
	Node next;
	
	public Node(String key, int value) {
		this.key = key;
		this.value = value;
	}
}
