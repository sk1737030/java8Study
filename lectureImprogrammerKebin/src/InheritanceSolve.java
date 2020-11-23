import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class InheritanceSolve {
    public static void main(String[] args) {
        // Given
        MyList3<Integer> myList3  = new MyList3<>();

        // When
        myList3.addAll(Arrays.asList(1,2,3,4));

        // Then
        System.out.println(myList3.getSize()); // 4

    }
}

//  InheritanceProblem에 가지고있던 문제점을 이런식으로 수정해야함
class MySet<E> extends  HashSet<E>{

    public boolean add0(E e) {
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c)
            if (add0(e))
                modified = true;
        return modified;
    }
}

class MyList3<E> extends MySet<E> {

    private int size;

    public int getSize() {
        return size;
    }

    @Override
    public boolean add(E element) {
        size+= 1;
        return super.add(element);
    }

    @Override
    public boolean addAll(Collection c) {
        size += c.size();
        return super.addAll(c);
    }
}

