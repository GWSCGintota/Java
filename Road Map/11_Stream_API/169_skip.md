# 169. skip()

[Coding practice →](../../code_practice/11_Stream_API/169_skip.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Discard a prefix with skip and understand its boundaries.
- Build stable, validated in-memory pagination.
- Explain ordering requirements and the cost of large offsets.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. Skip contract

`skip(long n)` is a stateful intermediate operation discarding the first `n` elements in encounter order when that order exists. It passes the remaining suffix downstream.

```java
System.out.println(Stream.of(1, 2, 3, 4, 5).skip(2)
        .collect(Collectors.toList())); // [3, 4, 5]
System.out.println(Stream.of(1, 2).skip(0)
        .collect(Collectors.toList())); // [1, 2]
System.out.println(Stream.of(1, 2).skip(10).count()); // 0
```

`n` must be nonnegative or `IllegalArgumentException` is thrown. Skipping exactly the source size or more leaves an empty result. The source collection is not modified.

## 2. Pagination with a stable order

```java
List<String> names = Arrays.asList("Grace", "Bob", "Ada", "Alan", "Emma");
int pageIndex = 1; // zero-based
int pageSize = 2;
if (pageIndex < 0 || pageSize <= 0) {
    throw new IllegalArgumentException("Invalid page");
}
long offset = Math.multiplyExact((long) pageIndex, (long) pageSize);
List<String> page = names.stream().sorted().skip(offset).limit(pageSize)
        .collect(Collectors.toList());
System.out.println(page); // [Bob, Emma]
```

Multiply in a sufficiently wide type before calling `skip`; `(long) (pageIndex * pageSize)` widens too late to prevent int overflow. Page boundaries are meaningful only for a defined stable ordering. Changes to the underlying dataset between requests can move records between pages.

## 3. Order of operations

```java
List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6);
System.out.println(values.stream().filter(n -> n % 2 == 0).skip(1)
        .collect(Collectors.toList())); // [4, 6]
System.out.println(values.stream().skip(1).filter(n -> n % 2 == 0)
        .collect(Collectors.toList())); // [2, 4, 6]
```

Skipping after selection discards matching elements; skipping before selection discards source elements. `skip(offset).limit(size)` describes a page; `limit(size).skip(offset)` describes a different, often empty result. On unordered input there is no guaranteed specific prefix to discard.

## 4. Costs, unbounded inputs, and alternatives

`skip` is not itself a short-circuiting bound: it may still leave an unbounded suffix. Combine it with a suitable terminal operation or `limit` when needed.

```java
System.out.println(Stream.iterate(1, n -> n + 1).skip(3).limit(3)
        .collect(Collectors.toList())); // [4, 5, 6]
```

Large ordered parallel offsets can require costly coordination. Some sources can optimize slicing, but the API does not promise constant-time random access for arbitrary pipelines. For indexed lists, a carefully bounded `subList` may directly express a slice; for databases, perform pagination at the query layer instead of materializing every row into a Java stream. Keyset pagination is another domain-specific option when large changing datasets make offset pagination unsuitable.

## 5. Complete runnable example

Save as `StreamLesson169.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson169 {
    public static void main(String[] args) throws Exception {
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(values.stream().skip(2).limit(3).collect(Collectors.toList()));
        System.out.println(values.stream().filter(n -> n % 2 == 0).skip(1)
                .collect(Collectors.toList()));
        System.out.println(values.stream().skip(1).filter(n -> n % 2 == 0)
                .collect(Collectors.toList()));
        System.out.println(Stream.iterate(1, n -> n + 1).skip(3).limit(3)
                .collect(Collectors.toList()));
        System.out.println(Stream.of(1, 2).skip(10).count());
    }
}
```

Compile and run:

```shell
javac StreamLesson169.java
java StreamLesson169
```

Expected output:

```text
[3, 4, 5]
[4, 6]
[2, 4, 6]
[4, 5, 6]
0
```

## 6. Common mistakes

| Mistake | Better approach |
|---|---|
| Using skip as an upper bound | It removes a prefix; use limit to bound the suffix. |
| Paginating without stable order | Define an encounter order and a tie policy. |
| Multiplying page values in int before widening | Widen operands first and validate inputs. |
| Swapping skip and limit | Trace the intended slice in order. |
| Loading every database row to paginate in Java | Apply the appropriate query-level pagination. |

## 7. Practice exercises

1. Take page two with zero-based index 1 and size 3. **Hint:** skip(3).limit(3).
2. Test skip with zero, exact size, and an oversized offset. **Hint:** Observe unchanged versus empty suffixes.
3. Skip the first two matching even values. **Hint:** Filter before skip.
4. Build a bounded slice of an iterate source. **Hint:** skip then limit.
5. Explain page drift when values are inserted between requests. **Hint:** Stable sorting does not make the dataset a frozen snapshot.

## 8. Interview questions and answers

### 1. Does skip remove elements from the collection?

No. It changes which stream elements continue downstream.

### 2. Is skip a short-circuiting operation?

It is stateful but not a terminating bound by itself; an unbounded suffix can remain.

### 3. Why can ordered parallel skip be expensive?

Preserving the correct encounter-order prefix removal across partitions can require coordination.

## Completion checklist

- [ ] I can explain suffix selection and invalid offsets.
- [ ] I can construct and validate a stable page.
- [ ] I can reason about filter/skip/limit order.
- [ ] I can choose a more appropriate slicing layer when needed.

## Official references

- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)
- [Stream package guide](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html)

---

[← 168. limit()](168_limit.md) | [Course Index](../README.md) | [170. reduce() →](170_reduce.md)
