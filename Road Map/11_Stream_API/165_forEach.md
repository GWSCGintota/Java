# 165. forEach()

[Coding practice →](../../code_practice/11_Stream_API/165_forEach.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Use terminal actions with Consumer without expecting a returned collection.
- Choose encounter-order guarantees deliberately.
- Avoid unsafe mutation and misplaced required effects.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. Terminal action contract

`void forEach(Consumer<? super T> action)` invokes an action for stream elements. It is terminal and returns no result. Use it for a deliberate action such as printing or sending data to an already-defined output boundary.

```java
Stream.of("Ada", "Grace").forEachOrdered(System.out::println);
// Ada
// Grace
```

An `Iterable`, such as `List`, also has a `forEach` method. A collection method is not itself a stream pipeline; a stream action can follow filters and transformations.

## 2. Filter, transform, then act

```java
Arrays.asList("Ada", "Bob", "Grace").stream()
        .filter(name -> name.length() > 3)
        .map(name -> name.toUpperCase(Locale.ROOT))
        .forEachOrdered(System.out::println); // GRACE
```

Keep transformations in `map` and selection in `filter`. If the requirement is a returned list, use `collect` rather than externally appending elements as a side effect.

## 3. forEach and forEachOrdered

| Operation | Encounter order | Typical purpose |
|---|---|---|
| `forEach` | Does not guarantee encounter order for parallel execution | Independent actions without ordering requirements |
| `forEachOrdered` | Preserves encounter order when one exists | Ordered output or ordered actions |

```java
IntStream.rangeClosed(1, 3).parallel()
        .forEachOrdered(n -> System.out.println("Number: " + n));
// Number: 1
// Number: 2
// Number: 3
```

Ordering is not a promise of one particular worker thread. `forEachOrdered` orders its actions, while upstream parallel callbacks still have their own concurrency requirements. It cannot invent an encounter order for an unordered source.

## 4. Accumulation and shared state

```java
List<String> names = Arrays.asList("Ada", "Grace", "Alan");
List<Integer> lengths = names.parallelStream()
        .map(String::length).collect(Collectors.toList());
System.out.println(lengths); // [3, 5, 4]
```

Capturing one ordinary `ArrayList` and calling `add` from parallel `forEach` is unsafe. A collector manages intermediate accumulation containers. A synchronized collection can protect individual operations without automatically providing the desired order or multi-step algorithm correctness. Do not structurally modify the collection being traversed.

## 5. No loop break, and explicit failures

A consumer's bare `return;` ends that one invocation; it does not break out of the stream. Use `anyMatch`, `findFirst`, another short-circuiting operation, or a loop when the goal is to stop traversal.

```java
Stream.of(1, 2, 3).forEachOrdered(n -> {
    if (n == 2) {
        return;
    }
    System.out.println(n); // 1, then 3
});
```

Unchecked callback exceptions propagate; parallel tasks may already have performed other actions before failure is observed. There is no transaction or rollback guarantee. Checked exceptions need handling or a deliberately different functional contract. `peek` is intermediate and unsuitable for required business effects.

## 6. Complete runnable example

Save as `StreamLesson165.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson165 {
    public static void main(String[] args) throws Exception {
        List<String> names = Arrays.asList("Ada", "Bob", "Grace");
        names.stream().filter(name -> name.length() > 3)
                .map(name -> name.toUpperCase(Locale.ROOT))
                .forEachOrdered(System.out::println);
        IntStream.rangeClosed(1, 3).parallel()
                .forEachOrdered(n -> System.out.println("Number: " + n));
        System.out.println(names.parallelStream().map(String::length)
                .collect(Collectors.toList()));
    }
}
```

Compile and run:

```shell
javac StreamLesson165.java
java StreamLesson165
```

Expected output:

```text
GRACE
Number: 1
Number: 2
Number: 3
[3, 3, 5]
```

## 7. Common mistakes

| Mistake | Better approach |
|---|---|
| Assigning the result of forEach to a List | Use map and collect for returned data. |
| Assuming parallel forEach preserves list order | Use forEachOrdered when that contract matters. |
| Using return as a stream-wide break | Use a short-circuiting operation or a loop. |
| Appending to one shared ArrayList in parallel | Use a collector. |
| Expecting failure to roll back earlier actions | Define effect and failure handling explicitly. |

## 8. Practice exercises

1. Print only even values in encounter order. **Hint:** filter followed by forEachOrdered.
2. Compare List.forEach with stream().filter(...).forEachOrdered. **Hint:** The stream version adds pipeline processing.
3. Build a list of uppercase names without an external result list. **Hint:** map and collect.
4. Demonstrate that return skips only one consumer invocation. **Hint:** Print 1 and 3 from [1, 2, 3].
5. Print a parallel ordered integer range predictably. **Hint:** forEachOrdered.

## 9. Interview questions and answers

### 1. Does forEach return a transformed collection?

No. Its result is void; it performs terminal actions.

### 2. Does forEachOrdered make an entire pipeline single-threaded?

No. It constrains action order, not all upstream execution or a fixed worker identity.

### 3. Why avoid shared external accumulation?

It mixes transformation and effects and can introduce races and ordering problems under parallel execution; collectors express result construction directly.

## Completion checklist

- [ ] I can choose a terminal action or a returned-data terminal operation.
- [ ] I can explain parallel ordering guarantees.
- [ ] I can avoid shared mutable accumulation.
- [ ] I can reason about return, exceptions, and earlier effects.

## Official references

- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)
- [Stream package guide](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html)

---

[← 164. flatMap()](164_flatMap.md) | [Course Index](../README.md) | [166. sorted() →](166_sorted.md)
