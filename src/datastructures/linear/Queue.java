package datastructures.linear;

/**
 * @author ShaheerZK
 */
public class Queue<T>
{
    // I'll let this ArrayList implementation of Queue here.
//    private static final int DEFAULTSIZE = 100;
//    private final int TOP = 0;
//    private int capacity;
//    private ArrayList<T> arrayList;
//    
//    /**
//     * Takes the initial capacity for the Queue.
//     * @param capacity 
//     */
//    public Queue(int capacity)
//    {
//        if (capacity > 0)
//            this.capacity = capacity;
//        else
//        {
//            System.out.println("The capacity has to be positive. Default capacity of " + DEFAULTSIZE + " is assigned.");
//            this.capacity = DEFAULTSIZE;
//        }
//        arrayList = new ArrayList<>(this.capacity);
//    }
//    /**
//     * Assigns the default initial capacity of DEFAULTCAPACITY for the Queue.
//     */
//    public Queue()
//    {
//        this(DEFAULTSIZE);
//    }
//    /**
//     * Add the object to the end of the queue.
//     * @param obj 
//     */
//    public void enqueue(T obj)
//    {
//        arrayList.add(obj);
//    }
//    /**
//     * Removes and returns the element at the start of the queue
//     * @return 
//     */
//    public T dequeue()
//    {
//        return arrayList.remove(TOP);
//    }
//    /**
//     * Merely returns the element at the start of the queue.
//     * @return 
//     */
//    public T peek()
//    {
//        return arrayList.get(TOP);
//    }
//    /**
//     * Returns the size (current number of elements) of the queue.
//     * @return 
//     */
//    public int getSize()
//    {
//        return arrayList.size();
//    }
//    /**
//     * Returns whether the queue is empty.
//     * @return 
//     */
//    public boolean isEmpty()
//    {
//        return arrayList.isEmpty();
//    }
//    /**
//     * Returns whether the queue contains the specified object.
//     * @param obj
//     * @return 
//     */
//    public boolean contains(T obj)
//    {
//        return arrayList.contains(obj);
//    }
//    /**
//     * Clears the queue.
//     */
//    public void clear()
//    {
//        arrayList.clear();
//    }
    private LinkedList<T> list = new LinkedList<>();
    /**
     * Adds to the last of the queue.
     * @param obj 
     */
    public void enqueue(T obj)
    {
        list.add(obj);
    }
    /**
     * Returns the first element in the queue.
     * @return 
     */
    public T dequeue()
    {
        return list.removeFirst();
    }
    /**
     * Returns the size of the queue.
     * @return 
     */
    public int getSize()
    {
        return list.size();
    }
    /**
     * Returns whether the element exists in the queue or not.
     * @param obj
     * @return 
     */
    public boolean contains(T obj)
    {
        return list.contains(obj);
    }
    /**
     * Clears the whole queue.
     */
    public void clear()
    {
        list.clear();
    }
    /**
     * Returns whether the list is empty or not.
     * @return 
     */
    public boolean isEmpty()
    {
        return list.size() == 0;
    }
    /**
     * Merely returns the first element in the queue.
     * @return 
     */
    public T peek()
    {
        return list.getFirstElement();
    }
}