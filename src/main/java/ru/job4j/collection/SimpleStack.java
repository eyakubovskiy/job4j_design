package ru.job4j.collection;

import java.util.Iterator;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        Iterator it = linked.iterator();
        T val = (T) it.next();
        linked.deleteFirst();
        return val;
    }

    public void push(T value) {
        linked.addFirst(value);
    }
}
