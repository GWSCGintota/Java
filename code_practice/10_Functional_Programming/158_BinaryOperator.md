[← 157. UnaryOperator](157_UnaryOperator.md) | [Course Index](../README.md) | [159. Higher-order style programming →](159_Higher-order_style_programming.md)

# Java BinaryOperator Practice — 10 Coding Questions and Answers

These exercises follow [158. BinaryOperator](../../Road%20Map/10_Functional_Programming/158_BinaryOperator.md), progressing from two-input operations to reductions and merge policies. Each question includes a model solution and a short explanation.

**Java version:** Java 8+. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports. Run exercises independently. Place nested classes inside `Main` and example statements inside `main()`. Inputs are non-null and arithmetic uses small values that do not overflow.

## Question 1 — Sum, Product, and Maximum

**Task:** Create three `BinaryOperator<Integer>` instances for addition, multiplication, and maximum. Apply each to `6, 4`, then `2, 9`.

```java
BinaryOperator<Integer> sum = Integer::sum;
BinaryOperator<Integer> product = (a, b) -> a * b;
BinaryOperator<Integer> maximum = Integer::max;
System.out.println(sum.apply(6, 4));
System.out.println(product.apply(6, 4));
System.out.println(maximum.apply(6, 4));
System.out.println(sum.apply(2, 9));
System.out.println(product.apply(2, 9));
System.out.println(maximum.apply(2, 9));
```

**Output:**
```text
10
24
6
11
18
9
```

**Concept:** `BinaryOperator<T>` combines two values of type `T` into a result of the same type. Invoke it with `apply(first, second)`.

## Question 2 — Choose Correct Reduction Identities

**Task:** Reduce `[2, 3, 4]` to its sum and product. Choose a neutral identity for each operation and show both empty-stream results.

```java
List<Integer> numbers = Arrays.asList(2, 3, 4);
BinaryOperator<Integer> sum = Integer::sum;
BinaryOperator<Integer> product = (a, b) -> a * b;
System.out.println(numbers.stream().reduce(0, sum));
System.out.println(numbers.stream().reduce(1, product));
System.out.println(Stream.<Integer>empty().reduce(0, sum));
System.out.println(Stream.<Integer>empty().reduce(1, product));
```

**Output:**
```text
9
24
0
1
```

**Concept:** Zero is neutral for addition; one is neutral for multiplication. A valid reduction identity must be neutral for every element, including when parallel partitions use it independently.

## Question 3 — Reduce Without an Identity

**Task:** Find the maximum of `[3, 9, 4]` using an operator and the identity-free `reduce()` overload. Compare with an empty stream.

```java
BinaryOperator<Integer> maximum = Integer::max;
Optional<Integer> largest = Arrays.asList(3, 9, 4).stream().reduce(maximum);
Optional<Integer> empty = Stream.<Integer>empty().reduce(maximum);
System.out.println(largest.get());
System.out.println(empty.isPresent());
```

**Output:**
```text
9
false
```

**Concept:** An identity-free reduction returns `Optional<T>` because an empty stream has no result. `get()` is safe here only because the sample input is known to be non-empty.

## Question 4 — Prove Subtraction Is Not Associative

**Task:** Use a subtraction operator to evaluate `(10 - 3) - 2` and `10 - (3 - 2)`.

```java
BinaryOperator<Integer> subtract = (a, b) -> a - b;
System.out.println(subtract.apply(subtract.apply(10, 3), 2));
System.out.println(subtract.apply(10, subtract.apply(3, 2)));
```

**Output:**
```text
5
9
```

**Concept:** Associativity requires regrouping to preserve the result. Subtraction fails this rule, so it is unsuitable as a general stream reduction operator. Use an explicit loop when a particular left-to-right subtraction is required.

## Question 5 — Select by Comparator with a Tie-Breaker

**Task:** Use `BinaryOperator.minBy()` to select the shorter string, breaking equal-length ties alphabetically. Test `Grace, Ada` and `Bob, Ada`.

```java
Comparator<String> byLengthThenName = Comparator.comparingInt(String::length)
        .thenComparing(Comparator.naturalOrder());
BinaryOperator<String> preferred = BinaryOperator.minBy(byLengthThenName);
System.out.println(preferred.apply("Grace", "Ada"));
System.out.println(preferred.apply("Bob", "Ada"));
```

**Output:**
```text
Ada
Ada
```

**Concept:** `minBy()` selects an input according to the comparator. A deliberate secondary key resolves meaningful ties. `maxBy()` selects according to the reverse preference.

## Question 6 — Count Repeated Words with `Map.merge()`

**Task:** Count `[java, lambda, java, stream, lambda, java]` using `Map.merge()` and an addition operator. Print counts in first-seen order.

```java
Map<String, Integer> counts = new LinkedHashMap<>();
BinaryOperator<Integer> add = Integer::sum;
for (String word : Arrays.asList("java", "lambda", "java", "stream", "lambda", "java")) {
    counts.merge(word, 1, add);
}
counts.forEach((word, count) -> System.out.println(word + "=" + count));
```

