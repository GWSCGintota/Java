[← 158. BinaryOperator](158_BinaryOperator.md) | [Course Index](../README.md) | [160. Streams →](../11_Stream_API/160_Streams.md)

# Java Higher-order Programming Practice — 10 Coding Questions and Answers

[← Road Map lesson](../../Road%20Map/10_Functional_Programming/159_Higher-order_style_programming.md)

These exercises follow [159. Higher-order style programming](../../Road%20Map/10_Functional_Programming/159_Higher-order_style_programming.md), progressing from passing behavior to factories, pipelines, and wrappers. Each question includes a model solution and a short explanation.

**Java version:** Java 8+. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports. Run questions independently. Place helper methods and nested classes inside `Main`, and example statements inside `main()`. Inputs and behavior references are non-null unless a task says otherwise. Arithmetic uses small values.

## Question 1 — Accept a Calculation Strategy

**Task:** Write `calculate(a, b, operation)` accepting an `IntBinaryOperator`. Use it for addition and multiplication of `6` and `4`.

```java
public static int calculate(int a, int b, IntBinaryOperator operation) {
    return Objects.requireNonNull(operation, "operation").applyAsInt(a, b);
}
```

**Example calls:**

```java
System.out.println(calculate(6, 4, (a, b) -> a + b));
System.out.println(calculate(6, 4, (a, b) -> a * b));
```

**Output:**
```text
10
24
```

**Concept:** A higher-order method accepts behavior, returns behavior, or both. This method receives a strategy and controls when it is invoked.

## Question 2 — Return a Configured Predicate

**Task:** Implement `atLeast(minimum)` returning a predicate. Create predicates for minimum ages `18` and `21`, then test age `20` with both.

```java
public static Predicate<Integer> atLeast(int minimum) {
    return age -> age >= minimum;
}
```

**Example calls:**

```java
Predicate<Integer> adult = atLeast(18);
Predicate<Integer> age21Plus = atLeast(21);
System.out.println(adult.test(20));
System.out.println(age21Plus.test(20));
```

**Output:**
```text
true
false
```

**Concept:** Each returned closure captures its own effectively final configuration. Returning a predicate lets callers reuse the configured rule.

## Question 3 — Create Many Independent Values

**Task:** Implement `createMany(count, factory)`, rejecting negative counts. Use a constructor supplier to create two independent lists. Modify only the first, and test zero creations.

```java
public static <T> List<T> createMany(int count, Supplier<? extends T> factory) {
    if (count < 0) {
        throw new IllegalArgumentException("count must not be negative");
    }
    Objects.requireNonNull(factory, "factory");
    List<T> result = new ArrayList<>();
    for (int i = 0; i < count; i++) {
        result.add(factory.get());
    }
    return result;
}
```

**Example calls:**

```java
List<List<String>> lists = createMany(2, ArrayList<String>::new);
lists.get(0).add("Ada");
System.out.println(lists);
System.out.println(lists.get(0) == lists.get(1));
System.out.println(createMany(0, ArrayList<String>::new).size());
```

**Output:**
```text
[[Ada], []]
false
0
```

**Concept:** The method controls how often the factory runs. Independence comes from the chosen constructor supplier; a supplier returning a captured list would share it.

## Question 4 — Conditionally Invoke a Fallback

**Task:** Write `valueOrGet(value, fallback)` that calls the fallback only for null input. Count fallback invocations while testing a present and a missing value.

```java
public static <T> T valueOrGet(T value, Supplier<? extends T> fallback) {
    Objects.requireNonNull(fallback, "fallback");
    return value != null ? value : fallback.get();
}
```

**Example calls:**

```java
java.util.concurrent.atomic.AtomicInteger calls =
        new java.util.concurrent.atomic.AtomicInteger();
Supplier<String> fallback = () -> {
    calls.incrementAndGet();
    return "Guest";
};
System.out.println(valueOrGet("Ada", fallback));
System.out.println("Calls: " + calls.get());
System.out.println(valueOrGet(null, fallback));
System.out.println("Calls: " + calls.get());
```

**Output:**
```text
Ada
Calls: 0
Guest
Calls: 1
```

**Concept:** Passing a supplier defers work so the receiving method can decide whether it is needed. This helper permits a null fallback result.

## Question 5 — Adapt One Supplier into Another

**Task:** Implement `mapSupplier(source, mapper)`. Count source invocations and show that wrapper creation is lazy while repeated calls are not cached.

