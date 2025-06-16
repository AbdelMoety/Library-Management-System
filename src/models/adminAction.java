package models;

public class adminAction
{
    private String actionType;
    private book bookBefore;
    private book bookAfter;

    public adminAction(String actionType, book bookBefore, book bookAfter)
    {
        this.actionType = actionType;
        this.bookBefore = bookBefore;
        this.bookAfter = bookAfter;
    }

    // Getters
    public String getActionType()
    {
        return actionType;
    }

    public book getBookBefore()
    {
        return bookBefore;
    }

    public book getBookAfter()
    {
        return bookAfter;
    }

    // Setters
    public void setActionType(String actionType)
    {
        this.actionType = actionType;
    }

    public void setBookBefore(book bookBefore)
    {
        this.bookBefore = bookBefore;
    }

    public void setBookAfter(book bookAfter)
    {
        this.bookAfter = bookAfter;
    }
}