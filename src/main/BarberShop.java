package main;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

class BarberShop {
	private ArrayBlockingQueue<Customer> waitingSeats;
	private int haircutTime;

	BarberShop(int waitingRoomCapacity, int haircutTimeSeconds) {
		waitingSeats = new ArrayBlockingQueue<Customer>(waitingRoomCapacity);
		haircutTime = haircutTimeSeconds;
	}

	// as customers enter the shop, they seat themselves in the queue until
	// they are next in line, if the barber is sleeping they have to wake him up
	// they can only enter one by one in this implementation
	void enterShop() throws InterruptedException {
		Customer customer = (Customer) Thread.currentThread();
		LocalDateTime dt = LocalDateTime.now();
		System.out.println(customer.getName() + " arrived at " + dt.getHour() + ":" + dt.getMinute() + ":" + dt.getSecond() + ".");
		if (waitingSeats.remainingCapacity() > 0) {
			waitingSeats.offer(customer);
		} else {
			System.out.println("No more free seats in the waiting room, " + customer.getName() + " left.");
		}
		// when the customer arrives he wakes up the barber
		// if the barber is cutting hair he waits in line - nothing happens here
		synchronized (this) {
			notifyAll();
		}
	}

	// this method is called by the barber thread, where he serves customers
	// waiting in the queue, if there are none, he falls asleep
	synchronized void work() throws InterruptedException {
		System.out.println("Barber starts his shift.");

		// this loop is here to ensure, that after all customers are served,
		// the barber can fall asleep and then be woken up again.
		while (true) {
			while (waitingSeats.size() == 0) {
				System.out.println("Baber is sleeping in his chair...");
				wait();
			}
			
			serveCustomers();
		}
	}

	// this method ensures that barber doesn't fall asleep while there are
	// customers waiting in the queue
	private void serveCustomers() throws InterruptedException {
		System.out.println(waitingSeats.peek().getName() + " wakes up the barber.");
		
		while (!waitingSeats.isEmpty()) {
			// barber 'calls' a customer to get a haircut
			// - removes him from the queue
			waitingSeats.poll().sitInBarbersChair();
		}
	}

	// this method is called by a customer that was 'called' by the barber
	synchronized void getHaircut(Customer customer) throws InterruptedException {
		String name = customer.getName();

		System.out.println("The barber is cutting " + name + "'s hair...");
		cutHair();
		System.out.println(name + "'s hair is done.");
	}

	// timeout to get a better understanding how the program runs
	private void cutHair() throws InterruptedException {
		Thread.sleep(haircutTime * 1000);
	}
}
