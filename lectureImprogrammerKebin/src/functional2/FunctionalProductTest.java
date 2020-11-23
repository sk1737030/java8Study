package functional2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalProductTest {
    public static void main(String[] args) {
        final List<Product> products = Arrays.asList(
                new Product(1L, "A", new BigDecimal("10.00")),
                new Product(2L, "B", new BigDecimal("55.50")),
                new Product(3L, "C", new BigDecimal("17.40")),
                new Product(4L, "D", new BigDecimal("25.25")),
                new Product(5L, "E", new BigDecimal("777.11"))
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
        final List<Product> FdiscountedProducts = map(expensiveProducts, product -> new DisCountedProduct(product.getId(), product.getName(),
                product.getPrice().multiply(new BigDecimal("0.5"))));
        System.out.println("expensive products: " + expensiveProducts);
        System.out.println("FdisCounted products: " + FdiscountedProducts);
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        final List<R> result = new ArrayList<>();
        for (final T t : list) {
            result.add(function.apply(t));
        }
        return result;

    }

    private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();

        for (final T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }

        return result;
    }
}
