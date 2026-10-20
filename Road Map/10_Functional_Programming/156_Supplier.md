[← 155. Consumer](155_Consumer.md) | [Course Index](../README.md) | [157. UnaryOperator →](157_UnaryOperator.md)

---

# 156. Supplier

**Section:** Java Functional Programming

**Level:** Beginner → Advanced

**Version:** Examples use Java 8+ unless marked otherwise.

## 1. What is a Supplier?

`Supplier<T>` represents an operation that takes no arguments and supplies a result of type `T`. Its abstract method is `T get()`.

```java
import java.util.function.Supplier;

// Inside a method:
Supplier<String> greeting = () -> "Hello, Java!";
System.out.println(greeting.get()); // Hello, Java!
```

Use suppliers for object factories, deferred computations, fallback values, and APIs that request values when needed. Creating a lambda does not run its body; the caller decides when and how often to invoke `get()`.

A supplier does **not** automatically cache results, run asynchronously, return a new object, or guarantee thread safety. It can return a constant, an existing object, a newly created object, null, or a value computed from changing state.

The core declaration is:

```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
```

`Supplier` has no built-in `andThen`, `compose`, or memoization method.

## 2. Supplier versus related interfaces

| Interface | Operation | Purpose |
| --- | --- | --- |
| `Supplier<T>` | `T get()` | Produce a value without input |
| `Function<T, R>` | `R apply(T value)` | Transform an input |
| `Consumer<T>` | `void accept(T value)` | Perform an action |
| `Predicate<T>` | `boolean test(T value)` | Check an input |
| `Runnable` | `void run()` | Run a task without a result |
| `Callable<V>` | `V call() throws Exception` | Run a value-producing task permitting checked exceptions |

Suppliers can capture configuration even though `get` has no parameters. If the caller must supply a different input each time, consider `Function` instead.

## 3. Constants, computations, time, and identifiers

Unless imports are shown, snippets assume imports for the types used and belong inside a method. The runnable example later includes its own imports.

```java
Supplier<Integer> answer = () -> 42;
Supplier<Integer> calculation = () -> 6 * 7;
Supplier<String> message = () -> "Learning suppliers";
Supplier<java.time.LocalDate> today = java.time.LocalDate::now;
Supplier<java.util.UUID> identifier = java.util.UUID::randomUUID;

System.out.println(answer.get());      // 42
System.out.println(calculation.get()); // 42
System.out.println(message.get());     // Learning suppliers
System.out.println(today.get());       // Current date in the system default zone
System.out.println(identifier.get());  // A generated UUID
```

Time and random results are intentionally variable; do not assert an exact output. Use an injected `Clock` when time-dependent code needs reproducible tests:

```java
java.time.Clock clock = java.time.Clock.fixed(
        java.time.Instant.parse("2026-01-01T00:00:00Z"), java.time.ZoneOffset.UTC);
Supplier<java.time.LocalDate> date = () -> java.time.LocalDate.now(clock);
System.out.println(date.get()); // 2026-01-01
```

Generic arguments use reference types, such as `Integer`, not `int`. Primitive alternatives appear in section 10.

## 4. Constructor references and object factories

```java
Supplier<List<String>> newList = ArrayList::new;
List<String> first = newList.get();
List<String> second = newList.get();
first.add("Ada");
System.out.println(first);            // [Ada]
System.out.println(second);           // []
System.out.println(first == second);  // false
```

The constructor runs on each invocation of this supplier. A supplier that returns an existing reference behaves differently:

```java
List<String> shared = new ArrayList<>();
Supplier<List<String>> existingList = () -> shared;
System.out.println(existingList.get() == existingList.get()); // true
```

Never infer independent results from the `Supplier` type alone.

### Factories with captured constructor arguments

Declare this type at class scope:

```java
static class User {
    private final String name;
    User(String name) { this.name = name; }
    String getName() { return name; }
}

// Inside a method:
String defaultName = "Guest";
Supplier<User> userFactory = () -> new User(defaultName);
System.out.println(userFactory.get().getName()); // Guest
```

`User::new` cannot target a no-input supplier here because this constructor needs an argument. A capturing lambda supplies the fixed argument. Use `Function<String, User> factory = User::new;` if the caller should choose the name each time.

### Arrays

```java
Supplier<String[]> fixedArray = () -> new String[3];
System.out.println(fixedArray.get().length); // 3
IntFunction<String[]> variableArray = String[]::new;
System.out.println(variableArray.apply(5).length); // 5
```

Array constructor references accept a length, so `String[]::new` matches `IntFunction<String[]>`, not a no-input `Supplier<String[]>`.

## 5. Deferred computation: what is actually lazy?

Put this helper in a class:

```java
static String buildDefault() {
    System.out.println("Building default");
    return "Guest";
}
```

