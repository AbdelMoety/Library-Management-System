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
        N.setNext(top);
        top = N;
    }

    public adminAction pop()
    {
        if(isEmpty())
        {
            return null;
        }

        adminAction action = top.getAction();
        top = top.getNext();
        return action;
    }

    public void displayActions()
    {
        sNode temp = top;
        while (temp != null)
        {
            System.out.println(temp.getAction());
            temp = temp.getNext();
        }
        System.out.println("null");
    }

    public boolean isEmpty()
    {
        return top == null;
    }
}