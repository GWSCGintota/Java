import com.sun.jdi.IntegerValue;

import javax.print.attribute.standard.JobKOctets;
import java.beans.beancontext.BeanContext;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateTest {

    public static void main (String arg[]){

        List<Integer> numbers=Arrays.asList(2,5,8,10,12,15,18,20,25,30);
        Predicate<Integer> isEven= n-> n%2==0;
        Predicate<Integer> greaterThan10 = n-> n>10;
        Predicate<Integer> divisibleBy5= n-> n%5==0;

        Predicate<Integer> finalResult= isEven.and(greaterThan10).or(divisibleBy5.negate());

        List<Integer> newList= numbers.stream().filter(finalResult).collect(Collectors.toList());
        System.out.println(newList);

    }
}
