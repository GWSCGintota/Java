#### [<-Back](../functional_programming.md) 
# Java Functional Programming: Stream API - Comprehensive Notes with Examples

## 1. Introduction

The **Stream API** in Java is used to process collections of data in a functional programming style.

It was introduced in **Java 8**.

A stream allows us to perform operations such as:

* Filtering data
* Mapping data
* Sorting data
* Counting data
* Finding data
* Reducing data
* Grouping data
* Collecting data into lists, sets, or maps

The Stream API is commonly used with:

* Lambda expressions
* Functional interfaces
* Method references
* Collections
* Optional

---

## 2. What is a Stream?

A **stream** is a sequence of elements that can be processed.

A stream does not store data. It only processes data from a source such as:

* `List`
* `Set`
* Array
* Map values
* File lines

Example:

```java
List<String> names = Arrays.asList("John", "Anne", "Peter");

names.stream()
     .forEach(System.out::println);
```

Output:

```text
John
Anne
Peter
```

---

## 3. Why Do We Use Stream API?

The Stream API is used because it makes data processing:

* Shorter
* Cleaner
* More readable
* More functional
* Easier to chain
* Easier to parallelize

---

## 4. Without Stream vs With Stream

### Without Stream

```java
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 15, 20, 25, 30);
        List<Integer> evenNumbers = new ArrayList<>();

        for (Integer number : numbers) {
            if (number % 2 == 0) {
                evenNumbers.add(number);
            }
        }

        System.out.println(evenNumbers);
    }
}
```

Output:

```text
[10, 20, 30]
```

### With Stream

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 15, 20, 25, 30);

        List<Integer> evenNumbers = numbers.stream()
                .filter(number -> number % 2 == 0)
                .collect(Collectors.toList());

        System.out.println(evenNumbers);
    }
}
```

Output:

```text
[10, 20, 30]
```

The stream version is shorter and cleaner.

---

## 5. Important Stream Concepts

A stream usually has three parts:

```java
source.stream()
      .intermediateOperation()
      .terminalOperation();
```

Example:

```java
numbers.stream()
       .filter(number -> number % 2 == 0)
       .collect(Collectors.toList());
```

| Part                   | Example     | Description           |
| ---------------------- | ----------- | --------------------- |
| Source                 | `numbers`   | Data source           |
| Intermediate operation | `filter()`  | Processes the stream  |
| Terminal operation     | `collect()` | Produces final result |

---

## 6. Stream Pipeline

A stream pipeline is a chain of operations.

Example:

```java
List<String> result = names.stream()
        .filter(name -> name.startsWith("A"))
        .map(String::toUpperCase)
        .sorted()
        .collect(Collectors.toList());
```

This pipeline does the following:

1. Gets a stream from `names`
2. Filters names starting with `"A"`
3. Converts them to uppercase
4. Sorts them
5. Collects them into a list

---

## 7. Types of Stream Operations

Stream operations are mainly divided into two types:

1. **Intermediate operations**
2. **Terminal operations**

---

## 8. Intermediate Operations

Intermediate operations return another stream.

They do not produce the final result immediately.

Common intermediate operations:

| Method       | Description                          |
| ------------ | ------------------------------------ |
| `filter()`   | Filters elements                     |
| `map()`      | Transforms elements                  |
| `flatMap()`  | Flattens nested structures           |
| `sorted()`   | Sorts elements                       |
| `distinct()` | Removes duplicates                   |
| `limit()`    | Limits number of elements            |
| `skip()`     | Skips elements                       |
| `peek()`     | Performs action mainly for debugging |

---

## 9. Terminal Operations

Terminal operations produce the final result.

After a terminal operation, the stream cannot be reused.

Common terminal operations:

| Method        | Description                               |
| ------------- | ----------------------------------------- |
| `collect()`   | Collects result into list, set, map, etc. |
| `forEach()`   | Performs action on each element           |
| `count()`     | Counts elements                           |
| `reduce()`    | Combines elements into one result         |
| `findFirst()` | Finds first element                       |
| `findAny()`   | Finds any element                         |
| `anyMatch()`  | Checks if any element matches             |
| `allMatch()`  | Checks if all elements match              |
| `noneMatch()` | Checks if no elements match               |
| `min()`       | Finds minimum element                     |
| `max()`       | Finds maximum element                     |

---

## 10. Creating Streams

## 10.1 Stream from List

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Anne", "Peter");

        names.stream()
                .forEach(System.out::println);
    }
}
```

