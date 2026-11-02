# 164. flatMap()

[Coding practice →](../../code_practice/11_Stream_API/164_flatMap.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Flatten zero, one, or many mapped values per input.
- Distinguish nested map results from flatMap results.
- Handle nested nulls, primitive results, and mapped-stream lifetimes.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. Mapping followed by flattening

`flatMap` maps each input to a stream and combines those mapped streams into one outer stream. It is an intermediate operation useful for lists of lists, orders with line items, and text containing multiple tokens.

```text
map:     List<List<Integer>> → Stream<Stream<Integer>>
flatMap: List<List<Integer>> → Stream<Integer>
```

The mapper's result is a `Stream`, not an arbitrary `List`. Use `Collection::stream` to adapt collections. Each input can contribute zero, one, or many output elements.

## 2. Flatten nested collections

```java
List<List<Integer>> groups = Arrays.asList(
        Arrays.asList(1, 2), Collections.emptyList(), Arrays.asList(3, 4));
List<Integer> flat = groups.stream().flatMap(Collection::stream)
        .collect(Collectors.toList());
System.out.println(flat); // [1, 2, 3, 4]
```

An empty inner collection contributes no values. Ordered list sources and ordered mapped streams give the flattened encounter sequence used here. Flattening one level does not recursively flatten an arbitrary nested structure.

## 3. Tokenization and primitive variants

```java
List<String> words = Stream.of("Java streams", "Java functions")
        .flatMap(line -> Arrays.stream(line.split(" ")))
        .distinct().collect(Collectors.toList());
System.out.println(words); // [Java, streams, functions]
int total = Stream.of(new int[]{1, 2}, new int[]{3})
        .flatMapToInt(Arrays::stream).sum();
System.out.println(total); // 6
```

This split rule is intentionally limited to the single-space fixture. Real tokenization needs a deliberate whitespace, punctuation, case, and Unicode policy. `flatMapToInt`, `flatMapToLong`, and `flatMapToDouble` avoid boxed primitive output.

## 4. Null handling and Optional adaptation

```java
List<List<String>> groups = Arrays.asList(Arrays.asList("Ada", null), null,
        Collections.emptyList(), Arrays.asList("Grace"));
List<String> names = groups.stream().filter(Objects::nonNull)
        .flatMap(Collection::stream).filter(Objects::nonNull)
        .collect(Collectors.toList());
System.out.println(names); // [Ada, Grace]
```

Null inner collections and null inner elements are different cases. Returning a null mapped stream is treated by `flatMap` as empty, but deliberately returning `Stream.empty()` communicates intent more clearly. Calling `.stream()` on a null collection still throws before any mapped stream is returned.

Java 8 can flatten optional values using a present-or-empty branch. **Java 9+:** `Optional.stream()` provides zero or one elements and can be used as `flatMap(Optional::stream)`.

## 5. Resource lifetime and execution

Each mapped stream is closed after its contents have been incorporated. If the outer source itself owns a resource, close the outer pipeline too. Avoid eagerly opening many resources before traversal or capturing resources already closed by an enclosing scope.

`flatMap` remains lazy and does not itself select a parallel execution mode. A short-circuiting downstream operation may not require all mapped values. Keep the mapper non-interfering and do not rely on side-effect invocation counts. **Java 16+:** `mapMulti` is another option for small zero-to-many expansions, with a different callback contract.

## 6. Complete runnable example

Save as `StreamLesson164.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson164 {
    public static void main(String[] args) throws Exception {
        List<List<Integer>> groups = Arrays.asList(Arrays.asList(1, 2),
                Collections.emptyList(), Arrays.asList(3, 4));
        System.out.println(groups.stream().flatMap(Collection::stream)
                .collect(Collectors.toList()));
        System.out.println(Stream.of("Java streams", "Java functions")
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct().collect(Collectors.toList()));
        System.out.println(Stream.of(new int[]{1, 2}, new int[]{3})
                .flatMapToInt(Arrays::stream).sum());
    }
}
```

Compile and run:

```shell
javac StreamLesson164.java
java StreamLesson164
```

Expected output:

```text
[1, 2, 3, 4]
[Java, streams, functions]
6
```

## 7. Common mistakes

| Mistake | Better approach |
|---|---|
| Returning a List directly from flatMap | Return its stream. |
| Expecting recursive flattening of every depth | Flatten each required layer explicitly. |
| Calling stream on null inner collections | Filter or otherwise handle missing collections first. |
| Opening resources outside their useful lifetime | Create and close resource-backed streams deliberately. |
| Assuming flatMap removes duplicate values | Add distinct when equality-based deduplication is required. |

## 8. Practice exercises

1. Flatten a list of integer lists including an empty list. **Hint:** Collection::stream.
2. Flatten orders into their line items. **Hint:** Map each order to its item stream.
3. Flatten primitive arrays and calculate their sum. **Hint:** flatMapToInt(Arrays::stream).
4. Remove null groups and null elements independently. **Hint:** Use a guard before and after flattening.
5. Flatten present Optional values using only Java 8 APIs. **Hint:** Return Stream.of(value) or Stream.empty().

## 9. Interview questions and answers

### 1. How does flatMap differ from map?

map retains each mapper result as one element; flatMap expects streams and merges their elements into the output sequence.

### 2. What does an empty mapped stream contribute?

No output elements, allowing zero-to-many mapping.

### 3. Are mapped streams left open after flattening?

flatMap closes each mapped stream after incorporating it. The outer source's own resource lifetime still needs handling.

## Completion checklist

- [ ] I can choose map or flatMap by the intended result shape.
- [ ] I can flatten collections and primitive arrays.
- [ ] I can distinguish absent groups from absent elements.
- [ ] I can explain mapped-stream closure and outer resource ownership.

## Official references

- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)
- [Stream package guide](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html)

---

[← 163. map()](163_map.md) | [Course Index](../README.md) | [165. forEach() →](165_forEach.md)
