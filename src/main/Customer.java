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
	
	// this is called by the barber thread to continue execution after 
	// this thread was waiting in the queue
	public void sitInBarbersChair() throws InterruptedException {
		barberShop.getHaircut(this);
	}
}
