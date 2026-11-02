# 170. reduce()

[Coding practice →](../../code_practice/11_Stream_API/170_reduce.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Choose among the three reduce overloads.
- Verify neutral identities, associativity, and accumulator/combiner compatibility.
- Handle empty results and distinguish immutable reduction from mutable collection.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. What reduction does

A reduction combines elements into one summary. `reduce` is terminal and commonly used for sums, products, or immutable domain summaries. Prefer specialized terminals such as `sum`, `min`, or `max` when they express the intent directly.

| Overload | Result | Empty input |
|---|---|---|
| `reduce(identity, operator)` | `T` | Identity |
| `reduce(operator)` | `Optional<T>` | Empty optional |
| `reduce(identity, accumulator, combiner)` | `U` | Identity |

```java
List<Integer> values = Arrays.asList(2, 3, 4);
System.out.println(values.stream().reduce(0, Integer::sum)); // 9
System.out.println(values.stream().reduce(1, (a, b) -> a * b)); // 24
System.out.println(Stream.<Integer>empty().reduce(0, Integer::sum)); // 0
```

## 2. Neutral identity and associativity

An identity must leave values unchanged under the combining rule: zero for addition, one for multiplication, and an empty string for concatenation. Starting an addition reduction at ten is not a valid way to request a portable parallel sum plus ten. Compute the valid sum first, then add the offset.

An operator must be associative: regrouping must preserve the result. Addition for the small exact integers here is associative. Subtraction fails: `(10 - 3) - 2` is five, while `10 - (3 - 2)` is nine. Use an explicit loop for an intentionally sequential subtraction.

Associativity differs from commutativity. String concatenation is associative while its operand order still matters. Floating-point addition can round differently under regrouping, so parallel numeric results may differ from a sequential calculation. Exact-arithmetic overflow policies can also affect which groupings succeed.

## 3. No natural identity and optional results

```java
Optional<Integer> largest = Stream.of(3, 9, 4).reduce(Integer::max);
System.out.println(largest.orElse(-1)); // 9
System.out.println(Stream.<Integer>empty().reduce(Integer::max).isPresent()); // false
```

This avoids inventing a maximum for empty input. A null final result cannot be represented by this optional-returning reduction and causes a failure. Define element and result null policies before choosing a reduction. A fallback such as minus one is only a display policy, not a universal maximum identity.

## 4. Three-argument reduction

```java
int totalLength = Stream.of("Ada", "Grace").parallel()
        .reduce(0,
                (total, name) -> total + name.length(),
                Integer::sum);
System.out.println(totalLength); // 8
```

The accumulator combines a `U` partial result with a `T` source element; the combiner merges two `U` partial results. Their rules must agree: combining a partial result with the accumulation of an element from the identity must equal directly accumulating that element into the partial result. The combiner needs an identity and associative behavior too.

For this calculation, `mapToInt(String::length).sum()` is simpler. The three-argument form is useful when a fused immutable mapping/reduction is appropriate and its algebraic contracts are clear.

## 5. Why mutable containers belong in collect

Do not use one mutable `ArrayList` or `StringBuilder` as a reduction identity and mutate it in the accumulator. Multiple partial reductions can share that identity object, breaking independence and the identity contract.

```java
String joined = Stream.of("Ada", "Grace").collect(Collectors.joining(", "));
System.out.println(joined); // Ada, Grace
```

`collect` has a fresh-container factory and an explicit merging model for mutable accumulation. `reduce` works well when values are combined without mutating a shared identity. Neither API automatically makes arbitrary callbacks pure, atomic, or safe to retry.

## 6. Complete runnable example

Save as `StreamLesson170.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson170 {
    public static void main(String[] args) throws Exception {
        List<Integer> values = Arrays.asList(2, 3, 4);
        System.out.println(values.stream().reduce(0, Integer::sum));
        System.out.println(values.stream().reduce(1, (a, b) -> a * b));
        System.out.println(Stream.<Integer>empty().reduce(0, Integer::sum));
        System.out.println(Stream.<Integer>empty().reduce(Integer::max).isPresent());
        System.out.println(Stream.of("Ada", "Grace").parallel()
                .reduce(0, (total, name) -> total + name.length(), Integer::sum));
        System.out.println(Stream.of("Ada", "Grace").collect(Collectors.joining(", ")));
    }
}
```

Compile and run:

```shell
javac StreamLesson170.java
java StreamLesson170
```

Expected output:

```text
9
24
0
false
8
Ada, Grace
```

## 7. Common mistakes

| Mistake | Better approach |
|---|---|
| Using a non-neutral starting offset | Reduce with a true identity, then apply the offset. |
| Reducing with subtraction or division | Use an associative operation or an explicit sequential algorithm. |
| Ignoring accumulator/combiner compatibility | Verify that partial results merge under the same rule. |
| Mutating one shared identity object | Use collect with fresh containers. |
| Assuming floating-point regrouping is exact | Define acceptable numerical accuracy and test the intended execution. |

## 8. Practice exercises

1. Reduce [2, 3, 4] to sum and product. **Hint:** Use identities zero and one.
2. Find a maximum without an invented identity. **Hint:** reduce(BinaryOperator) returns Optional.
3. Show subtraction's regrouping mismatch. **Hint:** Evaluate both parenthesizations explicitly.
4. Sum string lengths with both three-argument reduce and mapToInt. **Hint:** Compare equivalent results.
5. Combine immutable count-and-total summaries. **Hint:** Create a new summary per combine and use a neutral zero summary.

## 9. Interview questions and answers

### 1. Why must the identity be neutral?

Parallel partitions may begin from it independently. A non-neutral identity can be applied repeatedly and corrupt the result.

### 2. Why is the combiner needed in the three-argument overload?

It merges independently produced partial results, which can have a different type from source elements.

### 3. When should collect replace reduce?

When the desired computation mutates accumulation containers that need separate creation and controlled merging.

## Completion checklist

- [ ] I can choose the appropriate reduce overload.
- [ ] I can verify identity and associativity.
- [ ] I can explain empty and null-result policies.
- [ ] I can distinguish compatible immutable reduction from mutable collection.

## Official references

- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)
- [Stream package guide](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html)

---

[← 169. skip()](169_skip.md) | [Course Index](../README.md) | [171. collect() →](171_collect.md)
