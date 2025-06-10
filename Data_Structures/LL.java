// LL for storing the borrow history of every student
public class LL
{
    Node head;
    Node tail;
    int length = 0;

    public void append(String name)
    {
        Node N = new Node(name);
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

    public String get(int index)
    {
        index--;
        if (index < 0 || index >= length)
        {
            System.out.println("This index is out of range.");
            System.out.println("Linked List length is: " + length);
           return "Value at index " + (index + 1) + " is: null";
        }

        Node temp = head;
        for (int i = 0; i < index; i++)
        {
            temp = temp.next;
        }
        return temp.name;
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
            Node temp = head;
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
            Node temp = head;
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
        Node temp = head;
        while (temp != null)
        {
            System.out.print(temp.name + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}
