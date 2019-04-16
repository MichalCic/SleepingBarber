package main;

public class Main {

	public static void main(String[] args) throws InterruptedException {	
		BarberShop barberShop = new BarberShop();
		
		Barber barber = new Barber(barberShop);
		barber.start();

		Customer customer1 = new Customer(barberShop);
		customer1.setName("Joe");
		Customer customer2 = new Customer(barberShop);
		customer2.setName("Johnny");
		Customer customer3 = new Customer(barberShop);
		customer3.setName("Jack");
		Customer customer4 = new Customer(barberShop);
		customer4.setName("Jermaine");

		customer1.start();
		
		customer2.start();
		
		customer3.start();
		
		customer4.start();
	}
}