Then compare:

```java
Supplier<String> deferred = () -> buildDefault(); // Does not call it yet
String eager = buildDefault();                   // Runs immediately
Supplier<String> alreadyComputed = () -> eager; // Delays retrieval only
System.out.println(deferred.get());              // Computes now
```

A supplier delays only the work inside its body or referenced operation. Constructing expensive captured arguments beforehand still does that work eagerly. A bound reference such as `createService()::load` calls `createService()` when the reference is created; `() -> createService().load()` calls it when invoked.

Every ordinary `deferred.get()` recomputes unless the implementation explicitly caches. Deferral and memoization are different concepts.

## 6. Optional fallbacks and exceptions

### orElse versus orElseGet

Using `buildDefault()` from above:

```java
Optional<String> present = Optional.of("Ada");
System.out.println(present.orElse(buildDefault()));
// Building default
// Ada

System.out.println(present.orElseGet(() -> buildDefault()));
// Ada: fallback body is not invoked

System.out.println(Optional.<String>empty().orElseGet(() -> buildDefault()));
// Building default
// Guest
```

Java evaluates ordinary method arguments before the call, which explains the first result. `orElseGet` calls its supplier only when empty. A cheap constant fallback does not necessarily need a supplier.

### Supply an exception

```java
String name = Optional.of("Ada").orElseThrow(
        () -> new IllegalArgumentException("Name is required"));
System.out.println(name); // Ada

// An empty Optional would invoke the factory and throw the supplied exception.
```

A checked exception supplied to `orElseThrow` must be handled or declared by the caller. The supplier creates the exception object; `orElseThrow` throws it.

### Optional.or (Java 9+)

```java
Optional<String> primary = Optional.empty();
Optional<String> result = primary.or(() -> Optional.of("Fallback"));
System.out.println(result.get()); // Fallback
```

This supplier returns another `Optional`, while `orElseGet` returns the contained value. The fallback Optional must not be null. A plain supplier may return null; API-specific contracts decide whether that result is allowed.

## 7. Lazy messages and logging

```java
java.util.logging.Logger logger = java.util.logging.Logger.getLogger("Example");
Supplier<String> diagnostic = () -> "Items processed: " + expensiveCount();
logger.fine(diagnostic);
```

Here `expensiveCount()` represents an application method. The logging API requests the message only if the relevant level is enabled. Capturing a precomputed expensive string defeats this benefit.

Java also supports lazy validation messages:

```java
String value = "Java";
String checked = Objects.requireNonNull(value, () -> "Missing required value");
System.out.println(checked); // Java
```

The message supplier is used on the null path. Keep diagnostic suppliers free of required business effects because they may never execute. Different logging libraries have different APIs; check the library you are using.

## 8. Streams: generate values and create containers

### Stream.generate

```java
List<String> greetings = Stream.generate(() -> "Hello")
        .limit(3)
        .collect(Collectors.toList());
System.out.println(greetings); // [Hello, Hello, Hello]
```

`generate` creates a potentially infinite, sequential, unordered stream. Bound it with an appropriate operation before collecting. Processing is lazy until a terminal operation requests values.

```java
List<UUID> ids = Stream.generate(UUID::randomUUID)
        .limit(3)
        .collect(Collectors.toList());
```

Do not depend on an exact supplier invocation count across all pipelines, particularly with parallel execution or short-circuiting. A stateful counter supplier is not automatically safe for parallel use. Prefer `IntStream.range` or `iterate` when expressing a numeric sequence.

### Fresh accumulation containers

```java
List<String> names = Arrays.asList("Ada", "Grace");
List<String> copy = names.stream().collect(
        ArrayList::new,
        List::add,
        List::addAll);
System.out.println(copy); // [Ada, Grace]

Set<String> unique = names.stream().collect(Collectors.toCollection(LinkedHashSet::new));
```

Collection suppliers must create fresh containers as required by the collection operation. Returning one shared mutable list breaks that contract, especially with parallel collection. Prefer `Collectors.toList()` when no custom list implementation is needed.

## 9. Thread-local factories and asynchronous execution

### One initialization per thread, not per get

```java
ThreadLocal<List<String>> local = ThreadLocal.withInitial(ArrayList::new);
try {
    local.get().add("Ada");
    System.out.println(local.get()); // [Ada] in this thread
} finally {
    local.remove();
}
```

The initial-value supplier runs when a thread first calls `get` without an existing value. Repeated calls on that thread return its stored value. After `remove`, a later `get` can initialize again. Thread-local initialization is API behavior, not a general property of suppliers.

### CompletableFuture.supplyAsync

```java
java.util.concurrent.CompletableFuture<String> future =
        java.util.concurrent.CompletableFuture.supplyAsync(() -> "Computed");
System.out.println(future.join()); // Computed
```

