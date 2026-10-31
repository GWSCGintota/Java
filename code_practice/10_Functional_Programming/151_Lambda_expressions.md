[← 150. Functional interfaces](150_Functional_interfaces.md) | [Course Index](../README.md) | [152. Method references →](152_Method_references.md)

# Java Lambda Expressions Practice — 10 Coding Questions and Answers

These exercises follow [151. Lambda expressions](../../Road%20Map/10_Functional_Programming/151_Lambda_expressions.md), progressing from lambda syntax to reusable behavior and stream processing. Each question includes a model solution and a short explanation.

**Java version:** Java 8+. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports. Run exercises independently. Place helper methods and nested classes inside `Main`, and example statements inside `main()`. Inputs are non-null unless handled explicitly; arithmetic uses small values.

## Question 1 — Zero, One, and Two Parameters

**Task:** Write lambdas that supply `42`, square an integer, and add two integers. Invoke each through its functional interface.

```java
IntSupplier answer = () -> 42;
IntUnaryOperator square = number -> number * number;
IntBinaryOperator add = (a, b) -> a + b;
System.out.println(answer.getAsInt());
System.out.println(square.applyAsInt(5));
System.out.println(add.applyAsInt(3, 4));
```

**Output:**
```text
42
25
7
```

**Concept:** Lambda parameter count and result type come from the target interface. No parameters require `()`, and multiple parameters require parentheses.

## Question 2 — Convert an Expression to a Block

**Task:** Write a block lambda that trims a string, stores the trimmed value locally, and returns its length. Test `" Java "` and `""`.

```java
Function<String, Integer> trimmedLength = text -> {
    String trimmed = text.trim();
    return trimmed.length();
};
System.out.println(trimmedLength.apply(" Java "));
System.out.println(trimmedLength.apply(""));
```

**Output:**
```text
4
0
```

**Concept:** A value-returning block must return a compatible result on every path that completes normally. An action such as `Consumer` cannot return a value.

## Question 3 — Pass Arithmetic Lambdas

**Task:** Implement `calculate()` accepting an `IntBinaryOperator`. Use addition, subtraction, and multiplication with `10` and `5`.

```java
public static int calculate(int a, int b, IntBinaryOperator operation) {
    return Objects.requireNonNull(operation, "operation").applyAsInt(a, b);
}
```

**Example calls:**

```java
System.out.println(calculate(10, 5, (a, b) -> a + b));
System.out.println(calculate(10, 5, (a, b) -> a - b));
System.out.println(calculate(10, 5, (a, b) -> a * b));
```

**Output:**
```text
15
5
50
```

**Concept:** Passing behavior makes one method reusable. A lambda is executed when the method invokes its interface operation, rather than when the lambda is created.

## Question 4 — Sort by Length, Then Alphabetically

**Task:** Sort `[Bob, Grace, Ada, Alan]` by ascending length, with alphabetical order for equal lengths. Use a lambda comparator.

```java
List<String> names = new ArrayList<>(Arrays.asList("Bob", "Grace", "Ada", "Alan"));
names.sort((first, second) -> {
    int byLength = Integer.compare(first.length(), second.length());
    return byLength != 0 ? byLength : first.compareTo(second);
});
System.out.println(names);
```

**Output:**
```text
[Ada, Bob, Alan, Grace]
```

**Concept:** A comparator returns a negative value, zero, or a positive value. `Integer.compare()` avoids the overflow risk of subtraction-based numeric comparison.

## Question 5 — Capture a Minimum

**Task:** Return a predicate from `atLeast(minimum)`. Test ages `16` and `21` against a minimum of `18`.

```java
public static Predicate<Integer> atLeast(int minimum) {
    return age -> age >= minimum;
}
```

**Example calls:**

```java
Predicate<Integer> adult = atLeast(18);
System.out.println(adult.test(16));
System.out.println(adult.test(21));
```

**Output:**
```text
false
true
```

**Concept:** Captured parameters and local variables must be final or effectively final. Incrementing or reassigning `minimum` would make this capture invalid.

## Question 6 — Captured References Can Point to Mutable Objects

