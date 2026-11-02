[← 149. Type erasure](../09_Generics/149_Type_erasure.md) | [Course Index](../README.md) | [151. Lambda expressions →](151_Lambda_expressions.md)

# Java Functional Interfaces Practice — 10 Coding Questions and Answers

[← Road Map lesson](../../Road%20Map/10_Functional_Programming/150_Functional_interfaces.md)

These exercises follow [150. Functional interfaces](../../Road%20Map/10_Functional_Programming/150_Functional_interfaces.md), progressing from custom contracts to reusable behavior. Each question includes a model solution and a short explanation.

**Java version:** Java 8+. Assume `java.util.*` and `java.util.function.*` imports. Run each question independently. Place interfaces outside `Main`, helper methods inside `Main`, and example statements inside `main()`. Inputs are non-null and arithmetic uses small values unless stated otherwise.

## Question 1 — Create a Calculator Interface

**Task:** Define a functional interface with `int calculate(int a, int b)`. Implement addition and subtraction using lambdas. Test both with `10` and `5`.

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}
```

**Example calls:**

```java
Calculator add = (a, b) -> a + b;
Calculator subtract = (a, b) -> a - b;
System.out.println(add.calculate(10, 5));
System.out.println(subtract.calculate(10, 5));
```

**Output:**
```text
15
5
```

**Concept:** A functional interface describes one abstract operation. A lambda provides its implementation; calling the interface method executes it.

## Question 2 — Add Default and Static Methods

**Task:** Define a `Formatter` with one abstract method, a default description, and a static prefix. Implement uppercase formatting using `Locale.ROOT`.

```java
@FunctionalInterface
interface Formatter {
    String format(String text);
    default String description() { return "Text formatter"; }
    static String prefix() { return "Result: "; }
}
```

**Example calls:**

```java
Formatter upper = text -> text.toUpperCase(Locale.ROOT);
System.out.println(upper.description());
System.out.println(Formatter.prefix() + upper.format("java"));
```

**Output:**
```text
Text formatter
Result: JAVA
```

**Concept:** Default and static methods have implementations and do not add abstract operations. Call an interface's static method through its interface name.

## Question 3 — Three Implementations of One Contract

**Task:** Implement a string-length function using a lambda, a method reference, and an anonymous class. Apply each to `Java`.

```java
Function<String, Integer> lambda = text -> text.length();
Function<String, Integer> reference = String::length;
Function<String, Integer> anonymous = new Function<String, Integer>() {
    @Override
    public Integer apply(String text) {
        return text.length();
    }
};
System.out.println(lambda.apply("Java"));
System.out.println(reference.apply("Java"));
System.out.println(anonymous.apply("Java"));
```

**Output:**
```text
4
4
4
```

**Concept:** Each implementation satisfies the same functional contract. The interface supplies the required parameter and result types.

## Question 4 — Choose the Built-in Interface

**Task:** Use the appropriate interfaces to check adulthood, calculate string length, print a message, and supply a greeting.

```java
Predicate<Integer> adult = age -> age >= 18;
Function<String, Integer> length = String::length;
Consumer<String> print = System.out::println;
Supplier<String> greeting = () -> "Hello, Java!";
System.out.println(adult.test(20));
System.out.println(length.apply("Java"));
print.accept("Learning interfaces");
System.out.println(greeting.get());
```

**Output:**
```text
true
4
Learning interfaces
Hello, Java!
```

**Concept:** Select the interface by its input and output contract: condition, transformation, action, or value production.

## Question 5 — Same-Type Operators

**Task:** Create a unary operator that doubles an integer and a binary operator that finds the larger integer. Test `7`, and the pair `7, 12`.

```java
UnaryOperator<Integer> doubleIt = value -> value * 2;
BinaryOperator<Integer> maximum = Integer::max;
System.out.println(doubleIt.apply(7));
System.out.println(maximum.apply(7, 12));
```

**Output:**
```text
14
12
```

**Concept:** `UnaryOperator<T>` has the shape `T → T`; `BinaryOperator<T>` has the shape `(T, T) → T`. Generic arguments use reference types such as `Integer`.

## Question 6 — Pass Behavior to a Method

**Task:** Implement `calculate(a, b, operation)` using an `IntBinaryOperator`. Supply addition and multiplication behavior.

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

**Concept:** Functional interfaces let callers supply behavior while a method controls invocation. Primitive specializations avoid boxed numeric parameters and results.

## Question 7 — Return Behavior with Captured Configuration

**Task:** Write `atLeast(minimum)` returning a predicate. Use an adulthood predicate to test `17` and `20`.

```java
public static Predicate<Integer> atLeast(int minimum) {
    return value -> value >= minimum;
}
```

**Example calls:**

```java
Predicate<Integer> adult = atLeast(18);
System.out.println(adult.test(17));
System.out.println(adult.test(20));
```

**Output:**
```text
false
true
```

**Concept:** The returned lambda captures the effectively final parameter `minimum`. Reassigning that parameter would invalidate the capture.

## Question 8 — Repair a Non-Functional Interface

**Task:** An interface declares both `calculate(int, int)` and abstract `description()`. Repair it so a lambda can implement it, keeping a reusable description.

```java
@FunctionalInterface
interface Operation {
    int calculate(int a, int b);
    default String description() { return "Integer operation"; }
}
```

**Example calls:**

```java
Operation multiply = (a, b) -> a * b;
System.out.println(multiply.description());
System.out.println(multiply.calculate(3, 4));
```

**Output:**
```text
Integer operation
12
```

**Concept:** Two unrelated abstract methods prevent lambda implementation. Giving one a default implementation restores the single abstract operation. `@FunctionalInterface` makes the compiler check the contract.

## Question 9 — Inherited Abstract Methods

**Task:** Define `NamedLength` extending an interface with `int length(String text)`. Redeclare the same method and add a default label. Implement it with a method reference.

```java
interface Length {
    int length(String text);
}

