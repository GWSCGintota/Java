[← 153. Predicate](153_Predicate.md) | [Course Index](../README.md) | [155. Consumer →](155_Consumer.md)

# Java Function Practice — 10 Coding Questions and Answers

[← Road Map lesson](../../Road%20Map/10_Functional_Programming/154_Function.md)

These exercises follow [154. Function](../../Road%20Map/10_Functional_Programming/154_Function.md), progressing from basic transformations to generic mapping and dynamic function composition. Each question includes a model solution and a short explanation.

**Java version:** Java 8+. Snippets assume `java.util.*`, `java.util.function.*`, and `java.util.stream.Collectors` imports. Place statements inside `main()` and helper methods inside the enclosing class. Run each exercise independently. Inputs are non-null unless a task specifies otherwise; numeric inputs are small enough to avoid overflow.

## Question 1 — Trimmed String Length

**Task:** Create a `Function<String, Integer>` that returns the length after trimming leading and trailing spaces. Test `" Java "`, `"Lambda"`, and `""`.

```java
Function<String, Integer> trimmedLength = text -> text.trim().length();
for (String text : Arrays.asList(" Java ", "Lambda", "")) {
    System.out.println(trimmedLength.apply(text));
}
```

**Output:**
```text
4
6
0
```

**Concept:** `Function<T, R>` transforms a `T` into an `R`. Invoke it with `apply()`. Generic type arguments use `Integer`, not `int`.

## Question 2 — Build an `andThen()` Pipeline

**Task:** Create separate functions to trim text, parse an integer, double it, and format it as `Result: value`. Combine them with `andThen()` and test `" 21 "`.

```java
Function<String, String> trim = String::trim;
Function<String, Integer> parse = Integer::parseInt;
Function<Integer, Integer> doubleIt = n -> n * 2;
Function<Integer, String> format = n -> "Result: " + n;

Function<String, String> pipeline = trim.andThen(parse)
        .andThen(doubleIt)
        .andThen(format);
System.out.println(pipeline.apply(" 21 "));
```

**Output:**
```text
Result: 42
```

**Concept:** `andThen()` executes stages from left to right when the combined function is applied. Each result type must match what the next stage accepts. Invalid integer text throws `NumberFormatException`.

## Question 3 — Compare `compose()` and `andThen()`

**Task:** Create increment and square functions. Predict, then print, the results of `square.compose(increment)` and `square.andThen(increment)` for input `3`.

```java
Function<Integer, Integer> increment = n -> n + 1;
Function<Integer, Integer> square = n -> n * n;

System.out.println(square.compose(increment).apply(3));
System.out.println(square.andThen(increment).apply(3));
```

**Output:**
```text
16
10
```

**Concept:** `f.compose(g)` means `f(g(input))`; `f.andThen(g)` means `g(f(input))`. Composition order changes the result.

## Question 4 — Map a List and Sum Lengths

**Task:** Transform `[1, 2, 3]` into item labels using a `Function`. Then use a `ToIntFunction<String>` to sum the lengths of `Ada` and `Grace`.

```java
Function<Integer, String> label = n -> "Item " + n;
List<String> labels = Arrays.asList(1, 2, 3).stream()
        .map(label)
        .collect(Collectors.toList());
System.out.println(labels);

ToIntFunction<String> length = String::length;
int totalLength = Arrays.asList("Ada", "Grace").stream()
        .mapToInt(length)
        .sum();
System.out.println(totalLength);
```

**Output:**
```text
[Item 1, Item 2, Item 3]
8
```

**Concept:** `map()` transforms elements; `filter()` selects them. `ToIntFunction` returns primitive `int` through `applyAsInt()` and can be passed to `mapToInt()`.

## Question 5 — Identity and Duplicate Map Keys

**Task:** Build a name-to-length map from `[Ada, Ada, Grace]` using `Function.identity()`. Keep the existing value for duplicate keys. Also demonstrate that identity returns the original mutable list reference.

