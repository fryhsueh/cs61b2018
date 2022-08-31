package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (fillCount == capacity) {
            throw new RuntimeException("Ring buffer overflow");
        }

        rb[last] = x;
        last = indexAddOne(last);
        fillCount += 1;
    }

    private int indexAddOne(int index) {
        int out = index + 1;
        return out == capacity ? 0 : out;
    }

    private int indexMinusOne(int index) {
        int out = index - 1;
        return out == -1 ? (capacity - 1) : out; 
    }

 
    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update 
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }

        T item = rb[first];
        rb[first] = null;

        first = indexAddOne(first);
        fillCount -= 1;

        return item;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (isEmpty()) {
            return null;
        }
        return rb[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return new ArrayRingIterator();
    }

    private class ArrayRingIterator implements Iterator<T> {
        private int index;
        private int IteratorCount;

        public ArrayRingIterator() {
            index = first;
            IteratorCount = 0;
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub

            return IteratorCount < capacity;
        }

        @Override
        public T next() {
            // TODO Auto-generated method stub
            T item = rb[index];
            index = indexAddOne(index);
            IteratorCount += 1;
            return item;
        }

    }
}
