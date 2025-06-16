package Data_Structures;
// hash table for storing students providing O(1) average case for searching

import models.Student;
import models.studentNode;

public class hashTable
{
    private static final int size = 10;
    private studentNode[] table = new studentNode[size];

    private int hash(int id)
    {
        return id % size;
    }
    
    public boolean addUser(Student user)
    {
        int index = hash(user.getId());
        studentNode N = new studentNode(user);

        if(table[index] == null)
        {
            table[index] = N;
        }

        else
        {
            studentNode temp = table[index];
            while (true)
            {
                if (temp.getStudent().getId() == user.getId())
                {
                    return false;
                }

                if (temp.getNext() == null)
                {
                    temp.setNext(N);
                    return true;
                }

                temp = temp.getNext();
            }
        }
        return true;
    }

    public Student getUser(int id)
    {
        int index = hash(id);
        studentNode temp = table[index];

        while(temp != null)
        {
            if(temp.getStudent().getId() == id)
            {
                return temp.getStudent();
            }
            temp = temp.getNext();
        }

        return null;
    }

    public void displayUser()
    {
        for(int i = 0; i < size; i++)
        {
            studentNode temp = table[i];
            while(temp != null)
            {
                System.out.println("ID: " + temp.getStudent().getId() + ", Name: " + temp.getStudent().getName() + ", Year: " + temp.getStudent().getYear());
                temp = temp.getNext();
            }
        }
    }
}

