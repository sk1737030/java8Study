package streamexample;


import java.math.BigInteger;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {
        IntStream.range(0,10).forEach(i -> System.out.print(i + " "));

        IntStream.iterate(1, i -> i + 1).forEach(i -> System.out.println(i + " "));



        // seed가 첫 값이다  map과 비슷
        Stream.iterate(BigInteger.ONE, i -> i.add(BigInteger.ONE)).forEach(i -> System.out.println( i +  ""));
    }
}
