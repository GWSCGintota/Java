[← 09. Generics](09_Generics.md) | [Course Index](../README.md) | [11. Stream API →](11_Stream_API.md)

---

# Functional Programming — Interview Questions and Answers

**Coverage:** Functional interfaces, lambdas, method references, Predicate, Function, Consumer, Supplier, UnaryOperator, BinaryOperator, and higher-order programming.

**Java version:** All runnable solutions use Java 8-compatible APIs. Lambda parameter `var` requires Java 11.

## 1. Functional Interfaces

### Q1. What is functional programming in Java?

It is a style that models work as transformations and passes behavior through functions. Java supports this through functional interfaces, lambdas, method references, and APIs such as streams. Pure functions, immutable data, and avoiding shared mutable state make this style easier to reason about. Java supports a mixture of object-oriented and functional styles.

### Q2. What is a functional interface?

An interface representing one abstract operation, often called a SAM (single abstract method). Default and static methods do not count. Abstract declarations matching public `Object` methods do not count either; compatible inherited declarations can represent one operation.

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);

    default String description() {
        return "Calculates a result";
    }
}
```

### Q3. Is @FunctionalInterface required?

No. It documents intent and makes the compiler reject declarations that do not satisfy the functional interface rules.

### Q4. Can a lambda implement a class or an interface with two unrelated abstract methods?

No. A lambda needs a functional interface target. For multiple independent operations or additional instance state, use a class or an anonymous class.

### Q5. Can a functional interface extend another interface?

Yes, if the resulting interface still represents one abstract operation. Inheriting additional unrelated abstract methods prevents it from being functional.

[Study note: 150. Functional interfaces](../Road%20Map/10_Functional_Programming/150_Functional_interfaces.md)

## 2. Lambda Expressions

### Q6. What is a lambda expression?

A concise implementation of a functional interface's abstract method. It allows behavior to be stored, passed to methods, and returned from methods.

```java
IntBinaryOperator add = (a, b) -> a + b;
System.out.println(add.applyAsInt(4, 6)); // 10
```

### Q7. What are the main lambda syntax rules?

```java
Supplier<Integer> answer = () -> 42;
IntUnaryOperator square = n -> n * n;
IntBinaryOperator add = (int a, int b) -> a + b;
IntUnaryOperator absolute = n -> {
    if (n < 0) {
        return -n;
    }
    return n;
};
```

Zero or multiple parameters require parentheses, as do explicitly typed parameters. A single inferred parameter may omit parentheses. Do not mix explicit and inferred parameter styles. A value-returning block must return a compatible value on every path that completes normally. The absolute-value example assumes an input other than `Integer.MIN_VALUE`, whose positive magnitude cannot fit in an `int`.

### Q8. What is target typing? Why does var task = () -> {} fail?

Java uses the surrounding functional interface type to determine a lambda's signature. A bare lambda initializer does not provide a type for local-variable `var` inference.

```java
Runnable task = () -> {}; // Valid
// var task = () -> {};   // Invalid: no target functional interface
```

This differs from `(var a, var b) -> a + b`, which is supported for lambda parameters from Java 11 when a target interface is available.

### Q9. What does effectively final mean?

A local variable is effectively final if it is never reassigned after initialization. Captured local variables and enclosing parameters must be final or effectively final.

```java
int factor = 3;
IntUnaryOperator triple = value -> value * factor;
// factor = 4; // Would invalidate the capture
```

### Q10. Can a lambda change a captured object's contents?

Yes. The local reference must remain effectively final; the object need not be immutable. For example, a captured list may be modified. Shared mutation still requires consideration of thread safety.

### Q11. How does this differ between a lambda and an anonymous class?

A lambda uses the enclosing meaning of `this`. An anonymous class has its own `this`. Lambda parameters and locals also cannot redeclare enclosing local variables or parameters that remain in scope.

### Q12. Does defining a lambda run it or start a thread?

No. Its body executes when its interface method is invoked. An API may arrange asynchronous execution, but the lambda itself does not.

### Q13. Can a lambda throw a checked exception?

Only if its target abstract method allows it, or the lambda handles it internally. `Callable.call()` declares checked exceptions; `Supplier.get()` does not. Choose an appropriate interface, catch the exception, or wrap it meaningfully instead of silently discarding it.

### Q14. What does return inside a lambda exit?

Only the current lambda invocation, not the enclosing method. A lambda cannot use `break` or `continue` to control an enclosing loop.

[Study note: 151. Lambda expressions](../Road%20Map/10_Functional_Programming/151_Lambda_expressions.md)

## 3. Method References

### Q15. What is a method reference?

A reference to an existing method or constructor used as the implementation of a compatible functional interface. It is often clearer than a lambda that only forwards arguments.

| Kind | Lambda | Method reference |
| --- | --- | --- |
| Static method | `s -> Integer.parseInt(s)` | `Integer::parseInt` |
| Method on a particular object | `s -> System.out.println(s)` | `System.out::println` |
| Method on an input object | `s -> s.length()` | `String::length` |
| Constructor | `() -> new ArrayList<String>()` | `ArrayList<String>::new` |

### Q16. How does String::length receive an object without explicitly declaring a parameter?

For a target such as `ToIntFunction<String>`, the function's input becomes the receiver of the instance method. `length.applyAsInt("Java")` effectively calls `"Java".length()`.

### Q17. When should you keep a lambda instead of a method reference?

When extra logic or argument adaptation makes the lambda clearer, such as `text -> text.trim().length()`. Both forms need compatible target types; neither is inherently a performance improvement.

[Study note: 152. Method references](../Road%20Map/10_Functional_Programming/152_Method_references.md)

## 4. Standard Functional Interfaces

### Q18. How do the common interfaces differ?

These interfaces are in `java.util.function`.

| Interface | Abstract method | Example |
| --- | --- | --- |
| `Predicate<T>` | `boolean test(T value)` | `n -> n > 0` |
| `Function<T, R>` | `R apply(T value)` | `s -> s.length()` |
| `Consumer<T>` | `void accept(T value)` | `s -> System.out.println(s)` |
| `Supplier<T>` | `T get()` | `() -> new ArrayList<String>()` |
| `UnaryOperator<T>` | `T apply(T value)` | `s -> s.trim()` |
| `BinaryOperator<T>` | `T apply(T a, T b)` | `(a, b) -> a + b` |

The examples assume suitable type arguments. Generic type arguments use reference types, such as `Integer`, rather than primitives.

### Q19. How do you combine predicates safely?

Use `and`, `or`, and `negate`. `and` and `or` short-circuit, which lets an earlier check protect a later one.

```java
Predicate<String> nonNull = text -> text != null;
Predicate<String> nonBlank = text -> !text.trim().isEmpty();
Predicate<String> valid = nonNull.and(nonBlank);
System.out.println(valid.test(null)); // false; no NullPointerException
```

Reversing these checks would dereference null first.

### Q20. How do Function.compose and Function.andThen differ?

```java
Function<Integer, Integer> doubleIt = n -> n * 2;
Function<Integer, Integer> addThree = n -> n + 3;
System.out.println(doubleIt.andThen(addThree).apply(5)); // 13
System.out.println(doubleIt.compose(addThree).apply(5)); // 16
```

`f.andThen(g)` applies `f` then `g`; `f.compose(g)` applies `g` then `f`.

### Q21. Does Consumer return a value?

No. Its abstract method returns `void`. It is intended for actions such as printing or updating an external system. `andThen` sequences consumers; if the first throws, the second does not run.

### Q22. Is Supplier evaluated immediately or guaranteed to return a new object?

Neither. Its body runs when `get()` is called. It may create a new object, return an existing value, or compute a result. Laziness depends on when the caller invokes it; memoization is not automatic.

### Q23. How do UnaryOperator and Function differ?

`UnaryOperator<T>` extends `Function<T, T>`. It makes the same-input-and-output-type requirement explicit. `Function<T, R>` permits different types.

### Q24. How do BinaryOperator and BiFunction differ?

`BinaryOperator<T>` extends `BiFunction<T, T, T>`: both inputs and the result share a type. `BiFunction<T, U, R>` permits different input and output types.

### Q25. Why use primitive functional interfaces?

Types such as `IntPredicate`, `IntUnaryOperator`, `IntBinaryOperator`, and `ToIntFunction<T>` can avoid boxing. Their invocation methods reflect their primitive signatures, such as `applyAsInt` rather than `apply`.

### Q26. Can you assign a Predicate<String> to a Function<String, Boolean>?

Not directly. These are different interface types despite similar behavior. Adapt the invocation instead:

```java
Predicate<String> empty = String::isEmpty;
Function<String, Boolean> adapted = empty::test;
```

Study notes: [153. Predicate](../Road%20Map/10_Functional_Programming/153_Predicate.md) | [154. Function](../Road%20Map/10_Functional_Programming/154_Function.md) | [155. Consumer](../Road%20Map/10_Functional_Programming/155_Consumer.md) | [156. Supplier](../Road%20Map/10_Functional_Programming/156_Supplier.md) | [157. UnaryOperator](../Road%20Map/10_Functional_Programming/157_UnaryOperator.md) | [158. BinaryOperator](../Road%20Map/10_Functional_Programming/158_BinaryOperator.md)

## 5. Higher-order Programming and Design

### Q27. What is a higher-order method?

A method that receives or returns behavior represented by a functional interface.

```java
static IntUnaryOperator multiplier(int factor) {
    return value -> value * factor;
}

