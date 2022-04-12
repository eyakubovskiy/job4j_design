package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void containsCorrectlyFindElementWhenElementAdded() {
        Set<Integer> set = new SimpleSet<>();
        set.add(123);
        assertTrue(set.contains(123));
    }

    @Test
    public void containsCorrectlyDoesNotFindElementWhenElementNotAdded() {
        Set<Integer> set = new SimpleSet<>();
        set.add(321);
        assertFalse(set.contains(123));
    }

    @Test
    public void returnCorrectIterator() {
        Set<Integer> set = new SimpleSet<>();
        set.add(123);
        var iterator = set.iterator();
        assertTrue(iterator.hasNext());
        assertTrue(iterator.next() == 123);
        assertFalse(iterator.hasNext());
    }

}