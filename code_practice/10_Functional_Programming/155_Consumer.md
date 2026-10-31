[← 154. Function](154_Function.md) | [Course Index](../README.md) | [156. Supplier →](156_Supplier.md)

# Java Consumer Practice — 10 Coding Questions and Answers

These exercises follow [155. Consumer](../../Road%20Map/10_Functional_Programming/155_Consumer.md), progressing from basic actions to generic processing and dynamic consumer composition. Each question includes a model solution and a short explanation.

**Java version:** Java 8+. Snippets assume `java.util.*`, `java.util.function.*`, `java.util.stream.Collectors`, and `java.util.stream.IntStream` imports. Place statements inside `main()` and helper methods inside the enclosing class. Run each exercise independently. Inputs are non-null unless a task specifies otherwise; numeric examples use small values that do not overflow.

## Question 1 — Print a String and Its Length

**Task:** Create a `Consumer<String>` that prints a string and its length on separate lines. Invoke it with `Java`.

```java
Consumer<String> describe = text -> {
    System.out.println("Input: " + text);
    System.out.println("Length: " + text.length());
};
describe.accept("Java");
```

**Output:**
```text
Input: Java
Length: 4
```

**Concept:** `Consumer<T>` accepts an input and performs an action. Invoke it with `accept()`, which returns `void`.

## Question 2 — Chain Actions with `andThen()`

**Task:** Create two consumers that record `first:value` and `second:value` in an event list. Combine them using `andThen()`. Print the list before and after accepting `A`.

```java
List<String> events = new ArrayList<>();
Consumer<String> first = value -> events.add("first:" + value);
Consumer<String> second = value -> events.add("second:" + value);
Consumer<String> combined = first.andThen(second);

System.out.println(events);
combined.accept("A");
System.out.println(events);
```

**Output:**
```text
[]
[first:A, second:A]
```

**Concept:** Composition does not execute either action. On invocation, both consumers receive the same input in left-to-right order. No result is passed between them.

## Question 3 — Process a Collection with a Method Reference

**Task:** Use a consumer backed by `List.add` to copy `[Ada, Grace, Alan]` into a separate mutable list. Print the copy using `forEach()` and a method reference.

```java
List<String> names = Arrays.asList("Ada", "Grace", "Alan");
List<String> copy = new ArrayList<>();
Consumer<String> addName = copy::add;

names.forEach(addName);
copy.forEach(System.out::println);
```

**Output:**
```text
Ada
Grace
Alan
```

**Concept:** `List.add()` returns a boolean, but its result is discarded when used as a `Consumer`. This sequential example updates a separate list; avoid structural changes to the collection being traversed.

## Shared Employee Class and Data (Questions 4–5)

Place this class outside the enclosing `Main` class:

```java
class Employee {
    String name;
    final String department;

    Employee(String name, String department) {
        this.name = name;
        this.department = department;
    }
}
```

Create fresh sample data inside `main()` for each exercise:

```java
List<Employee> employees = Arrays.asList(
        new Employee(" john ", "IT"),
        new Employee(" alice ", "IT"),
        new Employee(" bob ", "HR"));
```

## Question 4 — Update and Print Custom Objects

**Task:** Normalize each employee's name by trimming spaces and converting it to uppercase with `Locale.ROOT`. Chain normalization with printing. Demonstrate that the original object was updated.

```java
Consumer<Employee> normalize = employee ->
        employee.name = employee.name.trim().toUpperCase(Locale.ROOT);
Consumer<Employee> printName = employee -> System.out.println(employee.name);

employees.forEach(normalize.andThen(printName));
System.out.println("Stored: " + employees.get(0).name);
```

**Output:**
```text
JOHN
ALICE
BOB
Stored: JOHN
```

**Concept:** Both actions receive the same object reference, so printing sees the mutation. Use a `Function` returning a new object when a transformation should preserve the original.

## Question 5 — Generic Processing with `Consumer<? super T>`

**Task:** Implement a reusable `processAll()` method. Use it to print trimmed employee names, then process strings using a `Consumer<Object>`.

```java
public static <T> void processAll(
        Iterable<T> values, Consumer<? super T> action) {
    Objects.requireNonNull(values, "values");
    Objects.requireNonNull(action, "action");
    for (T value : values) {
        action.accept(value);
    }
}
```

**Example calls inside `main()`:**

```java
processAll(employees, employee -> System.out.println(employee.name.trim()));
Consumer<Object> printObject = value -> System.out.println("Value: " + value);
processAll(Arrays.asList("Ada", "Grace"), printObject);
```

**Output:**
```text
john
alice
bob
Value: Ada
Value: Grace
```

**Concept:** `Consumer<? super T>` allows an action accepting `T` or a supertype of `T`. The method performs actions and does not build a result list.

## Question 6 — Return a Consumer and Handle Missing Values

**Task:** Write `prefixedPrinter(prefix)` returning a consumer that prints the prefix and message, ignoring null messages. Use it directly with null and with present and empty optionals.

```java
public static Consumer<String> prefixedPrinter(String prefix) {
    Objects.requireNonNull(prefix, "prefix");
    return message -> {
        if (message == null) {
            return;
        }
        System.out.println(prefix + message);
    };
}
```

**Example calls:**

```java
Consumer<String> printUser = prefixedPrinter("User: ");
printUser.accept(null);
Optional.of("Ada").ifPresent(printUser);
Optional.<String>empty().ifPresent(printUser);
printUser.accept("Grace");
```

**Output:**
```text
User: Ada
User: Grace
```

