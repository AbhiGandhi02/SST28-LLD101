package bookMyShow;

import java.util.List;

public class SeatLockManager {
    private final long lockDurationMillis;

    public SeatLockManager(long lockDurationMillis) {
        this.lockDurationMillis = lockDurationMillis;
    }

    public synchronized boolean lockSeats(List<ShowSeat> seats, User user) {
        for (ShowSeat seat : seats) {
            if (seat.getStatus() == SeatStatus.BOOKED) {
                return false;
            }
            if (seat.getStatus() == SeatStatus.LOCKED && !seat.isLockExpired() && !seat.getLockedBy().equals(user.getEmail())) {
                return false;
            }
        }

        long expirationTime = System.currentTimeMillis() + lockDurationMillis;
        for (ShowSeat seat : seats) {
            seat.lock(user.getEmail(), expirationTime);
        }
        return true;
    }

    public synchronized void unlockSeats(List<ShowSeat> seats) {
        for (ShowSeat seat : seats) {
            if (seat.getStatus() == SeatStatus.LOCKED) {
                seat.unlock();
            }
        }
    }
}
