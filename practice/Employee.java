import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Employee {
    String name;
    int age;
    double salary;
    String department;

    Employee(String name, int age,
             double salary, String department) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }


    public static List<Employee> filterEmployee(List<Employee> employees, Predicate<Employee> condition){
        return employees.stream().filter(condition).collect(Collectors.toList());
    }


}