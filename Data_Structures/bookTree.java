// BST containing books sorted by id
public class bookTree
{
    private tNode root;

    public String insert(book book)
    {
        if(root == null)
        {
            root = new tNode(book);
            return "Book with name: " + book.name + ",and ID: " + book.id + " was added succefully.";
        }
        return insertRecursion(root, book);
    }

    private String insertRecursion(tNode current, book book)
    {
        if(book.id == current.book.id)
        {
            return "Book with name: " + book.name + ",and ID: " + book.id + " was added succefully.";
        }
        
        else if(book.id < current.book.id)
        {
            if(current.left == null)
            {
                current.left = new tNode(book);
                return "Book with name: " + book.name + ",and ID: " + book.id + " was added succefully.";
            }

            return insertRecursion(current.left, book);
        }

        else
        {
            if(current.right == null)
            {
                current.right = new tNode(book);
                return "Book with name: " + book.name + ", and ID: " + book.id + " was added succefully.";
            }

            return insertRecursion(current.right, book);
        }
    }

    public String search(int id)
    {
        return searchRecursion(root, id);
    }

    private String searchRecursion(tNode current, int id)
    {
        if(current == null)
        {
            return "There is no books in the library with this id.";
        }
        
        if(current.book.id == id)
        {
            return "Name: " + current.book.name + ", ID: " + current.book.id + ", Author: " + current.book.author;
        }

        if(current.book.id > id)
        {
            return searchRecursion(current.left, id);
        }

        return searchRecursion(current.right, id);
    }

    public void showBooks(tNode current)
    {
        if(current != null)
        {
            showBooks(current.left);
            System.out.println("Book name: " + current.book.name + ", ID: " + current.book.id + ", Author: " + current.book.author);
            showBooks(current.right);
        }
    }

    public void showSameAuthor(tNode current, String author)
    {
        if(current != null)
        {
            showSameAuthor(current.left, author);

            if (author.equalsIgnoreCase(current.book.author))
            {
                System.out.println("Book name: " + current.book.name + ", ID: " + current.book.id + ", Author: " + current.book.author);
            }

            showSameAuthor(current.right, author);
        }
    }

    public boolean delete(int id)
    {
        String found = search(id);
        if (found.startsWith("There"))
        {
            return false;
        }
        root = deleteRecursion(root, id);
        return true;
    }

    private tNode deleteRecursion(tNode current, int id)
    {
        if (current == null)
        {
            return null;
        }

        if (id < current.book.id)
        {
            current.left = deleteRecursion(current.left, id);
        }
        
        else if (id > current.book.id)
        {
            current.right = deleteRecursion(current.right, id);
        }
        
        else
        {
            if (current.left == null && current.right == null)
            {
                return null;
            }
            
            else if (current.left == null)
            {
                return current.right;
            }
            
            else if (current.right == null)
            {
                return current.left;
            }
            
            else
            {
                tNode smallest = findSmallest(current.right);
                current.book = smallest.book;
                current.right = deleteRecursion(current.right, smallest.book.id);
            }
        }
        return current;
    }

    private tNode findSmallest(tNode node)
    {
        return node.left == null ? node : findSmallest(node.left);
    }
}