[← 157. UnaryOperator](157_UnaryOperator.md) | [Course Index](../README.md) | [159. Higher-order style programming →](159_Higher-order_style_programming.md)

---

# 158. BinaryOperator

**Section:** Java Functional Programming

**Level:** Beginner → Advanced

**Version:** All examples use Java 8-compatible APIs.

## 1. What is a BinaryOperator?

`BinaryOperator<T>` combines two inputs of type `T` into a result of type `T`. It extends `BiFunction<T, T, T>` and inherits `apply`.

```java
import java.util.function.BinaryOperator;

// Inside a method:
BinaryOperator<Integer> add = (first, second) -> first + second;
System.out.println(add.apply(10, 5)); // 15
```

Use it for arithmetic, selecting one of two values, combining immutable summaries, reducing streams, and resolving duplicate map values. Declaring an operator does not execute it. Invoke `apply` or pass it to an API that invokes it.

Its core declaration is equivalent to:

```java
@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T, T, T> {
    // Inherits T apply(T first, T second).
    // Also provides static minBy and maxBy factories.
}
```

**Binary** means two inputs. It does not require bitwise operations or binary-number processing.

## 2. Related interfaces

| Interface | Shape | Purpose |
| --- | --- | --- |
| `Function<T, R>` | `T → R` | Transform one input |
| `UnaryOperator<T>` | `T → T` | Transform one same-type input |
| `BiFunction<T, U, R>` | `(T, U) → R` | Transform two possibly different input types |
| `BinaryOperator<T>` | `(T, T) → T` | Combine two same-type inputs |
| `BiPredicate<T, U>` | `(T, U) → boolean` | Check two inputs |
| `BiConsumer<T, U>` | `(T, U) → void` | Perform an action with two inputs |

```java
BinaryOperator<Integer> sum = Integer::sum;
BiFunction<String, Integer, String> describe = (name, age) -> name + ": " + age;
// BinaryOperator<String> invalid = (a, b) -> a.length() + b.length();
// Invalid: the result would be Integer rather than String.
```

A BinaryOperator is usable as a BiFunction. An arbitrary existing BiFunction reference does not automatically become a BinaryOperator:

```java
BiFunction<Integer, Integer, Integer> function = Integer::sum;
BinaryOperator<Integer> operator = function::apply; // Explicit adaptation
```

## 3. Arithmetic examples

Unless shown otherwise, snippets assume imports from `java.util`, `java.util.function`, and `java.util.stream`, and belong inside a method. Inputs are non-null. Arithmetic examples assume small values unless testing overflow explicitly.

```java
BinaryOperator<Integer> add = Integer::sum;
BinaryOperator<Integer> subtract = (a, b) -> a - b;
BinaryOperator<Integer> multiply = (a, b) -> a * b;
BinaryOperator<Integer> divide = (a, b) -> a / b;
BinaryOperator<Integer> remainder = (a, b) -> a % b;
BinaryOperator<Integer> maximum = Integer::max;

System.out.println(add.apply(10, 5));       // 15
System.out.println(subtract.apply(10, 5));  // 5
System.out.println(multiply.apply(10, 5));  // 50
System.out.println(divide.apply(10, 4));    // 2: integer division
System.out.println(remainder.apply(10, 4)); // 2
System.out.println(maximum.apply(10, 5));   // 10
```

Division and remainder by zero throw `ArithmeticException`. Ordinary integer arithmetic can overflow silently. Use exact operations when overflow should fail:

```java
BinaryOperator<Integer> safeAdd = Math::addExact;
System.out.println(safeAdd.apply(10, 20)); // 30
// safeAdd.apply(Integer.MAX_VALUE, 1); // ArithmeticException
```

Exact arithmetic is useful for a direct call, but overflow-sensitive exception behavior can depend on grouping in a parallel reduction. Choose your numeric representation and reduction policy deliberately.

### Bitwise and boolean examples

