import java.util.List;

public class NearestSlotStrategy implements SlotAssignmentStrategy{
    @Override
    public Slot findSlot(VehicleType vehicleType, Gate gate, List<Slot> allSlots){
        Slot nearestSlot = null;
        double minDistance = Integer.MAX_VALUE;
        for(Slot slots : allSlots){
            if(slots.isAvailable() && slots.canFit(vehicleType)){
                double distance = slots.distanceTo(gate);
                if(distance < minDistance){
                    minDistance = distance;
                    nearestSlot = slots;
                }
            }
        }
        return nearestSlot;
    }
}
