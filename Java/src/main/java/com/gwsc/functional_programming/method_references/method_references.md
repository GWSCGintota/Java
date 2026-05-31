# Java Functional Programming: Method References - Comprehensive Notes with Examples

## 1. Introduction

In Java functional programming, a **method reference** is a shorter and cleaner way to write a lambda expression when the lambda only calls an existing method.

Method references were introduced in **Java 8** together with lambda expressions and the Stream API.

A method reference uses the `::` operator.

Example:

```java
System.out::println
```

This is a method reference.

It is the shorter version of this lambda expression:

```java
value -> System.out.println(value)
```

---

## 2. What is a Method Reference?

A **method reference** refers to an existing method by name.

Instead of writing a lambda expression that only calls a method, we can directly refer to that method.

### Lambda expression

```java
name -> System.out.println(name)
```

### Method reference

```java
System.out::println
```

Both do the same thing.

---

## 3. Why Do We Use Method References?

Method references are used to:

* Make code shorter
* Improve readability
* Avoid unnecessary lambda syntax
* Reuse existing methods
* Work neatly with functional interfaces
* Make Stream API code cleaner

Example:

```java
names.forEach(name -> System.out.println(name));
```

Can be written as:

```java
names.forEach(System.out::println);
```

---

## 4. Basic Syntax of Method Reference

The general syntax is:

```java
ClassName::methodName
```

or:

```java
objectName::methodName
```

or:

```java
ClassName::new
```

The `::` operator is called the **method reference operator**.

---

## 5. Relationship Between Lambda and Method Reference

A method reference is mainly a shortcut for a lambda expression.

Example:

```java
number -> Math.sqrt(number)
```

Can be written as:

```java
Math::sqrt
```

Another example:

```java
text -> text.toUpperCase()
```

Can be written as:

```java
String::toUpperCase
```

---

## 6. Important Rule

A method reference can only be used where Java expects a **functional interface**.

A functional interface is an interface with exactly one abstract method.

Example:

```java
@FunctionalInterface
interface Printer {
    void print(String message);
}
```

Using lambda:

```java
Printer printer = message -> System.out.println(message);
```

Using method reference:

```java
Printer printer = System.out::println;
```

---

## 7. Complete Basic Example

```java
@FunctionalInterface
interface Printer {
    void print(String message);
}

public class Main {
    public static void main(String[] args) {
        Printer printer = System.out::println;

        printer.print("Hello Java Method Reference");
    }
}
```

### Output

```text
Hello Java Method Reference
```

### Explanation

This method reference:

```java
System.out::println
```

is equivalent to:

```java
message -> System.out.println(message)
```

---

# 8. Types of Method References in Java

Java has four main types of method references:

| Type                                                                  | Syntax                           | Example               |
| --------------------------------------------------------------------- | -------------------------------- | --------------------- |
| Static method reference                                               | `ClassName::staticMethodName`    | `Integer::parseInt`   |
| Instance method reference of a particular object                      | `objectName::instanceMethodName` | `System.out::println` |
| Instance method reference of an arbitrary object of a particular type | `ClassName::instanceMethodName`  | `String::toUpperCase` |
| Constructor reference                                                 | `ClassName::new`                 | `ArrayList::new`      |

---

# 9. Type 1: Static Method Reference

A static method reference refers to a static method of a class.

## Syntax

```java
ClassName::staticMethodName
```

## Example with Lambda

```java
Function<String, Integer> converter = text -> Integer.parseInt(text);
```

## Example with Method Reference

```java
Function<String, Integer> converter = Integer::parseInt;
```

## Complete Example

```java
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<String, Integer> converter = Integer::parseInt;

        int number = converter.apply("100");

        System.out.println(number);
    }
}
```

### Output

```text
100
```

### Explanation

This method reference:

```java
Integer::parseInt
```

is equal to:

```java
text -> Integer.parseInt(text)
```

---

## 10. Another Static Method Reference Example

```java
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<Double, Double> squareRoot = Math::sqrt;

        System.out.println(squareRoot.apply(25.0));
    }
}
```

### Output

```text
5.0
```

Here:

```java
Math::sqrt
```

is equal to:

```java
number -> Math.sqrt(number)
```

---

# 11. Type 2: Instance Method Reference of a Particular Object

