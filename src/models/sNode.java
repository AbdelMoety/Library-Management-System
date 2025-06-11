package models;
// stack node
public class sNode
{
    public adminAction action;
    public sNode next;

    public sNode(adminAction action)
    {
        this.action = action;
        this.next = null;
    }
}
