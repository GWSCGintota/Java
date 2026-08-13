[← Previous: 30. Functional Interfaces](30-functional-interfaces.md) | [Main Index](../main.md) | [Next: 32. Optional →](32-optional.md)

---

# 31. Stream API
# Java Streams — Complete Lesson with Examples

Java Streams provide a clean way to process collections of data. They are commonly used for filtering, transforming, sorting, grouping, and calculating values.

Streams were introduced in **Java 8**.

---

## 1. What Is a Java Stream?

A stream is a sequence of elements that supports data-processing operations.

Suppose you have this list:

```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
```

Using a traditional loop, you might find even numbers like this:

```java
List<Integer> evenNumbers = new ArrayList<>();

for (Integer number : numbers) {
    if (number % 2 == 0) {
        evenNumbers.add(number);
    }
}

System.out.println(evenNumbers);
```

Output:

```text
[2, 4, 6]
```

Using a stream:

```java
List<Integer> evenNumbers = numbers.stream()
        .filter(number -> number % 2 == 0)
        .toList();

System.out.println(evenNumbers);
```

The stream version expresses the intention more directly:

1. Take the numbers.
2. Keep only even numbers.
3. Convert the result into a list.

---

## 2. Collection vs Stream

A collection stores data.

```java
List<String> names = List.of("Amal", "Nimal", "Kamal");
```

A stream processes data.

```java
names.stream()
        .filter(name -> name.startsWith("A"))
        .forEach(System.out::println);
```

| Collection | Stream |
|---|---|
| Stores elements | Processes elements |
| Can normally be reused | Cannot be reused after a terminal operation |
| Can add and remove values | Does not directly modify the source |
| Uses external iteration with loops | Uses internal iteration |

A stream normally does not change the original collection.

```java
List<Integer> numbers = List.of(1, 2, 3, 4);

List<Integer> doubled = numbers.stream()
        .map(number -> number * 2)
        .toList();

System.out.println(numbers);
System.out.println(doubled);
```

Output:

```text
[1, 2, 3, 4]
[2, 4, 6, 8]
```

---

## 3. The Stream Pipeline

A stream pipeline normally has three parts:

```java
source
    .intermediateOperation()
    .terminalOperation();
```

Example:

```java
List<String> result = names.stream()       // Source
        .filter(name -> name.length() > 4) // Intermediate operation
        .map(String::toUpperCase)          // Intermediate operation
        .toList();                         // Terminal operation
```

### Source

The data being processed:

```java
names.stream()
```

### Intermediate Operations

Operations that transform or filter the stream:

```java
filter(...)
map(...)
sorted(...)
distinct()
```

Intermediate operations return another stream.

### Terminal Operation

The operation that produces the final result:

```java
toList()
forEach(...)
count()
collect(...)
reduce(...)
```

A stream does not execute fully until a terminal operation is called.

---

## 4. Creating Streams

### From a List

```java
List<String> names = List.of("Amal", "Nimal", "Kamal");

Stream<String> stream = names.stream();
```

### From a Set

```java
Set<Integer> numbers = Set.of(10, 20, 30);

Stream<Integer> stream = numbers.stream();
```

### From an Array

```java
String[] names = {"Amal", "Nimal", "Kamal"};

Stream<String> stream = Arrays.stream(names);
```

### Using `Stream.of()`

```java
Stream<String> stream = Stream.of("Java", "Spring", "Angular");
```

### Using a Numeric Range

```java
IntStream.range(1, 5)
        .forEach(System.out::println);
```

Output:

```text
1
2
3
4
```

`range()` excludes the final value.

```java
IntStream.rangeClosed(1, 5)
        .forEach(System.out::println);
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

## 5. Lambda Expressions in Streams

Streams frequently use lambda expressions.

```java
number -> number % 2 == 0
```

This lambda accepts a number and returns `true` when it is even.

It is similar to this method:

```java
public static boolean isEven(Integer number) {
    return number % 2 == 0;
}
```

You could use the method with a stream:

```java
List<Integer> evenNumbers = numbers.stream()
        .filter(Main::isEven)
        .toList();
```

---

## 6. `filter()`

`filter()` keeps elements that satisfy a condition.

```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

List<Integer> evenNumbers = numbers.stream()
        .filter(number -> number % 2 == 0)
        .toList();

System.out.println(evenNumbers);
```

Output:

```text
[2, 4, 6]
```

Filtering strings:

```java
List<String> names = List.of(
        "Amal",
        "Nimal",
        "Kamal",
        "Anusha",
        "Sunil"
);

List<String> namesStartingWithA = names.stream()
        .filter(name -> name.startsWith("A"))
        .toList();

