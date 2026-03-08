package com.example.reports;

/**
 * Viewer depends only on the Report interface (not the concrete ReportFile).
 */
public class ReportViewer {

    public void open(Report report, User user) {
        report.display(user);
    }
}
