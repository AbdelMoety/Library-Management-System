package Main;

import Data_Structures.bookTree;
import Data_Structures.hashTable;
import Data_Structures.undoStack;
import models.admin;

public class start
{
    private static final int NUM_YEARS = 5;
    private static final int numAdmins = 3;
    private static final admin[] adminList = new admin[numAdmins];
    private static hashTable[] studentTables = new hashTable[NUM_YEARS];
    private static bookTree bookTree1 = new bookTree();
    private static undoStack undoStack1 = new undoStack();
    
    static
    {
        for (int i = 0; i < NUM_YEARS; i++)
        {
            studentTables[i] = new hashTable();
        }

        adminList[0] = new admin(266, "admin1008", "Omar");
        adminList[1] = new admin(111, "admin1111", "111");
        adminList[2] = new admin(912, "admin1234", "Mohammed");
    }

    public static int getNumYears()
    {
        return NUM_YEARS;
    }

    public static int getNumAdmins()
    {
        return numAdmins;
    }

    public static admin[] getAdmins()
    {
        return adminList;
    }

    public static hashTable[] getStudenTables()
    {
        return studentTables;
    }

    public static bookTree getBookTree()
    {
        return bookTree1;
    }

    public static undoStack geStack()
    {
        return undoStack1;
    }

    private start()
    {
        // Private constructor to prevent instantiation
    }
}
