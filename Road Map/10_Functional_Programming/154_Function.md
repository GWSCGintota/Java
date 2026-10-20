[← 153. Predicate](153_Predicate.md) | [Course Index](../README.md) | [155. Consumer →](155_Consumer.md)

---

# 154. Function

**Section:** Java Functional Programming

**Level:** Beginner → Advanced

**Version:** All examples use Java 8-compatible APIs.

## 1. What is Function?

`Function<T, R>` represents a transformation from an input of type `T` to a result of type `R`. Its abstract method is `R apply(T value)`.

```java
import java.util.function.Function;

// Inside a method:
Function<String, Integer> length = text -> text.length();
System.out.println(length.apply("Java")); // 4
```

Use a function when you want to pass or reuse a transformation: parsing text, extracting fields, converting objects, formatting output, or mapping stream elements. Declaring a function does not execute it. Call `apply`, or pass it to an API that invokes it.

The core declaration looks like this:

```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T value);
    // The actual interface also provides composition and identity methods.
}
```

`T` and `R` can be the same type or different types. Generic type arguments must be reference types: use `Integer`, not `int`. Primitive specializations are covered later.

## 2. Function versus related interfaces

| Interface | Abstract operation | Best use |
| --- | --- | --- |
| `Function<T, R>` | `R apply(T value)` | Transform one input |
| `Predicate<T>` | `boolean test(T value)` | Check a condition |
| `Consumer<T>` | `void accept(T value)` | Perform an action |
| `Supplier<T>` | `T get()` | Produce a value without input |
| `UnaryOperator<T>` | `T apply(T value)` | Transform within the same type |
| `BiFunction<T, U, R>` | `R apply(T first, U second)` | Transform two inputs |
| `BinaryOperator<T>` | `T apply(T first, T second)` | Combine two values of the same type |

`Function<T, Boolean>` is legal, but `Predicate<T>` better expresses a condition and provides logical composition. A function returning `Void` is usually less appropriate for an action than a `Consumer`.

## 3. Basic transformation examples

Unless imports are shown, snippets assume imports from `java.util`, `java.util.function`, and `java.util.stream` and belong inside a method. Inputs are non-null unless the example handles null explicitly. Numeric examples use small values that do not overflow.

```java
Function<Integer, Integer> square = number -> number * number;
Function<Integer, String> label = number -> "Value: " + number;
Function<String, String> trim = String::trim;
Function<String, String> uppercase = text -> text.toUpperCase(Locale.ROOT);
Function<String, Integer> parse = Integer::parseInt;

System.out.println(square.apply(5));          // 25
System.out.println(label.apply(10));         // Value: 10
System.out.println(trim.apply(" Java "));    // Java
System.out.println(uppercase.apply("java"));  // JAVA
System.out.println(parse.apply("42"));       // 42
```

`Integer::parseInt` may throw `NumberFormatException` for malformed or out-of-range text. A function does not automatically validate or recover from bad input.

For same-type transformations, `UnaryOperator<String>` or `IntUnaryOperator` can express intent more precisely.

## 4. Lambdas, method references, and anonymous classes

These implement the same transformation for non-null strings:

```java
Function<String, Integer> lambda = text -> text.length();
Function<String, Integer> reference = String::length;
Function<String, Integer> anonymous = new Function<String, Integer>() {
    @Override
    public Integer apply(String text) {
        return text.length();
    }
};
```

The target type supplies parameter and result requirements. A bare declaration such as `var length = String::length;` fails because it provides no functional interface target. Local-variable `var` itself requires Java 10+.

Keep a lambda when you need additional logic, such as `text -> text.trim().length()`. See [152. Method references](152_Method_references.md).

## 5. API overview

| Method | Purpose |
| --- | --- |
| `apply(value)` | Run the transformation |
| `andThen(after)` | Run this function, then another |
| `compose(before)` | Run another function, then this one |
| `Function.identity()` | Create a function returning its input |

