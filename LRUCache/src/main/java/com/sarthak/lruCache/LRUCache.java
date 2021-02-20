package com.sarthak.lruCache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	
	private Map<String,Node> keyValueStore;
	private Node start;
	private Node end;
	private int capacity = 5;
	private int size;
	
	public LRUCache() {
		keyValueStore = new HashMap<>();
		size = 0;
	}
	
	public void insertValue(String key, int value) {
		
		if(keyValueStore.containsKey(key))
		{
			Node node = keyValueStore.get(key);
			keyValueStore.remove(key);
			removeNode(node);
			addToTop(key, value);
		}
		else
		{
			if(size == capacity)
			{
				keyValueStore.remove(end.getKey());
				removeNode(end);
				addToTop(key, value);
			}
			else
			{
				addToTop(key, value);
				size++;
			}
		}
		
		keyValueStore.put(key, start);
		
	}
	
	public Integer getValue(String key) {
		if(keyValueStore.containsKey(key)) {
			removeNode(keyValueStore.get(key));
			addToTop(key, keyValueStore.get(key).getValue());
			return keyValueStore.get(key).getValue();
		}
		
		return null;
	}
	
	private void removeNode(Node node) {
		Node prev = node.getPrev();
		Node next = node.getNext();
		
		if(prev == null)
		{
			start = next;
			if(next == null)
				end = null;
			else
				next.setPrev(null);
		}
		else
		{
			prev.setNext(next);
			if(next == null)
				end = prev;
			else
				next.setPrev(prev);
		}
	}
	
	private void addToTop(String key, int value) {
		Node node = new Node(key, value);
		node.setNext(start);
		node.setPrev(null);
		
		if(start != null)
			start.setPrev(node);
		
		start = node;
		
		if(end == null)
			end = start;
	}
		
}