This API schedules the supplier asynchronously, using the default asynchronous execution facility unless an executor is supplied. The supplier interface itself remains synchronous when you call `get` directly. Failures complete the future exceptionally; `join` reports them through `CompletionException`. Shared captured state still needs appropriate synchronization.

## 10. Primitive suppliers

| Interface | Result | Invocation |
| --- | --- | --- |
| `IntSupplier` | `int` | `getAsInt()` |
| `LongSupplier` | `long` | `getAsLong()` |
| `DoubleSupplier` | `double` | `getAsDouble()` |
| `BooleanSupplier` | `boolean` | `getAsBoolean()` |

```java
IntSupplier answer = () -> 42;
LongSupplier time = System::nanoTime;
DoubleSupplier random = Math::random;
BooleanSupplier enabled = () -> true;

System.out.println(answer.getAsInt());    // 42
System.out.println(enabled.getAsBoolean()); // true
```

`System.nanoTime` is intended for elapsed-time measurement, not a wall-clock timestamp. `Math.random` supplies a value from 0.0 inclusive to 1.0 exclusive.

Primitive variants avoid boxed return values. They are separate interfaces, not subtypes of `Supplier<Integer>` and similar types:

```java
Supplier<Integer> boxed = answer::getAsInt;
IntSupplier unboxed = boxed::get; // A null boxed result would fail on unboxing
```

`IntStream.generate`, `LongStream.generate`, and `DoubleStream.generate` accept the corresponding primitive suppliers.

## 11. Passing, returning, and adapting suppliers

Place these helpers inside a class:

```java
static <T> T create(Supplier<? extends T> factory) {
    return Objects.requireNonNull(factory, "factory").get();
}

static Supplier<String> greetingFor(String name) {
    return () -> "Hello, " + name;
}

static <T, R> Supplier<R> mapSupplier(
        Supplier<? extends T> source, Function<? super T, ? extends R> mapper) {
    Objects.requireNonNull(source, "source");
    Objects.requireNonNull(mapper, "mapper");
    return () -> mapper.apply(source.get());
}

// Inside main:
List<String> names = create(ArrayList<String>::new);
Supplier<Integer> length = mapSupplier(greetingFor("Ada"), String::length);
System.out.println(length.get()); // 10: Hello, Ada
```

`? extends T` lets a factory return a subtype of the requested result. The captured `name` is effectively final. `mapSupplier` explicitly adds a transformation because Supplier itself has no composition method; each invocation runs the source and mapper again.

## 12. Memoization: calculate once and reuse

Memoization is an explicit policy. This educational helper serializes access and caches the first successfully returned result, including null:

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

// Inside main:
Supplier<List<String>> cached = memoize(ArrayList<String>::new);
System.out.println(cached.get() == cached.get()); // true
```

Important design choices in this example:

- The flag distinguishes an uninitialized cache from a cached null.
- If the source throws, initialization stays incomplete; a later call retries.
- Synchronization protects initialization and visibility, but does not make a returned mutable object thread-safe.
- No expiration or invalidation is provided, and the cached value and source remain retained.
- The source must not recursively request the same memoized value. This helper is for non-recursive initialization, not a general cache library.

Only cache results when reusing them fits the domain. A memoized list factory no longer provides independent lists. Consider established cache tools for expiration, eviction, cancellation, or more complex concurrency requirements.

## 13. Exceptions, nulls, and resources

`Supplier.get` declares no checked exceptions. Use an appropriate throwing interface or `Callable`, or handle checked failures explicitly.

```java
@FunctionalInterface
interface ThrowingSupplier<T, E extends Exception> {
    T get() throws E;
}

// Inside a method, with java.io and java.nio.file imports:
ThrowingSupplier<byte[], IOException> bytes =
        () -> Files.readAllBytes(Paths.get("note.txt"));