System.out.println(namesStartingWithA);
```

Output:

```text
[Amal, Anusha]
```

Multiple conditions:

```java
List<String> result = names.stream()
        .filter(name -> name.startsWith("A"))
        .filter(name -> name.length() > 4)
        .toList();
```

You can also combine the conditions:

```java
List<String> result = names.stream()
        .filter(name -> name.startsWith("A") && name.length() > 4)
        .toList();
```

---

## 7. `map()`

`map()` transforms each element into another value.

```java
List<Integer> numbers = List.of(1, 2, 3, 4);

List<Integer> squares = numbers.stream()
        .map(number -> number * number)
        .toList();

System.out.println(squares);
```

Output:

```text
[1, 4, 9, 16]
```

Convert strings to uppercase:

```java
List<String> names = List.of("amal", "nimal", "kamal");

List<String> uppercaseNames = names.stream()
        .map(String::toUpperCase)
        .toList();

System.out.println(uppercaseNames);
```

Output:

```text
[AMAL, NIMAL, KAMAL]
```

Convert strings to their lengths:

```java
List<Integer> lengths = names.stream()
        .map(String::length)
        .toList();

System.out.println(lengths);
```

Output:

```text
[4, 5, 5]
```

The output type does not need to match the input type.

```text
Stream<String> -> Stream<Integer>
```

---

## 8. `filter()` vs `map()`

`filter()` decides whether an element should remain.

```java
.filter(number -> number > 10)
```

`map()` changes an element.

```java
.map(number -> number * 2)
```

Example using both:

```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

List<Integer> result = numbers.stream()
        .filter(number -> number % 2 == 0)
        .map(number -> number * 10)
        .toList();

System.out.println(result);
```

Output:

```text
[20, 40, 60]
```

---

## 9. `forEach()`

`forEach()` performs an action for every element.

```java
List<String> names = List.of("Amal", "Nimal", "Kamal");

names.stream()
        .forEach(name -> System.out.println(name));
```

Using a method reference:

```java
names.stream()
        .forEach(System.out::println);
```

`forEach()` is a terminal operation.

Avoid using it to build collections manually:

```java
List<String> result = new ArrayList<>();

names.stream()
        .filter(name -> name.startsWith("A"))
        .forEach(result::add);
```

Prefer:

```java
List<String> result = names.stream()
        .filter(name -> name.startsWith("A"))
        .toList();
```

---

## 10. Method References

A method reference is a shorter form of a lambda.

Lambda:

```java
name -> name.toUpperCase()
```

Method reference:

```java
String::toUpperCase
```

Lambda:

```java
name -> System.out.println(name)
```

Method reference:

```java
System.out::println
```

Lambda:

```java
person -> person.getName()
```

Method reference:

```java
Person::getName
```

Example:

```java
List<String> uppercaseNames = names.stream()
        .map(String::toUpperCase)
        .toList();
```

---

## 11. `sorted()`

Sort elements in natural order:

```java
List<Integer> numbers = List.of(5, 2, 8, 1, 4);

List<Integer> sortedNumbers = numbers.stream()
        .sorted()
        .toList();

System.out.println(sortedNumbers);
```

Output:

```text
[1, 2, 4, 5, 8]
```

Descending order:

```java
List<Integer> descending = numbers.stream()
        .sorted(Comparator.reverseOrder())
        .toList();

System.out.println(descending);
```

Output:

```text
[8, 5, 4, 2, 1]
```

Sort strings by length:

```java
List<String> names = List.of("Christopher", "Amal", "Nimal");

List<String> sortedByLength = names.stream()
        .sorted(Comparator.comparing(String::length))
        .toList();

System.out.println(sortedByLength);
```

Sort by length in descending order:

```java
List<String> sortedByLengthDescending = names.stream()
        .sorted(Comparator.comparing(String::length).reversed())
        .toList();
```

---

## 12. `distinct()`

`distinct()` removes duplicate values.

```java
List<Integer> numbers = List.of(1, 2, 2, 3, 3, 3, 4);

List<Integer> uniqueNumbers = numbers.stream()
        .distinct()
        .toList();

System.out.println(uniqueNumbers);
```

Output:

```text
[1, 2, 3, 4]
```

For custom objects, `distinct()` depends on correctly implemented `equals()` and `hashCode()` methods.

---

## 13. `limit()` and `skip()`

### `limit()`

Keeps only a specified number of elements.

```java
List<Integer> numbers = List.of(10, 20, 30, 40, 50);

List<Integer> firstThree = numbers.stream()
        .limit(3)
        .toList();

System.out.println(firstThree);
```

Output:

```text
[10, 20, 30]
```

### `skip()`

Skips a specified number of elements.

```java
List<Integer> remaining = numbers.stream()
        .skip(2)
        .toList();