```java
Map<String, Integer> lengths = Arrays.asList("Ada", "Ada", "Grace").stream()
        .collect(Collectors.toMap(
                Function.identity(), String::length,
                (existing, incoming) -> existing));
System.out.println(lengths.get("Ada"));
System.out.println(lengths.get("Grace"));

List<String> original = new ArrayList<>();
Function<List<String>, List<String>> identity = Function.identity();
System.out.println(identity.apply(original) == original);
```

**Output:**
```text
3
5
true
```

**Concept:** Identity returns its input without copying it. The merge function explicitly resolves duplicate keys; printing individual lookups avoids depending on map iteration order.

## Shared Employee Class and Data (Questions 6–7)

Place this class outside the enclosing `Main` class:

```java
class Employee {
    final String name;
    final int age;
    final String department;

    Employee(String name, int age, String department) {
        this.name = name;
        this.age = age;
        this.department = department;
    }
}
```

Create this sample data inside `main()`:

```java
List<Employee> employees = Arrays.asList(
        new Employee("John", 28, "IT"),
        new Employee("Alice", 35, "IT"),
        new Employee("Bob", 40, "HR"));
```

## Question 6 — Transform Custom Objects

**Task:** Map employees to strings in the format `Name (Department)`. Define a null employee policy that returns `Unknown`, and test it explicitly.

```java
Function<Employee, String> summary = employee -> employee == null
        ? "Unknown"
        : employee.name + " (" + employee.department + ")";

System.out.println(employees.stream()
        .map(summary)
        .collect(Collectors.toList()));
System.out.println(summary.apply(null));
```

**Output:**
```text
[John (IT), Alice (IT), Bob (HR)]
Unknown
```

**Concept:** Functions can transform domain objects into display values. Null handling must be deliberate; `Function` does not supply it automatically.

## Question 7 — Generic Transformation Method

**Task:** Implement `transform()` using `Function<? super T, ? extends R>`. Use it to extract employee names and convert strings into lengths. Return a new list in input order.

```java
public static <T, R> List<R> transform(
        List<T> items, Function<? super T, ? extends R> mapper) {
    Objects.requireNonNull(items, "items");
    Objects.requireNonNull(mapper, "mapper");
    List<R> result = new ArrayList<>();
    for (T item : items) {
        result.add(mapper.apply(item));
    }
    return result;
}
```

**Example calls inside `main()`:**

```java
System.out.println(transform(employees, employee -> employee.name));
System.out.println(transform(Arrays.asList("Ada", "Grace"), String::length));
```

**Output:**
```text
[John, Alice, Bob]
[3, 5]
```

**Concept:** `T` and `R` let the same method transform different input and output types. The wildcards allow a mapper accepting a supertype of `T` and returning a subtype of `R`.

## Question 8 — Return a Configurable Function

**Task:** Write `multiplier(int factor)` returning a function that multiplies its input by the captured factor. Create separate triple and five-times functions and apply both to `7`.

```java
public static Function<Integer, Integer> multiplier(int factor) {
    return value -> value * factor;
}
```

**Example calls:**

```java
Function<Integer, Integer> triple = multiplier(3);
Function<Integer, Integer> fiveTimes = multiplier(5);
System.out.println(triple.apply(7));
System.out.println(fiveTimes.apply(7));
```

**Output:**
```text
21
35
```

**Concept:** A method can return reusable behavior. Captured local variables and parameters must be final or effectively final; each call here captures its own factor.

## Question 9 — Safe Parsing with `Optional.flatMap()`

**Task:** Create a function returning `Optional<Integer>`. Trim and parse valid text; return empty for null, malformed, or out-of-range input. Use `Optional.flatMap()` and print `-1` when parsing fails. Test `" 42 "`, `"oops"`, `"2147483648"`, and null.

