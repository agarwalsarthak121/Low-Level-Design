package com.sarthak.producerConsumer;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Custom Blocking Queue
 */
public class BoundedBuffer {
	
	private Queue<Integer> list = new LinkedList<Integer>();
	private int capacity = 2;
	
	//Produce Data to Queue
	public void add(int value) throws InterruptedException {
		synchronized (this) {
			while(list.size() == capacity)
				wait();
			
			list.add(value);
			notify();
		}
	}
	
	//Consume data from Queue
	public int poll() throws InterruptedException{
		synchronized (this) {
			while(list.size() == 0)
				wait();
			
			int value = list.poll();
			
			notify();
			return value;
		}
	}
}
