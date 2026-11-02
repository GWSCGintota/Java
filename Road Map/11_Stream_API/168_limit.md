# 168. limit()

[Coding practice →](../../code_practice/11_Stream_API/168_limit.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Select a bounded prefix with limit.
- Handle zero, negative, finite, and generated inputs.
- Reason about pipeline order and ordered-parallel costs.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. Prefix contract

`limit(long maxSize)` is a stateful, short-circuiting intermediate operation. It retains at most the specified number of elements. On an ordered stream it retains the encounter-order prefix.

```java
System.out.println(Stream.of(1, 2, 3, 4).limit(2)
        .collect(Collectors.toList())); // [1, 2]
System.out.println(Stream.of(1, 2).limit(10)
        .collect(Collectors.toList())); // [1, 2]
System.out.println(Stream.of(1, 2).limit(0).count()); // 0
```

The maximum is a `long`; it must be nonnegative. A negative value throws `IllegalArgumentException`. A larger bound does not pad the result. Empty input stays empty.

## 2. Filter and limit are not interchangeable

```java
List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6);
System.out.println(values.stream().filter(n -> n % 2 == 0).limit(2)
        .collect(Collectors.toList())); // [2, 4]
System.out.println(values.stream().limit(2).filter(n -> n % 2 == 0)
        .collect(Collectors.toList())); // [2]
```

The first pipeline asks for two matching values. The second examines only the first two source values and retains their matches. Likewise, sorting before limiting selects globally ranked values from a finite source; limiting before sorting ranks only the selected prefix.

## 3. Bound generated sources

```java
System.out.println(Stream.iterate(1, n -> n + 1).limit(5)
        .collect(Collectors.toList())); // [1, 2, 3, 4, 5]
System.out.println(IntStream.iterate(0, n -> n + 2).limit(4).sum()); // 12
```

Downstream bounding can make unbounded generation usable. It does not guarantee termination through every upstream stage: an always-false filter may never produce one retained value, and a preceding global sort generally needs to exhaust input first. Think about whether the next requested element can actually be produced.

## 4. Order, parallelism, and resource lifetime

On unordered input, the contract does not identify a particular prefix. Ordered parallel limiting can be expensive because partitions must coordinate to retain the earliest elements. `unordered()` is a semantic choice, not a free optimization if the original prefix mattered.

`limit` does not mutate the source or close an I/O-backed source after collection. Use try-with-resources when the source needs it. Do not use callback invocation counts to infer exactly how much upstream work parallel execution performed; retained results and execution scheduling are different contracts.

For pagination, combine a validated `skip(offset)` with a bound and a stable order. See [169. skip](169_skip.md).

## 5. Complete runnable example

Save as `StreamLesson168.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson168 {
    public static void main(String[] args) throws Exception {
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(values.stream().filter(n -> n % 2 == 0).limit(2)
                .collect(Collectors.toList()));
        System.out.println(values.stream().limit(2).filter(n -> n % 2 == 0)
                .collect(Collectors.toList()));
        System.out.println(Stream.iterate(1, n -> n + 1).limit(5)
                .collect(Collectors.toList()));
        System.out.println(Stream.of(1, 2).limit(0).count());
        try { Stream.of(1, 2).limit(-1); }
        catch (IllegalArgumentException exception) { System.out.println("Negative limit"); }
    }
}
```

Compile and run:

```shell
javac StreamLesson168.java
java StreamLesson168
```

Expected output:

```text
[2, 4]
[2]
[1, 2, 3, 4, 5]
0
Negative limit
```

## 6. Common mistakes

| Mistake | Better approach |
|---|---|
| Expecting exactly n output elements | The result has at most n elements. |
| Passing a negative maximum | Validate configuration or handle IllegalArgumentException. |
| Moving limit across filter without checking meaning | Decide whether the bound applies before or after selection. |
| Sorting infinite input before limiting | Bound a suitable source or change the algorithm. |
| Assuming ordered parallel prefix selection is free | Measure and preserve required ordering semantics. |

## 7. Practice exercises

1. Take the first three positive values from mixed input. **Hint:** Filter before limiting.
2. Generate six powers of two. **Hint:** iterate plus limit.
3. Compare limits of zero and greater than source size. **Hint:** Count both results.
4. Find the two smallest values in a finite list. **Hint:** Sort before limit.
5. Explain why an impossible filter followed by limit(1) can hang. **Hint:** No output element ever reaches the limiting stage.

## 8. Interview questions and answers

### 1. Is limit terminal?

No. It is a short-circuiting intermediate stage; a terminal operation is still needed.

### 2. Does limit guarantee termination on every infinite pipeline?

No. Upstream stages must be capable of producing the requested elements without first requiring unbounded work.

### 3. What does limit mean without encounter order?

It constrains the result size, but a particular first-n subset is not promised.

## Completion checklist

- [ ] I can explain at-most-prefix behavior.
- [ ] I can handle invalid and boundary bounds.
- [ ] I can reason about filter/sort ordering and termination.
- [ ] I can distinguish retained output from upstream scheduling.

## Official references

- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)
- [Stream package guide](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html)

---

[← 167. distinct()](167_distinct.md) | [Course Index](../README.md) | [169. skip() →](169_skip.md)
