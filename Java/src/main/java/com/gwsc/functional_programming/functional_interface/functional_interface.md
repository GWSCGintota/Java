#### [<-Back](../functional_programming.md)
# Java Functional Interface - Comprehensive Notes with Examples

## 1. Introduction

A **functional interface** in Java is an interface that contains exactly **one abstract method**.

Functional interfaces are very important in Java because they are used with:

* Lambda expressions
* Method references
* Stream API
* Functional programming
* Callback-style programming
* Cleaner and shorter code

Functional interfaces were heavily introduced and used from **Java 8** onwards.

---

## 2. What is a Functional Interface?

A functional interface is an interface with only one abstract method.

Example:

```java
@FunctionalInterface
interface Greeting {
    void sayHello();
}
```

Here, `Greeting` is a functional interface because it has only one abstract method:

```java
void sayHello();
```

---

## 3. Simple Functional Interface Example

```java
@FunctionalInterface
interface Message {
    void showMessage();
}

public class Main {
    public static void main(String[] args) {
        Message message = () -> System.out.println("Hello Java");

        message.showMessage();
    }
}
```

### Output

```text
Hello Java
```

### Explanation

The interface `Message` has one abstract method:

```java
void showMessage();
```

The lambda expression provides the implementation:

```java
() -> System.out.println("Hello Java")
```

---

## 4. Why Functional Interfaces Are Used

Functional interfaces are used to represent a single action or behavior.

They allow us to pass behavior as data.

For example, instead of writing a full class to define behavior, we can use a lambda expression.

### Without Lambda

```java
interface Greeting {
    void sayHello();
}

class EnglishGreeting implements Greeting {
    public void sayHello() {
        System.out.println("Hello");
    }
}

public class Main {
    public static void main(String[] args) {
        Greeting greeting = new EnglishGreeting();
        greeting.sayHello();
    }
}
```

### With Lambda

```java
@FunctionalInterface
interface Greeting {
    void sayHello();
}

public class Main {
    public static void main(String[] args) {
        Greeting greeting = () -> System.out.println("Hello");

        greeting.sayHello();
    }
}
```

The lambda version is shorter and cleaner.

---

## 5. The `@FunctionalInterface` Annotation

The `@FunctionalInterface` annotation is used to tell the compiler that an interface should be a functional interface.

Example:

```java
@FunctionalInterface
interface Calculator {
    int add(int a, int b);
}
```

This annotation is optional, but it is recommended.

If we accidentally add more than one abstract method, Java gives a compilation error.

### Invalid Example

```java
@FunctionalInterface
interface Calculator {
    int add(int a, int b);
    int subtract(int a, int b);
}
```

This is invalid because the interface has two abstract methods.

---

## 6. Single Abstract Method Concept

A functional interface is also called a **SAM interface**.

SAM means:

```text
Single Abstract Method
```

Example:

```java
@FunctionalInterface
interface Printer {
    void print(String message);
}
```

This interface has only one abstract method:

```java
void print(String message);
```

Therefore, it is a functional interface.

---

## 7. Functional Interface with Parameters

A functional interface method can have parameters.

```java
@FunctionalInterface
interface Printer {
    void print(String message);
}

public class Main {
    public static void main(String[] args) {
        Printer printer = message -> System.out.println(message);

        printer.print("Learning Functional Interfaces");
    }
}
```

### Output

```text
Learning Functional Interfaces
```

---

## 8. Functional Interface with Return Value

A functional interface method can also return a value.

```java
@FunctionalInterface
interface Square {
    int calculate(int number);
}

public class Main {
    public static void main(String[] args) {
        Square square = number -> number * number;

        System.out.println(square.calculate(5));
    }
}
```

### Output

```text
25
```

---

## 9. Functional Interface with Multiple Parameters

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

public class Main {
    public static void main(String[] args) {
        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;

        System.out.println(add.calculate(10, 5));
        System.out.println(multiply.calculate(10, 5));
    }
}
```

### Output

```text
15
50
```

---

## 10. Functional Interface and Lambda Expression

A lambda expression can only be used where Java expects a functional interface.

Example:

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

Here:

```java
name -> System.out.println("Hello " + name)
```

is the lambda expression.

It provides the implementation for:

```java
void sayHello(String name);
```

---

## 11. Functional Interface and Anonymous Class

Before Java 8, anonymous classes were commonly used.

### Using Anonymous Class

```java
@FunctionalInterface
interface Greeting {
    void sayHello();
}

public class Main {
    public static void main(String[] args) {
        Greeting greeting = new Greeting() {
            @Override
            public void sayHello() {
                System.out.println("Hello using anonymous class");
            }
        };

        greeting.sayHello();
    }
}
```

### Using Lambda

```java
@FunctionalInterface
interface Greeting {
    void sayHello();
}

