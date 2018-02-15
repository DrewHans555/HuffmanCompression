package huffmancompression;

/**
 * This class defines a MinHeap implementation using an ArrayList (based on Heap class in Y Daniel Liang's Introduction
 * To Java Programming 10th Edition Chapter 23)
 *
 * @author Drew Hans
 * @param <E>
 */
public class MinHeap<E extends Comparable> {

    private java.util.ArrayList<E> list = new java.util.ArrayList<>();

    /**
     * Create an empty MinHeap
     */
    public MinHeap() {
    }//end MinHeap constructor

    /**
     * Create a MinHeap from an array of objects
     *
     * @param objects
     */
    public MinHeap(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            add(objects[i]);
        }
    }//end MinHeap constructor

    /**
     * Add a new object into the heap
     *
     * @param newObject
     */
    public final void add(E newObject) {
        list.add(newObject); // Append to the heap
        int currentIndex = list.size() - 1; // The index of the last node

        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            // Swap if the current object is greater than its parent
            if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0) {
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
            } else {
                break; // the heap has been balanced
            }
            currentIndex = parentIndex;
        }
    }//end add method

    /**
     * Remove the root from the heap
     *
     * @return the removedObject
     */
    public final E remove() {
        if (list.isEmpty()) {
            return null;
        }

        E removedObject = list.get(0); // get the object at top of MinHeap
        list.set(0, list.get(list.size() - 1)); // change list size
        list.remove(list.size() - 1); // remove the object from the list

        // balance the heap
        int currentIndex = 0;
        while (currentIndex < list.size()) {
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;

            // Find the maximum between two children
            if (leftChildIndex >= list.size()) {
                break; // the heap has been balanced
            }
            int maxIndex = leftChildIndex;
            if (rightChildIndex < list.size()) {
                if (list.get(maxIndex).compareTo(
                        list.get(rightChildIndex)) < 0) {
                    maxIndex = rightChildIndex;
                }
            }

            // Swap if the current node is less than the maximum
            if (list.get(currentIndex).compareTo(
                    list.get(maxIndex)) < 0) {
                E temp = list.get(maxIndex);
                list.set(maxIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = maxIndex;
            } else {
                break; // the heap has been balanced
            }
        }

        return removedObject;
    }//end remove method

    /**
     * Get the number of nodes in the tree
     *
     * @return
     */
    public int getSize() {
        return list.size();
    }//end getSize method

}//end MinHeap class
