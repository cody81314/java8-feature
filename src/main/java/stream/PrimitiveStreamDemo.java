package stream;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Created by cody81314 on 2017/10/29.
 */
public class PrimitiveStreamDemo {

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
    }

    private static void demo1() {
        System.out.println("=======================demo1=========================");
        IntStream.rangeClosed(1,10)
                .forEach(System.out::println);
        System.out.println("=====================================================");
    }

    private static void demo2() {
        System.out.println("=======================demo2=========================");
        LongStream.rangeClosed(1,10)
                .forEach(System.out::println);
        System.out.println("=====================================================");
    }

    private static void demo3() {
        System.out.println("=======================demo3=========================");
        System.out.println("找出1~100所有符合畢氏定理數組");
        IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}))
                .filter(t -> t[2] % 1 == 0)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + "," + t[2] + ")"));

        System.out.println("=====================================================");
    }
}
