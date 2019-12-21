package ua.edu.ucu;
import ua.edu.ucu.stream.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import static org.junit.Assert.*;

public class AsIntStreamTest {

    private IntStream intStream;

    @Before
    public void init() {
        int[] arr = {-5, 2, 0, 20, 4, 8, 55};
        intStream = AsIntStream.of(arr);
    }

    @Test
    public void testOf() {
        int[] testArr = {0,1,2,3};
        IntStream testStream = AsIntStream.of(testArr);
        StringBuilder result = new StringBuilder();
        testStream.forEach(x -> result.append(x));
        assertEquals(result.toString(), "0123");
    }

    @Test
    public void testAverage() {
        assertEquals(intStream.average(), new Double(12.0));
    }

    @Test
    public void testMin() {
        assertEquals(intStream.min(), new Integer(-5));
    }

    @Test
    public void testMax() {
        assertEquals(intStream.max(), new Integer(55));
    }

    @Test
    public void testCount() {
        long size = 7;
        assertEquals(intStream.count(), size);
    }

    @Test
    public void testSum() {
        assertEquals(intStream.sum(), new Integer(84));
    }

    @Test
    public void testReduce() {
        assertEquals(intStream.reduce(0, (sum, x) -> sum += x), 84);
    }

    @Test
    public void testForEach() {
        StringBuilder builder = new StringBuilder();
        intStream.forEach(x -> builder.append(x).append(", "));
        String result = builder.toString();
        assertEquals(result, "-5, 2, 0, 20, 4, 8, 55, ");
    }

    @Test
    public void testToArray() {
        int[] expected = {-5, 2, 0, 20, 4, 8, 55};
        assertArrayEquals(intStream.toArray(), expected);
    }

    @Test
    public void testFilter() {
        int[] expected = {-5, 0, 20, 55};
        assertArrayEquals(intStream.filter(x -> x % 5 == 0).toArray(), expected);
    }

    @Test
    public void testMap() {
        int[] expected = {-10, 4, 0, 40, 8, 16, 110};
        assertArrayEquals(intStream.map(x -> x * 2).toArray(), expected);
    }

    @Test
    public void testFlatMap() {
        int[] expected = {-5, -6, 2, 1, 0, -1, 20, 19, 4, 3, 8, 7, 55, 54};
        assertArrayEquals(intStream.flatMap(x -> AsIntStream.of(x, x - 1)).toArray(), expected);
    }

}
