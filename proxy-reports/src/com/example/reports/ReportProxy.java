package com.example.reports;

/**
 * Proxy — controls access and lazy-loads the RealReport.
 * - Checks user permissions via AccessControl before allowing display.
 * - Lazily creates the RealReport only on first authorized access.
 * - Caches the RealReport so repeated displays don't reload from disk.
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();
    private RealReport realReport; // lazy — null until first authorized access

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("[ACCESS DENIED] " + user.getName()
                    + " (" + user.getRole() + ") cannot view " + classification + " report: " + title);
            return;
        }

        if (realReport == null) {
            realReport = new RealReport(reportId, title, classification);
        }
        realReport.display(user);
    }
}
