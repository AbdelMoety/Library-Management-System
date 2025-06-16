package models;

public class sNode
{
    private adminAction action;
    private sNode next;

    public sNode(adminAction action)
    {
        this.action = action;
        this.next = null;
    }

    // Getters
    public adminAction getAction()
    {
        return action;
    }

    public sNode getNext()
    {
        return next;
    }

    // Setters
    public void setAction(adminAction action)
    {
        this.action = action;
    }

    public void setNext(sNode next)
    {
        this.next = next;
    }
}