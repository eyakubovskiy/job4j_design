package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    private int size = 0;

    public T poll() {

        if (size == 0) {
            throw new NoSuchElementException();
        }
        size--;
        move(in, out, size);
        T tmp = in.pop();
        move(out, in, size);
        return tmp;
    }

    public void push(T value) {
        in.push(value);
        size++;
    }

    private void move(SimpleStack<T> from, SimpleStack<T> to, int count) {
        T tmp;
        for (int i = 0; i < count; i++) {
            tmp = from.pop();
            to.push(tmp);
        }
    }
}