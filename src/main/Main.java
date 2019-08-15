package main;

public class Main {
    private static final int WAITING_ROOM_CAPACITY = 5;
    private static final int HAIRCUT_TIME_SECONDS = 5;

    public static void main(String[] args) throws InterruptedException {
        String[] customerNames = {
                "Jack", "John", "Brad", "Felix", "Joe", "Jermaine", "Frank", "Matt", "Leo", "Rob", "Carl"
        };

        // most of the monitoring and synchronization happens in the barberShop
        BarberShop barberShop = new BarberShop(WAITING_ROOM_CAPACITY, HAIRCUT_TIME_SECONDS);

        // this starts the shift
        Barber barber = new Barber(barberShop);
        barber.setName("Barber");
        barber.start();

        // 'infinite' (100) customer loop
        Customer customer;
        for (int i = 0; i < 100; i++) {
            long sleepTime = Math.round(Math.random() * 10000);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            customer = new Customer(barberShop);
            int randomNameIndex = (int) Math.round(Math.random() * 10);
            customer.setName(customerNames[randomNameIndex]);
            customer.start();
        }
    }
}
