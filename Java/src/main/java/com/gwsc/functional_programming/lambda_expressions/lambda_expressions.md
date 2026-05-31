#### [<-Back](../functional_programming.md)  


# Java Lambda Functions - Comprehensive Notes with Java Examples

## Code
- [Foreach function](ForEach.java)
- 
## 1. Introduction

A **lambda function** in Java is a short way to write an anonymous function.

Lambda functions were introduced in **Java 8**. They allow Java programs to use functional programming features, especially when working with collections, streams, sorting, filtering, and event handling.

In simple words:

> A lambda function is a function without a name.

Example:

```java
(a, b) -> a + b
```

This lambda takes two values, `a` and `b`, and returns their sum.

---

## 2. Why Lambda Functions Are Used

Lambda functions are used to:

* Reduce boilerplate code
* Make code shorter and cleaner
* Improve readability
* Pass behavior as an argument
* Work with functional interfaces
* Process collections easily
* Use the Stream API
* Write functional-style Java code

---

## 3. Basic Lambda Syntax

The basic syntax of a lambda expression is:

```java
(parameters) -> expression
```

or:

```java
(parameters) -> {
    statements;
}
```

### Example

```java
(int a, int b) -> a + b
```

This means:

```text
Take two integer values a and b, then return a + b.
```

---

## 4. Parts of a Lambda Expression

A lambda expression has three main parts:

```java
(parameters) -> body
```

Example:

```java
(a, b) -> a + b
```

| Part     | Description           |
| -------- | --------------------- |
| `(a, b)` | Parameters            |
| `->`     | Lambda arrow operator |
| `a + b`  | Lambda body           |

---

## 5. Simple Lambda Examples

### 5.1 Lambda with No Parameter

```java
() -> System.out.println("Hello Java");
```

This lambda does not take any input.

---

### 5.2 Lambda with One Parameter

```java
name -> System.out.println(name);
```

For one parameter, parentheses are optional.

This is also valid:

```java
(name) -> System.out.println(name);
```

---

### 5.3 Lambda with Multiple Parameters

```java
(a, b) -> a + b
```

For multiple parameters, parentheses are required.

---

### 5.4 Lambda with Multiple Statements

```java
(a, b) -> {
    int sum = a + b;
    return sum;
}
```

When the body has multiple statements, curly braces `{}` are required.

---

## 6. Before Lambda vs With Lambda

Before Java 8, anonymous classes were commonly used.

### Before Lambda

```java
public class Main {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Running...");
            }
        };

        runnable.run();
    }
}
```

### With Lambda

```java
public class Main {
    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("Running...");

        runnable.run();
    }
}
```

### Output

```text
Running...
```

The lambda version is shorter and easier to read.

---

## 7. Functional Interface

A lambda expression can only be used with a **functional interface**.

A **functional interface** is an interface that has exactly **one abstract method**.

Example:

```java
@FunctionalInterface
interface Calculator {
    int add(int a, int b);
}
```

Using lambda:

```java
public class Main {
    public static void main(String[] args) {
        Calculator calculator = (a, b) -> a + b;

        System.out.println(calculator.add(10, 20));
    }
}
```

### Output

```text
30
```

---

## 8. Complete Example Using Custom Functional Interface

```java
@FunctionalInterface
interface Greeting {
    void sayHello(String name);
}

public class Main {
    public static void main(String[] args) {
        Greeting greeting = name -> System.out.println("Hello " + name);

        greeting.sayHello("John");
    }
}
```

### Output

```text
Hello John
```

---

## 9. Why Lambda Needs a Functional Interface

Java is a strongly typed language. A lambda expression needs a target type.

Example:

```java
Calculator calculator = (a, b) -> a + b;
```

Java understands that:

* `a` is an integer
* `b` is an integer
* the return type is integer

because the functional interface method is:

```java
int add(int a, int b);
```

Without a functional interface, Java does not know the type of the lambda expression.

---

## 10. The `@FunctionalInterface` Annotation

The `@FunctionalInterface` annotation tells Java that the interface should contain only one abstract method.

Example:

```java
@FunctionalInterface
interface Message {
    void printMessage();
}
```

Invalid example:

```java
@FunctionalInterface
interface Message {
    void printMessage();
    void showMessage();
}
```

This is invalid because it has two abstract methods.

---

## 11. Built-in Functional Interfaces in Java

Java provides built-in functional interfaces in the package:

```java
java.util.function
```

Common built-in functional interfaces are:

| Interface             | Method     | Description                             |
| --------------------- | ---------- | --------------------------------------- |
| `Predicate<T>`        | `test()`   | Takes one input and returns boolean     |
| `Consumer<T>`         | `accept()` | Takes one input and returns nothing     |
| `Supplier<T>`         | `get()`    | Takes no input and returns a value      |
| `Function<T, R>`      | `apply()`  | Takes one input and returns one output  |
| `BiFunction<T, U, R>` | `apply()`  | Takes two inputs and returns one output |
| `UnaryOperator<T>`    | `apply()`  | Takes one input and returns same type   |
| `BinaryOperator<T>`   | `apply()`  | Takes two inputs and returns same type  |

