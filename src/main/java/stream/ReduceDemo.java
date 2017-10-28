package stream;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by cody81314 on 2017/10/28.
 */
public class ReduceDemo {

    public static void main(String[] args) {
        demo1();
    }

    public static void demo1() {
        System.out.println("=======================demo1=========================");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Integer sum = numbers.stream()
                .reduce(0, Integer::sum);

        System.out.println("sum: " + sum);
        System.out.println("=====================================================");
    }
}
