[← 152. Method references](152_Method_references.md) | [Course Index](../README.md) | [154. Function →](154_Function.md)


# Java Predicate Practice — 10 Coding Questions and Answers

These exercises follow our practice conversation, progressing from basic predicates to generic filtering and dynamic predicate composition. Each question includes a model solution and a short explanation.

**Java version:** Java 8+ (`Collectors.toList()` is used for compatibility). Code snippets assume the relevant `java.util.*`, `java.util.function.Predicate`, and `java.util.stream.Collectors` imports unless shown otherwise.

## Question 1 — Positive and Even

**Task:** Create a `Predicate<Integer>` that checks whether a number is both positive and even. Test `10`, `7`, `-4`, and `0`.

```java
Predicate<Integer> even = n -> n % 2 == 0;
Predicate<Integer> positive = n -> n > 0;
Predicate<Integer> positiveEven = even.and(positive);

for (int n : new int[]{10, 7, -4, 0}) {
    System.out.println(n + " -> " + positiveEven.test(n));
}
```

**Output:**
```text
10 -> true
7 -> false
-4 -> false
0 -> false
```

**Concept:** `and()` requires both predicates to be true.

## Question 2 — Starts with A OR Longer Than Five Characters

**Task:** Check whether a string starts with `A` **or** has more than five characters. Test `Apple`, `Banana`, `Cat`, and `Avocado`.

```java
Predicate<String> startsWithA = s -> s.startsWith("A");
Predicate<String> longerThanFive = s -> s.length() > 5;
Predicate<String> matches = startsWithA.or(longerThanFive);

for (String s : Arrays.asList("Apple", "Banana", "Cat", "Avocado")) {
    System.out.println(s + " -> " + matches.test(s));
}
```

**Output:**
```text
Apple -> true
Banana -> true
Cat -> false
Avocado -> true
```

**Concept:** `or()` requires at least one predicate to be true.

## Question 3 — Using `negate()`

**Task:** Identify numbers **not divisible by 3**, using `negate()`. Test `3`, `7`, `9`, `10`, and `15`.

```java
Predicate<Integer> divisibleByThree = n -> n % 3 == 0;
Predicate<Integer> notDivisibleByThree = divisibleByThree.negate();

for (int n : new int[]{3, 7, 9, 10, 15}) {
    System.out.println(n + " -> " + notDivisibleByThree.test(n));
}
```

**Output:**
```text
3 -> false
7 -> true
9 -> false
10 -> true
15 -> false
```

**Concept:** `negate()` reverses a predicate's boolean result. Writing `n -> n % 3 != 0` is logically equivalent, but does not demonstrate `negate()`.

## Question 4 — Filter Numbers with Two Conditions

**Task:** From `[5, 12, 18, 7, 20, 3, 25, 30]`, select numbers greater than 10 **and** divisible by 5.

```java
List<Integer> numbers = Arrays.asList(5, 12, 18, 7, 20, 3, 25, 30);
Predicate<Integer> greaterThanTen = n -> n > 10;
Predicate<Integer> divisibleByFive = n -> n % 5 == 0;
Predicate<Integer> matches = greaterThanTen.and(divisibleByFive);

List<Integer> result = numbers.stream()
        .filter(matches)
        .collect(Collectors.toList());
System.out.println(result);
```

**Output:**
```text
[20, 25, 30]
```

**Concept:** `Stream.filter()` accepts a predicate, so calling `test()` explicitly is unnecessary.

## Question 5 — Combine `and()`, `or()`, and `negate()`

**Task:** From `[2, 5, 8, 10, 12, 15, 18, 20, 25, 30]`, select numbers satisfying **(even AND greater than 10) OR (NOT divisible by 5)**.

```java
List<Integer> numbers = Arrays.asList(2, 5, 8, 10, 12, 15, 18, 20, 25, 30);
Predicate<Integer> isEven = n -> n % 2 == 0;
Predicate<Integer> greaterThanTen = n -> n > 10;
Predicate<Integer> divisibleByFive = n -> n % 5 == 0;

Predicate<Integer> matches = isEven.and(greaterThanTen)
        .or(divisibleByFive.negate());

System.out.println(numbers.stream()
        .filter(matches)
        .collect(Collectors.toList()));
```

**Output:**
```text
[2, 8, 12, 18, 20, 30]
```

**Concept:** Predicates can be composed into larger logical expressions; `and()` and `or()` short-circuit.

## Shared Employee Class and Data (Questions 6–10)

Use this class and sample data for the remaining exercises:

```java
class Employee {
    String name;
    int age;
    double salary;
    String department;

    Employee(String name, int age, double salary, String department) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }
}

List<Employee> employees = Arrays.asList(
    new Employee("John", 28, 75000, "IT"),
    new Employee("Alice", 35, 95000, "IT"),
    new Employee("Bob", 40, 85000, "HR"),
    new Employee("David", 32, 110000, "IT"),
    new Employee("Emma", 26, 65000, "Finance")
);
```

Place the `employees` declaration inside a method such as `main()`. Place the `Employee` class outside the `Main` class (or as a suitable nested class).

## Question 6 — Predicates with Custom Objects

**Task:** Print employees who work in IT, earn more than 80,000, and are older than 30. Create three separate predicates and combine them with `and()`.

```java
Predicate<Employee> isIT = e -> "IT".equals(e.department);
Predicate<Employee> salaryAbove80K = e -> e.salary > 80000;
Predicate<Employee> ageAbove30 = e -> e.age > 30;

Predicate<Employee> eligible = isIT.and(salaryAbove80K).and(ageAbove30);
employees.stream()
        .filter(eligible)
        .forEach(e -> System.out.println(e.name));
```

**Output:**
```text
Alice
David
```

