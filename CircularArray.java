import java.util.Iterator;

public class CircularArray<T> implements Iterable<T> {

    private T[] items;
    private int head;

    public CircularArray(int size) {
        items = (T[]) new Object[size];
        head = 0;
    }

    private int convert(int index) {
        if (index < 0 || index >= items.length) {
            throw new IndexOutOfBoundsException();
        }

        return (head + index) % items.length;
    }

    public T get(int index) {
        return items[convert(index)];
    }

    public void set(int index, T value) {
        items[convert(index)] = value;
    }

    public void rotate(int shift) {
        int n = items.length;
        head = ((head - shift) % n + n) % n;
    }

    @Override
    public Iterator<T> iterator() {
        return new CircularArrayIterator();
    }

    private class CircularArrayIterator
            implements Iterator<T> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < items.length;
        }

        @Override
        public T next() {
            return get(current++);
        }
    }
}


// For interview purposes, the interviewer is usually looking for three observations:

// Use Generics (<T>).
// Make rotation O(1) by changing a head offset instead of moving elements.
// Implement Iterable<T> so it works with Java's enhanced for-each loop.