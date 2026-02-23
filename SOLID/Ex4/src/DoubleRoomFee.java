public class DoubleRoomFee implements FeeComponent {
    @Override
    public Money compute(BookingRequest req) {
        if (req.roomType == LegacyRoomTypes.DOUBLE) {
            return new Money(15000.0);
        }
        return new Money(0);
    }
}
