[← 052. Arrays utility class](052_Arrays_utility_class.md) | [Course Index](../README.md) | [054. Searching arrays →](054_Searching_arrays.md)

# Java Sorting arrays Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/04_Arrays/053_Sorting_arrays.md)

These exercises follow [053. Sorting arrays](../../Road%20Map/04_Arrays/053_Sorting_arrays.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Ascending integers

**Task:** Sort integers in ascending order.

```java
int[] values = {5, 1, 3};
Arrays.sort(values);
System.out.println(Arrays.toString(values));
```

**Expected result:**
```text
[1, 3, 5]
```

**Concept:** Primitive sorting uses the natural numeric ordering for this type.

## Question 2 — Negative values

**Task:** Sort mixed negative and positive integers.

```java
int[] values = {2, -3, 0, -1};
Arrays.sort(values);
System.out.println(Arrays.toString(values));
```

**Expected result:**
```text
[-3, -1, 0, 2]
```

**Concept:** Numeric sorting includes negative values normally.

## Question 3 — Duplicates

**Task:** Sort an array containing repeated values.

```java
int[] values = {2, 1, 2, 1};
Arrays.sort(values);
System.out.println(Arrays.toString(values));
```

**Expected result:**
```text
[1, 1, 2, 2]
```

**Concept:** Sorting orders duplicates rather than removing them.

## Question 4 — Empty array

**Task:** Sort an empty array safely.

```java
int[] values = {};
Arrays.sort(values);
System.out.println(Arrays.toString(values));
```

**Expected result:**
```text
[]
```

**Concept:** Empty arrays need no component comparisons.

## Question 5 — Range sort

**Task:** Sort only the middle two components.

```java
int[] values = {9, 3, 1, 8};
Arrays.sort(values, 1, 3);
System.out.println(Arrays.toString(values));
```

**Expected result:**
```text
[9, 1, 3, 8]
```

**Concept:** Range sort uses an inclusive start and exclusive end.

## Question 6 — Descending boxed values

**Task:** Sort an Integer array in reverse order.

```java
Integer[] values = {1, 3, 2};
Arrays.sort(values, Comparator.reverseOrder());
System.out.println(Arrays.toString(values));
```

**Expected result:**
```text
[3, 2, 1]
```

**Concept:** Comparator overloads operate on reference arrays, not int[].

## Question 7 — Strings

**Task:** Sort names by natural ordering.

```java
String[] names = {"Grace", "Ada", "Bob"};
Arrays.sort(names);
System.out.println(Arrays.toString(names));
```

**Expected result:**
```text
[Ada, Bob, Grace]
```

**Concept:** String natural order is lexicographic rather than locale-specific collation.

## Question 8 — Custom ordering

**Task:** Sort strings by length, then natural order.

```java
String[] names = {"Bob", "Grace", "Ada", "Alan"};
Arrays.sort(names, Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()));
System.out.println(Arrays.toString(names));
```

**Expected result:**
```text
[Ada, Bob, Alan, Grace]
```

**Concept:** A secondary key makes a meaningful tie policy explicit.

## Question 9 — Preserve source

**Task:** Sort a clone without changing the original array.

```java
int[] original = {3, 1, 2}, sorted = original.clone();
Arrays.sort(sorted);
System.out.println(Arrays.toString(original));
System.out.println(Arrays.toString(sorted));
```

**Expected result:**
```text
[3, 1, 2]
[1, 2, 3]
```

**Concept:** Sorting is mutating unless you deliberately copy first.

## Question 10 — Null-aware sorting

**Task:** Sort nullable names with null last.

```java
String[] names = {"Bob", null, "Ada"};
Arrays.sort(names, Comparator.nullsLast(Comparator.naturalOrder()));
System.out.println(Arrays.toString(names));
```

**Expected result:**
```text
[Ada, Bob, null]
```

**Concept:** A comparator can define a deliberate policy for null components.

---

## Interview Questions and Answers

### Interview Question 1 — Does Arrays.sort modify the input?

**Answer:** Yes. Clone or copy first when the original order must be preserved.

### Interview Question 2 — Can an int[] be sorted using Comparator.reverseOrder?

**Answer:** Not with the reference-array comparator overload. Use a suitable primitive strategy or boxed values when the tradeoff is justified.

### Interview Question 3 — What does stable sorting mean?

**Answer:** Comparator-equal elements retain their relative encounter order. The object-array sorting contract is stable; primitive-array sorting has different contracts and implementation details.

### Interview Question 4 — Why define a secondary comparator key?

**Answer:** It gives a deterministic domain preference for otherwise equal primary keys, rather than leaving important ties unspecified.

### Interview Question 5 — Must binary search use the same ordering as sorting?

**Answer:** Yes. Sorting and searching must agree on the ordering of the searched range.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Ascending integers | Primitive sorting uses the natural numeric ordering for this type. |
| Negative values | Numeric sorting includes negative values normally. |
| Duplicates | Sorting orders duplicates rather than removing them. |
| Empty array | Empty arrays need no component comparisons. |
| Range sort | Range sort uses an inclusive start and exclusive end. |

[← 052. Arrays utility class](052_Arrays_utility_class.md) | [Course Index](../README.md) | [054. Searching arrays →](054_Searching_arrays.md)
