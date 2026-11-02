[← 155. Consumer](155_Consumer.md) | [Course Index](../README.md) | [157. UnaryOperator →](157_UnaryOperator.md)

# Java Supplier Practice — 10 Coding Questions and Answers

[← Road Map lesson](../../Road%20Map/10_Functional_Programming/156_Supplier.md)

These exercises follow [156. Supplier](../../Road%20Map/10_Functional_Programming/156_Supplier.md), progressing from basic factories to deferred computation and memoization. Each question includes a model solution and a short explanation.

**Java version:** Java 8+. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports. Run questions independently. Put helpers inside `Main` and example statements inside `main()`. Suppliers and mappers are non-null unless a task states otherwise.

## Question 1 — Supply a Constant

**Task:** Create a `Supplier<String>` returning `Hello, Java!` and invoke it twice.

```java
Supplier<String> greeting = () -> "Hello, Java!";
System.out.println(greeting.get());
System.out.println(greeting.get());
```

**Output:**
```text
Hello, Java!
Hello, Java!
```

**Concept:** `Supplier<T>` has no input and returns a value through `get()`. The interface does not guarantee different values across invocations.

## Question 2 — Fresh Factory Versus Shared Object

**Task:** Supply two independent lists through a constructor reference. Then contrast that with a supplier returning one captured list.

```java
Supplier<List<String>> factory = ArrayList<String>::new;
List<String> first = factory.get();
List<String> second = factory.get();
first.add("Ada");
System.out.println(first);
System.out.println(second);
System.out.println(first == second);

List<String> shared = new ArrayList<>();
Supplier<List<String>> sharedFactory = () -> shared;
sharedFactory.get().add("Grace");
System.out.println(sharedFactory.get());
System.out.println(sharedFactory.get() == sharedFactory.get());
```

**Output:**
```text
[Ada]
[]
false
[Grace]
true
```

**Concept:** Freshness depends on the supplier body. A constructor factory creates objects; returning a captured reference shares the same object.

## Question 3 — Observe Deferred Execution

**Task:** Create a supplier that prints `Computing` before returning `42`. Print `Created` after declaring it, then invoke it twice.

```java
Supplier<Integer> answer = () -> {
    System.out.println("Computing");
    return 42;
};
System.out.println("Created");
System.out.println(answer.get());
System.out.println(answer.get());
```

**Output:**
```text
Created
Computing
42
Computing
42
```

**Concept:** Work inside the supplier body is deferred until `get()`. Deferral is separate from caching: each call here repeats the computation.

## Question 4 — Compare `orElse()` and `orElseGet()`

**Task:** Define a fallback method that prints when called. Compare eager and lazy fallbacks on a present optional, then test a lazy fallback on an empty optional.

```java
public static String buildDefault() {
    System.out.println("Building default");
    return "Guest";
}
```

**Example calls:**

```java
Optional<String> present = Optional.of("Ada");
System.out.println(present.orElse(buildDefault()));
System.out.println(present.orElseGet(Main::buildDefault));
System.out.println(Optional.<String>empty().orElseGet(Main::buildDefault));
```

**Output:**
```text
Building default
Ada
Ada
Building default
Guest
```

**Concept:** Java evaluates the argument to `orElse()` before calling it. `orElseGet()` invokes its supplier only when empty. Replace `Main` if your enclosing class has another name.

## Question 5 — Supply an Exception

**Task:** Use an exception supplier with `orElseThrow()` on an empty optional. Catch the exception and print its message.

```java
Optional<String> name = Optional.empty();
try {
    name.orElseThrow(() -> new IllegalStateException("Name is required"));
} catch (IllegalStateException exception) {
    System.out.println(exception.getMessage());
}
```

**Output:**
```text
Name is required
```

**Concept:** The supplier creates the exception only for the empty case. The supplier-taking `orElseThrow()` overload is available in Java 8.

## Question 6 — Generate a Bounded Stream

**Task:** Generate five copies of `Java` using `Stream.generate()`, and collect them into a list.

```java
Supplier<String> word = () -> "Java";
List<String> words = Stream.generate(word)
        .limit(5)
        .collect(Collectors.toList());
System.out.println(words);
```

**Output:**
```text
[Java, Java, Java, Java, Java]
```

**Concept:** `Stream.generate()` creates an unbounded stream. A bound such as `limit()` makes this collection terminate. Generating values does not imply caching or fresh object creation.

## Question 7 — Primitive Suppliers and Adaptation

**Task:** Create an `IntSupplier` returning `42` and a `BooleanSupplier` returning `true`. Adapt the integer supplier to `Supplier<Integer>` with a method reference.

```java
IntSupplier answer = () -> 42;
BooleanSupplier enabled = () -> true;
Supplier<Integer> boxed = answer::getAsInt;
System.out.println(answer.getAsInt());
System.out.println(enabled.getAsBoolean());
System.out.println(boxed.get());
```

**Output:**
```text
42
true
42
```

