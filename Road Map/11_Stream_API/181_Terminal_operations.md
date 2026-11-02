# 181. Terminal operations

[Coding practice →](../../code_practice/11_Stream_API/181_Terminal_operations.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Select terminal operations by result shape and empty-input policy.
- Use short-circuiting searches and matching correctly.
- Handle single use, null results, and resource lifetimes.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. Terminals initiate a result computation

A terminal operation ends a pipeline and returns a value, container, optional, array, or `void` action. The stream cannot then be reused. A fresh source stream is needed for each independent calculation.

| Goal | Terminal operation | Result |
|---|---|---|
| Count elements | `count` | `long` |
| Combine values | `reduce` | Value or optional |
| Build a result | `collect` | Collector-defined result |
| Build an array | `toArray` | Array |
| Find an element | `findFirst`, `findAny` | `Optional<T>` |
| Test conditions | `anyMatch`, `allMatch`, `noneMatch` | `boolean` |
| Find an extreme | `min`, `max` | `Optional<T>` |
| Perform actions | `forEach`, `forEachOrdered` | `void` |

Primitive streams additionally offer `sum`, `average`, and `summaryStatistics`. **Java 16+:** `Stream.toList()` is a terminal operation with an unmodifiable-result contract.

## 2. Containers, arrays, and numeric results

```java
List<String> names = Arrays.asList("Ada", "Grace");
System.out.println(names.stream().collect(Collectors.joining(", ")));
// Ada, Grace
String[] array = names.stream().toArray(String[]::new);
System.out.println(Arrays.toString(array)); // [Ada, Grace]
System.out.println(names.stream().mapToInt(String::length).sum()); // 8
```

`toArray()` without a generator on a reference stream returns `Object[]`; do not cast that array to `String[]`. Use an array constructor reference. Numeric sum identities return zero on empty input, while averages and optional min/max results represent no observed value as absence.

## 3. Search and matching

```java
List<Integer> values = Arrays.asList(1, 3, 4, 6);
System.out.println(values.stream().filter(n -> n % 2 == 0).findFirst().orElse(-1)); // 4
System.out.println(values.stream().anyMatch(n -> n > 5)); // true
System.out.println(values.stream().allMatch(n -> n > 0)); // true
System.out.println(values.stream().noneMatch(n -> n < 0)); // true
```

These terminals may short-circuit when enough information is available. `findFirst` follows encounter order when defined. `findAny` permits any element, which can be useful for parallel execution; it does not promise a random or different result.

Use `anyMatch` instead of counting every matching element merely to ask whether a match exists. A short-circuiting terminal does not guarantee that every unbounded pipeline can finish; upstream must be able to produce the needed evidence.

## 4. Empty and null policies

```java
System.out.println(Stream.<Integer>empty().anyMatch(n -> n > 0));  // false
System.out.println(Stream.<Integer>empty().allMatch(n -> n > 0));  // true
System.out.println(Stream.<Integer>empty().noneMatch(n -> n > 0)); // true
System.out.println(Stream.<Integer>empty().findFirst().isPresent()); // false
```

`allMatch` and `noneMatch` are true on empty input because no counterexample exists; the predicate is not evaluated. Decide whether your domain additionally requires non-empty input.

A null element selected by `findFirst` or `findAny` cannot become a present `Optional` and causes a failure. Similarly, a null selected minimum is not an empty result. Filter nulls or define a different representation before those terminals. Do not call `Optional.get()` without an established presence guarantee.

## 5. Traversal, closure, and side effects

A terminal operation need not execute every upstream callback: matching and searching can stop early, and `count` may use known size when transformations are irrelevant. Required business effects do not belong in intermediate observers.

`forEachOrdered` provides encounter-ordered terminal actions when such an order exists, whereas parallel `forEach` does not. Neither operation provides rollback for effects already performed when a later action fails.

Completion does not automatically close I/O sources such as `Files.lines`. Use try-with-resources even when the terminal short-circuits. `iterator()` and `spliterator()` are terminal escape hatches that allow caller-controlled traversal; they do not mean that every element was eagerly consumed at the call.

## 6. Complete runnable example

Save as `StreamLesson181.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson181 {
    public static void main(String[] args) throws Exception {
        List<Integer> values = Arrays.asList(1, 3, 4, 6);
        System.out.println(values.stream().count());
        System.out.println(values.stream().mapToInt(Integer::intValue).sum());
        System.out.println(values.stream().filter(n -> n % 2 == 0).findFirst().orElse(-1));
        System.out.println(values.stream().anyMatch(n -> n > 5));
        System.out.println(Stream.<Integer>empty().allMatch(n -> n > 0));
        System.out.println(Stream.<Integer>empty().noneMatch(n -> n > 0));
        String[] names = Stream.of("Ada", "Grace").toArray(String[]::new);
        System.out.println(Arrays.toString(names));
    }
}
```

Compile and run:

```shell
javac StreamLesson181.java
java StreamLesson181
```

Expected output:

```text
4
14
4
true
true
true
[Ada, Grace]
```

## 7. Common mistakes

| Mistake | Better approach |
|---|---|
| Reusing a consumed stream for a second terminal | Create a fresh pipeline. |
| Calling Optional.get on a possibly empty result | Handle absence explicitly. |
| Expecting allMatch on empty input to be false | Understand its no-counterexample behavior. |
| Casting Object[] from toArray to String[] | Supply String[]::new. |
| Assuming terminal completion closes a file source | Use try-with-resources. |

## 8. Practice exercises

1. Count, sum, and find the maximum of one list using fresh streams. **Hint:** Build three independent pipelines.
2. Find the first non-null matching name. **Hint:** Guard before findFirst.
3. Predict all three matching operations on empty input. **Hint:** Any is false; all and none are true.
4. Convert names into a typed array. **Hint:** toArray(String[]::new).
5. Explain why count is a poor trigger for required peek effects. **Hint:** The implementation may answer without invoking that observer.

## 9. Interview questions and answers

### 1. Does every terminal consume every element?

No. Short-circuiting, legal optimizations, and traversal escape hatches have different consumption behavior.

### 2. Why is allMatch true for an empty stream?

There is no element that violates the predicate; this is vacuous truth, not proof that at least one matching element exists.

### 3. Does a terminal operation close its source?

Not automatically. Resource ownership must be handled separately, normally with try-with-resources for I/O-backed streams.

## Completion checklist

- [ ] I can select a terminal by its result contract.
- [ ] I can predict empty matching and optional results.
- [ ] I can handle typed arrays and nullable elements.
- [ ] I can distinguish terminal completion, full traversal, and resource closure.

## Official references

- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)
- [Stream package guide](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html)

---

[← 180. Intermediate operations](180_Intermediate_operations.md) | [Course Index](../README.md) | [182. null →](../12_Optional_and_Null_Handling/182_null.md)
