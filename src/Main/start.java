package Main;
import Data_Structures.LL;
import Data_Structures.bookTree;
import Data_Structures.hashTable;
import Data_Structures.queue;
import Data_Structures.undoStack;
import models.admin;


public class start
{
    public static final int NUM_YEARS = 5;
    public static final admin[] adminList ;
    public static final int numAdmins =3 ;
    public static hashTable[] studentTables = new hashTable[NUM_YEARS];
    public static bookTree bookTree1 = new bookTree();
    public static undoStack undoStack = new undoStack();
    public static queue q = new queue();
    public static LL l = new LL();
     

    
    static
    {
        for (int i = 0; i < NUM_YEARS; i++)
        {
            studentTables[i] = new hashTable();
        }
        admin a1= new admin(266, "admin1008", "Omar");
        admin a2= new admin(148, "admin0098", "Ahmed");
        admin a3= new admin(912, "admin1234", "Mohammed");
        adminList = new admin[]{a1, a2, a3};

    }

    private start()
    {
        // Private constructor to prevent instantiation
    }
}