**Output:**
```text
java=3
lambda=2
stream=1
```

**Concept:** With no existing non-null value, `merge()` stores the supplied value. Otherwise, it combines existing and incoming values. A null remapping result removes an existing mapping; this addition policy always returns a count.

## Question 7 — Resolve Duplicate Collector Keys

**Task:** Collect `[Ada, Alan, Bob, Alice]` by first letter. Keep the longer name for duplicate initials; break equal-length ties alphabetically. Print lookups for `A` and `B`.

```java
Comparator<String> preference = Comparator.comparingInt(String::length)
        .reversed()
        .thenComparing(Comparator.naturalOrder());
BinaryOperator<String> chooseName = BinaryOperator.minBy(preference);
Map<Character, String> byInitial = Arrays.asList("Ada", "Alan", "Bob", "Alice").stream()
        .collect(Collectors.toMap(name -> name.charAt(0), Function.identity(), chooseName));
System.out.println(byInitial.get('A'));
System.out.println(byInitial.get('B'));
```

**Output:**
```text
Alice
Bob
```

**Concept:** The merge operator makes duplicate-key handling explicit. This comparator puts longer names first, so `minBy()` selects the preferred name. Individual lookups avoid depending on default map iteration order.

## Question 8 — Combine Immutable Summary Objects

**Task:** Define an immutable `Summary` containing an item count and total. Combine two summaries into a new summary without changing either input.

```java
static class Summary {
    final int count;
    final int total;
    Summary(int count, int total) { this.count = count; this.total = total; }
}
```

**Example calls:**

```java
Summary first = new Summary(2, 30);
Summary second = new Summary(3, 45);
BinaryOperator<Summary> combine = (a, b) ->
        new Summary(a.count + b.count, a.total + b.total);
Summary result = combine.apply(first, second);
System.out.println(result.count + ":" + result.total);
System.out.println(first.count + ":" + first.total);
System.out.println(second.count + ":" + second.total);
```

**Output:**
```text
5:75
2:30
3:45
```

**Concept:** A binary operator can combine domain values, not just numbers. Returning a new immutable result avoids mutating either input. A summary of `(0, 0)` would be neutral for these combining rules.

## Question 9 — Primitive Operators and Result Composition

**Task:** Sum `2, 3, 4` using an `IntBinaryOperator`. Separately compose a boxed multiplication operator with formatting and store the result in the correct interface type.

```java
IntBinaryOperator add = Integer::sum;
System.out.println(IntStream.of(2, 3, 4).reduce(0, add));
BinaryOperator<Integer> multiply = (a, b) -> a * b;
BiFunction<Integer, Integer, String> formatted =
        multiply.andThen(value -> "Product: " + value);
System.out.println(formatted.apply(3, 4));
```

**Output:**
```text
9
Product: 12
```

**Concept:** Primitive operators use `applyAsInt()` and avoid boxed inputs and results. Generic `andThen()` is inherited from `BiFunction` and returns `BiFunction`, allowing the final result type to change.

## Question 10 — Map While Reducing and Accumulate Atomically

**Task:** Use three-argument `reduce()` to sum the lengths of `Ada` and `Grace`. Then use an integer addition operator with `AtomicInteger.accumulateAndGet()` starting at `10` and adding `5`.

```java
int totalLength = Arrays.asList("Ada", "Grace").stream()
        .reduce(0, (total, name) -> total + name.length(), Integer::sum);
System.out.println(totalLength);
java.util.concurrent.atomic.AtomicInteger total =
        new java.util.concurrent.atomic.AtomicInteger(10);
IntBinaryOperator add = Integer::sum;
System.out.println(total.accumulateAndGet(5, add));
```

**Output:**
```text
8
15
```

**Concept:** Three-argument reduction separates element accumulation from combining partial results; the two rules must be compatible. Atomic accumulation callbacks may be retried, so keep them free of external side effects.

---

## Key Takeaways

| API or rule | Purpose | Example |
|---|---|---|
| `BinaryOperator<T>` | Combine two same-type values | `apply(a, b)` |
| Neutral identity | Leave values unchanged when combined | `0` for sum, `1` for product |
| Associativity | Permit regrouping | Addition, not subtraction |
| `reduce(operator)` | Reduce without a supplied identity | Returns `Optional<T>` |
| `minBy()` / `maxBy()` | Select an input by comparator | Shortest or longest name |
| `Map.merge()` | Combine an existing and incoming value | Word counts |
| `Collectors.toMap()` | Resolve duplicate keys with a merge operator | Name by initial |
| `IntBinaryOperator` | Combine primitive integers | `applyAsInt(a, b)` |
| `andThen()` | Transform a combined result | Returns `BiFunction` |

**Reminder:** Use `collect()` for mutable accumulation containers rather than a shared mutable `reduce()` identity. Associativity and commutativity are different properties: string concatenation is associative but preserves a meaningful order.

[← 157. UnaryOperator](157_UnaryOperator.md) | [Course Index](../README.md) | [159. Higher-order style programming →](159_Higher-order_style_programming.md)