public class Main {
    public static void main(String[] args) {
        Greeting greeting = () -> System.out.println("Hello using lambda");

        greeting.sayHello();
    }
}
```

Lambda expressions make the code shorter and easier to read.

---

## 12. Can Functional Interfaces Have Default Methods?

Yes. A functional interface can have default methods.

A default method does not count as an abstract method.

Example:

```java
@FunctionalInterface
interface Vehicle {
    void start();

    default void stop() {
        System.out.println("Vehicle stopped");
    }
}
```

This is valid because `Vehicle` has only one abstract method:

```java
void start();
```

Complete example:

```java
@FunctionalInterface
interface Vehicle {
    void start();

    default void stop() {
        System.out.println("Vehicle stopped");
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle car = () -> System.out.println("Car started");

        car.start();
        car.stop();
    }
}
```

### Output

```text
Car started
Vehicle stopped
```

---

## 13. Can Functional Interfaces Have Static Methods?

Yes. A functional interface can have static methods.

Static methods also do not count as abstract methods.

Example:

```java
@FunctionalInterface
interface Utility {
    void process();

    static void showInfo() {
        System.out.println("This is a static method");
    }
}
```

Complete example:

```java
@FunctionalInterface
interface Utility {
    void process();

    static void showInfo() {
        System.out.println("Static method inside functional interface");
    }
}

public class Main {
    public static void main(String[] args) {
        Utility utility = () -> System.out.println("Processing...");

        utility.process();
        Utility.showInfo();
    }
}
```

### Output

```text
Processing...
Static method inside functional interface
```

---

## 14. Can Functional Interfaces Have Methods from Object Class?

Yes. Methods from the `Object` class do not count as abstract methods.

Example:

```java
@FunctionalInterface
interface MyInterface {
    void display();

    String toString();
}
```

This is still valid because `toString()` comes from the `Object` class.

However, this type of design is not commonly needed in beginner-level programming.

---

## 15. Built-in Functional Interfaces in Java

Java provides many built-in functional interfaces in the package:

```java
java.util.function
```

Common built-in functional interfaces include:

| Functional Interface  | Method     | Purpose                                 |
| --------------------- | ---------- | --------------------------------------- |
| `Predicate<T>`        | `test()`   | Takes one input and returns boolean     |
| `Consumer<T>`         | `accept()` | Takes one input and returns nothing     |
| `Supplier<T>`         | `get()`    | Takes no input and returns a value      |
| `Function<T, R>`      | `apply()`  | Takes one input and returns one output  |
| `BiFunction<T, U, R>` | `apply()`  | Takes two inputs and returns one output |
| `UnaryOperator<T>`    | `apply()`  | Takes one input and returns same type   |
| `BinaryOperator<T>`   | `apply()`  | Takes two inputs and returns same type  |

---

## 16. Predicate Functional Interface

`Predicate<T>` is used to test a condition.

It takes one input and returns `true` or `false`.

### Syntax

```java
Predicate<T> predicateName = value -> condition;
```

### Example

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

### Explanation

```java
number -> number % 2 == 0
```

checks whether the number is even.

---

## 17. Consumer Functional Interface

`Consumer<T>` is used when we want to take an input but return nothing.

### Syntax

```java
Consumer<T> consumerName = value -> action;
```

### Example

```java
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Consumer<String> printer = name -> System.out.println(name);

        printer.accept("Java Functional Interface");
    }
}
```

### Output

```text
Java Functional Interface
```

---

## 18. Supplier Functional Interface

`Supplier<T>` is used when we want to return a value without taking any input.

### Syntax

```java
Supplier<T> supplierName = () -> value;
```

### Example

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

## 19. Function Functional Interface

`Function<T, R>` is used when we want to take one input and return one output.

* `T` means input type
* `R` means return type

### Syntax

```java
Function<T, R> functionName = input -> output;
```

### Example

```java
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<String, Integer> getLength = text -> text.length();

        System.out.println(getLength.apply("Java"));
    }
}
```

### Output

```text
4
```

---

## 20. BiFunction Functional Interface

`BiFunction<T, U, R>` takes two inputs and returns one output.

* `T` means first input type
* `U` means second input type
* `R` means return type

### Example

```java
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

        System.out.println(add.apply(10, 20));
    }
}
```

### Output

```text
30
```

---

## 21. UnaryOperator Functional Interface

`UnaryOperator<T>` takes one input and returns the same type.

It is a special type of `Function`.

### Example

```java
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        UnaryOperator<Integer> square = number -> number * number;

        System.out.println(square.apply(6));
    }
}
```

### Output

```text
36
```

---

## 22. BinaryOperator Functional Interface

`BinaryOperator<T>` takes two inputs of the same type and returns the same type.

It is a special type of `BiFunction`.

### Example

```java
import java.util.function.BinaryOperator;

