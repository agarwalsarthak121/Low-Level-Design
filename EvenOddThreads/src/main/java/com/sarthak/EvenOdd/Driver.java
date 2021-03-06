package com.sarthak.EvenOdd;

public class Driver {
	public static void main(String[] args) {
		
		final PrintNum printer = new PrintNum(21);
		Runnable oddPrinter = new Runnable() {
			
			@Override
			public void run() {
				try {
					printer.printOdd();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		Runnable evenPrinter = new Runnable() {
			
			@Override
			public void run() {
				try {
					printer.printEven();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Thread oddThread = new Thread(oddPrinter);
		Thread evenThread = new Thread(evenPrinter);
		
		oddThread.start();
		evenThread.start();
	}
}
