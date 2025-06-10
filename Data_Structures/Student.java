// class for students containing every student's info
public class Student
{
    public String name;
    public int id;
    public LL borrowHistory;
    public int borrowcount;

    Student(String name, int id)
    {
        this.name = name;
        this.id = id;
        this.borrowHistory = new LL();
        this.borrowcount = 0;
    }
}