System.out.println(remaining);
```

Output:

```text
[30, 40, 50]
```

Pagination example:

```java
int pageNumber = 2;
int pageSize = 3;

List<Integer> page = numbers.stream()
        .skip((long) (pageNumber - 1) * pageSize)
        .limit(pageSize)
        .toList();
```

---

## 14. `count()`

`count()` returns the number of elements.

```java
long count = numbers.stream()
        .filter(number -> number % 2 == 0)
        .count();

System.out.println(count);
```

---

## 15. `anyMatch()`, `allMatch()`, and `noneMatch()`

### `anyMatch()`

Returns `true` if at least one element matches.

```java
boolean hasNegativeNumber = numbers.stream()
        .anyMatch(number -> number < 0);
```

### `allMatch()`

Returns `true` if every element matches.

```java
boolean allPositive = numbers.stream()
        .allMatch(number -> number > 0);
```

### `noneMatch()`

Returns `true` if no element matches.

```java
boolean noZeroValues = numbers.stream()
        .noneMatch(number -> number == 0);
```

Example:

```java
List<Integer> values = List.of(10, 20, 30);

System.out.println(values.stream().anyMatch(value -> value > 25));
System.out.println(values.stream().allMatch(value -> value > 0));
System.out.println(values.stream().noneMatch(value -> value < 0));
```

Output:

```text
true
true
true
```

---

## 16. `findFirst()` and `findAny()`

### `findFirst()`

Returns the first matching element.

```java
Optional<Integer> firstEvenNumber = numbers.stream()
        .filter(number -> number % 2 == 0)
        .findFirst();
```

Process the value safely:

```java
firstEvenNumber.ifPresent(System.out::println);
```

Or provide a default:

```java
int value = firstEvenNumber.orElse(0);
```

### `findAny()`

Returns any matching element.

```java
Optional<Integer> anyEvenNumber = numbers.stream()
        .filter(number -> number % 2 == 0)
        .findAny();
```

In a normal sequential stream, it often behaves like `findFirst()`. The difference is more meaningful with parallel streams.

---

## 17. Understanding `Optional`

Methods such as `findFirst()`, `max()`, and `min()` return an `Optional`.

An `Optional` may contain a value or may be empty.

```java
Optional<String> result = names.stream()
        .filter(name -> name.startsWith("Z"))
        .findFirst();
```

Safe approaches:

```java
String name = result.orElse("Not found");
```

```java
result.ifPresent(System.out::println);
```

```java
String name = result.orElseThrow(
        () -> new RuntimeException("Name not found")
);
```

Avoid calling `get()` without checking:

```java
String name = result.get();
```

This can throw `NoSuchElementException` if the optional is empty.

---

## 18. `min()` and `max()`

Find the minimum:

```java
Optional<Integer> minimum = numbers.stream()
        .min(Integer::compareTo);
```

Find the maximum:

```java
Optional<Integer> maximum = numbers.stream()
        .max(Integer::compareTo);
```

Provide default values:

```java
int min = numbers.stream()
        .min(Integer::compareTo)
        .orElse(0);

int max = numbers.stream()
        .max(Integer::compareTo)
        .orElse(0);
```

With custom objects:

```java
Employee highestPaidEmployee = employees.stream()
        .max(Comparator.comparing(Employee::getSalary))
        .orElseThrow();
```

---

## 19. `reduce()`

`reduce()` combines all elements into one result.

### Sum Using `reduce()`

```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5);

int total = numbers.stream()
        .reduce(0, (sum, number) -> sum + number);

System.out.println(total);
```

Output:

```text
15
```

The value `0` is the identity value.

Using a method reference:

```java
int total = numbers.stream()
        .reduce(0, Integer::sum);
```

### Product

```java
int product = numbers.stream()
        .reduce(1, (result, number) -> result * number);
```

### Find the Longest String

```java
List<String> words = List.of("Java", "Spring Boot", "Angular");

String longest = words.stream()
        .reduce("", (first, second) ->
                first.length() >= second.length() ? first : second
        );

System.out.println(longest);
```

Output:

```text
Spring Boot
```

---

## 20. Primitive Streams

Java provides special streams for primitive values:

```text
IntStream
LongStream
DoubleStream
```

These avoid unnecessary boxing and provide useful numeric methods.

```java
List<Integer> numbers = List.of(10, 20, 30, 40);
```

Convert to `IntStream`:

```java
IntStream intStream = numbers.stream()
        .mapToInt(Integer::intValue);
```

Calculate the sum:

```java
int sum = numbers.stream()
        .mapToInt(Integer::intValue)
        .sum();
```

Calculate the average:

```java
double average = numbers.stream()
        .mapToInt(Integer::intValue)
        .average()
        .orElse(0.0);
```

Calculate the maximum:

```java
int max = numbers.stream()
        .mapToInt(Integer::intValue)
        .max()
        .orElse(0);
