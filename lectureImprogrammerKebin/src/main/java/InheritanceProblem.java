import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * Object Oriented
 * inheritance  문제점
 *
 * inheritance 상속의 문제점
 */
public class InheritanceProblem {
    public static void main(String[] args) {

        // Given
        MyList<Integer> list = new MyList<Integer>();

        /*// When
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        // Then
        System.out.println(list.size()); // 4
*/
        // When
        list.addAll(Arrays.asList(1, 2, 3, 4));

        // Then
        System.out.println(list.getSize()); // 4

        // Given
        MyList2<Integer> listHash = new MyList2<Integer>();

        /*
        // When
        listHash.add(1);
        listHash.add(2);
        listHash.add(3);
        listHash.add(4);

        // Then
        System.out.println(list.size()); // 4

        */
        // When
        listHash.addAll(Arrays.asList(1,2,3,4));

        // Then
        System.out.println(listHash.getSize()); // 8
    }
}


class MyList2<E> extends HashSet<E> {

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


class MyList<E> extends ArrayList<E> {

    private int size;

    public int getSize() {
        return size;
    }

    @Override
    public boolean add(E element) {
        size += 1;
        return super.add(element);
    }

    @Override
    public boolean addAll(Collection c) {
        size += c.size();
        return super.addAll(c);
    }
}
