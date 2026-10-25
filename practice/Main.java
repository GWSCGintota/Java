import javax.sound.midi.SoundbankResource;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee("John",28 ,75000 ,"IT" ),
                new Employee("Alice", 35, 95000, "IT"),
                new Employee("Bob", 40, 85000, "HR"),
                new Employee("David", 32, 110000, "IT"),
                new Employee("Emma", 26, 65000, "Finance")
        );



    }

    public static List<Employee> filterEmployee(List<Employee> employees,
                                                List<Predicate<Employee>> conditions){
        return employees.stream().filter(conditions.stream().
                reduce(e->true,Predicate::and)).collect(Collectors.toList());

    }


}
