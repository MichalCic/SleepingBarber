package main;

public class BarberShop {

	public static void main(String[] args) throws InterruptedException {
//		Barber barber = new Barber();
//		barber.setName("Barber");
//		barber.start();
		
		Chair chair = new Chair();

		Customer joe = new Customer(chair);
		joe.setName("Joe");
		Customer johnny = new Customer(chair);
		johnny.setName("Johnny");
		Customer jack = new Customer(chair);
		jack.setName("Jack");

		//Thread.sleep(1000);
		joe.start();
		
		//Thread.sleep(6000);
		jack.start();
		
		Thread.sleep(3000);
		johnny.start();
	}
}
