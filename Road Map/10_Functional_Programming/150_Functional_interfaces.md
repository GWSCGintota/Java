# 150. Functional interfaces

**Section:** Java Functional Programming  
**Level:** Beginner → Intermediate

## Learning goal

Understand what a **functional interface** is and how to implement it using a lambda expression.

## What is a functional interface?

A functional interface is an interface with **exactly one abstract method**. It describes one operation, such as calculating a result, checking a condition, or processing a value.

You can provide the implementation of that method using a **lambda expression**, a method reference, or a class that implements the interface.

- `@FunctionalInterface` is optional, but recommended: the compiler checks that the interface meets the functional interface rules.
- An interface can also have `default`, `static`, and private methods. These have implementations and do not count as abstract methods.
- Inherited abstract methods count too. Multiple inherited declarations can represent one method when their signatures and return types are compatible.
- Abstract declarations matching public methods of `Object`, such as `equals(Object)`, do not count toward the single abstract method requirement.

## Example: a custom functional interface

Save this complete example as `Main.java`:

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b); // The single abstract method

    default String description() {
        return "Calculates a result from two integers";
    }
}

public class Main {
    public static void main(String[] args) {
        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;

        System.out.println(add.calculate(10, 5));      // 15
        System.out.println(multiply.calculate(10, 5)); // 50
        System.out.println(add.description());
    }
}
```

In `(a, b) -> a + b`:

- `(a, b)` supplies the parameters of `calculate`.
- `->` separates the parameters from the implementation.
- `a + b` is the returned result. Java infers the parameter and return types from `Calculator`.

The lambda implements `calculate`; it does not call it immediately. Calling `add.calculate(10, 5)` executes the operation.

## Common built-in functional interfaces

These interfaces are in `java.util.function`:

| Interface | Abstract method | Purpose |
| --- | --- | --- |
| `Predicate<T>` | `boolean test(T value)` | Check a condition |
| `Function<T, R>` | `R apply(T value)` | Transform a value |
| `Consumer<T>` | `void accept(T value)` | Process a value without returning a result |
| `Supplier<T>` | `T get()` | Supply a value without an input |
| `UnaryOperator<T>` | `T apply(T value)` | Transform a value into the same type |
| `BinaryOperator<T>` | `T apply(T first, T second)` | Combine two values of the same type |

Here is another complete `Main.java` example:

```java
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Predicate<Integer> isAdult = age -> age >= 18;
        Function<String, Integer> nameLength = name -> name.length();
        Consumer<String> print = System.out::println;
        Supplier<String> greeting = () -> "Hello, Java!";

        System.out.println(isAdult.test(20));       // true
        System.out.println(nameLength.apply("Java")); // 4
        print.accept("Learning functional interfaces");
        System.out.println(greeting.get());        // Hello, Java!
    }
}
```

`System.out::println` is a method reference: it supplies the implementation of `Consumer.accept` by referring to an existing method.

## When to use one

Use a functional interface when you want to pass behavior into a method. For example, `stream.filter(...)` accepts a `Predicate` that decides which elements to keep, and `stream.map(...)` accepts a `Function` that transforms each element.

## Common mistakes

- Adding a second unrelated abstract method makes the interface unsuitable for a lambda. With `@FunctionalInterface`, the compiler reports this at the interface declaration.
- The lambda parameters and return value must match the abstract method's types.
- A lambda needs a functional interface as its target type. For example, `Calculator add = (a, b) -> a + b;` works, but `var add = (a, b) -> a + b;` does not provide a target type.
- Captured local variables must be final or effectively final: you cannot reassign a captured local variable.

## Practice

1. Add a `Calculator subtract` lambda and use it to subtract `5` from `10`.
2. Create a `Predicate<Integer>` that checks whether a number is even.
3. Create a `Function<String, String>` that converts a name to uppercase.
4. Temporarily add another abstract method to `Calculator` and observe the compiler error, then remove it.

## Completion checklist

- [ ] I can explain **Functional interfaces** in my own words.
- [ ] I can recognize it in Java code.
- [ ] I can write or modify a small example that uses it.
- [ ] I know at least one common mistake related to it.
- [ ] I understand why this topic is useful before moving on.

---

[← 149. Type erasure](../09_Generics/149_Type_erasure.md) | [Course Index](../README.md) | [151. Lambda expressions →](151_Lambda_expressions.md)
