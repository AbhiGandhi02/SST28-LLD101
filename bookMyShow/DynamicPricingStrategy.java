package bookMyShow;

public class DynamicPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(ShowSeat showSeat, Show show) {
        double basePrice = showSeat.getSeat().getCategory() == SeatCategory.DIAMOND ? 300.0 : 150.0;
        double demandMultiplier = 1.2; 
        return basePrice * demandMultiplier;
    }
}