Composition builds a new function without running the transformations. Calling the resulting function invokes its stages in order.

## 6. andThen: execute left to right

```java
Function<Integer, Integer> doubleIt = n -> n * 2;
Function<Integer, Integer> addThree = n -> n + 3;
Function<Integer, Integer> combined = doubleIt.andThen(addThree);
System.out.println(combined.apply(5)); // 13: (5 * 2) + 3
```

The result type of the first stage must be acceptable as input to the next stage. Different types can be chained:

```java
Function<String, String> trim = String::trim;
Function<String, Integer> parse = Integer::parseInt;
Function<Integer, Integer> doubleIt = n -> n * 2;
Function<Integer, String> format = n -> "Result: " + n;

Function<String, String> pipeline = trim.andThen(parse)
        .andThen(doubleIt)
        .andThen(format);
System.out.println(pipeline.apply(" 21 ")); // Result: 42
```

Conceptually: `String → String → Integer → Integer → String`.

## 7. compose: preprocess the input

```java
Function<Integer, Integer> doubleIt = n -> n * 2;
Function<Integer, Integer> addThree = n -> n + 3;
System.out.println(doubleIt.compose(addThree).apply(5)); // 16: (5 + 3) * 2
```

For `f` and `g`:

- `f.andThen(g)` means `g(f(input))`.
- `f.compose(g)` means `f(g(input))`.

Example with different types:

```java
Function<String, Integer> parse = Integer::parseInt;
Function<Integer, Integer> square = n -> n * n;
Function<String, Integer> parseThenSquare = square.compose(parse);
System.out.println(parseThenSquare.apply("6")); // 36
```

Passing a null function to either composition method throws immediately. If a stage throws during invocation, the exception propagates and remaining stages do not execute. Composition does not catch exceptions or add null handling.

## 8. Function.identity

```java
Function<String, String> same = Function.identity();
System.out.println(same.apply("Java")); // Java
System.out.println(same.apply(null));   // null
```

Identity returns the same input, not a copy:

```java
List<String> original = new ArrayList<>();
Function<List<String>, List<String>> sameList = Function.identity();
System.out.println(sameList.apply(original) == original); // true
```

It is useful when an API requires an explicit mapping but one side should keep the original element:

```java
List<String> names = Arrays.asList("Ada", "Grace");
Map<String, Integer> lengths = names.stream().collect(
        Collectors.toMap(Function.identity(), String::length));
System.out.println(lengths.get("Ada")); // 3
```

Duplicate keys make the two-argument `toMap` collector throw. Supply a deliberate merge policy when duplicates are expected:

```java
Map<String, Integer> lengths = Arrays.asList("Ada", "Ada", "Grace").stream()
        .collect(Collectors.toMap(Function.identity(), String::length,
                (existing, incoming) -> existing));
```

A map's iteration order depends on its implementation; do not rely on the default collector's printed order.

## 9. Functions in streams

### Map elements

```java
List<String> names = Arrays.asList("Ada", "Grace", "Alan");
Function<String, Integer> length = String::length;
List<Integer> lengths = names.stream()
        .map(length)
        .collect(Collectors.toList());
System.out.println(lengths); // [3, 5, 4]
```

Mapping produces transformed elements; it does not remove elements like `filter`. Stream traversal is triggered by a terminal operation. Avoid mutating the source or shared state from mapping functions, particularly with parallel streams.

### Group by a computed key

```java
Map<Integer, List<String>> byLength = names.stream()
        .collect(Collectors.groupingBy(String::length));
System.out.println(byLength.get(3)); // [Ada]
System.out.println(byLength.get(4)); // [Alan]
```

### Flatten mapped results

```java
List<String> lines = Arrays.asList("Java streams", "Java functions");
List<String> words = lines.stream()
        .flatMap(line -> Arrays.stream(line.split(" ")))
        .collect(Collectors.toList());
System.out.println(words); // [Java, streams, Java, functions]
```

