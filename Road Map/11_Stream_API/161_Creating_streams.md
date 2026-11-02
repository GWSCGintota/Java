# 161. Creating streams

[Coding practice →](../../code_practice/11_Stream_API/161_Creating_streams.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Create streams from collections, arrays, values, ranges, generators, and files.
- Choose primitive and object stream factories correctly.
- Bound generated streams and close resource-backed streams.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. Factory choices

| Source | Factory | Notes |
|---|---|---|
| Collection | `values.stream()` | Sequential by default |
| Object array | `Arrays.stream(array)` | Stream of array elements |
| Primitive int array | `Arrays.stream(intArray)` | Produces `IntStream` |
| Explicit values | `Stream.of(a, b, c)` | Object reference stream |
| Empty result | `Stream.empty()` | Useful for a no-values branch |
| Integer interval | `IntStream.range(start, end)` | End is exclusive |
| Repeated values | `Stream.generate(supplier)` | Unbounded factory |
| Iteration | `Stream.iterate(seed, next)` | Java 8 overload is unbounded |
| File lines | `Files.lines(path, charset)` | Caller closes the stream |

## 2. Arrays and the primitive-array trap

```java
String[] names = {"Ada", "Grace"};
System.out.println(Arrays.stream(names).count()); // 2
int[] numbers = {1, 2, 3};
System.out.println(Arrays.stream(numbers).sum()); // 6
System.out.println(Stream.of(numbers).count()); // 1
```

`Stream.of(intArray)` produces a `Stream<int[]>` containing the array object, not a stream of boxed integers. Use `Arrays.stream(numbers)` and `boxed()` if wrapper values are needed. An explicitly typed `Stream.of((String) null)` contains one null element; it is not empty.

## 3. Ranges and generated values

```java
System.out.println(IntStream.range(1, 4).sum());       // 6
System.out.println(IntStream.rangeClosed(1, 4).sum()); // 10
List<Integer> powers = Stream.iterate(1, n -> n * 2)
        .limit(5).collect(Collectors.toList());
System.out.println(powers); // [1, 2, 4, 8, 16]
List<String> words = Stream.generate(() -> "Java")
        .limit(3).collect(Collectors.toList());
System.out.println(words); // [Java, Java, Java]
```

Bound unbounded streams before a terminal operation requiring all elements. `generate` has an unordered stream contract; `iterate` defines the seed-based encounter sequence. Captured mutable generators need a separate thread-safety policy.

## 4. Builders, concatenation, and repeatable creation

```java
Stream.Builder<String> builder = Stream.builder();
builder.add("Ada").add("Grace");
System.out.println(builder.build().collect(Collectors.toList())); // [Ada, Grace]
System.out.println(Stream.concat(Stream.of(1, 2), Stream.of(3))
        .collect(Collectors.toList())); // [1, 2, 3]
Supplier<Stream<Integer>> factory = () -> Stream.of(1, 2, 3);
System.out.println(factory.get().count()); // 3
System.out.println(factory.get().mapToInt(n -> n).sum()); // 6
```

A builder cannot accept further elements after building. A factory must create a fresh stream per call; returning the same captured stream does not make it reusable. Avoid deeply chaining many `concat` calls when a collection of sources and `flatMap` is clearer.

## 5. Resource-backed sources and later APIs

`Files.lines` opens a file-backed stream. Keep the terminal operation inside its try-with-resources scope; do not return that stream from a block that immediately closes it. `Files.list` and `Files.walk` also need closure.

```java
java.nio.file.Path path = java.nio.file.Files.createTempFile("stream-lines-", ".txt");
try {
    java.nio.file.Files.write(path, Arrays.asList("Ada", "Grace"),
            java.nio.charset.StandardCharsets.UTF_8);
    try (Stream<String> lines = java.nio.file.Files.lines(path,
            java.nio.charset.StandardCharsets.UTF_8)) {
        System.out.println(lines.count()); // 2
    }
} finally {
    java.nio.file.Files.deleteIfExists(path);
}
```

**Java 9+:** `Stream.ofNullable(value)` creates zero or one elements. The three-argument `Stream.iterate(seed, hasNext, next)` adds a continuation condition. In Java 8, use an explicit null branch or bounded iteration. A fresh collection stream generally reads the source during traversal, not as an eager snapshot at the `stream()` call.

## 6. Complete runnable example

Save as `StreamLesson161.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson161 {
    public static void main(String[] args) throws Exception {
        int[] values = {1, 2, 3};
        System.out.println(Arrays.stream(values).sum());
        System.out.println(Stream.of(values).count());
        System.out.println(IntStream.range(1, 4).boxed().collect(Collectors.toList()));
        System.out.println(Stream.iterate(1, n -> n * 2).limit(5)
                .collect(Collectors.toList()));
        Supplier<Stream<String>> factory = () -> Stream.of("Ada", "Grace");
        System.out.println(factory.get().count());
        System.out.println(factory.get().collect(Collectors.joining(", ")));
    }
}
```

Compile and run:

```shell
javac StreamLesson161.java
java StreamLesson161
```

Expected output:

```text
6
1
[1, 2, 3]
[1, 2, 4, 8, 16]
2
Ada, Grace
```

## 7. Common mistakes

| Mistake | Better approach |
|---|---|
| Using Stream.of(intArray) for numeric elements | Use Arrays.stream(intArray). |
| Collecting an unbounded generator | Add a bound that allows termination. |
| Returning one consumed stream from a factory | Construct a new stream on each invocation. |
| Leaving Files.lines open | Use try-with-resources around traversal. |
| Confusing range with rangeClosed | Check whether the end is exclusive or inclusive. |

## 8. Practice exercises

1. Stream a string array and uppercase its elements. **Hint:** Arrays.stream and map.
2. Generate the first six multiples of three. **Hint:** iterate with seed 3 and limit 6.
3. Compare range(0, 3) and rangeClosed(0, 3). **Hint:** Print arrays or lists of their elements.
4. Create an empty-or-single-element stream in Java 8. **Hint:** Branch to Stream.empty or Stream.of.
5. Count lines in a temporary UTF-8 file and clean it up. **Hint:** Nest traversal inside try-with-resources.

## 9. Interview questions and answers

### 1. Why does Stream.of(new int[]{1, 2}) have count one?

Its single reference element is the primitive array object. It does not unpack primitive components.

### 2. How can a stream calculation be repeated?

Keep reusable data or a supplier that creates a genuinely fresh stream, then build a new pipeline.

### 3. Must every stream be explicitly closed?

Resource-backed sources must be closed according to ownership. Ordinary in-memory collection streams usually do not require explicit closure.

## Completion checklist

- [ ] I can choose the right factory for each source type.
- [ ] I can explain primitive-array and null factory behavior.
- [ ] I can bound generated streams and manage I/O lifetimes.
- [ ] I can distinguish fresh stream factories from consumed stream reuse.

## Official references

- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)
- [Stream package guide](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html)

---

[← 160. Streams](160_Streams.md) | [Course Index](../README.md) | [162. filter() →](162_filter.md)
