package com.sarthak.EvenOdd;

public class PrintNum {
	private int counter=1;
	private int limit;
	
	public PrintNum(int limit) {
		this.limit = limit;
	}
	
	public void printEven() throws InterruptedException {
		while(counter < limit)
		{
			synchronized (this) {
				while(counter%2==1) {
					wait();
				}
				System.out.println("Even Thread : " + counter);
				counter++;
				notify();
			}
		}
		
	}
	
	public void printOdd() throws InterruptedException {
		while(counter < limit)
		{
			synchronized (this) {
				while(counter%2==0) {
					wait();
				}
				
				System.out.println("Odd Thread : " + counter);
				counter++;
				notify();
			}
		}
	}
	
}
