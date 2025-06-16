package Data_Structures;
// LL for storing the borrow history of every student

import models.borrowedBook;
import models.borrowedBookNode;

public class LL
{
    private borrowedBookNode head;
    private borrowedBookNode tail;
    private int length = 0;

    public borrowedBookNode getHead()
    {
        return head;
    }
    
    public borrowedBookNode getTail()
    {
        return tail;
    }

    public int getLength()
    {
        return length;
    }

    public void append(borrowedBook b)
    {
        borrowedBookNode N = new borrowedBookNode(b);
        
        if (length == 0)
        {
            head = N;
            tail = N;
        }

        else
        {
            tail.setNext(N);
            tail = N;
        }
        
        length++;
    }

    public boolean exists(borrowedBook b)
    {
        borrowedBookNode temp = head;
        while (temp != null)
        {
            if (temp.getBorrowedBook() == b)
            {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    public String get(int index)
    {
        index--;
        if (index < 0 || index >= length)
        {
            System.out.println("This index is out of range.");
            System.out.println("Linked List length is: " + length);
           return "Value at index " + (index + 1) + " is: null";
        }

        borrowedBookNode temp = head;
        for (int i = 0; i < index; i++)
        {
            temp = temp.getNext();
        }
        return temp.getBorrowedBook().getName();
    }

    public borrowedBook getBB(int id)
    {
        borrowedBookNode temp = head;
        while (temp != null)
        {
            if (temp.getBorrowedBook().getId() == id)
            {
                return temp.getBorrowedBook();
            }
            temp = temp.getNext();
        }
        return null;
    }

    public void remove_first()
    {
        if (length == 0)
        {
            System.out.println("Empty Linked List. There is nothing to delete.");
            return;
        }

        else if(length == 1)
        {
            head = null;
            tail = null;
        }

        else
        {
            head = head.getNext();
            length--;
        }
    }

    public void remove(int index)
    {
        index--;
        if (index < 1 || index >= length)
        {
            System.out.println("This index is out of range.");
            System.out.println("Linked List length is: " + length);
            return;
        }
        
        if (length == 0 || length == 1 || index == 0)
        {
            remove_first();
            return;
        }
        
        if (index == length - 1)
        {
            remove_last();
        }
        
        else
        {
            borrowedBookNode temp = head;
            for (int i = 0; i < index - 1; i++)
            {
                temp = temp.getNext();
            }
            temp.setNext(temp.getNext().getNext());
            length--;
        }
    }
    
    public void remove_last()
    {
        if (length == 0 || length == 1)
        {
            remove_first();
        }

        else
        {
            borrowedBookNode temp = head;
            while(temp.getNext().getNext() != null)
            {
                temp = temp.getNext();
            }
            
            temp.setNext(null);
            tail = temp;
        }
        
        length--;
    }

    public boolean is_Empty()
    {
        return length == 0;
    }
}