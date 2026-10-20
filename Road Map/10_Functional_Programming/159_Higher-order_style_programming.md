[← 158. BinaryOperator](158_BinaryOperator.md) | [Course Index](../README.md) | [160. Streams →](../11_Stream_API/160_Streams.md)

---

# 159. Higher-order style programming

**Section:** Java Functional Programming

**Level:** Beginner → Advanced

**Version:** Examples use Java 8-compatible APIs.

## 1. What does higher-order mean?

A higher-order method accepts behavior as an argument, returns behavior as a result, or does both. In Java, that behavior is represented by a functional interface and supplied using a lambda, a method reference, or an implementing class.

```java
static int calculate(int a, int b, IntBinaryOperator operation) {
    return operation.applyAsInt(a, b);
}

// Inside main:
System.out.println(calculate(10, 5, (a, b) -> a + b)); // 15
System.out.println(calculate(10, 5, (a, b) -> a * b)); // 50
```

The method keeps the calculation structure while callers choose the operation. This separates a reusable procedure from the rules that vary.

Java remains statically typed and supports object-oriented and functional styles together. Higher-order code does not require recursion, streams, or a separate functional language.

## 2. Choose the correct behavior contract

| Interface | Shape | Typical higher-order use |
| --- | --- | --- |
| `Supplier<T>` | `() → T` | Defer work or inject a factory |
| `Function<T, R>` | `T → R` | Inject a transformation |
| `Predicate<T>` | `T → boolean` | Inject a selection rule |
| `Consumer<T>` | `T → void` | Inject an action or callback |
| `UnaryOperator<T>` | `T → T` | Inject a same-type update |
| `BinaryOperator<T>` | `(T, T) → T` | Inject a combining rule |
| `BiFunction<T, U, R>` | `(T, U) → R` | Inject a two-input calculation |

Types are in `java.util.function`. Use primitive variants where avoiding boxing is appropriate.

Unless a snippet includes imports, assume imports for the types used and place helper methods at class scope and example calls inside a method. All snippets are independent; repeated variable names are intentional.

## 3. Supplier basics: passing work instead of a value

A Supplier has no input parameters and returns a result through `get`:

```java
Supplier<String> greeting = () -> "Hello, Java!";
Supplier<List<String>> listFactory = ArrayList::new;
Supplier<UUID> idFactory = UUID::randomUUID;

System.out.println(greeting.get()); // Hello, Java!
List<String> names = listFactory.get();
UUID id = idFactory.get();
```

A supplier is particularly useful in higher-order methods because the method can decide **whether**, **when**, and **how often** to request a value. It does not inherently cache, create threads, guarantee new objects, or prevent null results.

For a dedicated reference covering primitive suppliers, Optional, resources, logging, and memoization, see [156. Supplier](156_Supplier.md).

## 4. Accepting a Supplier as a factory

```java
static <T> T create(Supplier<? extends T> factory) {
    return Objects.requireNonNull(factory, "factory").get();
}

// Inside main:
List<String> names = create(ArrayList<String>::new);
names.add("Ada");
System.out.println(names); // [Ada]
```

`? extends T` allows a factory returning a subtype of the requested type. The helper calls the supplier once per invocation, but the supplier determines whether the returned object is fresh or shared.

### Creating several independent values

```java
static <T> List<T> createMany(int count, Supplier<? extends T> factory) {
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

// Inside main:
List<StringBuilder> builders = createMany(2, StringBuilder::new);
builders.get(0).append("Java");
System.out.println(builders.get(0)); // Java
System.out.println(builders.get(1).length()); // 0
```

Independence comes from this constructor factory, not from Supplier itself. A supplier returning one captured builder would produce repeated references to the same object.

## 5. Returning behavior: configurable closures

```java
static IntUnaryOperator multiplier(int factor) {
    return value -> value * factor;
}

static Predicate<Integer> atLeast(int minimum) {
    return value -> value >= minimum;
}

static Supplier<String> greetingFor(String name) {
    return () -> "Hello, " + name;
}

// Inside main:
System.out.println(multiplier(3).applyAsInt(7)); // 21
System.out.println(atLeast(18).test(20));       // true
System.out.println(greetingFor("Ada").get());   // Hello, Ada
```

Returned lambdas retain access to captured values after the factory method returns. Captured local variables and enclosing parameters must be final or effectively final. Captured objects may still be mutable and can be kept alive by the returned behavior.

The multiplication example assumes no integer overflow; use an explicit overflow policy where required.

## 6. Deferred fallback computation with Supplier

```java
static <T> T valueOrGet(T value, Supplier<? extends T> fallback) {
    Objects.requireNonNull(fallback, "fallback");
    return value != null ? value : fallback.get();
}

static String buildDefault() {
    System.out.println("Building default");
    return "Guest";
}

// Inside main:
System.out.println(valueOrGet("Ada", Example::buildDefault)); // Ada
System.out.println(valueOrGet(null, Example::buildDefault));
// Building default
// Guest
```

