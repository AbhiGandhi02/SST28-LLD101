public class LaundryFee implements FeeComponent {
    @Override
    public Money compute(BookingRequest req) {
        if (req.addOns.contains(AddOn.LAUNDRY)) {
            return new Money(500.0);
        }
        return new Money(0);
    }
}