`map` keeps each mapper result as one element. `flatMap` expects a mapper producing a stream and flattens those streams. The simple split rule above is for the example's single-space input, not a complete text tokenizer.

## 10. Object-to-object mapping

Put these types inside a class, or declare them as separate appropriate classes:

```java
static class User {
    private final String name;
    private final int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    String getName() { return name; }
    int getAge() { return age; }
}

static class UserSummary {
    private final String displayName;

    UserSummary(String displayName) {
        this.displayName = displayName;
    }

    String getDisplayName() { return displayName; }
}

// Inside a method:
Function<User, String> name = User::getName;
Function<User, UserSummary> toSummary = user ->
        new UserSummary(user.getName() + " (" + user.getAge() + ")");
UserSummary summary = toSummary.apply(new User("Ada", 36));
System.out.println(summary.getDisplayName()); // Ada (36)
```

This pattern is useful for mapping domain objects to output models. Decide which fields to expose and how null or invalid data should be handled. Creating a new output object does not automatically deep-copy any referenced nested objects.

## 11. Optional.map and flatMap

```java
Optional<String> name = Optional.of(" Ada ");
Optional<Integer> length = name.map(String::trim).map(String::length);
System.out.println(length.orElse(0)); // 3
```

An empty `Optional` skips mapping. A mapper returning null makes `Optional.map` return empty, whereas `Stream.map` can produce null elements.

When a mapping already returns an `Optional`, use `flatMap` to avoid nesting:

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

Optional<Integer> result = Optional.of(" 42 ").flatMap(parseSafely);
System.out.println(result.orElse(-1)); // 42
```

`Optional.flatMap` requires a non-null `Optional` result. This example deliberately represents invalid input as absence; use a richer result type when callers need error details.

## 12. Map APIs

### computeIfAbsent accepts a Function

```java
Map<String, Integer> lengths = new HashMap<>();
Function<String, Integer> calculateLength = String::length;
int length = lengths.computeIfAbsent("Java", calculateLength);
System.out.println(length); // 4
```

The mapping function is used when the key has no non-null mapped value. If it returns null, no new mapping is recorded. Exceptions propagate. This is map storage behavior, not automatic caching built into every `Function`; thread safety and atomicity depend on the map implementation and API contract.

### replaceAll accepts a BiFunction

```java
Map<String, Integer> scores = new HashMap<>();
scores.put("Ada", 10);
scores.replaceAll((name, score) -> score + 5);
System.out.println(scores.get("Ada")); // 15
```

Unlike `List.replaceAll`, which accepts a `UnaryOperator`, `Map.replaceAll` receives both key and value. Avoid modifying the map's structure from these mapping callbacks.

## 13. BiFunction and operators

```java
BiFunction<String, Integer, String> describe =
        (name, age) -> name + " is " + age;
System.out.println(describe.apply("Ada", 36)); // Ada is 36

BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
BiFunction<Integer, Integer, String> formatted =
        multiply.andThen(value -> "Product: " + value);
System.out.println(formatted.apply(3, 4)); // Product: 12

UnaryOperator<String> trim = String::trim;
BinaryOperator<Integer> add = Integer::sum;
```

`BiFunction` supports result composition with `andThen`, but does not have `Function.compose`. `UnaryOperator<T>` extends `Function<T, T>`; `BinaryOperator<T>` extends `BiFunction<T, T, T>`.

## 14. Primitive specializations

| Interface | Shape | Invocation |
| --- | --- | --- |
| `ToIntFunction<T>` | `T → int` | `applyAsInt(value)` |
| `ToLongFunction<T>` | `T → long` | `applyAsLong(value)` |
| `ToDoubleFunction<T>` | `T → double` | `applyAsDouble(value)` |
| `IntFunction<R>` | `int → R` | `apply(value)` |
| `LongFunction<R>` | `long → R` | `apply(value)` |
| `DoubleFunction<R>` | `double → R` | `apply(value)` |
| `IntToDoubleFunction` | `int → double` | `applyAsDouble(value)` |
| `LongToIntFunction` | `long → int` | `applyAsInt(value)` |
| `IntUnaryOperator` | `int → int` | `applyAsInt(value)` |

Other primitive-to-primitive combinations and primitive-returning two-input functions are also available in `java.util.function`.

```java
ToIntFunction<String> length = String::length;
IntFunction<String> label = number -> "Item " + number;
IntToDoubleFunction half = number -> number / 2.0;
IntUnaryOperator square = number -> number * number;