```java
public static <T, R> Supplier<R> mapSupplier(
        Supplier<? extends T> source, Function<? super T, ? extends R> mapper) {
    Objects.requireNonNull(source, "source");
    Objects.requireNonNull(mapper, "mapper");
    return () -> mapper.apply(source.get());
}
```

**Example calls:**

```java
java.util.concurrent.atomic.AtomicInteger calls =
        new java.util.concurrent.atomic.AtomicInteger();
Supplier<String> label = mapSupplier(calls::incrementAndGet, n -> "Item " + n);
System.out.println(calls.get());
System.out.println(label.get());
System.out.println(label.get());
```

**Output:**
```text
0
Item 1
Item 2
```

**Concept:** This helper accepts behavior and returns new behavior. Each `get()` invokes the source once and the mapper once. Memoization would be a separate wrapper; see [Supplier Question 10](156_Supplier.md#question-10--memoize-successful-results-including-null).

## Question 6 — Inject Selection, Transformation, and an Action

**Task:** Implement a generic `process()` method accepting a predicate, mapper, and consumer. From `[Ada, Bob, Grace]`, select names longer than three characters, uppercase them with `Locale.ROOT`, and print them.

```java
public static <T, R> void process(
        List<T> values,
        Predicate<? super T> condition,
        Function<? super T, ? extends R> mapper,
        Consumer<? super R> action) {
    Objects.requireNonNull(values, "values");
    Objects.requireNonNull(condition, "condition");
    Objects.requireNonNull(mapper, "mapper");
    Objects.requireNonNull(action, "action");
    for (T value : values) {
        if (condition.test(value)) {
            action.accept(mapper.apply(value));
        }
    }
}
```

**Example call:**

```java
process(Arrays.asList("Ada", "Bob", "Grace"),
        name -> name.length() > 3,
        name -> name.toUpperCase(Locale.ROOT),
        System.out::println);
```

**Output:**
```text
GRACE
```

**Concept:** The method defines the processing sequence; callers supply each policy. Non-matching values never reach the mapper or action.

## Question 7 — Partially Apply a Two-Input Function

**Task:** Write `bindFirst(function, first)` returning a one-input function. Fix the first argument of multiplication to `3`, then apply the returned function to `7` and `4`.

```java
public static <A, B, R> Function<B, R> bindFirst(
        BiFunction<A, B, R> function, A first) {
    Objects.requireNonNull(function, "function");
    return second -> function.apply(first, second);
}
```

**Example calls:**

```java
BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
Function<Integer, Integer> triple = bindFirst(multiply, 3);
System.out.println(triple.apply(7));
System.out.println(triple.apply(4));
```

**Output:**
```text
21
12
```

**Concept:** Partial application fixes one argument and returns behavior accepting the remaining argument. The returned function captures both the operation and the fixed value.

## Question 8 — Build a Same-Type Operator Pipeline

**Task:** Write `pipeline(steps)` returning a unary operator that applies all steps in list order. An empty pipeline must preserve its input. Test trimming followed by uppercasing.

```java
public static <T> UnaryOperator<T> pipeline(List<UnaryOperator<T>> steps) {
    Objects.requireNonNull(steps, "steps");
    Function<T, T> combined = Function.identity();
    for (UnaryOperator<T> step : steps) {
        combined = combined.andThen(step);
    }
    return combined::apply;
}
```

**Example calls:**

```java
List<UnaryOperator<String>> steps = Arrays.asList(
        String::trim, text -> text.toUpperCase(Locale.ROOT));
System.out.println(pipeline(steps).apply(" java "));
UnaryOperator<String> empty = pipeline(Collections.emptyList());
System.out.println("[" + empty.apply(" Java ") + "]");
```

**Output:**
```text
JAVA
[ Java ]
```

**Concept:** Identity supplies the empty-pipeline behavior. Generic composition returns `Function`, so the final method reference adapts it back to `UnaryOperator`. Null stages are rejected by `andThen()` while building the pipeline.

## Question 9 — Inject a Predictable Identifier Factory

**Task:** Implement `labelFor(name, idFactory)` that requests one UUID and formats a label. Inject a fixed UUID supplier so the result is repeatable.

```java
public static String labelFor(String name, Supplier<UUID> idFactory) {
    Objects.requireNonNull(idFactory, "idFactory");
    return name + ":" + idFactory.get();
}
```

**Example calls:**

```java
UUID fixed = UUID.fromString("00000000-0000-0000-0000-000000000001");
Supplier<UUID> ids = () -> fixed;
System.out.println(labelFor("Ada", ids));
System.out.println(labelFor("Ada", ids));
```

**Output:**
```text
Ada:00000000-0000-0000-0000-000000000001
Ada:00000000-0000-0000-0000-000000000001
```

**Concept:** Injecting a dependency separates the formatting policy from ID generation. A production caller could supply `UUID::randomUUID`; tests can supply deterministic behavior.

## Question 10 — Wrap a Safe Computation with Bounded Retries

**Task:** Create `retry(source, maxAttempts)` for a repeatable computation. Retry only `TransientFailure`, propagate the final failure, and reject attempt counts below one. Simulate a source that fails twice before returning `Ready`. Show that creating the wrapper makes no calls.

```java
static class TransientFailure extends RuntimeException {
    TransientFailure(String message) { super(message); }
}

public static <T> Supplier<T> retry(Supplier<? extends T> source, int maxAttempts) {
    Objects.requireNonNull(source, "source");
    if (maxAttempts < 1) {
        throw new IllegalArgumentException("maxAttempts must be positive");
    }
    return () -> {
        int attempt = 0;
        while (true) {
            attempt++;
            try {
                return source.get();
            } catch (TransientFailure exception) {
                if (attempt >= maxAttempts) {
                    throw exception;
                }
            }
        }
    };
}
```

**Example calls:**

```java
java.util.concurrent.atomic.AtomicInteger calls =
        new java.util.concurrent.atomic.AtomicInteger();
Supplier<String> source = () -> {
    if (calls.incrementAndGet() < 3) {
        throw new TransientFailure("Not ready");
    }
    return "Ready";
};
Supplier<String> resilient = retry(source, 3);
System.out.println("Before: " + calls.get());
System.out.println(resilient.get());
System.out.println("Calls: " + calls.get());
```

**Output:**
```text
Before: 0
Ready
Calls: 3
```

**Concept:** Each wrapper invocation gets its own attempt budget. Only the chosen transient exception is retried; other failures propagate immediately. This teaching wrapper has no delay or backoff. Retry only operations safe to repeat, since failed attempts may already have produced effects.

---

## Key Takeaways

| Pattern | Purpose | Example |
|---|---|---|
| Accept behavior | Allow caller-defined strategy | `calculate()` |
| Return behavior | Capture reusable configuration | `atLeast()` |
| Factory injection | Control object creation | `createMany()` |
| Deferred fallback | Compute only when needed | `valueOrGet()` |
| Behavior adaptation | Wrap a supplier with a mapper | `mapSupplier()` |
| Policy injection | Separate selection, mapping, and actions | `process()` |
| Partial application | Fix one argument | `bindFirst()` |
| Composition | Build reusable stages | `pipeline()` |
| Dependency injection | Make external values predictable | Fixed UUID supplier |
| Retry wrapper | Apply an explicit failure policy | Bounded transient retries |

**Reminder:** Document when a wrapper runs its source, how often it runs, and how failures propagate. Wrapper order matters: memoizing a mapped supplier caches the mapped result, while mapping a memoized supplier reruns the mapper on each call. Keep resource-dependent callbacks within the resource's lifetime.

---

## Interview Questions and Answers

### Interview Question 1 — What makes a method higher-order?

**Answer:** It accepts behavior, returns behavior, or both. Java represents such behavior through functional interfaces rather than standalone function types.

### Interview Question 2 — What is partial application?

**Answer:** Fixing some arguments of an operation and returning behavior that accepts the remaining arguments. Capturing the first operand of multiplication can create a reusable multiplier.

### Interview Question 3 — How does dependency injection help test higher-order code?

**Answer:** Injecting factories, clocks, or identifier suppliers lets tests provide deterministic behavior while production callers provide real dependencies. The receiving method keeps the same policy and control flow.

### Interview Question 4 — Why does wrapper order matter?

**Answer:** Each wrapper controls which inner behavior it invokes. Memoizing a mapped supplier caches the mapped result, while mapping a memoized supplier reruns the mapper. Retrying or timing outside another wrapper also changes what is repeated or measured.

### Interview Question 5 — What should a retry wrapper specify?

**Answer:** Define retryable failures, maximum attempts, delay policy, final failure propagation, and whether the operation is safe to repeat. A failed attempt may already have caused effects, so retries do not imply exactly-once execution.


[← 158. BinaryOperator](158_BinaryOperator.md) | [Course Index](../README.md) | [160. Streams →](../11_Stream_API/160_Streams.md)
