# 171. collect()

[Coding practice →](../../code_practice/11_Stream_API/171_collect.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Collect data with predefined collectors or an explicit mutable reduction.
- Choose container, ordering, and duplicate-key policies.
- Explain fresh-container accumulation and parallel merging.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. Terminal mutable reduction

`collect` is terminal. It accumulates elements into a result such as a list, set, map, string, or grouped summary. Two main forms are available:

```text
collect(Collector<T, A, R>) → R
collect(supplier, accumulator, combiner) → R
```

In a collector, `A` is the intermediate accumulation type and `R` is the finished result type. The stream uses the collector's rules to create and merge accumulation state. You normally use factories from `Collectors` rather than implement `Collector` yourself.

## 2. Choose a container contract

```java
List<String> names = Stream.of("Ada", "Grace", "Ada")
        .collect(Collectors.toList());
Set<String> unique = Stream.of("Ada", "Grace", "Ada")
        .collect(Collectors.toCollection(LinkedHashSet::new));
System.out.println(names);  // [Ada, Grace, Ada]
System.out.println(unique); // [Ada, Grace]
```

`Collectors.toList()` does not promise a particular implementation, mutability, serializability, or thread safety. Use `toCollection(ArrayList::new)` when the mutable container type is required. `toSet()` does not promise encounter order; use an explicit ordered or sorted set factory when that matters.

## 3. Maps and duplicate values

```java
Map<String, Integer> lengths = Stream.of("Ada", "Ada", "Grace")
        .collect(Collectors.toMap(Function.identity(), String::length,
                (existing, incoming) -> existing));
System.out.println(lengths.get("Ada")); // 3
System.out.println(lengths.get("Grace")); // 5
```

Two-argument `toMap` rejects duplicate mapped keys. A merge function specifies which value to retain or how to combine values. A fourth argument can supply a map factory such as `LinkedHashMap::new`. Do not depend on default map iteration order or assume null mapped values are accepted.

## 4. Three-argument collect

```java
List<String> copy = Stream.of("Ada", "Grace").parallel().collect(
        ArrayList::new,
        List::add,
        List::addAll);
System.out.println(copy); // [Ada, Grace]
```

The supplier creates fresh containers. The accumulator mutates one container with one element. The combiner merges a second container into the first. Its return type is `void`, unlike a `BinaryOperator` used by `reduce`.

For ordinary list construction, `Collectors.toList()` is clearer. The three-argument form illustrates why parallel collection can use non-thread-safe containers safely: separate partial containers are created and merged under the reduction protocol. Returning one shared list from the supplier breaks this independence.

## 5. Finishing and newer list APIs

```java
List<String> readOnly = Stream.of("Ada", "Grace").collect(
        Collectors.collectingAndThen(Collectors.toCollection(ArrayList::new),
                Collections::unmodifiableList));
System.out.println(readOnly); // [Ada, Grace]
```

An unmodifiable container is not a deep copy of mutable elements. The wrapper here protects this privately created list from structural updates through the returned reference.

**Java 10+:** `Collectors.toUnmodifiableList()` returns an unmodifiable list and rejects null elements. **Java 16+:** `Stream.toList()` is terminal, returns an unmodifiable list, and has a different contract from `Collectors.toList()`. Do not mechanically substitute them when callers rely on mutability or null behavior. See [172. Collectors](172_Collectors.md) for collector composition.

## 6. Complete runnable example

Save as `StreamLesson171.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson171 {
    public static void main(String[] args) throws Exception {
        List<String> names = Stream.of("Ada", "Grace", "Ada")
                .collect(Collectors.toCollection(ArrayList::new));
        names.add("Alan");
        System.out.println(names);
        Set<String> unique = names.stream().collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println(unique);
        Map<String, Integer> lengths = names.stream().collect(
                Collectors.toMap(Function.identity(), String::length,
                        (existing, incoming) -> existing));
        System.out.println(lengths.get("Ada"));
        List<String> copy = Stream.of("Ada", "Grace").parallel()
                .collect(ArrayList::new, List::add, List::addAll);
        System.out.println(copy);
    }
}
```

Compile and run:

```shell
javac StreamLesson171.java
java StreamLesson171
```

Expected output:

```text
[Ada, Grace, Ada, Alan]
[Ada, Grace, Alan]
3
[Ada, Grace]
```

## 7. Common mistakes

| Mistake | Better approach |
|---|---|
| Assuming toList always returns mutable ArrayList | Use toCollection when implementation or mutability is required. |
| Ignoring duplicate map keys | Supply a deliberate merge function. |
| Returning a shared container from the supplier | Create a fresh accumulation container. |
| Using reduce with a mutable identity | Use collect's container protocol. |
| Treating unmodifiable as deeply immutable | Define element ownership separately. |

## 8. Practice exercises

1. Collect doubled integers into an explicit ArrayList. **Hint:** map and toCollection.
2. Build an encounter-ordered unique set. **Hint:** LinkedHashSet factory.
3. Count duplicate words with toMap. **Hint:** Map each word to one and merge using Integer::sum.
4. Implement a list copy with three-argument collect. **Hint:** Fresh supplier, add, addAll.
5. Return an unmodifiable Java 8 list. **Hint:** collectingAndThen and an unmodifiable wrapper.

## 9. Interview questions and answers

### 1. What does a collector's accumulation type represent?

Its intermediate mutable state, which can differ from the finished result type.

### 2. Why can parallel collect use ArrayList?

Correct non-concurrent collection creates isolated partial containers and merges them rather than concurrently appending to one captured list.

### 3. Are Stream.toList and Collectors.toList interchangeable?

No. Their availability, mutability, null, and implementation contracts differ; choose based on required behavior.

## Completion checklist

- [ ] I can select a result container explicitly.
- [ ] I can define duplicate and order policies.
- [ ] I can explain supplier, accumulator, combiner, and finisher.
- [ ] I can distinguish list APIs by their contracts.

## Official references

- [Collectors API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Collectors.html)
- [Collector contract](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Collector.html)
- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)

---

[← 170. reduce()](170_reduce.md) | [Course Index](../README.md) | [172. Collectors →](172_Collectors.md)
