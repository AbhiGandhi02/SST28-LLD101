import java.util.List;
import java.util.Map;

public class InvoiceFormatter {
    public String format(String invId, List<OrderLine> lines, Map<String, MenuItem> menu,
            double subtotal, double tax, double discount, double total) {
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(invId).append("\n");

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        // We need to calculate the actual tax percentage to display it correctly,
        // or we could add a method to TaxCalculator to return the rate.
        // But since the expected output shows "Tax(5%): 9.50", and we just
        // calculated the tax as a dollar amount, let's derive the percentage:
        double taxPct = subtotal > 0 ? (tax / subtotal) * 100.0 : 0.0;

        out.append(String.format("Subtotal: %.2f\n", subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", taxPct, tax));
        out.append(String.format("Discount: -%.2f\n", discount));
        out.append(String.format("TOTAL: %.2f\n", total));

        return out.toString();
    }
}
