package Logic;

import Main.start;
import models.book;
import models.adminAction;
import Data_Structures.bookTree;


public class adminOperations 
{
    public static String login(int id, String password) 
    {
        //admin temp= new admin(id, password);
        for (int i=0 ; i< start.numAdmins ; i++) 
        {
            if (start.adminList[i].id == id && start.adminList[i].password.equals(password) ) {
                return "Welcome " + start.adminList[i].name;
            }
        }
        return "Invalid ID or password";
    } 

     
    public static String addBook(int id, String name, String author, int count) 
    {
        book b = new book(id, name, author, count);
        String result = start.bookTree1.add(b);
        start.undoStack.push(new adminAction("ADD", null, b));
        return result;
    }

    
    public static String deleteBook(int id, int num) {
        book targetBook = start.bookTree1.search(id);
        if (targetBook == null) {
            return "Book not found.";
        }

        // make a copy of book before del to use in undo operation
        book bookBefore = new book(targetBook.id, targetBook.name, targetBook.author, targetBook.count);
        boolean successdel = start.bookTree1.delete(targetBook, num);
        book bookAfter = new book(targetBook.id, targetBook.name, targetBook.author, targetBook.count - num);
        if (successdel) {
            start.undoStack.push(new adminAction("DELETE", bookBefore, bookAfter));
            return "Book deleted successfully.";
        } else {
            return "Book could not be deleted.";
        }
    }

    
    public static String modifyBookID(int oldID, int newID) {
        book oldBook = start.bookTree1.search(oldID);
        if (oldBook == null) {
            return "Book with ID " + oldID + " not found.";
        }

        start.bookTree1.search(oldID).id = newID;
        book bookAfter = new book(newID, oldBook.name, oldBook.author, oldBook.count);

        start.undoStack.push(new adminAction("MODIFY", oldBook, bookAfter));
        return "Book ID modified from: " + oldID + " to: " + newID;
    }

    
    public static String undoLastOperation() 
    {
        if (start.undoStack.isEmpty()) return "No operations to undo.";

        adminAction action = start.undoStack.pop();

        switch (action.actionType) {
            case "ADD":
                start.bookTree1.delete(action.bookAfter, action.bookAfter.count);
                return " Added book removed.";

            case "DELETE":
                action.bookAfter.count = action.bookBefore.count;  
               
                return "The deleted book is added again.";

            case "MODIFY":
                start.bookTree1.search(action.bookAfter.id).id = action.bookBefore.id;
                return "book is unmodified";

            default:
                return "Unknown action.";
        }
    }
}
