[← 156. Supplier](156_Supplier.md) | [Course Index](../README.md) | [158. BinaryOperator →](158_BinaryOperator.md)

# Java UnaryOperator Practice — 10 Coding Questions and Answers

[← Road Map lesson](../../Road%20Map/10_Functional_Programming/157_UnaryOperator.md)

These exercises follow [157. UnaryOperator](../../Road%20Map/10_Functional_Programming/157_UnaryOperator.md), progressing from same-type transformations to composition, repetition, and immutable updates. Each question includes a model solution and a short explanation.

**Java version:** Java 8+. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports. Run exercises independently. Put helpers and nested classes inside `Main`, and example statements inside `main()`. Inputs are non-null unless specified; arithmetic uses small values except in the explicit overflow test.

## Question 1 — Clamp a Number

**Task:** Create a `UnaryOperator<Integer>` that clamps integers to the inclusive range `0–100`. Test `-5`, `40`, and `120`.

```java
UnaryOperator<Integer> clamp = number -> Math.max(0, Math.min(100, number));
for (int number : new int[]{-5, 40, 120}) {
    System.out.println(clamp.apply(number));
}
```

**Output:**
```text
0
40
100
```

**Concept:** `UnaryOperator<T>` takes and returns the same type. Its abstract operation is inherited from `Function<T, T>` and invoked using `apply()`.

## Question 2 — Normalize Text and Handle Null

**Task:** Write an operator that trims and uppercases text using `Locale.ROOT`. Return an empty string for null. Test `" java "`, null, and an empty string, printing brackets around each result.

```java
UnaryOperator<String> normalize = text -> text == null
        ? ""
        : text.trim().toUpperCase(Locale.ROOT);
for (String text : Arrays.asList(" java ", null, "")) {
    System.out.println("[" + normalize.apply(text) + "]");
}
```

**Output:**
```text
[JAVA]
[]
[]
```

**Concept:** A null policy is a design choice, not an operator feature. This policy intentionally treats missing and empty text alike.

## Question 3 — Compose in Both Orders

**Task:** Create increment and square operators. Apply increment-then-square and square-then-increment to `3`. Store the composed results in variables of the correct declared type.

```java
UnaryOperator<Integer> increment = number -> number + 1;
UnaryOperator<Integer> square = number -> number * number;
Function<Integer, Integer> incrementThenSquare = increment.andThen(square);
Function<Integer, Integer> squareThenIncrement = increment.compose(square);
System.out.println(incrementThenSquare.apply(3));
System.out.println(squareThenIncrement.apply(3));
```

**Output:**
```text
16
10
```

**Concept:** Generic `andThen()` and `compose()` are inherited from `Function` and return a `Function`, even when both stages are unary operators.

## Question 4 — Adapt Composition Back to an Operator

**Task:** Compose trimming and uppercasing. Adapt the resulting `Function<String, String>` to a `UnaryOperator<String>` using a method reference.

```java
UnaryOperator<String> trim = String::trim;
UnaryOperator<String> upper = text -> text.toUpperCase(Locale.ROOT);
Function<String, String> pipeline = trim.andThen(upper);
UnaryOperator<String> normalize = pipeline::apply;
System.out.println(normalize.apply(" java "));
```

**Output:**
```text
JAVA
```

**Concept:** Assigning `trim.andThen(upper)` directly to `UnaryOperator<String>` fails because of the inherited return type. An adapter explicitly supplies the operator contract.

## Question 5 — Replace List Elements

**Task:** Use `List.replaceAll()` to triple every element in a mutable list `[1, 2, 3]`. Print the updated list.

```java
List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3));
UnaryOperator<Integer> triple = number -> number * 3;
numbers.replaceAll(triple);
System.out.println(numbers);
```

**Output:**
```text
[3, 6, 9]
```

**Concept:** `List.replaceAll()` accepts a unary operator and replaces elements in the existing list. An unmodifiable list cannot support these replacements.

## Question 6 — Transform Without Replacing the Source

**Task:** Produce a new list of doubled numbers from `[1, 2, 3]` using `Stream.map()`. Print the source and result.

```java
List<Integer> source = Arrays.asList(1, 2, 3);
UnaryOperator<Integer> doubleIt = number -> number * 2;
List<Integer> result = source.stream()
        .map(doubleIt)
        .collect(Collectors.toList());
System.out.println(source);
System.out.println(result);
```

**Output:**
```text
[1, 2, 3]
[2, 4, 6]
```

**Concept:** Mapping here produces new values without replacing source elements. Mapping mutable objects would still require an explicit policy about mutation and copying.

## Question 7 — Generate an Iterative Sequence

**Task:** Generate the first six powers of two, starting with `1`, using a unary operator and `Stream.iterate()`.

```java
UnaryOperator<Integer> doubleIt = number -> number * 2;
List<Integer> powers = Stream.iterate(1, doubleIt)
        .limit(6)
        .collect(Collectors.toList());
System.out.println(powers);
```

