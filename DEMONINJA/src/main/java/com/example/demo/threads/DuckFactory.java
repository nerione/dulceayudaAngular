package com.example.demo.threads;

import java.util.Random;

public class DuckFactory extends Thread{

	@Override
	public void run() {
		
		for(int i = 0; i < 10; i++) {
			System.out.println("Pato " + i);
			
			try {
				
				Thread.sleep(numeroRandom(1000, 5000));
				
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static int numeroRandom(int min, int max) {
		Random random = new Random();
		int i = random.nextInt( (max - min) +1 ) + min;
		return i;
	}

}
