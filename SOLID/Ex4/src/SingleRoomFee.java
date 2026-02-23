public class SingleRoomFee implements FeeComponent {
    @Override
    public Money compute(BookingRequest req) {
        if (req.roomType == LegacyRoomTypes.SINGLE) {
            return new Money(14000.0);
        }
        return new Money(0);
    }
}