---

## 10.2 Stream from Array

```java
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] names = {"John", "Anne", "Peter"};

        Arrays.stream(names)
                .forEach(System.out::println);
    }
}
```

---

## 10.3 Stream Using `Stream.of()`

```java
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Stream.of("Java", "Python", "C++")
                .forEach(System.out::println);
    }
}
```

---

## 10.4 Stream from Set

```java
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> names = new HashSet<>();

        names.add("John");
        names.add("Anne");
        names.add("Peter");

        names.stream()
                .forEach(System.out::println);
    }
}
```

---

## 11. `filter()` Method

`filter()` is used to select elements based on a condition.

It uses a `Predicate`.

### Example: Filter Even Numbers

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 15, 20, 25, 30);

        List<Integer> evenNumbers = numbers.stream()
                .filter(number -> number % 2 == 0)
                .collect(Collectors.toList());

        System.out.println(evenNumbers);
    }
}
```

Output:

```text
[10, 20, 30]
```

### Explanation

```java
number -> number % 2 == 0
```

This lambda checks whether the number is even.

---

## 12. `map()` Method

`map()` is used to transform each element.

It uses a `Function`.

### Example: Convert Names to Uppercase

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("john", "anne", "peter");

        List<String> upperNames = names.stream()
                .map(name -> name.toUpperCase())
                .collect(Collectors.toList());

        System.out.println(upperNames);
    }
}
```

Output:

```text
[JOHN, ANNE, PETER]
```

Using method reference:

```java
List<String> upperNames = names.stream()
        .map(String::toUpperCase)
        .collect(Collectors.toList());
```

---

## 13. Difference Between `filter()` and `map()`

| Method     | Purpose             | Example                    |
| ---------- | ------------------- | -------------------------- |
| `filter()` | Selects elements    | Get even numbers           |
| `map()`    | Transforms elements | Convert names to uppercase |

Example:

```java
List<String> result = names.stream()
        .filter(name -> name.length() > 4)
        .map(String::toUpperCase)
        .collect(Collectors.toList());
```

This filters names longer than 4 letters and converts them to uppercase.

---

## 14. `forEach()` Method

`forEach()` is used to perform an action on each element.

It is a terminal operation.

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Anne", "Peter");

        names.stream()
                .forEach(name -> System.out.println(name));
    }
}
```

Using method reference:

```java
names.stream()
        .forEach(System.out::println);
```

---

## 15. `collect()` Method

`collect()` is used to collect stream results into a collection such as:

* List
* Set
* Map

---

## 15.1 Collect to List

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Anne", "Peter");

        List<String> result = names.stream()
                .filter(name -> name.length() > 4)
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

Output:

```text
[Peter]
```

---

## 15.2 Collect to Set

```java
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Anne", "John", "Peter");

        Set<String> uniqueNames = names.stream()
                .collect(Collectors.toSet());

        System.out.println(uniqueNames);
    }
}
```

---

## 15.3 Collect to Map

```java
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Student {
    private int id;
    private String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student(1, "John"),
                new Student(2, "Anne"),
                new Student(3, "Peter")
        );

        Map<Integer, String> studentMap = students.stream()
                .collect(Collectors.toMap(Student::getId, Student::getName));

        System.out.println(studentMap);
    }
}
```

Output:

```text
{1=John, 2=Anne, 3=Peter}
```

---

## 16. `sorted()` Method

`sorted()` is used to sort stream elements.

---

## 16.1 Sort Numbers

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 2, 9, 1);

        List<Integer> sortedNumbers = numbers.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(sortedNumbers);
    }
}
```

Output:

```text
[1, 2, 5, 9]
```

---

