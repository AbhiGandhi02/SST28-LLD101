import java.time.LocalDateTime;

public interface PricingStrategy {
    double calculateFee(Ticket ticket, LocalDateTime exitTIme);
}
