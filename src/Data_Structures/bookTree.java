package Data_Structures;
// BST containing books sorted by ID

import java.util.ArrayList;
import java.util.List;

import models.book;
import models.tNode;

public class bookTree
{
    private tNode root;
    private int counter = 0;

    public tNode getRoot()
    {
        return root;
    }
    
    public String add(book book)
    {
        if(root == null)
        {
            root = new tNode(book);
            counter = counter +1;
            System.out.println(book.getCount());
            return "Book with name: " + book.getName() + ", and ID: " + book.getId() + " was added succefully.";
        }
        
        return addRecursion(root, book);
    }

    private String addRecursion(tNode current, book book)
    {
        if(book.getId() == current.getBook().getId())
        {
            
            return book.getCount() + " Books with name: " + book.getName() + ", and ID: " + book.getId() + " were added succefully." ;
        }
        
        else if(book.getId() < current.getBook().getId())
        {
            if(current.getLeft() == null)
            {
                current.setLeft(new tNode(book));
                return "Book with name: " + book.getName() + ",and ID: " + book.getId() + " was added succefully.";
            }

            return addRecursion(current.getLeft(), book);
        }

        else
        {
            if(current.getRight() == null)
            {
                current.setRight(new tNode(book));
                book.setIsAvailable(true);
                return "Book with name: " + book.getName() + ", and ID: " + book.getId() + " was added succefully.";
            }

            return addRecursion(current.getRight(), book);
        }
    }

    public book search(int id)
    {
        return searchRecursion(root, id);
    }

    private book searchRecursion(tNode current, int id)
    {
        if(current == null)
        {
            return null;
        }
        
        if(current.getBook().getId() == id)
        {
            return current.getBook();
        }

        if(current.getBook().getId() > id)
        {
            return searchRecursion(current.getLeft(), id);
        }

        return searchRecursion(current.getRight(), id);
    }

    public book[] showBooks(tNode current)
    {
        List<book> bookList = new ArrayList<>();
        traverseBooks(current, bookList);
        return bookList.toArray(new book[0]);
    }

    private void traverseBooks(tNode current, List<book> bookList)
    {
        if (current == null) return;

        traverseBooks(current.getLeft(), bookList);
        bookList.add(current.getBook());
        traverseBooks(current.getRight(), bookList);
    }


    public void showSameAuthor(tNode current, String author)
    {
        if(current != null)
        {
            showSameAuthor(current.getLeft(), author);

            if (author.equalsIgnoreCase(current.getBook().getAuthor()))
            {
                System.out.println("Book name: " + current.getBook().getName() + ", ID: " + current.getBook().getId() + ", Author: " + current.getBook().getAuthor());
            }

            showSameAuthor(current.getRight(), author);
        }
    }

    public boolean deleteCount(book b, int num)
    {
        if (b.getCount() < num)
        {
            return false;
        }

        book found = search(b.getId());

        if (found == null)
        {
            return false;
        }

        b.setCount(b.getCount() - num);
        return true;
    }

    public boolean delete(book b, int num)
    {
        if (b.getCount() < num)
        {
            return false;
        }

        book found = search(b.getId());

        if (found == null)
        {
            return false;
        }

        root = deleteRecursion(root, b.getId());
        b.setCount(b.getCount() - num);
        return true;
    }

    private tNode deleteRecursion(tNode current, int id)
    {
        if (current == null)
        {
            return null;
        }

        if (id < current.getBook().getId())
        {
            current.setLeft(deleteRecursion(current.getLeft(), id));
        }
        
        else if (id > current.getBook().getId())
        {
            current.setRight(deleteRecursion(current.getRight(), id));
        }
        
        else
        {
            if (current.getLeft() == null && current.getRight() == null)
            {
                return null;
            }
            
            else if (current.getLeft() == null)
            {
                return current.getRight();
            }
            
            else if (current.getRight() == null)
            {
                return current.getLeft();
            }
            
            else
            {
                tNode smallest = findSmallest(current.getRight());
                current.setBook(smallest.getBook());
                current.setRight(deleteRecursion(current.getRight(), smallest.getBook().getId()));
            }
        }
        return current;
    }

    private tNode findSmallest(tNode node)
    {
        return node.getLeft() == null ? node : findSmallest(node.getLeft());
    }
}