package bookMyShow;

import java.util.List;
import java.util.UUID;

public class Booking {
    private String id;
    private User user;
    private Show show;
    private List<ShowSeat> seats;
    private double amount;
    private BookingStatus status;

    public Booking(User user, Show show, List<ShowSeat> seats, double amount) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.show = show;
        this.seats = seats;
        this.amount = amount;
        this.status = BookingStatus.PENDING;
    }

    public String getId() { return id; }
    public List<ShowSeat> getSeats() { return seats; }
    public BookingStatus getStatus() { return status; }
    public double getAmount() { return amount; }
    
    public void confirm() { this.status = BookingStatus.CONFIRMED; }
    public void cancel() { this.status = BookingStatus.CANCELLED; }
}
