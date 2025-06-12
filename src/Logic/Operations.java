package Logic;

import java.util.Random;

import models.Student;
import models.book;
import models.borrowedBook;
import Main.start;

public class Operations {
    Random random = new Random();

    public String borrow(Student s, book b) {
        if (s.borrowcount >= Student.maxBorrow) {
            return "This student reached maximum borrowing count.";
        }

        if (!b.isAvailble) {
            return "We don't have this book.";
        }

        if (b.count > 0) {
            s.addToHistory(b.name); // سجل استعارة الكتاب
            b.count--;
            return "Borrowed successfully.";
        } else {
            b.waitingList.Enqueue(s);
            return "The book is out of stock, student added to waiting list.";
        }
    }

    public String returnBook(Student s, book b) {
        // نرجعه لو موجود في الهيستوري
        for (int i = 0; i < s.history.size(); i++) {
            if (s.history.get(i).title.equals(b.name)) {
                s.returnBook(b.name);
                b.count++;
                return "Book returned successfully.";
            }
        }
        return "This student doesn't have this book.";
    }

    private int generateID(int year) {
        int num = random.nextInt(100, 999);
        String id = String.valueOf(year) + String.valueOf(num);
        return Integer.parseInt(id);
    }

    public int newStudent(String name, int year) {
        int id = generateID(year);
        while (start.studentTables[year - 1].getUser(id).startsWith("Name")) {
            id = generateID(year);
        }
        Student s = new Student(name, id, year);
        start.studentTables[year - 1].addUser(s);
        return id;
    }
}
