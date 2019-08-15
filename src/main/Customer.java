package main;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Timer;

public class Customer extends Thread {
	BarberShop barberShop;
	
	Customer(BarberShop barberShop) {
		this.barberShop = barberShop;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(Math.round((Math.random() * 1000)));
			barberShop.enterShop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		super.run();
	}
	
	// this is called by the barber thread to continue execution after 
	// this thread was waiting in the queue
	void sitInBarbersChair() throws InterruptedException {
		barberShop.getHaircut(this);
	}
}
