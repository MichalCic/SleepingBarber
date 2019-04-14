package main;

public class Barber extends Thread {
	Chair chair;
	
	public Barber(Chair chair) {
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
