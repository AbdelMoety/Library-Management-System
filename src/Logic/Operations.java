package Logic;

import java.util.Random;

import models.Student;
import models.book;
import models.borrowedBook;
import Main.start;

public class Operations
{
    Random random = new Random();

    public static boolean borrow(Student s, book b)
    {
        if (s.borrowcount >= Student.maxBorrow)
        {
            return false;
        }

        if (!b.isAvailble)
        {
            return false;
        }

        if (b.count > 0)
        {
            s.addToHistory(b);
            b.count--;
            return true;
        }
        
        else
        {
            b.waitingList.Enqueue(s);
            return false;
        }
    }

    public static boolean returnBook(Student s, borrowedBook bo)
    {
        if (s.borrowHistory.exists(bo))
        {
            book b = start.bookTree1.search(bo.id);
            s.borrowcount--;
            b.count++;
            return true;
        }
        return false;
    }

    private int generateID(int year)
    {
        int num = random.nextInt(100, 999);
        String id = String.valueOf(year) + String.valueOf(num);
        return Integer.parseInt(id);
    }

    public int newStudent(String name, int year)
    {
        int id = generateID(year);
        while (start.studentTables[year - 1].getUser(id) != null)
        {
            id = generateID(year);
        }
        Student s = new Student(name, id, year);
        start.studentTables[year - 1].addUser(s);
        return id;
    }

    public String getDueDate(borrowedBook b)
    {
        return "Days left: " + String.valueOf(b.getDaysLeft());
    }

    public void showHistory(Student s)
    {
        if (s.borrowHistory.is_Empty())
        {
            System.out.println("No borrow history.");
            return;
        }

        for (int i = 0; i < s.borrowHistory.length; i++)
        {
            System.out.println("Title: " + s.borrowHistory.getBB(i).name);
            System.out.println("Borrowed on: " + s.borrowHistory.getBB(i).borrowDate);
            System.out.println("Due on: " + s.borrowHistory.getBB(i).dueDate);
            System.out.println("Days left: " + s.borrowHistory.getBB(i).getDaysLeft());
            System.out.println("------");
        }
    }
}