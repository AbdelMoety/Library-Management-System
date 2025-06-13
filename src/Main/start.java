package Main;
import Data_Structures.LL;
import Data_Structures.bookTree;
import Data_Structures.hashTable;
import Data_Structures.queue;
import Data_Structures.undoStack;

public class start
{
    public static final int NUM_YEARS = 5;
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
    }

    private start()
    {
        // Private constructor to prevent instantiation
    }
}