**Concept:** Primitive suppliers use methods such as `getAsInt()` and `getAsBoolean()`. They are separate interfaces from boxed suppliers, so adaptation must be explicit.

## Question 8 — Inject a Fixed Clock

**Task:** Create a date supplier using a fixed clock at midnight UTC on 1 January 2024. Invoke it twice to obtain predictable results.

```java
java.time.Clock clock = java.time.Clock.fixed(
        java.time.Instant.parse("2024-01-01T00:00:00Z"),
        java.time.ZoneOffset.UTC);
Supplier<java.time.LocalDate> today = () -> java.time.LocalDate.now(clock);
System.out.println(today.get());
System.out.println(today.get());
```

**Output:**
```text
2024-01-01
2024-01-01
```

**Concept:** Injecting a clock makes time-dependent behavior repeatable. Both the instant and zone are explicit; a supplier need not read the real system clock.

## Question 9 — Map a Supplier Without Caching

**Task:** Implement `mapSupplier()`. Wrap a source that returns an increasing call number, map it to a label, and demonstrate that constructing the wrapper does not invoke the source.

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
Supplier<Integer> source = calls::incrementAndGet;
Supplier<String> label = mapSupplier(source, number -> "Call " + number);
System.out.println(calls.get());
System.out.println(label.get());
System.out.println(label.get());
```

**Output:**
```text
0
Call 1
Call 2
```

**Concept:** Each wrapper invocation runs the source and then the mapper. `Supplier` has no built-in `map()` method; this helper adds that behavior explicitly.

## Question 10 — Memoize Successful Results, Including Null

**Task:** Implement a synchronized memoizing supplier that caches the first successful result, including null. Failed invocations must remain retryable. Test a source that fails once and then returns null.

```java
public static <T> Supplier<T> memoize(Supplier<? extends T> source) {
    Objects.requireNonNull(source, "source");
    return new Supplier<T>() {
        private boolean initialized;
        private T value;

        @Override
        public synchronized T get() {
            if (!initialized) {
                value = source.get();
                initialized = true;
            }
            return value;
        }
    };
}
```

**Example calls:**

```java
java.util.concurrent.atomic.AtomicInteger calls =
        new java.util.concurrent.atomic.AtomicInteger();
Supplier<String> cached = memoize(() -> {
    if (calls.incrementAndGet() == 1) {
        throw new IllegalStateException("Try again");
    }
    return null;
});
try {
    cached.get();
} catch (IllegalStateException exception) {
    System.out.println(exception.getMessage());
}
System.out.println(cached.get());
System.out.println(cached.get());
System.out.println("Calls: " + calls.get());
```

**Output:**
```text
Try again
null
null
Calls: 2
```

**Concept:** A separate flag distinguishes cached null from uninitialized state. A throwing source never sets the flag. This helper has no expiration and assumes non-recursive initialization; synchronization does not make a cached mutable object thread-safe.

---

## Key Takeaways

| API or pattern | Purpose | Example |
|---|---|---|
| `Supplier<T>` | Produce a value without input | `get()` |
| Constructor supplier | Create a new object | `ArrayList<String>::new` |
| `orElseGet()` | Compute a fallback only when empty | `Main::buildDefault` |
| `orElseThrow()` | Create an exception when empty | Exception supplier |
| `Stream.generate()` | Generate values from a supplier | Use `limit()` to bound output |
| `IntSupplier` | Supply primitive `int` | `getAsInt()` |
| `mapSupplier()` | Transform a supplied value | Source runs on every call |
| Memoization | Cache a successful result explicitly | Separate initialized flag |

**Reminder:** Suppliers do not inherently cache, schedule asynchronous execution, or guarantee fresh objects. Document invocation, null, failure, and ownership policies when exposing a supplier.

---

## Interview Questions and Answers

### Interview Question 1 — Does Supplier guarantee fresh objects or cached values?

**Answer:** Neither. Its body defines whether it creates a new value, returns a shared object, reads changing state, or returns a constant. Memoization is a separate policy.

### Interview Question 2 — What is the difference between orElse and orElseGet?

**Answer:** The argument expression for orElse is evaluated before the method call. orElseGet receives behavior and invokes it only when the optional is empty.

### Interview Question 3 — How can a memoizer cache null?

**Answer:** Track initialization with a separate boolean rather than treating null as uninitialized. Set that flag only after the source returns successfully if failed attempts should remain retryable.

### Interview Question 4 — Does a supplier run asynchronously?

**Answer:** Calling get is an ordinary synchronous call. An API such as CompletableFuture.supplyAsync explicitly schedules the supplier and defines how completion and failure are reported.

### Interview Question 5 — Why must collection factories provide fresh containers?

**Answer:** Mutable collection operations may create multiple partial containers, particularly in parallel collection. Returning the same shared container breaks the independence needed by that collection design.


[← 155. Consumer](155_Consumer.md) | [Course Index](../README.md) | [157. UnaryOperator →](157_UnaryOperator.md)
