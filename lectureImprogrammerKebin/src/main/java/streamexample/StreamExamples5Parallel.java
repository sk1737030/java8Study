package streamexample;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class StreamExamples5Parallel {
    public static void main(String[] args) {
        final int[] sum = {0};

        IntStream.range(0, 100)

                .forEach(i -> sum[0] += i);

        System.out.println("stream sum side-effect : " + sum[0]);

        final int[] sum2 = {0};


        IntStream.range(0, 100)
                .parallel()
                .forEach(i -> sum2[0] += i);
        System.out.println("parallel sum with side-effect : " + sum2[0]);

        System.out.println("Stream sum without side-effect : " +
                IntStream.range(0, 100)
                        .sum());

        System.out.println("Parallel sum without side-effect : " +
                IntStream.range(0, 100)
                        .parallel()
                        .sum());

        System.out.println("\n==========================================");

        final long start = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .stream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));
        System.out.println(System.currentTimeMillis() - start);

        System.out.println("\n==========================================");
        System.out.println("Parallel Stream");
        // cpu core 개수를 결정하는 옵션
        // 0은 singleCore 1 dualCore
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "7");
        // 실행 할 때 -Djava.util.concurrent.ForkJoinPool.common.parallelism=8 이런식으로 줄 수 있다.
        // parallel stream은 순서 보장은 안함
        // 이미 Sort가 된것은 느릴 수 있다.
        // lazy Fetch 할 때 (hibernate) 쓰면안됨.
        final long start2 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));
        ;
        System.out.println(System.currentTimeMillis() - start2);
    }
}
