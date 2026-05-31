# Question Paper: Java Functional Programming — Stream API

**Subject:** Java Programming  
**Topic:** Functional Programming — Stream API  
**Duration:** 2 Hours  
**Total Marks:** 100

---

## Instructions to Students

1. Answer all questions.
2. Write Java code clearly and use proper syntax.
3. For coding questions, imports may be assumed unless specifically requested.
4. Do not write unnecessary code.
5. Marks are shown in brackets.

---

## Section A: Multiple Choice Questions
**Answer all questions. Each question carries 1 mark.**  
**Total: 20 marks**

### Question 1
The Stream API was introduced in which Java version?

A. Java 5  
B. Java 6  
C. Java 8  
D. Java 11
```text
Write your answer here...
```

### Question 2
A Java Stream is mainly used to:

A. Store data permanently  
B. Process data from a source  
C. Replace all classes  
D. Create databases

### Question 3
Which of the following is a source for creating a stream?

A. `List`  
B. Array  
C. `Set`  
D. All of the above

### Question 4
Which method is used to create a stream from a collection?

A. `createStream()`  
B. `stream()`  
C. `newStream()`  
D. `collection()`

### Question 5
Which of the following is an intermediate operation?

A. `collect()`  
B. `count()`  
C. `filter()`  
D. `forEach()`

### Question 6
Which of the following is a terminal operation?

A. `map()`  
B. `filter()`  
C. `sorted()`  
D. `collect()`

### Question 7
What does the `filter()` method do?

A. Converts each element  
B. Selects elements based on a condition  
C. Sorts elements  
D. Counts elements

### Question 8
What does the `map()` method do?

A. Removes duplicate elements  
B. Transforms each element  
C. Stops the stream  
D. Creates a map only

### Question 9
Which method is used to remove duplicate values from a stream?

A. `unique()`  
B. `remove()`  
C. `distinct()`  
D. `delete()`

### Question 10
Which method is used to sort stream elements?

A. `order()`  
B. `arrange()`  
C. `sorted()`  
D. `compare()`

### Question 11
Which method is used to collect stream results into a list?

A. `toListOnly()`  
B. `Collectors.toList()`  
C. `list.collect()`  
D. `stream.list()`

### Question 12
Which method is mainly used to combine stream elements into one result?

A. `reduce()`  
B. `combineAll()`  
C. `merge()`  
D. `joinAll()`

### Question 13
Which method returns the number of elements in a stream?

A. `size()`  
B. `length()`  
C. `count()`  
D. `total()`

### Question 14
Which method is mainly used to flatten nested collections?

A. `map()`  
B. `flatMap()`  
C. `filter()`  
D. `peek()`

### Question 15
What does lazy evaluation mean in streams?

A. Streams execute before the program starts  
B. Intermediate operations execute only when a terminal operation is called  
C. Streams always execute slowly  
D. Streams cannot execute

### Question 16
Can a Java stream be reused after a terminal operation?

A. Yes  
B. No  
C. Only in Java 8  
D. Only for arrays

### Question 17
Which method is useful for debugging stream pipelines?

A. `debug()`  
B. `peek()`  
C. `trace()`  
D. `watch()`

### Question 18
Which stream is used for primitive `int` values?

A. `IntegerStream`  
B. `NumberStream`  
C. `IntStream`  
D. `PrimitiveStream`

### Question 19
Which method can group objects by a field?

A. `Collectors.groupingBy()`  
B. `Collectors.joining()`  
C. `Collectors.toSet()`  
D. `Collectors.partitioningBy()`

### Question 20
A parallel stream processes data using:

A. Only one thread  
B. Multiple threads  
C. No threads  
D. Only the main method

---

## Section B: Short Answer Questions
**Answer all questions. Each question carries 3 marks.**  
**Total: 30 marks**

### Question 1
Define Java Stream API.

### Question 2
Explain why the Stream API is useful in Java programming.

### Question 3
Write the basic structure of a stream pipeline.

### Question 4
Explain the difference between a stream source, an intermediate operation, and a terminal operation.

### Question 5
Differentiate between intermediate operations and terminal operations.

### Question 6
Explain the difference between `filter()` and `map()`.

### Question 7
Explain the difference between `map()` and `flatMap()`.

### Question 8
What is lazy evaluation in streams? Give a simple example.

### Question 9
Why can a stream not be reused?

### Question 10
Write three advantages and three disadvantages of using Stream API.

---

## Section C: Code Output Questions
**Answer all questions. Each question carries 4 marks.**  
**Total: 20 marks**

### Question 1
What is the output of the following code?

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 15, 20, 25, 30);

        List<Integer> result = numbers.stream()
                .filter(number -> number % 2 == 0)
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

