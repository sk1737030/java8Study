import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {

        final Supplier<String> helloSupplier = () -> "Hellow ";

        System.out.println(helloSupplier.get() + "world");

        printIfValidIndex(0, "Dongho");
        // 불필요한 시간소요가 걸림
        printIfValidIndex(0, getVeryExpensiveValue());
        printIfValidIndex(-1, getVeryExpensiveValue());

        // 필요할 때만 사용가능하다.
        // 앞에작업을  비교 했을 때 필요하다면 뒤에작업을 실행하는 로직이있을 때 유용용        printIfValidIndex2(0, () -> getVeryExpensiveValue());
        printIfValidIndex2(-1,() -> getVeryExpensiveValue());
    }

    private static String getVeryExpensiveValue() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Let's just say it has very expensive calculation here!
        return "Dongho";
    }

    private static void printIfValidIndex(int number, String value) {
        if (number >= 0) {
            System.out.println("this value is " + value + ".");
        } else {
            System.out.println("Invalid");
        }
    }

    private static void printIfValidIndex2(int number, Supplier<String> filter) {
        if (number >= 0) {
            System.out.println("this value is " + filter + ".");
        } else {
            System.out.println("Invalid");
        }
    }

}
