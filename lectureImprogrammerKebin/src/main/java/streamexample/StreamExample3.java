package streamexample;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamExample3 {
    public static void main(String[] args) {

        List<String> listCollect = Stream.of(1, 3, 3, 6, 6)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(toList());
        System.out.println("collect(toLisT()):" + listCollect);

        Set<String> setCollect = Stream.of(1, 3, 3, 6, 6)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(toSet());
        System.out.println("collect(toSet()):" + setCollect);

        // delimiter
        String joinCollect = Stream.of(1, 3, 3, 6, 6)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(joining(", "));
        System.out.println("collect(toJoing(\", \")):" + joinCollect);

        // delimiter
        String joinCollect2 = Stream.of(1, 3, 3, 6, 6)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .distinct() // 중복제거
                .collect(joining(", ", "[", "]"));
        System.out.println("collect(toJoing(\", [,\" ])):" + joinCollect2);


        System.out.println(
                Stream.of(1, 2, 3, 4, 5)
                        .filter(i -> i == 3)
                        .findFirst()
        );

        // return 127
        final Integer integer127 = 127;
        System.out.println(
                Stream.of(1, 2, 3, 4, 127)
                        .filter(i -> i == integer127)
                        .findFirst()
        );

        // return empty
        // Object == Object 비교는 메모리의 값으로 비교를 한다.
        final Integer integer128 = 128;
        System.out.println(
                Stream.of(1, 2, 3, 4, 128)
                        .filter(i -> i == integer128)
                        .findFirst()
        );

        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Iterator 또는 Array가 numbers에 들어간다.
        // 향상된 for문은 External Iterator
        for (Integer i : numbers) {
            System.out.print(i);
        }

        // Stream은 Internal Iterator
        Stream.of(1, 2, 3, 4, 5)
                .forEach(i -> System.out.print(i));


    }
}
