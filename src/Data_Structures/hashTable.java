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
    
    public String addUser(Student user)
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
                    return user.name + " already exists in the system.";
                }

                if (temp.next == null)
                {
                    temp.next = N;
                    return user.name + " was added to the system.";
                }

                temp = temp.next;
            }
        }
        return user.name + " was added to the system.";
    }

    public String getUser(int id)
    {
        int index = hash(id);
        studentNode temp = table[index];

        while(temp != null)
        {
            if(temp.user.id == id)
            {
                return "Name: " + temp.user.name + ", ID: " + temp.user.id;
            }
            temp = temp.next;
        }

        return "User with id: " + id + " doesn't exist in the system.";
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









    public static void main(String[] args)
    {
        hashTable h = new hashTable();
        Student u1 = new Student("Ahmed", 103, 4);
        Student u2 = new Student("Ali", 113, 1);
        Student u3 = new Student("Mahmoud", 109, 4);
        Student u4 = new Student("Mohammed", 101,3);
        Student u5 = new Student("Noah", 112,2);

        h.addUser(u1);
        h.addUser(u2);
        h.addUser(u3);
        h.addUser(u4);
        h.addUser(u5);

        h.displayUser();
    }
}

