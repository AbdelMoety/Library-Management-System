package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class borrowedBook
{
    private String name;
    private int id;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private boolean returned = false;

    public borrowedBook(String name, int id, LocalDate borrowDate)
    {
        this.name = name;
        this.id = id;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(15); // due after 15 days
    }

    // Getters
    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return id;
    }

    public LocalDate getBorrowDate()
    {
        return borrowDate;
    }

    public LocalDate getDueDate()
    {
        return dueDate;
    }

    public long getDaysLeft()
    {
        return ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
    }

    public boolean getReturned()
    {
        return returned;
    }

    // Setters
    public void setName(String name)
    {
        this.name = name;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setBorrowDate(LocalDate borrowDate)
    {
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(15); // update dueDate accordingly
    }

    public void setReturned(boolean returned)
    {
        this.returned = returned;
    }
}