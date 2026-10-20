[← 152. Method references](152_Method_references.md) | [Course Index](../README.md) | [154. Function →](154_Function.md)

---

# 153. Predicate

**Section:** Java Functional Programming

**Level:** Beginner → Advanced

**Version:** Core examples use Java 8+. Examples using `Predicate.not` or `String.isBlank` require Java 11+ and are labelled.

## 1. What is a Predicate?

`Predicate<T>` represents a condition on one input of type `T`. Its abstract method returns a primitive `boolean`.

```java
import java.util.function.Predicate;

// Inside a method:
Predicate<Integer> isAdult = age -> age >= 18;
System.out.println(isAdult.test(20)); // true
System.out.println(isAdult.test(15)); // false
```

Think of a predicate as a reusable yes/no rule. Common uses include filtering, validation, searching, conditional removal, and business rules. Creating a predicate does not test anything; calling `test` or passing it to an API that invokes it executes the rule.

Its core declaration is equivalent to:

```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T value);
    // The actual interface also provides default and static methods.
}
```

`T` is a reference type: use `Predicate<Integer>`, not `Predicate<int>`. Use `IntPredicate` for primitive `int` values.

### Predicate versus other functional interfaces

| Interface | Operation | Purpose |
| --- | --- | --- |
| `Predicate<T>` | `boolean test(T value)` | Check a condition |
| `Function<T, R>` | `R apply(T value)` | Transform a value |
| `Consumer<T>` | `void accept(T value)` | Perform an action |
| `Supplier<T>` | `T get()` | Supply a value |
| `BiPredicate<T, U>` | `boolean test(T first, U second)` | Check two inputs |

`Function<T, Boolean>` also models a boolean-like result, but returns boxed `Boolean` and has different composition methods. Choose `Predicate` for a condition.

## 2. Quick API reference

| Method | Kind | Meaning |
| --- | --- | --- |
| `test(value)` | Abstract | Evaluate the condition |
| `and(other)` | Default | Both conditions must pass; short-circuits |
| `or(other)` | Default | At least one condition must pass; short-circuits |
| `negate()` | Default | Reverse the condition |
| `Predicate.isEqual(target)` | Static | Build a null-safe equality check |
| `Predicate.not(target)` | Static, Java 11+ | Build the opposite of a predicate |

Combining predicates creates another predicate; it does not immediately test an input. The original predicates remain usable.

## 3. Basic numeric examples

Unless a snippet includes imports, assume types from `java.util`, `java.util.function`, and `java.util.stream` are imported and declarations appear inside a method. Examples with boxed numbers assume non-null input unless explicitly guarded.

```java
Predicate<Integer> even = n -> n % 2 == 0;
Predicate<Integer> odd = n -> n % 2 != 0;
Predicate<Integer> positive = n -> n > 0;
Predicate<Integer> zero = n -> n == 0;
Predicate<Integer> inRange = n -> n >= 10 && n <= 20;

System.out.println(even.test(8));      // true
System.out.println(odd.test(-3));      // true
System.out.println(positive.test(0));  // false
System.out.println(zero.test(0));      // true
System.out.println(inRange.test(20));  // true: upper bound included
```

Use `n % 2 != 0` rather than `n % 2 == 1` when negative odd integers are allowed.

## 4. String examples and null safety

```java
Predicate<String> empty = String::isEmpty;
Predicate<String> longEnough = text -> text.length() >= 5;
Predicate<String> startsWithJava = text -> text.startsWith("Java");
Predicate<String> containsAt = text -> text.contains("@");
Predicate<String> digitsOnly = text -> text.matches("[0-9]+");

System.out.println(empty.test(""));           // true
System.out.println(longEnough.test("Hello")); // true
System.out.println(startsWithJava.test("Java 8")); // true
System.out.println(digitsOnly.test("123"));   // true
System.out.println(digitsOnly.test(""));      // false
```

The `containsAt` example is only a substring check, not a complete email validator. `length()` measures UTF-16 code units, not necessarily user-perceived characters.

### Build a null-safe rule

```java
Predicate<String> nonNull = Objects::nonNull;
Predicate<String> nonEmptyAfterTrim = text -> !text.trim().isEmpty();
Predicate<String> usable = nonNull.and(nonEmptyAfterTrim);

System.out.println(usable.test(null));    // false
System.out.println(usable.test("   "));   // false
System.out.println(usable.test(" Java ")); // true
```

The null check must run first. `trim()` does not handle every Unicode whitespace character; choose a whitespace policy appropriate to the application.

### Java 11+: blank strings

```java
Predicate<String> nonBlank = Predicate.not(String::isBlank);
Predicate<String> usable = ((Predicate<String>) Objects::nonNull).and(nonBlank);
```

`isBlank()` uses Java's whitespace definition. `nonBlank.test(null)` still throws; the composed `usable` rule provides the null guard.

