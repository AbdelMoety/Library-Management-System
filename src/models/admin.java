package models;

public class admin 
{
    private int id;
    private String password;
    private String name;

    public admin(int id, String password, String name)
    {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    // Getters
    public int getId()
    {
        return id;
    }

    public String getPassword()
    {
        return password;
    }

    public String getName()
    {
        return name;
    }

    // Setters
    public void setId(int id)
    {
        this.id = id;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}