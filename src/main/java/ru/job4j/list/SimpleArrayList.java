package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;
    private int size;
    private int modCount;

    private int index;
    boolean thisIsTwiceNext;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (container.length == size) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T result = container[index];
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        size--;
        modCount++;
        return result;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                checkSafe();
                thisIsTwiceNext = false;
                return (index < size);
            }

            @Override
            public T next() {
                checkSafe();
                if (thisIsTwiceNext) {
                    index = 0;
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Objects.checkIndex(index, size);
                thisIsTwiceNext = true;
                return container[index++];
            }

            public void checkSafe() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}