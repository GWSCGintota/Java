# 151. Lambda expressions

**Section:** Java Functional Programming
**Level:** Beginner → Advanced
**Version:** Lambdas require Java 8+. Lambda parameter `var` requires Java 11+.

## Learning goals

Understand how lambdas implement functional interfaces; write, pass, return, and compose behavior; explain capture, scope, exceptions, and safe use with collections and streams.

## 1. What is a lambda expression?

A **lambda expression** is a concise implementation of a functional interface's abstract method. It lets you store behavior in a variable, pass it to a method, or return it from a method.

```java
Runnable task = () -> System.out.println("Hello, Java!");
task.run(); // Prints Hello, Java!
```

Creating a lambda does **not** execute its body. Its body runs when the interface method is invoked by your code or an API. A lambda does not automatically start a thread or make code asynchronous.

## 2. Functional interfaces: the foundation

A functional interface represents one abstract operation, often called its **SAM** (single abstract method). Default and static methods do not count. Abstract declarations matching public `Object` methods do not count either; compatible inherited declarations can represent one operation.

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

// Inside a method:
Calculator add = (a, b) -> a + b;
Calculator multiply = (a, b) -> a * b;
System.out.println(add.calculate(10, 5));      // 15
System.out.println(multiply.calculate(10, 5)); // 50
```

The interface determines parameter count, parameter types, return type, and permitted checked exceptions. Parameter names need not match the interface declaration. `@FunctionalInterface` is optional but asks the compiler to verify the interface.

A lambda cannot directly implement a class, abstract class, or interface with multiple unrelated abstract operations. A sealed interface is not a functional interface.

See [150. Functional interfaces](150_Functional_interfaces.md).

## 3. Syntax and parameters

```text
(parameters) -> expression
(parameters) -> { statements; }
```

| Form | Example |
| --- | --- |
| No parameters | `() -> 42` |
| One inferred parameter | `name -> name.length()` |
| Parenthesized inferred parameter | `(name) -> name.length()` |
| Multiple parameters | `(a, b) -> a + b` |
| Explicit types | `(int a, int b) -> a + b` |
| Block body | `(a, b) -> { int sum = a + b; return sum; }` |

Each example needs a compatible functional interface supplied by its context. Parentheses are required for zero parameters, multiple parameters, and explicitly typed parameters. Parameter styles must be consistent: `(int a, b)` is invalid.

You do not declare a method name, access modifier, return type, or `throws` clause before the arrow.

```java
// Import java.util.function types for these snippets.
IntUnaryOperator square = n -> n * n;
IntBinaryOperator add = (int a, int b) -> a + b;
Consumer<String> print = text -> System.out.println(text);
```

### Parameter var (Java 11+)

```java
IntBinaryOperator add = (var a, var b) -> a + b;
```

The target interface still determines the types. `var` is useful when adding annotations or modifiers, for example `(final var text) -> text.trim()`. Use `var` for every parameter together; do not mix it with explicit types or bare parameter names. Parentheses remain required even for one `var` parameter.

## 4. Expression bodies, block bodies, and return

An expression body supplies its value directly to a value-returning target:

```java
Function<String, Integer> length = text -> text.length();
```

A value-returning block uses explicit returns on every path that completes normally:

```java
Function<String, Integer> length = text -> {
    if (text == null) {
        return 0;
    }
    return text.length();
};
```

A path that throws need not return a value. For a `void` target, use statements and optionally a bare `return;`:

```java
Consumer<String> print = text -> {
    if (text == null) {
        return;
    }
    System.out.println(text);
};
```

An expression lambda can target `void` when its body is a valid statement expression, such as a method call. Any result is discarded.

```java
Consumer<String> ignoreLength = text -> text.length(); // Valid
// Consumer<Integer> invalid = n -> n + 1;             // Not a statement expression
// Function<String, Integer> missing = s -> { s.length(); }; // Missing return
```

`return` exits this lambda invocation, not the enclosing method. A lambda cannot use `break` or `continue` to control an enclosing loop; loops inside the lambda can use them normally.

## 5. Target typing and inference

A lambda needs a target functional interface from an assignment, method argument, return context, or cast. The compiler uses that target to infer parameter types and check the body.

```java
Predicate<String> empty = text -> text.isEmpty();
Function<String, Boolean> alsoEmpty = text -> text.isEmpty();
System.out.println(empty.test(""));      // true
System.out.println(alsoEmpty.apply("")); // true

