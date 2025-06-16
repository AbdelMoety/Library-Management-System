package models;

import Data_Structures.*;

public class book
{
    private int id;
    private String name;
    private String author;
    private int count;
    private boolean isAvailble = false;
    private queue waitingList = new queue();

    public book(int id, String name, String author, int count)
    {
        this.id = id;
        this.name = name;
        this.author = author;
        this.count = count;
    }

    // Getters
    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getAuthor()
    {
        return author;
    }

    public int getCount()
    {
        return count;
    }

    public boolean getIsAvailable()
    {
        return isAvailble;
    }

    public queue getWaitingList()
    {
        return waitingList;
    }

    // Setters
    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public void setIsAvailable(boolean isAvailble)
    {
        this.isAvailble = isAvailble;
    }

    public void setWaitingList(queue waitingList)
    {
        this.waitingList = waitingList;
    }

    public boolean doesExist()
    {
        return count > 0;
    }
}