## 16.2 Sort Names

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Peter", "Anne", "John");

        List<String> sortedNames = names.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(sortedNames);
    }
}
```

Output:

```text
[Anne, John, Peter]
```

---

## 16.3 Sort Objects by Field

```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private int salary;

    Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    String getName() {
        return name;
    }

    int getSalary() {
        return salary;
    }

    public String toString() {
        return name + " - " + salary;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John", 50000),
                new Employee("Anne", 70000),
                new Employee("Peter", 40000)
        );

        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .collect(Collectors.toList());

        System.out.println(sortedEmployees);
    }
}
```

Output:

```text
[Peter - 40000, John - 50000, Anne - 70000]
```

---

## 16.4 Sort in Descending Order

```java
List<Employee> sortedEmployees = employees.stream()
        .sorted(Comparator.comparing(Employee::getSalary).reversed())
        .collect(Collectors.toList());
```

---

## 17. `distinct()` Method

`distinct()` removes duplicate values.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 4);

        List<Integer> uniqueNumbers = numbers.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println(uniqueNumbers);
    }
}
```

Output:

```text
[1, 2, 3, 4]
```

---

## 18. `limit()` Method

`limit()` is used to take only a specific number of elements.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);

        List<Integer> result = numbers.stream()
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

Output:

```text
[10, 20, 30]
```

---

## 19. `skip()` Method

`skip()` is used to skip a specific number of elements.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);

        List<Integer> result = numbers.stream()
                .skip(2)
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

Output:

```text
[30, 40, 50]
```

---

## 20. `count()` Method

`count()` returns the number of elements in the stream.

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Anne", "Peter", "Amanda");

        long count = names.stream()
                .filter(name -> name.startsWith("A"))
                .count();

        System.out.println(count);
    }
}
```

Output:

```text
2
```

---

## 21. `reduce()` Method

`reduce()` combines stream elements into a single result.

---

## 21.1 Sum of Numbers

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        int total = numbers.stream()
                .reduce(0, (a, b) -> a + b);

        System.out.println(total);
    }
}
```

Output:

```text
15
```

Using method reference:

```java
int total = numbers.stream()
        .reduce(0, Integer::sum);
```

---

## 21.2 Find Maximum Using `reduce()`

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 25, 5, 40);

        int max = numbers.stream()
                .reduce(Integer.MIN_VALUE, Integer::max);

        System.out.println(max);
    }
}
```

Output:

```text
40
```

---

## 22. `min()` and `max()` Methods

`min()` and `max()` return an `Optional` because the stream may be empty.

```java
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 25, 5, 40);

        Optional<Integer> minNumber = numbers.stream()
                .min(Integer::compareTo);

        Optional<Integer> maxNumber = numbers.stream()
                .max(Integer::compareTo);

        minNumber.ifPresent(value -> System.out.println("Min: " + value));
        maxNumber.ifPresent(value -> System.out.println("Max: " + value));
    }
}
```

Output:

```text
Min: 5
Max: 40
```

---

## 23. `findFirst()` Method

`findFirst()` returns the first matching element.

It returns an `Optional`.

```java
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Anne", "Peter");

        Optional<String> result = names.stream()
                .filter(name -> name.startsWith("A"))
                .findFirst();

        result.ifPresent(System.out::println);
    }
}
```

Output:

```text
Anne
```

---

## 24. `findAny()` Method

`findAny()` returns any matching element.

It is useful in parallel streams.

```java
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Anne", "Alex", "Peter");

        Optional<String> result = names.stream()
                .filter(name -> name.startsWith("A"))
                .findAny();

        result.ifPresent(System.out::println);
    }
}
```

Output:

```text
Anne
```

---

## 25. Match Methods

Java Stream API provides three match methods:

| Method        | Description                             |
| ------------- | --------------------------------------- |
| `anyMatch()`  | Checks if any element matches condition |
| `allMatch()`  | Checks if all elements match condition  |
| `noneMatch()` | Checks if no elements match condition   |

---

## 25.1 `anyMatch()`

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40);

        boolean result = numbers.stream()
                .anyMatch(number -> number > 25);

        System.out.println(result);
    }
}
```

Output:

```text
true
```

---

