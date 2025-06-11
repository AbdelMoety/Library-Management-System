package Data_Structures;
// stack for storing admin operations so we can undo them

import models.adminAction;
import models.sNode;

public class undoStack
{
    private sNode top;

    public void push(adminAction action)
    {
        sNode N = new sNode(action);
        N.next = top;
        top = N;
    }

    public adminAction pop()
    {
        if(isEmpty())
        {
            return null;
        }

        adminAction action = top.action;
        top = top.next;
        return action;
    }

    public boolean isEmpty()
    {
        return top == null;
    }
}