---

## 12. `Predicate` Example

`Predicate` is used to check a condition.

It returns `true` or `false`.

```java
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Predicate<Integer> isEven = number -> number % 2 == 0;

        System.out.println(isEven.test(10));
        System.out.println(isEven.test(7));
    }
}
```

### Output

```text
true
false
```

---

## 13. `Consumer` Example

`Consumer` takes an input but does not return anything.

```java
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Consumer<String> printer = name -> System.out.println(name);

        printer.accept("Java Lambda");
    }
}
```

### Output

```text
Java Lambda
```

---

## 14. `Supplier` Example

`Supplier` does not take input, but returns a value.

```java
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Supplier<String> message = () -> "Hello from Supplier";

        System.out.println(message.get());
    }
}
```

### Output

```text
Hello from Supplier
```

---

## 15. `Function` Example

`Function` takes one input and returns one output.

```java
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<String, Integer> getLength = text -> text.length();

        System.out.println(getLength.apply("Lambda"));
    }
}
```

### Output

```text
6
```

---

## 16. `BiFunction` Example

`BiFunction` takes two inputs and returns one output.

```java
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

        System.out.println(add.apply(5, 10));
    }
}
```

### Output

```text
15
```

---

## 17. Lambda with Collections

Lambda expressions are commonly used with collections such as `ArrayList`.

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Anne", "Peter");

        names.forEach(name -> System.out.println(name));
    }
}
```

### Output

```text
John
Anne
Peter
```

The lambda expression is:

```java
name -> System.out.println(name)
```

---

## 18. Lambda with Sorting

Lambda expressions are very useful for sorting.

### Sort Numbers in Ascending Order

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 2, 9, 1);

        numbers.sort((a, b) -> Integer.compare(a, b));

        System.out.println(numbers);
    }
}
```

### Output

```text
[1, 2, 5, 9]
```

---

### Sort Numbers in Descending Order

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 2, 9, 1);

        numbers.sort((a, b) -> Integer.compare(b, a));

        System.out.println(numbers);
    }
}
```

### Output

```text
[9, 5, 2, 1]
```

---

## 19. Lambda with Objects

Example: Sort students by age.

```java
import java.util.ArrayList;
import java.util.List;

class Student {
    String name;
    int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        students.add(new Student("John", 22));
        students.add(new Student("Anne", 20));
        students.add(new Student("Peter", 25));

        students.sort((s1, s2) -> Integer.compare(s1.age, s2.age));

        for (Student student : students) {
            System.out.println(student.name + " - " + student.age);
        }
    }
}
```

### Output

```text
Anne - 20
John - 22
Peter - 25
```

---

## 20. Lambda with Stream API

The Stream API is one of the most common places where lambda expressions are used.

A stream allows us to process collections in a functional style.

Common stream methods include:

* `filter()`
* `map()`
* `forEach()`
* `reduce()`
* `sorted()`
* `collect()`

---

## 21. Filtering with Lambda

Example: Get only even numbers.

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

### Output

```text
[10, 20, 30]
```

Here, the lambda expression is:

```java
number -> number % 2 == 0
```

---

## 22. Mapping with Lambda

Example: Convert names to uppercase.

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

### Output

```text
[JOHN, ANNE, PETER]
```

---

## 23. Reducing with Lambda

`reduce()` combines values into one result.

Example: Find the sum of numbers.

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);

        System.out.println(sum);
    }
}
```

### Output

```text
15
```

---

## 24. Sorting with Stream and Lambda

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Peter", "Anne", "John");

        List<String> sortedNames = names.stream()
                .sorted((name1, name2) -> name1.compareTo(name2))
                .collect(Collectors.toList());

        System.out.println(sortedNames);
    }
}
```

### Output

```text
[Anne, John, Peter]
```

---

## 25. Lambda and Method References

Sometimes a lambda expression only calls an existing method.

Example:

```java
names.forEach(name -> System.out.println(name));
```

This can be replaced with a method reference:

```java
names.forEach(System.out::println);
```

Both do the same thing.

---

## 26. Types of Method References

### 26.1 Static Method Reference

```java
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<String, Integer> converter = Integer::parseInt;

        System.out.println(converter.apply("100"));
    }
}
```

### Output

```text
100
```

This is the same as:

```java
Function<String, Integer> converter = text -> Integer.parseInt(text);
```

---

### 26.2 Instance Method Reference

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Anne", "Peter");

        names.forEach(System.out::println);
    }
}
```

