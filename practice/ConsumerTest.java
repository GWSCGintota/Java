import jdk.dynalink.beans.StaticClass;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ConsumerTest {

    public static void main(String[] args) {
        List<String> names= Arrays.asList("Ada", "Grace","Alan");
        names.forEach(System.out::println);

        List<Integer> lengths= names.stream().map(String::length).collect(Collectors.toList());



    }


}

