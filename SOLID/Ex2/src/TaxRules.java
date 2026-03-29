public class TaxRules implements TaxCalculator {
    @Override
    public double calculateTax(String customerType, double subtotal) {
        // hard-coded policy (smell)
        if ("student".equalsIgnoreCase(customerType))
            return subtotal * (5.0 / 100.0);
        if ("staff".equalsIgnoreCase(customerType))
            return subtotal * (2.0 / 100.0);
        return subtotal * (8.0 / 100.0);
    }
}
