package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntPredicate;

import java.util.*;

public class FilterIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private IntPredicate predicate;
    private int len;
    private Integer val;

    public FilterIterator(Iterator<Integer> i, IntPredicate p, int len) {
        this.iterator = i;
        this.predicate = p;
        this.len = len;
    }

    private void isEmpty() {
        if (len == 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean hasNext() {
        isEmpty();
        while (iterator.hasNext()) {
            val = iterator.next();
            if (predicate.test(val)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (val == null) {
            throw new NoSuchElementException();
        }
//        len++;
        return val;
    }

    public int getLen() {
        return len;
    }
}