This type refers to an instance method of a specific object.

## Syntax

```java
objectName::instanceMethodName
```

## Example

```java
System.out::println
```

Here, `System.out` is a specific object, and `println` is an instance method.

## Complete Example

```java
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Consumer<String> printer = System.out::println;

        printer.accept("Java Functional Programming");
    }
}
```

### Output

```text
Java Functional Programming
```

### Explanation

This method reference:

```java
System.out::println
```

is equal to:

```java
message -> System.out.println(message)
```

---

## 12. Custom Object Method Reference Example

```java
import java.util.function.Consumer;

class MessageService {
    public void sendMessage(String message) {
        System.out.println("Sending message: " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        MessageService service = new MessageService();

        Consumer<String> sender = service::sendMessage;

        sender.accept("Hello");
    }
}
```

### Output

```text
Sending message: Hello
```

### Explanation

This method reference:

```java
service::sendMessage
```

is equal to:

```java
message -> service.sendMessage(message)
```

---

# 13. Type 3: Instance Method Reference of an Arbitrary Object of a Particular Type

This type is slightly different.

It refers to an instance method, but the object is supplied later.

## Syntax

```java
ClassName::instanceMethodName
```

## Example

```java
String::toUpperCase
```

This means:

```java
text -> text.toUpperCase()
```

## Complete Example

```java
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<String, String> upperCase = String::toUpperCase;

        System.out.println(upperCase.apply("java"));
    }
}
```

### Output

```text
JAVA
```

---

## 14. Example with List and Stream

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("john", "anne", "peter");

        List<String> upperNames = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(upperNames);
    }
}
```

### Output

```text
[JOHN, ANNE, PETER]
```

### Explanation

This method reference:

```java
String::toUpperCase
```

is equal to:

```java
name -> name.toUpperCase()
```

---

## 15. Example with Sorting Strings

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Peter", "anne", "John");

        names.sort(String::compareToIgnoreCase);

        System.out.println(names);
    }
}
```

### Output

```text
[anne, John, Peter]
```

### Explanation

This method reference:

```java
String::compareToIgnoreCase
```

is equal to:

```java
(name1, name2) -> name1.compareToIgnoreCase(name2)
```

---

# 16. Type 4: Constructor Reference

A constructor reference is used to refer to a constructor.

## Syntax

```java
ClassName::new
```

## Example with Lambda

```java
Supplier<ArrayList<String>> supplier = () -> new ArrayList<>();
```

## Example with Constructor Reference

```java
Supplier<ArrayList<String>> supplier = ArrayList::new;
```

## Complete Example

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

---

## 17. Constructor Reference with One Parameter

```java
import java.util.function.Function;

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }

    void display() {
        System.out.println("Student name: " + name);
    }
}

public class Main {
    public static void main(String[] args) {
        Function<String, Student> studentCreator = Student::new;

        Student student = studentCreator.apply("Anne");

        student.display();
    }
}
```

### Output

```text
Student name: Anne
```

### Explanation

This constructor reference:

```java
Student::new
```

is equal to:

```java
name -> new Student(name)
```

---

## 18. Constructor Reference with Two Parameters

```java
import java.util.function.BiFunction;

class Employee {
    String name;
    int salary;

    Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    void display() {
        System.out.println(name + " earns " + salary);
    }
}

public class Main {
    public static void main(String[] args) {
        BiFunction<String, Integer, Employee> employeeCreator = Employee::new;

        Employee employee = employeeCreator.apply("John", 50000);

        employee.display();
    }
}
```

### Output

```text
John earns 50000
```

### Explanation

This constructor reference:

```java
Employee::new
```

is equal to:

```java
(name, salary) -> new Employee(name, salary)
```

---

# 19. Method References with Built-in Functional Interfaces

Method references are commonly used with Java built-in functional interfaces.

## 19.1 Method Reference with `Consumer`

```java
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Consumer<String> printer = System.out::println;

        printer.accept("Hello Consumer");
    }
}
```

### Output

```text
Hello Consumer
```

---

## 19.2 Method Reference with `Function`

```java
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<String, Integer> lengthFinder = String::length;

        System.out.println(lengthFinder.apply("Java"));
    }
}
```

### Output

```text
4
```

Here:

```java
String::length
```

is equal to:

```java
text -> text.length()
```

---