@FunctionalInterface
interface NamedLength extends Length {
    @Override
    int length(String text);
    default String label() { return "Length"; }
}
```

**Example calls:**

```java
NamedLength length = String::length;
System.out.println(length.label() + ": " + length.length("Java"));
```

**Output:**
```text
Length: 4
```

**Concept:** Compatible inherited and redeclared signatures can represent one abstract operation. Count the effective abstract contract, rather than method declarations alone.

## Question 10 — An Object Method Declaration

**Task:** Create a functional interface declaring `boolean equals(Object other)` and `String greet(String name)`. Implement the greeting using a lambda.

```java
@FunctionalInterface
interface Greeter {
    boolean equals(Object other);
    String greet(String name);
}
```

**Example calls:**

```java
Greeter greeter = name -> "Hello, " + name;
System.out.println(greeter.greet("Ada"));
```

**Output:**
```text
Hello, Ada
```

**Concept:** Abstract declarations matching public methods of `Object` do not count toward the functional interface's abstract operation. Avoid relying on the identity or equality of separately created lambda objects.

---

## Key Takeaways

| API or rule | Purpose | Example |
|---|---|---|
| `@FunctionalInterface` | Check the single abstract operation | Custom `Calculator` |
| Default method | Provide inherited behavior | `default String description()` |
| Static method | Provide an interface utility | `Formatter.prefix()` |
| `Predicate<T>` | Check a condition | `test(value)` |
| `Function<T, R>` | Transform input | `apply(value)` |
| `Consumer<T>` | Perform an action | `accept(value)` |
| `Supplier<T>` | Produce a value | `get()` |
| `IntBinaryOperator` | Combine primitive integers | `applyAsInt(a, b)` |

**Reminder:** A lambda needs a functional interface target type. `var operation = (a, b) -> a + b` supplies no target type and does not compile.

---

## Interview Questions and Answers

### Interview Question 1 — What makes an interface functional?

**Answer:** It has one effective abstract operation after inheritance and compatible method declarations are considered. Default and static methods do not add abstract operations; declarations matching public Object methods are excluded.

### Interview Question 2 — Is @FunctionalInterface required?

**Answer:** No. It asks the compiler to verify the functional contract and documents intent. A valid functional interface can be used as a lambda target without the annotation.

### Interview Question 3 — How do Function, Predicate, Consumer, and Supplier differ?

**Answer:** Function transforms an input using apply; Predicate checks it using test; Consumer performs an action using accept; Supplier produces a value without input using get.

### Interview Question 4 — Can a lambda have two unrelated abstract methods to implement?

**Answer:** No. A single lambda supplies one functional operation. Split the contract into separate interfaces or implement a regular interface with a class.

### Interview Question 5 — Why does a bare lambda need a target type?

**Answer:** Its parameter types, result contract, and allowed checked exceptions come from a functional interface target supplied by assignment, a cast, or an invocation context.


[← 149. Type erasure](../09_Generics/149_Type_erasure.md) | [Course Index](../README.md) | [151. Lambda expressions →](151_Lambda_expressions.md)
