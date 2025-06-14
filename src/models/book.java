package models;
import Data_Structures.*;
// class for the book containing every book's info
public class book
{
    public int id;
    public String name;
    public String author;
    public int count;
    public boolean isAvailble = false;
    public queue waitingList = new queue();

    public book(int id, String name, String author, int count)
    {
        this.id = id;
        this.name = name;
        this.author = author;
        this.count = count;
        
    }

}
