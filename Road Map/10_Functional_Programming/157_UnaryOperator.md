[← 156. Supplier](156_Supplier.md) | [Course Index](../README.md) | [158. BinaryOperator →](158_BinaryOperator.md)

---

# 157. UnaryOperator

**Section:** Java Functional Programming

**Level:** Beginner → Advanced

**Version:** All examples use Java 8-compatible APIs.

## 1. What is a UnaryOperator?

`UnaryOperator<T>` describes a transformation taking one value of type `T` and returning a value of the same type. It extends `Function<T, T>` and inherits its abstract method `apply`.

```java
import java.util.function.UnaryOperator;

// Inside a method:
UnaryOperator<Integer> square = number -> number * number;
System.out.println(square.apply(5)); // 25
```

Use it for same-type transformations such as normalizing text, updating a configuration value, adjusting a number, or creating an updated immutable object. Defining an operator does not execute it; call `apply` or pass it to an API that invokes it.

The declaration is equivalent to:

```java
@FunctionalInterface
public interface UnaryOperator<T> extends Function<T, T> {
    // Inherits T apply(T value).
    // Also provides static identity().
}
```

**Unary** means one input. It does not mean the operator must use a unary arithmetic symbol or mutate the input object.

## 2. Comparison with other functional interfaces

| Interface | Shape | Use |
| --- | --- | --- |
| `Function<T, R>` | `T → R` | General transformation |
| `UnaryOperator<T>` | `T → T` | Same-type transformation |
| `BinaryOperator<T>` | `(T, T) → T` | Combine two same-type inputs |
| `Predicate<T>` | `T → boolean` | Check a condition |
| `Consumer<T>` | `T → void` | Perform an action |
| `Supplier<T>` | `() → T` | Supply a value |

```java
UnaryOperator<String> trim = String::trim;
Function<String, Integer> length = String::length;
// UnaryOperator<String> invalid = String::length; // Result is not String
```

A UnaryOperator is usable as a Function, but an existing Function reference is not automatically a UnaryOperator:

```java
Function<String, String> function = String::trim;
// UnaryOperator<String> operator = function; // Invalid assignment
UnaryOperator<String> operator = function::apply; // Explicit adaptation
```

## 3. Numeric examples

Unless imports are shown, snippets assume imports from `java.util`, `java.util.function`, and `java.util.stream`, and appear inside a method. Inputs are non-null unless handled explicitly. Basic arithmetic examples assume values small enough to avoid overflow.

```java
UnaryOperator<Integer> increment = n -> n + 1;
UnaryOperator<Integer> square = n -> n * n;
UnaryOperator<Integer> negate = n -> -n;
UnaryOperator<Integer> clamp = n -> Math.max(0, Math.min(100, n));

System.out.println(increment.apply(5)); // 6
System.out.println(square.apply(6));    // 36
System.out.println(negate.apply(4));    // -4
System.out.println(clamp.apply(120));   // 100
System.out.println(clamp.apply(-5));    // 0
```

Generic arguments use reference types, such as `Integer`, not `int`. Primitive specializations avoid boxing and are covered later.

### Detect overflow when required

```java
UnaryOperator<Integer> safeSquare = n -> Math.multiplyExact(n, n);
System.out.println(safeSquare.apply(100)); // 10000
// safeSquare.apply(50000); // Throws ArithmeticException
```

Ordinary integer arithmetic can overflow silently. Negating or taking the absolute value of `Integer.MIN_VALUE` cannot produce its positive magnitude as an `int`; choose an explicit policy or a wider representation.

## 4. String normalization examples

