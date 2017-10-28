package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by cody81314 on 2017/10/26.
 */
public class FlatmapDemo {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("Hello", "World");
        Stream<String> chars = words.stream()
                .flatMap(word -> Arrays.stream(word.split("")));

        chars.forEach(System.out::println);
    }
}
