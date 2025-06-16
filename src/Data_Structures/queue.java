package Data_Structures;
// queue for the waiting list for books that are already borrowed

import javax.swing.JTextArea;

import models.Student;
import models.studentNode;

public class queue
{
    private studentNode head;
    private studentNode tail;
    private int length;

    public studentNode getHead()
    {
        return head;
    }
    
    public studentNode getTail()
    {
        return tail;
    }

    public int getLength()
    {
        return length;
    }
    
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
            tail.setNext(N);
            tail = N;
        }
        
        length++;
        return (s.getName() + " has been added to the waiting list!");
    }

    public String giveBook(int id)
    {
        if (isEmpty())
        {
            return "There is no students in the waiting list!";
        }

        String student = head.getStudent().getName();
        head = head.getNext();
        length--;
        return ("Book " + id + " has been given to " + student);
    }

    public String peek()
    {
        if (isEmpty())
        {
            return ("None");
        }
        
        return (head.getStudent().getName());
    }

    public String removeStudent(int id)
    {
        if (isEmpty())
        {
            return ("There is no students waiting!");
        }

        if (head.getStudent().getId() == id)
        {
            head = head.getNext();
            length--;
            return (head.getStudent().getName() + " has been removed from the waiting list!");
        }
    
    
        studentNode temp = head.getNext();
        while (temp.getNext() != null)
        {
            if (temp.getStudent().getId() == id)
            {
                temp.setNext(temp.getNext().getNext());
                length--;
                return (temp.getStudent().getName() + " has been removed from the waiting list!");
            }

            temp = temp.getNext();
        }

        return (temp.getStudent().getName() + " is not found in the waiting list!");
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
            area.append("Name: " + temp.getStudent().getName() + ", ID: " + temp.getStudent().getId() + "\n");
            temp = temp.getNext();
        }
    }
}