// queue for the waiting list for books that are already borrowed
public class queue
{
    studentNode head;
    studentNode tail;
    int length;
    
    public String Enqueue(Student s)
    {
        studentNode N = new studentNode(s);
        
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
        return (s.name + " has been added to the waiting list!");
    }

    public String giveBook(int id)
    {
        if (isEmpty())
        {
            return "There is no students in the waiting list!";
        }

        String student = head.user.name;
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
        
        return (head.user.name);
    }

    public String removeStudent(int id)
    {
        if (isEmpty())
        {
            return ("There is no students waiting!");
        }

        if (head.user.id == id)
        {
            head = head.next;
            length--;
            return (head.user.name + " has been removed from the waiting list!");
        }
    
    
        studentNode temp = head.next;
        while (temp.next != null)
        {
            if (temp.user.id == id)
            {
                temp.next = temp.next.next;
                length--;
                return (temp.user.name + " has been removed from the waiting list!");
            }

            temp = temp.next;
        }

        return (temp.user.name + " is not found in the waiting list!");
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
        
        studentNode temp = head;
        while(temp != null)
        {
            System.out.println(temp.user.name);
            temp = temp.next;
        }
    }
}