```java
BinaryOperator<Integer> bitwiseAnd = (a, b) -> a & b;
BinaryOperator<Integer> bitwiseOr = (a, b) -> a | b;
BinaryOperator<Integer> xor = (a, b) -> a ^ b;
BinaryOperator<Boolean> both = (a, b) -> a && b;
System.out.println(bitwiseAnd.apply(6, 3)); // 2
System.out.println(bitwiseOr.apply(6, 3));  // 7
System.out.println(xor.apply(6, 3));        // 5
System.out.println(both.apply(true, false)); // false
```

Both arguments to `apply` are evaluated before invocation. A boolean operator does not provide lazy evaluation of caller expressions in the way built-in `&&` and `||` can. Null boxed Boolean values may fail on unboxing.

## 4. String combination

```java
BinaryOperator<String> concat = String::concat;
BinaryOperator<String> spaced = (first, second) -> first + " " + second;
BinaryOperator<String> longer = (first, second) ->
        first.length() >= second.length() ? first : second;

System.out.println(concat.apply("Java", "Script")); // JavaScript
System.out.println(spaced.apply("Hello", "Java"));  // Hello Java
System.out.println(longer.apply("Ada", "Grace"));  // Grace
```

These rules have different semantics. The `longer` example explicitly keeps the first value on equal lengths. String concatenation order matters: combining two strings is not generally commutative.

For joining many strings with separators, prefer:

```java
String names = Arrays.asList("Ada", "Grace", "Alan").stream()
        .collect(Collectors.joining(", "));
System.out.println(names); // Ada, Grace, Alan
```

Repeated immutable concatenation can allocate many intermediate strings. A joining collector better expresses this task.

## 5. minBy and maxBy

```java
Comparator<String> byLength = Comparator.comparingInt(String::length);
BinaryOperator<String> shorter = BinaryOperator.minBy(byLength);
BinaryOperator<String> longer = BinaryOperator.maxBy(byLength);

System.out.println(shorter.apply("Ada", "Grace")); // Ada
System.out.println(longer.apply("Ada", "Grace"));  // Grace
```

These factories select an operand using a comparator. Passing a null comparator throws. The operands' null policy depends on the comparator:

```java
Comparator<String> nullLast = Comparator.nullsLast(Comparator.naturalOrder());
BinaryOperator<String> minimum = BinaryOperator.minBy(nullLast);
System.out.println(minimum.apply(null, "Java")); // Java
```

If comparator-equal objects must be distinguished, define a tie-breaker or an explicit selection policy rather than relying on which equal operand is selected.

```java
Comparator<String> byLengthThenName = Comparator.comparingInt(String::length)
        .thenComparing(Comparator.naturalOrder());
BinaryOperator<String> preferred = BinaryOperator.minBy(byLengthThenName);
System.out.println(preferred.apply("Bob", "Ada")); // Ada
```

Use comparison methods instead of subtraction in numeric comparators to avoid overflow.

## 6. Reducing streams with an identity

```java
List<Integer> numbers = Arrays.asList(2, 3, 4);
BinaryOperator<Integer> sum = Integer::sum;
BinaryOperator<Integer> product = (a, b) -> a * b;
System.out.println(numbers.stream().reduce(0, sum));     // 9
System.out.println(numbers.stream().reduce(1, product)); // 24
System.out.println(Stream.<Integer>empty().reduce(0, sum)); // 0
```

An identity is neutral for the operation. Useful choices include:

| Operation | Identity |
| --- | --- |
| Integer addition | `0` |
| Integer multiplication | `1` |
| String concatenation | `""` |
| Bitwise OR on int values | `0` |
| Bitwise AND on int values | `-1` (all bits set) |

A wrong identity can corrupt results, especially when parallel partitions each start from it. For example, `reduce(10, Integer::sum)` does not mean “sum, then add 10” under the required reduction contract. Calculate the valid sum first, then add the offset.

## 7. Reduction without an identity

```java
BinaryOperator<Integer> max = Integer::max;
Optional<Integer> largest = Arrays.asList(3, 9, 4).stream().reduce(max);
System.out.println(largest.orElse(-1)); // 9

Optional<Integer> absent = Stream.<Integer>empty().reduce(max);
System.out.println(absent.isPresent()); // false
```

This form returns Optional because an empty stream has no result. It is useful when there is no natural identity. A null final result cannot be represented by this Optional-returning reduction and causes a failure; define null handling before reduction.

