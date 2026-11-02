[← 164. flatMap()](164_flatMap.md) | [Course Index](../README.md) | [166. sorted() →](166_sorted.md)

# Java forEach() Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/165_forEach.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — Print list values

**Task:** Print Ada and Grace with List.forEach.

```java
Arrays.asList("Ada", "Grace").forEach(System.out::println);
```

**Output:**
```text
Ada
Grace
```

**Concept:** Iterable.forEach is a collection action rather than a stream pipeline.

## Question 2 — Select before acting

**Task:** Print only even integers from [1, 2, 3, 4] in encounter order.

```java
Stream.of(1, 2, 3, 4).filter(n -> n % 2 == 0).forEachOrdered(System.out::println);
```

**Output:**
```text
2
4
```

**Concept:** A stream terminal action can follow selection stages.

## Question 3 — Transform before acting

**Task:** Print uppercase versions of Ada and Grace using Locale.ROOT.

```java
Stream.of("Ada", "Grace").map(s -> s.toUpperCase(Locale.ROOT)).forEachOrdered(System.out::println);
```

**Output:**
```text
ADA
GRACE
```

**Concept:** map expresses transformation; the terminal Consumer performs output.

## Question 4 — Reusable Consumer

**Task:** Create a Consumer that prints User: followed by each name in [Ada, Bob].

```java
Consumer<String> print = name -> System.out.println("User: " + name);
Stream.of("Ada", "Bob").forEachOrdered(print);
```

**Output:**
```text
User: Ada
User: Bob
```

**Concept:** A Consumer accepts input and returns void.

## Question 5 — Chained actions

**Task:** For Java, first print the text and then its length using Consumer.andThen.

```java
Consumer<String> print = s -> System.out.println("Text: " + s);
Consumer<String> length = s -> System.out.println("Length: " + s.length());
Stream.of("Java").forEachOrdered(print.andThen(length));
```

**Output:**
```text
Text: Java
Length: 4
```

**Concept:** Chained consumers receive the same input in action order.

## Question 6 — Return is not break

**Task:** Process [1, 2, 3], using return inside the consumer to skip printing 2.

```java
Stream.of(1, 2, 3).forEachOrdered(n -> {
    if (n == 2) { return; }
    System.out.println(n);
});
```

**Output:**
```text
1
3
```

**Concept:** return exits one consumer invocation and does not stop the stream.

## Question 7 — Ordered parallel actions

**Task:** Print integers 1 through 4 from a parallel ordered range predictably.

```java
IntStream.rangeClosed(1, 4).parallel().forEachOrdered(System.out::println);
```

**Output:**
```text
1
2
3
4
```

**Concept:** forEachOrdered preserves required encounter order without promising a worker identity.

## Question 8 — Collect instead of external accumulation

**Task:** Return uppercase names from [Ada, Grace] using a parallel stream and no captured result list.

```java
List<String> result = Arrays.asList("Ada", "Grace").parallelStream()
        .map(s -> s.toUpperCase(Locale.ROOT))
        .collect(Collectors.toList());
System.out.println(result);
```

**Output:**
```text
[ADA, GRACE]
```

**Concept:** Correct collection manages intermediate result containers instead of sharing one external ArrayList.

## Question 9 — Empty action source

**Task:** Run an action over an empty stream, then print Done.

```java
Stream.<String>empty().forEach(s -> System.out.println("Unexpected: " + s));
System.out.println("Done");
```

**Output:**
```text
Done
```

**Concept:** Empty input invokes the element action zero times.

## Question 10 — Sequential failure preserves earlier effects

**Task:** Record [1, 2, 3] in order but throw before recording 2; catch the failure and print the recorded list.

```java
List<Integer> recorded = new ArrayList<>();
try {
    Stream.of(1, 2, 3)
            .forEachOrdered(n -> { if (n == 2) { throw new IllegalStateException("Stop"); } recorded.add(n); });
} catch (IllegalStateException e) { System.out.println(e.getMessage()); }
System.out.println(recorded);
```

**Output:**
```text
Stop
[1]
```

**Concept:** The sequential failure skips later work but does not roll back earlier effects; parallel failures can have additional in-flight effects.

---

## Interview Questions and Answers

### Interview Question 1 — Does forEach return a transformed collection?

**Answer:** No. Its result is void; it performs terminal actions.

### Interview Question 2 — Does forEachOrdered make an entire pipeline single-threaded?

**Answer:** No. It constrains action order, not all upstream execution or a fixed worker identity.

### Interview Question 3 — Why avoid shared external accumulation?

**Answer:** It mixes transformation and effects and can introduce races and ordering problems under parallel execution; collectors express result construction directly.

### Interview Question 4 — Can a Consumer return a transformed value?

**Answer:** Its operation returns void. Use map for returned transformations and collect when the caller needs a result container.

### Interview Question 5 — Does a terminal action automatically close Files.lines?

**Answer:** No. The file-backed stream still needs a resource ownership scope such as try-with-resources.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| Print list values | Iterable.forEach is a collection action rather than a stream pipeline. |
| Select before acting | A stream terminal action can follow selection stages. |
| Transform before acting | map expresses transformation; the terminal Consumer performs output. |
| Reusable Consumer | A Consumer accepts input and returns void. |
| Chained actions | Chained consumers receive the same input in action order. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/165_forEach.md)

[← 164. flatMap()](164_flatMap.md) | [Course Index](../README.md) | [166. sorted() →](166_sorted.md)
