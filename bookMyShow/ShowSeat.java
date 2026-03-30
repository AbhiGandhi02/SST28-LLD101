package bookMyShow;

public class ShowSeat {
    private String id;
    private Seat seat;
    private SeatStatus status;
    private String lockedBy;
    private long lockExpirationTime;

    public ShowSeat(String id, Seat seat) {
        this.id = id;
        this.seat = seat;
        this.status = SeatStatus.AVAILABLE;
    }

    public Seat getSeat() { return seat; }
    public SeatStatus getStatus() { return status; }
    public String getLockedBy() { return lockedBy; }

    public boolean isLockExpired() {
        return status == SeatStatus.LOCKED && System.currentTimeMillis() > lockExpirationTime;
    }

    public void lock(String email, long expirationTime) {
        this.status = SeatStatus.LOCKED;
        this.lockedBy = email;
        this.lockExpirationTime = expirationTime;
    }

    public void unlock() {
        this.status = SeatStatus.AVAILABLE;
        this.lockedBy = null;
        this.lockExpirationTime = 0;
    }

    public void book() {
        this.status = SeatStatus.BOOKED;
    }
}
