package com.example.tickets;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * INTENTION: A ticket should be an immutable record-like object.
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - mutable fields
 * - multiple constructors
 * - public setters
 * - tags list can be modified from outside
 * - validation is scattered elsewhere
 *
 * TODO (student): refactor to immutable + Builder.
 */
public class IncidentTicket {

    // All fields are private final
    private final String id;
    private final String reporterEmail;
    private final String title;
    private final String description;
    private final String priority;
    private final List<String> tags;
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes;
    private final String source;

    // Only ONE constructor, and it's PRIVATE
    // Only Builder can create a ticket
    private IncidentTicket(Builder b) {
        this.id = b.id;
        this.reporterEmail = b.reporterEmail;
        this.title = b.title;
        this.description = b.description;
        this.priority = b.priority;
        this.tags = new ArrayList<>(b.tags); // defensive copy!
        this.assigneeEmail = b.assigneeEmail;
        this.customerVisible = b.customerVisible;
        this.slaMinutes = b.slaMinutes;
        this.source = b.source;
    }

    // Getters only — NO setters
    public String getId() {
        return id;
    }

    public String getReporterEmail() {
        return reporterEmail;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public List<String> getTags() {
        return Collections.unmodifiableList(tags);
    }

    public String getAssigneeEmail() {
        return assigneeEmail;
    }

    public boolean isCustomerVisible() {
        return customerVisible;
    }

    public Integer getSlaMinutes() {
        return slaMinutes;
    }

    public String getSource() {
        return source;
    }

    // Convenience to create a new ticket from an existing one
    // Copies all data into a Builder so you can change one field and build a new
    // ticket
    public Builder toBuilder() {
        return new Builder(id, reporterEmail, title)
                .description(description)
                .priority(priority)
                .tags(tags)
                .assigneeEmail(assigneeEmail)
                .customerVisible(customerVisible)
                .slaMinutes(slaMinutes)
                .source(source);
    }

    // Static factory — cleaner than "new Builder(...)"
    public static Builder builder(String id, String reporterEmail, String title) {
        return new Builder(id, reporterEmail, title);
    }

    // THE BUILDER

    public static class Builder {
        // Required fields (set in constructor — can't be skipped)
        private final String id;
        private final String reporterEmail;
        private final String title;

        // Optional fields (have defaults)
        private String description;
        private String priority;
        private List<String> tags = new ArrayList<>();
        private String assigneeEmail;
        private boolean customerVisible;
        private Integer slaMinutes;
        private String source;

        public Builder(String id, String reporterEmail, String title) {
            this.id = id;
            this.reporterEmail = reporterEmail;
            this.title = title;
        }

        // Each method returns "this" so you can chain: .priority("HIGH").source("CLI")
        public Builder description(String val) {
            this.description = val;
            return this;
        }

        public Builder priority(String val) {
            this.priority = val;
            return this;
        }

        public Builder tags(List<String> val) {
            this.tags = new ArrayList<>(val);
            return this;
        }

        public Builder addTag(String val) {
            this.tags.add(val);
            return this;
        }

        public Builder assigneeEmail(String val) {
            this.assigneeEmail = val;
            return this;
        }

        public Builder customerVisible(boolean val) {
            this.customerVisible = val;
            return this;
        }

        public Builder slaMinutes(Integer val) {
            this.slaMinutes = val;
            return this;
        }

        public Builder source(String val) {
            this.source = val;
            return this;
        }

        // ALL validation in ONE place  
        public IncidentTicket build() {
            Validation.requireTicketId(id);
            Validation.requireEmail(reporterEmail, "reporterEmail");
            Validation.requireNonBlank(title, "title");
            Validation.requireMaxLen(title, 80, "title");

            if (priority != null)
                Validation.requireOneOf(priority, "priority", "LOW", "MEDIUM", "HIGH", "CRITICAL");
            if (assigneeEmail != null)
                Validation.requireEmail(assigneeEmail, "assigneeEmail");

            Validation.requireRange(slaMinutes, 5, 7200, "slaMinutes");

            return new IncidentTicket(this);
        }
    }

    @Override
    public String toString() {
        return "IncidentTicket{" +
                "id='" + id + '\'' +
                ", reporterEmail='" + reporterEmail + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", tags=" + tags +
                ", assigneeEmail='" + assigneeEmail + '\'' +
                ", customerVisible=" + customerVisible +
                ", slaMinutes=" + slaMinutes +
                ", source='" + source + '\'' +
                '}';
    }
}
