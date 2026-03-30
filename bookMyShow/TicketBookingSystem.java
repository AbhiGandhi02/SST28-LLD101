package bookMyShow;

import java.util.List;

public class TicketBookingSystem {
    private SeatLockManager lockManager;
    private PricingStrategy pricingStrategy;

    public TicketBookingSystem(SeatLockManager lockManager, PricingStrategy pricingStrategy) {
        this.lockManager = lockManager;
        this.pricingStrategy = pricingStrategy;
    }

    public Booking bookTicket(User user, Show show, List<ShowSeat> selectedSeats) throws Exception {
        boolean seatsLocked = lockManager.lockSeats(selectedSeats, user);
        
        if (!seatsLocked) {
            throw new Exception("Seats are currently unavailable or locked by another user.");
        }

        double totalAmount = 0;
        for (ShowSeat seat : selectedSeats) {
            totalAmount += pricingStrategy.calculatePrice(seat, show);
        }

        return new Booking(user, show, selectedSeats, totalAmount);
    }

    public boolean confirmPayment(Booking booking) {
        if (booking.getStatus() == BookingStatus.CANCELLED) return false;

        for (ShowSeat seat : booking.getSeats()) {
            if (seat.isLockExpired()) {
                lockManager.unlockSeats(booking.getSeats());
                booking.cancel();
                return false;
            }
        }

        for (ShowSeat seat : booking.getSeats()) {
            seat.book();
        }
        booking.confirm();
        return true;
    }

    public void cancelBooking(Booking booking) {
        if (booking.getStatus() == BookingStatus.CONFIRMED) {
            for (ShowSeat seat : booking.getSeats()) {
                seat.unlock();
            }
            booking.cancel();
        }
    }
}
