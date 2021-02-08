package com.sarthak.producerConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerUsingThreadPools {
	public static void main(String[] args) throws InterruptedException {
			
		final BlockingQueue<Integer> list = new LinkedBlockingQueue<Integer>(2);
		
		//Creating Thread Pool of Size 2
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		Runnable producer = new Runnable() {
			
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
		};
			
			
		Runnable consumer = new Runnable() {
			
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
		};
		
		//Submit and execute producer and consumer
		executor.submit(producer);
		executor.submit(consumer);
		
		//Shutdown executor after threads exit
		executor.shutdown();
	}
}
