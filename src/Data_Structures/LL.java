package Data_Structures;
// LL for storing the borrow history of every student

import models.book;
import models.bookNode;

public class LL
{
    bookNode head;
    bookNode tail;
    int length = 0;

    public void append(book b)
    {
        bookNode N = new bookNode(b);
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

    public boolean exists(book b)
    {
        bookNode temp = head;
        while (temp != null)
        {
            if (temp.book == b)
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

        bookNode temp = head;
        for (int i = 0; i < index; i++)
        {
            temp = temp.next;
        }
        return temp.book.name;
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
            bookNode temp = head;
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
            bookNode temp = head;
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
        bookNode temp = head;
        while (temp != null)
        {
            System.out.print(temp.book.name + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main( String args[])
    {
        LL l = new LL();
        book b1 = new book(110, null, null, 2);
        book b2 = new book(050, null, null, 2);
        book b3 = new book(30, null, null, 2);
        l.append(b1);
       
        l.append(b3);
        System.out.println(l.exists(b2));
    }
}
