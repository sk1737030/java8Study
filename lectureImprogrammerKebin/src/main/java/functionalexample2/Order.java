package functionalexample2;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
@Data
public class Order {
    private Long id;
    private String orderNumber;
    private List<OrderedItem> items;


    public BigDecimal totalPrice() {
        return total(items, item -> item.getItemTotal());
    }

    private static <T> BigDecimal total(List<T> list, Function<T, BigDecimal> mapper) {
        BigDecimal total = BigDecimal.ZERO;
        for (final T t : list) {
            total = total.add(mapper.apply(t));
        }

        return total;
    }

}
