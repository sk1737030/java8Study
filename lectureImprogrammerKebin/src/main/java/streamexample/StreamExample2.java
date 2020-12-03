package streamexample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamExample2 {
    public static void main(String[] args) {
        /*Stream.of(1, 2, 3, 4, 5)
                .forEach(i -> System.out.println(i + " "));*/

        // 10보다가장 가까이큰 수 구하기
        Integer result = null;

        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        for (final Integer number : numbers) {
            if (number > 3 && number < 9) {
                final Integer newNumber = number * 2;
                if (newNumber > 10) {
                    result = newNumber;
                    break;
                }
            }
        }
        System.out.println("Imperative Result: " + result);
        // 코드가 더러워진다

        // SOOOOOO 간단
        // 근데 성능은 과연어떨까?
        // 둘이 비슷하다 엄청 똑똑하게 함
        System.out.println(numbers.stream()
                .filter(number -> number > 3)
                .filter(number -> number < 9)
                .map(number -> number * 2)
                .filter(number -> number > 10)
                .findFirst()); // Optional Type이다
/*

        final List<Integer> greaterThan3 = filter(numbers, i -> i > 3);
        final List<Integer> lessThan9 = filter(greaterThan3, i -> i < 9);
        final List<Integer> doubled = map(lessThan9, i -> i * 2);
        List<Integer> greaterThan10 = filter(doubled, i -> i > 10);
*/
        final List<Integer> greaterThan3 = filter(numbers, i -> i > 3);
        final List<Integer> lessThan9 = filter(greaterThan3, i -> i < 9);
        final List<Integer> doubled = map(lessThan9, i -> i * 2);
        List<Integer> greaterThan10 = filter(doubled, i -> i > 10);

        System.out.println(greaterThan10);
        System.out.println(greaterThan10.get(0));
        // 효율성이 낮다
        System.out.println(filter(map(filter(filter(numbers, i -> i > 3), i -> i < 9), i -> 2 * 2), i -> i > 10));
    }

    private static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
        final List<T> result = new ArrayList<>();
        for (final T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();
        for (final T t : list) {
            result.add(mapper.apply(t));
        }
        return result;
    }

}
