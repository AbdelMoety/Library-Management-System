// BST node
package models;
public class tNode
{
    public book book;
    public tNode left;
    public tNode right;

    public tNode(book book)
    {
        this.book = book;
        this.left = null;
        this.right = null;
    }
}
