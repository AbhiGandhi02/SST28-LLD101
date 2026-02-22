import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final PricingService pricing;
    private final TaxRules taxRules;
    private final DiscountRules discountRules;
    private final InvoiceFormatter formatter;
    private final FileStore store;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(PricingService pricing, TaxRules taxRules,
            DiscountRules discountRules, InvoiceFormatter formatter,
            FileStore store) {
        this.pricing = pricing;
        this.taxRules = taxRules;
        this.discountRules = discountRules;
        this.formatter = formatter;
        this.store = store;
    }

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        double subtotal = pricing.calculateSubtotal(lines, menu);
        double tax = taxRules.calculateTax(customerType, subtotal);
        double discount = discountRules.calculateDiscount(customerType, subtotal, lines.size());

        double total = subtotal + tax - discount;

        String printable = formatter.format(invId, lines, menu, subtotal, tax, discount, total);
        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
