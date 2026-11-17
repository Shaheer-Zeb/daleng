package datastructures.linear;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author ShaheerZK
 */
public class LinkedList<T> implements Iterable<T>
{
    private int size;
    
    private Node<T> head;
    private Node<T> tail;

    private class LinkedListIterator implements Iterator<T>
    {
        Node node = head;
        @Override
        public boolean hasNext() 
        {
            return node != null;
        }
        @Override
        public T next() 
        {
            if (!hasNext())
                throw new NoSuchElementException();
            T obj = (T)node.obj;
            node = node.next;
            return obj;
        }
    }
    @Override
    public Iterator<T> iterator() 
    {
        return new LinkedListIterator();
    }
    private class Node<T>
    {
        T obj;
        Node<T> previous;
        Node<T> next;
        Node(T obj, Node previous, Node next)
        {
            this.obj = obj;
            this.previous = previous;
            this.next = next;
        }
        Node(T obj)
        {
            this(obj, null, null);
        }
    }
    /**
     * Adding an element to the end of the list.
     * @param obj 
     */
    public void add(T obj)
    {
        Node<T> node;
        if (head == null || tail == null)
        {
            node = new Node(obj);
            head = node;
        }
        else
        {
            node = new Node(obj);
            tail.next = node;
            node.previous = tail;
        }
        tail = node;            
        size ++;
    }
    /**
     * Adds the object at the beginning of the list.
     * @param obj 
     */
    public void addFirst(T obj)
    {
        if (head == null)
        {
            add(obj);
            return;
        }
        Node node = new Node(obj);
        head.previous = node;
        node.next = head;
        head = node;
        size++;
    }
    /**
     * Literally the same thing as add(), don't know why you'd use addLast() instead of add().
     * @param obj 
     */
    public void addLast(T obj)
    {
        add(obj);
    }
    public void clear()
    {
        for (Node node = head; node != null; node = node.next)
        {
            node.obj = null;
            node.previous = null;
        }
        head = null;
        tail = null;
        size = 0;
    }
    /**
     * Returns whether the LinkedList contains the provided object.
     * @param obj
     * @return 
     */
    public boolean contains(T obj)
    {
        for (Node node = head; node != null; node = node.next)
        {
            if (obj != null && node.obj.equals(obj))
                return true;
            else if (obj == null && node.obj == null)
                return true;
        }
        return false;
    }
    /**
     * Returns the object at the specified index, and null if the index isn't valid or the object isn't found.
     * @param index
     * @return 
     */
    public T get(int index)
    {
        if (index < 0)
            return null;
        Node node = head;
        for (int i = 0; node != null; node = node.next, i++)
        {
            if (i == index)
                return (T)node.obj;
        }
        return null;
    }
    /**
     * Are you an idiot? It of course returns the first element of the LinkedList.
     * @return 
     */
    public T getFirstElement()
    {
        return head.obj;
    }
    /**
     * As the name says, it returns the last element of the LinkedList.
     * @return 
     */
    public T getLastElement()
    {
        return tail.obj;
    }
    /**
     * Returns the first index of the first matching specified object in the LinkedList, if not found, it returns -1.
     * @param obj
     * @return 
     */
    public int indexOf(T obj)
    {
        Node node = head;
        for (int i = 0; node != null; node = node.next, i++)
        {
            if (obj != null && node.obj.equals(obj))
                return i;
            else if (obj == null && node.obj == null)
                return i;
        }
        return -1;
    }
    /**
     * Returns the last index of the first matching specified object in the LinkedList, if not found, it returns -1.
     * @param obj
     * @return 
     */
    public int lastIndexOf(T obj)
    {
        Node node = tail;
        for (int i = size - 1; node != null; node = node.previous, i--)
        {
            if (obj != null && node.obj.equals(obj))
                return i;
            else if (obj == null && node.obj == null)
                return i;
        }
        return -1;
    }
    /**
     * If the index is valid, replaces the element at the specified index in the LinkedList.
     * @param index
     * @param obj 
     */
    public void set(int index, T obj)
    {
        if (index < 0)
        {
            System.out.println("The index to be set can't be negative and has to be less than the size.");
            return;
        }
        Node node = head;
        for (int i = 0; node != null; node = node.next, i++)
        {
            if (i == index)
            {
                node.obj = obj;
                break;
            }
        }
    }
    /**
     * If the index is valid, returns and removes the object stored at the specified index
     * @param index
     * @return 
     */
    public T remove(int index)
    {
        if (index == 0)
        {
            return removeFirst();
        }
        if (index == size - 1)
        {
            return removeLast();
        }
        
        if (index < 0 || index > size - 1)
        {
            try 
            {
                throw new Exception("Invalid index for removal.");
            } 
            catch (Exception ex) 
            {
                System.getLogger(LinkedList.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        int i = 0;
        for (Node node = head; node != null; node = node.next, i++)
        {
            if (i == index)
            {
                deleteHelper(node);
                size--;
                return (T)node.obj;
            }
        }
        return null;
    }
    private void deleteHelper(Node node)
    {
        node.previous.next = node.next;
        node.next.previous = node.previous;
    }
    /**
     * Removes and returns true if it found the specified object, if the object isn't found or if it's null, it'd return false.
     * @param obj
     * @return 
     */
    public boolean remove(T obj)
    {
        if (obj == null)
            return false;
        for (Node node = head; node != null; node = node.next)
        {
            if (node == head && node.obj.equals(obj))
            {
                head.obj = null;

                head = head.next;
                head.previous = null;

                size--;
                return true;
            }
            else if (node == tail && node.obj.equals(obj))
            {
                tail.obj = null;

                tail = tail.previous;
                tail.next = null;

                size--;
                return true;
            }
            else if (node.obj.equals(obj))
            {
                node.obj = null;
                node.previous.next = node.next;
                node.next.previous = node.previous;

                size--;
                return true;
            }
        }
        return false;
    }
    /**
     * Removes and returns the first element from the list.
     * @return 
     */
    public T removeFirst()
    {
        if (head == null)
            return null;
        Node temp = head;
        head = head.next;
        if (head != null)
            head.previous = null;
        size--;
        return (T)temp.obj;
    }
    /**
     * Removes and returns the last element on the list.
     * @return 
     */
    public T removeLast()
    {
        Node temp = tail;
        tail = tail.previous;
        if (tail != null)
            tail.next = null;
        size--;
        return (T)temp.obj;
    }
    /**
     * Returns the size of the LinkedList.
     * @return 
     */
    public int size()
    {
        return size;
    }
    /**
     * Returns an array of the same type as the LinkedList with all the objects in the LinkedList.
     * @return 
     */
    public T[] toArray()
    {
        T[] arr = (T[]) new Object[size];
        Node node = head;
        for (int i = 0; node != null; i++, node = node.next)
        {
            arr[i] = (T)node.obj;
        }
        return arr;
    }
}