Replace `Example` with the enclosing class name. The fallback body runs only when the value is null. The helper still validates the fallback reference on every call. It deliberately permits a null fallback result; change that policy if callers require a non-null result.

Creating a supplier around an already calculated value does not defer the calculation:

```java
String calculated = buildDefault(); // Runs now
Supplier<String> retrieval = () -> calculated; // Only retrieval is deferred
Supplier<String> computation = () -> buildDefault(); // Work is deferred
```

The standard `Optional.orElseGet` follows the conditional-fallback pattern. `Optional.orElse(buildDefault())` evaluates the argument before the Optional method runs.

## 7. Adapting a Supplier with a Function

A higher-order wrapper can accept behavior and return new behavior:

```java
static <T, R> Supplier<R> mapSupplier(
        Supplier<? extends T> source, Function<? super T, ? extends R> mapper) {
    Objects.requireNonNull(source, "source");
    Objects.requireNonNull(mapper, "mapper");
    return () -> mapper.apply(source.get());
}

// Inside main:
Supplier<String> text = () -> "Java";
Supplier<Integer> length = mapSupplier(text, String::length);
System.out.println(length.get()); // 4
```

Each `length.get()` invokes the source and mapper again. This helper is lazy adaptation, not memoization. Supplier has no built-in `andThen`; the helper explicitly implements that idea.

## 8. Decorating a Supplier with timing

```java
static <T> Supplier<T> timed(Supplier<T> source, Consumer<Long> reportNanos) {
    Objects.requireNonNull(source, "source");
    Objects.requireNonNull(reportNanos, "reportNanos");
    return () -> {
        long start = System.nanoTime();
        try {
            return source.get();
        } finally {
            reportNanos.accept(System.nanoTime() - start);
        }
    };
}

// Inside main:
Supplier<String> work = timed(() -> "Done", nanos ->
        System.out.println("Elapsed ns: " + nanos));
System.out.println(work.get()); // Elapsed message, then Done
```

The timing action runs on success or failure. The measured duration varies. `System.nanoTime` measures elapsed time, not calendar time.

Require the reporting action to be non-throwing for this simple wrapper: a reporting exception in `finally` can replace a source exception or prevent returning the source result. Wrapper failure policies are part of their contract.

## 9. Memoization as a higher-order wrapper

```java
static <T> Supplier<T> memoize(Supplier<? extends T> source) {
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

This wrapper caches the first successfully returned value, including null. It retries after a source exception. Synchronization protects initialization and publication, but not later mutation of the cached object. There is no expiration or invalidation; the source and result remain retained.

Use only for non-recursive initialization. A source that calls back into the same memoized supplier is unsupported. Do not memoize consumable streams or a factory that must provide independent mutable objects.

```java
Supplier<List<String>> cached = memoize(ArrayList<String>::new);
System.out.println(cached.get() == cached.get()); // true
```

## 10. Retry wrappers: restrict them to safe operations

```java
static <T> Supplier<T> retry(
        Supplier<T> operation, int attempts, Predicate<RuntimeException> retryable) {
    Objects.requireNonNull(operation, "operation");
    Objects.requireNonNull(retryable, "retryable");
    if (attempts < 1) {
        throw new IllegalArgumentException("attempts must be positive");
    }
    return () -> {
        for (int remaining = attempts; ; remaining--) {
            try {
                return operation.get();
            } catch (RuntimeException failure) {
                if (remaining == 1 || !retryable.test(failure)) {
                    throw failure;
                }
            }
        }
    };
}
```

This educational helper retries only RuntimeExceptions explicitly accepted by the policy, up to a fixed total attempt count. It has no delay, backoff, deadline, cancellation, or special interruption handling; do not use it unchanged as a general network resilience solution.

Retry only operations whose repeated execution is safe. A failure can occur after an external effect has already happened, so retrying a payment, email, or insert can duplicate effects. Never treat all exceptions as transient by default.

Wrapper order also matters: timing each attempt differs from timing the entire retry operation. Caching a successful result changes which later calls need retries at all.

## 11. Selection, transformation, and actions as parameters

```java
static <T, R> List<R> selectAndMap(
        List<T> values,
        Predicate<? super T> rule,
        Function<? super T, ? extends R> mapper) {
    Objects.requireNonNull(values, "values");
    Objects.requireNonNull(rule, "rule");
    Objects.requireNonNull(mapper, "mapper");
    List<R> result = new ArrayList<>();
    for (T value : values) {
        if (rule.test(value)) {
            result.add(mapper.apply(value));
        }
    }
    return result;
}

