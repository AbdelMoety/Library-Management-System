// class for the book containing every book's info
public class book
{
    int id;
    String name;
    String author;
    int count;
    boolean isAvailble = false;
    queue waitingList;

    

    book(int id, String name, String author, int count)
    {
        this.id = id;
        this.name = name;
        this.author = author;
        this.count = count;
    }
    

}
