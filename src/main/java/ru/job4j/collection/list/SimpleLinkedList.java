package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private Node<E> first = null;
    private Node<E> last = null;
    private int size = 0;
    private int modCount = 0;

    @Override
    public void add(E value) {
        if (first == null) {
            Node<E> newNode = new Node<>(value);
            first = newNode;
            last = newNode;
        } else {
            Node<E> newNode = new Node<>(value, last, null);
            last.nextNode = newNode;
            last = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> nextNode = first;
        for (int i = 0; i < index; i++) {
            nextNode = nextNode.nextNode;
        }
        return nextNode.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            final int createModCount = modCount;
            Node<E> current = first;

            @Override
            public boolean hasNext() {
                if (createModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E val = current.value;
                current = current.nextNode;
                return val;
            }
        };
    }

    private class Node<E> {
        private E value;
        private Node<E> previousNode;
        private Node<E> nextNode;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> previousNode, Node<E> nextNode) {
            this.value = value;
            this.previousNode = previousNode;
            this.nextNode = nextNode;
        }
    }
}