For ordinary min/max selection, dedicated methods may be clearer:

```java
Optional<String> longest = Arrays.asList("Ada", "Grace", "Alan").stream()
        .max(Comparator.comparingInt(String::length));
```

## 8. Associativity, ordering, and parallel correctness

An associative operation satisfies:

```text
op(op(a, b), c) == op(a, op(b, c))
```

Stream reduction requires an associative, stateless, non-interfering operation. Regrouping must preserve the intended result. Addition of ints uses defined wraparound arithmetic; subtraction is not associative:

```text
(10 - 3) - 2 = 5
10 - (3 - 2) = 9
```

Subtraction can be a valid direct BinaryOperator call, but is unsuitable for general stream reduction. Use an explicit loop when your algorithm requires a sequential left fold with non-associative behavior.

**Associative is not the same as commutative.** String concatenation is associative, but `"A" + "B"` differs from `"B" + "A"`. Preserve the order required by your operation and source.

Floating-point addition can change under regrouping due to rounding. Use numerical algorithms and types appropriate to the problem; do not assume a parallel floating-point sum must exactly match a sequential result.

Keep reducers free of required side effects and shared mutable accumulators. For mutable result containers, prefer `collect` rather than mutating an identity object inside `reduce`.

## 9. Three-argument reduce: mapping plus combining

```java
List<String> names = Arrays.asList("Ada", "Grace", "Alan");
int totalLength = names.stream().reduce(
        0,
        (total, name) -> total + name.length(),
        Integer::sum);
System.out.println(totalLength); // 12
```

This overload uses:

- An identity of the result type, here Integer.
- A BiFunction accumulating a source element into that result type.
- A BinaryOperator combining partial results.

The combiner must be associative and compatible with the accumulator and identity. Here a clearer primitive equivalent is `names.stream().mapToInt(String::length).sum()`.

The types differ from three-argument `collect`, whose accumulator and combiner are BiConsumers acting on mutable containers.

## 10. Map.merge and duplicate keys

### Update a count

```java
Map<String, Integer> counts = new HashMap<>();
BinaryOperator<Integer> add = Integer::sum;
counts.merge("Java", 1, add);
counts.merge("Java", 1, add);
System.out.println(counts.get("Java")); // 2
```

The remapping function receives the old and incoming values when a non-null old value exists. Otherwise the incoming non-null value is inserted without calling it. Returning null from the remapping function removes the existing mapping.

```java
counts.merge("Java", 1, (oldValue, incoming) -> null);
System.out.println(counts.containsKey("Java")); // false
```

Atomicity and concurrency guarantees depend on the map implementation; an ordinary HashMap is not made thread-safe by using merge.

### Resolve duplicate collector keys

```java
Map<Character, String> namesByInitial = Arrays.asList("Ada", "Alan", "Grace").stream()
        .collect(Collectors.toMap(
                name -> name.charAt(0),
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparingInt(String::length))));
System.out.println(namesByInitial.get('A')); // Alan
System.out.println(namesByInitial.get('G')); // Grace
```

The merge operator decides which value survives a duplicate key. The example assumes non-empty names and unequal lengths for names sharing an initial. Define a tie policy when it matters. Without a merge function, duplicate keys cause `toMap` to throw. Do not rely on the default map's iteration order.

## 11. Combining domain values

Declare this immutable type at class scope:

```java
static class Totals {
    private final int count;
    private final long amountInCents;

    Totals(int count, long amountInCents) {
        this.count = count;
        this.amountInCents = amountInCents;
    }
}

// Inside a method in the enclosing class:
BinaryOperator<Totals> combine = (first, second) -> new Totals(
        Math.addExact(first.count, second.count),
        Math.addExact(first.amountInCents, second.amountInCents));
Totals result = combine.apply(new Totals(2, 1500), new Totals(3, 2500));
System.out.println(result.count);         // 5
System.out.println(result.amountInCents); // 4000
```

This creates a new value rather than modifying either input. Integral cents are an example representation for a fixed currency scale; define currency, scale, rounding, and overflow rules for real financial models. Exact arithmetic may throw when limits are exceeded.

A BinaryOperator may select an existing operand or create a new object; same-type inputs and result do not imply any particular copying or mutation policy.

