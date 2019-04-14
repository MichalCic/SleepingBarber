package main;

public class Customer extends Thread {
	Chair chair;
	
	public Customer(Chair chair) {
		this.chair = chair;
	}
	
	@Override
	public void run() {
		try {
			chair.sitDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		super.run();
	}
}
