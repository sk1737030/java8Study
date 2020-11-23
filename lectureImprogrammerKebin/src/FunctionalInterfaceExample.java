import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Functional
 */
public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        Function<String, Integer> toInt = new Function<String, Integer>() {
            @Override
            public Integer apply(final String s) {
                return Integer.parseInt(s);
            }
        };

        final Integer number = toInt.apply("100");
        System.out.println(number);


        // Identity
        Function<String, Integer> toInt2 = (s) -> Integer.parseInt(s);

        final Integer number2 = toInt2.apply("100");
        System.out.println(number2);

        Function<Object, Object> identity = Function.identity();
        System.out.println(identity.apply(999));

        // Consumer
        Consumer<String> printConsumer = s -> System.out.println(s);

        // Bad return type in lambda expression: void cannot be converted to R
        // Function은 무조건 입력값과 출력값이 있어야한다.
        // Function<String,void> printConsumer2 = s -> System.out.println(s);

        printConsumer.accept("Hello");

        Consumer<String> printConsumer2 = s -> System.out.println("Hellow" + s);
        printConsumer2.accept("world");
    }
}