// Inside main:
List<Integer> lengths = selectAndMap(
        Arrays.asList("Ada", "Grace", "Alan"),
        name -> name.length() >= 4,
        String::length);
System.out.println(lengths); // [5, 4]
```

The helper owns iteration; callers own the rule and mapping. Input-element null behavior belongs to those functions. For an action-only variant, accept a Consumer rather than constructing a result list.

Equivalent stream operations are also higher-order API calls:

```java
List<Integer> lengths = names.stream()
        .filter(name -> name.length() >= 4)
        .map(String::length)
        .collect(Collectors.toList());
```

## 12. Composition and partial application

```java
Function<String, String> trim = String::trim;
Function<String, Integer> parse = Integer::parseInt;
Function<Integer, Integer> doubleIt = n -> n * 2;
Function<String, Integer> pipeline = trim.andThen(parse).andThen(doubleIt);
System.out.println(pipeline.apply(" 21 ")); // 42
```

`f.andThen(g)` runs f before g; `f.compose(g)` runs g before f. Composition creates behavior without invoking it. Exceptions stop the pipeline unless handled explicitly.

Fixing one argument of a two-input operation is called **partial application**:

```java
BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
Function<Integer, Integer> triple = value -> multiply.apply(3, value);
System.out.println(triple.apply(7)); // 21
```

**Currying** represents a multi-input operation as nested one-input functions:

```java
Function<Integer, Function<Integer, Integer>> curriedAdd =
        first -> second -> first + second;
System.out.println(curriedAdd.apply(10).apply(5)); // 15
```

Use these techniques when they simplify reuse; nested functions can be harder to read than an ordinary named method.

## 13. Returning an operator pipeline

```java
static <T> UnaryOperator<T> chain(List<UnaryOperator<T>> steps) {
    Objects.requireNonNull(steps, "steps");
    List<UnaryOperator<T>> snapshot = new ArrayList<>(steps);
    snapshot.forEach(step -> Objects.requireNonNull(step, "step"));
    return input -> {
        T value = input;
        for (UnaryOperator<T> step : snapshot) {
            value = step.apply(value);
        }
        return value;
    };
}

// Inside main:
UnaryOperator<String> normalize = chain(Arrays.asList(
        String::trim,
        text -> text.toUpperCase(Locale.ROOT)));
System.out.println(normalize.apply(" java ")); // JAVA
```

The snapshot prevents later structural changes to the caller's step list from changing this pipeline. It does not copy captured state inside each step. An empty pipeline returns its input. Generic UnaryOperator.andThen returns Function; this helper deliberately returns a same-type UnaryOperator.

## 14. Dependency injection and repeatable tests

Higher-order parameters provide small dependency seams without requiring a framework:

```java
static String issueLabel(Supplier<UUID> ids) {
    return "ID:" + Objects.requireNonNull(ids.get(), "generated id");
}

// Production:
String label = issueLabel(UUID::randomUUID);
// Deterministic example:
UUID fixed = UUID.fromString("00000000-0000-0000-0000-000000000001");
System.out.println(issueLabel(() -> fixed));
// ID:00000000-0000-0000-0000-000000000001
```

The function controls how many IDs it requests; the caller supplies the source. Similar patterns inject time, factories, configuration retrieval, or external-service adapters. Avoid converting large multi-operation dependencies into unrelated callbacks when a clear interface would better express the contract.

## 15. Exceptions, resources, and execution contracts

Standard Supplier, Function, Predicate, and Consumer methods do not declare checked exceptions. Handle checked failures intentionally or define a throwing interface:

```java
@FunctionalInterface
interface ThrowingSupplier<T, E extends Exception> {
    T get() throws E;
}
```

Higher-order APIs should state:

- When and how often behavior can execute, and on which thread.
- Whether results may be null and objects are fresh or shared.
- Which failures propagate, are wrapped, retried, or cached.
- Who owns and closes resources returned by a factory.
- Whether callbacks may be retained after the method returns.

For example, CompletableFuture.supplyAsync schedules a supplier; calling get directly does not. A callback using a writer after its try-with-resources scope ends can fail. Stream mapping rules must not interfere with the source, and atomic update callbacks may execute more than once under contention.

Higher-order style does not automatically enforce pure functions, immutable data, transactions, or thread safety.

## 16. Complete runnable example

Save as `HigherOrderDemo.java`. This program works on Java 8+.

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class HigherOrderDemo {
    static IntUnaryOperator multiplier(int factor) {
        return value -> value * factor;
    }

    static <T, R> List<R> selectAndMap(
            List<T> values, Predicate<? super T> rule,
            Function<? super T, ? extends R> mapper) {
        Objects.requireNonNull(values, "values");
        Objects.requireNonNull(rule, "rule");
        Objects.requireNonNull(mapper, "mapper");
        List<R> result = new ArrayList<>();
        for (T value : values) {
            if (rule.test(value)) {
                result.add(mapper.apply(value));
            }
        }
        return result;
    }

    static <T, R> Supplier<R> mapSupplier(
            Supplier<? extends T> source, Function<? super T, ? extends R> mapper) {
        Objects.requireNonNull(source, "source");
        Objects.requireNonNull(mapper, "mapper");
        return () -> mapper.apply(source.get());
    }

    static <T> T valueOrGet(T value, Supplier<? extends T> fallback) {
        Objects.requireNonNull(fallback, "fallback");
        return value != null ? value : fallback.get();
    }

    static String defaultName() {
        System.out.println("Building fallback");
        return "Guest";
    }

    public static void main(String[] args) {
        Function<String, String> trim = String::trim;
        Function<String, String> upper = text -> text.toUpperCase(Locale.ROOT);
        Function<String, String> normalize = trim.andThen(upper);
        Predicate<String> nonBlank = text -> text != null && !text.trim().isEmpty();

        List<String> names = Arrays.asList(" ada ", null, " ", " grace ");
        System.out.println(selectAndMap(names, nonBlank, normalize));
        System.out.println(multiplier(3).applyAsInt(7));

        Supplier<List<String>> factory = ArrayList::new;
        List<String> first = factory.get();
        List<String> second = factory.get();
        first.add("Ada");
        System.out.println(second.isEmpty());

        Supplier<Integer> length = mapSupplier(() -> "Java", String::length);
        System.out.println(length.get());

        System.out.println(valueOrGet("Ada", HigherOrderDemo::defaultName));
        System.out.println(valueOrGet(null, HigherOrderDemo::defaultName));

        Function<Integer, Function<Integer, Integer>> add = a -> b -> a + b;
        System.out.println(add.apply(10).apply(5));
    }
}
```

