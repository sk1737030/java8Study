package streamexample;


import functionalexample2.Order;
import functionalexample2.OrderedItem;
import functionalexample2.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class StreamExample4 {
    public static void main(String[] args) {
        final List<Product> products =
                Arrays.asList(
                        new Product(1L, "A", new BigDecimal("100.50")),
                        new Product(2L, "B", new BigDecimal("23.00")),
                        new Product(3L, "C", new BigDecimal("31.50")),
                        new Product(4L, "D", new BigDecimal("80.50")),
                        new Product(5L, "E", new BigDecimal("7.50"))
                );


        System.out.println("Products.prie >= 30 : \n" + products.stream()
                .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                .collect(toList())
        );

        System.out.println("\n========================================================");
        System.out.println("Products.price >= 30 (with joining) : \n" + products.stream()
                .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                .map(product -> product.toString())
                .collect(joining("\n"))
        );

        System.out.println("\n========================================================");
        System.out.println("IntStream.sum : " +
                IntStream.of(1, 2, 3, 4, 5)
                        .sum()
        );

        System.out.println("\n========================================================");
        System.out.println("Products Total Price 값 " +
                products.stream()
                        .map(product -> product.getPrice()) // Big Decimal로 변형해줘야한다.
                        .reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2))
        );

        System.out.println("\n========================================================");
        System.out.println("Products Price over 30: " +
                products.stream()
                        .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                        .map(product -> product.getPrice())
                        .reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2))
        );

        System.out.println("\n========================================================");
        System.out.println("# of Products Price >= 30: " +
                products.stream()
                        .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                        .count()
        );

        final OrderedItem item1 = new OrderedItem(1L, products.get(0), 1);
        final OrderedItem item3 = new OrderedItem(2L, products.get(2), 2);
        final OrderedItem item2 = new OrderedItem(3L, products.get(4), 10);

        final Order order = new Order(1L, "order-11",Arrays.asList(item1, item2, item3));

        System.out.println("\n========================================================");
        System.out.println("order total Product : " + order.totalPriceStream()) ;


    }
}