public class Main {
    public static void main(String[] args) {
        BinaryOperator<Integer> multiply = (a, b) -> a * b;

        System.out.println(multiply.apply(4, 5));
    }
}
```

### Output

```text
20
```

---

## 23. Functional Interface with Collections

Functional interfaces are commonly used with Java collections.

Example:

```java
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Anne", "Peter");

        Consumer<String> printName = name -> System.out.println(name);

        names.forEach(printName);
    }
}
```

### Output

```text
John
Anne
Peter
```

The `forEach()` method accepts a `Consumer`.

---

## 24. Functional Interface with Stream API

The Stream API uses functional interfaces heavily.

Example:

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

Here:

```java
number -> number % 2 == 0
```

is used as a `Predicate`.

---

## 25. Functional Interface with `filter()`

The `filter()` method uses `Predicate`.

Example:

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Anne", "John", "Alex", "Peter");

        List<String> namesStartingWithA = names.stream()
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());

        System.out.println(namesStartingWithA);
    }
}
```

### Output

```text
[Anne, Alex]
```

---

## 26. Functional Interface with `map()`

The `map()` method uses `Function`.

Example:

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

Here:

```java
name -> name.toUpperCase()
```

is used as a `Function`.

---

## 27. Functional Interface with `forEach()`

The `forEach()` method uses `Consumer`.

Example:

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

Here:

```java
name -> System.out.println(name)
```

is used as a `Consumer`.

---

## 28. Functional Interface with `reduce()`

The `reduce()` method can use `BinaryOperator`.

Example:

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

### Output

```text
15
```

Here:

```java
(a, b) -> a + b
```

acts like a `BinaryOperator`.

---

## 29. Functional Interface with Comparator

`Comparator<T>` is also a functional interface.

It has one main abstract method:

```java
int compare(T o1, T o2);
```

Example:

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

## 30. Functional Interface with Runnable

`Runnable` is a functional interface.

It has one abstract method:

```java
void run();
```

Example:

```java
public class Main {
    public static void main(String[] args) {
        Runnable task = () -> System.out.println("Task is running");

        task.run();
    }
}
```

### Output

```text
Task is running
```

Example with thread:

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

## 31. Functional Interface with Callable

`Callable` is also a functional interface.

Unlike `Runnable`, `Callable` can return a value.

Example:

```java
import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) throws Exception {
        Callable<String> task = () -> "Task completed";

        System.out.println(task.call());
    }
}
```

### Output

```text
Task completed
```

---

## 32. Method Reference with Functional Interface

A method reference is a shorter form of a lambda expression.

Example using lambda:

```java
Consumer<String> printer = name -> System.out.println(name);
```

Using method reference:

```java
Consumer<String> printer = System.out::println;
```

Complete example:

```java
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Consumer<String> printer = System.out::println;

        printer.accept("Hello Method Reference");
    }
}
```

### Output

```text
Hello Method Reference
```

---

## 33. Types of Method References

There are different types of method references.

### 33.1 Static Method Reference

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

### 33.2 Instance Method Reference

```java
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Consumer<String> printer = System.out::println;

        printer.accept("Java");
    }
}
```

### Output

```text
Java
```

---

### 33.3 Constructor Reference

```java
import java.util.ArrayList;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Supplier<ArrayList<String>> listSupplier = ArrayList::new;

        ArrayList<String> list = listSupplier.get();
        list.add("Java");

        System.out.println(list);
    }
}
```

### Output

```text
[Java]
```

---

## 34. Functional Interface Inheritance

A functional interface can extend another interface only if it still has one abstract method.

### Valid Example

```java
@FunctionalInterface
interface Parent {
    void show();
}

@FunctionalInterface
interface Child extends Parent {
}
```

This is valid because `Child` still has only one abstract method inherited from `Parent`.

---

### Invalid Example

```java
interface Parent {
    void show();
}

@FunctionalInterface
interface Child extends Parent {
    void display();
}
```

This is invalid because `Child` now has two abstract methods:

```java
void show();
void display();
```

---

## 35. Difference Between Functional Interface and Normal Interface

| Functional Interface                        | Normal Interface                                              |
| ------------------------------------------- | ------------------------------------------------------------- |
| Has exactly one abstract method             | Can have many abstract methods                                |
| Can be used with lambda expressions         | Cannot be used with lambda if multiple abstract methods exist |
| Supports functional programming             | Used for general abstraction                                  |
| Often annotated with `@FunctionalInterface` | No special annotation needed                                  |

---

## 36. Difference Between Functional Interface and Lambda

| Functional Interface         | Lambda Expression                   |
| ---------------------------- | ----------------------------------- |
| It is an interface           | It is an implementation of a method |
| Defines the method structure | Provides the method body            |
| Has one abstract method      | Matches that abstract method        |
| Example: `Calculator`        | Example: `(a, b) -> a + b`          |