// var task = () -> System.out.println("Hello"); // No target type
// Object task = () -> System.out.println("Hello"); // Object is not functional
Runnable task = () -> System.out.println("Hello");
Object object = (Runnable) () -> System.out.println("Hello");
```

Identical lambda syntax can implement different interfaces. Generic inference, compatible conversions, and boxing participate in type checking. If overloaded methods accepting different functional interfaces make a call ambiguous, use a typed variable or a cast to specify the intended interface.

## 6. Common functional interfaces

Most are in `java.util.function`. `Runnable` is in `java.lang`, `Comparator` in `java.util`, and `Callable` in `java.util.concurrent`.

| Interface | Abstract method | Purpose |
| --- | --- | --- |
| `Predicate<T>` | `boolean test(T t)` | Check a condition |
| `Function<T, R>` | `R apply(T t)` | Transform input into a result |
| `Consumer<T>` | `void accept(T t)` | Perform an action |
| `Supplier<T>` | `T get()` | Supply a result without input |
| `UnaryOperator<T>` | `T apply(T t)` | Transform within the same type |
| `BinaryOperator<T>` | `T apply(T a, T b)` | Combine two values of one type |
| `BiPredicate<T, U>` | `boolean test(T t, U u)` | Check two inputs |
| `BiFunction<T, U, R>` | `R apply(T t, U u)` | Transform two inputs |
| `BiConsumer<T, U>` | `void accept(T t, U u)` | Process two inputs |
| `Runnable` | `void run()` | Task without a result |
| `Callable<V>` | `V call() throws Exception` | Task with a result and checked exceptions |
| `Comparator<T>` | `int compare(T a, T b)` | Define ordering |

```java
Predicate<Integer> adult = age -> age >= 18;
Function<String, Integer> length = name -> name.length();
Consumer<String> print = name -> System.out.println(name);
Supplier<List<String>> newList = () -> new ArrayList<>();
UnaryOperator<String> trim = text -> text.trim();
BinaryOperator<Integer> add = (a, b) -> a + b;
```

Generic arguments must be reference types, such as `Integer`, not `int`.

### Primitive specializations

Interfaces such as `IntPredicate`, `IntUnaryOperator`, `IntBinaryOperator`, `IntConsumer`, `IntSupplier`, and `ToIntFunction<T>` can avoid boxing. Related interfaces support `long` and `double`.

```java
IntPredicate even = n -> n % 2 == 0;
IntUnaryOperator square = n -> n * n;
ToIntFunction<String> length = text -> text.length();
System.out.println(square.applyAsInt(5)); // 25
```

## 7. Passing and returning behavior

```java
static int calculate(int a, int b, IntBinaryOperator operation) {
    return operation.applyAsInt(a, b);
}

static IntUnaryOperator multiplier(int factor) {
    return value -> value * factor;
}

// Inside main:
int sum = calculate(8, 2, (a, b) -> a + b);     // 10
int product = calculate(8, 2, (a, b) -> a * b); // 16
IntUnaryOperator triple = multiplier(3);
System.out.println(triple.applyAsInt(7)); // 21
```

This is a higher-order programming pattern: methods accept or return behavior. The returned lambda retains access to captured `factor` after `multiplier` finishes.

## 8. Variable capture: final and effectively final

Captured local variables and enclosing parameters must be **final or effectively final**, and definitely assigned before use. Effectively final means never reassigned after initialization.

```java
int minimumAge = 18;
Predicate<Integer> eligible = age -> age >= minimumAge;
// minimumAge = 21; // Would invalidate the capture

int count = 0;
// Runnable increment = () -> count++; // Does not compile
```

The restriction applies throughout the variable's scope, both inside and outside the lambda. Capturing a reference does not make its object immutable:

```java
List<String> names = new ArrayList<>();
Consumer<String> addName = name -> names.add(name);
addName.accept("Ada"); // Changes the list, not the local reference
// names = new ArrayList<>(); // Would invalidate the capture
```

Instance and static fields are not subject to this local-variable restriction. However, shared mutation can still introduce race conditions. Lambdas are not automatically thread-safe. A lambda capturing `this` can also keep the enclosing object alive.

For stream totals and counts, prefer `sum`, `count`, `reduce`, or `collect` over mutable holders. An atomic counter makes individual updates atomic, but does not automatically make the whole algorithm correct.

## 9. Scope, this, and anonymous classes

A lambda uses the enclosing meaning of `this` and `super`; it does not introduce an anonymous-class-style `this`.

```java
class Greeting {
    private String message = "Hello";

    void demonstrate() {
        Runnable task = () -> System.out.println(this.message);
        task.run(); // this refers to the Greeting instance
    }
}
```

In a static context, a lambda cannot use `this`. Its parameters and locals cannot redeclare enclosing local variables or parameters still in scope.

```java
String name = "Ada";
// Consumer<String> print = name -> System.out.println(name); // Name conflict
Consumer<String> print = value -> System.out.println(value);
```

| Lambda | Anonymous class |
| --- | --- |
| Implements a functional operation | Can implement an interface or extend a class |
| Uses enclosing `this` | Has its own `this` |
| Concise behavior definition | Can declare fields and additional methods |
| Cannot redeclare enclosing local names | Introduces a different class scope |

```java
Runnable oldStyle = new Runnable() {
    @Override
    public void run() {
        System.out.println("Running");
    }
};
Runnable lambdaStyle = () -> System.out.println("Running");
```

These supply similar behavior, but their scope and runtime identity rules differ.

## 10. Exception handling

A checked exception may escape a lambda only if the target abstract method permits it.

```java
// Imports: java.nio.file.Files, java.nio.file.Paths,
//          java.util.concurrent.Callable
Callable<String> read = () ->
        new String(Files.readAllBytes(Paths.get("note.txt")),
                   java.nio.charset.StandardCharsets.UTF_8);
