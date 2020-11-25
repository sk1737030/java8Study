package functionalexmaple;

@FunctionalInterface
public interface CustomFunctional<T1, T2, T3, R> {
    R apply(T1 t1, T2 t2, T3 t3);
}



// labda Expression 사용 할 수 없다. 코드 작성시 제너릭 타입 알기가 에매함.
@FunctionalInterface
interface CustomFunctional2 {
    <T> String apply2(T t1);
}