# 162. filter()

[Coding practice →](../../code_practice/11_Stream_API/162_filter.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Use Predicate-based selection without changing element types.
- Combine conditions with explicit null handling.
- Reason about filtering order, short-circuiting, and safe callback behavior.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. Contract and basic selection

`filter(Predicate<? super T> predicate)` is an intermediate operation producing a `Stream<T>` of matching elements. It retains or rejects each value; it does not transform retained values into another type. It can keep all, some, or no elements.

```java
List<Integer> result = Arrays.asList(-2, 0, 3, 4, 6).stream()
        .filter(n -> n > 0)
        .filter(n -> n % 2 == 0)
        .collect(Collectors.toList());
System.out.println(result); // [4, 6]
```

This does not remove values from the source list. For in-place collection removal, consider a dedicated operation such as `removeIf`, which has different mutation semantics.

## 2. Reusable and null-safe predicates

```java
Predicate<String> nonBlank = text -> !text.trim().isEmpty();
List<String> names = Arrays.asList(null, "", " Ada ", "  ", "Grace").stream()
        .filter(Objects::nonNull)
        .filter(nonBlank)
        .map(String::trim)
        .collect(Collectors.toList());
System.out.println(names); // [Ada, Grace]
Predicate<Integer> positive = n -> n > 0;
Predicate<Integer> even = n -> n % 2 == 0;
System.out.println(positive.and(even).test(4)); // true
```

The null guard precedes the dereference. A combined predicate such as `text -> text != null && !text.trim().isEmpty()` also uses short-circuiting. `and`, `or`, and `negate` express different selection policies; adding multiple filters means all their conditions must pass.

## 3. Objects and execution order

Object predicates can inspect fields or getters without changing the object. For example, filter employees by department before mapping them to display names. String content comparisons need `equals`, not reference `==`.

```java
List<String> result = Arrays.asList(" Ada ", "Bob", "Alice").stream()
        .map(String::trim)
        .filter(name -> name.startsWith("A"))
        .collect(Collectors.toList());
System.out.println(result); // [Ada, Alice]
```

Trimming before the prefix check changes the rule. Reordering pure independent filters preserves membership, but moving a filter across a transformation may change meaning. Put a cheap selective check before expensive work only when the reordering preserves the contract.

## 4. Empty results, laziness, and short-circuiting

```java
System.out.println(Stream.of(1, 3, 5).filter(n -> n % 2 == 0).count()); // 0
Optional<Integer> first = Stream.of(1, 3, 4, 6)
        .filter(n -> n % 2 == 0).findFirst();
System.out.println(first.orElse(-1)); // 4
```

Creating the filter stage does not invoke the predicate. Terminal operations trigger the needed work, and `findFirst` may stop once a result is established. An unbounded source with a filter that never passes can fail to terminate even when followed by `findFirst` or `limit`.

Do not use counters, source mutation, or required logging inside a filter to implement business effects. Predicates may throw; exceptions propagate through the terminal call and are not automatically converted to non-matches.

## 5. Complete runnable example

Save as `StreamLesson162.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson162 {
    public static void main(String[] args) throws Exception {
        List<String> source = Arrays.asList(null, " Ada ", "", "Grace", "  ");
        List<String> names = source.stream().filter(Objects::nonNull)
                .map(String::trim).filter(name -> !name.isEmpty())
                .collect(Collectors.toList());
        System.out.println(names);
        Predicate<Integer> positive = n -> n > 0;
        Predicate<Integer> even = n -> n % 2 == 0;
        System.out.println(Stream.of(-2, 0, 3, 4, 6).filter(positive.and(even))
                .collect(Collectors.toList()));
        System.out.println(Stream.of(1, 3, 5).filter(even).findFirst().isPresent());
    }
}
```

Compile and run:

```shell
javac StreamLesson162.java
java StreamLesson162
```

Expected output:

```text
[Ada, Grace]
[4, 6]
false
```

## 6. Common mistakes

| Mistake | Better approach |
|---|---|
| Using map to discard values | Use filter with a boolean condition. |
| Dereferencing before a null guard | Filter null values first or short-circuit the condition. |
| Using == for string values | Use equals or a defined comparison policy. |
| Mutating the traversed collection | Keep predicates non-interfering. |
| Assuming no match throws | Collect an empty result or handle an empty Optional. |

## 7. Practice exercises

1. Select values in the inclusive range 10–20. **Hint:** Combine two comparisons with &&.
2. Select non-null names longer than three after trimming. **Hint:** Guard, normalize, then filter.
3. Compare filter before limit with limit before filter. **Hint:** Use [1, 2, 3, 4] and an even condition.
4. Find the first positive even value. **Hint:** Combine predicates and findFirst.
5. Filter objects by two properties without mutating them. **Hint:** Use getters and content equality.

## 8. Interview questions and answers

### 1. Does filter change the stream element type?

No. It selects elements of the same type. Use map for transformations.

### 2. Do multiple filters behave like AND?

Their retained membership requires every predicate to pass, assuming the callbacks obey the stream contracts.

### 3. Can a filter throw?

Yes. A failing predicate propagates its exception through traversal; it is not silently treated as false.

## Completion checklist

- [ ] I can implement and combine selection predicates.
- [ ] I can guard null before dereferencing.
- [ ] I can explain why pipeline order affects selection.
- [ ] I can handle empty results and avoid predicate side effects.

## Official references

- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)
- [Stream package guide](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html)

---

[← 161. Creating streams](161_Creating_streams.md) | [Course Index](../README.md) | [163. map() →](163_map.md)
