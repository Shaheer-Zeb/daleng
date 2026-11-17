package datastructures.heap;

import datastructures.linear.ArrayList;

/**
 * The array implementation of the binary heap. The (i - 1) index is the parent, (i * 2) + 1 is the left child and (i * 2) + 2 is the right child. And (i - 1) / 2 gives the parent.
 * @author ShaheerZK
 * @param <T>
 */
public abstract class Heap<T extends Comparable<? super T>>
{
    protected ArrayList<T> array = new ArrayList<>();
    
    /**
     * Inserts the element in the Heap, then siftsUp the array depending on whether the implementation is MinHeap or the MaxHeap.
     * @param t 
     */
    public void insert(T t)
    {
        array.add(t);
        siftUp(array.size()- 1);
    }
    /**
     * Returns the top of the array essentially. In MinHeap, it'd be the smallest element and in MaxHeap, it'd be the largest element. Returns null if the heap is empty.
     * @return 
     */
    public T getTop()
    {
        if (array.isEmpty())
        {
            System.out.println("Heap is empty.");
            return null;
        }
        return array.get(0);
    }
    /**
     * Removes and returns the top of the heap and then siftsDown the heap. Again, in MinHeap, it'd be the smallest and in MaxHeap, it'd be the largest element.
     * @return 
     */
    public T extractTop()
    {
        if (array.isEmpty())
        {
            System.out.println("Heap is empty.");
            return null;
        }
        T element = array.get(0);
        T lastElement = array.remove(array.size() - 1);
        
        if (!array.isEmpty())
        {
            array.replace(0, lastElement);
            siftDown(0);
        }
        return element;
    }
    /**
     * Returns the sorted array list depending on the type of heap, if called on the MinHeap implementation, it sorts in ascending, and if called on MaxHeap, it sorts in descending.
     * @return 
     */
    public ArrayList<T> heapSort()
    {
        ArrayList<T> list = new ArrayList<>();
        while (!isEmpty())
            list.add(extractTop());
        return list;
    }
    /**
     * Sifts all the way up the heap depending on the heap type so that the heap property isn't violated. It's called by the insert method.
     * @param i The index of the element which we'll swap with its parent to preserve the heap property..
     */
    protected abstract void siftUp(int i);
    /**
     * Sifts down from the first element, maintaining the heap property. It's called by the extractTop method.
     * @param i 
     */
    protected abstract void siftDown(int i);
    /**
     * Returns the index of the parent.
     * @param i The child's index.
     * @return 
     */
    protected int getParent(int i)
    {
        return (i - 1) / 2;
    }
    /**
     * Returns the left child index of the given parent index.
     * @param i The parent's index.
     * @return 
     */
    protected int getLeftChild(int i)
    {
        return (i * 2) + 1;
    }
    /**
     * Returns the right child of the given parent index.
     * @param i The parent's index.
     * @return 
     */
    protected int getRightChild(int i)
    {
        return (i * 2) + 2;
    }
    /**
     * Swap the elements at index i and j.
     * @param i
     * @param j 
     */
    protected void swap(int i, int j)
    {
        T temp = array.get(i);
        array.replace(i, array.get(j));
        array.replace(j, temp);
    }
    /**
     * Returns the size of the heap.
     * @return 
     */
    public int size()
    {
        return array.size();
    }
    /**
     * Returns whether the array is empty or not. 
     * @return 
     */
    public boolean isEmpty()
    {
        return array.isEmpty();
    }
}
