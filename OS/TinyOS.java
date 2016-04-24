//package test;

import java.util.*;
import java.io.*;


public class TinyOS {
	public static void main(String[] args) throws InterruptedException {
		//N: number of philosophers = number of chopsticks.
		int N = 5;		
		// nRice: number of rice in the bowl; nBites: number of bites each time
		int nRice = 100;
		int nBites = 5;
		
		// Initialize philosophers and chopsticks
		ArrayDeque<Philosopher> queue = new ArrayDeque<Philosopher>();
		List<Chopstick> chopsticks = new ArrayList<Chopstick>();
		for (int i = 0; i < N; i++) {
			queue.add(new Philosopher(i));
			chopsticks.add(new Chopstick(i, N));
		}
		
		Scheduler myScheduler = new Scheduler();
		myScheduler.run(nRice, nBites, queue, chopsticks);
	}
	
}

class Scheduler{
	public Scheduler(){};
	// nRice: number of rice in the bowl; nBites: number of bites each time
	public void run(int nRice, int nBites, ArrayDeque<Philosopher> queue, List<Chopstick> chopsticks) throws InterruptedException {
		// run until rice goes empty
		while(nRice - nBites > 0) {
			// take a philosopher out from the head of the queue
			Philosopher phil = queue.poll();
			phil.eats(chopsticks, nBites);
			nRice -= nBites;
			queue.add(phil);
			
		}
		System.out.println("Not enough rice left!");
	}
	
}

class Philosopher{
	int index;
	public Philosopher(int i) {
		index = i;
	}
	public void eats(List<Chopstick> chopsticks, int nBites) throws InterruptedException {
		List<String> store = new ArrayList<String>();
		Chopstick chop1 = null;
		if (this.index == 0)
			chop1 = chopsticks.get(chopsticks.size() - 1);
		else
			chop1 = chopsticks.get(this.index - 1);
	//	System.out.println("Philosopher " + this.index + " takes Chopstick " + chop1.label);
		String temp = "Philosopher " + this.index + " takes Chopstick " + chop1.label;
		store.add(temp);
		Chopstick chop2 = chopsticks.get(this.index);
//		System.out.println("Philosopher " + this.index + " takes Chopstick " + chop2.label);
		String temp1 = "Philosopher " + this.index + " takes Chopstick " + chop2.label;
		store.add(temp1);
		Thread.sleep(200);
//		System.out.println("Philosopher " + this.index + " takes " + nBites + " bites.");
		String temp2 = "Philosopher " + this.index + " takes " + nBites + " bites.";
		store.add(temp2);
		//	System.out.println("Philosopher " + this.index + " returns Chopstick " + chop1.label);
		String temp3 = "Philosopher " + this.index + " returns Chopstick " + chop1.label;
		store.add(temp3);
		//System.out.println("Philosopher " + this.index + " returns Chopstick " + chop2.label);
		String temp4 = "Philosopher " + this.index + " returns Chopstick " + chop2.label;
		store.add(temp4);
		Thread.sleep(200);
		String[] storeArray = store.toArray(new String[0]);
		for(String t: storeArray){
			System.out.println(t);
		}
	}
}

class Chopstick{
	String label;
	public Chopstick(int i, int N) {
		label = "";
		label += i;
		label += '-';
		label += ((i + 1) % N);
	}
	
}