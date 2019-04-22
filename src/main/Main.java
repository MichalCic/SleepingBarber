package main;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		//most of the monitoring and synchronization happens here 
		BarberShop barberShop = new BarberShop();
		Barber barber = new Barber(barberShop);
		Customer customer1 = new Customer(barberShop);
		Customer customer2 = new Customer(barberShop);
		Customer customer3 = new Customer(barberShop);
		Customer customer4 = new Customer(barberShop);
		barber.start();
		customer1.start();	
		customer2.start();	
		customer3.start();		
		customer4.start();
		
	}
	
}
