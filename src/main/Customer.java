package main;

public class Customer extends Thread {
	
	private final int ID = BarberShop.CUSTOMER_ID_GENERATOR.incrementAndGet();
	private final int HAIRCUT_DURATION = (int) (Math.random() * 5000 + 1);
	private int delay = (int) (Math.random() * 10000 + 1);
	private BarberShop barberShop;
	
	public Customer(BarberShop barberShop) {
		this.barberShop = barberShop;
	}
	
	public Customer(BarberShop barberShop, int delay) {
		this.barberShop = barberShop;
		this.delay = delay;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(this.delay);
			this.barberShop.enter(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		super.run();
	}
	
	public int getHaircutDuration() {
		return this.HAIRCUT_DURATION;
	}
	
	@Override
	public String toString() {
		return "Customer " + this.ID;
	}
	
}
