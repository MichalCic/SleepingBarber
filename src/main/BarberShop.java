package main;

import java.util.LinkedList;
import java.util.Queue;

public class BarberShop {

	private final int WAITING_ROOM_CAPACITY = 3;
	// We are using LinkedList as it supports polling objects respecting FIFO
	// method.
	private Queue<Customer> waitingRoomChairs = new LinkedList<Customer>();

	/*
	 * Customer threads call this method to enter the shop. If there are any seats
	 * left, customer sit and wait till it is his/her turn. Seated customers are
	 * notifying (waking up) barber about their presence.
	 */
	public synchronized void enterShop(Customer customer) throws InterruptedException {
		System.out.println("Customer " + customer + " entered the barber shop.");
		if (waitingRoomChairs.size() == this.WAITING_ROOM_CAPACITY) {
			System.out.println("Customer " + customer + " is leaving. No seats available.");
			customer.interrupt();
			return;
		}
		System.out.println("Customer " + customer + " is waiting for haircut.");
		this.waitingRoomChairs.offer(customer);
		this.notifyAll();
	}

	/*
	 * This method is called by barber when he starts his/her shift and keeps
	 * running indefinitely. While waiting room is empty barber takes a nap.
	 * Customer has to wake up him. When barber is woken up by waiting customer,
	 * barber takes customer from queue and do his/her magic (haircut).
	 */
	public synchronized void startShift() throws InterruptedException {
		System.out.println("Barber's shift started.");
		while (true) {
			while (waitingRoomChairs.isEmpty()) {
				System.out.println("No customers. Barber is sleeping. Zzz.");
				wait();
				System.out.println("New customer! Barber woke up.");
			}
			Customer customer = waitingRoomChairs.poll();
			System.out.println("Barber is cutting customer " + customer + "'s hair.");
			Thread.sleep(customer.getHaircutDuration());
			System.out.println("Customer " + customer + "'s hair is done.");
		}
	}

}