// Calling read.call() requires handling or declaring Exception.
```

`Callable.call()` declares `throws Exception`; `Supplier.get()` does not. A `Supplier<String>` cannot directly use that body without handling the checked `IOException`.

Choose an appropriate throwing interface, catch the exception inside the lambda, or wrap it meaningfully, for example with `UncheckedIOException`. Runtime exceptions can propagate normally. Do not swallow failures. An outer `try` around lambda creation does not handle exceptions thrown when its body executes later.

## 11. Collections and maps

These snippets belong inside a method and use `java.util` types:

```java
List<String> names = new ArrayList<>(Arrays.asList("Grace", "Ada", "Linus"));
names.removeIf(name -> name.length() < 4);
names.replaceAll(name -> name.toUpperCase(Locale.ROOT));
names.sort((a, b) -> a.compareTo(b));
names.forEach(name -> System.out.println(name)); // GRACE, then LINUS

Map<String, Integer> counts = new HashMap<>();
counts.merge("Java", 1, (oldCount, increment) -> oldCount + increment);
counts.computeIfAbsent("Python", key -> key.length());
```

Mutation operations require collections that support the modification. Use `Integer.compare(a, b)` instead of subtraction in numeric comparators to avoid overflow. Helpers such as `Comparator.comparingInt(String::length)` often express sorting more clearly.

## 12. Streams and execution timing

```java
List<String> names = Arrays.asList("Ada", "Grace", "Alan");
List<String> result = names.stream()
        .filter(name -> name.startsWith("A"))
        .map(name -> name.toUpperCase(Locale.ROOT))
        .collect(java.util.stream.Collectors.toList());
System.out.println(result); // [ADA, ALAN]
```

`filter` accepts a `Predicate`; `map` accepts a `Function`. Intermediate stream operations are lazy: processing starts when a terminal operation such as `collect` runs. Short-circuiting and optimizations mean you must not assume every lambda runs once for every element.

Stream behavior should generally be stateless and must not interfere with the source. Avoid adding to a shared `ArrayList` from `parallelStream().forEach(...)`; use a collector. Parallel `forEach` does not guarantee encounter order; use `forEachOrdered` when needed. Lambdas do not automatically make processing parallel or faster.

## 13. Composition

```java
Predicate<String> nonNull = text -> text != null;
Predicate<String> nonEmpty = text -> !text.isEmpty();
Predicate<String> usable = nonNull.and(nonEmpty);
System.out.println(usable.test(null)); // false: and short-circuits

Function<Integer, Integer> doubleIt = n -> n * 2;
Function<Integer, Integer> addThree = n -> n + 3;
System.out.println(doubleIt.andThen(addThree).apply(5)); // 13
System.out.println(doubleIt.compose(addThree).apply(5)); // 16
```

- `Predicate.and`, `or`, and `negate` combine conditions; `and` and `or` short-circuit.
- `f.andThen(g)` applies `f` first, then `g`.
- `f.compose(g)` applies `g` first, then `f`.
- `Consumer.andThen` sequences actions; if the first throws, the second does not run.

These are methods on interfaces, not additional lambda syntax.

## 14. Method references

When a lambda just forwards arguments to an existing method or constructor, a method reference may be clearer.

| Lambda | Method reference |
| --- | --- |
| `text -> System.out.println(text)` | `System.out::println` |
| `text -> text.length()` | `String::length` |
| `text -> Integer.parseInt(text)` | `Integer::parseInt` |
| `() -> new ArrayList<String>()` | `ArrayList<String>::new` |

Both need compatible target types. Keep a lambda when extra logic helps, such as `text -> text.trim().length()`. Continue with [152. Method references](152_Method_references.md).

## 15. Complete runnable example

Save as `LambdaDemo.java`. This example works on Java 8+.

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaDemo {
    static int calculate(int a, int b, IntBinaryOperator operation) {
        return operation.applyAsInt(a, b);
    }

    static IntUnaryOperator multiplier(int factor) {
        return value -> value * factor;
    }

    public static void main(String[] args) {
        int minimumLength = 4;
        Predicate<String> longEnough = name -> name.length() >= minimumLength;
        Function<String, String> uppercase = name -> name.toUpperCase(Locale.ROOT);

        List<String> names = new ArrayList<>(Arrays.asList("Ada", "Grace", "Linus"));
        names.sort((a, b) -> Integer.compare(a.length(), b.length()));

        List<String> selected = names.stream()
                .filter(longEnough)
                .map(uppercase)
                .collect(Collectors.toList());

        selected.forEach(name -> System.out.println(name));
        System.out.println(calculate(10, 5, (a, b) -> a + b));
        System.out.println(multiplier(3).applyAsInt(7));

        Runnable task = () -> System.out.println("Task executed");
        task.run();
    }
}
```