```

Get numeric statistics:

```java
IntSummaryStatistics statistics = numbers.stream()
        .mapToInt(Integer::intValue)
        .summaryStatistics();

System.out.println("Count: " + statistics.getCount());
System.out.println("Sum: " + statistics.getSum());
System.out.println("Minimum: " + statistics.getMin());
System.out.println("Maximum: " + statistics.getMax());
System.out.println("Average: " + statistics.getAverage());
```

---

## 21. Collecting Stream Results

### `toList()`

```java
List<String> result = names.stream()
        .filter(name -> name.length() > 4)
        .toList();
```

In modern Java, `Stream.toList()` returns an unmodifiable list.

This will normally fail:

```java
result.add("New Name");
```

### `Collectors.toList()`

```java
List<String> result = names.stream()
        .filter(name -> name.length() > 4)
        .collect(Collectors.toList());
```

When you specifically need an `ArrayList`:

```java
List<String> result = names.stream()
        .filter(name -> name.length() > 4)
        .collect(Collectors.toCollection(ArrayList::new));
```

### Collect to a Set

```java
Set<String> result = names.stream()
        .collect(Collectors.toSet());
```

### Join Strings

```java
String joinedNames = names.stream()
        .collect(Collectors.joining(", "));

System.out.println(joinedNames);
```

Output:

```text
Amal, Nimal, Kamal
```

With prefix and suffix:

```java
String joinedNames = names.stream()
        .collect(Collectors.joining(", ", "[", "]"));
```

Output:

```text
[Amal, Nimal, Kamal]
```

---

## 22. Working with Custom Objects

Consider this `Employee` class:

```java
public class Employee {

    private final int id;
    private final String name;
    private final String department;
    private final double salary;
    private final int age;

    public Employee(
            int id,
            String name,
            String department,
            double salary,
            int age
    ) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}
```

Create employee data:

```java
List<Employee> employees = List.of(
        new Employee(1, "Amal", "IT", 120000, 30),
        new Employee(2, "Nimal", "Finance", 90000, 38),
        new Employee(3, "Kamal", "IT", 150000, 42),
        new Employee(4, "Sunil", "HR", 80000, 28),
        new Employee(5, "Anusha", "Finance", 110000, 35)
);
```

---

## 23. Filter Custom Objects

Find IT employees:

```java
List<Employee> itEmployees = employees.stream()
        .filter(employee -> employee.getDepartment().equals("IT"))
        .toList();
```

Find employees earning more than 100,000:

```java
List<Employee> highEarners = employees.stream()
        .filter(employee -> employee.getSalary() > 100000)
        .toList();
```

Multiple conditions:

```java
List<Employee> result = employees.stream()
        .filter(employee -> employee.getDepartment().equals("IT"))
        .filter(employee -> employee.getSalary() > 130000)
        .toList();
```

---

## 24. Extract Fields with `map()`

Get all employee names:

```java
List<String> employeeNames = employees.stream()
        .map(Employee::getName)
        .toList();

System.out.println(employeeNames);
```

Output:

```text
[Amal, Nimal, Kamal, Sunil, Anusha]
```

Get unique departments:

```java
List<String> departments = employees.stream()
        .map(Employee::getDepartment)
        .distinct()
        .toList();
```

---

## 25. Sort Custom Objects

Sort by salary:

```java
List<Employee> sortedBySalary = employees.stream()
        .sorted(Comparator.comparing(Employee::getSalary))
        .toList();
```

Sort by salary descending:

```java
List<Employee> sortedBySalaryDescending = employees.stream()
        .sorted(Comparator.comparing(Employee::getSalary).reversed())
        .toList();
```

Sort by department and then by name:

```java
List<Employee> sortedEmployees = employees.stream()
        .sorted(
                Comparator.comparing(Employee::getDepartment)
                        .thenComparing(Employee::getName)
        )
        .toList();
```

---

## 26. Sum and Average Custom Object Values

Total salary:

```java
double totalSalary = employees.stream()
        .mapToDouble(Employee::getSalary)
        .sum();
```

Average salary:

```java
double averageSalary = employees.stream()
        .mapToDouble(Employee::getSalary)
        .average()
        .orElse(0.0);
```

Average age of IT employees:

```java
double averageAge = employees.stream()
        .filter(employee -> employee.getDepartment().equals("IT"))
        .mapToInt(Employee::getAge)
        .average()
        .orElse(0.0);
```

---

## 27. `groupingBy()`

`groupingBy()` groups elements based on a property.

```java
Map<String, List<Employee>> employeesByDepartment =
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
```

Print each group:

```java
employeesByDepartment.forEach((department, employeeList) -> {
    System.out.println(department);
    employeeList.forEach(System.out::println);
});
```

---

## 28. Count Elements in Each Group

```java
Map<String, Long> employeeCountByDepartment =
        employees.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.counting()
                        )
                );

