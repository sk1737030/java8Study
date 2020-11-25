package streamexample;

public class StreamPrelude {
    public static void main(String[] args) {
        final int abs1 = Math.abs(-1);
        final int abs2 = Math.abs(1);

        System.out.println("abs1: " + abs1);
        System.out.println("abs2: " + abs2);
        System.out.println("abs1 == abs2 is " + (abs1 == abs2));

        //  java 32bit signed Int -214783648
        final int mintInt = Math.abs(Integer.MIN_VALUE);
        final int maxInt = Math.abs(Integer.MAX_VALUE);
        System.out.println("minInt: " + mintInt);
        System.out.println("maxInt: " + maxInt);
        /**
         * minInt: -2147483648
         * maxInt: 2147483647
         */
    }
}
