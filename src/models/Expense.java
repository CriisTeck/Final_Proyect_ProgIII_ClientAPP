package models;

import java.time.LocalDateTime;

public class Expense {
    private final int mount;
    private final LocalDateTime date;
    private final String description;

    public Expense(int mount, LocalDateTime date, String description) {
        this.mount = mount;
        this.date = date;
        this.description = description;
    }

    public int getMount() {
        return mount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }


}
