package ua.edu.ucu.function;

import ua.edu.ucu.stream.IntStream;

import java.util.Iterator;

public interface IntToIntStreamFunction {
     IntStream applyAsIntStream(int value);
}