import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

public class HourlyPricingStrategy implements PricingStrategy{
    HashMap<SlotType, Double> map;

    public HourlyPricingStrategy(){
        this.map = new HashMap<>();
        map.put(SlotType.SMALL, 10.0);
        map.put(SlotType.MEDIUM, 20.0);
        map.put(SlotType.LARGE, 50.0);
    }


    @Override
    public double calculateFee(Ticket ticket, LocalDateTime exitTime){
        long hours = Duration.between(ticket.getEntryTime(), exitTime).toHours();
        if(hours == 0){
            hours = 1;
        }
        double rate = map.get(ticket.getSlot().getSlotType());
        return rate * hours;
    }
}
