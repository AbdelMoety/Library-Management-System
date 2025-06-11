// student node
package models;
public class studentNode {
    public Student user;
    public studentNode next;

    public studentNode(Student user)
    {
        this.user = user;
        this.next = null;
    }
}