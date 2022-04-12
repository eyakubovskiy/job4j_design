package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);

        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);

        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void correctAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 1, 2);

        assertThat(input, is(Arrays.asList(1, 3, 2)));
    }

    @Test
    public void correctAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 5));
        ListUtils.addBefore(input, 2, 4);

        assertThat(input, is(Arrays.asList(1, 3, 4, 5)));
    }

    @Test
    public void correctRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 5, 7));
        ListUtils.removeIf(input, (x) -> (x < 5));

        assertThat(input, is(Arrays.asList(5, 7)));
    }

    @Test
    public void correctReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 5, 7));
        int a = 0;
        ListUtils.replaceIf(input, (x) -> (x == 5), 0);

        assertThat(input, is(Arrays.asList(1, 3, 0, 7)));
    }

    @Test
    public void correctRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 5, 7));
        List<Integer> toRemove = new ArrayList<>(Arrays.asList(3, 5));
        ListUtils.removeAll(input, toRemove);

        assertThat(input, is(Arrays.asList(1, 7)));
    }

}