**Concept:** For Java string value comparison, use `.equals()`, not `==` (which compares references).

## Question 7 — Reusable Employee Filtering Method

**Task:** Implement `filterEmployees(List<Employee>, Predicate<Employee>)`. Use it to find (1) salaries above 80,000 and (2) IT employees older than 30.

```java
public static List<Employee> filterEmployees(
        List<Employee> employees,
        Predicate<Employee> condition) {
    return employees.stream()
            .filter(condition)
            .collect(Collectors.toList());
}
```

**Example calls inside `main()`:**

```java
Predicate<Employee> salaryAbove80K = e -> e.salary > 80000;
Predicate<Employee> isIT = e -> "IT".equals(e.department);
Predicate<Employee> ageAbove30 = e -> e.age > 30;

System.out.println("Salary > 80000:");
filterEmployees(employees, salaryAbove80K)
        .forEach(e -> System.out.println(e.name));

System.out.println("IT and Age > 30:");
filterEmployees(employees, isIT.and(ageAbove30))
        .forEach(e -> System.out.println(e.name));
```

**Output:**
```text
Salary > 80000:
Alice
Bob
David
IT and Age > 30:
Alice
David
```

**Concept:** Passing a predicate to a method lets callers supply different filtering behavior.

## Question 8 — Generic Filtering with `<T>`

**Task:** Write a reusable generic method `filterItems()` that filters both integers and strings. Select numbers greater than 15 and names beginning with A.

```java
public static <T> List<T> filterItems(
        List<T> items,
        Predicate<T> condition) {
    return items.stream()
            .filter(condition)
            .collect(Collectors.toList());
}
```

**Example calls:**

```java
List<Integer> numbers = Arrays.asList(5, 10, 15, 20, 25, 30);
List<String> names = Arrays.asList("John", "Alice", "Bob", "Alexander", "Emma");

System.out.println(filterItems(numbers, n -> n > 15));
System.out.println(filterItems(names, s -> s.startsWith("A")));
```

**Output:**
```text
[20, 25, 30]
[Alice, Alexander]
```

**Concept:** Java infers `T` as `Integer` or `String` from the call. The same method works for both.

## Question 9 — Dynamic Predicate Composition Using `reduce()`

**Task:** Accept a list of employee predicates, combine all with logical AND, and filter employees. Return all employees if the condition list is empty.

```java
public static List<Employee> filterEmployees(
        List<Employee> employees,
        List<Predicate<Employee>> conditions) {

    Predicate<Employee> combined = conditions.stream()
            .reduce(e -> true, Predicate::and);

    return employees.stream()
            .filter(combined)
            .collect(Collectors.toList());
}
```

**Example:**

```java
List<Predicate<Employee>> conditions = Arrays.asList(
    e -> "IT".equals(e.department),
    e -> e.salary > 80000,
    e -> e.age > 30
);

filterEmployees(employees, conditions)
        .forEach(e -> System.out.println(e.name));
```

**Output:**
```text
Alice
David
```

**Concept:** `reduce(e -> true, Predicate::and)` starts with the identity for logical AND. Each predicate is combined into one predicate. An empty condition list produces an always-true predicate.

Equivalent loop:

```java
Predicate<Employee> combined = e -> true;
for (Predicate<Employee> condition : conditions) {
    combined = combined.and(condition);
}
```

## Question 10 — Dynamic AND/OR Employee Search

**Task:** Implement `searchEmployees(employees, conditions, matchAll)`. When `matchAll` is `true`, require all conditions (AND). Otherwise, accept any condition (OR). If conditions are empty, return all employees in either mode.

```java
public static List<Employee> searchEmployees(
        List<Employee> employees,
        List<Predicate<Employee>> conditions,
        boolean matchAll) {

    if (conditions.isEmpty()) {
        return new ArrayList<>(employees);
    }

    Predicate<Employee> combined = matchAll
            ? conditions.stream().reduce(e -> true, Predicate::and)
            : conditions.stream().reduce(e -> false, Predicate::or);

    return employees.stream()
            .filter(combined)
            .collect(Collectors.toList());
}
```

**Example calls:**

```java
List<Predicate<Employee>> conditions = Arrays.asList(
    e -> "IT".equals(e.department),
    e -> e.salary > 100000
);

System.out.println("AND:");
searchEmployees(employees, conditions, true)
        .forEach(e -> System.out.println(e.name));

System.out.println("OR:");
searchEmployees(employees, conditions, false)
        .forEach(e -> System.out.println(e.name));
```

**Output:**
```text
AND:
David
OR:
John
Alice
David
```

**Concept:** `true` is the identity for AND, and `false` is the identity for OR. The explicit empty-list check is necessary because this exercise requires returning all employees in either mode.

---

## Key Takeaways

| API | Purpose | Example |
|---|---|---|
| `Predicate<T>` | Represent a boolean condition | `n -> n > 10` |
| `test(value)` | Evaluate a predicate | `predicate.test(20)` |
| `and(other)` | Require both conditions | `a.and(b)` |
| `or(other)` | Require at least one | `a.or(b)` |
| `negate()` | Reverse a condition | `a.negate()` |
| `Stream.filter()` | Keep matching elements | `.filter(predicate)` |
| `Stream.reduce()` | Combine stream elements into one result | `.reduce(e -> true, Predicate::and)` |
| `Predicate::and` | Method reference for predicate combination | `Predicate::and` |
| `<T>` | Make filtering generic | `<T> List<T> filterItems(...)` |

**Reminder:** `reduce()` is a Stream API method, **not** a Java keyword or a method on `Predicate` itself. In these exercises, it combines a stream of predicates into a single predicate.


[← 152. Method references](152_Method_references.md) | [Course Index](../README.md) | [154. Function →](154_Function.md)
