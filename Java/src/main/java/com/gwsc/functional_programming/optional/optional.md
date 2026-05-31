#### [<-Back](../functional_programming.md) 
# Java Functional Programming: Optional - Comprehensive Notes with Examples

## 1. Introduction

`Optional` is a container object in Java that may or may not contain a non-null value.

It was introduced in **Java 8** as part of Java’s functional programming features.

`Optional` is mainly used to avoid `NullPointerException` and to write cleaner code when a value may be missing.

---

## 2. What is `Optional`?

`Optional<T>` is a class from the package:

```java
java.util.Optional
```

It represents a value that can be either:

* Present
* Empty

Example:

```java
Optional<String> name = Optional.of("John");
```

Here, the value `"John"` is present.

Example of an empty Optional:

```java
Optional<String> name = Optional.empty();
```

Here, there is no value.

---

## 3. Why Do We Use Optional?

Before `Optional`, Java programs often used `null` to represent missing values.

Example:

```java
String name = null;
System.out.println(name.length());
```

This causes:

```text
NullPointerException
```

`Optional` helps avoid this by forcing us to handle the missing value properly.

---

## 4. Problem Without Optional

```java
public class Main {
    public static void main(String[] args) {
        String name = getName();

        System.out.println(name.toUpperCase());
    }

    public static String getName() {
        return null;
    }
}
```

### Output

```text
Exception in thread "main" java.lang.NullPointerException
```

The problem happens because `name` is `null`.

---

## 5. Solution With Optional

```java
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<String> name = getName();

        name.ifPresent(value -> System.out.println(value.toUpperCase()));
    }

    public static Optional<String> getName() {
        return Optional.empty();
    }
}
```

In this example, the program does not crash. It only prints the value if the value is present.

---

# 6. Creating Optional Objects

There are three main ways to create an `Optional`.

---

## 6.1 `Optional.of()`

Use `Optional.of()` when you are sure the value is not null.

```java
Optional<String> name = Optional.of("Java");

System.out.println(name);
```

### Output

```text
Optional[Java]
```

### Important

If the value is null, `Optional.of()` will throw `NullPointerException`.

```java
Optional<String> name = Optional.of(null);
```

This is invalid.

---

## 6.2 `Optional.ofNullable()`

Use `Optional.ofNullable()` when the value may be null.

```java
String value = null;

Optional<String> name = Optional.ofNullable(value);

System.out.println(name);
```

### Output

```text
Optional.empty
```

If the value is not null:

```java
String value = "Java";

Optional<String> name = Optional.ofNullable(value);

System.out.println(name);
```

### Output

```text
Optional[Java]
```

---

## 6.3 `Optional.empty()`

Use `Optional.empty()` to create an empty Optional.

```java
Optional<String> name = Optional.empty();

System.out.println(name);
```

### Output

```text
Optional.empty
```

---

# 7. Checking Whether a Value Exists

## 7.1 `isPresent()`

`isPresent()` returns `true` if a value exists.

```java
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("John");

        if (name.isPresent()) {
            System.out.println(name.get());
        }
    }
}
```

### Output

```text
John
```

---

## 7.2 `isEmpty()`

`isEmpty()` returns `true` if the Optional has no value.

```java
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<String> name = Optional.empty();

        if (name.isEmpty()) {
            System.out.println("No value found");
        }
    }
}
```

### Output

```text
No value found
```

Note: `isEmpty()` was introduced in Java 11.

---

# 8. Getting Value from Optional

## 8.1 `get()`

`get()` returns the value if it is present.

```java
Optional<String> name = Optional.of("Java");

System.out.println(name.get());
```

### Output

```text
Java
```

### Warning

Do not use `get()` without checking whether a value is present.

Bad example:

```java
Optional<String> name = Optional.empty();

System.out.println(name.get());
```

This causes:

```text
NoSuchElementException
```

Better:

```java
if (name.isPresent()) {
    System.out.println(name.get());
}
```

Even better:

```java
name.ifPresent(System.out::println);
```

---

# 9. `ifPresent()`

`ifPresent()` executes code only if the value exists.

```java
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("Anne");

        name.ifPresent(value -> System.out.println(value));
    }
}
```

### Output

```text
Anne
```

Using method reference:

```java
name.ifPresent(System.out::println);
```

---

# 10. `ifPresentOrElse()`

`ifPresentOrElse()` is used when we want to do one thing if the value exists and another thing if the value is missing.