System.out.println(employeeCountByDepartment);
```

Possible output:

```text
{Finance=2, HR=1, IT=2}
```

---

## 29. Sum Values in Each Group

Calculate total salary by department:

```java
Map<String, Double> totalSalaryByDepartment =
        employees.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.summingDouble(Employee::getSalary)
                        )
                );
```

Calculate average salary by department:

```java
Map<String, Double> averageSalaryByDepartment =
        employees.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.averagingDouble(Employee::getSalary)
                        )
                );
```

---

## 30. Transform Values While Grouping

Get employee names grouped by department:

```java
Map<String, List<String>> namesByDepartment =
        employees.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.mapping(
                                        Employee::getName,
                                        Collectors.toList()
                                )
                        )
                );
```

Possible output:

```text
{
  IT=[Amal, Kamal],
  Finance=[Nimal, Anusha],
  HR=[Sunil]
}
```

---

## 31. `partitioningBy()`

`partitioningBy()` divides elements into two groups: `true` and `false`.

```java
Map<Boolean, List<Employee>> partitionedEmployees =
        employees.stream()
                .collect(
                        Collectors.partitioningBy(
                                employee -> employee.getSalary() >= 100000
                        )
                );
```

High earners:

```java
List<Employee> highEarners = partitionedEmployees.get(true);
```

Other employees:

```java
List<Employee> otherEmployees = partitionedEmployees.get(false);
```

Use `groupingBy()` when there can be many groups. Use `partitioningBy()` when there are exactly two groups.

---

## 32. Convert a Stream to a Map

Create a map using employee ID as the key:

```java
Map<Integer, Employee> employeeMap =
        employees.stream()
                .collect(
                        Collectors.toMap(
                                Employee::getId,
                                employee -> employee
                        )
                );
```

Using `Function.identity()`:

```java
Map<Integer, Employee> employeeMap =
        employees.stream()
                .collect(
                        Collectors.toMap(
                                Employee::getId,
                                Function.identity()
                        )
                );
```

Create an ID-to-name map:

```java
Map<Integer, String> employeeNames =
        employees.stream()
                .collect(
                        Collectors.toMap(
                                Employee::getId,
                                Employee::getName
                        )
                );
```

---

## 33. Handling Duplicate Keys in `toMap()`

If several employees belong to the same department, this throws an exception:

```java
Map<String, Employee> employeeByDepartment =
        employees.stream()
                .collect(
                        Collectors.toMap(
                                Employee::getDepartment,
                                Function.identity()
                        )
                );
```

Provide a merge function:

```java
Map<String, Employee> highestPaidEmployeeByDepartment =
        employees.stream()
                .collect(
                        Collectors.toMap(
                                Employee::getDepartment,
                                Function.identity(),
                                (first, second) ->
                                        first.getSalary() >= second.getSalary()
                                                ? first
                                                : second
                        )
                );
```

---

## 34. `flatMap()`

`flatMap()` is used when each input element contains multiple nested elements.

```java
List<List<String>> nestedNames = List.of(
        List.of("Amal", "Nimal"),
        List.of("Kamal", "Sunil"),
        List.of("Anusha")
);
```

Using `flatMap()`:

```java
List<String> allNames = nestedNames.stream()
        .flatMap(List::stream)
        .toList();

System.out.println(allNames);
```

Output:

```text
[Amal, Nimal, Kamal, Sunil, Anusha]
```

Conceptually:

```text
Stream<List<String>> -> Stream<String>
```

---

## 35. `flatMap()` with Custom Objects

```java
public class Student {

    private final String name;
    private final List<String> subjects;