```java
UnaryOperator<String> trim = String::trim;
UnaryOperator<String> upper = text -> text.toUpperCase(Locale.ROOT);
UnaryOperator<String> lower = text -> text.toLowerCase(Locale.ROOT);
UnaryOperator<String> removeHyphens = text -> text.replace("-", "");
UnaryOperator<String> collapseWhitespace = text -> text.trim().replaceAll("\\s+", " ");

System.out.println(trim.apply(" Java "));       // Java
System.out.println(upper.apply("java"));        // JAVA
System.out.println(lower.apply("JAVA"));        // java
System.out.println(removeHyphens.apply("A-B")); // AB
System.out.println(collapseWhitespace.apply(" Java   notes ")); // Java notes
```

`Locale.ROOT` makes casing independent of the machine's default locale. `trim()` and the default regex whitespace class do not cover every Unicode whitespace policy. These are illustrative normalization rules, not universal rules for names, passwords, or identifiers.

## 5. Creating operators

Lambdas, method references, and classes can provide the implementation:

```java
UnaryOperator<String> lambda = text -> text.trim();
UnaryOperator<String> reference = String::trim;
UnaryOperator<String> anonymous = new UnaryOperator<String>() {
    @Override
    public String apply(String text) {
        return text.trim();
    }
};
```

The target interface supplies the required signature. A bare `var trim = String::trim;` has no functional interface target and is invalid; local-variable `var` itself requires Java 10+.

## 6. Composition: andThen and compose

```java
UnaryOperator<Integer> doubleIt = n -> n * 2;
UnaryOperator<Integer> addThree = n -> n + 3;

Function<Integer, Integer> first = doubleIt.andThen(addThree);
Function<Integer, Integer> second = doubleIt.compose(addThree);
System.out.println(first.apply(5));  // 13: (5 * 2) + 3
System.out.println(second.apply(5)); // 16: (5 + 3) * 2
```

- `f.andThen(g)` means `g(f(input))`.
- `f.compose(g)` means `f(g(input))`.

### Important: generic composition returns Function

UnaryOperator inherits these methods from Function. Their declared result is a `Function`, even when both stages are unary operators.

```java
UnaryOperator<String> trim = String::trim;
UnaryOperator<String> upper = text -> text.toUpperCase(Locale.ROOT);
Function<String, String> pipeline = trim.andThen(upper);

// UnaryOperator<String> invalid = trim.andThen(upper); // Does not compile
UnaryOperator<String> normalize = pipeline::apply;
// Another option: text -> upper.apply(trim.apply(text))
System.out.println(normalize.apply(" java ")); // JAVA
```

The inherited API can even change the final type:

```java
Function<String, Integer> trimmedLength = trim.andThen(String::length);
System.out.println(trimmedLength.apply(" Java ")); // 4
```

Composition does not immediately execute the stages. A null component throws during composition; if a stage throws during invocation, later stages do not run. There is no automatic exception recovery or rollback of earlier mutations.

## 7. Identity operator

```java
UnaryOperator<String> same = UnaryOperator.identity();
System.out.println(same.apply("Java")); // Java
System.out.println(same.apply(null));   // null
```

Identity returns the input itself, not a copy:

```java
List<String> original = new ArrayList<>();
UnaryOperator<List<String>> identity = UnaryOperator.identity();
System.out.println(identity.apply(original) == original); // true
```

It is useful as a no-change policy or the starting point for a sequence of transformations. A no-op policy does not imply the input object is immutable.

## 8. List.replaceAll

```java
List<String> names = new ArrayList<>(Arrays.asList(" ada ", " grace "));
UnaryOperator<String> normalize = text -> text.trim().toUpperCase(Locale.ROOT);
names.replaceAll(normalize);
System.out.println(names); // [ADA, GRACE]
```

`replaceAll` updates each list position with the operator result; it does not remove or add positions. It requires a list supporting element replacement.

```java
List<Integer> values = Arrays.asList(1, 2, 3);
values.replaceAll(n -> n * 10);
System.out.println(values); // [10, 20, 30]
```

`Arrays.asList` is fixed-size but supports replacement; its backing array is also updated. Unmodifiable lists reject replacement. If an operator throws partway through, earlier replacements may already have happened; replacement is not transactional.

