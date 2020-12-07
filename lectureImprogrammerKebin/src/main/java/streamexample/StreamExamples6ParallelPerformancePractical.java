package streamexample;

import functionalexample2.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamExamples6ParallelPerformancePractical {
    private static final String[] priceStrings = {"1.0", "100.99", "35.75", "21.30", "88.00"};
    private static final BigDecimal[] targetPrices = {new BigDecimal("30"), new BigDecimal("20"), new BigDecimal("31")};
    private static final Random random = new Random(123);
    private static final Random targetPriceRandom = new Random(111);

    private static final List<Product> products;

    static {
        final int length = 8_000_000;
        // 초기에 10개의 용량이 생성된다.
        // 그러니 미리 좀 넣어준다.
//        final List<Product> list = new ArrayList<>();
//        final List<Product> list = new ArrayList<>(10000);

        final Product[] list = new Product[length];

        /*for (int i = 1; i <= length; i++) {
            list.add(new Product((long) i, "Product" + i, new BigDecimal(priceStrings[random.nextInt(5)])));
        }*/
        for (int i = 1; i <= length; i++) {
            list[i - 1] = new Product((long) i, "Product" + i, new BigDecimal(priceStrings[random.nextInt(5)]));
        }
        //
        // products = Collections.unmodifiableList(list);
        products = Arrays.asList(list);
        // 속도면에서 미리 배열로만들고 asList로 만드는게 속도면에서 더빠르다.
    }


    private static BigDecimal imperativeSum(final List<Product> products, final Predicate<Product> predicate) {
        BigDecimal sum = BigDecimal.ZERO;
        for (final Product product : products) {
            if (predicate.test(product)) {
                sum = sum.add(product.getPrice());
            }
        }
        return sum;
    }

    private static BigDecimal streamSum(final Stream<Product> stream, final Predicate<Product> predicate) {
        return stream.filter(predicate).map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static void imperativeTest(BigDecimal targetPrice) {
        System.out.println("=====================================================");
        System.out.println("\nImperative Sum\n");
        final long start = System.currentTimeMillis();
        System.out.println("Sum : " +
                imperativeSum(products, product -> product.getPrice().compareTo(targetPrice) >= 0));
        System.out.println("it took " + (System.currentTimeMillis() - start) + " ms");
        System.out.println("=====================================================");
    }

    private static void streamTest(BigDecimal targetPrice) {
        System.out.println("=====================================================");
        final long start = System.currentTimeMillis();
        System.out.println("\n Stream Sum\n" +
                streamSum(products.stream(), product -> product.getPrice().compareTo(targetPrice) >= 0));
        System.out.println("it took " + (System.currentTimeMillis() - start) + " ms");
        System.out.println("=====================================================");
    }

    private static void parallelStreamTest(BigDecimal targetPrice) {
        System.out.println("=====================================================");
        final long start = System.currentTimeMillis();
        System.out.println("\n parallel Sum\n" +
                streamSum(products.parallelStream(), product -> product.getPrice().compareTo(targetPrice) >= 0));
        System.out.println("it took " + (System.currentTimeMillis() - start) + " ms");
        System.out.println("=====================================================");

    }

    public static void main(String[] args) {
        BigDecimal targetPrice = new BigDecimal("30");
        imperativeTest(targetPrice);
        streamTest(targetPrice);
        parallelStreamTest(targetPrice);

        System.out.println("\nIgnore Tests Above because of jvm prepare somethings\n ==============================");
        System.out.println("Start!");
        for (int i = 0; i < 5; i++) {
            BigDecimal price = targetPrices[targetPriceRandom.nextInt(3)];
            imperativeTest(price);
            streamTest(price);
            parallelStreamTest(price);

        }
    }
}