```java
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<String> name = Optional.empty();

        name.ifPresentOrElse(
                value -> System.out.println("Name: " + value),
                () -> System.out.println("Name not found")
        );
    }
}
```

### Output

```text
Name not found
```

Note: `ifPresentOrElse()` was introduced in Java 9.

---

# 11. Providing Default Values

Optional provides different methods to return default values when the Optional is empty.

---

## 11.1 `orElse()`

`orElse()` returns the value if present. Otherwise, it returns a default value.

```java
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<String> name = Optional.empty();

        String result = name.orElse("Default Name");

        System.out.println(result);
    }
}
```

### Output

```text
Default Name
```

If value is present:

```java
Optional<String> name = Optional.of("John");

String result = name.orElse("Default Name");

System.out.println(result);
```

### Output

```text
John
```

---

## 11.2 `orElseGet()`

`orElseGet()` returns a default value using a `Supplier`.

It is useful when the default value is expensive to create.

```java
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<String> name = Optional.empty();

        String result = name.orElseGet(() -> "Generated Default Name");

        System.out.println(result);
    }
}
```

### Output

```text
Generated Default Name
```

---

## 11.3 Difference Between `orElse()` and `orElseGet()`

The main difference is:

* `orElse()` always evaluates the default value
* `orElseGet()` only evaluates the default value if the Optional is empty

Example:

```java
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("John");

        String result = name.orElse(getDefaultName());

        System.out.println(result);
    }

    public static String getDefaultName() {
        System.out.println("Default method called");
        return "Default Name";
    }
}
```

### Output

```text
Default method called
John
```

Even though `"John"` exists, `getDefaultName()` is still called.

Now using `orElseGet()`:

```java
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("John");

        String result = name.orElseGet(() -> getDefaultName());

        System.out.println(result);
    }

    public static String getDefaultName() {
        System.out.println("Default method called");
        return "Default Name";
    }
}
```

### Output

```text
John
```

Here, `getDefaultName()` is not called because the value is already present.

---

# 12. Throwing Exception with `orElseThrow()`

`orElseThrow()` returns the value if present. If the value is missing, it throws an exception.

```java
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<String> name = Optional.empty();

        String result = name.orElseThrow(() -> new RuntimeException("Name not found"));

        System.out.println(result);
    }
}
```

### Output

```text
Exception in thread "main" java.lang.RuntimeException: Name not found
```

---

# 13. Transforming Values with `map()`

`map()` is used to transform the value inside an Optional.

Example:

```java
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("java");

        Optional<String> upperName = name.map(value -> value.toUpperCase());

        System.out.println(upperName);
    }
}
```

### Output

```text
Optional[JAVA]
```

Using method reference:

```java
Optional<String> upperName = name.map(String::toUpperCase);
```

---

# 14. Example: Getting Length Using `map()`

```java
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("Java");

        Optional<Integer> length = name.map(String::length);

        System.out.println(length);
    }
}
```

### Output

```text
Optional[4]
```

---

# 15. Filtering Values with `filter()`

`filter()` keeps the value only if it satisfies a condition.

```java
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("Anne");

        Optional<String> result = name.filter(value -> value.startsWith("A"));

        System.out.println(result);
    }
}
```

### Output

```text
Optional[Anne]
```

If the condition is false:

```java
Optional<String> name = Optional.of("John");

Optional<String> result = name.filter(value -> value.startsWith("A"));

System.out.println(result);
```

### Output

```text
Optional.empty
```

---

# 16. `flatMap()`

`flatMap()` is used when the mapping function already returns an Optional.

## Problem with `map()`

```java
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("John");

        Optional<Optional<String>> result = name.map(value -> getUpperName(value));

        System.out.println(result);
    }

    public static Optional<String> getUpperName(String name) {
        return Optional.of(name.toUpperCase());
    }
}
```

### Output

```text
Optional[Optional[JOHN]]
```

This creates a nested Optional.

---

## Solution with `flatMap()`

```java
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("John");

        Optional<String> result = name.flatMap(value -> getUpperName(value));

        System.out.println(result);
    }

    public static Optional<String> getUpperName(String name) {
        return Optional.of(name.toUpperCase());
    }
}
```

### Output

```text
Optional[JOHN]
```

---

# 17. Difference Between `map()` and `flatMap()`

