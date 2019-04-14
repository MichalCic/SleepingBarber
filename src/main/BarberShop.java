package main;

import java.util.Random;

public class BarberShop {

	public static void main(String[] args) throws InterruptedException {	
		Random rng = new Random();
		Chair chair = new Chair();

		Customer customer1 = new Customer(chair);
		customer1.setName("Joe");
		Customer customer2 = new Customer(chair);
		customer2.setName("Johnny");
		Customer customer3 = new Customer(chair);
		customer3.setName("Jack");
		Customer customer4 = new Customer(chair);
		customer4.setName("Jermaine");

		//Thread.sleep(rng.nextInt(2000));
		customer1.start();
		
		//Thread.sleep(rng.nextInt(2000));
		customer2.start();
		
		//Thread.sleep(rng.nextInt(2000));
		customer3.start();
		
		//Thread.sleep(rng.nextInt(2000));
		customer4.start();
	}
}
