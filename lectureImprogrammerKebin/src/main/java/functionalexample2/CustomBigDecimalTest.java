package functionalexample2;

import java.math.BigDecimal;

public class CustomBigDecimalTest {
    public static void main(String[] args) {
        BigDecimalToCurrency bigDecimalToCurrency = bd -> "$" + bd.toString();
        System.out.println(bigDecimalToCurrency.toCurrency(new BigDecimal("120.00")));
    }
}