## 12. Primitive binary operators

| Interface | Shape | Invocation |
| --- | --- | --- |
| `IntBinaryOperator` | `(int, int) → int` | `applyAsInt(a, b)` |
| `LongBinaryOperator` | `(long, long) → long` | `applyAsLong(a, b)` |
| `DoubleBinaryOperator` | `(double, double) → double` | `applyAsDouble(a, b)` |

```java
IntBinaryOperator add = Integer::sum;
LongBinaryOperator max = Long::max;
DoubleBinaryOperator multiply = (a, b) -> a * b;
System.out.println(add.applyAsInt(4, 6));       // 10
System.out.println(max.applyAsLong(10L, 20L));  // 20
System.out.println(multiply.applyAsDouble(2.5, 4.0)); // 10.0

int sum = IntStream.rangeClosed(1, 4).reduce(0, add);
System.out.println(sum); // 10
```

These avoid boxed inputs and results. They are separate types from BinaryOperator<Integer> and similar boxed forms, and do not provide the generic inherited andThen method.

```java
BinaryOperator<Integer> boxed = add::applyAsInt;
IntBinaryOperator primitive = boxed::apply; // Null boxed results would fail on unboxing
```

## 13. Composition with andThen

BinaryOperator inherits andThen from BiFunction:

```java
BinaryOperator<Integer> multiply = (a, b) -> a * b;
BiFunction<Integer, Integer, String> describe =
        multiply.andThen(result -> "Product: " + result);
System.out.println(describe.apply(3, 4)); // Product: 12
```

The next function consumes the single combined result, not the original two arguments.

**The declared return type is BiFunction**, even when the result remains the same type:

```java
Function<Integer, Integer> doubleIt = value -> value * 2;
BiFunction<Integer, Integer, Integer> composed = multiply.andThen(doubleIt);
// BinaryOperator<Integer> invalid = multiply.andThen(doubleIt);
BinaryOperator<Integer> adapted = composed::apply;
System.out.println(adapted.apply(3, 4)); // 24
```

BinaryOperator has no compose method and no general identity factory. Unlike UnaryOperator.identity, a reduction identity is a **value** chosen for a particular operation.

## 14. Atomic accumulation

```java
java.util.concurrent.atomic.AtomicInteger count =
        new java.util.concurrent.atomic.AtomicInteger(10);
System.out.println(count.accumulateAndGet(5, Integer::sum)); // 15
System.out.println(count.getAndAccumulate(2, Integer::sum)); // 15: previous value
System.out.println(count.get()); // 17
```

AtomicReference offers corresponding operations with BinaryOperator<T>. Under contention, update functions may be retried; they must be safe to reapply without external effects. Do not send messages, mutate shared input objects, or perform required I/O inside such a function.

## 15. Passing operators and handling failures

Declare this method inside a class:

```java
static <T> T combine(T first, T second, BinaryOperator<T> operation) {
    return Objects.requireNonNull(operation, "operation").apply(first, second);
}

// Inside main:
System.out.println(combine(10, 5, Integer::sum)); // 15
```

Element null handling belongs to the operator. For example, unboxing null Integer arguments throws. A custom rule can explicitly reject or handle them:

```java
BinaryOperator<String> joinNullable = (a, b) ->
        Objects.toString(a, "") + Objects.toString(b, "");
System.out.println(joinNullable.apply(null, "Java")); // Java
```

This deliberately treats null as empty text. `apply` declares no checked exceptions. Handle checked failures inside a wrapper or define a suitable throwing interface. Runtime exceptions propagate; composition does not recover automatically.

## 16. Complete runnable example

Save as `BinaryOperatorDemo.java`. This example works on Java 8+.

