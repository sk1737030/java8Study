import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class LambdaExample {
    public static void main(String[] args) {
        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> result = new ArrayList<>();

        for (Integer number : list) {
            if (number > 2) {
                result.add(number);
            }
        }

        System.out.println(result);

        List<Integer> result2 = new ArrayList<>();

        for (Integer number : list) {
            if (number < 7) {
                result2.add(number);
            }
        }
        // 불필요한 노이즈 소스들이 많다

        System.out.println(result2);

        // 자바 8 이전
        List<Integer> result3 = filter(list, new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 2;
            }
        });

        System.out.println(result3);

        List<Integer> result4 = filter(list, new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer < 7;
            }
        });

        System.out.println(result4);

        // Lamabda 사용 .
        List<Integer> result5 = filter(list, (Integer integer) -> integer < 7);
        System.out.println(result5);


        // Lamabda and 사용 함수는 재사용이가능함.
        Predicate<Integer> lessThan7 = integer -> integer < 7;
        Predicate<Integer> biggerThan2 = integer -> integer > 2;
        List<Integer> result6 = filter(list, biggerThan2.and(lessThan7));
        System.out.println(result6);

        // java의 Closer?
        int factor = 10;
        // factor = 1;
        Comparator<Integer> comparator = (o1, o2) -> o1 > factor ? o1 : o1.compareTo(o2);
        // factor = 1;

        int[] sum = new int[1];
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
                .parallelStream()
                .forEach(i -> sum[0] = sum[0] + i);
        System.out.println(sum[0]);


        int total = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
                .parallelStream()
                .reduce(0, (i1, i2) -> i1 + i2);
        System.out.println(total);


        // 멀티코어 예제
        long start = System.currentTimeMillis();
        int total2 = Arrays.asList(1, 2, 3, 4)
                .parallelStream()
                .peek(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
                .reduce(0, (i1, i2) -> i1 + i2);
        System.out.println("it took " + (System.currentTimeMillis() - start));
        System.out.println(total2);

    }


    // 개선 전 만약 작은거면 또 새로운 함수를 만들게 된다..
    private <T> List<T> filter(List<T> list, int biggerThan) {
        final List<T> result = new ArrayList<>();
        for (T value : list) {
            if ((Integer) value < biggerThan) {
                result.add(value);
            }
        }
        return result;
    }

    // 개선 코드
    private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        final List<T> result = new ArrayList<>();
        for (T value : list) {
            if (predicate.test(value)) {
                result.add(value);
            }
        }
        return result;
    }


}