## 9. Streams: transform without replacing source elements

```java
List<Integer> values = Arrays.asList(1, 2, 3);
UnaryOperator<Integer> square = n -> n * n;
List<Integer> squares = values.stream()
        .map(square)
        .collect(Collectors.toList());
System.out.println(squares); // [1, 4, 9]
System.out.println(values);  // [1, 2, 3]
```

`Stream.map` accepts Function, so it also accepts UnaryOperator. Mapping does not replace positions in the source. An operator that mutates element objects can still change shared objects; collecting a new list is not a deep copy.

### Iterative sequences

```java
List<Integer> sequence = Stream.iterate(1, n -> n * 2)
        .limit(5)
        .collect(Collectors.toList());
System.out.println(sequence); // [1, 2, 4, 8, 16]
```

The two-argument `Stream.iterate` accepts a seed and a UnaryOperator for the next value. It is potentially infinite, so bound it when collecting. Processing is lazy. Arithmetic may overflow if the sequence continues too far.

## 10. Primitive unary operators

| Interface | Shape | Invocation |
| --- | --- | --- |
| `IntUnaryOperator` | `int → int` | `applyAsInt(value)` |
| `LongUnaryOperator` | `long → long` | `applyAsLong(value)` |
| `DoubleUnaryOperator` | `double → double` | `applyAsDouble(value)` |

```java
IntUnaryOperator square = n -> n * n;
LongUnaryOperator next = n -> n + 1L;
DoubleUnaryOperator half = n -> n / 2.0;

System.out.println(square.applyAsInt(6));   // 36
System.out.println(next.applyAsLong(10L));  // 11
System.out.println(half.applyAsDouble(5));   // 2.5

int total = IntStream.rangeClosed(1, 3).map(square).sum();
System.out.println(total); // 14
```

These avoid boxing but are separate types from UnaryOperator<Integer>, UnaryOperator<Long>, and UnaryOperator<Double>.

### Primitive composition preserves its operator type

```java
IntUnaryOperator doubleIt = n -> n * 2;
IntUnaryOperator addThree = n -> n + 3;
IntUnaryOperator pipeline = doubleIt.andThen(addThree);
System.out.println(pipeline.applyAsInt(5)); // 13
System.out.println(IntUnaryOperator.identity().applyAsInt(7)); // 7
```

Unlike inherited generic composition, IntUnaryOperator's own composition methods return IntUnaryOperator. LongUnaryOperator and DoubleUnaryOperator provide the analogous API.

Adapt explicitly when switching between primitive and boxed forms:

```java
UnaryOperator<Integer> boxed = square::applyAsInt;
IntUnaryOperator primitive = boxed::apply; // Null results would fail on unboxing
```

A type-changing primitive transformation, such as `int → double`, belongs to `IntToDoubleFunction` rather than a unary operator with matching input and result types.

## 11. Immutable updates versus mutation

Put this class at class scope:

```java
static class Counter {
    private final int value;
    Counter(int value) { this.value = value; }
    int getValue() { return value; }
}

// Inside a method:
UnaryOperator<Counter> increment = counter ->
        new Counter(Math.addExact(counter.getValue(), 1));
Counter original = new Counter(10);
Counter updated = increment.apply(original);
System.out.println(original.getValue()); // 10
System.out.println(updated.getValue());  // 11
```

Same type does not require the same object. A different operator might mutate and return the input:

```java
UnaryOperator<StringBuilder> appendMarker = builder -> builder.append("!");
StringBuilder text = new StringBuilder("Java");
System.out.println(appendMarker.apply(text)); // Java!
System.out.println(text);                    // Java!
```

Document mutation clearly. Prefer a Consumer when the operation's purpose is only an effect and callers should not use a returned object.

## 12. Optional and atomic updates

### Optional.map

```java
UnaryOperator<String> trim = String::trim;
Optional<String> name = Optional.of(" Ada ").map(trim);
System.out.println(name.orElse("Guest")); // Ada
```

