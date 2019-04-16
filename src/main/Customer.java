package main;

public class Customer extends Thread {
	BarberShop barberShop;
	
	public Customer(BarberShop barberShop) {
		this.barberShop = barberShop;
	}
	
	@Override
	public void run() {
		try {
			barberShop.enterShop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		super.run();
	}
}
