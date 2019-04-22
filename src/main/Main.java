package main;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		BarberShop barberShop = new BarberShop();
		Barber barber = new Barber(barberShop, 0);

		Customer customer1 = new Customer(barberShop, 0);
		Customer customer2 = new Customer(barberShop, 0);
		Customer customer3 = new Customer(barberShop, 1000);
		Customer customer4 = new Customer(barberShop, 2000);
		barber.start();
		customer1.start();
		customer2.start();
		customer3.start();
		customer4.start();

	}

}