An empty Optional skips the operator. If the operator returns null, Optional.map produces an empty Optional.

### AtomicReference.updateAndGet

```java
java.util.concurrent.atomic.AtomicReference<String> state =
        new java.util.concurrent.atomic.AtomicReference<>("draft");
UnaryOperator<String> publish = text -> text.toUpperCase(Locale.ROOT);
System.out.println(state.updateAndGet(publish)); // DRAFT
```

Under contention, atomic update functions may be retried. Keep the function free of side effects such as sending messages or incrementing external counters. `updateAndGet` returns the updated value; `getAndUpdate` returns the previous value.

For atomic integers, use a primitive operator:

```java
java.util.concurrent.atomic.AtomicInteger count =
        new java.util.concurrent.atomic.AtomicInteger(5);
System.out.println(count.updateAndGet(n -> n + 1)); // 6
```

An atomic reference protects its reference update; it does not make a mutable referenced object automatically thread-safe.

## 13. Passing and returning operators

Declare these helpers inside a class:

```java
static <T> T applyTimes(T value, int times, UnaryOperator<T> operation) {
    Objects.requireNonNull(operation, "operation");
    if (times < 0) {
        throw new IllegalArgumentException("times must not be negative");
    }
    T result = value;
    for (int i = 0; i < times; i++) {
        result = operation.apply(result);
    }
    return result;
}

static UnaryOperator<Integer> multiplier(int factor) {
    return value -> value * factor;
}

// Inside main:
System.out.println(applyTimes(1, 3, n -> n * 2)); // 8
System.out.println(multiplier(3).apply(7));      // 21
```

The captured factor is effectively final. Zero repetitions return the original input. This helper does not add null handling; that belongs to the operation's documented policy.

For generic operators, an API may deliberately require `UnaryOperator<T>` to enforce matching input and result types. Use Function with suitable wildcard bounds when a more general transformation is needed.

## 14. Nulls, exceptions, and safe design

```java
UnaryOperator<String> safeTrim = text -> text == null ? "" : text.trim();
System.out.println(safeTrim.apply(null)); // Empty string
```

This policy deliberately treats missing text as empty; do not use it when the distinction matters. Without a guard, dereferencing or unboxing null can throw.

`apply` declares no checked exceptions. Handle them inside the operator, use a named wrapper, or define a throwing interface:

```java
@FunctionalInterface
interface ThrowingUnaryOperator<T, E extends Exception> {
    T apply(T value) throws E;
}

// Inside a method, with java.nio.file and java.io imports:
ThrowingUnaryOperator<Path, IOException> realPath = path -> path.toRealPath();
// Calling realPath.apply(path) requires handling or declaring IOException.
```

UnaryOperator does not guarantee purity, immutability, thread safety, or idempotence. An idempotent transformation gives the same result when repeated; trimming is typically idempotent, while incrementing is not. This matters when transformations can be retried or applied repeatedly.

## 15. Complete runnable example