```java
Function<String, Optional<Integer>> parseSafely = text -> {
    if (text == null) {
        return Optional.empty();
    }
    try {
        return Optional.of(Integer.parseInt(text.trim()));
    } catch (NumberFormatException exception) {
        return Optional.empty();
    }
};

for (String text : Arrays.asList(" 42 ", "oops", "2147483648", null)) {
    Optional<Integer> result = Optional.ofNullable(text).flatMap(parseSafely);
    System.out.println(result.orElse(-1));
}
System.out.println(parseSafely.apply(null).isPresent());
```

**Output:**
```text
42
-1
-1
-1
false
```

**Concept:** `flatMap()` avoids `Optional<Optional<Integer>>`. An empty optional skips the mapper; the final call separately verifies the function's null policy. The fallback `-1` is a display choice, not a way to distinguish failure from a valid parsed `-1`.

## Question 10 — Dynamic Function Composition

**Task:** Accept a list of `Function<String, String>` stages and combine them in list order. An empty stage list must return its input unchanged. Test trimming, uppercasing with `Locale.ROOT`, and adding square brackets.

```java
public static Function<String, String> composeAll(
        List<Function<String, String>> stages) {
    Function<String, String> combined = Function.identity();
    for (Function<String, String> stage : stages) {
        combined = combined.andThen(stage);
    }
    return combined;
}
```

**Example calls:**

```java
List<Function<String, String>> stages = Arrays.asList(
        String::trim,
        text -> text.toUpperCase(Locale.ROOT),
        text -> "[" + text + "]");

System.out.println(composeAll(stages).apply(" java "));
System.out.println(composeAll(Collections.emptyList()).apply("Java"));
```

**Output:**
```text
[JAVA]
Java
```

**Concept:** Identity is the starting transformation for a dynamic pipeline. Building the pipeline does not run it. Each invocation executes its stages in order; if a stage throws, later stages do not run. This exercise assumes a non-null list containing non-null stages.

---

## Key Takeaways

| API | Purpose | Example |
|---|---|---|
| `Function<T, R>` | Transform one input into a result | `text -> text.length()` |
| `apply(value)` | Execute a function | `length.apply("Java")` |
| `andThen(after)` | Transform, then run the next function | `trim.andThen(parse)` |
| `compose(before)` | Preprocess input before this function | `square.compose(increment)` |
| `Function.identity()` | Return the original input | Starting point for a pipeline |
| `Stream.map()` | Transform stream elements | `.map(summary)` |
| `ToIntFunction<T>` | Transform an object into primitive `int` | `String::length` |
| `Optional.flatMap()` | Apply an optional-returning mapper without nesting | `.flatMap(parseSafely)` |
| `<T, R>` | Make transformation generic | `<T, R> List<R> transform(...)` |

**Reminder:** A function does not automatically validate input, copy objects, cache results, or handle exceptions. Define these policies explicitly when required.

---

## Interview Questions and Answers

### Interview Question 1 — How do compose and andThen differ?

**Answer:** f.compose(g) applies g first and then f. f.andThen(g) applies f first and then g. Intermediate result types must be compatible with the next input type.

### Interview Question 2 — Does Function.identity create a copy?

**Answer:** No. It returns the same input, including the same object reference or null. Copying requires an explicit transformation with a defined copy depth.

### Interview Question 3 — What happens if a composed stage throws?

**Answer:** The exception propagates and subsequent stages are skipped. Composition does not provide exception recovery, rollback, caching, or asynchronous execution.

### Interview Question 4 — How do Function and UnaryOperator differ?

**Answer:** Function<T, R> permits different input and result types. UnaryOperator<T> specializes the same-type case by extending Function<T, T>.

### Interview Question 5 — Why use primitive function specializations?

**Answer:** They express primitive parameters or results without boxing for those positions. They are separate interfaces with methods such as applyAsInt, and their composition APIs differ.


[← 153. Predicate](153_Predicate.md) | [Course Index](../README.md) | [155. Consumer →](155_Consumer.md)