Compile and run:

```shell
javac LambdaDemo.java
java LambdaDemo
```

Expected output:

```text
GRACE
LINUS
15
21
Task executed
```

## 16. Advanced boundaries and runtime behavior

- A lambda evaluates to a reference implementing its target interface. Java does not promise a fresh object per evaluation. Do not depend on lambda identity with `==`, identity hash codes, or synchronization on lambda objects.
- Java compilers commonly implement lambdas through `invokedynamic`. Treat allocation, generated classes, and caching as implementation details rather than assuming an anonymous class is created each time.
- An intersection cast can add a marker interface: `(Runnable & java.io.Serializable) () -> System.out.println("Hello")`. Captured state must also support serialization. Serialized lambdas are fragile across code changes; avoid using them as durable data.
- A lambda cannot implement a functional descriptor whose abstract operation is itself generic, such as `<T> T identity(T value)`. Use a class or anonymous class. Ordinary generic interfaces such as `Function<String, String>` work normally.
- A local lambda variable cannot reference itself before initialization. Prefer a named method for recursion.

## 17. Common mistakes and good habits

| Mistake | Better approach |
| --- | --- |
| Expecting execution on assignment | Invoke the interface method or use an API that does |
| Using `var` for a bare lambda variable | Declare a functional interface target |
| Missing a return in a value block | Return a compatible value on each normal path |
| Reassigning a captured local | Keep it effectively final or redesign the operation |
| Assuming captured objects are immutable | Consider mutation and thread safety separately |
| Writing long nested lambdas | Extract named methods |
| Dereferencing nullable inputs | Define and implement null behavior |
| Calling a null behavior reference | Ensure the functional-interface variable is initialized |
| Mutating shared state in parallel streams | Use suitable reductions or collectors |
| Ignoring checked exceptions | Handle or propagate them intentionally |

Prefer short lambdas with meaningful parameter names. Use ordinary loops when early exit, complex control flow, or debugging is clearer. Lambdas are useful tools, not a requirement to rewrite every method or loop.

Predicate<T> — tests a condition and returns a boolean.

Function<T, R> — transforms an input into an output.

Consumer<T> — accepts an input and performs an action.

Supplier<T> — supplies a value without taking input.

UnaryOperator<T> — takes one input and returns the same type.

BinaryOperator<T> — takes two inputs and returns the same type.

## 18. Practice

1. Write an `IntPredicate` that checks whether a number is even.
2. Write a `Function<String, Integer>` returning a trimmed string's length.
3. Pass addition, subtraction, and multiplication lambdas to `calculate`.
4. Sort names by length, then alphabetically when lengths match.
5. Return a predicate from `atLeast(int minimum)` and explain its capture.
6. Combine predicates to reject null and blank text using Java 8-compatible methods.
7. Filter numbers, square even values, and calculate their sum with a stream.
8. Reassign a captured local intentionally and explain the compiler error.
9. Replace forwarding lambdas with method references.
10. Explain why `Supplier` and `Callable` differ when reading a file.

## Completion checklist

- [ ] I can explain how lambdas implement functional interfaces.
- [ ] I can write zero-, one-, and multiple-parameter lambdas.
- [ ] I understand expression bodies, blocks, and return rules.
- [ ] I can explain target typing and the two different uses of `var`.
- [ ] I understand capture, object mutation, lexical scope, and `this`.
- [ ] I can choose and invoke standard functional interfaces.
- [ ] I can pass, return, and compose behavior.
- [ ] I can handle exceptions and avoid unsafe stream side effects.
- [ ] I can compile, run, and explain the complete example.

## Official references

- [Dev.java: Lambda expressions](https://dev.java/learn/lambdas/) — learning path and examples.
- [Java Tutorials: Lambda expressions](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html) — introduction and target typing (Java 8 tutorial).
- [Java Language Specification, section 15.27](https://docs.oracle.com/javase/specs/jls/se25/html/jls-15.html#jls-15.27) — formal language rules.
- [Java API: java.util.function](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/function/package-summary.html) — interface signatures and primitive specializations.

---

[← 150. Functional interfaces](150_Functional_interfaces.md) | [Course Index](../README.md) | [152. Method references →](152_Method_references.md)
