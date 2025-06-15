package Data_Structures;
// queue for the waiting list for books that are already borrowed

import javax.swing.JTextArea;

import models.Student;
import models.studentNode;

public class queue
{
    public studentNode head;
    public studentNode tail;
    public int length;
    
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
    
    public void printAll(JTextArea area)
    {
        area.setText(""); 
        if (isEmpty())
        {
            area.append("There are no students!\n");
            return;
        }
        
        studentNode temp = head;
        while(temp != null)
        {
            area.append("Name: " + temp.user.name + ", ID: " + temp.user.id + "\n");
            temp = temp.next;
        }
    }
}