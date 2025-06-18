package Logic;

import java.util.Random;

import models.Student;
import models.book;
import models.borrowedBook;
import models.borrowedBookNode;
import models.studentNode;
import Main.start;

public class Operations
{
    Random random = new Random();

    public static boolean borrow(Student s, book b)
    {
        
        if (s.getBorrowHistory().getBB(b.getId()) != null)
        {
            if (!s.getBorrowHistory().getBB(b.getId()).getReturned())
            {
                return false;
            }
        }

        if (!s.canBorrow() || !b.getIsAvailable())
        {
            return false;
        }

        if (b.doesExist())
        {
            s.addToHistory(b);
            b.setCount(b.getCount() - 1);
            return true;
        }
        
        else
        {
            b.getWaitingList().Enqueue(s);
            return false;
        }
    }

    public static boolean returnBook(Student s, borrowedBook bo)
    {
        if (s.getBorrowHistory().exists(bo))
        {
            book b = start.getBookTree().search(bo.getId());
            s.setBorrowCount(s.getBorrowCount() - 1);
            b.setCount(b.getCount() + 1);
            Operations.borrow(b.getWaitingList().peek(), b);
            b.getWaitingList().Dequeue();
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
        Data_Structures.hashTable[] table = start.getStudenTables();

        while (table[year - 1].getUser(id) != null)
        {
            id = generateID(year);
        }
        Student s = new Student(name, id, year);
        table[year - 1].addUser(s);
        return id;
    }

    public String getDueDate(borrowedBook b)
    {
        return "Days left: " + String.valueOf(b.getDaysLeft());
    }

    public borrowedBook[] showHistory(Student s)
    {
        borrowedBookNode temp = s.getBorrowHistory().getHead();
        borrowedBook[] borrowHis = new borrowedBook[s.getBorrowHistory().getLength()];
        
        for (int i=0; i < s.getBorrowHistory().getLength(); i++)
        {
            borrowHis[i] = temp.getBorrowedBook();
            temp = temp.getNext();
        }
        
        return borrowHis;
    }

    public Student[] displayBookWaitingList(int bookId)
    {
        book foundBook = start.getBookTree().search(bookId);
        studentNode temp = foundBook.getWaitingList().getHead();
        Student[] waitingList = new Student[foundBook.getWaitingList().getLength()];
        for (int i = 0; i < foundBook.getWaitingList().getLength(); i++)
        {
            waitingList[i] = temp.getStudent();
            temp = temp.getNext();
        }
        return waitingList;
    }
}