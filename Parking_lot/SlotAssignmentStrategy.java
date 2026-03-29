import java.util.List;

public interface SlotAssignmentStrategy {
    Slot findSlot(VehicleType vehicleType, Gate gate, List<Slot> allSlots); 
}