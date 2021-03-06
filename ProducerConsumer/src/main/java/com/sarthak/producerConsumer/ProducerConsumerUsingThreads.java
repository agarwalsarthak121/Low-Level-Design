package com.sarthak.producerConsumer;

public class ProducerConsumerUsingThreads {

	public static void main(String[] args) throws InterruptedException {
		final BoundedBuffer buffer = new BoundedBuffer();
			
		//Create Producer Thread
		Thread producer = new Thread(new Runnable() {
			
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
			
		//Create Consumer Thread
		Thread consumer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try
				{
					while(true) {
						int value = buffer.poll();
						System.out.println("Value Consumed : " + value);
						Thread.sleep(1000);
					}
				}
				catch (InterruptedException e) {
				}
			}
		});
		
		//Start both the threads
		producer.start();
		consumer.start();
		
		//Producer will end before consumer
		producer.join();
		consumer.join();
	}
}
