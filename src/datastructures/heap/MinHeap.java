package datastructures.heap;

/**
 * The MinHeap, in which the smallest element would be at the very top.
 * @author ShaheerZK
 * @param <T>
 */
public class MinHeap<T extends Comparable<? super T>> extends Heap<T>
{
    @Override
    protected void siftUp(int i) 
    {
        while (i > 0)
        {
            int parentIndex = getParent(i);
            if (array.get(i).compareTo(array.get(parentIndex)) < 0)
            {
                swap(i, parentIndex);
                i = parentIndex;
            }
            else
                break;
        }
    }

    @Override
    protected void siftDown(int i) 
    {
        
        while (getLeftChild(i) < size())
        {
            int leftChild = getLeftChild(i);
            int rightChild = getRightChild(i);
            int smallerChild = leftChild;
            if (rightChild < size() && array.get(rightChild).compareTo(array.get(leftChild)) < 0)
            {
                smallerChild = rightChild;
            }
            if (array.get(i).compareTo(array.get(smallerChild)) > 0)
            {
                swap(i, smallerChild);
                i = smallerChild;
            }
            else
                break;
        }
        
    }
}
