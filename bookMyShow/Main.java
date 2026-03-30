package bookMyShow;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SeatLockManager lockManager = new SeatLockManager(5 * 60 * 1000); 
        PricingStrategy pricingStrategy = new DynamicPricingStrategy();
        TicketBookingSystem system = new TicketBookingSystem(lockManager, pricingStrategy);

        User user1 = new User("alice@email.com", "Alice");
        User user2 = new User("bob@email.com", "Bob");

        Movie movie = new Movie("M1", "Inception", "English");
        Theater theater = new Theater("T1", "Cineplex", "Bengaluru");
        Screen screen = new Screen("SC1", "Screen 1", theater);

        Seat seat1 = new Seat("S1", 1, 1, SeatCategory.GOLD);
        Seat seat2 = new Seat("S2", 1, 2, SeatCategory.GOLD);

        ShowSeat showSeat1 = new ShowSeat("SS1", seat1);
        ShowSeat showSeat2 = new ShowSeat("SS2", seat2);
        List<ShowSeat> availableSeats = Arrays.asList(showSeat1, showSeat2);

        Show show = new Show("SH1", movie, screen, System.currentTimeMillis(), availableSeats);

        System.out.println("--- Concurrency Test ---");

        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("Alice attempting to book...");
                Booking booking = system.bookTicket(user1, show, availableSeats);
                System.out.println("Alice locked the seats! Proceeding to payment. Amount: " + booking.getAmount());
                
                boolean paymentSuccess = system.confirmPayment(booking);
                System.out.println("Alice payment status: " + paymentSuccess);
            } catch (Exception e) {
                System.out.println("Alice failed: " + e.getMessage());
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("Bob attempting to book exactly the same seats...");
                Booking booking = system.bookTicket(user2, show, availableSeats);
                System.out.println("Bob locked the seats!");
            } catch (Exception e) {
                System.out.println("Bob failed: " + e.getMessage());
            }
        });

        thread1.start();
        
        try { Thread.sleep(100); } catch (InterruptedException e) {} 
        
        thread2.start();
    }
}
