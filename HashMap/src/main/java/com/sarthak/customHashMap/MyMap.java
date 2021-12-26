package com.sarthak.customHashMap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Pair<U,V>{
	U key;
	V value;
	
	public Pair(U key, V value) {
		this.key = key;
		this.value = value;
	}
}

class Bucket{
	
	private List<Pair<Integer,Integer>> bucket;
	
	public Bucket() {
		this.bucket = new LinkedList<Pair<Integer,Integer>>();
	}
	
	public Integer get(Integer key) {
		for(Pair<Integer,Integer> pair : bucket) {
			if(pair.key == key)
				return pair.value;
		}
		
		return null;
	}
	
	public void update(Integer key, Integer value) {
		boolean found = false;
		for(Pair<Integer,Integer> pair : bucket) {
			if(pair.key == key)
				pair.value = value;
				found = true;
		}
		
		if(!found) {
			this.bucket.add(new Pair<Integer,Integer>(key, value));
		}
		
	}
	
	public void remove(Integer key) {
		for(Pair<Integer,Integer> pair : bucket) {
			if(pair.key == key) {
				this.bucket.remove(pair);
				break;
			}
		}
	}
}

public class MyMap {
	
	private List<Bucket> hash_table;
	private int capacity;
	
	public MyMap() {
		this.hash_table = new ArrayList<Bucket>();
		this.capacity = 2069;
		for(int i=0;i<capacity;i++) {
			hash_table.add(new Bucket());
		}
	}
	
	public int get(int key) {
		int hash_key = key % this.capacity;
		return this.hash_table.get(hash_key).get(key);
	}
	
	public void put(int key, int value) {
		int hash_key = key % this.capacity;
		this.hash_table.get(hash_key).update(key, value);
	}
	
	public void remove(int key) {
		int hash_key = key % this.capacity;
		this.hash_table.get(hash_key).remove(key);
	}
}