**Output:**
```text
[1, 2, 4, 8, 16, 32]
```

**Concept:** The seed is the first element. The operator repeatedly transforms the previous value. The Java 8 overload is unbounded, so `limit()` is needed here.

## Question 8 — Repeat an Operator

**Task:** Write generic `applyTimes(value, times, operator)`. Reject negative repetition counts. Apply an increment operator zero, one, and three times to `5`.

```java
public static <T> T applyTimes(T value, int times, UnaryOperator<T> operator) {
    if (times < 0) {
        throw new IllegalArgumentException("times must not be negative");
    }
    Objects.requireNonNull(operator, "operator");
    T result = value;
    for (int i = 0; i < times; i++) {
        result = operator.apply(result);
    }
    return result;
}
```

**Example calls:**

```java
UnaryOperator<Integer> increment = number -> number + 1;
System.out.println(applyTimes(5, 0, increment));
System.out.println(applyTimes(5, 1, increment));
System.out.println(applyTimes(5, 3, increment));
```

**Output:**
```text
5
6
8
```

**Concept:** Passing an operator separates repetition from the transformation. Zero repetitions return the original input without invoking the operator.

## Question 9 — Immutable Updates and Identity

**Task:** Define an immutable `User`. Create an operator that returns a new user with a normalized name, preserving the original. Demonstrate that identity returns the original reference.

```java
static class User {
    private final String name;
    User(String name) { this.name = name; }
    String getName() { return name; }
}
```

**Example calls:**

```java
User original = new User(" ada ");
UnaryOperator<User> normalize = user ->
        new User(user.getName().trim().toUpperCase(Locale.ROOT));
User updated = normalize.apply(original);
System.out.println("[" + original.getName() + "]");
System.out.println(updated.getName());
System.out.println(original == updated);
UnaryOperator<User> same = UnaryOperator.identity();
System.out.println(same.apply(original) == original);
```

**Output:**
```text
[ ada ]
ADA
false
true
```

**Concept:** Same input and output types do not require in-place mutation. Identity returns its input reference; it does not create a copy.

## Question 10 — Primitive Composition and Exact Arithmetic

**Task:** Compose primitive increment and square operators and apply them to `3`. Use `Math.multiplyExact()` for squaring and catch overflow when squaring `50000`.

```java
IntUnaryOperator increment = number -> number + 1;
IntUnaryOperator square = number -> Math.multiplyExact(number, number);
IntUnaryOperator pipeline = increment.andThen(square);
System.out.println(pipeline.applyAsInt(3));
try {
    square.applyAsInt(50000);
} catch (ArithmeticException exception) {
    System.out.println("Overflow");
}
```

**Output:**
```text
16
Overflow
```

**Concept:** Primitive composition preserves `IntUnaryOperator` and avoids boxing. Exact multiplication makes overflow explicit; ordinary integer multiplication wraps. Increment overflow would need its own policy for unrestricted inputs.

---

## Key Takeaways

| API | Purpose | Example |
|---|---|---|
| `UnaryOperator<T>` | Same-type transformation | `apply(value)` |
| `andThen()` | Apply this stage, then another | Increment, then square |
| `compose()` | Preprocess before this stage | Square, then increment |
| `UnaryOperator.identity()` | Return the original input | Reference preservation |
| `List.replaceAll()` | Replace existing list elements | Tripling numbers |
| `Stream.map()` | Produce transformed stream elements | New doubled list |
| `Stream.iterate()` | Repeatedly apply an operator | Powers of two |
| `IntUnaryOperator` | Primitive same-type operation | `applyAsInt(value)` |
| `Math.multiplyExact()` | Detect multiplication overflow | Exact square |

**Reminder:** Generic operator composition returns `Function`. Atomic update APIs may retry their operators, so those callbacks should avoid external side effects.

---

## Interview Questions and Answers

### Interview Question 1 — What is the shape of UnaryOperator<T>?

**Answer:** It accepts T and returns T, extending Function<T, T>. It does not require mutation: returning a new immutable instance also satisfies the contract.

### Interview Question 2 — Why does generic operator composition return Function?

**Answer:** UnaryOperator inherits compose and andThen from Function. Their declared result is Function, even when both input stages are unary operators. Adapt the result explicitly if an operator target is needed.

### Interview Question 3 — How does replaceAll differ from stream mapping?

**Answer:** List.replaceAll replaces elements in the existing list. Mapping followed by collection builds a separate result list, although a mapper can still mutate objects if written to do so.

### Interview Question 4 — What does UnaryOperator.identity preserve?

**Answer:** The exact input value or reference. It is an identity transformation, not a clone or a deep copy.

### Interview Question 5 — Why should atomic update operators avoid external effects?

**Answer:** Atomic update methods may retry their callbacks after contention. Logging, sending events, or changing unrelated state in such callbacks could happen more than once.


[← 156. Supplier](156_Supplier.md) | [Course Index](../README.md) | [158. BinaryOperator →](158_BinaryOperator.md)