## 25.2 `allMatch()`

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40);

        boolean result = numbers.stream()
                .allMatch(number -> number > 5);

        System.out.println(result);
    }
}
```

Output:

```text
true
```

---

## 25.3 `noneMatch()`

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40);

        boolean result = numbers.stream()
                .noneMatch(number -> number < 0);

        System.out.println(result);
    }
}
```

Output:

```text
true
```

---

## 26. `flatMap()` Method

`flatMap()` is used to flatten nested collections.

### Example Without `flatMap()`

```java
List<List<String>> names = Arrays.asList(
        Arrays.asList("John", "Anne"),
        Arrays.asList("Peter", "Mary")
);
```

This is a list inside a list.

### Example With `flatMap()`

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<List<String>> nestedNames = Arrays.asList(
                Arrays.asList("John", "Anne"),
                Arrays.asList("Peter", "Mary")
        );

        List<String> allNames = nestedNames.stream()
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());

        System.out.println(allNames);
    }
}
```

Output:

```text
[John, Anne, Peter, Mary]
```

Using method reference:

```java
List<String> allNames = nestedNames.stream()
        .flatMap(List::stream)
        .collect(Collectors.toList());
```

---

## 27. `peek()` Method

`peek()` is mainly used for debugging stream pipelines.

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("john", "anne", "peter");

        List<String> result = names.stream()
                .peek(name -> System.out.println("Original: " + name))
                .map(String::toUpperCase)
                .peek(name -> System.out.println("Uppercase: " + name))
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

Output:

```text
Original: john
Uppercase: JOHN
Original: anne
Uppercase: ANNE
Original: peter
Uppercase: PETER
[JOHN, ANNE, PETER]
```

---

## 28. Lazy Evaluation in Streams

Intermediate operations are lazy.

This means they are not executed until a terminal operation is called.

Example:

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Anne", "Peter");

        names.stream()
                .filter(name -> {
                    System.out.println("Filtering: " + name);
                    return name.startsWith("A");
                });
    }
}
```

Output:

```text
No output
```

There is no output because there is no terminal operation.

Now add terminal operation:

```java
names.stream()
        .filter(name -> {
            System.out.println("Filtering: " + name);
            return name.startsWith("A");
        })
        .collect(Collectors.toList());
```

Now the filter operation runs.

---

## 29. Streams Cannot Be Reused

A stream can be used only once.

Bad example:

```java
Stream<String> stream = names.stream();

stream.forEach(System.out::println);
stream.count(); // Error
```

This causes an error because the stream is already consumed.

Correct:

```java
names.stream().forEach(System.out::println);
long count = names.stream().count();
```

---

## 30. Primitive Streams

Java provides primitive streams for better performance:

| Primitive Stream | Used For      |
| ---------------- | ------------- |
| `IntStream`      | int values    |
| `LongStream`     | long values   |
| `DoubleStream`   | double values |

---

## 30.1 `IntStream` Example

```java
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        IntStream.range(1, 5)
                .forEach(System.out::println);
    }
}
```

Output:

```text
1
2
3
4
```

`range(1, 5)` includes 1 but excludes 5.

---

## 30.2 `IntStream.rangeClosed()`

```java
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        IntStream.rangeClosed(1, 5)
                .forEach(System.out::println);
    }
}
```

Output:

```text
1
2
3
4
5
```

---

## 30.3 Sum Using IntStream

```java
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int total = IntStream.rangeClosed(1, 5)
                .sum();

        System.out.println(total);
    }
}
```

Output:

```text
15
```

---

## 31. Parallel Stream

A parallel stream processes data using multiple threads.

