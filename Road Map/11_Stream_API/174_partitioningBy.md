# 174. partitioningBy()

[Coding practice →](../../code_practice/11_Stream_API/174_partitioningBy.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Split values by a Predicate into true and false partitions.
- Use downstream reductions while retaining both partition keys.
- Distinguish partitioning from filtering and general grouping.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. Exactly two categories

`Collectors.partitioningBy(predicate)` divides elements into matching and non-matching lists. Its result is `Map<Boolean, List<T>>`. Both `true` and `false` keys are present, even when a partition contains no values.

```java
Map<Boolean, List<Integer>> parity = Stream.of(1, 2, 3, 4, 5)
        .collect(Collectors.partitioningBy(n -> n % 2 == 0));
System.out.println(parity.get(true));  // [2, 4]
System.out.println(parity.get(false)); // [1, 3, 5]
```

The predicate controls membership without transforming elements. Neither the source nor its objects are copied or mutated merely by partitioning.

## 2. Count or summarize each side

```java
Map<Boolean, Long> counts = Stream.of(1, 2, 3, 4, 5)
        .collect(Collectors.partitioningBy(n -> n % 2 == 0,
                Collectors.counting()));
System.out.println(counts.get(true));  // 2
System.out.println(counts.get(false)); // 3
Map<Boolean, Integer> totals = Stream.of(1, 2, 3, 4, 5)
        .collect(Collectors.partitioningBy(n -> n % 2 == 0,
                Collectors.summingInt(Integer::intValue)));
System.out.println(totals.get(true)); // 6
```

The downstream result can be a count, total, mapped set, joined string, or another valid reduction. Both partitions are reduced using the downstream collector's empty-input policy.

## 3. Empty and one-sided inputs

```java
Map<Boolean, Long> empty = Stream.<Integer>empty().collect(
        Collectors.partitioningBy(n -> n > 0, Collectors.counting()));
System.out.println(empty.get(true));  // 0
System.out.println(empty.get(false)); // 0
Map<Boolean, List<Integer>> positive = Stream.of(1, 2).collect(
        Collectors.partitioningBy(n -> n > 0));
System.out.println(positive.get(false)); // []
```

This differs from `groupingBy(predicate::test)`, where only observed Boolean keys are normally inserted. Do not infer map iteration order from the guarantee that both keys exist; print explicit lookups when output order matters.

## 4. Choose the correct operation

| Goal | Operation |
|---|---|
| Retain only valid values | `filter(valid)` |
| Retain valid and invalid values | `partitioningBy(valid)` |
| Use many categories such as departments | `groupingBy(department)` |
| Ask whether any valid value exists | `anyMatch(valid)` |

```java
Map<Boolean, List<String>> parts = Arrays.asList("Ada", null, "Grace")
        .stream().collect(Collectors.partitioningBy(Objects::nonNull));
System.out.println(parts.get(true));  // [Ada, Grace]
System.out.println(parts.get(false)); // [null]
```

A predicate must safely handle inputs permitted by the contract. Exceptions propagate. Keep it stateless and non-interfering, especially for parallel collection. The returned map and nested containers have no general implementation or thread-safety promise.

## 5. Complete runnable example

Save as `StreamLesson174.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson174 {
    public static void main(String[] args) throws Exception {
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5);
        Map<Boolean, List<Integer>> parts = values.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println(parts.get(true));
        System.out.println(parts.get(false));
        Map<Boolean, Long> counts = values.stream().collect(
                Collectors.partitioningBy(n -> n % 2 == 0, Collectors.counting()));
        System.out.println(counts.get(true));
        System.out.println(counts.get(false));
        Map<Boolean, Long> empty = Stream.<Integer>empty().collect(
                Collectors.partitioningBy(n -> n > 0, Collectors.counting()));
        System.out.println(empty.get(true) + ":" + empty.get(false));
    }
}
```

Compile and run:

```shell
javac StreamLesson174.java
java StreamLesson174
```

Expected output:

```text
[2, 4]
[1, 3, 5]
2
3
0:0
```

## 6. Common mistakes

| Mistake | Better approach |
|---|---|
| Filtering twice when both partitions are needed | Use one partitioning reduction. |
| Assuming an empty input produces no keys | Both Boolean keys remain present. |
| Using partitioning for several department categories | Use groupingBy. |
| Dereferencing nullable inputs in the predicate | Define a null policy first. |
| Assuming deterministic printed map iteration order | Print true and false lookups explicitly. |

## 7. Practice exercises

1. Partition integers into positive and nonpositive values. **Hint:** Choose a predicate matching the exact zero boundary.
2. Count pass and fail scores. **Hint:** partitioningBy with counting.
3. Partition nullable strings safely. **Hint:** Objects::nonNull.
4. Compare empty groupingBy(Boolean key) with empty partitioningBy. **Hint:** Inspect containsKey for both Boolean values.
5. Store only names in each object partition. **Hint:** Add mapping downstream.

## 8. Interview questions and answers

### 1. Are both Boolean keys guaranteed?

Yes. A missing side is represented by an empty downstream result rather than an absent key.

### 2. How does filtering differ?

filter retains only matching elements; partitioning retains both sides and reduces each separately.

### 3. Does partitioningBy require a map factory?

It offers predicate-only and predicate-plus-downstream forms rather than the general groupingBy map-factory overload.

## Completion checklist

- [ ] I can choose a two-way partition instead of general grouping.
- [ ] I can predict empty and one-sided results.
- [ ] I can compose a downstream reduction.
- [ ] I can define safe predicates and distinguish key presence from iteration order.

## Official references

- [Collectors API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Collectors.html)
- [Collector contract](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Collector.html)
- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)

---

[← 173. groupingBy()](173_groupingBy.md) | [Course Index](../README.md) | [175. mapping() →](175_mapping.md)
