import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionsTest {

    public static void main(String[] args) {
        Function<String, String> greet= n-> "Hello, " +n+"!";
        System.out.println(greet.apply("Kamal"));
        System.out.println(greet.apply("Nimal"));

        Function<String,Integer> getLength= n->n.length();
        System.out.println(getLength.apply("Java"));
        System.out.println(getLength.apply("Functional Programming"));
        System.out.println(getLength.apply("Hello"));

        Function<String,String> upper= n->n.toUpperCase(Locale.ROOT);
        List<String> names= Arrays.asList("kamal", "nimal", "sunil",  "amal");
        List<String> upperList=names.stream().map(upper).collect(Collectors.toList());
        System.out.println(upperList);




        Function<String,String > trimFunction = n->n.trim();
        Function<String,String> upperFunction= n->n.toUpperCase();

        Function<String,String> combine= trimFunction.andThen(upperFunction);
        System.out.println(combine.apply("  hello Java "));

        Function<Integer,Integer> multiplyByTwo= n->n*2;
        Function<Integer,Integer> addTen= n->n+10;
        Function<Integer,Integer> last= multiplyByTwo.compose(addTen);
        System.out.println(last.apply(5));

        List<Integer> list= Arrays.asList(10,20,30,40,50);
        Function<Integer,Integer> name= Function.identity();
        List<Integer> newList= list.stream().map(name).collect(Collectors.toList());

        System.out.println(newList);

        BiFunction<Double,Integer,Double> calculateTotal=(n,m)-> n*m;
        Function<Double,Double> applyDiscount= n->n*0.9;
        System.out.println(calculateTotal.andThen(applyDiscount).apply(100.0 ,3));


    }
}