    public Student(String name, List<String> subjects) {
        this.name = name;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public List<String> getSubjects() {
        return subjects;
    }
}
```

Data:

```java
List<Student> students = List.of(
        new Student("Amal", List.of("Java", "Python")),
        new Student("Nimal", List.of("Java", "Database")),
        new Student("Kamal", List.of("Angular", "Database"))
);
```

Find all unique subjects:

```java
List<String> subjects = students.stream()
        .flatMap(student -> student.getSubjects().stream())
        .distinct()
        .sorted()
        .toList();

System.out.println(subjects);
```

Output:

```text
[Angular, Database, Java, Python]
```

---

## 36. `map()` vs `flatMap()`

Using `map()`:

```java
List<List<String>> result = values.stream()
        .map(list -> list)
        .toList();
```

Result:

```text
[[A, B], [C, D]]
```

Using `flatMap()`:

```java
List<String> result = values.stream()
        .flatMap(List::stream)
        .toList();
```

Result:

```text
[A, B, C, D]
```

Use `map()` for one-to-one transformation.

Use `flatMap()` for one-to-many transformation followed by flattening.

---

## 37. Lazy Evaluation

Intermediate stream operations are lazy.

This code does not print anything:

```java
Stream<Integer> stream = numbers.stream()
        .filter(number -> {
            System.out.println("Checking " + number);
            return number % 2 == 0;
        });
```

Once you add a terminal operation:

```java
List<Integer> result = numbers.stream()
        .filter(number -> {
            System.out.println("Checking " + number);
            return number % 2 == 0;
        })
        .toList();
```

The filtering operation executes.

---

## 38. Short-Circuit Operations

Some operations stop processing early:

```text
findFirst()
findAny()
anyMatch()
noneMatch()
allMatch()
limit()
```

Example:

```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

Optional<Integer> result = numbers.stream()
        .peek(number -> System.out.println("Processing " + number))
        .filter(number -> number % 2 == 0)
        .findFirst();
```

Output:

```text
Processing 1
Processing 2
```

---

## 39. `peek()`

`peek()` allows you to inspect values during stream processing.

```java
List<Integer> result = numbers.stream()
        .peek(number -> System.out.println("Before filter: " + number))
        .filter(number -> number % 2 == 0)
        .peek(number -> System.out.println("After filter: " + number))
        .map(number -> number * 10)
        .peek(number -> System.out.println("After map: " + number))
        .toList();
```

`peek()` is mainly useful for debugging.

Avoid using it for important business logic or modifying objects.

---

## 40. Streams Cannot Be Reused

Incorrect:

```java
Stream<String> stream = names.stream();

long count = stream.count();

List<String> result = stream.toList();
```

This throws:

```text
IllegalStateException: stream has already been operated upon or closed
```

Correct:

```java
long count = names.stream().count();

List<String> result = names.stream().toList();
```

---

## 41. Order of Stream Operations Matters

```java
List<Integer> numbers = List.of(5, 1, 8, 2, 9, 3);
```

Sort every element and then select two:

```java
List<Integer> result = numbers.stream()
        .sorted()
        .limit(2)
        .toList();
```

Result:

```text
[1, 2]
```

Select the first two and then sort:

```java
List<Integer> result = numbers.stream()
        .limit(2)
        .sorted()
        .toList();
```

Result:

```text
[1, 5]
```

Filtering early is often more efficient:

```java
employees.stream()
        .filter(employee -> employee.getDepartment().equals("IT"))
        .sorted(Comparator.comparing(Employee::getSalary))
        .toList();
```

---

## 42. Handling `null` Values

Streams do not automatically ignore `null`.

```java
List<String> names = Arrays.asList(
        "Amal",
        null,
        "Nimal",
        null,
        "Kamal"
);
```

This may throw `NullPointerException`:

```java
names.stream()
        .map(String::toUpperCase)
        .toList();
```

Filter out `null` values first:

```java
List<String> result = names.stream()
        .filter(Objects::nonNull)
        .map(String::toUpperCase)
        .toList();
```

Keep only null values:

```java
List<String> nullValues = names.stream()
        .filter(Objects::isNull)
        .toList();
```

---

## 43. Nested Null Values

Suppose an employee's department can be `null`.

Avoid:

```java
.filter(employee -> employee.getDepartment().equals("IT"))
```

Use:

```java
.filter(employee -> "IT".equals(employee.getDepartment()))
```

Calling `equals()` on the non-null constant is safe.

---

## 44. Parallel Streams

A parallel stream processes elements using multiple threads.

```java
numbers.parallelStream()
        .map(number -> number * 2)
        .forEach(System.out::println);
```

You can also write:

```java
numbers.stream()
        .parallel()
        .forEach(System.out::println);
```

To preserve encounter order:

```java
numbers.parallelStream()
        .forEachOrdered(System.out::println);
```

Parallel streams may help when:

- The collection is large.
- Each operation is computationally expensive.
- Operations are independent.
- The task is CPU-bound.
- Performance has been measured.

Avoid them when:

- The collection is small.
- Operations perform database or network calls.
- The code modifies shared mutable state.
- Processing order matters.
- You have not measured a real performance benefit.

---

## 45. Side Effects and Shared State

Unsafe with parallel streams:

```java
List<Integer> result = new ArrayList<>();

numbers.parallelStream()
        .filter(number -> number % 2 == 0)
        .forEach(result::add);
```

Prefer:

```java
List<Integer> result = numbers.parallelStream()
        .filter(number -> number % 2 == 0)
        .toList();
```

Stream functions should ideally be:

- Stateless
- Non-interfering
- Free from unexpected side effects

---

## 46. Practical Employee Examples

### Find the Highest-Paid IT Employee

```java
Employee highestPaidItEmployee = employees.stream()
        .filter(employee -> "IT".equals(employee.getDepartment()))
        .max(Comparator.comparing(Employee::getSalary))
        .orElseThrow(
                () -> new IllegalArgumentException("No IT employees found")
        );
```

### Get Names of Employees Older Than 30

```java
List<String> names = employees.stream()
        .filter(employee -> employee.getAge() > 30)
        .map(Employee::getName)
        .toList();
```

### Get the Three Highest-Paid Employees

```java
List<Employee> topThree = employees.stream()
        .sorted(Comparator.comparing(Employee::getSalary).reversed())
        .limit(3)
        .toList();
```

### Check Whether Any Employee Earns More Than 140,000

```java
boolean result = employees.stream()
        .anyMatch(employee -> employee.getSalary() > 140000);
```

### Check Whether Every Employee Is an Adult

```java
boolean allAdults = employees.stream()
        .allMatch(employee -> employee.getAge() >= 18);
```

### Get Unique Department Names Alphabetically

```java
List<String> departments = employees.stream()
        .map(Employee::getDepartment)
        .filter(Objects::nonNull)
        .distinct()
        .sorted()
        .toList();
```

### Calculate Total Salary for IT Employees

```java
double totalItSalary = employees.stream()
        .filter(employee -> "IT".equals(employee.getDepartment()))
        .mapToDouble(Employee::getSalary)
        .sum();
```

---

## 47. Complete Runnable Example

```java
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamLesson {

    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new Employee(1, "Amal", "IT", 120000, 30),
                new Employee(2, "Nimal", "Finance", 90000, 38),
                new Employee(3, "Kamal", "IT", 150000, 42),
                new Employee(4, "Sunil", "HR", 80000, 28),
                new Employee(5, "Anusha", "Finance", 110000, 35)
        );

