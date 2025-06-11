package models;
// LL node
public class Node
{
    public String name;
    public Node next;

    public Node(String name)
    {
        this.name = name.toLowerCase();
        this.next = null;
    }
}