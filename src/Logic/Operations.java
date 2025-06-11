package Logic;

import java.util.Random;
import models.Student;
import models.book;


import Main.start;

public class Operations {
    //this class will contain methods to handle borrowing requests from studnets
    //the input is entered by admin
    Random random= new Random();

    public String borrow(Student s, book b )
    {
        if (s.borrowcount< 3 && b.isAvailble ) //checks that student didnt exceed borrowing limit and book is available
        {
            if (b.count != 0) //add the book to the students list and subtract the count
            {
                s.borrowHistory.append(b); // call the add to history method(from Sohaila)
                b.count--;
                s.borrowcount++;
                return "Borrowed successfully";
            }
            else  // add the student to the waiting list
            {
                b.waitingList.Enqueue(s);
                return "The book is out of stock, the student is added to the waiting list";
            }
        }

        if (!b.isAvailble)
        {
            return "We dont have this book";
        }
        
        if (s.borrowcount>= 3)
        {
            return "This student reached maximum borrowing count";
        }
        return null;
        
    }

    public String returnBook(Student s, book b)
    {
        if (s.borrowHistory.exists(b))
        {
            s.borrowcount--;
            b.count++;
            return "Book returned successfully";
        }
        return "This is not right,he is an imposter, RUN!!";
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
        while(start.studentTables[year - 1].getUser(id).startsWith("Name"))
        {
            id = generateID(year);
        }
        Student s = new Student(name, id, year);
        start.studentTables[year - 1].addUser(s);
        return id;
    }
}
