[← 170. reduce()](170_reduce.md) | [Course Index](../README.md) | [172. Collectors →](172_Collectors.md)

# Java collect() Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/171_collect.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — Explicit mutable list

**Task:** Double [1, 2, 3] into an ArrayList and append 8.

```java
List<Integer> result = Stream.of(1, 2, 3).map(n -> n * 2).collect(Collectors.toCollection(ArrayList::new));
result.add(8);
System.out.println(result);
```

**Output:**
```text
[2, 4, 6, 8]
```

**Concept:** An explicit container factory supplies a required mutable implementation.

## Question 2 — Ordered unique collection

**Task:** Collect [Ada, Bob, Ada, Grace] into a LinkedHashSet.

```java
Set<String> result = Stream.of("Ada", "Bob", "Ada", "Grace")
        .collect(Collectors.toCollection(LinkedHashSet::new));
System.out.println(result);
```

**Output:**
```text
[Ada, Bob, Grace]
```

**Concept:** A chosen set factory defines uniqueness and iteration order.

## Question 3 — Unique map keys

**Task:** Map Ada and Grace to their lengths and print individual lookups.

```java
Map<String, Integer> lengths = Stream.of("Ada", "Grace")
        .collect(Collectors.toMap(Function.identity(), String::length));
System.out.println(lengths.get("Ada"));
System.out.println(lengths.get("Grace"));
```

**Output:**
```text
3
5
```

**Concept:** toMap maps keys and values; individual lookups avoid unspecified default map order.

## Question 4 — Duplicate-key rejection

**Task:** Attempt to collect [Ada, Ada] using two-argument toMap, catch the error, and print Duplicate key.

```java
try { Stream.of("Ada", "Ada")
        .collect(Collectors.toMap(Function.identity(), String::length)); } catch (IllegalStateException e) { System.out.println("Duplicate key"); }
```

**Output:**
```text
Duplicate key
```

**Concept:** The two-argument map collector rejects duplicate mapped keys.

## Question 5 — Frequency map with merging

**Task:** Count [java, lambda, java] using toMap and an addition merge function.

```java
Map<String, Integer> counts = Stream.of("java", "lambda", "java")
        .collect(Collectors.toMap(Function.identity(), word -> 1, Integer::sum));
System.out.println(counts.get("java"));
System.out.println(counts.get("lambda"));
```

**Output:**
```text
2
1
```

**Concept:** A merge function defines the collision policy.

## Question 6 — Explicit ordered map

**Task:** Collect [Grace, Ada, Bob] to a LinkedHashMap of lengths and print its keys.

```java
Map<String, Integer> lengths = Stream.of("Grace", "Ada", "Bob")
        .collect(Collectors.toMap(Function.identity(), String::length, (a, b) -> a, LinkedHashMap::new));
System.out.println(lengths.keySet());
```

**Output:**
```text
[Grace, Ada, Bob]
```

**Concept:** A map factory expresses an iteration-order requirement.

## Question 7 — Three-argument list collection

**Task:** Copy [Ada, Grace, Alan] from a parallel stream using supplier, accumulator, and combiner.

```java
List<String> result = Stream.of("Ada", "Grace", "Alan").parallel()
        .collect(ArrayList::new, List::add, List::addAll);
System.out.println(result);
```

**Output:**
```text
[Ada, Grace, Alan]
```

**Concept:** Fresh partial lists and controlled merging avoid one captured shared list.

## Question 8 — Finished unmodifiable list

**Task:** Collect [Ada, Grace] into an unmodifiable Java 8 list, then catch an attempted add.

```java
List<String> result = Stream.of("Ada", "Grace")
        .collect(Collectors.collectingAndThen(Collectors.toCollection(ArrayList::new), Collections::unmodifiableList));
System.out.println(result);
try { result.add("Bob"); } catch (UnsupportedOperationException e) { System.out.println("Unmodifiable"); }
```

**Output:**
```text
[Ada, Grace]
Unmodifiable
```

**Concept:** A finishing operation can adapt a privately built result without deep-copying its elements.

## Question 9 — Mutable string accumulation

**Task:** Concatenate A, B, and C using three-argument collect with StringBuilder.

```java
StringBuilder result = Stream.of("A", "B", "C").parallel()
        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
System.out.println(result);
```

**Output:**
```text
ABC
```

**Concept:** Each partition gets fresh mutable state; the combiner appends one partial result to another.

## Question 10 — Empty explicit container

**Task:** Collect an empty string stream into an ArrayList, append Ada, and print it.

```java
List<String> result = Stream.<String>empty().collect(Collectors.toCollection(ArrayList::new));
result.add("Ada");
System.out.println(result);
```

**Output:**
```text
[Ada]
```

**Concept:** An explicit factory also defines the empty result's usable container contract.

---

## Interview Questions and Answers

### Interview Question 1 — What does a collector's accumulation type represent?

**Answer:** Its intermediate mutable state, which can differ from the finished result type.

### Interview Question 2 — Why can parallel collect use ArrayList?

**Answer:** Correct non-concurrent collection creates isolated partial containers and merges them rather than concurrently appending to one captured list.

### Interview Question 3 — Are Stream.toList and Collectors.toList interchangeable?

**Answer:** No. Their availability, mutability, null, and implementation contracts differ; choose based on required behavior.

### Interview Question 4 — Why is a supplier returning one captured list invalid for parallel collect?

**Answer:** Partial reductions need independently created containers. Returning the same object shares mutable accumulation state and breaks that protocol.

### Interview Question 5 — Does an unmodifiable list imply immutable elements?

**Answer:** No. It restricts operations through the list interface; its referenced elements can still be mutable.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| Explicit mutable list | An explicit container factory supplies a required mutable implementation. |
| Ordered unique collection | A chosen set factory defines uniqueness and iteration order. |
| Unique map keys | toMap maps keys and values; individual lookups avoid unspecified default map order. |
| Duplicate-key rejection | The two-argument map collector rejects duplicate mapped keys. |
| Frequency map with merging | A merge function defines the collision policy. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/171_collect.md)

[← 170. reduce()](170_reduce.md) | [Course Index](../README.md) | [172. Collectors →](172_Collectors.md)