### Output

```text
John
Anne
Peter
```

---

### 26.3 Constructor Reference

```java
import java.util.ArrayList;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Supplier<ArrayList<String>> listSupplier = ArrayList::new;

        ArrayList<String> names = listSupplier.get();
        names.add("Java");

        System.out.println(names);
    }
}
```

### Output

```text
[Java]
```

This is the same as:

```java
Supplier<ArrayList<String>> listSupplier = () -> new ArrayList<>();
```

---

## 27. Variable Capture in Lambda

A lambda expression can access variables from the surrounding method.

```java
public class Main {
    public static void main(String[] args) {
        String message = "Hello";

        Runnable runnable = () -> System.out.println(message);

        runnable.run();
    }
}
```

### Output

```text
Hello
```

However, the variable must be **final** or **effectively final**.

---

## 28. Final and Effectively Final Variables

### Final Variable

```java
final int number = 10;
```

A final variable cannot be changed.

---

### Effectively Final Variable

```java
int number = 10;
```

If the value is not changed later, Java treats it as effectively final.

This is valid:

```java
public class Main {
    public static void main(String[] args) {
        int number = 10;

        Runnable runnable = () -> System.out.println(number);

        runnable.run();
    }
}
```

This is invalid:

```java
public class Main {
    public static void main(String[] args) {
        int number = 10;

        Runnable runnable = () -> System.out.println(number);

        number = 20; // Error
    }
}
```

The error happens because `number` is changed after being used inside the lambda.

---

## 29. Lambda with Threads

Lambda expressions are commonly used with threads.

### Before Lambda

```java
public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread is running");
            }
        });

        thread.start();
    }
}
```

### With Lambda

```java
public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println("Thread is running"));

        thread.start();
    }
}
```

### Output

```text
Thread is running
```

---

## 30. Lambda vs Anonymous Class

| Anonymous Class                        | Lambda Expression                |
| -------------------------------------- | -------------------------------- |
| More code                              | Less code                        |
| Harder to read                         | Cleaner and shorter              |
| Can have fields and additional methods | Mainly represents behavior       |
| Uses class-like syntax                 | Uses function-like syntax        |
| Works with classes/interfaces          | Works with functional interfaces |

### Anonymous Class Example

```java
Runnable runnable = new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello");
    }
};
```

### Lambda Example

```java
Runnable runnable = () -> System.out.println("Hello");
```

---

## 31. Lambda Rules

### Rule 1: Lambda Works Only with Functional Interfaces

Valid:

```java
@FunctionalInterface
interface MyInterface {
    void show();
}
```

Invalid:

```java
interface MyInterface {
    void show();
    void display();
}
```

The invalid example has two abstract methods.

---

### Rule 2: Parentheses Are Optional for One Parameter

Valid:

```java
name -> System.out.println(name);
```

Also valid:

```java
(name) -> System.out.println(name);
```

For zero parameters, parentheses are required:

```java
() -> System.out.println("Hello");
```

For multiple parameters, parentheses are required:

```java
(a, b) -> a + b
```

---

### Rule 3: Return Keyword Is Optional for Single Expression

Valid:

```java
(a, b) -> a + b
```

If curly braces are used, `return` is required when returning a value:

```java
(a, b) -> {
    return a + b;
}
```

---

### Rule 4: Parameter Types Are Usually Optional

Valid:

```java
(a, b) -> a + b
```

Also valid:

```java
(int a, int b) -> a + b
```

Java can usually infer the parameter types.

---

## 32. Practical Example: Employee Filtering

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Employee {
    String name;
    int salary;

    Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
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

        List<Employee> highSalaryEmployees = employees.stream()
                .filter(employee -> employee.salary > 50000)
                .collect(Collectors.toList());

        highSalaryEmployees.forEach(employee ->
                System.out.println(employee.name + " - " + employee.salary)
        );
    }
}
```

### Output

```text
Anne - 70000
Mary - 90000
```

---

## 33. Practical Example: Product Discount

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", 1000),
                new Product("Mouse", 50),
                new Product("Keyboard", 100)
        );

        List<Double> discountedPrices = products.stream()
                .map(product -> product.price * 0.9)
                .collect(Collectors.toList());

        System.out.println(discountedPrices);
    }
}
```

### Output

```text
[900.0, 45.0, 90.0]
```

---

