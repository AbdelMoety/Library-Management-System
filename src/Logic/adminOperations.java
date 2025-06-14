package Logic;

import Main.start;
import models.book;
import models.adminAction;


public class adminOperations 
{
    public static boolean login(int id, String password) 
    {
        //admin temp= new admin(id, password);
        for (int i=0 ; i< start.numAdmins ; i++) 
        {
            if (start.adminList[i].id == id && start.adminList[i].password.equals(password) )
            {
                return true;
            }
        }

        return false;
    } 

     
    public static boolean addBook(int id, String name, String author, int count) 
    {
        book b = new book(id, name, author, count);
        start.undoStack.push(new adminAction("ADD", null, b));
        return true;
    }

    
    public static boolean deleteBook(int id, int num)
    {
        book targetBook = start.bookTree1.search(id);
        if (targetBook == null)
        {
            return false;
        }

        // make a copy of book before del to use in undo operation
        book bookBefore = new book(targetBook.id, targetBook.name, targetBook.author, targetBook.count);
        boolean successdel = start.bookTree1.delete(targetBook, num);
        book bookAfter = new book(targetBook.id, targetBook.name, targetBook.author, targetBook.count - num);
        if (successdel)
        {
            start.undoStack.push(new adminAction("DELETE", bookBefore, bookAfter));
            return true;
        }
        
        else
        {
            return false;
        }
    }

    
    public static boolean modifyBookID(int oldID, int newID)
    {
        book oldBook = start.bookTree1.search(oldID);
        if (oldBook == null)
        {
            return false;
        }

        start.bookTree1.search(oldID).id = newID;
        book bookAfter = new book(newID, oldBook.name, oldBook.author, oldBook.count);

        start.undoStack.push(new adminAction("MODIFY", oldBook, bookAfter));
        return true;
    }

    
    public static boolean undoLastOperation() 
    {
        if (start.undoStack.isEmpty()) return false;

        adminAction action = start.undoStack.pop();

        switch (action.actionType)
        {
            case "ADD":
                start.bookTree1.delete(action.bookAfter, action.bookAfter.count);
                return false;

            case "DELETE":
                action.bookAfter.count = action.bookBefore.count;  
               
                return false;

            case "MODIFY":
                start.bookTree1.search(action.bookAfter.id).id = action.bookBefore.id;
                return false;

            default:
                return false;
        }
    }
}
