package functional2;

import java.math.BigDecimal;

/**
 * 결과 값이 명확한 경우
 * Generic 값이 아니여도 된다
 */
@FunctionalInterface
public interface BigDecimalToCurrency {
    String toCurrency(BigDecimal value);
}
