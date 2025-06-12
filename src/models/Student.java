package models;

import java.time.LocalDate;
import java.util.ArrayList;
import models.borrowedBook;

public class Student {
    public String name;
    public int id;
    public int year;
    public int borrowcount;
    public static final int maxBorrow = 3;

    public ArrayList<borrowedBook> history = new ArrayList<>();

    public Student(String name, int id, int year) {
        this.name = name;
        this.id = id;
        this.year = year;
        this.borrowcount = 0;
    }

    public void addToHistory(String bookTitle) {
        borrowedBook newBook = new borrowedBook(bookTitle, LocalDate.now());
        history.add(newBook);
        borrowcount++;
    }

    public void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No borrow history.");
            return;
        }

        for (borrowedBook book : history) {
            System.out.println("Title: " + book.title);
            System.out.println("Borrowed on: " + book.borrowDate);
            System.out.println("Due on: " + book.dueDate);
            System.out.println("Days left: " + book.getDaysLeft());
            System.out.println("------");
        }
    }

    public void returnBook(String title) {
        for (int i = 0; i < history.size(); i++) {
            if (history.get(i).title.equals(title)) {
                history.remove(i);
                borrowcount--;
                System.out.println("Book returned: " + title);
                return;
            }
        }
        System.out.println("This book is not found in your history.");
    }
}