## 5. Combining rules with and, or, and negate

```java
Predicate<Integer> adult = age -> age >= 18;
Predicate<Integer> belowRetirement = age -> age < 65;
Predicate<Integer> workingAge = adult.and(belowRetirement);

System.out.println(workingAge.test(25)); // true
System.out.println(workingAge.test(70)); // false

Predicate<Integer> child = age -> age < 12;
Predicate<Integer> senior = age -> age >= 65;
Predicate<Integer> discounted = child.or(senior);
System.out.println(discounted.test(70)); // true

Predicate<Integer> notAdult = adult.negate();
System.out.println(notAdult.test(16)); // true
```

### Short-circuiting

- `a.and(b)` skips `b` when `a` is false.
- `a.or(b)` skips `b` when `a` is true.
- If an evaluated predicate throws, the exception propagates and later rules do not run.
- Passing null to `and` or `or` throws during composition, even if a later test could have short-circuited.

Prefer inexpensive guards before costly checks when this preserves intended semantics.

### Grouping changes the result

```java
Predicate<Integer> positive = n -> n > 0;
Predicate<Integer> even = n -> n % 2 == 0;
Predicate<Integer> special = n -> n == -1;

Predicate<Integer> first = positive.and(even).or(special);
Predicate<Integer> second = positive.and(even.or(special));
System.out.println(first.test(-1));  // true: (positive AND even) OR special
System.out.println(second.test(-1)); // false: positive AND (even OR special)
```

Chained method calls group according to their call structure, not the precedence of hypothetical `&&` and `||` operators. Name intermediate rules when grouping becomes difficult to read.

## 6. Equality with Predicate.isEqual

```java
Predicate<String> isJava = Predicate.isEqual("Java");
System.out.println(isJava.test("Java")); // true
System.out.println(isJava.test("java")); // false
System.out.println(isJava.test(null));   // false

Predicate<String> isNull = Predicate.isEqual(null);
System.out.println(isNull.test(null)); // true
```

Equality follows `Objects.equals`, so a null target is allowed. It is not a case-insensitive comparison or a deep array-content comparison. For arrays, write a rule using a suitable `Arrays.equals` overload.

## 7. Negation with Predicate.not (Java 11+)

```java
List<String> words = Arrays.asList("Java", "", "Streams");
List<String> nonEmpty = words.stream()
        .filter(Predicate.not(String::isEmpty))
        .collect(Collectors.toList());
System.out.println(nonEmpty); // [Java, Streams]
```

Java 8 alternative:

```java
Predicate<String> empty = String::isEmpty;
Predicate<String> nonEmpty = empty.negate();
```

`Predicate.not` is particularly readable with method references. Neither version automatically handles null elements.

## 8. Predicates with collections

### Remove matching elements

```java
List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
boolean changed = numbers.removeIf(n -> n % 2 == 0);
System.out.println(numbers); // [1, 3, 5]
System.out.println(changed); // true
```

`removeIf` removes elements for which the predicate returns true. Use a collection that supports removal; fixed-size or unmodifiable collections can reject it. This operation mutates the original collection.

### Check membership against a set

```java
Set<String> allowed = new HashSet<>(Arrays.asList("ADMIN", "EDITOR"));
Predicate<String> allowedRole = allowed::contains;
System.out.println(allowedRole.test("ADMIN")); // true
```

This predicate observes the set's current contents. If the set later changes, results can change too. Capture an intentional snapshot when stable rules are required.

## 9. Predicates with streams

### Filter without changing the source list

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
Predicate<Integer> even = n -> n % 2 == 0;
List<Integer> result = numbers.stream()
        .filter(even)
        .collect(Collectors.toList());
System.out.println(result);  // [2, 4, 6]
System.out.println(numbers); // [1, 2, 3, 4, 5, 6]
```

### anyMatch, allMatch, and noneMatch

```java
System.out.println(numbers.stream().anyMatch(even));  // true
System.out.println(numbers.stream().allMatch(even));  // false
System.out.println(numbers.stream().noneMatch(even)); // false
```

These terminal operations can short-circuit. On an empty stream, `anyMatch` returns false, while `allMatch` and `noneMatch` return true; the predicate is not evaluated.

### Find the first match

```java
Optional<Integer> firstEven = numbers.stream().filter(even).findFirst();
System.out.println(firstEven.orElse(-1)); // 2
```

The first match follows encounter order where the stream has one. Use `Optional` to represent the possibility of no match.

### Partition into true and false groups

```java
Map<Boolean, List<Integer>> groups = numbers.stream()
        .collect(Collectors.partitioningBy(even));
