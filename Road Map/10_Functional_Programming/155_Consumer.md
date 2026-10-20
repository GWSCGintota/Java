[← 154. Function](154_Function.md) | [Course Index](../README.md) | [156. Supplier →](156_Supplier.md)

---

# 155. Consumer

**Section:** Java Functional Programming

**Level:** Beginner → Advanced

**Version:** Examples use Java 8+ unless marked otherwise.

## 1. What is a Consumer?

`Consumer<T>` represents an action that accepts one input and returns no result. Its abstract method is `void accept(T value)`.

```java
import java.util.function.Consumer;

// Inside a method:
Consumer<String> print = text -> System.out.println(text);
print.accept("Hello, Java!"); // Prints Hello, Java!
```

Use it for actions such as printing, updating an object, recording an event, or processing a callback. Unlike a transformation, an action is usually useful because of an observable side effect.

Creating a consumer does not execute its body. Call `accept`, or pass it to an API that invokes it. A consumer does not automatically create a thread or make the action asynchronous.

The core declaration looks like this:

```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T value);
    // The actual interface also provides andThen.
}
```

## 2. Consumer versus related interfaces

| Interface | Abstract operation | Purpose |
| --- | --- | --- |
| `Consumer<T>` | `void accept(T value)` | Perform an action |
| `Function<T, R>` | `R apply(T value)` | Transform input into a result |
| `Predicate<T>` | `boolean test(T value)` | Check a condition |
| `Supplier<T>` | `T get()` | Supply a result without input |
| `BiConsumer<T, U>` | `void accept(T first, U second)` | Act on two inputs |
| `Runnable` | `void run()` | Run an action without input |

Use `Function` if the caller needs a returned value. A `Consumer` cannot be assigned a computed result from `accept`, because it returns `void`.

## 3. Basic examples

Unless imports are shown, snippets assume imports from `java.util`, `java.util.function`, and `java.util.stream`, and declarations appear inside a method. Inputs are non-null unless guarded.

```java
Consumer<String> print = System.out::println;
Consumer<String> printLength = text -> System.out.println(text.length());
Consumer<Integer> printSquare = n -> System.out.println(n * n);
Consumer<String> greeting = name -> System.out.println("Hello, " + name);

print.accept("Java");        // Java
printLength.accept("Java");  // 4
printSquare.accept(5);       // 25
 greeting.accept("Ada");     // Hello, Ada
```

Generic arguments are reference types, so use `Consumer<Integer>`, not `Consumer<int>`. Numeric examples assume values small enough to avoid overflow. `String.length()` counts UTF-16 code units rather than necessarily user-perceived characters.

### Multiple statements and early return

```java
Consumer<String> describe = text -> {
    if (text == null) {
        return; // Ends this invocation, not the enclosing method
    }
    System.out.println("Input: " + text);
    System.out.println("Length: " + text.length());
};
describe.accept("Java");
```

A consumer block may use a bare `return;`, but cannot return a value.

## 4. Lambdas, references, and discarded results

```java
Consumer<String> lambda = text -> System.out.println(text);
Consumer<String> reference = System.out::println;
Consumer<String> anonymous = new Consumer<String>() {
    @Override
    public void accept(String text) {
        System.out.println(text);
    }
};
```

A value-returning method invocation can also implement a void action when its result is discarded:

```java
List<String> names = new ArrayList<>();
Consumer<String> addName = names::add; // List.add returns boolean; ignored here
addName.accept("Ada");
System.out.println(names); // [Ada]

Consumer<String> ignoreLength = text -> text.length(); // Legal, but not useful
// Consumer<Integer> invalid = n -> n + 1; // Not a statement expression
```

A standalone arithmetic expression is not a valid statement-expression body for a void target. If the result matters, use a function instead.

## 5. Chaining actions with andThen

```java
Consumer<String> printInput = text -> System.out.println("Input: " + text);
Consumer<String> printLength = text -> System.out.println("Length: " + text.length());
Consumer<String> combined = printInput.andThen(printLength);
combined.accept("Java");
// Input: Java
// Length: 4
```

Both actions receive the same input. There is no returned output passed from the first action to the second. Composition itself does not run either action, and the original consumers remain usable.

```java
List<String> events = new ArrayList<>();
Consumer<String> first = value -> events.add("first:" + value);
Consumer<String> second = value -> events.add("second:" + value);
Consumer<String> third = value -> events.add("third:" + value);
first.andThen(second).andThen(third).accept("A");
System.out.println(events); // [first:A, second:A, third:A]
```

### Failure behavior

```java
Consumer<String> validate = value -> {
    if (value.isEmpty()) {
        throw new IllegalArgumentException("Value must not be empty");
    }
};
Consumer<String> print = System.out::println;
Consumer<String> action = validate.andThen(print);
// action.accept(""); // Throws; print does not run
```

