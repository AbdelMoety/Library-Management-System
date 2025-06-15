package models;

import java.time.LocalDate;
import Data_Structures.LL;

public class Student
{
    public String name;
    public int id;
    public int year;
    public int borrowcount;
    public static final int maxBorrow = 3;

    public LL borrowHistory = new LL();

    public Student(String name, int id, int year)
    {
        this.name = name;
        this.id = id;
        this.year = year;
        this.borrowcount = 0;
    }

    public void addToHistory(book b)
    {
        borrowedBook newBook = new borrowedBook(b.name, b.id, LocalDate.now());
        borrowHistory.append(newBook);
        borrowcount++;
    }
}
