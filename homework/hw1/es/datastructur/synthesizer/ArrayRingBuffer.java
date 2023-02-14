package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    private int capacity;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int fillCount () {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        fillCount++;
        last++;
        if (last == capacity) {
            last = 0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T victim = rb[first];
        rb[first] = null;
        fillCount--;
        first++;
        if (first == capacity) {
            first = 0;
        }
        return victim;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        return rb[first];
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    public Iterator<T> iterator() {
        return new ArrayQueueIterator();
    }
    private class ArrayQueueIterator implements Iterator<T> {
        private int wizPos;
        public ArrayQueueIterator() {
            wizPos = first;
        }
        public boolean hasNext() {
            return wizPos != last;
        }
        public T next() {
            T returnValue = rb[wizPos];
            wizPos++;
            if (wizPos == capacity) {
                wizPos = 0;
            }
            return returnValue;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == this.getClass()) {
            ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
            if (other.fillCount() == this.fillCount()) {
                Iterator<T> thisIterator = this.iterator();
                Iterator<T> otherIterator = other.iterator();
                while (thisIterator.hasNext()) {
                    if (thisIterator.next() != otherIterator.next()) {
                        return false;
                    }
                }
                return true;
            }
            
        }
        return false;
    }
}
    // TODO: Remove all comments that say TODO when you're done.