Exceptions propagate to the caller. If the first action throws, later actions do not run. Passing null to `andThen` throws during composition.

Chaining does not provide rollback or a transaction. If an earlier action changes state and a later one fails, the earlier effect remains unless your application explicitly reverses it.

## 6. Mutating an object

Declare this class at class scope:

```java
static class User {
    private String name;

    User(String name) { this.name = name; }
    String getName() { return name; }
    void setName(String name) { this.name = name; }
}

// Inside a method:
Consumer<User> normalizeName = user ->
        user.setName(user.getName().trim().toUpperCase(Locale.ROOT));
Consumer<User> printName = user -> System.out.println(user.getName());
User user = new User(" ada ");
normalizeName.andThen(printName).accept(user); // ADA
```

The second consumer sees the mutation because both receive the same object. This example assumes non-null user and name. Use immutable output objects with `Function` when changing the original object is inappropriate.

## 7. Iterable.forEach and collection actions

```java
List<String> names = Arrays.asList("Ada", "Grace", "Alan");
names.forEach(System.out::println);

List<Integer> lengths = new ArrayList<>();
Consumer<String> collectLength = name -> lengths.add(name.length());
names.forEach(collectLength);
System.out.println(lengths); // [3, 5, 4]
```

For a transformation, a mapper often communicates intent better:

```java
List<Integer> lengths = names.stream()
        .map(String::length)
        .collect(Collectors.toList());
```

Avoid structural modification of the collection being traversed. For removal, use `removeIf` with a predicate rather than removing elements from inside `forEach`. A consumer's `return` does not break out of `forEach`; use a loop when you need early exit.

## 8. Stream.forEach, ordering, and peek

```java
List<String> names = Arrays.asList("Ada", "Grace", "Alan");
names.stream()
        .filter(name -> name.startsWith("A"))
        .forEach(System.out::println); // Ada, Alan
```

`forEach` is terminal and returns `void`. For a parallel ordered stream, `forEach` does not guarantee encounter order; use `forEachOrdered` if the order is required:

```java
names.parallelStream().forEachOrdered(System.out::println);
```

`forEachOrdered` preserves encounter order where defined, but does not promise one particular worker thread. Upstream parallel work still has its own concurrency considerations.

### peek is not a required-action hook

```java
List<String> selected = names.stream()
        .peek(name -> System.out.println("Inspect: " + name))
        .filter(name -> name.length() > 3)
        .collect(Collectors.toList());
```

`peek` is intermediate and mainly useful for observing processing. It does nothing without terminal traversal, and optimizations or short-circuiting can skip processing. Do not put required persistence or business effects in `peek`.

### Avoid shared mutable accumulation in parallel streams

```java
// Unsafe: several threads may modify the same ordinary ArrayList.
// List<String> result = new ArrayList<>();
// names.parallelStream().forEach(result::add);

List<String> result = names.parallelStream().collect(Collectors.toList());
```

Use a collector or reduction for result construction. A synchronized collection may protect individual updates but does not automatically define encounter order or make a larger algorithm correct.

## 9. Optional actions

```java
Optional<String> name = Optional.of("Ada");
name.ifPresent(value -> System.out.println("Found: " + value)); // Found: Ada
Optional.<String>empty().ifPresent(System.out::println); // No output
```

The action is called only for a present value. If its body throws, the exception propagates.

Java 9+ adds an empty-case action:

```java
name.ifPresentOrElse(
        value -> System.out.println("Found: " + value),
        () -> System.out.println("No name"));
```

The present branch is a `Consumer`; the empty branch is a `Runnable`.

## 10. BiConsumer: actions with two inputs

```java
BiConsumer<String, Integer> printScore = (name, score) ->
        System.out.println(name + " scored " + score);
printScore.accept("Ada", 95); // Ada scored 95
```

Map iteration supplies the key and value:

```java
Map<String, Integer> scores = new LinkedHashMap<>();
scores.put("Ada", 95);
scores.put("Grace", 98);
scores.forEach(printScore); // Ada scored 95, then Grace scored 98
```

`LinkedHashMap` gives the insertion order used above. Other map types may have different ordering.

`BiConsumer.andThen` sends the same pair to both actions:

```java
List<String> audit = new ArrayList<>();
BiConsumer<String, Integer> record = (name, score) -> audit.add(name + ":" + score);
printScore.andThen(record).accept("Alan", 90);
System.out.println(audit); // [Alan:90]
```

A bound map method can be used as an action, discarding its return value:

```java
Map<String, Integer> stored = new HashMap<>();
BiConsumer<String, Integer> save = stored::put;
save.accept("Ada", 95);
```

`BiConsumer<T, U>` is a separate interface, not a subtype of `Consumer<T>`. Neither action reports success through a return value; choose another interface when the caller needs that information.

