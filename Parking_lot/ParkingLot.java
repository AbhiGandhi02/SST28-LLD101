import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private List<Slot> slots;
    private Map<Integer, Gate> gates;
    private Map<Integer, Ticket> activeTickets;
    private SlotAssignmentStrategy assignmentStrategy;
    private PricingStrategy pricingStrategy;

    public ParkingLot(SlotAssignmentStrategy assignmentStrategy, PricingStrategy pricingStrategy) {
        this.slots = new ArrayList<>();
        this.gates = new HashMap<>();
        this.activeTickets = new HashMap<>();
        this.assignmentStrategy = assignmentStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public void addSlot(Slot slot) {
        slots.add(slot);
    }

    public void addGate(Gate gate) {
        gates.put(gate.getId(), gate);
    }

    // Thread-safe park
    public synchronized Ticket park(Vehicle vehicle, LocalDateTime entryTime, int gateId) {
        Gate gate = gates.get(gateId);
        if (gate == null) {
            throw new RuntimeException("Gate " + gateId + " not found");
        }

        Slot slot = assignmentStrategy.findSlot(vehicle.getVehicleType(), gate, slots);
        if (slot == null) {
            throw new RuntimeException("No available slot for " + vehicle.getVehicleType());
        }

        slot.parkVehicle(vehicle);
        Ticket ticket = new Ticket(vehicle, slot, entryTime, gate);
        activeTickets.put(ticket.getId(), ticket);

        System.out.println("Parked " + vehicle.getLicensePlate() + " (" + vehicle.getVehicleType() +
                ") at Slot " + slot.getId() + " (Floor " + slot.getFloor() + ", " + slot.getSlotType() + ")");

        return ticket;
    }

    // Thread-safe exit
    public synchronized double exit(int ticketId, LocalDateTime exitTime) {
        Ticket ticket = activeTickets.remove(ticketId);
        if (ticket == null) {
            throw new RuntimeException("Ticket " + ticketId + " not found");
        }

        double fee = pricingStrategy.calculateFee(ticket, exitTime);
        ticket.getSlot().removeVehicle();

        System.out.println(ticket.getVehicle().getLicensePlate() + " exited. Fee: Rs." + fee);

        return fee;
    }

    // Status: available slots per floor per type
    public synchronized void status() {
        Map<Integer, Map<SlotType, int[]>> floorStatus = new HashMap<>();

        for (Slot slot : slots) {
            int floor = slot.getFloor();
            SlotType type = slot.getSlotType();

            if (!floorStatus.containsKey(floor)) {
                floorStatus.put(floor, new HashMap<>());
            }

            Map<SlotType, int[]> typeMap = floorStatus.get(floor);

            if (!typeMap.containsKey(type)) {
                typeMap.put(type, new int[]{0, 0}); // [total, available]
            }

            int[] counts = typeMap.get(type);
            counts[0]++; // total slots of this type 
            if (slot.isAvailable()) {
                counts[1]++; // available slots
            }
        }

        // Print the status
        System.out.println("\n=== Parking Status ===");
        for (Map.Entry<Integer, Map<SlotType, int[]>> floorEntry : floorStatus.entrySet()) {
            System.out.println("Floor " + floorEntry.getKey() + ":");

            for (Map.Entry<SlotType, int[]> typeEntry : floorEntry.getValue().entrySet()) {
                int total = typeEntry.getValue()[0];
                int available = typeEntry.getValue()[1];
                System.out.println("  " + typeEntry.getKey() + ": " + available + "/" + total + " available");
            }
        }
    }
}
