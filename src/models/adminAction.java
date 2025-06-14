package models;
// class for the actions that the admin can do to the books in the BST
public class adminAction
{
    public String actionType; // "ADD", "DELETE", "MODIFY"
    public book bookBefore;   // book before action (null for ADD)
    public book bookAfter;    // book after action (null for DELETE)

    public adminAction(String actionType, book bookBefore, book bookAfter)
    {
        this.actionType = actionType;
        this.bookBefore = bookBefore;
        this.bookAfter = bookAfter;
    }
}