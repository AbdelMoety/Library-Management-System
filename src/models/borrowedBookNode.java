package models;

public class borrowedBookNode
{
    public borrowedBook borrowedBook;
    public borrowedBookNode next;

    public borrowedBookNode(borrowedBook borrowedBook)
    {
        this.borrowedBook = borrowedBook;
        this.next = null;
    }
}