## 11. Primitive and object-plus-primitive consumers

| Interface | Inputs | Invocation |
| --- | --- | --- |
| `IntConsumer` | `int` | `accept(int)` |
| `LongConsumer` | `long` | `accept(long)` |
| `DoubleConsumer` | `double` | `accept(double)` |
| `ObjIntConsumer<T>` | `T`, `int` | `accept(T, int)` |
| `ObjLongConsumer<T>` | `T`, `long` | `accept(T, long)` |
| `ObjDoubleConsumer<T>` | `T`, `double` | `accept(T, double)` |

```java
IntConsumer printInt = System.out::println;
LongConsumer printLong = System.out::println;
DoubleConsumer printDouble = System.out::println;
printInt.accept(10);
printLong.accept(100L);
printDouble.accept(2.5);

ObjIntConsumer<String> repeat = (text, count) -> {
    for (int i = 0; i < count; i++) {
        System.out.println(text);
    }
};
repeat.accept("Java", 2); // Java printed twice
```

Primitive variants avoid boxed numeric inputs. `IntConsumer`, `LongConsumer`, and `DoubleConsumer` provide their own `andThen`; the `ObjIntConsumer`, `ObjLongConsumer`, and `ObjDoubleConsumer` interfaces do not provide that method.

These interfaces are separate types, not subtypes of boxed consumers. Adapt explicitly when needed:

```java
IntConsumer primitive = System.out::println;
Consumer<Integer> boxed = primitive::accept; // Unboxes; null would throw
IntStream.rangeClosed(1, 3).forEach(primitive); // 1, 2, 3
```

## 12. Passing and returning consumers

Declare these methods inside a class:

```java
static <T> void processAll(Iterable<T> values, Consumer<? super T> action) {
    Objects.requireNonNull(values, "values");
    Objects.requireNonNull(action, "action");
    for (T value : values) {
        action.accept(value);
    }
}

static Consumer<String> prefixedPrinter(String prefix) {
    return message -> System.out.println(prefix + message);
}

// Inside main:
processAll(Arrays.asList("Ada", "Grace"), prefixedPrinter("User: "));
// User: Ada
// User: Grace
```

`Consumer<? super T>` lets an action accepting `T` or a supertype consume these values. A `Consumer<Object>` can therefore process strings.

The returned action captures `prefix`, which is effectively final. A captured local reference cannot be reassigned; an object referenced by it may still be mutable.

## 13. Consumer-shaped callbacks and accumulation

An event-processing method can receive a callback:

```java
static void publish(String message, Consumer<String> listener) {
    Objects.requireNonNull(listener, "listener");
    listener.accept(message);
}

// Inside main:
publish("Build finished", System.out::println);
```

This example invokes the listener synchronously. An asynchronous API must explicitly arrange asynchronous execution and define error handling.

Mutable reduction uses consumer-like operations on containers:

```java
List<String> names = Arrays.asList("Ada", "Grace");
List<String> copy = names.stream().collect(
        ArrayList::new,
        List::add,
        List::addAll);
System.out.println(copy); // [Ada, Grace]
```

The three-argument `collect` uses a supplier, a `BiConsumer` accumulator, and a `BiConsumer` combiner. It is not the same as capturing one shared list in parallel `forEach`: collection manages intermediate result containers. Prefer `Collectors.toList()` for a straightforward list copy.

## 14. Nulls, exceptions, and resource lifetime

A consumer has no automatic null policy:

```java
Consumer<String> printIfPresent = text -> {
    if (text != null) {
        System.out.println(text);
    }
};
printIfPresent.accept(null); // Deliberately ignored
```

`System.out::println` can print a null string, but a body that calls `text.length()` will throw for null. Validate the action reference separately from its input.

`Consumer.accept` declares no checked exceptions. Handle checked failures inside the action, use a named wrapper, or define a throwing interface:

```java
@FunctionalInterface
interface ThrowingConsumer<T, E extends Exception> {
    void accept(T value) throws E;
}

// Inside a method, with java.io and java.nio.file imports:
try (BufferedWriter writer = Files.newBufferedWriter(
        Paths.get("output.txt"), java.nio.charset.StandardCharsets.UTF_8)) {
    ThrowingConsumer<String, IOException> write = writer::write;
    write.accept("Hello");
}
// The enclosing method must handle or declare IOException.
```

Keep resource-using callbacks within the resource's lifetime. A stored callback referring to a closed writer will fail when invoked later. Do not swallow failures or assume an outer try around callback creation catches failures from future invocations.

Bound method references capture their receiver when created. For example, `System.out::println` retains the current output stream, while `text -> System.out.println(text)` reads `System.out` when invoked. See [152. Method references](152_Method_references.md) for timing details.

## 15. Complete runnable example

