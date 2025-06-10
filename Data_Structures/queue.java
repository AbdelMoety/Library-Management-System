// queue for the waiting list for books that are already borrowed
public class queue
{
    Node head;
    Node tail;
    int length;
    
    public String Enqueue(String name)
    {
        Node N = new Node(name);
        
        if (isEmpty())
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
        return (name + " has been added to the waiting list!");
    }

    public String giveBook(int id)
    {
        if (isEmpty())
        {
            return "There is no students in the waiting list!";
        }

        String student = head.name;
        head = head.next;
        length--;
        return ("Book " + id + " has been given to " + student);
    }

    public String peek()
    {
        if (isEmpty())
        {
            return ("None");
        }
        
        return (head.name);
    }

    public String removeStudent(String name)
    {
        if (isEmpty())
        {
            return ("There is no students waiting!");
        }

        if (head.name.equalsIgnoreCase(name))
        {
            head = head.next;
            length--;
            return (name + " has been removed from the waiting list!");
        }
    
    
        Node temp = head.next;
        while (temp.next != null)
        {
            if (temp.next.name.equalsIgnoreCase(name))
            {
                temp.next = temp.next.next;
                length--;
                return (name + " has been removed from the waiting list!");
            }

            temp = temp.next;
        }

        return (name + " is not found in the waiting list!");
    }

    public boolean isEmpty()
    {
        return length == 0;
    }

    public int numStudents()
    {
        return length;
    }
    
    public void printAll()
    {
        if (isEmpty())
        {
            System.out.println("There are no students!");
            return;
        }
        
        Node temp = head;
        while(temp != null)
        {
            System.out.println(temp.name);
            temp = temp.next;
        }
    }
}