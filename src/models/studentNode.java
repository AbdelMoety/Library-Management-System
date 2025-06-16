package models;

public class studentNode
{
    private Student student;
    private studentNode next;

    public studentNode(Student student)
    {
        this.student = student;
        this.next = null;
    }

    // Getters
    public Student getStudent()
    {
        return student;
    }

    public studentNode getNext()
    {
        return next;
    }

    // Setters
    public void setStudent(Student student)
    {
        this.student = student;
    }

    public void setNext(studentNode next)
    {
        this.next = next;
    }
}