System.out.println(groups.get(true));  // [2, 4, 6]
System.out.println(groups.get(false)); // [1, 3, 5]
```

Stream predicates should generally be stateless and must not interfere with the source. Intermediate operations are lazy; do not depend on a particular number of predicate invocations or use them for required logging or external updates. Parallel processing makes shared mutation especially hazardous.

## 10. Optional.filter

```java
Predicate<String> longEnough = text -> text.length() >= 5;
Optional<String> accepted = Optional.of("Grace").filter(longEnough);
System.out.println(accepted.orElse("Rejected")); // Grace

Optional<String> rejected = Optional.of("Ada").filter(longEnough);
System.out.println(rejected.orElse("Rejected")); // Rejected
```

An empty `Optional` stays empty without testing an input. A present value remains present only when the predicate passes.

## 11. Real-world object validation

```java
class Product {
    private final String name;
    private final double price;
    private final boolean inStock;

    Product(String name, double price, boolean inStock) {
        this.name = name;
        this.price = price;
        this.inStock = inStock;
    }

    String getName() { return name; }
    double getPrice() { return price; }
    boolean isInStock() { return inStock; }
}

// Inside a method:
Predicate<Product> nonNull = Objects::nonNull;
Predicate<Product> inStock = Product::isInStock;
Predicate<Product> affordable = product -> product.getPrice() <= 100.0;
Predicate<Product> eligible = nonNull.and(inStock).and(affordable);

Product keyboard = new Product("Keyboard", 75.0, true);
System.out.println(eligible.test(keyboard)); // true
System.out.println(eligible.test(null));     // false
```

This is a selection rule, not complete product validation. Validate negative prices and other domain constraints separately. For real monetary calculations, choose a suitable representation such as `BigDecimal` rather than assuming binary floating-point represents decimal amounts exactly.

## 12. BiPredicate: conditions with two inputs

```java
BiPredicate<String, String> startsWith = String::startsWith;
System.out.println(startsWith.test("Java", "Ja")); // true

BiPredicate<String, Integer> atLeastLength = (text, minimum) ->
        text != null && minimum != null && text.length() >= minimum;
System.out.println(atLeastLength.test("Java", 4)); // true
```

`BiPredicate<T, U>` also supports `and`, `or`, and `negate`. It is a separate interface, not a subtype of `Predicate`. Turn it into a one-input rule by fixing an argument with a lambda:

```java
Predicate<String> atLeastFour = text -> atLeastLength.test(text, 4);
```

## 13. Primitive predicate variants

```java
IntPredicate even = n -> n % 2 == 0;
LongPredicate positiveId = id -> id > 0L;
DoublePredicate finitePositive = value -> Double.isFinite(value) && value > 0;

System.out.println(even.test(8));                    // true
System.out.println(positiveId.test(10L));            // true
System.out.println(finitePositive.test(Double.NaN)); // false

int total = IntStream.rangeClosed(1, 6).filter(even).sum();
System.out.println(total); // 12
```

These avoid boxed numeric inputs and have their own `and`, `or`, and `negate` methods. They are separate types from `Predicate<Integer>`, `Predicate<Long>`, and `Predicate<Double>`. Adapt explicitly when needed:

```java
Predicate<Integer> boxedEven = even::test; // Unboxes; null would throw
```

Use an explicit tolerance or a domain-specific rule for approximate floating-point comparisons rather than assuming exact `==` is always appropriate.

## 14. Returning and accepting predicates

Predicate factories make configurable rules reusable. Put these methods inside a class:

```java
static Predicate<Integer> atLeast(int minimum) {
    return value -> value >= minimum;
}

static <T> List<T> select(List<T> values, Predicate<? super T> rule) {
    Objects.requireNonNull(values, "values");
    Objects.requireNonNull(rule, "rule");
    List<T> result = new ArrayList<>();
    for (T value : values) {
        if (rule.test(value)) {
            result.add(value);
        }
    }
    return result;
}

