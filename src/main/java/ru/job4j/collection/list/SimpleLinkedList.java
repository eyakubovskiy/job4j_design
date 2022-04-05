package ru.job4j.collection.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    Node<E> first = null;
    Node<E> last = null;
    int size = 0;

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

            Node<E> current = null;

            @Override
            public boolean hasNext() {
                return (current == null && first != null)
                        || (current != null && current.nextNode != null);
            }

            @Override
            public E next() {
                if (hasNext()) {
                    if (current == null) {
                        current = first;
                    } else {
                        current = current.nextNode;
                    }
                    return current.value;
                }
                throw new NoSuchElementException();
            }
        };
    }

    class Node<E> {
        E value;
        Node<E> previousNode;
        Node<E> nextNode;

        Node(E value) {
            this.value = value;
        }

        Node(E value, Node<E> previousNode, Node<E> nextNode) {
            this.value = value;
            this.previousNode = previousNode;
            this.nextNode = nextNode;
        }
    }
}