        List<Employee> itEmployees = employees.stream()
                .filter(employee -> "IT".equals(employee.getDepartment()))
                .toList();

        System.out.println("IT employees:");
        itEmployees.forEach(System.out::println);

        List<String> employeeNames = employees.stream()
                .map(Employee::getName)
                .toList();

        System.out.println("\nEmployee names:");
        System.out.println(employeeNames);

        List<Employee> sortedBySalary = employees.stream()
                .sorted(
                        Comparator.comparing(Employee::getSalary)
                                .reversed()
                )
                .toList();

        System.out.println("\nEmployees sorted by salary:");
        sortedBySalary.forEach(System.out::println);

        double totalSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();

        System.out.println("\nTotal salary: " + totalSalary);

        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);

        System.out.println("Average salary: " + averageSalary);

        Employee highestPaidEmployee = employees.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow();

        System.out.println("\nHighest-paid employee:");
        System.out.println(highestPaidEmployee);

        Map<String, List<Employee>> employeesByDepartment =
                employees.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Employee::getDepartment
                                )
                        );

        System.out.println("\nEmployees grouped by department:");
        employeesByDepartment.forEach((department, employeeList) -> {
            System.out.println(department + ": " + employeeList);
        });

        Map<String, Long> employeeCountByDepartment =
                employees.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Employee::getDepartment,
                                        Collectors.counting()
                                )
                        );

        System.out.println("\nEmployee count by department:");
        System.out.println(employeeCountByDepartment);

        Map<String, Double> averageSalaryByDepartment =
                employees.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Employee::getDepartment,
                                        Collectors.averagingDouble(
                                                Employee::getSalary
                                        )
                                )
                        );

        System.out.println("\nAverage salary by department:");
        System.out.println(averageSalaryByDepartment);
    }

    private static class Employee {

        private final int id;
        private final String name;
        private final String department;
        private final double salary;
        private final int age;

        public Employee(
                int id,
                String name,
                String department,
                double salary,
                int age
        ) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", department='" + department + '\'' +
                    ", salary=" + salary +
                    ", age=" + age +
                    '}';
        }
    }
}
```

---

## 48. Stream Examples in Spring Boot

Suppose your service receives a list of entities:

```java
List<User> users = userRepository.findAll();
```

### Convert Entities to DTOs

```java
List<UserResponse> responses = users.stream()
        .map(user -> new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        ))
        .toList();
```

You can move the conversion to a method:

```java
private UserResponse toResponse(User user) {
    return new UserResponse(
            user.getId(),
            user.getName(),
            user.getEmail()
    );
}
```

Then use a method reference:

```java
List<UserResponse> responses = users.stream()
        .map(this::toResponse)
        .toList();
```

### Filter Active Users

```java
List<UserResponse> activeUsers = users.stream()
        .filter(User::isActive)
        .map(this::toResponse)
        .toList();
