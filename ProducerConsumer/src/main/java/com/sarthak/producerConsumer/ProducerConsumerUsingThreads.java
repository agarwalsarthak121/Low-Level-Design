package com.sarthak.producerConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerUsingThreads {

	public static void main(String[] args) throws InterruptedException {
			final Buffer buffer = new Buffer();
			
			Thread t1 = new Thread(new Runnable() {
				
				@Override
				public void run() {
					int value = 0;
					try
					{
						while(true) {
							buffer.add(value);
							System.out.println("Value Produced : " + value);
							value++;
							Thread.sleep(1000);
						}
					}
					catch(InterruptedException e) {
						
					}
				}
			});
			
			
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try
				{
					while(true) {
						int value;
						value = buffer.poll();
						System.out.println("Value Consumed : " + value);
						Thread.sleep(1000);
					}
				}
				catch (InterruptedException e) {
				}
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}
}

class Buffer
{
	private Queue<Integer> list = new LinkedList<Integer>();
	private int capacity = 2;
	
	
	public void add(int value) throws InterruptedException {
		synchronized (this) {
			while(list.size() == capacity)
				wait();
			
			list.add(value);
			notify();
			
		}
	}
	
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
