package models;

import java.time.LocalDate;
import Data_Structures.LL;

public class Student
{
    private String name;
    private int id;
    private int year;
    private int borrowcount;
    private static final int maxBorrow = 3;

    private LL borrowHistory = new LL();

    public Student(String name, int id, int year)
    {
        this.name = name;
        this.id = id;
        this.year = year;
        this.borrowcount = 0;
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

    public int getYear()
    {
        return year;
    }

    public int getBorrowCount()
    {
        return borrowcount;
    }

    public static int getMaxBorrow()
    {
        return maxBorrow;
    }

    public LL getBorrowHistory()
    {
        return borrowHistory;
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

    public void setYear(int year)
    {
        this.year = year;
    }

    public void setBorrowCount(int borrowcount)
    {
        this.borrowcount = borrowcount;
    }

    public void setBorrowHistory(LL borrowHistory)
    {
        this.borrowHistory = borrowHistory;
    }

    public boolean canBorrow()
    {
        if (borrowcount < maxBorrow)
        {
            return true;
        }
        
        else
        {
            return false;
        }    
    }

    public void addToHistory(book b)
    {
        borrowedBook newBook = new borrowedBook(b.getName(), b.getId(), LocalDate.now());
        borrowHistory.append(newBook);
        borrowcount++;
    }
}
