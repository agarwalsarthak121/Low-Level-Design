package com.sarthak.lruCache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	
	private Map<String,Node> keyValueStore;
	private Node start;
	private Node end;
	private int capacity;
	private int size;
	
	public LRUCache(int capacity) {
		keyValueStore = new HashMap<>();
		size = 0;
		this.capacity = capacity;
	}
	
	public void put(String key, int value) {
		
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
				keyValueStore.remove(end.key);
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
			addToTop(key, keyValueStore.get(key).value);
			return keyValueStore.get(key).value;
		}
		
		return null;
	}
	
	private void removeNode(Node node) {
		Node prev = node.prev;
		Node next = node.next;
		
		if(prev == null)
		{
			start = next;
			if(next == null)
				end = null;
			else
				next.prev = null;
		}
		else
		{
			prev.next = next;
			if(next == null)
				end = prev;
			else
				next.prev = prev;
		}
	}
	
	private void addToTop(String key, int value) {
		Node node = new Node(key, value);
		node.next = start;
		node.prev = null;
		
		if(start != null)
			start.prev = node;
		
		start = node;
		
		if(end == null)
			end = start;
	}
		
}
