// stack node
public class sNode
{
    public adminAction action;
    public sNode next;

    sNode(adminAction action)
    {
        this.action = action;
        this.next = null;
    }
}
