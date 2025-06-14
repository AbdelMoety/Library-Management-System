package Data_Structures;
// LL for storing the borrow history of every student

import models.borrowedBook;
import models.borrowedBookNode;

public class LL
{
    borrowedBookNode head;
    borrowedBookNode tail;
    public int length = 0;

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
            tail.next = N;
            tail = N;
        }
        length++;
    }

    public boolean exists(borrowedBook b)
    {
        borrowedBookNode temp = head;
        while (temp != null)
        {
            if (temp.borrowedBook == b)
            {
                return true;
            }
            temp = temp.next;
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
            temp = temp.next;
        }
        return temp.borrowedBook.name;
    }

    public borrowedBook getBB(int id)
    {
        borrowedBookNode temp = head;
        while (temp.next != null)
        {
            if (temp.borrowedBook.id == id)
            {
                return temp.borrowedBook;
            }
            temp = temp.next;
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
            head = head.next;
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
                temp = temp.next;
            }
            temp.next = temp.next.next;
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
            while(temp.next.next != null)
            {
                temp = temp.next;
            }
            temp.next = null;
            tail = temp;
        }
        length--;
    }

    public void display()
    {
        borrowedBookNode temp = head;
        while (temp != null)
        {
            System.out.print(temp.borrowedBook.name + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public boolean is_Empty()
    {
        return length == 0;
    }
}