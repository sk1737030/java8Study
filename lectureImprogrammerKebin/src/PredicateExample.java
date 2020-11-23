import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String[] args) {
        /*
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 0;
            }
        }
        */

        Predicate<Integer> positivePredicate = integer -> integer > 0;

        System.out.println(positivePredicate.test(1));
        System.out.println(positivePredicate.test(0));
        System.out.println(positivePredicate.test(-1));

        List<Integer> numbers = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);

        List<Integer> positiveNumbers = new ArrayList<>();

        for (Integer num : numbers) {
            if (positivePredicate.test(num)) {
                positiveNumbers.add(num);
            }
        }

        System.out.println("positive integers" + positiveNumbers);

        Predicate<Integer> lessThan3 = integer -> integer < 3;
        List<Integer> numbersLessThan3 = new ArrayList<>();

        for (Integer num : numbers) {
            if (positivePredicate.test(num)) {
                positiveNumbers.add(num);
            }
        }
        System.out.println("less Than 3" + positiveNumbers);

        // boilerplate(상용구) 코드가 많아짐


        System.out.println("positive Numbers" + filter(numbers, positivePredicate));
        System.out.println("less Than 3" + filter(numbers, lessThan3));


    }

    private static <T> List<T> filter(List<T> list, Predicate<T> filter) {
        List<T> result = new ArrayList<>();
        for (T input : list) {
            if (filter.test(input)) {
                result.add(input);
            }
        }

        return result;
    }
}
