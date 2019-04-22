package main;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class BarberShop {

	public static final int WAITING_ROOM_CAPACITY = 3;
	public static final AtomicInteger CUSTOMER_ID_GENERATOR = new AtomicInteger();
	private Queue<Customer> waitingRoomChairs = new LinkedList<Customer>();

	// as customers enter the shop, they seat themselves in the queue until
	// they are next in line, if the barber is sleeping they have to wake him up
	// they can only enter one by one in this implementation
	public synchronized void enter(Customer customer) throws InterruptedException {
		System.out.println(customer + " entered the barber shop.");
		if (waitingRoomChairs.size() == BarberShop.WAITING_ROOM_CAPACITY) {
			System.out.println(customer + " is leaving. No seats available.");
			customer.interrupt();
			return;
		}
		System.out.println(customer + " is waiting for haircut.");
		this.waitingRoomChairs.offer(customer);
		// when the customer arrives he wakes up the barber
		// if the barber is cutting hair he waits in line - nothing happens here
		this.notifyAll();
	}

	// this method is called by a customer that was 'called' by the barber
	public synchronized void getHaircut(Customer customer) throws InterruptedException {
		System.out.println("The barber is cutting " + customer + "'s hair...");
		Thread.sleep(customer.getHaircutDuration());
		System.out.println(customer + "'s hair is done.");
	}

	// this method is called by the barber thread, where he serves customers
	// waiting in the queue, if there are none, he falls asleep
	public synchronized void work() throws InterruptedException {
		System.out.println("Barber's shift started.");
		while (true) {
			while (waitingRoomChairs.isEmpty()) {
				System.out.println("Barber is sleeping in his chair...");
				wait();
				System.out.println("Barber woke up.");
			}
			Customer customer = waitingRoomChairs.poll();
			this.getHaircut(customer);
		}
	}

}