| Method      | Used When                       | Result                 |
| ----------- | ------------------------------- | ---------------------- |
| `map()`     | Function returns a normal value | `Optional<Value>`      |
| `flatMap()` | Function returns an Optional    | Avoids nested Optional |

Example using `map()`:

```java
Optional<String> name = Optional.of("Java");

Optional<Integer> length = name.map(String::length);
```

Example using `flatMap()`:

```java
Optional<String> name = Optional.of("Java");

Optional<String> result = name.flatMap(value -> getValue(value));
```

where:

```java
public static Optional<String> getValue(String value) {
    return Optional.of(value.toUpperCase());
}
```

---

# 18. Chaining Optional Methods

Optional methods can be chained together.

```java
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("anne");

        String result = name
                .filter(value -> value.length() > 3)
                .map(String::toUpperCase)
                .orElse("Invalid Name");

        System.out.println(result);
    }
}
```

### Output

```text
ANNE
```

If the name does not satisfy the condition:

```java
Optional<String> name = Optional.of("Al");

String result = name
        .filter(value -> value.length() > 3)
        .map(String::toUpperCase)
        .orElse("Invalid Name");

System.out.println(result);
```

### Output

```text
Invalid Name
```

---

# 19. Optional with Objects

Example with a `User` class:

```java
import java.util.Optional;

class User {
    private String name;
    private String email;

    User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }
}

public class Main {
    public static void main(String[] args) {
        Optional<User> user = findUserById(1);

        String email = user
                .map(User::getEmail)
                .orElse("No email found");

        System.out.println(email);
    }

    public static Optional<User> findUserById(int id) {
        if (id == 1) {
            return Optional.of(new User("John", "john@example.com"));
        }
        return Optional.empty();
    }
}
```

### Output

```text
john@example.com
```

---

# 20. Optional with Nested Objects

Suppose we have a `User` object and an `Address` object.

```java
import java.util.Optional;

class Address {
    private String city;

    Address(String city) {
        this.city = city;
    }

    String getCity() {
        return city;
    }
}

class User {
    private Address address;

    User(Address address) {
        this.address = address;
    }

    Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }
}

public class Main {
    public static void main(String[] args) {
        Optional<User> user = Optional.of(new User(new Address("Sydney")));

        String city = user
                .flatMap(User::getAddress)
                .map(Address::getCity)
                .orElse("City not found");

        System.out.println(city);
    }
}
```

### Output

```text
Sydney
```

### Explanation

```java
.flatMap(User::getAddress)
```

is used because `getAddress()` returns `Optional<Address>`.

```java
.map(Address::getCity)
```

is used because `getCity()` returns a normal `String`.

---

# 21. Optional with Stream API

`Optional` works well with the Stream API.

Example:

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

### Output

```text
Anne
```

`findFirst()` returns an Optional because there may or may not be a matching value.

---

# 22. Stream Methods That Return Optional

Some Stream methods return Optional because the result may not exist.

| Stream Method | Return Type   | Reason                              |
| ------------- | ------------- | ----------------------------------- |
| `findFirst()` | `Optional<T>` | There may be no first matching item |
| `findAny()`   | `Optional<T>` | There may be no matching item       |
| `max()`       | `Optional<T>` | Stream may be empty                 |
| `min()`       | `Optional<T>` | Stream may be empty                 |
| `reduce()`    | `Optional<T>` | Stream may be empty                 |

---

## 22.1 Example with `max()`

```java
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 25, 5, 40);

        Optional<Integer> maxNumber = numbers.stream()
                .max(Integer::compareTo);

        maxNumber.ifPresent(System.out::println);
    }
}
```

### Output

```text
40
```

---

## 22.2 Example with Empty List

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();

        Optional<Integer> maxNumber = numbers.stream()
                .max(Integer::compareTo);

        System.out.println(maxNumber.orElse(0));
    }
}
```

### Output

```text
0
```

---

# 23. Optional with Repository or Service Layer

`Optional` is commonly used when searching for data.

Example:

```java
import java.util.Optional;

class User {
    private int id;
    private String name;

    User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class UserRepository {
    Optional<User> findById(int id) {
        if (id == 1) {
            return Optional.of(new User(1, "John"));
        }
        return Optional.empty();
    }
}

public class Main {
    public static void main(String[] args) {
        UserRepository repository = new UserRepository();

        String name = repository.findById(1)
                .map(User::getName)
                .orElse("Unknown User");

        System.out.println(name);
    }
}
```

### Output

```text
John
```

---

# 24. Optional with Exception Handling

```java
import java.util.Optional;

class User {
    private String name;

