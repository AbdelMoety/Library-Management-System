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
        int index = hash(user.id);
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
                if (temp.user.id == user.id)
                {
                    return false;
                }

                if (temp.next == null)
                {
                    temp.next = N;
                    return true;
                }

                temp = temp.next;
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
            if(temp.user.id == id)
            {
                return temp.user;
            }
            temp = temp.next;
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
                System.out.println("ID: " + temp.user.id + ", Name: " + temp.user.name + ", Year: " + temp.user.year);
                temp = temp.next;
            }
        }
    }

        public boolean canBorrow(Student b)
    {
        if (b.borrowcount< Student.maxBorrow)
        { return true; }
        else
        { return false; }    
    }
}

