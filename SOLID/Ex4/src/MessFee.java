public class MessFee implements FeeComponent {
    @Override
    public Money compute(BookingRequest req) {
        if (req.addOns.contains(AddOn.MESS)) {
            return new Money(1000.0);
        }
        return new Money(0);
    }
}
