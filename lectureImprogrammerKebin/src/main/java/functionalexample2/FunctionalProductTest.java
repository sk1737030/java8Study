package functionalexample2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalProductTest {
    public static void main(String[] args) {
        Product product1 = new Product(1L, "A", new BigDecimal("10.00"));
        Product product2 = new Product(2L, "B", new BigDecimal("55.50"));
        Product product3 = new Product(3L, "C", new BigDecimal("17.40"));
        Product product4 = new Product(4L, "D", new BigDecimal("25.25"));
        Product product5 = new Product(5L, "E", new BigDecimal("777.11"));
        final List<Product> products = Arrays.asList(
                product1,
                product2,
                product3,
                product4,
                product5
        );


        final BigDecimal twenty = new BigDecimal("20");
        List<Product> result = new ArrayList<>();
        for (final Product product : products) {
            if (product.getPrice().compareTo(new BigDecimal("20")) >= 0) {
                result.add(product);
            }
        }

        // functional 사용 소스가 간결해진다. 위에 소스가가 값만 다른 소스가 기하급수적으로 늘어나야함
        System.out.println("product >= $20:  " + filter(products, product -> product.getPrice().compareTo(twenty) > 0));
        System.out.println("product >= $20:  " + filter(products, product -> product.getPrice().compareTo(new BigDecimal("10")) <= 0));

        // 비싼 제품 할인
        List<Product> expensiveProducts = filter(products, product -> product.getPrice().compareTo(new BigDecimal("50")) > 0);

        // 구 방식
        final List<DisCountedProduct> disCountedProducts = new ArrayList<>();

        for (final Product product : expensiveProducts) {
            disCountedProducts.add(new DisCountedProduct(product.getId(), product.getName(), product.getPrice()));
        }

        System.out.println("expensive products: " + expensiveProducts);
        System.out.println("disCounted products: " + disCountedProducts);
        System.out.println(result);

        // Functional 사용
        final List<Product> fDiscountedProducts = map(expensiveProducts, product -> new DisCountedProduct(product.getId(), product.getName(),
                product.getPrice().multiply(new BigDecimal("0.5"))));
        System.out.println("expensive products: " + expensiveProducts);
        System.out.println("FdisCounted products: " + fDiscountedProducts);


        Predicate<Product> lessThanOrEqualTo30 = product -> product.getPrice().compareTo(new BigDecimal("30")) <= 0;

        System.out.println("DiscountedProducts under 30$");
        System.out.println(filter(disCountedProducts, lessThanOrEqualTo30)); // Discounted Product -> Product
        System.out.println(filter(products, lessThanOrEqualTo30));


        List<BigDecimal> prices = map(products, product -> product.getPrice());
        BigDecimal total = BigDecimal.ZERO;
        for (final BigDecimal price : prices) {
            total = total.add(price); // BigDecimal add는 return 함
        }
        System.out.println("total: " + total);

        // 어떤 타입이라도 더할 수 있dma.
        final BigDecimal newTotal = total(products, product -> product.getPrice());
        System.out.println("total: " + newTotal);

        final BigDecimal disCountedTotal = total(disCountedProducts, product -> product.getPrice());
        System.out.println("total" + disCountedTotal);

        Order order = new Order(1L, "on-1234", Arrays.asList(
                new OrderedItem(1L, product1, 2),
                new OrderedItem(1L, product2, 1),
                new OrderedItem(1L, product3, 10)
        ));

        System.out.println("order total " + order.totalPrice());
    }

    private static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
        List<T> result = new ArrayList<>();

        for (final T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }

        return result;
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        final List<R> result = new ArrayList<>();
        for (final T t : list) {
            result.add(function.apply(t));
        }
        return result;

    }

    private static <T> BigDecimal total(List<T> list, Function<T, BigDecimal> mapper) {
        BigDecimal total = BigDecimal.ZERO;
        for (final T t : list) {
            total = total.add(mapper.apply(t));
        }

        return total;
    }
}
