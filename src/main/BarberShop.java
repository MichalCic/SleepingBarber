package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BarberShop {
	private List<String> names = new ArrayList<String>();
	private AtomicInteger customers = new AtomicInteger();
	// private BlockingQueue<String> waitingRoom = new
	// LinkedBlockingQueue<String>(3);
	private boolean isCuttingHair = false;

	public void enterShop() throws InterruptedException {
		System.out.println(customers.incrementAndGet() + " customers are in the queue.\n");

		sitDown();
	}

	public synchronized void sitDown() throws InterruptedException {
		String name = Thread.currentThread().getName();

		if (!isCuttingHair)
			System.out.println(name + " wakes up the barber.\n");

		isCuttingHair = true;
		System.out.println("The barber is cutting " + name + "'s hair...\n");
		cutHair();
		System.out.println(name + "'s hair is done.\n");

		if (customers.get() <= 1)
			isCuttingHair = false;

		if (customers.decrementAndGet() == 0)
			notifyAll();
	}

	public synchronized void nap() throws InterruptedException {
		while (customers.get() == 0) {
			System.out.println("Baber is sleeping in his chair...\n");
			wait();
		}
	}

	public void cutHair() throws InterruptedException {
		Thread.sleep(2000);
	}

	public void sleep() throws InterruptedException {
		Thread.sleep(3000);
	}
}