### Question 2
What is the output of the following code?

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("john", "anne", "peter");

        List<String> result = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

### Question 3
What is the output of the following code?

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 4);

        List<Integer> result = numbers.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

### Question 4
What is the output of the following code?

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

### Question 5
What is the output of the following code?

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        int total = numbers.stream()
                .reduce(0, Integer::sum);

        System.out.println(total);
    }
}
```

---

## Section D: Practical Programming Questions
**Answer all questions. Each question carries 10 marks.**  
**Total: 30 marks**

### Question 1
Write a Java program using Stream API to filter even numbers from the following list and collect them into a new list.

```java
List<Integer> numbers = Arrays.asList(5, 10, 15, 20, 25, 30);
```

Your program should display:

```text
[10, 20, 30]
```

### Question 2
Write a Java program using Stream API to convert all names in the following list to uppercase and sort them alphabetically.

```java
List<String> names = Arrays.asList("peter", "anne", "john", "mary");
```

Expected output:

```text
[ANNE, JOHN, MARY, PETER]
```

### Question 3
Create an `Employee` class with the following fields:

- `name`
- `department`
- `salary`

Then create a list of employees and use Stream API to:

1. Filter employees whose salary is greater than or equal to 60000.
2. Sort them by salary in descending order.
3. Collect only their names into a list.
4. Print the final list.

Use the following sample data:

```java
new Employee("John", "IT", 50000)
new Employee("Anne", "HR", 70000)
new Employee("Peter", "IT", 40000)
new Employee("Mary", "Finance", 90000)
```

Expected output:

```text
[Mary, Anne]
```

---

## Section E: Additional Long Answer Practice Questions
**Optional practice section. Not included in the 100 marks.**

### Question 1
Explain the main concepts of the Java Stream API. Your answer should include:

- Stream source
- Intermediate operations
- Terminal operations
- Stream pipeline
- Lazy evaluation

### Question 2
Explain the difference between using normal loops and using Stream API for collection processing. Include one suitable example.

### Question 3
Explain the following Stream API methods with examples:

- `filter()`
- `map()`
- `flatMap()`
- `collect()`
- `reduce()`

### Question 4
Discuss best practices and common mistakes when using Java Stream API.

Your answer should include:

- Keeping stream pipelines readable
- Using method references
- Avoiding side effects
- Not reusing streams
- Using parallel streams carefully

---

## Section F: Additional Scenario-Based Practice Question
**Optional practice section. Not included in the 100 marks.**

### Question 1
A company has a list of students. Each student has a name, course, and marks.

Write a Java program using Stream API to:

1. Filter students who passed. A student passes if marks are greater than or equal to 50.
2. Sort the passed students by marks in descending order.
3. Collect only the names of passed students into a list.
4. Group all students by course.
5. Calculate the average marks of all students.

Use the following sample data:

```java
new Student("John", "Java", 75)
new Student("Anne", "Java", 90)
new Student("Peter", "Python", 45)
new Student("Mary", "Python", 82)
new Student("Alex", "Java", 60)
```

Expected output should include:

```text
Passed students: [Anne, Mary, John, Alex]
Students by course: ...
Average marks: 70.4
```

---

## Mark Allocation Summary

| Section | Topic | Marks |
|---|---|---:|
| Section A | Multiple Choice Questions | 20 |
| Section B | Short Answer Questions | 30 |
| Section C | Code Output Questions | 20 |
| Section D | Practical Programming Questions | 30 |

---

---

# Answer Key and Suggested Answers

## Section A: Multiple Choice Answers

| Question | Answer |
|---|---|
| 1 | C. Java 8 |
| 2 | B. Process data from a source |
| 3 | D. All of the above |
| 4 | B. `stream()` |
| 5 | C. `filter()` |
| 6 | D. `collect()` |
| 7 | B. Selects elements based on a condition |
| 8 | B. Transforms each element |
| 9 | C. `distinct()` |
| 10 | C. `sorted()` |
| 11 | B. `Collectors.toList()` |
| 12 | A. `reduce()` |
| 13 | C. `count()` |
| 14 | B. `flatMap()` |
| 15 | B. Intermediate operations execute only when a terminal operation is called |
| 16 | B. No |
| 17 | B. `peek()` |
| 18 | C. `IntStream` |
| 19 | A. `Collectors.groupingBy()` |
| 20 | B. Multiple threads |

---

## Section B: Short Answer Suggested Answers

### Answer 1
The Java Stream API is a feature introduced in Java 8 that allows collections and other data sources to be processed in a functional programming style.

### Answer 2
The Stream API is useful because it makes data processing shorter, cleaner, more readable, easier to chain, and easier to parallelize. It works well with lambda expressions, functional interfaces, and method references.

### Answer 3
Basic stream pipeline structure:

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

### Answer 4
A stream source is where the data comes from, such as a `List`, `Set`, array, or file. An intermediate operation processes the stream and returns another stream, such as `filter()` or `map()`. A terminal operation produces the final result, such as `collect()`, `count()`, or `forEach()`.

### Answer 5

| Intermediate Operation | Terminal Operation |
|---|---|
| Returns another stream | Produces the final result |
| Lazy | Executes the pipeline |
| Can be chained | Ends the stream pipeline |
| Examples: `filter()`, `map()`, `sorted()` | Examples: `collect()`, `count()`, `forEach()` |

### Answer 6
`filter()` is used to select elements based on a condition. `map()` is used to transform each element into another form.

Example:

```java
names.stream()
     .filter(name -> name.length() > 4)
     .map(String::toUpperCase)
     .collect(Collectors.toList());
