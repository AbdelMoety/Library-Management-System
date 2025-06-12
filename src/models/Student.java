// class for students containing every student's info
package models;

import java.util.ArrayList;

import Data_Structures.LL;
import models.borrowedBook;

public class Student {
    public String name;
    public int id;
    public int year;
    public LL borrowHistory;
    public int borrowcount;
    public static int maxBorrow = 3;

    public ArrayList<borrowedBook> history = new ArrayList<>();

    public Student(String name, int id, int year) {
        this.name = name;
        this.id = id;
        this.year = year;
        this.borrowHistory = new LL();
        this.borrowcount = 0;
    }

}