System.out.println(length.applyAsInt("Java")); // 4
System.out.println(label.apply(7));             // Item 7
System.out.println(half.applyAsDouble(5));      // 2.5
System.out.println(square.applyAsInt(6));       // 36

int totalLength = Arrays.asList("Ada", "Grace").stream()
        .mapToInt(length).sum();
System.out.println(totalLength); // 8
```

Primitive specializations are separate types, not subtypes of boxed `Function` forms. They do not all expose the same composition methods. Adapt with a lambda or method reference when necessary. Avoid assuming primitive narrowing conversions cannot lose information.

## 15. Passing and returning functions

Put these methods inside a class:

```java
static <T, R> List<R> transform(
        List<T> values, Function<? super T, ? extends R> mapper) {
    Objects.requireNonNull(values, "values");
    Objects.requireNonNull(mapper, "mapper");
    List<R> result = new ArrayList<>();
    for (T value : values) {
        result.add(mapper.apply(value));
    }
    return result;
}

static Function<Integer, Integer> multiplier(int factor) {
    return value -> value * factor;
}

// Inside main:
List<Integer> lengths = transform(Arrays.asList("Ada", "Grace"), String::length);
System.out.println(lengths); // [3, 5]
System.out.println(multiplier(3).apply(7)); // 21
```

`? super T` permits a mapper accepting `T` or a supertype; `? extends R` permits a result of `R` or a subtype. Captured local variables such as `factor` must be final or effectively final. This helper allows null results; element null handling belongs to the mapper.

## 16. Null handling, exceptions, and side effects

Define a policy rather than assuming every function accepts null:

```java
Function<String, Integer> safeLength = text -> text == null ? 0 : text.length();
System.out.println(safeLength.apply(null)); // 0
```

Returning zero is a deliberate policy; it conflates missing input with an empty string. Choose a different result if the distinction matters.

`Function.apply` declares no checked exceptions. Handle them inside the function, use a named wrapper, or define a throwing functional interface:

```java
@FunctionalInterface
interface ThrowingFunction<T, R, E extends Exception> {
    R apply(T value) throws E;
}

// Inside a method, with java.nio.file and java.io imports:
ThrowingFunction<Path, byte[], IOException> read = Files::readAllBytes;
// read.apply(path) requires handling or declaring IOException.
```

A `Function` can have side effects, read changing state, or return different results across calls; the interface does not enforce purity. Prefer predictable transformations. Composition does not memoize results, create threads, or make captured objects thread-safe.

## 17. Complete runnable example

Save as `FunctionDemo.java`. The output is shown below.

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class FunctionDemo {
    static Function<Integer, Integer> multiplier(int factor) {
        return number -> number * factor;
    }

    public static void main(String[] args) {
        Function<String, String> trim = String::trim;
        Function<String, String> upper = text -> text.toUpperCase(Locale.ROOT);
        Function<String, String> normalize = trim.andThen(upper);
        List<String> names = Arrays.asList(" ada ", " grace ");
        List<String> normalized = names.stream().map(normalize)
                .collect(Collectors.toList());
        System.out.println(normalized);

        Function<String, Integer> parse = Integer::parseInt;
        Function<Integer, Integer> doubleIt = number -> number * 2;
        System.out.println(parse.compose(trim).andThen(doubleIt).apply(" 21 "));

        Function<Integer, Integer> addThree = number -> number + 3;
        System.out.println(doubleIt.andThen(addThree).apply(5));
        System.out.println(doubleIt.compose(addThree).apply(5));

        Map<String, Integer> lengths = normalized.stream().collect(
                Collectors.toMap(Function.identity(), String::length));
        System.out.println(lengths.get("ADA"));
        System.out.println(lengths.get("GRACE"));

        ToIntFunction<String> length = String::length;
        System.out.println(normalized.stream().mapToInt(length).sum());

        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        BiFunction<Integer, Integer, String> product =
                multiply.andThen(value -> "Product: " + value);
        System.out.println(product.apply(3, 4));
        System.out.println(multiplier(3).apply(7));

        List<String> original = new ArrayList<>();
        Function<List<String>, List<String>> identity = Function.identity();
        System.out.println(identity.apply(original) == original);
    }
}
```