```

### Find a User by ID

For database lookup, prefer the repository:

```java
User user = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id));
```

For an existing in-memory list:

```java
User user = users.stream()
        .filter(existingUser -> existingUser.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new UserNotFoundException(id));
```

---

## 49. Common Mistakes

### Mistake 1: Forgetting the Terminal Operation

This does not fully process the stream:

```java
numbers.stream()
        .filter(number -> number > 10);
```

Add a terminal operation:

```java
List<Integer> result = numbers.stream()
        .filter(number -> number > 10)
        .toList();
```

### Mistake 2: Reusing a Stream

Incorrect:

```java
Stream<Integer> stream = numbers.stream();

stream.count();
stream.toList();
```

Correct:

```java
numbers.stream().count();
numbers.stream().toList();
```

### Mistake 3: Modifying the Source During Streaming

Avoid:

```java
numbers.stream()
        .forEach(numbers::remove);
```

### Mistake 4: Using Streams for Complicated Procedural Logic

When the pipeline becomes difficult to understand, split it into methods or use a normal loop.

### Mistake 5: Using `parallelStream()` Without Measuring

Parallel processing adds overhead and complexity. Use it only after performance testing.

### Mistake 6: Using `Optional.get()`

Avoid:

```java
Employee employee = optionalEmployee.get();
```

Prefer:

```java
Employee employee = optionalEmployee.orElseThrow();
```

### Mistake 7: Using `map()` When `flatMap()` Is Needed

If the result becomes nested:

```text
List<List<String>>
```

you may need:

```java
flatMap(List::stream)
```

---

## 50. Important Stream Operations Summary

| Operation | Purpose | Type |
|---|---|---|
| `filter()` | Keep matching elements | Intermediate |
| `map()` | Transform elements | Intermediate |
| `flatMap()` | Transform and flatten nested data | Intermediate |
| `sorted()` | Sort elements | Intermediate |
| `distinct()` | Remove duplicates | Intermediate |
| `limit()` | Keep first N elements | Intermediate |
| `skip()` | Skip first N elements | Intermediate |
| `peek()` | Inspect elements while debugging | Intermediate |
| `forEach()` | Perform an action | Terminal |
| `toList()` | Create a list | Terminal |
| `collect()` | Collect into a structure | Terminal |
| `count()` | Count elements | Terminal |
| `reduce()` | Combine elements | Terminal |
| `findFirst()` | Find first element | Terminal |
| `findAny()` | Find any element | Terminal |
| `anyMatch()` | Check whether any match | Terminal |
| `allMatch()` | Check whether all match | Terminal |
| `noneMatch()` | Check whether none match | Terminal |
| `min()` | Find minimum | Terminal |
| `max()` | Find maximum | Terminal |

---

## 51. How to Read a Stream Pipeline

Consider:

```java
List<String> result = employees.stream()
        .filter(employee -> employee.getSalary() >= 100000)
        .sorted(Comparator.comparing(Employee::getSalary).reversed())
        .map(Employee::getName)
        .limit(3)
        .toList();
```

Read it as:

> From the employee list, keep employees earning at least 100,000, sort them by salary from highest to lowest, extract their names, keep the first three, and return them as a list.

This is the main benefit of streams: they describe **what should happen** rather than manually controlling every iteration step.

---

## 52. Practice Questions

Using the `Employee` list, write streams that:

1. Find all Finance employees.
2. Find employees younger than 35.
3. Return the names of IT employees.
4. Sort employees by age.
5. Find the employee with the lowest salary.
6. Calculate the average age.
7. Count employees in each department.
8. Find the highest salary in each department.
9. Group employee names by department.
10. Check whether any employee earns more than 200,000.

Example answer for question 1:

```java
List<Employee> financeEmployees = employees.stream()
        .filter(employee -> "Finance".equals(employee.getDepartment()))
        .toList();
```

Example answer for question 8:

```java
Map<String, Optional<Employee>> highestPaidByDepartment =
        employees.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.maxBy(
                                        Comparator.comparing(
                                                Employee::getSalary
                                        )
                                )
                        )
                );
```

A version without `Optional` values:

```java
Map<String, Employee> highestPaidByDepartment =
        employees.stream()
                .collect(
                        Collectors.toMap(
                                Employee::getDepartment,
                                employee -> employee,
                                (first, second) ->
                                        first.getSalary() >= second.getSalary()
                                                ? first
                                                : second
                        )
                );
```

---

## Key Operations to Master First

```text
filter()
map()
sorted()
toList()
findFirst()
anyMatch()
mapToInt()
mapToDouble()
groupingBy()
flatMap()
reduce()
```
xShhhhhhhhhhhhhhhhhhhH

[← Previous: 30. Functional Interfaces](30-functional-interfaces.md) | [Main Index](../main.md) | [Next: 32. Optional →](32-optional.md)
