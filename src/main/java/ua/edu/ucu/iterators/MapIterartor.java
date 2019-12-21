package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntUnaryOperator;

import java.util.*;

public class MapIterartor implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private IntUnaryOperator mapper;
    private int len;

    public MapIterartor(Iterator<Integer> i, IntUnaryOperator m, int len) {
        this.iterator = i;
        this.mapper = m;
        this.len = len;
    }

//    private void isEmpty() {
//        if (len == 0) {
//            throw new IllegalArgumentException();
//        }
//    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }


    @Override
    public Integer next() {
        return mapper.apply(iterator.next());

    }

    public int getLen() {
        return len;
    }
}
