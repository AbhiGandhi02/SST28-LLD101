public class TripleRoomFee implements FeeComponent {
    @Override
    public Money compute(BookingRequest req) {
        if (req.roomType == LegacyRoomTypes.TRIPLE) {
            return new Money(12000.0);
        }
        return new Money(0);
    }
}