Example:

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

public class Main {
    public static void main(String[] args) {
        Calculator add = (a, b) -> a + b;

        System.out.println(add.calculate(5, 3));
    }
}
```

Here:

```java
Calculator
```

is the functional interface.

```java
(a, b) -> a + b
```

is the lambda expression.

---

## 37. Advantages of Functional Interfaces

### 37.1 Supports Lambda Expressions

Functional interfaces allow us to use lambda expressions in Java.

### 37.2 Reduces Boilerplate Code

They reduce the need for long anonymous class implementations.

### 37.3 Improves Readability

Simple behavior can be written in a clean and short way.

### 37.4 Useful in Stream API

Functional interfaces are used in methods such as:

* `filter()`
* `map()`
* `forEach()`
* `reduce()`
* `sorted()`

### 37.5 Encourages Functional Programming

They allow behavior to be passed as an argument.

---

## 38. Disadvantages of Functional Interfaces

### 38.1 Can Reduce Readability if Overused

Too many lambdas can make code hard to understand.

### 38.2 Debugging Can Be Difficult

Debugging lambda expressions can sometimes be harder than debugging normal methods.

### 38.3 Not Suitable for Complex Logic

If the logic is long or complex, a separate method or class is better.

---

## 39. Best Practices

### 39.1 Use `@FunctionalInterface`

Always use `@FunctionalInterface` when creating your own functional interface.

Good:

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}
```

---

### 39.2 Keep Lambda Expressions Short

Good:

```java
number -> number * number
```

Avoid writing long business logic inside a lambda.

---

### 39.3 Use Built-in Functional Interfaces When Possible

Instead of creating a new interface every time, use Java’s built-in interfaces.

Instead of this:

```java
@FunctionalInterface
interface Checker {
    boolean check(int number);
}
```

Use:

```java
Predicate<Integer> checker = number -> number > 0;
```

---

### 39.4 Use Meaningful Variable Names

Less clear:

```java
x -> x.getSalary()
```

Better:

```java
employee -> employee.getSalary()
```

---

### 39.5 Use Method References When Suitable

Instead of:

```java
name -> System.out.println(name)
```

Use:

```java
System.out::println
```

---

## 40. Common Interview Questions

### Question 1: What is a functional interface in Java?

A functional interface is an interface that has exactly one abstract method.

---

### Question 2: What is the purpose of `@FunctionalInterface`?

It tells the compiler that the interface should have only one abstract method. If more than one abstract method is added, the compiler gives an error.

---

### Question 3: Can a functional interface have default methods?

Yes. A functional interface can have default methods because default methods do not count as abstract methods.

---

### Question 4: Can a functional interface have static methods?

Yes. Static methods are allowed in functional interfaces.

---

### Question 5: Can a functional interface have more than one abstract method?

No. A functional interface can have only one abstract method.

---

### Question 6: What is the relationship between lambda expressions and functional interfaces?

A lambda expression provides the implementation for the single abstract method of a functional interface.

---

### Question 7: Give examples of built-in functional interfaces.

Examples include:

* `Predicate`
* `Consumer`
* `Supplier`
* `Function`
* `BiFunction`
* `UnaryOperator`
* `BinaryOperator`
* `Runnable`
* `Comparator`
* `Callable`

---

## 41. Final Complete Example

```java
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Function;
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
                new Student("Peter", 45),
                new Student("Mary", 82)
        );

        Predicate<Student> hasPassed = student -> student.marks >= 50;

        Function<Student, String> getStudentName = student -> student.name;

        List<String> passedStudentNames = students.stream()
                .filter(hasPassed)
                .map(getStudentName)
                .collect(Collectors.toList());

        System.out.println(passedStudentNames);
    }
}
```

### Output

```text
[John, Anne, Mary]
```

### Explanation

In this example:

```java
Predicate<Student> hasPassed = student -> student.marks >= 50;
```

checks whether a student has passed.

```java
Function<Student, String> getStudentName = student -> student.name;
```

converts a `Student` object into the student's name.

The stream uses these functional interfaces:

```java
.filter(hasPassed)
.map(getStudentName)
```

---

## 42. Simple Summary

A **functional interface** is an interface with exactly one abstract method.

Basic example:

```java
@FunctionalInterface
interface Greeting {
    void sayHello();
}
```

Functional interfaces are mainly used with:

* Lambda expressions
* Method references
* Stream API
* Collection processing
* Functional programming

Important points:

* It has only one abstract method.
* It can have default methods.
* It can have static methods.
* It can be used with lambda expressions.
* It is recommended to use the `@FunctionalInterface` annotation.

Final simple example:

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

public class Main {
    public static void main(String[] args) {
        Calculator add = (a, b) -> a + b;

        System.out.println(add.calculate(10, 20));
    }
}
```

Output:

```text
30
```