Example:

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Anne", "Peter", "Mary");

        names.parallelStream()
                .forEach(System.out::println);
    }
}
```

Important note:

The output order may not be guaranteed.

Use parallel streams carefully, especially when order matters or when operations modify shared data.

---

## 32. Stream API with Objects

Example using an `Employee` class:

```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private String department;
    private int salary;

    Employee(String name, String department, int salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    String getName() {
        return name;
    }

    String getDepartment() {
        return department;
    }

    int getSalary() {
        return salary;
    }

    public String toString() {
        return name + " - " + department + " - " + salary;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John", "IT", 50000),
                new Employee("Anne", "HR", 70000),
                new Employee("Peter", "IT", 40000),
                new Employee("Mary", "Finance", 90000)
        );

        List<String> highSalaryEmployeeNames = employees.stream()
                .filter(employee -> employee.getSalary() >= 60000)
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .map(Employee::getName)
                .collect(Collectors.toList());

        System.out.println(highSalaryEmployeeNames);
    }
}
```

Output:

```text
[Mary, Anne]
```

---

## 33. Grouping Data with `Collectors.groupingBy()`

`groupingBy()` is used to group objects based on a field.

```java
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private String department;
    private int salary;

    Employee(String name, String department, int salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    String getName() {
        return name;
    }

    String getDepartment() {
        return department;
    }

    int getSalary() {
        return salary;
    }

    public String toString() {
        return name + " - " + salary;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John", "IT", 50000),
                new Employee("Anne", "HR", 70000),
                new Employee("Peter", "IT", 40000),
                new Employee("Mary", "Finance", 90000)
        );

        Map<String, List<Employee>> employeesByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println(employeesByDepartment);
    }
}
```

Example output:

```text
{Finance=[Mary - 90000], HR=[Anne - 70000], IT=[John - 50000, Peter - 40000]}
```

---

## 34. Counting with `groupingBy()`

```java
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private String department;

    Employee(String name, String department) {
        this.name = name;
        this.department = department;
    }

    String getDepartment() {
        return department;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John", "IT"),
                new Employee("Anne", "HR"),
                new Employee("Peter", "IT"),
                new Employee("Mary", "Finance")
        );

        Map<String, Long> countByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

        System.out.println(countByDepartment);
    }
}
```

Output:

```text
{Finance=1, HR=1, IT=2}
```

---

## 35. Partitioning with `Collectors.partitioningBy()`

`partitioningBy()` splits data into two groups:

* `true`
* `false`

Example:

```java
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private int salary;

    Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    int getSalary() {
        return salary;
    }

    public String toString() {
        return name + " - " + salary;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John", 50000),
                new Employee("Anne", 70000),
                new Employee("Peter", 40000),
                new Employee("Mary", 90000)
        );

        Map<Boolean, List<Employee>> partitionedEmployees = employees.stream()
                .collect(Collectors.partitioningBy(employee -> employee.getSalary() >= 60000));

        System.out.println(partitionedEmployees);
    }
}
```

Output:

```text
{
  false=[John - 50000, Peter - 40000],
  true=[Anne - 70000, Mary - 90000]
}
```

---

## 36. Joining Strings with `Collectors.joining()`

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Anne", "Peter");

        String result = names.stream()
                .collect(Collectors.joining(", "));

        System.out.println(result);
    }
}
```

Output:

```text
John, Anne, Peter
```

---

## 37. Summary Statistics

For numeric values, streams can calculate summary statistics.

```java
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> marks = Arrays.asList(75, 90, 45, 82);

        IntSummaryStatistics statistics = marks.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();

        System.out.println("Count: " + statistics.getCount());
        System.out.println("Min: " + statistics.getMin());
        System.out.println("Max: " + statistics.getMax());
        System.out.println("Average: " + statistics.getAverage());
        System.out.println("Sum: " + statistics.getSum());
    }
}
```

Output:

```text
Count: 4
Min: 45
Max: 90
Average: 73.0
Sum: 292
```

---

## 38. `mapToInt()`, `mapToDouble()`, and `mapToLong()`

These methods convert object streams into primitive streams.

Example:

```java
import java.util.Arrays;
import java.util.List;

class Employee {
    private String name;
    private int salary;

    Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    int getSalary() {
        return salary;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John", 50000),
                new Employee("Anne", 70000),
                new Employee("Peter", 40000)
        );

        int totalSalary = employees.stream()
                .mapToInt(Employee::getSalary)
                .sum();

        System.out.println(totalSalary);
    }
}
```

Output:

```text
160000
```

---

## 39. Stream API with Optional

Some stream methods return `Optional`.

