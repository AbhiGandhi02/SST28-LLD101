import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        // Create parking lot with strategies
        ParkingLot lot = new ParkingLot(
            new NearestSlotStrategy(),
            new HourlyPricingStrategy()
        );

        // Add gates (id, floor, x, y)
        lot.addGate(new Gate(1, 0, 0, 0));    // Ground floor entrance
        lot.addGate(new Gate(2, 2, 10, 10));   // Floor 2 entrance

        // Add slots — Floor 0
        lot.addSlot(new Slot(1, 0, 2, 3, SlotType.SMALL));
        lot.addSlot(new Slot(2, 0, 4, 5, SlotType.MEDIUM));
        lot.addSlot(new Slot(3, 0, 6, 7, SlotType.MEDIUM));
        lot.addSlot(new Slot(4, 0, 8, 9, SlotType.LARGE));

        // Add slots — Floor 1
        lot.addSlot(new Slot(5, 1, 2, 3, SlotType.SMALL));
        lot.addSlot(new Slot(6, 1, 4, 5, SlotType.MEDIUM));
        lot.addSlot(new Slot(7, 1, 6, 7, SlotType.LARGE));
        lot.addSlot(new Slot(8, 1, 8, 9, SlotType.LARGE));

        // Add slots — Floor 2
        lot.addSlot(new Slot(9, 2, 2, 3, SlotType.SMALL));
        lot.addSlot(new Slot(10, 2, 4, 5, SlotType.MEDIUM));
        lot.addSlot(new Slot(11, 2, 6, 7, SlotType.LARGE));

        // --- Parking ---
        System.out.println("=== Parking Vehicles ===");

        // Bike enters Gate 1 → should get nearest SMALL slot on Floor 0
        Vehicle bike = new Vehicle("KA-01-1111", VehicleType.BIKE);
        Ticket t1 = lot.park(bike, LocalDateTime.now().minusHours(3), 1);

        // Car enters Gate 1 → should get nearest MEDIUM slot on Floor 0
        Vehicle car1 = new Vehicle("KA-01-2222", VehicleType.CAR);
        Ticket t2 = lot.park(car1, LocalDateTime.now().minusHours(2), 1);

        // Truck enters Gate 2 → should get nearest LARGE slot on Floor 2
        Vehicle truck = new Vehicle("KA-01-3333", VehicleType.TRUCK);
        Ticket t3 = lot.park(truck, LocalDateTime.now().minusHours(1), 2);

        // Car enters Gate 1 → can fit in MEDIUM or LARGE
        Vehicle car2 = new Vehicle("KA-01-4444", VehicleType.CAR);
        Ticket t4 = lot.park(car2, LocalDateTime.now().minusHours(1), 1);

        // --- Status ---
        lot.status();

        // --- Exit ---
        System.out.println("\n=== Exiting Vehicles ===");
        lot.exit(t1.getId(), LocalDateTime.now());  // Bike, 3 hrs
        lot.exit(t2.getId(), LocalDateTime.now());  // Car, 2 hrs
        lot.exit(t3.getId(), LocalDateTime.now());  // Truck, 1 hr
        lot.exit(t4.getId(), LocalDateTime.now());

        // --- Status after exits ---
        lot.status();
    }
}
