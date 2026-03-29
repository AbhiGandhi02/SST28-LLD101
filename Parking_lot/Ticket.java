import java.time.LocalDateTime;

public class Ticket {
    private static int counter = 0;

    private int id;
    private Vehicle vehicle;
    Slot slot;
    LocalDateTime entryTime;
    Gate entryGate;

    public Ticket(Vehicle vehicle, Slot slot, LocalDateTime entryTime, Gate entryGate){
        this.id = ++counter;
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryTime = entryTime;
        this.entryGate = entryGate;
    }

    
    public int getId() { 
        return id; 
    }
    
    public Vehicle getVehicle() { 
        return vehicle; 
    }
    
    public Slot getSlot() { 
        return slot; 
    }
    
    public LocalDateTime getEntryTime() { 
        return entryTime; 
    }
    
    public Gate getEntryGate() { 
        return entryGate; 
    }
    
}
