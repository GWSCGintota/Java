# 167. distinct()

[Coding practice →](../../code_practice/11_Stream_API/167_distinct.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Deduplicate using equals and hashCode rather than comparator equality.
- Explain retained order and mutable-object hazards.
- Choose a collector or key-based strategy when distinct is not the right contract.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. Equality-based uniqueness

`distinct()` is a stateful intermediate operation producing unique elements according to object equality. Correct `equals` and `hashCode` implementations matter for reference types; do not rely on object identity when the domain needs value equality.

```java
System.out.println(Stream.of(3, 1, 3, 2, 1).distinct()
        .collect(Collectors.toList())); // [3, 1, 2]
```

An ordered stream retains the first encountered representative of each equal value. For an unordered stream, no stable representative or output order is promised. `distinct` does not sort the values or alter the original collection.

## 2. Normalize before deduplicating

```java
List<String> names = Arrays.asList(" Ada ", "ada", "Grace").stream()
        .map(String::trim).map(s -> s.toLowerCase(Locale.ROOT))
        .distinct().collect(Collectors.toList());
System.out.println(names); // [ada, grace]
System.out.println(Arrays.asList("Ada", "ADA").stream()
        .sorted(String.CASE_INSENSITIVE_ORDER).distinct()
        .collect(Collectors.toList())); // [Ada, ADA]
```

Sorting with a comparator does not change the equality rule used by `distinct`. Normalize values if normalized equality is the requirement. Two strings that compare equal ignoring case can still be unequal under `String.equals`.

## 3. Objects, keys, and nulls

For custom objects, decide which fields define equality and ensure their hashes agree. Avoid changing equality-relevant fields during traversal or while objects are stored in hash-based structures. Two independently created objects remain distinct if their class inherits identity-based `Object.equals`.

```java
System.out.println(Arrays.asList(null, "Ada", null, "Ada").stream()
        .distinct().collect(Collectors.toList())); // [null, Ada]
```

If uniqueness should be by a selected key while retaining full objects, `distinct()` has no key-extractor overload. Use an explicit `toMap` key mapping and merge policy, or group values. A captured stateful `HashSet` in a filter is not a robust general replacement, particularly in parallel streams.

## 4. Costs and alternatives

Tracking seen values can require memory proportional to the number of distinct values. Ordered parallel deduplication may need coordination and buffering to preserve first-encounter semantics. Removing an order requirement with `unordered()` may permit different execution choices but also changes the allowed result order; it does not shuffle data.

```java
Set<Integer> sortedUnique = Stream.of(3, 1, 3, 2)
        .collect(Collectors.toCollection(TreeSet::new));
System.out.println(sortedUnique); // [1, 2, 3]
```

A `TreeSet` deduplicates by comparator order rather than the same contract as `distinct`. A `LinkedHashSet` is useful for an explicitly insertion-ordered set result. Deduplicating an unbounded source can retain growing state even if downstream processing is incremental.

## 5. Complete runnable example

Save as `StreamLesson167.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson167 {
    public static void main(String[] args) throws Exception {
        List<Integer> values = Arrays.asList(3, 1, 3, 2, 1);
        System.out.println(values.stream().distinct().collect(Collectors.toList()));
        System.out.println(values.stream().distinct().sorted().collect(Collectors.toList()));
        System.out.println(Stream.of(" Ada ", "ada", "Grace").map(String::trim)
                .map(s -> s.toLowerCase(Locale.ROOT)).distinct()
                .collect(Collectors.toList()));
        System.out.println(Arrays.asList(null, "Ada", null).stream()
                .distinct().collect(Collectors.toList()));
    }
}
```

Compile and run:

```shell
javac StreamLesson167.java
java StreamLesson167
```

Expected output:

```text
[3, 1, 2]
[1, 2, 3]
[ada, grace]
[null, Ada]
```

## 6. Common mistakes

| Mistake | Better approach |
|---|---|
| Assuming comparator equality drives distinct | distinct uses equality, not the sorting comparator. |
| Overriding equals without hashCode | Preserve the equality/hash contract. |
| Expecting distinct to sort | Add sorted only if the required order calls for it. |
| Using a stateful seen-set predicate in parallel | Use a proper collection or merge strategy. |
| Mutating equality fields during traversal | Keep the equality representation stable. |

## 7. Practice exercises

1. Deduplicate [2, 1, 2, 3] while preserving encounter order. **Hint:** distinct.
2. Deduplicate names after trimming and lowercase normalization. **Hint:** Normalize before distinct.
3. Compare sorted(caseInsensitive).distinct() with normalized distinct. **Hint:** Comparator equality and equals differ.
4. Build a LinkedHashSet result. **Hint:** toCollection(LinkedHashSet::new).
5. Design an employee-ID merge policy. **Hint:** toMap with a key extractor and an explicit first/last/preferred rule.

## 8. Interview questions and answers

### 1. Which duplicate is retained in an ordered stream?

The first encountered representative according to equals, with stable encounter-order behavior.

### 2. Can distinct use an arbitrary key extractor?

Not directly. Map to keys, collect by key with a merge policy, or group values depending on the desired result.

### 3. Why can ordered parallel distinct be costly?

Preserving the first encountered representative across partitions can require coordination and buffering.

## Completion checklist

- [ ] I can explain equality-based uniqueness and order.
- [ ] I can choose normalization before deduplication.
- [ ] I can distinguish distinct from comparator-based set uniqueness.
- [ ] I can reason about custom keys, state, and parallel costs.

## Official references

- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)
- [Stream package guide](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html)

---

[← 166. sorted()](166_sorted.md) | [Course Index](../README.md) | [168. limit() →](168_limit.md)