// bytes.get() requires handling or declaring IOException.
```

Wrapping an `IOException` in `UncheckedIOException` can be appropriate when a non-throwing API is required; do not silently swallow failures. Runtime exceptions propagate from ordinary supplier invocation.

A supplier that opens a resource must have a clear ownership contract:

```java
Supplier<Stream<String>> openLines = () -> {
    try {
        return java.nio.file.Files.lines(java.nio.file.Paths.get("note.txt"));
    } catch (java.io.IOException exception) {
        throw new java.io.UncheckedIOException(exception);
    }
};
try (Stream<String> lines = openLines.get()) {
    lines.forEach(System.out::println);
}
```

Each invocation opens a new stream, and the caller closes it. Do not memoize a consumable stream: it can be traversed only once and may already be closed. Resource failures can also occur during traversal.

## 14. Capture, changing state, and testability

Captured local references must be final or effectively final, but referenced objects can change:

```java
List<String> names = new ArrayList<>();
Supplier<Integer> size = names::size;
System.out.println(size.get()); // 0
names.add("Ada");
System.out.println(size.get()); // 1
```

The supplier observes current state; it did not snapshot the size. It is not automatically safe when other threads mutate the list.

Inject suppliers to control external values during tests. For example, a method receiving `Supplier<UUID>` can use `UUID::randomUUID` in production and a fixed UUID supplier in a deterministic test. Avoid depending on global mutable state when a small injected dependency will do.

## 15. Complete runnable example

Save as `SupplierDemo.java`. This program works on Java 8+.

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SupplierDemo {
    static String fallback() {
        System.out.println("Creating fallback");
        return "Guest";
    }

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

    public static void main(String[] args) {
        Supplier<String> greeting = () -> "Hello, Java!";
        System.out.println(greeting.get());

        Supplier<List<String>> factory = ArrayList::new;
        List<String> first = factory.get();
        List<String> second = factory.get();
        first.add("Ada");
        System.out.println(first);
        System.out.println(second);
        System.out.println(first == second);

        System.out.println(Optional.of("Grace").orElseGet(SupplierDemo::fallback));
        System.out.println(Optional.<String>empty().orElseGet(SupplierDemo::fallback));

        IntSupplier answer = () -> 42;
        System.out.println(answer.getAsInt());

        List<String> repeated = Stream.generate(() -> "Java")
                .limit(3).collect(Collectors.toList());
        System.out.println(repeated);

        Supplier<List<String>> cached = memoize(() -> {
            System.out.println("Initializing cache");
            return new ArrayList<>(Arrays.asList("Saved"));
        });
        List<String> saved = cached.get();
        System.out.println(saved);
        System.out.println(saved == cached.get());
    }
}
```

Compile and run:

```shell
javac SupplierDemo.java
java SupplierDemo
```

Expected output:

```text
Hello, Java!
[Ada]
[]
false
Grace
Creating fallback
Guest
42
[Java, Java, Java]
Initializing cache
[Saved]
true
```

## 16. Common mistakes

| Mistake | Better approach |
| --- | --- |
| Calling apply or accept on Supplier | Call get |
| Expecting a new result on every call | Read the factory's documented policy |
| Assuming deferral means caching | Add memoization explicitly if needed |
| Computing a fallback before wrapping it | Put the expensive operation inside the supplier body |
| Using orElse with an expensive fallback call | Use orElseGet when conditional computation is intended |
| Collecting from an unbounded generated stream | Add an appropriate bound |
| Returning a shared container to collect | Supply fresh accumulation containers |
| Memoizing a consumable stream or open resource | Define ownership and create fresh resources as needed |
| Assuming suppliers execute asynchronously | Use an API that explicitly schedules execution |
| Ignoring shared-state concurrency | Design thread safety separately |

## 17. Practice exercises

1. Supply a constant greeting and invoke it twice.
2. Create two lists from a constructor supplier; prove mutations are independent.
3. Contrast that factory with a supplier returning one shared list.
4. Compare orElse and orElseGet using a fallback that prints when called.
5. Use orElseThrow with a custom exception message for an empty Optional.
6. Generate five constant values with Stream.generate and limit.
7. Create an IntSupplier and adapt it to Supplier<Integer>.
8. Inject a fixed Clock into a date supplier and predict the result.
9. Implement mapSupplier and explain when the source runs.
10. Use the memoization helper to count source invocations; test a null result too.
11. Make a memoized source fail once and then succeed. Explain the retry policy.
12. Explain why a shared mutable list is unsafe as a parallel collection factory.

**Hints:** 2. `ArrayList::new`; 4. test both present and empty Optionals; 6. `limit(5)`; 8. use a fixed instant and explicit zone; 10. cache null with a separate initialized flag; 11. exceptions are not cached by this helper.

## Completion checklist

- [ ] I can create and invoke a supplier using get.
- [ ] I can distinguish fresh factories, shared values, deferral, and memoization.
- [ ] I can use Optional fallbacks, primitive suppliers, and generated streams.
- [ ] I understand factory contracts for collection and thread-local APIs.
- [ ] I can explain asynchronous API behavior separately from Supplier.
- [ ] I can handle nulls, checked failures, resources, and shared state intentionally.
- [ ] I can explain the complete example and memoization policy.

## Official references

- [Supplier API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/Supplier.html) — get and result guarantees.
- [Optional API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Optional.html) — lazy fallback and exception factories.
- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html) — generation and collection.
- [ThreadLocal API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/ThreadLocal.html) — per-thread initialization.
- [CompletableFuture API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/CompletableFuture.html) — asynchronous suppliers.

---

[← 155. Consumer](155_Consumer.md) | [Course Index](../README.md) | [157. UnaryOperator →](157_UnaryOperator.md)
