package Data_Structures;
// BST containing books sorted by id

import java.util.ArrayList;
import java.util.List;

import models.book;
import models.tNode;

public class bookTree
{
    public tNode root;
    public int counter =0;

    public String add(book book)
    {
        if(root == null)
        {
            root = new tNode(book);
            this.counter++;
            System.out.println(book.count);
            return "Book with name: " + book.name + ",and ID: " + book.id + " was added succefully.";
        }
        
        return addRecursion(root, book);
    }

    private String addRecursion(tNode current, book book)
    {
        if(book.id == current.book.id)
        {
            
            return book.count + " Books with name: " + book.name + ",and ID: " + book.id + "were added succefully." ;
        }
        
        else if(book.id < current.book.id)
        {
            if(current.left == null)
            {
                current.left = new tNode(book);
                return "Book with name: " + book.name + ",and ID: " + book.id + " was added succefully.";
            }

            return addRecursion(current.left, book);
        }

        else
        {
            if(current.right == null)
            {
                current.right = new tNode(book);
                book.isAvailble = true;
                return "Book with name: " + book.name + ", and ID: " + book.id + " was added succefully.";
            }

            return addRecursion(current.right, book);
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
        
        if(current.book.id == id)
        {
            return current.book;
        }

        if(current.book.id > id)
        {
            return searchRecursion(current.left, id);
        }

        return searchRecursion(current.right, id);
    }

    public book[] showBooks(tNode current) {
        List<book> bookList = new ArrayList<>();
        traverseBooks(current, bookList);
        return bookList.toArray(new book[0]); // convert list to array
    }

    private void traverseBooks(tNode current, List<book> bookList) {
        if (current == null) return;

        traverseBooks(current.left, bookList);
        bookList.add(current.book);
        traverseBooks(current.right, bookList);
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

    public boolean deleteCount(book b, int num)
    {
        if (b.count< num)
        {
            return false;
        }
        book found = search(b.id);
        if (found == null)
        {
            return false;
        }
        b.count -= num;
        return true;
    }

    public boolean delete(book b, int num)
    {
        if (b.count< num)
        {
            return false;
        }
        book found = search(b.id);
        if (found == null)
        {
            return false;
        }
        root = deleteRecursion(root, b.id);
        b.count -= num;
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