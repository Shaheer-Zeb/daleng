package datastructures.linear;

/**
 * A generic HashSet class.
 * @author ShaheerZK
 */
public class HashSet<T>
{
    private final Object DUMMY = new Object();
    private final HashMap<T, Object> map = new HashMap<>();
    
    /**
     * Returns whether the set contains the provided object / value.
     * @param obj
     * @return 
     */
    public boolean contains(T obj)
    {
        return map.containsKey(obj);
    }
    /**
     * Adds the provided object / value to the set.
     * @param obj 
     */
    public void add(T obj)
    {
        map.put(obj, DUMMY);
    }
    /**
     * Returns the current size of the map.
     * @return 
     */
    public int size()
    {
        return map.size();
    }
    /**
     * Returns whether the set is empty or not.
     * @return 
     */
    public boolean isEmpty()
    {
        return map.isEmpty();
    }
    /**
     * Removes the provided object from the HashSet.
     * @param obj 
     */
    public void remove(T obj)
    {
        map.remove(obj);
    }
    /**
     * Clears the set.
     */
    public void clear()
    {
        map.clear();
    }
}