## 34. Practical Example: Count Names Starting with A

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Anne", "Alex", "John", "Peter", "Amanda");

        long count = names.stream()
                .filter(name -> name.startsWith("A"))
                .count();

        System.out.println(count);
    }
}
```

### Output

```text
3
```

---

## 35. Advantages of Lambda Functions

### 35.1 Less Code

Lambda expressions reduce unnecessary boilerplate code.

### 35.2 Better Readability

Simple operations become easier to understand.

### 35.3 Supports Functional Programming

Lambda expressions allow Java to use functional programming concepts.

### 35.4 Useful with Streams

Streams and lambdas work very well together.

### 35.5 Easy Collection Processing

Filtering, mapping, sorting, and reducing data becomes easier.

---

## 36. Disadvantages of Lambda Functions

### 36.1 Can Reduce Readability if Overused

Very long lambda expressions can be hard to understand.

Bad example:

```java
list.stream().filter(x -> x > 10).map(x -> x * 2).filter(x -> x < 50).forEach(x -> System.out.println(x));
```

Better formatting:

```java
list.stream()
        .filter(x -> x > 10)
        .map(x -> x * 2)
        .filter(x -> x < 50)
        .forEach(System.out::println);
```

---

### 36.2 Debugging Can Be Harder

Errors inside lambda expressions can sometimes be harder to debug.

---

### 36.3 Not Suitable for Complex Logic

If the logic is complex, it is better to write a separate method.

---

## 37. Best Practices

### 37.1 Keep Lambdas Short

Good:

```java
name -> name.toUpperCase()
```

Avoid writing long business logic inside lambda expressions.

---

### 37.2 Use Meaningful Parameter Names

Less clear:

```java
x -> x.getSalary()
```

Better:

```java
employee -> employee.getSalary()
```

---

### 37.3 Use Method References When Possible

Instead of:

```java
names.forEach(name -> System.out.println(name));
```

Use:

```java
names.forEach(System.out::println);
```

---

### 37.4 Move Complex Logic to Separate Methods

Instead of:

```java
employees.stream()
        .filter(employee -> employee.salary > 50000 && employee.name.startsWith("A"));
```

Use:

```java
employees.stream()
        .filter(employee -> isHighSalaryEmployee(employee));
```

Then define the method:

```java
private static boolean isHighSalaryEmployee(Employee employee) {
    return employee.salary > 50000 && employee.name.startsWith("A");
}
```

---

## 38. Common Interview Questions

### Question 1: What is a lambda expression in Java?

A lambda expression is a short way to write an anonymous function. It is mainly used to provide implementation for a functional interface.

---

### Question 2: What is a functional interface?

A functional interface is an interface that has exactly one abstract method.

Example:

```java
@FunctionalInterface
interface Calculator {
    int add(int a, int b);
}
```

---

### Question 3: Can lambda expressions be used with any interface?

No. Lambda expressions can only be used with functional interfaces.

---

### Question 4: What is the difference between lambda expression and anonymous class?

A lambda expression is shorter and cleaner. An anonymous class is more verbose. Lambda expressions are mainly used with functional interfaces.

---

### Question 5: What are common functional interfaces in Java?

Common functional interfaces are:

* `Predicate`
* `Consumer`
* `Supplier`
* `Function`
* `BiFunction`
* `UnaryOperator`
* `BinaryOperator`

---

### Question 6: What is the difference between `Predicate` and `Function`?

| Interface        | Purpose                                   |
| ---------------- | ----------------------------------------- |
| `Predicate<T>`   | Takes input and returns `true` or `false` |
| `Function<T, R>` | Takes input and returns another value     |

Example:

```java
Predicate<Integer> isEven = number -> number % 2 == 0;
Function<String, Integer> getLength = text -> text.length();
```

---

### Question 7: What is method reference in Java?

A method reference is a shorter way to write a lambda that calls an existing method.

Example:

```java
names.forEach(System.out::println);
```

This is the same as:

```java
names.forEach(name -> System.out.println(name));
```

---

## 39. Simple Summary

A Java lambda function is a concise way to write an anonymous function.

Basic syntax:

```java
(parameters) -> expression
```

Example:

```java
(a, b) -> a + b
```

Lambda functions are commonly used with:

* Functional interfaces
* Collections
* Stream API
* Sorting
* Filtering
* Mapping
* Reducing
* Threads
* Method references

The main goal of lambda functions is to make Java code shorter, cleaner, and easier to write.

---

## 40. Final Complete Java Example

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Student {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("John", 75),
                new Student("Anne", 90),
                new Student("Peter", 55),
                new Student("Mary", 82)
        );

        List<String> passedStudents = students.stream()
                .filter(student -> student.marks >= 60)
                .sorted((s1, s2) -> Integer.compare(s2.marks, s1.marks))
                .map(student -> student.name)
                .collect(Collectors.toList());

        passedStudents.forEach(System.out::println);
    }
}
```

### Output

```text
Anne
Mary
John
```

### Explanation

In this example:

```java
student -> student.marks >= 60
```

filters students who passed.

```java
(s1, s2) -> Integer.compare(s2.marks, s1.marks)
```

sorts students by marks in descending order.

```java
student -> student.name
```

converts each student object into the student's name.

```java
System.out::println
```

prints each student name.
