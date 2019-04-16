package main;

public class Barber extends Thread {
	BarberShop barberShop;
	
	public Barber(BarberShop barberShop) {
		this.barberShop = barberShop;
	}
	
	@Override
	public void run() {
		try {
			barberShop.nap();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.run();
	}
}
