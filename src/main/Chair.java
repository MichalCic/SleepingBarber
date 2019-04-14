package main;

import java.util.ArrayList;
import java.util.List;

public class Chair {
	private List<String> names = new ArrayList<String>();;

	public void sitDown() throws InterruptedException {
		String name = Thread.currentThread().getName();
		System.out.println(name + " waits in the queue.\n");
		names.add(name);
		
		synchronized (this) {
			System.out.println("The barber is cutting " + name + "'s hair.\n" + name + " is done.\n");
			names.remove(name);
		}
		
		if(names.size() == 0)
			System.out.println("Barber is sleeping.\n");
	}
}
