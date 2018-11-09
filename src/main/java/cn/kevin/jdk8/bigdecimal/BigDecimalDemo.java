package cn.kevin.jdk8.bigdecimal;

import java.math.BigDecimal;

/**
 *
 *
 *
 * @author yongkang.zhang
 * created at 09/11/2018
 */
public class BigDecimalDemo {

    /**
     * @see <a href="https://stackoverflow.com/questions/1078953/check-if-bigdecimal-is-integer-value">Check if BigDecimal is integer value</a>
     * @param args 参数
     */
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(6.00);
        BigDecimal b2 = new BigDecimal(6.01);
        BigDecimal b3 = new BigDecimal(6.0045);

        System.out.println(bigDecimal.stripTrailingZeros().scale() > 0);
        System.out.println(b2.stripTrailingZeros().scale() > 0);
        System.out.println(b3.stripTrailingZeros().scale() > 0);
    }
}
