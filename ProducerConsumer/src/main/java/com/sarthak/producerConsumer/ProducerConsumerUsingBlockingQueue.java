package com.sarthak.producerConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerUsingBlockingQueue {
	public static void main(String[] args) throws InterruptedException {
		
		//Bounded Blocking Queue of capacity 2
		final BlockingQueue<Integer> list = new LinkedBlockingQueue<Integer>(2);
		
		Thread producer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int value = 0;
				try
				{
					while(true) {
						list.add(value);
						System.out.println("Value Produced : " + value);
						value++;
						Thread.sleep(1000);
					}
				}
				catch(InterruptedException e) {
					
				}
			}
		});
		
		
	Thread consumer = new Thread(new Runnable() {
		
		@Override
		public void run() {
			try
			{
				while(true) {
					int value;
					value = list.take();
					System.out.println("Value Consumed : " + value);
					Thread.sleep(1000);
				}
			}
			catch (InterruptedException e) {
			}
		}
	});
	
	producer.start();
	consumer.start();
	
	producer.join();
	consumer.join();
}
}