Example:

```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class Employee {
    private String name;
    private int salary;

    Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    String getName() {
        return name;
    }

    int getSalary() {
        return salary;
    }

    public String toString() {
        return name + " - " + salary;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John", 50000),
                new Employee("Anne", 70000),
                new Employee("Peter", 40000)
        );

        Optional<Employee> highestPaidEmployee = employees.stream()
                .max(Comparator.comparing(Employee::getSalary));

        highestPaidEmployee.ifPresent(System.out::println);
    }
}
```

Output:

```text
Anne - 70000
```

---

## 40. Complete Example: Student Data Processing

```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Student {
    private String name;
    private String course;
    private int marks;

    Student(String name, String course, int marks) {
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    String getName() {
        return name;
    }

    String getCourse() {
        return course;
    }

    int getMarks() {
        return marks;
    }

    boolean hasPassed() {
        return marks >= 50;
    }

    public String toString() {
        return name + " - " + course + " - " + marks;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("John", "Java", 75),
                new Student("Anne", "Java", 90),
                new Student("Peter", "Python", 45),
                new Student("Mary", "Python", 82),
                new Student("Alex", "Java", 60)
        );

        List<String> passedStudentNames = students.stream()
                .filter(Student::hasPassed)
                .sorted(Comparator.comparing(Student::getMarks).reversed())
                .map(Student::getName)
                .collect(Collectors.toList());

        System.out.println("Passed students: " + passedStudentNames);

        Map<String, List<Student>> studentsByCourse = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse));

        System.out.println("Students by course: " + studentsByCourse);

        double averageMarks = students.stream()
                .mapToInt(Student::getMarks)
                .average()
                .orElse(0);

        System.out.println("Average marks: " + averageMarks);
    }
}
```

Output:

```text
Passed students: [Anne, Mary, John, Alex]
Students by course: {Java=[John - Java - 75, Anne - Java - 90, Alex - Java - 60], Python=[Peter - Python - 45, Mary - Python - 82]}
Average marks: 70.4
```

---

## 41. Advantages of Stream API

* Reduces boilerplate code
* Makes collection processing easier
* Supports functional programming
* Works well with lambda expressions
* Works well with method references
* Supports parallel processing
* Improves readability for data pipelines
* Provides powerful operations like filtering, mapping, grouping, and reducing

---

## 42. Disadvantages of Stream API

* Can be difficult for beginners
* Debugging can be harder
* Overusing streams can reduce readability
* Not always faster than loops
* Parallel streams can cause unexpected behavior if used incorrectly
* Streams cannot be reused

---

## 43. Best Practices

## 43.1 Keep Stream Pipelines Readable

Good:

```java
List<String> result = names.stream()
        .filter(name -> name.startsWith("A"))
        .map(String::toUpperCase)
        .collect(Collectors.toList());
```

Avoid writing everything in one long line.

---

## 43.2 Use Method References When Clear

Good:

```java
names.forEach(System.out::println);
```

Good:

```java
names.stream()
        .map(String::toUpperCase)
        .collect(Collectors.toList());
```

---

## 43.3 Avoid Side Effects

Avoid modifying external variables inside streams.

Bad:

```java
List<String> result = new ArrayList<>();

names.stream()
        .forEach(name -> result.add(name));
```

Better:

```java
List<String> result = names.stream()
        .collect(Collectors.toList());
```

---

## 43.4 Do Not Reuse Streams

Bad:

```java
Stream<String> stream = names.stream();

stream.forEach(System.out::println);
stream.count();
```

Good:

```java
names.stream().forEach(System.out::println);
long count = names.stream().count();
```

---

## 43.5 Use Loops When Logic is Complex

Streams are good for data processing pipelines.

If the logic is very complex, a normal loop may be easier to read.

---

## 44. Common Mistakes

## Mistake 1: Forgetting Terminal Operation

Bad:

```java
names.stream()
        .filter(name -> name.startsWith("A"));
```

Nothing happens because there is no terminal operation.

Correct:

```java
List<String> result = names.stream()
        .filter(name -> name.startsWith("A"))
        .collect(Collectors.toList());
```