static int calculate(int a, int b, IntBinaryOperator operation) {
    return operation.applyAsInt(a, b);
}
```

### Q28. What is a pure function?

A function whose result depends only on its inputs and which has no observable side effects. Updating shared state, performing I/O, or reading changing external state makes reasoning about purity harder. Java does not enforce purity for lambdas.

### Q29. Why avoid shared mutable state in stream operations?

It can create order dependencies and race conditions, especially in parallel processing. Prefer reductions or collectors for aggregate results. Do not add elements to a shared ordinary `ArrayList` from parallel `forEach`.

### Q30. What property should a reduction operator have?

It should be associative: regrouping inputs should not change the result. When an identity is supplied, it must be neutral for that operator. Addition of integers with identity `0` fits; subtraction does not. Floating-point arithmetic requires additional care because rounding can make regrouping change results.

### Q31. Are two equivalent lambdas guaranteed to be the same object?

No. Do not depend on lambda identity or use lambda objects as synchronization locks. Allocation and caching are implementation details.

### Q32. When should behavior be extracted into a named method or class?

When a lambda is long, deeply nested, reused, needs its own state or multiple operations, or obscures intent. Choose readability over making every operation a lambda.

[Study note: 159. Higher-order style programming](../Road%20Map/10_Functional_Programming/159_Higher-order_style_programming.md)

## 6. Code Questions — Predict, Explain, and Fix

### C1. Predict the output

```java
Function<Integer, Integer> increment = n -> n + 1;
Function<Integer, Integer> square = n -> n * n;
System.out.println(increment.andThen(square).apply(3));
System.out.println(increment.compose(square).apply(3));
```

**Answer:** `16`, then `10`. The first squares the incremented input; the second increments the squared input.

### C2. Why does this not compile? Fix it without a mutable holder.

```java
int total = 0;
// numbers.forEach(n -> total += n);
```

**Answer:** The lambda would reassign a captured local variable. Assuming `numbers` is a non-null `List<Integer>` with no null elements, use a reduction:

```java
int total = numbers.stream().mapToInt(Integer::intValue).sum();
```

### C3. Find the null-handling bug

```java
Predicate<String> valid = text -> !text.isEmpty() && text != null;
```

**Answer:** The dereference happens before the null check. Reverse the conditions:

```java
Predicate<String> valid = text -> text != null && !text.isEmpty();
```

### C4. Will this compile?

```java
Consumer<String> action = text -> text.length();
```

**Answer:** Yes. A method invocation is a valid statement expression, so its returned value can be discarded for a `void` target. In contrast, `Consumer<Integer> action = n -> n + 1;` is invalid because arithmetic alone is not a statement expression.

### C5. Predict the output and explain the mutation

```java
List<String> names = new ArrayList<>();
Consumer<String> add = names::add;
add.accept("Ada");
System.out.println(names.size());
```

**Answer:** `1`. The reference is effectively final while the referenced list is mutable. The boolean returned by `add` is discarded for the `Consumer` target.

### C6. Why is subtraction a poor sorting comparator?

```java
Comparator<Integer> order = (a, b) -> a - b;
```

**Answer:** Integer subtraction can overflow and produce the wrong sign. Use `Integer::compare` or `Comparator.naturalOrder()`.

## 7. Coding Practice

Try each task before opening the solutions. Unless stated otherwise, assume non-null inputs and values small enough to avoid integer overflow.

1. **Even numbers:** Write an `IntPredicate` and test `8` and `7`. Expected: `true`, `false`.
2. **Safe text validation:** Combine predicates to reject null, empty, and whitespace-only strings. Test `null`, `"   "`, and `" Java "`. Expected: `false`, `false`, `true`.
3. **Transform a string:** Trim and uppercase `" java "` using two composed functions and `Locale.ROOT`. Expected: `JAVA`.
4. **Supply independent lists:** Use a supplier to create two lists. Add an item to the first; the second should remain empty.
5. **Pass an operation:** Implement `calculate` accepting an `IntBinaryOperator`. For inputs `10` and `5`, demonstrate addition, subtraction, and multiplication. Expected: `15`, `5`, `50`.
6. **Return behavior:** Implement `multiplier(3)` and apply it to `7`. Expected: `21`.
7. **Sort names:** Sort `Bob, Ada, Grace, Al` by length, then alphabetically. Expected: `[Al, Ada, Bob, Grace]`.
8. **Transform a list:** Use `UnaryOperator<String>` and `List.replaceAll` to trim and uppercase `" ada "` and `" grace "`. Expected: `[ADA, GRACE]`.
9. **Reduce values:** Use a `BinaryOperator<Integer>` to add `[2, 4, 6]` with identity `0`. Expected: `12`.
10. **Process numbers:** From `[1, 2, 3, 4, 5, 6]`, select even numbers, square them, and sum the results. Expected: `56`.
11. **Custom interface:** Declare `TextFormatter` with `String format(String text)`, then implement it with a lambda that wraps text in brackets. Expected for `Java`: `[Java]`.
12. **Sequence actions:** Combine two consumers. For `Java`, print `Input: Java` and then `Length: 4`.

## 8. Runnable Practice Solutions

Save this complete example as `FunctionalProgrammingPractice.java`. The snippets earlier in this document are independent examples; this class provides the imports and runnable context for all twelve practice solutions.

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class FunctionalProgrammingPractice {
    @FunctionalInterface
    interface TextFormatter {
        String format(String text);
    }

    static int calculate(int a, int b, IntBinaryOperator operation) {
        return operation.applyAsInt(a, b);
    }

    static IntUnaryOperator multiplier(int factor) {
        return value -> value * factor;
    }

    public static void main(String[] args) {
        // 1. Primitive predicate
        IntPredicate even = n -> n % 2 == 0;
        System.out.println(even.test(8)); // true
        System.out.println(even.test(7)); // false

        // 2. Short-circuiting null protection
        Predicate<String> nonNull = text -> text != null;
        Predicate<String> nonBlank = text -> !text.trim().isEmpty();
        Predicate<String> valid = nonNull.and(nonBlank);
        System.out.println(valid.test(null));     // false
        System.out.println(valid.test("   "));    // false
        System.out.println(valid.test(" Java ")); // true

        // 3. Function composition
        Function<String, String> trim = String::trim;
        Function<String, String> upper = text -> text.toUpperCase(Locale.ROOT);
        System.out.println(trim.andThen(upper).apply(" java ")); // JAVA

        // 4. A new list on each get() for this particular supplier
        Supplier<List<String>> listFactory = ArrayList::new;
        List<String> first = listFactory.get();
        List<String> second = listFactory.get();
        first.add("Ada");
        System.out.println(second.isEmpty()); // true

        // 5. Passing behavior
        System.out.println(calculate(10, 5, (a, b) -> a + b)); // 15
        System.out.println(calculate(10, 5, (a, b) -> a - b)); // 5
        System.out.println(calculate(10, 5, (a, b) -> a * b)); // 50

        // 6. Returning behavior with a captured parameter
        System.out.println(multiplier(3).applyAsInt(7)); // 21

        // 7. Comparator composition
        List<String> names = new ArrayList<>(
                Arrays.asList("Bob", "Ada", "Grace", "Al"));
        names.sort(Comparator.comparingInt(String::length)
                .thenComparing(Comparator.naturalOrder()));
        System.out.println(names); // [Al, Ada, Bob, Grace]

        // 8. UnaryOperator and in-place replacement
        UnaryOperator<String> normalize = text ->
                text.trim().toUpperCase(Locale.ROOT);
        List<String> padded = new ArrayList<>(Arrays.asList(" ada ", " grace "));
        padded.replaceAll(normalize);
        System.out.println(padded); // [ADA, GRACE]

        // 9. BinaryOperator reduction
        BinaryOperator<Integer> sum = Integer::sum;
        int total = Arrays.asList(2, 4, 6).stream().reduce(0, sum);
        System.out.println(total); // 12

        // 10. Stateless pipeline and primitive sum
        int sumOfSquares = Arrays.asList(1, 2, 3, 4, 5, 6).stream()
                .mapToInt(Integer::intValue)
                .filter(even)
                .map(n -> n * n)
                .sum();
        System.out.println(sumOfSquares); // 56

        // 11. Custom functional interface
        TextFormatter brackets = text -> "[" + text + "]";
        System.out.println(brackets.format("Java")); // [Java]

        // 12. Consumer sequencing
        Consumer<String> printInput = text -> System.out.println("Input: " + text);
        Consumer<String> printLength = text ->
                System.out.println("Length: " + text.length());
        printInput.andThen(printLength).accept("Java");
        // Input: Java
        // Length: 4
    }
}
```

Compile and run:

```shell
javac FunctionalProgrammingPractice.java
java FunctionalProgrammingPractice
```

**Follow-up challenges:** Define a Unicode whitespace policy instead of relying on `trim()`, handle null list elements explicitly, and use `Math.multiplyExact` where overflowing multiplication should fail. Explain your choices before changing the solutions.

## Revision Checklist

- [ ] Explain functional interfaces, target typing, and lambda capture.
- [ ] Choose the correct interface and invocation method for a task.
- [ ] Distinguish method references from lambda expressions.
- [ ] Explain short-circuiting and composition order.
- [ ] Handle checked exceptions and shared state intentionally.
- [ ] Solve the coding tasks and explain their edge cases.

---

[← 09. Generics](09_Generics.md) | [Course Index](../README.md) | [11. Stream API →](11_Stream_API.md)
