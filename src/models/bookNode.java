package models;

public class bookNode
{
    private book book;
    private bookNode next;

    public bookNode(book b)
    {
        this.book = b;
        this.next = null;
    }

    // Getters
    public book getBook()
    {
        return book;
    }

    public bookNode getNext()
    {
        return next;
    }

    // Setters
    public void setBook(book book)
    {
        this.book = book;
    }

    public void setNext(bookNode next)
    {
        this.next = next;
    }
}