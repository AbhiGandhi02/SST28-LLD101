public class DeluxeRoomFee implements FeeComponent {
    @Override
    public Money compute(BookingRequest req) {
        if (req.roomType == LegacyRoomTypes.DELUXE) {
            return new Money(16000.0);
        }
        return new Money(0);
    }
}
