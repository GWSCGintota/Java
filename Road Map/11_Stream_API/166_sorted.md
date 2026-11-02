# 166. sorted()

[Coding practice →](../../code_practice/11_Stream_API/166_sorted.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Sort by natural order or a composed Comparator.
- Define tie, null, and encounter-order policies.
- Explain sorting costs and its interaction with limits and unbounded sources.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. Natural and custom order

`sorted()` is a stateful intermediate operation using natural order. Elements must be mutually comparable. `sorted(comparator)` uses an explicit ordering and can handle types without a natural order.

```java
System.out.println(Stream.of(4, 1, 3, 1).sorted()
        .collect(Collectors.toList())); // [1, 1, 3, 4]
System.out.println(Stream.of(4, 1, 3).sorted(Comparator.reverseOrder())
        .collect(Collectors.toList())); // [4, 3, 1]
```

Sorting does not remove duplicates and does not sort the backing collection in place. Use `List.sort` or `Arrays.sort` when mutation is the intended operation.

## 2. Keys and tie-breakers

```java
Comparator<String> byLengthThenName = Comparator.comparingInt(String::length)
        .thenComparing(Comparator.naturalOrder());
System.out.println(Stream.of("Bob", "Grace", "Ada", "Alan")
        .sorted(byLengthThenName).collect(Collectors.toList()));
// [Ada, Bob, Alan, Grace]
```

For objects, substitute getter references such as `Employee::getAge` and `Employee::getName`. Use `comparingInt`, `comparingLong`, or `comparingDouble` for primitive keys. Avoid subtraction-based comparisons, which can overflow.

`comparator.reversed()` reverses the whole comparator built so far. To reverse only the primary key, reverse it before adding the secondary comparator. A tie policy is part of the domain contract, not merely formatting.

## 3. Nulls, stability, and primitive streams

```java
Comparator<String> order = Comparator.nullsLast(Comparator.naturalOrder());
System.out.println(Arrays.asList("Bob", null, "Ada").stream().sorted(order)
        .collect(Collectors.toList())); // [Ada, Bob, null]
System.out.println(Arrays.toString(IntStream.of(3, 1, 2).sorted().toArray()));
// [1, 2, 3]
```

Natural-order sorting does not provide a usable null policy; supply one or remove nulls first. Ordered streams have stable sorting: comparator-equal elements retain encounter order. There is no stability guarantee for unordered streams. Primitive streams provide natural ascending `sorted()` but not a comparator overload; boxing can enable another ordering at an allocation cost.

## 4. Full-input work and operation order

General sorting must establish an order across the finite input before returning its first sorted element. It typically needs buffering and O(n log n) comparison work; exact implementation behavior is not the API contract.

```java
List<Integer> values = Arrays.asList(9, 1, 8, 2);
System.out.println(values.stream().sorted().limit(2)
        .collect(Collectors.toList())); // [1, 2]
System.out.println(values.stream().limit(2).sorted()
        .collect(Collectors.toList())); // [1, 9]
```

Sorting before a limit selects from the whole source, while limiting first changes the candidate set. An unbounded stream generally cannot supply a globally sorted first result. Use `min` or `max` when only one extreme value is needed, and consider a bounded top-k algorithm for large inputs rather than assuming sort-plus-limit avoids full-input work.

## 5. Complete runnable example

Save as `StreamLesson166.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson166 {
    public static void main(String[] args) throws Exception {
        List<String> names = Arrays.asList("Bob", "Grace", "Ada", "Alan");
        Comparator<String> order = Comparator.comparingInt(String::length)
                .thenComparing(Comparator.naturalOrder());
        System.out.println(names.stream().sorted(order).collect(Collectors.toList()));
        System.out.println(names);
        System.out.println(Arrays.asList("Bob", null, "Ada").stream()
                .sorted(Comparator.nullsLast(Comparator.naturalOrder()))
                .collect(Collectors.toList()));
        System.out.println(Stream.of(9, 1, 8, 2).sorted().limit(2)
                .collect(Collectors.toList()));
    }
}
```

Compile and run:

```shell
javac StreamLesson166.java
java StreamLesson166
```

Expected output:

```text
[Ada, Bob, Alan, Grace]
[Bob, Grace, Ada, Alan]
[Ada, Bob, null]
[1, 2]
```

## 6. Common mistakes

| Mistake | Better approach |
|---|---|
| Expecting sorted to mutate the source | Collect a separate result or use a collection sorting operation. |
| Subtracting numeric comparator keys | Use safe comparator factories. |
| Ignoring null values | Filter them or define a null-aware comparator. |
| Assuming limit makes a preceding sort cheap | Sorting may need the whole finite input. |
| Using sorted().findFirst() for a minimum | Prefer min with the required comparator. |

## 7. Practice exercises

1. Sort integers descending. **Hint:** Comparator.reverseOrder for boxed values.
2. Sort people by age, then name. **Hint:** comparingInt then thenComparing.
3. Put null names last. **Hint:** Comparator.nullsLast.
4. Explain sorted().limit(2) versus limit(2).sorted(). **Hint:** Use a source whose first two values are not its smallest.
5. Find the longest name without fully sorting. **Hint:** max with a length comparator.

## 8. Interview questions and answers

### 1. Is stream sorting stable?

For ordered streams, comparator-equal elements retain encounter order. Unordered streams have no such stability guarantee.

### 2. Does sorted remove duplicates?

No. Equality-based duplicate removal is distinct, a separate operation.

### 3. Why can sorted prevent short-circuiting from finishing on infinite input?

Global order cannot generally be determined without exhausting an unbounded source.

## Completion checklist

- [ ] I can choose natural order or a comparator.
- [ ] I can define tie and null policies.
- [ ] I can distinguish result ordering from source mutation.
- [ ] I can explain buffering and sort/limit ordering.

## Official references

- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)
- [Stream package guide](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html)

---

[← 165. forEach()](165_forEach.md) | [Course Index](../README.md) | [167. distinct() →](167_distinct.md)