**Concept:** The returned consumer captures the effectively final `prefix`. A bare `return;` ends one invocation. `Optional.ifPresent()` invokes the action only for a present value; an empty optional never passes null to it.

## Question 7 — Map Actions with `BiConsumer`

**Task:** Store Ada's score of `95` and Grace's score of `98` in a `LinkedHashMap`. Chain a score-printing `BiConsumer` with an audit-recording `BiConsumer`, then pass the combined action to `Map.forEach()`.

```java
Map<String, Integer> scores = new LinkedHashMap<>();
scores.put("Ada", 95);
scores.put("Grace", 98);
List<String> audit = new ArrayList<>();

BiConsumer<String, Integer> printScore = (name, score) ->
        System.out.println(name + " scored " + score);
BiConsumer<String, Integer> record = (name, score) ->
        audit.add(name + ":" + score);

scores.forEach(printScore.andThen(record));
System.out.println(audit);
```

**Output:**
```text
Ada scored 95
Grace scored 98
[Ada:95, Grace:98]
```

**Concept:** `BiConsumer<T, U>` accepts two inputs and returns no result. Both chained actions receive the same key-value pair. `LinkedHashMap` provides the insertion order used here.

## Question 8 — Primitive and Object-Plus-Primitive Consumers

**Task:** Use an `IntConsumer` to print squares from `1` to `3`. Then use an `ObjIntConsumer<StringBuilder>` to append `7` to a builder initially containing `Count: `.

```java
IntConsumer printSquare = number -> System.out.println(number * number);
IntStream.rangeClosed(1, 3).forEach(printSquare);

ObjIntConsumer<StringBuilder> appendNumber =
        (builder, number) -> builder.append(number);
StringBuilder message = new StringBuilder("Count: ");
appendNumber.accept(message, 7);
System.out.println(message);
```

**Output:**
```text
1
4
9
Count: 7
```

**Concept:** These interfaces accept primitive `int` without boxing it as `Integer`. `IntConsumer` has its own `andThen()`; `ObjIntConsumer` does not provide that method.

## Question 9 — Exceptions Stop Later Actions

**Task:** Build a three-stage consumer chain: record `started`, reject an empty string, then record `finished`. Invoke it with an empty string, catch the exception, and print the remaining events.

```java
List<String> events = new ArrayList<>();
Consumer<String> start = value -> events.add("started");
Consumer<String> validate = value -> {
    if (value.isEmpty()) {
        throw new IllegalArgumentException("Value must not be empty");
    }
};
Consumer<String> finish = value -> events.add("finished");
Consumer<String> action = start.andThen(validate).andThen(finish);

try {
    action.accept("");
} catch (IllegalArgumentException exception) {
    System.out.println(exception.getMessage());
}
System.out.println(events);
```

**Output:**
```text
Value must not be empty
[started]
```

**Concept:** Exceptions propagate to the caller and skip later actions. Earlier effects remain; `andThen()` does not roll them back.

## Question 10 — Dynamic Consumer Composition

**Task:** Implement `combineActions()` to execute a list of string consumers in list order. An empty list must produce a consumer that does nothing. Test with two event-recording actions, then with an empty list.

```java
public static Consumer<String> combineActions(List<Consumer<String>> actions) {
    Objects.requireNonNull(actions, "actions");
    Consumer<String> combined = value -> { };
    for (Consumer<String> action : actions) {
        combined = combined.andThen(action);
    }
    return combined;
}
```

**Example calls:**

```java
List<String> events = new ArrayList<>();
List<Consumer<String>> actions = Arrays.asList(
        value -> events.add("validate:" + value),
        value -> events.add("record:" + value));

Consumer<String> combined = combineActions(actions);
System.out.println(events);
combined.accept("Java");
System.out.println(events);
combineActions(Collections.emptyList()).accept("ignored");
System.out.println(events);
```

**Output:**
```text
[]
[validate:Java, record:Java]
[validate:Java, record:Java]
```

**Concept:** A consumer that does nothing is the starting action for dynamic composition. Building the chain does not execute it. A null stage throws during composition, and exceptions during invocation follow the behavior demonstrated in Question 9.

---

## Key Takeaways

| API | Purpose | Example |
|---|---|---|
| `Consumer<T>` | Perform an action on one input | `System.out::println` |
| `accept(value)` | Execute a consumer | `print.accept("Java")` |
| `andThen(after)` | Run actions in sequence on the same input | `normalize.andThen(print)` |
| `Iterable.forEach()` | Act on each element | `names.forEach(print)` |
| `Consumer<? super T>` | Accept actions for `T` or its supertypes | Generic `processAll()` |
| `Optional.ifPresent()` | Act only when a value is present | `name.ifPresent(print)` |
| `BiConsumer<T, U>` | Perform an action on two inputs | `scores.forEach(printScore)` |
| `IntConsumer` | Consume a primitive `int` | `IntStream.rangeClosed(1, 3).forEach(action)` |
| `ObjIntConsumer<T>` | Consume an object and primitive `int` | `appendNumber.accept(builder, 7)` |

**Reminder:** Consumers do not automatically create threads or make mutable objects thread-safe. For parallel result construction, prefer `collect(Collectors.toList())` over adding to a shared `ArrayList` inside `forEach()`. Use `forEachOrdered()` when a stream's encounter order must be preserved, and keep required actions out of `peek()`.

[← 154. Function](154_Function.md) | [Course Index](../README.md) | [156. Supplier →](156_Supplier.md)
