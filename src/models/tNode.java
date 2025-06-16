package models;

public class tNode
{
    private book book;
    private tNode left;
    private tNode right;

    public tNode(book book)
    {
        this.book = book;
        this.left = null;
        this.right = null;
    }

    // Getters
    public book getBook()
    {
        return book;
    }

    public tNode getLeft()
    {
        return left;
    }

    public tNode getRight()
    {
        return right;
    }

    // Setters
    public void setBook(book book)
    {
        this.book = book;
    }

    public void setLeft(tNode left)
    {
        this.left = left;
    }

    public void setRight(tNode right)
    {
        this.right = right;
    }
}