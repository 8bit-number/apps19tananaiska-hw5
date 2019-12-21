package ua.edu.ucu.iterators;

import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.function.IntToIntStreamFunction;
import java.util.Iterator;

public class FlatMapIterator implements Iterator<Integer> {
    private IntToIntStreamFunction func;
    private Iterator<Integer> finalIterator;
    private AsIntStream tempStream;


    public FlatMapIterator(Iterator<Integer> iter, IntToIntStreamFunction func, int len) {

        this.func = func;
        this.finalIterator = iter;
    }

    @Override
    public boolean hasNext() {

        if (this.tempStream == null || !this.tempStream.stream.hasNext()) {
            this.tempStream = null;
            return this.finalIterator.hasNext();
        }
        return true;
    }

    @Override
    public Integer next() {
        if (this.tempStream == null) {
            this.tempStream = new AsIntStream(func.applyAsIntStream(this.finalIterator.next()).toArray());
        }
        return this.tempStream.stream.next();
    }
}