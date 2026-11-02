[← 169. skip()](169_skip.md) | [Course Index](../README.md) | [171. collect() →](171_collect.md)

# Java reduce() Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/170_reduce.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — Sum with an identity

**Task:** Reduce [2, 3, 4] to its sum with a neutral identity.

```java
System.out.println(Stream.of(2, 3, 4).reduce(0, Integer::sum));
```

**Output:**
```text
9
```

**Concept:** Zero is neutral for addition.

## Question 2 — Product with an identity

**Task:** Reduce [2, 3, 4] to its product.

```java
System.out.println(Stream.of(2, 3, 4).reduce(1, (a, b) -> a * b));
```

**Output:**
```text
24
```

**Concept:** Multiplication requires identity one rather than zero.

## Question 3 — Maximum without an invented value

**Task:** Find the maximum of [-5, -2, -9] with the identity-free reduce overload.

```java
Optional<Integer> maximum = Stream.of(-5, -2, -9).reduce(Integer::max);
System.out.println(maximum.orElseThrow(() -> new IllegalStateException("Empty")));
```

**Output:**
```text
-2
```

**Concept:** Identity-free reduction represents absence with Optional instead of inventing zero as a maximum.

## Question 4 — Empty reductions

**Task:** Print empty sum and product identities, then whether an empty maximum is present.

```java
System.out.println(Stream.<Integer>empty().reduce(0, Integer::sum));
System.out.println(Stream.<Integer>empty().reduce(1, (a, b) -> a * b));
System.out.println(Stream.<Integer>empty().reduce(Integer::max).isPresent());
```

**Output:**
```text
0
1
false
```

**Concept:** Empty behavior depends on the chosen overload and identity.

## Question 5 — Ordered concatenation

**Task:** Reduce [Java, space, streams] into one string using an empty-string identity.

```java
System.out.println(Stream.of("Java", " ", "streams").reduce("", String::concat));
```

**Output:**
```text
Java streams
```

**Concept:** Concatenation is associative while operand order still matters; joining is usually clearer for many strings.

## Question 6 — Disprove subtraction associativity

**Task:** Use a subtraction operator to compare (10-3)-2 with 10-(3-2).

```java
BinaryOperator<Integer> subtract = (a, b) -> a - b;
System.out.println(subtract.apply(subtract.apply(10, 3), 2));
System.out.println(subtract.apply(10, subtract.apply(3, 2)));
```

**Output:**
```text
5
9
```

**Concept:** A general stream reduction requires associative behavior, which subtraction lacks.

## Question 7 — Different accumulation type

**Task:** Sum lengths of Ada and Grace using three-argument reduce on a parallel stream.

```java
int total = Stream.of("Ada", "Grace").parallel()
        .reduce(0, (partial, name) -> partial + name.length(), Integer::sum);
System.out.println(total);
```

**Output:**
```text
8
```

**Concept:** The accumulator combines an Integer partial result with a String; the combiner merges compatible Integer partials.

## Question 8 — Apply an offset after reduction

**Task:** Calculate the parallel sum of [1, 2, 3] plus ten using a valid reduction identity.

```java
int sum = Stream.of(1, 2, 3).parallel().reduce(0, Integer::sum);
System.out.println(sum + 10);
```

**Output:**
```text
16
```

**Concept:** A starting offset is not a neutral identity and belongs outside the reduction.

## Question 9 — Immutable summary reduction

**Task:** Combine summaries (2 items, total 30) and (3 items, total 45) without mutating either input.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {
    static final class Summary {
        final int count, total;
        Summary(int count, int total) { this.count=count; this.total=total; }
        Summary combine(Summary other) { return new Summary(count+other.count, total+other.total); }
        @Override public String toString() { return count + ":" + total; }
    }
    public static void main(String[] args) throws Exception {
        Summary first = new Summary(2, 30), second = new Summary(3, 45);
        Summary result = Stream.of(first, second).parallel().reduce(new Summary(0, 0), Summary::combine);
        System.out.println(result);
        System.out.println(first);
        System.out.println(second);
    }
}
```

**Output:**
```text
5:75
2:30
3:45
```

**Concept:** Immutable combination avoids sharing a mutable identity object.

## Question 10 — Choose collect for mutable output

**Task:** Join Ada and Grace with a comma without mutating a StringBuilder reduction identity.

```java
System.out.println(Stream.of("Ada", "Grace").parallel().collect(Collectors.joining(", ")));
```

**Output:**
```text
Ada, Grace
```

**Concept:** Collector container creation and merging fit mutable accumulation better than a shared reduce identity.

---

## Interview Questions and Answers

### Interview Question 1 — Why must the identity be neutral?

**Answer:** Parallel partitions may begin from it independently. A non-neutral identity can be applied repeatedly and corrupt the result.

### Interview Question 2 — Why is the combiner needed in the three-argument overload?

**Answer:** It merges independently produced partial results, which can have a different type from source elements.

### Interview Question 3 — When should collect replace reduce?

**Answer:** When the desired computation mutates accumulation containers that need separate creation and controlled merging.

### Interview Question 4 — Is associativity the same as commutativity?

**Answer:** No. Associativity permits regrouping, while commutativity permits swapping operands. String concatenation is associative but generally not commutative.

### Interview Question 5 — Can floating-point addition change under parallel regrouping?

**Answer:** Yes. Rounding can make different groupings produce different results. Define the numerical accuracy requirements rather than assuming exact real arithmetic.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| Sum with an identity | Zero is neutral for addition. |
| Product with an identity | Multiplication requires identity one rather than zero. |
| Maximum without an invented value | Identity-free reduction represents absence with Optional instead of inventing zero as a maximum. |
| Empty reductions | Empty behavior depends on the chosen overload and identity. |
| Ordered concatenation | Concatenation is associative while operand order still matters; joining is usually clearer for many strings. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/170_reduce.md)

[← 169. skip()](169_skip.md) | [Course Index](../README.md) | [171. collect() →](171_collect.md)
