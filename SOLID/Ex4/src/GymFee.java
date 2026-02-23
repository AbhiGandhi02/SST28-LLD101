public class GymFee implements FeeComponent {
    @Override
    public Money compute(BookingRequest req) {
        if (req.addOns.contains(AddOn.GYM)) {
            return new Money(300.0);
        }
        return new Money(0);
    }
}
