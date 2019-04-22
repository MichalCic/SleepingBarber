package main;

public class Barber extends Thread {

	private BarberShop barberShop;
	private int delay = (int) (Math.random() * 10000 + 1);

	public Barber(BarberShop barberShop) {
		this.barberShop = barberShop;
	}

	public Barber(BarberShop barberShop, int delay) {
		this.barberShop = barberShop;
		this.delay = delay;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(this.delay);
			this.barberShop.startShift();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		super.run();
	}

	public void generateCustomers() {
		while (true) {

		}
	}

}
