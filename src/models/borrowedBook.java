package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class borrowedBook {
    public String title;
    public LocalDate borrowDate;
    public LocalDate dueDate;

    public borrowedBook(String title, LocalDate borrowDate) {
        this.title = title;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(15); // due after 15 days
    }

    public long getDaysLeft() {
        return ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
    }
}
