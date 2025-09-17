package org.firstinspires.ftc.teamcode;

public class RingBuffer<T> {
    final T[] buffer;
    int head = 0;
    int tail = 0;
    int size = 0;
    final int capacity;

    RingBuffer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero");
        }
        this.capacity = capacity;
        this.buffer = (T[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    public void put(T item) {
        if (isFull()) {
            throw new IllegalStateException("Buffer is full");
        }
        buffer[tail] = item;
        tail = (tail + 1) % capacity;
        size++;
    }

    public T get() {
        if (isEmpty()) {
            throw new IllegalStateException("Buffer is empty");
        }
        T item = buffer[head];
        buffer[head] = null; // optional, helps garbage collection
        head = (head + 1) % capacity;
        size--;
        return item;
    }

    public T getValue(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of range. Size=" + size);
        }
        int actualIndex = (head + index) % capacity;
        return buffer[actualIndex];
    }

    /**
     * Fill buffer completely with a single repeated value.
     */
    public void fill(T value) {
        clear();
        for (int i = 0; i < capacity; i++) {
            buffer[i] = value;
        }
        head = 0;
        tail = 0;
        size = capacity;
    }

    /**
     * Fill buffer with values from an array (up to capacity).
     */
    public void fill(T[] values) {
        clear();
        int count = Math.min(values.length, capacity);
        for (int i = 0; i < count; i++) {
            buffer[i] = values[i];
        }
        head = 0;
        tail = count % capacity;
        size = count;
    }

    /**
     * Reset buffer to empty.
     */
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            buffer[i] = null;
        }
        head = 0;
        tail = 0;
        size = 0;
    }

    /** Get the most recent value (last inserted). */
    public T getLastValue() {
        if (isEmpty()) {
            throw new IllegalStateException("Buffer is empty");
        }
        int lastIndex = (tail - 1 + capacity) % capacity;
        return buffer[lastIndex];
    }
}