```shell
javac FunctionDemo.java
java FunctionDemo
```

Expected output:

```text
[ADA, GRACE]
42
13
16
3
5
8
Product: 12
21
true
```

## 18. Common mistakes

| Mistake | Better approach |
| --- | --- |
| Calling `test` on Function | Invoke `apply` |
| Using `Function<int, int>` | Use boxed arguments or a primitive specialization |
| Reversing composition order | Trace the intermediate values and types |
| Assuming identity copies an object | It returns the original input |
| Mapping when filtering is intended | Use `Predicate` with `filter` for selection |
| Assuming null input is always safe | Define explicit null handling |
| Collecting duplicate keys without a policy | Supply a merge function or use grouping |
| Swallowing parse or I/O errors | Define an intentional failure strategy |
| Depending on mapper side effects | Keep transformations predictable and separate required effects |
| Assuming every lambda is cached | Memoization requires a separate design |

## 19. Practice exercises

1. Transform a string into its trimmed length. Test `" Java "` and `""`.
2. Compose trimming, integer parsing, and squaring. For `" 6 "`, expect `36`.
3. Predict the results of increment-then-square and square-then-increment for input `3`.
4. Convert `[1, 2, 3]` into `["Item 1", "Item 2", "Item 3"]`.
5. Build a map from name to length, and explicitly define a duplicate-key policy.
6. Write a function mapping a product to a summary string. Define its null policy.
7. Return a configurable multiplier function and explain variable capture.
8. Replace a boxed length mapping with `ToIntFunction<String>` and sum lengths.
9. Write a safe parser returning `Optional<Integer>` and combine it with `Optional.flatMap`.
10. Demonstrate that `Function.identity()` preserves a mutable object's reference.
11. Use `computeIfAbsent` with a function and explain what happens if it returns null.
12. Add an overflow policy to the squaring example, for example `Math.multiplyExact`.

**Hints:** 1. `text -> text.trim().length()`; 2. `trim.andThen(parse).andThen(square)`; 3. `16` and `10`; 5. `toMap` with a merge function; 8. `mapToInt`; 10. compare references with `==` for the returned input, not the function object.

## Completion checklist

- [ ] I can choose Function and invoke it using `apply`.
- [ ] I understand the input and result type parameters.
- [ ] I can explain and use `compose`, `andThen`, and `identity`.
- [ ] I can map streams, objects, optionals, and map entries appropriately.
- [ ] I understand primitive specializations and related interfaces.
- [ ] I can pass and return reusable transformations.
- [ ] I can handle nulls, duplicates, exceptions, and side effects intentionally.
- [ ] I can explain the complete runnable example.

## Official references

- [Function API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/Function.html) — method contracts.
- [java.util.function API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/package-summary.html) — interface family and primitive variants.
- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html) — mapping operations.
- [Optional API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Optional.html) — optional mapping behavior.
- [Map API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Map.html) — mapping callbacks.

---

[← 153. Predicate](153_Predicate.md) | [Course Index](../README.md) | [155. Consumer →](155_Consumer.md)
