package main;

public class Barber extends Thread {
	BarberShop barberShop;

	public Barber(BarberShop barberShop) {
		this.barberShop = barberShop;
	}

	@Override
	public void run() {
		try {
			barberShop.work();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		super.run();
	}
}
