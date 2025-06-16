package models;

public class Node
{
    private String name;
    private Node next;

    public Node(String name)
    {
        this.name = name.toLowerCase();
        this.next = null;
    }

    // Getters
    public String getName()
    {
        return name;
    }

    public Node getNext()
    {
        return next;
    }

    // Setters
    public void setName(String name)
    {
        this.name = name.toLowerCase();
    }

    public void setNext(Node next)
    {
        this.next = next;
    }
}