## 19.3 Method Reference with `Predicate`

```java
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Predicate<String> isEmpty = String::isEmpty;

        System.out.println(isEmpty.test(""));
        System.out.println(isEmpty.test("Java"));
    }
}
```

### Output

```text
true
false
```

Here:

```java
String::isEmpty
```

is equal to:

```java
text -> text.isEmpty()
```

---

## 19.4 Method Reference with `Supplier`

```java
import java.time.LocalDate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Supplier<LocalDate> todaySupplier = LocalDate::now;

        System.out.println(todaySupplier.get());
    }
}
```

### Output

```text
Current date
```

Here:

```java
LocalDate::now
```

is equal to:

```java
() -> LocalDate.now()
```

---

# 20. Method References with Stream API

Method references are very useful with the Stream API.

---

## 20.1 `forEach()` Example

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

## 20.2 `map()` Example

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("john", "anne", "peter");

        List<String> upperNames = names.stream()
                .map(String::toUpperCase)
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

## 20.3 `filter()` Example

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("", "Java", "", "Lambda");

        List<String> nonEmptyWords = words.stream()
                .filter(word -> !word.isEmpty())
                .collect(Collectors.toList());

        System.out.println(nonEmptyWords);
    }
}
```

### Output

```text
[Java, Lambda]
```

In this example, we cannot directly use:

```java
String::isEmpty
```

because we want **not empty** values.

This works for empty values:

```java
.filter(String::isEmpty)
```

But for non-empty values, the lambda is clearer:

```java
.filter(word -> !word.isEmpty())
```

---

## 20.4 `sorted()` Example

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Peter", "anne", "John");

        names.sort(String::compareToIgnoreCase);

        System.out.println(names);
    }
}
```

### Output

```text
[anne, John, Peter]
```

---

# 21. Method Reference with Custom Classes

## Example: Employee Class

```java
import java.util.Arrays;
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
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John", 50000),
                new Employee("Anne", 70000),
                new Employee("Peter", 40000)
        );

        List<String> employeeNames = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        System.out.println(employeeNames);
    }
}
```

### Output

```text
[John, Anne, Peter]
```

### Explanation

This method reference:

```java
Employee::getName
```

is equal to:

```java
employee -> employee.getName()
```

---

## 22. Method Reference with Comparator

Method references are often used with `Comparator`.

```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

        employees.sort(Comparator.comparing(Employee::getSalary));

        System.out.println(employees);
    }
}
```

### Output

```text
[Peter - 40000, John - 50000, Anne - 70000]
```

### Explanation

This part:

```java
Comparator.comparing(Employee::getSalary)
```

sorts employees by salary.

---

## 23. Sorting in Descending Order

```java
employees.sort(Comparator.comparing(Employee::getSalary).reversed());
```

This sorts employees from highest salary to lowest salary.

---

# 24. Bound Method Reference vs Unbound Method Reference

## 24.1 Bound Method Reference

A **bound method reference** is connected to a specific object.

Example:

```java
System.out::println
```

Here, `System.out` is already known.

Equivalent lambda:

```java
value -> System.out.println(value)
```

---

## 24.2 Unbound Method Reference

An **unbound method reference** is connected to a class, but the actual object is passed later.

Example:

```java
String::toUpperCase
```

Equivalent lambda:

```java
text -> text.toUpperCase()
```

Here, `text` is the object that will be passed later.

---

## 25. Difference Between Lambda Expression and Method Reference

| Lambda Expression                    | Method Reference                  |
| ------------------------------------ | --------------------------------- |
| Can contain custom logic             | Only refers to an existing method |
| More flexible                        | Shorter and cleaner               |
| Uses `->`                            | Uses `::`                         |
| Example: `x -> x.toUpperCase()`      | Example: `String::toUpperCase`    |
| Good for conditions and calculations | Good when simply calling a method |

---

## 26. When to Use Method References

Use method references when the lambda expression only calls an existing method.

Good:

```java
names.forEach(System.out::println);
```

Good:

```java
names.stream()
        .map(String::toUpperCase)
        .forEach(System.out::println);
```

Good:

```java
employees.sort(Comparator.comparing(Employee::getSalary));
```

---

## 27. When Not to Use Method References

Do not use method references if the lambda contains extra logic.

Example:

```java
number -> number * 2
```

This cannot be replaced by a simple method reference unless there is already a method that doubles the number.

Another example:

```java
name -> "Hello " + name
```

This is better as a lambda because it contains custom logic.

Another example:

```java
word -> !word.isEmpty()
```

This is clearer as a lambda because it negates the result.

---

# 28. Common Mistake

## Mistake: Using a Method Reference When Logic Is Needed

Incorrect idea:

```java
.filter(String::isEmpty)
```

This filters empty strings.

But if you want non-empty strings, you need:

```java
.filter(word -> !word.isEmpty())
```

---

## Mistake: Forgetting That Method Reference Needs a Functional Interface

This is invalid by itself:

```java
String::toUpperCase;
```

A method reference must be assigned to a functional interface or passed to a method expecting a functional interface.

Valid:

```java
Function<String, String> converter = String::toUpperCase;
```

---

# 29. Complete Example: Students

```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Student {
    private String name;
    private int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    String getName() {
        return name;
    }

    int getMarks() {
        return marks;
    }

    boolean hasPassed() {
        return marks >= 50;
    }

    public String toString() {
        return name + " - " + marks;
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

        List<String> passedStudentNames = students.stream()
                .filter(Student::hasPassed)
                .sorted(Comparator.comparing(Student::getMarks).reversed())
                .map(Student::getName)
                .collect(Collectors.toList());

        passedStudentNames.forEach(System.out::println);
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

```java
.filter(Student::hasPassed)
```

is equal to:

```java
.filter(student -> student.hasPassed())
```

```java
.sorted(Comparator.comparing(Student::getMarks).reversed())
```

sorts students by marks in descending order.

```java
.map(Student::getName)
```

is equal to:

```java
.map(student -> student.getName())
```

```java
.forEach(System.out::println)
```

is equal to:

```java
.forEach(name -> System.out.println(name))
```

---

# 30. Complete Example: Products

```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Product {
    private String name;
    private double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    String getName() {
        return name;
    }

    double getPrice() {
        return price;
    }

    boolean isExpensive() {
        return price > 100;
    }

    public String toString() {
        return name + " - " + price;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", 1200),
                new Product("Mouse", 50),
                new Product("Keyboard", 150),
                new Product("Monitor", 300)
        );

        List<String> expensiveProductNames = products.stream()
                .filter(Product::isExpensive)
                .sorted(Comparator.comparing(Product::getPrice))
                .map(Product::getName)
                .collect(Collectors.toList());

        System.out.println(expensiveProductNames);
    }
}
```

### Output

```text
[Keyboard, Monitor, Laptop]
```

---

# 31. Method Reference with Custom Functional Interface

```java
@FunctionalInterface
interface StringFormatter {
    String format(String text);
}

public class Main {
    public static void main(String[] args) {
        StringFormatter formatter = String::toUpperCase;

        System.out.println(formatter.format("java"));
    }
}
```

### Output

```text
JAVA
```

### Explanation

The method reference:

```java
String::toUpperCase
```

matches the abstract method:

```java
String format(String text);
```

---

# 32. Method Reference with Static Custom Method

```java
import java.util.function.Function;

class TextUtils {
    static String removeSpaces(String text) {
        return text.replace(" ", "");
    }
}

public class Main {
    public static void main(String[] args) {
        Function<String, String> cleaner = TextUtils::removeSpaces;

        System.out.println(cleaner.apply("Java Method Reference"));
    }
}
```

### Output

```text
JavaMethodReference
```

### Explanation

This method reference:

```java
TextUtils::removeSpaces
```

is equal to:

```java
text -> TextUtils.removeSpaces(text)
```

---

# 33. Method Reference with Multiple Parameters

```java
import java.util.function.BiFunction;

class Calculator {
    static int add(int a, int b) {
        return a + b;
    }
}

public class Main {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> addition = Calculator::add;

        System.out.println(addition.apply(10, 20));
    }
}
```

### Output

```text
30
```

### Explanation

This method reference:

```java
Calculator::add
```

is equal to:

```java
(a, b) -> Calculator.add(a, b)
```

---

# 34. Array Constructor Reference

Java also supports array constructor references.

```java
import java.util.function.IntFunction;