---

## Mistake 2: Reusing a Stream

Bad:

```java
Stream<String> stream = names.stream();

stream.forEach(System.out::println);
stream.count();
```

A stream cannot be reused after a terminal operation.

---

## Mistake 3: Using `map()` Instead of `flatMap()`

If you have nested lists, use `flatMap()`.

Correct:

```java
List<String> allNames = nestedNames.stream()
        .flatMap(List::stream)
        .collect(Collectors.toList());
```

---

## Mistake 4: Using Parallel Stream Without Understanding

Parallel streams can cause problems if your code modifies shared data.

Bad:

```java
List<Integer> result = new ArrayList<>();

numbers.parallelStream()
        .forEach(number -> result.add(number));
```

Better:

```java
List<Integer> result = numbers.parallelStream()
        .collect(Collectors.toList());
```

---

## 45. Interview Questions

## Question 1: What is Stream API in Java?

The Stream API is a Java feature introduced in Java 8 that allows processing collections in a functional programming style.

---

## Question 2: Does a stream store data?

No. A stream does not store data. It processes data from a source such as a collection or array.

---

## Question 3: What is the difference between intermediate and terminal operations?

| Intermediate Operation | Terminal Operation    |
| ---------------------- | --------------------- |
| Returns another stream | Produces final result |
| Lazy                   | Executes the pipeline |
| Example: `filter()`    | Example: `collect()`  |
| Example: `map()`       | Example: `forEach()`  |

---

## Question 4: What is lazy evaluation in streams?

Lazy evaluation means intermediate operations do not execute until a terminal operation is called.

---

## Question 5: Can a stream be reused?

No. Once a terminal operation is performed, the stream is consumed and cannot be reused.

---

## Question 6: What is the difference between `map()` and `flatMap()`?

| Method      | Description                            |
| ----------- | -------------------------------------- |
| `map()`     | Transforms each element                |
| `flatMap()` | Transforms and flattens nested streams |

---

## Question 7: What is the difference between `findFirst()` and `findAny()`?

| Method        | Description                                              |
| ------------- | -------------------------------------------------------- |
| `findFirst()` | Returns the first element                                |
| `findAny()`   | Returns any matching element, useful in parallel streams |

---

## Question 8: What is the difference between collection and stream?

| Collection      | Stream                   |
| --------------- | ------------------------ |
| Stores data     | Does not store data      |
| Can be reused   | Cannot be reused         |
| Data structure  | Data processing pipeline |
| Example: `List` | Example: `list.stream()` |

---

## Question 9: What is a parallel stream?

A parallel stream processes data using multiple threads. It can improve performance for large datasets, but it should be used carefully.

---

## Question 10: What are common Stream API methods?

Common methods include:

* `filter()`
* `map()`
* `flatMap()`
* `sorted()`
* `distinct()`
* `limit()`
* `skip()`
* `collect()`
* `forEach()`
* `count()`
* `reduce()`
* `findFirst()`
* `findAny()`
* `anyMatch()`
* `allMatch()`
* `noneMatch()`

---

## 46. Simple Summary

The Java Stream API is used to process collections in a functional programming style.

Basic syntax:

```java
collection.stream()
          .operation()
          .terminalOperation();
```

Example:

```java
List<Integer> evenNumbers = numbers.stream()
        .filter(number -> number % 2 == 0)
        .collect(Collectors.toList());
```

Important points:

* A stream does not store data.
* A stream processes data.
* Stream operations can be chained.
* Intermediate operations are lazy.
* Terminal operations execute the pipeline.
* A stream cannot be reused.
* Streams work well with lambda expressions and method references.

---

## 47. Final Simple Example

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("john", "anne", "peter", "alex");

        List<String> result = names.stream()
                .filter(name -> name.length() > 4)
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

Output:

```text
[PETER]
```

Explanation:

```java
.filter(name -> name.length() > 4)
```

keeps only names with more than 4 letters.

```java
.map(String::toUpperCase)
```

converts names to uppercase.

```java
.sorted()
```

sorts the result.

```java
.collect(Collectors.toList())
```

collects the result into a list.
