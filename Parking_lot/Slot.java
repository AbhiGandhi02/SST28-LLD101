public class Slot {
    private int id;
    private int floor;
    private int x;
    private int y;
    private SlotType slotType;
    private boolean isAvailable;
    private Vehicle parkedVehicle;

    public Slot(int id, int floor, int x, int y, SlotType slotType){
        this.id = id;
        this.floor = floor;
        this.x = x;
        this.y = y;
        this.slotType = slotType;
        this.isAvailable = true;
        this.parkedVehicle = null;
    }

    public boolean canFit(VehicleType vehicleType){
        switch(vehicleType){
            case BIKE:
                return slotType == SlotType.SMALL || slotType == SlotType.MEDIUM;
            case CAR:
                return slotType == SlotType.MEDIUM || slotType == SlotType.LARGE;
            case TRUCK:
                return slotType == SlotType.LARGE;
            default:
                return false;
        }
    }

    public double distanceTo(Gate gate){
        int dx = this.x - gate.getX();
        int dy = this.y - gate.getY();
        int dz = (this.floor - gate.getFloor()) * 10;
        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }

    public void parkVehicle(Vehicle vehicle){
        this.isAvailable = false;
        this.parkedVehicle = vehicle;
    }

    public void removeVehicle(){
        this.isAvailable = true;
        this.parkedVehicle = null;
    }

    public int getId() { 
        return id; 
    }

    public int getFloor() { 
        return floor;
    }

    public SlotType getSlotType() { 
        return slotType; 
    }

    public boolean isAvailable() { 
        return isAvailable; 
    }

    public Vehicle getParkedVehicle() { 
        return parkedVehicle; 
    }
}