Save as `ConsumerDemo.java`. This example works on Java 8+.

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.ObjIntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsumerDemo {
    static Consumer<String> prefixedPrinter(String prefix) {
        return text -> System.out.println(prefix + text);
    }

    public static void main(String[] args) {
        Consumer<String> printInput = prefixedPrinter("Input: ");
        Consumer<String> printLength = text ->
                System.out.println("Length: " + text.length());
        printInput.andThen(printLength).accept("Java");

        List<String> names = Arrays.asList("Ada", "Grace");
        names.forEach(prefixedPrinter("User: "));

        List<String> events = new ArrayList<>();
        Consumer<String> record = events::add;
        record.accept("created");
        record.accept("checked");
        System.out.println(events);

        Map<String, Integer> scores = new LinkedHashMap<>();
        scores.put("Ada", 95);
        scores.put("Grace", 98);
        BiConsumer<String, Integer> printScore = (name, score) ->
                System.out.println(name + "=" + score);
        scores.forEach(printScore);

        Optional.of("Present").ifPresent(System.out::println);

        IntConsumer printNumber = number -> System.out.println("Number: " + number);
        IntStream.rangeClosed(1, 2).forEach(printNumber);

        ObjIntConsumer<String> repeat = (text, count) -> {
            for (int i = 0; i < count; i++) {
                System.out.println(text);
            }
        };
        repeat.accept("Again", 2);

        List<Integer> lengths = names.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(lengths);
    }
}
```

Compile and run:

```shell
javac ConsumerDemo.java
java ConsumerDemo
```

Expected output:

```text
Input: Java
Length: 4
User: Ada
User: Grace
[created, checked]
Ada=95
Grace=98
Present
Number: 1
Number: 2
Again
Again
[3, 5]
```

## 16. Common mistakes

| Mistake | Better approach |
| --- | --- |
| Calling `apply` or `test` | Invoke `accept` |
| Expecting a result from `accept` | Use Function when returning a value |
| Expecting andThen to pass a transformed value | Both actions receive the same input |
| Assuming chained actions roll back on failure | Design transactions or compensation explicitly when needed |
| Trying to break out of forEach with return | Use a loop or a suitable short-circuiting stream operation |
| Modifying the traversed collection structurally | Use a dedicated operation such as removeIf |
| Adding to a shared ArrayList in parallel forEach | Use a collector |
| Putting required effects inside peek | Use an explicit terminal action or ordinary control flow |
| Ignoring nulls or checked exceptions | Define an intentional policy |
| Retaining callbacks using closed resources | Keep invocation within the resource lifetime |

Consumers intentionally describe effects. Keep them focused and make failure behavior explicit. Thread safety depends on the objects and operations involved, not on using a lambda or a method reference.

## 17. Practice exercises

1. Write a consumer printing a string and its length. Test `Java`.
2. Chain two consumers recording `first:value` and `second:value` in a list; predict the order.
3. Update a mutable user's name with trimming and locale-independent uppercase conversion, then print it.
4. Use `Map.forEach` to print a LinkedHashMap in insertion order.
5. Create an `IntConsumer` printing the square of values from 1 to 5.
6. Use an `ObjIntConsumer<StringBuilder>` to append a number to a builder.
7. Write a consumer that ignores null input and document that policy.
8. Make the first action in a chain throw, and verify that the second does not execute.
9. Replace shared-list accumulation in parallel forEach with a collector.
10. Implement a reusable `processAll` accepting `Consumer<? super T>`.
11. Explain why returning a value from a Consumer block fails, while a value-returning method reference can be valid.
12. Compare Optional.ifPresent with the Java 9+ empty-case alternative.

**Hints:** 2. left-to-right execution; 4. BiConsumer; 6. `(builder, value) -> builder.append(value)`; 8. catch the expected exception in the calling code; 9. `collect(Collectors.toList())`; 11. method-invocation results can be discarded for a void target.

## Completion checklist

- [ ] I can create and invoke Consumer using accept.
- [ ] I can distinguish actions from transformations and predicates.
- [ ] I understand andThen order, shared input, and failure behavior.
- [ ] I can use collections, streams, optionals, and map callbacks.
- [ ] I understand BiConsumer and primitive variants.
- [ ] I can pass and return actions using functional interfaces.
- [ ] I can reason about mutation, concurrency, exceptions, and resource lifetime.
- [ ] I can explain the complete runnable example.

## Official references

- [Consumer API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/Consumer.html) — accept and composition contracts.
- [BiConsumer API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/BiConsumer.html) — two-input actions.
- [java.util.function API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/package-summary.html) — primitive variants.
- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html) — forEach, forEachOrdered, peek, and collect.
- [Optional API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Optional.html) — present and empty callbacks.

---

[← 154. Function](154_Function.md) | [Course Index](../README.md) | [156. Supplier →](156_Supplier.md)
