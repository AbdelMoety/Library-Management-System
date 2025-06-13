package models;

public class bookNode
{
    public book book;
    public bookNode next;

    public bookNode(book b)
    {
        this.book = b;
        this.next = null;
    }
}