// Inside main:
List<Integer> selected = select(Arrays.asList(10, 18, 25), atLeast(18));
System.out.println(selected); // [18, 25]
```

`Predicate<? super T>` lets a rule accepting `T` or a supertype of `T` be used. For example, a `Predicate<Object>` can test strings. The captured `minimum` is effectively final. Element null handling is determined by the supplied rule.

## 15. Regex predicates

Compile a reusable pattern once when testing many strings:

```java
Pattern digits = Pattern.compile("[0-9]+"); // java.util.regex.Pattern
Predicate<String> containsDigits = digits.asPredicate(); // Java 8+: find()
System.out.println(containsDigits.test("abc123")); // true
```

Java 11+ provides a whole-input alternative:

```java
Predicate<String> onlyDigits = digits.asMatchPredicate(); // matches()
System.out.println(onlyDigits.test("abc123")); // false
System.out.println(onlyDigits.test("123"));    // true
```

In Java 8, use `text -> digits.matcher(text).matches()` for a whole-input match. These examples require non-null input unless guarded.

## 16. Complete runnable example

Save as `PredicateDemo.java`. This program works on Java 8+.

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PredicateDemo {
    static Predicate<Integer> atLeast(int minimum) {
        return value -> value >= minimum;
    }

    public static void main(String[] args) {
        Predicate<Integer> even = n -> n % 2 == 0;
        Predicate<Integer> eligible = atLeast(4).and(even);
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> selected = numbers.stream()
                .filter(eligible)
                .collect(Collectors.toList());
        System.out.println(selected);

        Predicate<String> nonNull = Objects::nonNull;
        Predicate<String> nonBlank = text -> !text.trim().isEmpty();
        Predicate<String> usable = nonNull.and(nonBlank);
        List<String> names = Arrays.asList(null, "", "  ", "Ada", "Grace");
        System.out.println(names.stream().filter(usable)
                .collect(Collectors.toList()));

        List<Integer> mutable = new ArrayList<>(numbers);
        mutable.removeIf(even);
        System.out.println(mutable);

        Map<Boolean, List<Integer>> groups = numbers.stream()
                .collect(Collectors.partitioningBy(even));
        System.out.println(groups.get(true));
        System.out.println(groups.get(false));

        Predicate<String> isJava = Predicate.isEqual("Java");
        System.out.println(isJava.test("Java"));
        System.out.println(isJava.test(null));

        IntPredicate primitiveEven = n -> n % 2 == 0;
        System.out.println(IntStream.rangeClosed(1, 6)
                .filter(primitiveEven).sum());
    }
}
```

Compile and run:

```shell
javac PredicateDemo.java
java PredicateDemo
```

Expected output:

```text
[4, 6]
[Ada, Grace]
[1, 3, 5]
[2, 4, 6]
[1, 3, 5]
true
false
12
```

## 17. Common mistakes and limits

- Call `test`, not `apply` or `accept`, to invoke a predicate.
- Guard null before dereferencing or unboxing. Predicates do not automatically reject null safely.
- Do not confuse `filter` (keeps true matches) with `removeIf` (removes true matches).
- Negating a throwing rule does not make it safe: the exception still propagates.
- `Predicate.test` declares no checked exceptions. Handle them inside the rule, use a named wrapper, or define a suitable throwing interface.
- Captured local variables must remain final or effectively final; captured objects may still be mutable.
- `and` and `or` accept null inputs only as far as their component rules do; a null component predicate itself is invalid.
- A single boolean does not explain validation failures. Use structured validation results when callers need multiple error messages.
- Prefer named intermediate rules over deeply nested conditions. Do not hide required side effects inside a condition.

## 18. Practice exercises

1. Write an `IntPredicate` accepting integers divisible by both 3 and 5. Test `15`, `9`, and `0`.
2. Create a null-safe rule accepting strings from 3 to 10 characters long.
3. Combine age predicates into an inclusive range from 18 to 60; test both boundaries.
4. Filter null and blank names, then partition the remaining names by length greater than 4.
5. Remove negative values from a mutable list without changing the positive values or zero.
6. Implement `between(int minimum, int maximum)` returning a predicate; define behavior when minimum exceeds maximum.
7. Create a `BiPredicate<String, String>` for a null-safe, case-insensitive comparison. Decide whether two nulls should match.
8. Use `anyMatch`, `allMatch`, and `noneMatch` on both a populated and an empty stream; predict all results first.
9. Compare `Pattern.asPredicate()` with whole-input matching on `abc123` and `123`.
10. Explain why `Predicate<Integer> positive = n -> n > 0; positive.test(null);` throws.

**Hints:** 1. `n % 3 == 0 && n % 5 == 0` accepts zero; 2. null-check first; 3. use `>=` and `<=`; 5. `removeIf`; 6. reject an invalid range or document another policy; 7. handle null before `equalsIgnoreCase`; 8. empty-stream results are false, true, true; 10. null unboxing.

## Completion checklist

- [ ] I can write and invoke a predicate with `test`.
- [ ] I understand `and`, `or`, negation, and short-circuiting.
- [ ] I can use null-safe equality and validation rules.
- [ ] I can filter, remove, match, find, and partition using predicates.
- [ ] I can choose between Predicate, BiPredicate, and primitive variants.
- [ ] I can pass and return reusable rules.
- [ ] I understand Java 8 versus Java 11 API differences.
- [ ] I can explain the runnable example and its edge cases.

## Official references

- [Predicate API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/Predicate.html) — method contracts and version information.
- [BiPredicate API](https://docs.oracle.com/javase/8/docs/api/java/util/function/BiPredicate.html) — two-input conditions.
- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html) — filtering and match operations.
- [Pattern API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/regex/Pattern.html) — regex predicate adapters.

---

[← 152. Method references](152_Method_references.md) | [Course Index](../README.md) | [154. Function →](154_Function.md)