```

### Answer 7
`map()` transforms each element but keeps the stream structure. `flatMap()` transforms elements and also flattens nested streams or nested collections into one stream.

Example:

```java
List<String> allNames = nestedNames.stream()
        .flatMap(List::stream)
        .collect(Collectors.toList());
```

### Answer 8
Lazy evaluation means intermediate operations do not run until a terminal operation is called.

Example:

```java
names.stream()
     .filter(name -> name.startsWith("A"));
```

In this code, nothing happens because there is no terminal operation.

### Answer 9
A stream cannot be reused because a terminal operation consumes the stream. After the stream is consumed, another operation on the same stream causes an error. A new stream should be created if another operation is needed.

### Answer 10
Advantages:

- Reduces boilerplate code
- Improves readability for data processing
- Supports functional programming and method chaining

Disadvantages:

- Can be difficult for beginners
- Debugging can be harder
- Streams cannot be reused

---

## Section C: Code Output Answers

### Answer 1

```text
[10, 20, 30]
```

### Answer 2

```text
[JOHN, ANNE, PETER]
```

### Answer 3

```text
[1, 2, 3, 4]
```

### Answer 4

```text
2
```

### Answer 5

```text
15
```

---

## Section D: Practical Programming Suggested Answers

### Answer 1

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 10, 15, 20, 25, 30);

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

### Answer 2

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anne", "john", "mary");

        List<String> result = names.stream()
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

Output:

```text
[ANNE, JOHN, MARY, PETER]
```

### Answer 3

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

## Section E: Optional Long Answer Practice — Suggested Answer Points

### Answer 1
A good answer should explain that a stream processes data from a source such as a collection or array. It should describe intermediate operations such as `filter()`, `map()`, and `sorted()`, terminal operations such as `collect()`, `count()`, and `forEach()`, and how a stream pipeline chains these operations together. It should also mention lazy evaluation, where intermediate operations execute only after a terminal operation is called.

### Answer 2
A normal loop processes data step by step using explicit iteration. Stream API processes data using a functional pipeline. Streams usually make filtering, mapping, sorting, and collecting shorter and more readable.

Example:

```java
List<Integer> evenNumbers = numbers.stream()
        .filter(number -> number % 2 == 0)
        .collect(Collectors.toList());
```

### Answer 3

- `filter()` selects elements based on a condition.
- `map()` transforms each element.
- `flatMap()` flattens nested collections or nested streams.
- `collect()` collects the result into a collection or other result type.
- `reduce()` combines elements into a single value.

Example:

```java
int total = numbers.stream()
        .reduce(0, Integer::sum);
```

### Answer 4
Best practices include keeping stream pipelines readable, using method references when they improve clarity, avoiding side effects, not reusing streams, and using parallel streams carefully. Common mistakes include forgetting a terminal operation, reusing a consumed stream, using `map()` instead of `flatMap()` for nested lists, and modifying shared data inside a parallel stream.

---

## Section F: Optional Scenario-Based Practice — Suggested Answer

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

        Map<String, List<Student>> studentsByCourse = students.stream()
                .collect(Collectors.groupingBy(Student::getCourse));

        double averageMarks = students.stream()
                .mapToInt(Student::getMarks)
                .average()
                .orElse(0);

        System.out.println("Passed students: " + passedStudentNames);
        System.out.println("Students by course: " + studentsByCourse);
        System.out.println("Average marks: " + averageMarks);
    }
}
```

Expected output:

```text
Passed students: [Anne, Mary, John, Alex]
Students by course: {Java=[John - Java - 75, Anne - Java - 90, Alex - Java - 60], Python=[Peter - Python - 45, Mary - Python - 82]}
Average marks: 70.4
```

---

## End of Question Paper