Compile and run:

```shell
javac HigherOrderDemo.java
java HigherOrderDemo
```

Expected output:

```text
[ADA, GRACE]
21
true
4
Ada
Building fallback
Guest
15
```

## 17. Common mistakes

| Mistake | Better approach |
| --- | --- |
| Passing a computed value when behavior is required | Wrap the computation in a suitable lambda |
| Assuming Supplier means fresh or cached | Document the factory and caching policy |
| Capturing changing shared state unintentionally | Prefer explicit inputs or intentional snapshots |
| Retrying operations with duplicate external effects | Require safe repeatability and a precise failure policy |
| Making wrapper order accidental | Trace calls, caching, timing, and exceptions |
| Swallowing checked exceptions | Handle, wrap, or declare them through a suitable interface |
| Retaining callbacks with closed resources | Define resource ownership and callback lifetime |
| Creating deeply nested lambdas | Extract named methods and meaningful interfaces |

## 18. Practice exercises

1. Accept an IntBinaryOperator and demonstrate addition and multiplication.
2. Return a predicate for a configurable minimum age.
3. Implement createMany with a supplier returning independent lists.
4. Demonstrate conditional fallback with a supplier that records invocation count.
5. Implement mapSupplier and show that it is not automatically cached.
6. Wrap a supplier with timing and explain the reporter's failure policy.
7. Demonstrate memoization of null and retry after a failed initialization.
8. Compose trimming, parsing, and doubling into one transformation.
9. Partially apply a two-input multiplication function.
10. Build a same-type pipeline whose empty step list preserves the input.
11. Inject a fixed UUID supplier into a label generator.
12. Explain how retry, timing, and memoization behave in different wrapper orders.

**Hints:** 3. constructor reference; 4. test both present and missing values; 5. count source calls; 7. separate initialized flag; 9. capture one fixed argument; 12. write down which wrapper invokes which source before reasoning about outcomes.

## Completion checklist

- [ ] I can identify methods that accept or return behavior.
- [ ] I can choose an appropriate functional interface.
- [ ] I understand Supplier factories, deferral, adaptation, and memoization.
- [ ] I can compose functions and explain partial application and currying.
- [ ] I can define execution, failure, resource, and concurrency contracts.
- [ ] I can use injected behavior for repeatable examples and tests.
- [ ] I can explain the complete runnable program.

## Official references and related notes

- [java.util.function API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/package-summary.html) — functional-interface shapes.
- [Supplier API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/Supplier.html) — value-provider contract.
- [Function API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/Function.html) — composition.
- [156. Supplier](156_Supplier.md) — dedicated supplier examples.
- [154. Function](154_Function.md) — transformation patterns.
- [157. UnaryOperator](157_UnaryOperator.md) — same-type pipelines.

---

[← 158. BinaryOperator](158_BinaryOperator.md) | [Course Index](../README.md) | [160. Streams →](../11_Stream_API/160_Streams.md)
