import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");
        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));

        List<FeeComponent> components = List.of(
                new SingleRoomFee(),
                new DoubleRoomFee(),
                new TripleRoomFee(),
                new DeluxeRoomFee(),
                new MessFee(),
                new LaundryFee(),
                new GymFee());

        HostelFeeCalculator calc = new HostelFeeCalculator(new FakeBookingRepo(), components);
        calc.process(req);
    }
}
