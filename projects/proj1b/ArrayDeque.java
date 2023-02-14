public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;
    private int capacity;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 7;
        nextLast = 0;
        size = 0;
        capacity = 8;
    }
    public ArrayDeque(ArrayDeque<T> other) {
        items = (T[]) new Object[other.capacity];
        System.arraycopy(other.items, 0, items, 0, other.capacity);
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;
        capacity = other.capacity;
    }
    private int indexCorrection(int index) {
        if (index < 0) {
            index += capacity;
        }
        if (index >= capacity) {
            index -= capacity;
        }
        return index;
    }
    private void resize() {
        if (size == capacity) {
            capacity *= 2;
            T[] newItems = (T[]) new Object[capacity];
            for (int i = 0; i < size; i++) {
                newItems[i] = items[indexCorrection(nextFirst + 1 + i)];
            }
            items = newItems;
            nextFirst = capacity - 1;
            nextLast = size;
        }
        if (size * 4 < capacity && capacity >= 16) {
            capacity /= 2;
            T[] newItems = (T[]) new Object[capacity];
            for (int i = 0; i < size; i++) {
                newItems[i] = items[indexCorrection(nextFirst + 1 + i)];
            }
            items = newItems;
            nextFirst = capacity - 1;
            nextLast = size;
        }
    }
    @Override
    public void addFirst(T item) {
        items[nextFirst] = item;
        nextFirst = indexCorrection(nextFirst - 1);
        size++;
        resize();
    }
    @Override
    public void addLast(T item) {
        items[nextLast] = item;
        nextLast = indexCorrection(nextLast + 1);
        size++;
        resize();
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[indexCorrection(nextFirst + 1 + i)]);
            System.out.print(" ");
        }
        System.out.println();
    }
    @Override
    public T removeFirst() {
        T victim = items[indexCorrection(nextFirst + 1)];
        items[indexCorrection(nextFirst + 1)] = null;
        nextFirst = indexCorrection(nextFirst + 1);
        size--;
        resize();
        return victim;
    }
    @Override
    public T removeLast() {
        T victim = items[indexCorrection(nextLast - 1)];
        items[indexCorrection(nextLast - 1)] = null;
        nextLast = indexCorrection(nextLast - 1);
        size--;
        resize();
        return victim;
    }
    @Override
    public T get(int index) {
        return items[nextFirst + 1 + index];
    }
}
