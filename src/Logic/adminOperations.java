package Logic;

import Main.start;
import models.book;
import models.adminAction;
import models.Student;


public class adminOperations 
{
    public static boolean login(int id, String password) 
    {
        models.admin[] admins = start.getAdmins();
        
        for (int i = 0 ; i < start.getNumAdmins() ; i++) 
        {
            if (admins[i].getId() == id && admins[i].getPassword().equals(password) )
            {
                return true;
            }
        }

        return false;
    } 

     
    public static boolean addBook(int id, String name, String author, int count) 
    {
        book b = start.getBookTree().search(id);
        if(b == null )
        {
            b = new book(id, name, author, count);
            start.getBookTree().add(b);
            start.geStack().push(new adminAction("ADD", null, b));
        }
        
        else
        {
            book bookBefore = new book(b.getId(), b.getName(), b.getAuthor(), b.getCount());
            book bookAfter = new book(id, name, author, count+b.getCount());
            b.setCount(b.getCount() + count);
            Student[] students = b.getWaitingList().getQueueWaitingList();
            int length = students.length;
            if (length <= count)
            {
                for (int i = 0; i < length; i++)
                {
                    Operations.borrow(students[i], b);
                    b.getWaitingList().Dequeue();
                }
            }

            else
            {
                for (int i = 0; i < count; i++)
                {
                    Operations.borrow(students[i], b);
                    b.getWaitingList().Dequeue();
                }
            }
            start.geStack().push(new adminAction("INC", bookBefore, bookAfter));
        }
        
        b.setIsAvailable(true);
        
        return true;
    }

    
    public static boolean deleteBook(int id, int num)
    {
        book targetBook = start.getBookTree().search(id);
        if (targetBook == null)
        {
            return false;
        }

        // make a copy of book before del to use in undo operation
        book bookBefore = new book(targetBook.getId(), targetBook.getName(), targetBook.getAuthor(), targetBook.getCount());
        boolean successdel = start.getBookTree().deleteCount(targetBook, num);
        book bookAfter = new book(targetBook.getId(), targetBook.getName(), targetBook.getAuthor(), targetBook.getCount() - num);
        if (successdel)
        {
            start.geStack().push(new adminAction("DELETE", bookBefore, bookAfter));
            return true;
        }
        
        else
        {
            return false;
        }
    }

    
    public static boolean modifyBookID(int oldID, int newID)
    {
        book oldBook = start.getBookTree().search(oldID);
        if (oldBook == null)
        {
            return false;
        }
        
        book bookBefore = new book(oldID, oldBook.getName(), oldBook.getAuthor(), oldBook.getCount());
        start.getBookTree().search(oldID).setId(newID);
        book bookAfter = new book(newID, oldBook.getName(), oldBook.getAuthor(), oldBook.getCount());

        start.geStack().push(new adminAction("MODIFY", bookBefore, bookAfter));
        return true;
    }

    
    public static boolean undoLastOperation() 
    {
        if (start.geStack().isEmpty()) return false;

        adminAction action = start.geStack().pop();

        switch (action.getActionType())
        {
            case "INC":
                start.getBookTree().deleteCount(action.getBookBefore(), action.getBookAfter().getCount());
                start.getBookTree().search(action.getBookAfter().getId()).setCount(action.getBookBefore().getCount());
                return true;

            case "ADD":
                start.getBookTree().delete(action.getBookAfter(), action.getBookAfter().getCount());
                return true;

            case "DELETE": 
                start.getBookTree().search(action.getBookAfter().getId()).setCount(action.getBookBefore().getCount());
                return true;

            case "MODIFY":
                start.getBookTree().search(action.getBookAfter().getId()).setId(action.getBookBefore().getId());
                return true;

            default:
                return false;
        }
    }
}
