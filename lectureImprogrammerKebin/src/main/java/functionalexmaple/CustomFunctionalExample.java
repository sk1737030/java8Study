package functionalexmaple;

public class CustomFunctionalExample {

    public static void main(String[] args) {
        println(1, 2, 3, (i1, i2, i3) -> String.valueOf((i1 + i2 + i3)));
        println("Area is ", 12, 20, (message, length, width) -> message + (length * width));
        println(1L, "Kevin", "sk1737@naver.com", (id, name, email) -> "User info: Id = " + id + ", name = " + name + ", email = " + email);
    }

    private static <T1, T2, T3> void println(T1 t1, T2 t2, T3 t3, CustomFunctional<T1, T2, T3, String> function) {
        System.out.println(function.apply(t1, t2, t3));
    }
}
