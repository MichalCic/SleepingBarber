package main;

import java.util.LinkedList;
import java.util.Queue;

public class BarberShop {
	private Queue<Customer> customerLine = new LinkedList<Customer>();
	
	public synchronized void enterShop() throws InterruptedException {
		Customer customer = (Customer) Thread.currentThread();
		System.out.println(customer.getName() + " arrived.");
		customerLine.offer(customer);
		notifyAll();
	}

	public synchronized void getHaircut(Customer customer) throws InterruptedException {
		String name = customer.getName();

		System.out.println("The barber is cutting " + name + "'s hair...");
		cutHair();
		System.out.println(name + "'s hair is done.");
	}

	public synchronized void work() throws InterruptedException {
		while (true) {
			while (customerLine.size() == 0) {
				System.out.println("Baber is sleeping in his chair...");
				wait();
			}
			
			serveCustomers();
		} 
	}

	private void serveCustomers() throws InterruptedException {
		while (!customerLine.isEmpty()) {
			customerLine.poll().sitInBarbersChair();;
		}
	}

	public void cutHair() throws InterruptedException {
		Thread.sleep(1500);
	}

	public void sleep() throws InterruptedException {
		Thread.sleep(3000);
	}

	public boolean hasCustomers() {
		return !customerLine.isEmpty();
	}
}