```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;

public class BinaryOperatorDemo {
    public static void main(String[] args) {
        BinaryOperator<Integer> add = Integer::sum;
        BinaryOperator<Integer> multiply = (a, b) -> a * b;
        System.out.println(add.apply(10, 5));
        System.out.println(multiply.apply(10, 5));

        List<Integer> values = Arrays.asList(2, 3, 4);
        System.out.println(values.stream().reduce(0, add));
        System.out.println(values.stream().reduce(1, multiply));
        Optional<Integer> max = values.stream().reduce(Integer::max);
        System.out.println(max.orElse(-1));

        BinaryOperator<String> longer = BinaryOperator.maxBy(
                Comparator.comparingInt(String::length));
        System.out.println(longer.apply("Ada", "Grace"));

        Map<String, Integer> counts = new HashMap<>();
        counts.merge("Java", 1, add);
        counts.merge("Java", 1, add);
        System.out.println(counts.get("Java"));

        Map<Character, String> namesByInitial =
                Arrays.asList("Ada", "Alan", "Grace").stream()
                        .collect(Collectors.toMap(
                                name -> name.charAt(0),
                                Function.identity(),
                                longer));
        System.out.println(namesByInitial.get('A'));
        System.out.println(namesByInitial.get('G'));

        BiFunction<Integer, Integer, String> format =
                multiply.andThen(value -> "Product: " + value);
        System.out.println(format.apply(3, 4));

        IntBinaryOperator primitiveAdd = Integer::sum;
        System.out.println(primitiveAdd.applyAsInt(7, 8));
        System.out.println(values.parallelStream().reduce(0, add));
    }
}
```

Compile and run:

```shell
javac BinaryOperatorDemo.java
java BinaryOperatorDemo
```

Expected output:

```text
15
50
9
24
4
Grace
2
Alan
Grace
Product: 12
15
9
```

## 17. Common mistakes

| Mistake | Better approach |
| --- | --- |
| Returning a different type | Use BiFunction<T, U, R> |
| Calling apply with only one argument | Supply two inputs, or choose UnaryOperator |
| Reducing with subtraction or division | Use an associative operation or an explicit sequential loop |
| Choosing the wrong identity | Verify that it is neutral for the operator |
| Assuming associativity means commutativity | Consider regrouping and reordering separately |
| Mutating a shared reduce identity | Use collect for mutable accumulation |
| Expecting andThen to return BinaryOperator | Store the BiFunction result or adapt it explicitly |
| Ignoring duplicate map keys | Define a merge and tie policy |
| Using comparator subtraction | Use comparison methods or comparator factories |
| Putting external effects in atomic callbacks | Keep update functions safe for retries |

## 18. Practice exercises

1. Write sum, product, and maximum operators; test two different input pairs.
2. Reduce `[2, 3, 4]` using the correct identities for sum and product.
3. Explain why subtraction is not associative using concrete numbers.
4. Select the shortest string using minBy and define a tie-breaker.
5. Count repeated words with Map.merge.
6. Collect names by initial and resolve duplicates with a documented rule.
7. Combine immutable summary objects without modifying either input.
8. Compare boxed and primitive integer reductions.
9. Compose multiplication with formatting; explain its declared return type.
10. Compare empty-stream reduction with and without an identity.
11. Explain why a shared StringBuilder is a poor mutable reduce identity.
12. Demonstrate atomic accumulation and explain retry safety.

**Hints:** 2. identities 0 and 1; 3. `(10 - 3) - 2` versus `10 - (3 - 2)`; 5. merge with Integer::sum; 9. BiFunction; 10. identity value versus Optional.empty; 11. use a collector for independent mutable containers.

## Completion checklist

- [ ] I can distinguish BinaryOperator from BiFunction and UnaryOperator.
- [ ] I can create and invoke arithmetic, selection, and combination operators.
- [ ] I understand minBy, maxBy, and comparator policies.
- [ ] I can choose reduction identities and explain associativity.
- [ ] I can use map merging, primitive operators, and result composition.
- [ ] I understand nulls, overflow, mutation, and atomic retries.
- [ ] I can explain the complete runnable example.

## Official references

- [BinaryOperator API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/BinaryOperator.html) — same-type combination and selection factories.
- [BiFunction API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/BiFunction.html) — inherited result composition.
- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html) — reduction requirements.
- [Map API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Map.html) — merge behavior.
- [AtomicInteger API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/atomic/AtomicInteger.html) — accumulation callbacks.

---

[← 157. UnaryOperator](157_UnaryOperator.md) | [Course Index](../README.md) | [159. Higher-order style programming →](159_Higher-order_style_programming.md)