    User(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class UserRepository {
    Optional<User> findById(int id) {
        if (id == 1) {
            return Optional.of(new User("Anne"));
        }
        return Optional.empty();
    }
}

public class Main {
    public static void main(String[] args) {
        UserRepository repository = new UserRepository();

        User user = repository.findById(2)
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println(user.getName());
    }
}
```

### Output

```text
Exception in thread "main" java.lang.RuntimeException: User not found
```

---

# 25. Optional Primitive Types

Java also provides primitive Optional types:

* `OptionalInt`
* `OptionalLong`
* `OptionalDouble`

These avoid boxing and unboxing.

---

## 25.1 OptionalInt Example

```java
import java.util.OptionalInt;

public class Main {
    public static void main(String[] args) {
        OptionalInt number = OptionalInt.of(10);

        number.ifPresent(System.out::println);
    }
}
```

### Output

```text
10
```

---

## 25.2 Empty OptionalInt

```java
import java.util.OptionalInt;

public class Main {
    public static void main(String[] args) {
        OptionalInt number = OptionalInt.empty();

        System.out.println(number.orElse(0));
    }
}
```

### Output

```text
0
```

---

# 26. Optional and Functional Programming

`Optional` supports functional programming because it allows us to process values using functions instead of many `if` conditions.

Traditional style:

```java
String name = getName();

if (name != null) {
    name = name.toUpperCase();
} else {
    name = "Unknown";
}
```

Functional style:

```java
String name = getName()
        .map(String::toUpperCase)
        .orElse("Unknown");
```

This makes the code shorter and more expressive.

---

# 27. Optional vs Null Check

## Using Null Check

```java
String name = getName();

if (name != null) {
    System.out.println(name.toUpperCase());
} else {
    System.out.println("No name");
}
```

## Using Optional

```java
Optional<String> name = getName();

String result = name
        .map(String::toUpperCase)
        .orElse("No name");

System.out.println(result);
```

---

# 28. Common Optional Methods Summary

| Method              | Description                                     |
| ------------------- | ----------------------------------------------- |
| `empty()`           | Creates an empty Optional                       |
| `of(value)`         | Creates Optional with non-null value            |
| `ofNullable(value)` | Creates Optional that can handle null           |
| `isPresent()`       | Checks if value exists                          |
| `isEmpty()`         | Checks if value does not exist                  |
| `get()`             | Gets value, but unsafe if empty                 |
| `ifPresent()`       | Runs code if value exists                       |
| `ifPresentOrElse()` | Runs one action if present, another if empty    |
| `orElse()`          | Returns value or default                        |
| `orElseGet()`       | Returns value or generated default              |
| `orElseThrow()`     | Returns value or throws exception               |
| `map()`             | Transforms value                                |
| `flatMap()`         | Transforms value when function returns Optional |
| `filter()`          | Keeps value only if condition is true           |

---

# 29. Best Practices

## 29.1 Use Optional as Return Type

Good:

```java
public Optional<User> findUserById(int id) {
    return Optional.empty();
}
```

This clearly tells the caller that the user may not exist.

---

## 29.2 Do Not Use Optional for Fields

Avoid this:

```java
class User {
    private Optional<String> email;
}
```

Better:

```java
class User {
    private String email;
}
```

Use Optional mainly as a return type, not as a field.

---

## 29.3 Avoid `get()` Without Checking

Bad:

```java
Optional<String> name = Optional.empty();

System.out.println(name.get());
```

Good:

```java
name.ifPresent(System.out::println);
```

or:

```java
String result = name.orElse("Default");
```

---

## 29.4 Prefer `orElseGet()` for Expensive Default Values

Bad:

```java
String result = optionalName.orElse(loadDefaultName());
```

Better:

```java
String result = optionalName.orElseGet(() -> loadDefaultName());
```

---

## 29.5 Do Not Return Null from a Method Returning Optional

Bad:

```java
public Optional<String> getName() {
    return null;
}
```

Good:

```java
public Optional<String> getName() {
    return Optional.empty();
}
```

---

## 29.6 Use `map()` and `flatMap()` for Transformations

Good:

```java
String email = user
        .map(User::getEmail)
        .orElse("No email");
```

Good with nested Optional:

```java
String city = user
        .flatMap(User::getAddress)
        .map(Address::getCity)
        .orElse("No city");
```

---

# 30. Common Mistakes

## Mistake 1: Using Optional Like a Normal Object

Bad:

```java
Optional<String> name = Optional.of("Java");

System.out.println(name.toUpperCase());
```

This is invalid because `name` is an Optional, not a String.

Correct:

```java
Optional<String> name = Optional.of("Java");

System.out.println(name.map(String::toUpperCase).orElse(""));
```

---

## Mistake 2: Calling `get()` on Empty Optional

Bad:

```java
Optional<String> name = Optional.empty();

System.out.println(name.get());
```

This causes:

```text
NoSuchElementException
```

Correct:

```java
System.out.println(name.orElse("Default Name"));
```

---

## Mistake 3: Using `Optional.of()` with Null

Bad:

```java
String value = null;

Optional<String> optional = Optional.of(value);
```

Correct:

```java
String value = null;

Optional<String> optional = Optional.ofNullable(value);
```

---

## Mistake 4: Returning Null Instead of Optional.empty()

Bad:

```java
public Optional<String> findName() {
    return null;
}
```

Correct:

```java
public Optional<String> findName() {
    return Optional.empty();
}
```

---

# 31. Interview Questions

## Question 1: What is Optional in Java?

`Optional` is a container object that may or may not contain a non-null value. It is used to represent missing values safely.

---

## Question 2: Why was Optional introduced?

`Optional` was introduced to reduce `NullPointerException` and make missing values more explicit.

---

## Question 3: What is the difference between `Optional.of()` and `Optional.ofNullable()`?

| Method                  | Description                            |
| ----------------------- | -------------------------------------- |
| `Optional.of()`         | Used when value is definitely not null |
| `Optional.ofNullable()` | Used when value may be null            |

---

## Question 4: What happens if we call `get()` on an empty Optional?

It throws `NoSuchElementException`.

---

## Question 5: What is the difference between `orElse()` and `orElseGet()`?

| Method        | Description                                          |
| ------------- | ---------------------------------------------------- |
| `orElse()`    | Default value is evaluated immediately               |
| `orElseGet()` | Default value is evaluated only if Optional is empty |

---

## Question 6: What is the difference between `map()` and `flatMap()`?

| Method      | Description                               |
| ----------- | ----------------------------------------- |
| `map()`     | Used when function returns a normal value |
| `flatMap()` | Used when function returns Optional       |

---

## Question 7: Should Optional be used as a class field?

Usually no. Optional is mainly recommended as a return type, not as a field.

---

# 32. Final Complete Example

```java
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class Student {
    private int id;
    private String name;
    private int marks;

    Student(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    int getMarks() {
        return marks;
    }
}

class StudentService {
    private List<Student> students = Arrays.asList(
            new Student(1, "John", 75),
            new Student(2, "Anne", 90),
            new Student(3, "Peter", 45)
    );

    Optional<Student> findStudentById(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst();
    }
}

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();

        String result = service.findStudentById(2)
                .filter(student -> student.getMarks() >= 50)
                .map(Student::getName)
                .map(String::toUpperCase)
                .orElse("Student not found or failed");

        System.out.println(result);
    }
}
```

### Output

```text
ANNE
```

### Explanation

This line searches for the student:

```java
service.findStudentById(2)
```

This returns:

```java
Optional<Student>
```

This line checks whether the student has passed:

```java
.filter(student -> student.getMarks() >= 50)
```

This line gets the student name:

```java
.map(Student::getName)
```

This line converts the name to uppercase:

```java
.map(String::toUpperCase)
```

This line returns a default message if the student is not found or failed:

```java
.orElse("Student not found or failed")
```

---

# 33. Simple Summary

`Optional` is a Java class used to handle values that may be missing.

Basic example:

```java
Optional<String> name = Optional.ofNullable(getName());
```

Instead of writing many null checks, we can write:

```java
String result = name
        .map(String::toUpperCase)
        .orElse("Unknown");
```

Important points:

* `Optional` can contain a value or be empty.
* It helps avoid `NullPointerException`.
* It works well with lambda expressions.
* It works well with method references.
* It works well with the Stream API.
* Use `ofNullable()` when a value may be null.
* Avoid using `get()` without checking.
* Prefer `map()`, `flatMap()`, `filter()`, `orElse()`, and `orElseGet()`.