public class Main {
    public static void main(String[] args) {
        IntFunction<String[]> arrayCreator = String[]::new;

        String[] names = arrayCreator.apply(3);

        names[0] = "John";
        names[1] = "Anne";
        names[2] = "Peter";

        for (String name : names) {
            System.out.println(name);
        }
    }
}
```

### Output

```text
John
Anne
Peter
```

### Explanation

This:

```java
String[]::new
```

is equal to:

```java
size -> new String[size]
```

---

# 35. Best Practices

## 35.1 Use Method References When They Improve Readability

Good:

```java
names.forEach(System.out::println);
```

Good:

```java
names.stream()
        .map(String::toUpperCase)
        .forEach(System.out::println);
```

---

## 35.2 Use Lambdas When Logic Is More Complex

Good:

```java
names.stream()
        .filter(name -> name.length() > 5)
        .forEach(System.out::println);
```

This is better as a lambda because it contains a condition.

---

## 35.3 Prefer `Comparator.comparing()`

Instead of:

```java
employees.sort((e1, e2) -> Integer.compare(e1.getSalary(), e2.getSalary()));
```

Use:

```java
employees.sort(Comparator.comparing(Employee::getSalary));
```

---

## 35.4 Use Meaningful Method Names

Method references are clearer when the method name clearly describes the action.

Good:

```java
.filter(Student::hasPassed)
```

Less clear:

```java
.filter(Student::check)
```

---

# 36. Advantages of Method References

* Shorter than lambda expressions
* Cleaner syntax
* Improves readability
* Reuses existing methods
* Works well with Stream API
* Reduces boilerplate code
* Encourages functional programming style

---

# 37. Disadvantages of Method References

* Not suitable for complex logic
* Can be confusing for beginners
* Sometimes lambda expressions are more readable
* Requires understanding of functional interfaces
* Method signature must match the functional interface method

---

# 38. Common Interview Questions

## Question 1: What is a method reference in Java?

A method reference is a shorter way to write a lambda expression that only calls an existing method.

Example:

```java
System.out::println
```

---

## Question 2: What operator is used for method references?

The `::` operator is used for method references.

Example:

```java
ClassName::methodName
```

---

## Question 3: What are the types of method references?

The four main types are:

1. Static method reference
2. Instance method reference of a particular object
3. Instance method reference of an arbitrary object of a particular type
4. Constructor reference

---

## Question 4: What is the difference between lambda and method reference?

A lambda expression can contain custom logic, while a method reference directly refers to an existing method.

Example lambda:

```java
name -> System.out.println(name)
```

Method reference:

```java
System.out::println
```

---

## Question 5: Can every lambda be replaced by a method reference?

No. Only lambdas that simply call an existing method can usually be replaced by method references.

Example that can be replaced:

```java
name -> System.out.println(name)
```

Replacement:

```java
System.out::println
```

Example that should remain lambda:

```java
name -> "Hello " + name
```

---

## Question 6: What is a constructor reference?

A constructor reference is a method reference that refers to a constructor.

Example:

```java
Student::new
```

This is equal to:

```java
name -> new Student(name)
```

---

# 39. Simple Summary

A **method reference** in Java is a shortcut for a lambda expression that calls an existing method.

Basic syntax:

```java
ClassName::methodName
```

or:

```java
objectName::methodName
```

or:

```java
ClassName::new
```

Example:

```java
System.out::println
```

is equal to:

```java
value -> System.out.println(value)
```

Method references are commonly used with:

* Functional interfaces
* Lambda expressions
* Stream API
* Collections
* Sorting
* Mapping
* Filtering
* Constructor creation

---

# 40. Final Complete Java Example

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

    boolean isHighSalaryEmployee() {
        return salary >= 60000;
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

        List<String> highSalaryEmployeeNames = employees.stream()
                .filter(Employee::isHighSalaryEmployee)
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .map(Employee::getName)
                .collect(Collectors.toList());

        highSalaryEmployeeNames.forEach(System.out::println);
    }
}
```

### Output

```text
Mary
Anne
```

### Explanation

This example uses several method references:

```java
Employee::isHighSalaryEmployee
```

This filters employees with salary greater than or equal to 60000.

```java
Employee::getSalary
```

This is used to sort employees by salary.

```java
Employee::getName
```

This converts each employee object into the employee name.

```java
System.out::println
```

This prints each employee name.
