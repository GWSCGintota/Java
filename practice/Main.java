import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Calculator add= (a,b)-> a+b;
        Calculator multiply =(a,b) -> a*b;
        System.out.println("Addition : "+add.calculator(10,5));
        System.out.println("Multiplication : "+add.calculator(10,5));


        List<Integer> number= Arrays.asList(10,15,20,25,30,35,40);
        Predicate<Integer> isEven= num->num%2==0;
        number.stream().filter(isEven).collect(Collectors.toList()).forEach(System.out::println);


        List<String> names= Arrays.asList("Supul","John","david", "michael");

        Function<String,String> name= nam-> nam.toUpperCase(Locale.ROOT);

        names.stream().map(name).forEach(System.out::println);

        Consumer<String> nameConsumer= nam-> System.out.println("Hello "+nam);
        names.forEach(nam->nameConsumer.accept(nam));


        UnaryOperator<Integer> num= s->s*s;
        System.out.println(num.apply(5));

        BinaryOperator<Integer> findMax =  (num1,num2) -> Math.max(10,25);
        System.out.println(findMax.apply(5,10));

        names.stream().map(n->n.toUpperCase()).forEach(System.out::println);


        List<Integer> numbers=  Arrays.asList(5, 10, 15, 20, 25, 30, 35, 40);

        Predicate<Integer> num1= n-> n>20;
        Function<Integer,Integer> num2= n-> n*2;
        numbers.stream().filter(num1).map(num2).forEach(System.out::println);


        Predicate<Integer> greaterThan10 = n -> n > 10;

        Predicate<Integer> even = n -> n % 2 == 0;

        numbers.stream().filter(greaterThan10.and(even)).collect(Collectors.toList());


        Function<String,Integer> parse= Integer::parseInt;
        System.out.println(parse.apply("42"));

        IntBinaryOperator maximum= Math::max;
        System.out.println(maximum.applyAsInt(7,12));

        ToIntFunction<String> parsePrimitive = Integer::parseInt;
        int value= parsePrimitive.applyAsInt("42");

        StringBuilder builder = new StringBuilder();
        Consumer<String> append=builder::append;
        append.accept("Java");
        append.accept("8+");
        System.out.println(builder);



    }

    public static int applyOperation(
            int number,
            Function<Integer, Integer> operation) {
        return operation.apply(number);

    }

    public static List<Integer> filterNumbers(
            List<Integer> numbers,
            Predicate<Integer> condition) {
              return numbers.stream().filter(condition).collect(Collectors.toList());
        // your code
    }

    public static Function<Integer, Integer> createMultiplier(int multiplier) {
            return n-> n* multiplier;

    }



}
