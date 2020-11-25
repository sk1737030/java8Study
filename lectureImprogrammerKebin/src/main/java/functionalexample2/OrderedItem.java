package functionalexample2;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class OrderedItem {
    private Long id;
    private Product product;
    private int quantity;

    public BigDecimal getItemTotal() {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }
}
