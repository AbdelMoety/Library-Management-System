package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class borrowedBook {
    public String name;
    public int id;
    public LocalDate borrowDate;
    public LocalDate dueDate;

    public borrowedBook(String name, int id, LocalDate borrowDate)
    {
        this.name = name;
        this.id = id;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(15); // due after 15 days
    }

    public long getDaysLeft()
    {
        return ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
    }
}