Save as `UnaryOperatorDemo.java`. This program works on Java 8+.

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnaryOperatorDemo {
    static UnaryOperator<Integer> multiplier(int factor) {
        return number -> number * factor;
    }

    public static void main(String[] args) {
        UnaryOperator<String> trim = String::trim;
        UnaryOperator<String> upper = text -> text.toUpperCase(Locale.ROOT);
        Function<String, String> pipeline = trim.andThen(upper);
        UnaryOperator<String> normalize = pipeline::apply;

        List<String> names = new ArrayList<>(Arrays.asList(" ada ", " grace "));
        names.replaceAll(normalize);
        System.out.println(names);

        UnaryOperator<Integer> square = number -> number * number;
        List<Integer> squares = Arrays.asList(1, 2, 3).stream()
                .map(square).collect(Collectors.toList());
        System.out.println(squares);

        UnaryOperator<Integer> doubleIt = number -> number * 2;
        UnaryOperator<Integer> addThree = number -> number + 3;
        System.out.println(doubleIt.andThen(addThree).apply(5));
        System.out.println(doubleIt.compose(addThree).apply(5));

        IntUnaryOperator primitiveDouble = number -> number * 2;
        IntUnaryOperator primitiveAdd = number -> number + 3;
        IntUnaryOperator primitivePipeline = primitiveDouble.andThen(primitiveAdd);
        System.out.println(primitivePipeline.applyAsInt(5));

        List<Integer> powers = Stream.iterate(1, number -> number * 2)
                .limit(5).collect(Collectors.toList());
        System.out.println(powers);

        UnaryOperator<List<String>> identity = UnaryOperator.identity();
        System.out.println(identity.apply(names) == names);
        System.out.println(multiplier(3).apply(7));
    }
}
```

Compile and run:

```shell
javac UnaryOperatorDemo.java
java UnaryOperatorDemo
```

Expected output:

```text
[ADA, GRACE]
[1, 4, 9]
13
16
13
[1, 2, 4, 8, 16]
true
21
```

## 16. Common mistakes

| Mistake | Better approach |
| --- | --- |
| Using UnaryOperator for a type-changing result | Use Function<T, R> |
| Calling accept or test | Invoke apply, or applyAsInt/Long/Double for primitive forms |
| Assigning generic andThen directly to UnaryOperator | Store the Function result and adapt with a lambda or method reference |
| Assuming replaceAll works on unmodifiable lists | Use a replacement-capable list or collect a new list |
| Assuming same type means in-place mutation | Read the operator's object and mutation policy |
| Treating identity as a copy | It returns the original input |
| Ignoring integer overflow | Use exact arithmetic or another explicit policy |
| Putting effects into atomic update functions | Keep them safe to retry |
| Assuming mapping copies nested objects | Define copy depth and ownership explicitly |
| Assuming a null input is automatically safe | Define and implement a null policy |

## 17. Practice exercises

1. Write an operator that clamps integers to the inclusive range 0–100.
2. Normalize text by trimming and uppercasing with Locale.ROOT.
3. Chain increment and square in both orders; predict the outputs for 3.
4. Explain why generic andThen cannot be assigned directly to UnaryOperator.
5. Use replaceAll to triple every element of a mutable list.
6. Produce a new list of transformed values without replacing the source's elements.
7. Generate the first six powers of two with Stream.iterate.
8. Implement applyTimes and test zero, one, and three repetitions.
9. Create an immutable updated object while preserving the original.
10. Demonstrate reference preservation with UnaryOperator.identity.
11. Use IntUnaryOperator composition without boxing.
12. Explain why sending an email inside AtomicReference.updateAndGet is unsafe.

**Hints:** 1. nested min/max; 3. increment-then-square gives 16, square-then-increment gives 10; 4. inherited return type is Function; 7. seed 1 and limit 6; 12. the update function may be retried.

## Completion checklist

- [ ] I can distinguish UnaryOperator from Function and BinaryOperator.
- [ ] I can create and invoke same-type transformations.
- [ ] I understand generic composition's declared return type.
- [ ] I can use identity, replaceAll, map, and iterate.
- [ ] I can choose primitive unary operators when appropriate.
- [ ] I understand mutation, nulls, overflow, exceptions, and retry safety.
- [ ] I can explain and run the complete example.

## Official references

- [UnaryOperator API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/UnaryOperator.html) — interface and identity.
- [Function API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/Function.html) — inherited composition methods.
- [IntUnaryOperator API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/IntUnaryOperator.html) — primitive composition.
- [List API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html) — replaceAll contract.
- [AtomicReference API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/atomic/AtomicReference.html) — retryable update functions.

---

[← 156. Supplier](156_Supplier.md) | [Course Index](../README.md) | [158. BinaryOperator →](158_BinaryOperator.md)
