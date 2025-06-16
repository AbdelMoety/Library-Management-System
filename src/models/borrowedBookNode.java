package models;

public class borrowedBookNode
{
    private borrowedBook borrowedBook;
    private borrowedBookNode next;

    public borrowedBookNode(borrowedBook borrowedBook)
    {
        this.borrowedBook = borrowedBook;
        this.next = null;
    }

    // Getters
    public borrowedBook getBorrowedBook()
    {
        return borrowedBook;
    }

    public borrowedBookNode getNext()
    {
        return next;
    }

    // Setters
    public void setBorrowedBook(borrowedBook borrowedBook)
    {
        this.borrowedBook = borrowedBook;
    }

    public void setNext(borrowedBookNode next)
    {
        this.next = next;
    }
}