package ua.edu.ucu.stream;

import java.util.*;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterators.*;

public class AsIntStream implements IntStream {

    public Iterator<Integer> stream = null;
    private int size = 0;

    public AsIntStream(Iterator<Integer> l, int size) {
        this.stream = l;
        this.size = size;
    }

    public AsIntStream(int[] l) {
        ArrayList<Integer> al = new ArrayList<>();
        for (int x : l) {
            al.add(x);

        }
        this.stream = al.iterator();
        this.size = al.size();

    }

    public static AsIntStream of(int... values) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            list.add(values[i]);
        }
        Iterator<Integer> iter = list.iterator();
        return new AsIntStream(iter, values.length);
    }

    @Override
    public Double average() {
        Integer sum = sum();
        return (double) sum / this.size;
    }

    @Override
    public Integer max() {
        return reduce(stream.next(), (Math::max));
    }

    @Override
    public Integer min() {
        return reduce(stream.next(), (Math::min));
    }

    @Override
    public long count() {
        return this.size;
    }

    @Override
    public Integer sum() {
        return reduce(0, (sum, x) -> sum += x);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        FilterIterator j = new FilterIterator(stream, predicate, size);
        return new AsIntStream(j, j.getLen());
    }

    @Override
    public void forEach(IntConsumer action) {
        while (stream.hasNext()) {
            action.accept(stream.next());
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        MapIterartor j = new MapIterartor(stream, mapper, size);
        return new AsIntStream(j, j.getLen());
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        if (stream == null){
            throw new IllegalArgumentException();
        }
        FlatMapIterator n = new FlatMapIterator(stream, func, size);
        return new AsIntStream(n, size);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int result = identity;
        Iterator<Integer> it = this.stream;
        while (it.hasNext()) {
            int element = it.next();
            result = op.apply(result, element);
        }
        return result;
    }

    private int getStreamLength() {
        int len = 0;
        while (stream.hasNext()) {
            stream.next();
            len++;
        }
        return len;
    }

    @Override
    public int[] toArray() {
        ArrayList<Integer> list = new ArrayList<>();
        Iterator<Integer> cop = stream;
        while (cop.hasNext()) {
            list.add(cop.next());
        }

        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}