**Task:** Capture an event list in a consumer, add two messages, and print the list. Keep the local list reference effectively final.

```java
List<String> events = new ArrayList<>();
Consumer<String> record = message -> events.add(message);
record.accept("created");
record.accept("checked");
System.out.println(events);
```

**Output:**
```text
[created, checked]
```

**Concept:** Changing an object's contents does not reassign the captured reference. Adding `events = new ArrayList<>()` anywhere later in its scope would invalidate the lambda capture. Mutation still needs separate concurrency design.

## Question 7 — Null-Safe Predicate Composition

**Task:** Filter `[null, "", "  ", " Java ", "Ada"]` to retain non-null strings that are not blank after trimming. Preserve the original retained strings.

```java
Predicate<String> nonNull = text -> text != null;
Predicate<String> nonBlank = text -> !text.trim().isEmpty();
List<String> result = Arrays.asList(null, "", "  ", " Java ", "Ada").stream()
        .filter(nonNull.and(nonBlank))
        .collect(Collectors.toList());
System.out.println(result.size());
result.forEach(text -> System.out.println("[" + text + "]"));
```

**Output:**
```text
2
[ Java ]
[Ada]
```

**Concept:** `and()` short-circuits, so the null guard runs before any dereference. Filtering selects elements without transforming them. This Java 8 example uses `trim().isEmpty()` rather than `isBlank()`.

## Question 8 — Filter, Transform, and Sum

**Task:** From `[1, 2, 3, 4, 5, 6]`, select even numbers, square them, and calculate their sum using lambdas.

```java
int total = Arrays.asList(1, 2, 3, 4, 5, 6).stream()
        .filter(number -> number % 2 == 0)
        .mapToInt(number -> number * number)
        .sum();
System.out.println(total);
```

**Output:**
```text
56
```

**Concept:** `filter` and `mapToInt` are intermediate operations; `sum` triggers traversal. A reduction expresses the total without modifying captured shared state.

## Question 9 — Enclosing `this` in a Lambda

**Task:** Define a nested `Greeting` class with a message field and a method that returns a `Runnable`. Have the lambda print `this.message`, then invoke it.

```java
static class Greeting {
    private final String message;

    Greeting(String message) { this.message = message; }

    Runnable task() {
        return () -> System.out.println(this.message);
    }
}
```

**Example call:**

```java
new Greeting("Hello, Ada").task().run();
```

**Output:**
```text
Hello, Ada
```

**Concept:** A lambda uses the enclosing meaning of `this`; it does not create the anonymous-class-style meaning of `this`. A static context has no enclosing instance.

## Question 10 — Checked Exceptions and Target Types

**Task:** Create a `Callable<String>` that deliberately throws an `IOException`. Invoke it and print the checked exception's message. Keep handling compatible with `Callable.call()`.

```java
java.util.concurrent.Callable<String> task = () -> {
    throw new java.io.IOException("Cannot read input");
};
try {
    System.out.println(task.call());
} catch (Exception exception) {
    System.out.println(exception.getMessage());
}
```

**Output:**
```text
Cannot read input
```

**Concept:** `Callable.call()` permits checked exceptions. `Supplier.get()` does not, so this body cannot directly target `Supplier<String>` without handling or wrapping the failure. Creating the task does not throw the exception; invoking it does.

---

## Key Takeaways

| Syntax or API | Purpose | Example |
|---|---|---|
| `() -> value` | No-input behavior | `IntSupplier` |
| `value -> expression` | One-input behavior | Squaring a number |
| `(a, b) -> expression` | Two-input behavior | Addition |
| `{ ... return value; }` | Multi-statement value body | Trimmed length |
| `Comparator<T>` | Define ordering | Length, then name |
| Effectively final capture | Retain local configuration | Minimum age |
| `Predicate.and()` | Compose short-circuiting checks | Null guard first |
| `Callable<V>` | Allow a checked exception | `call()` |

**Reminder:** A lambda needs a functional interface target. It does not automatically run asynchronously, cache results, or make captured objects thread-safe.

[← 150. Functional interfaces](150_Functional_interfaces.md) | [Course Index](../README.md) | [152. Method references →](152_Method_references.md)
