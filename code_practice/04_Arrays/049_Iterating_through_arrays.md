[← 048. Accessing array elements](048_Accessing_array_elements.md) | [Course Index](../README.md) | [050. Multidimensional arrays →](050_Multidimensional_arrays.md)

# Java Iterating through arrays Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/04_Arrays/049_Iterating_through_arrays.md)

These exercises follow [049. Iterating through arrays](../../Road%20Map/04_Arrays/049_Iterating_through_arrays.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Index loop

**Task:** Print array elements with their indices.

```java
int[] values = {3, 6};
for (int i = 0; i < values.length; i++) { System.out.println(i + ":" + values[i]); }
```

**Expected result:**
```text
0:3
1:6
```

**Concept:** Indexed traversal makes positions available.

## Question 2 — Enhanced loop

**Task:** Print every value without an index.

```java
for (int value : new int[]{2, 4, 6}) { System.out.println(value); }
```

**Expected result:**
```text
2
4
6
```

**Concept:** Enhanced for is concise when only elements matter.

## Question 3 — Sum

**Task:** Calculate the sum of an array.

```java
int total = 0;
for (int value : new int[]{1, 2, 3}) { total += value; }
System.out.println(total);
```

**Expected result:**
```text
6
```

**Concept:** Traversal can accumulate a summary.

## Question 4 — Maximum

**Task:** Find the maximum in a non-empty array.

```java
int[] values = {-4, -2, -9}; int maximum = values[0];
for (int i = 1; i < values.length; i++) { maximum = Math.max(maximum, values[i]); }
System.out.println(maximum);
```

**Expected result:**
```text
-2
```

**Concept:** Seed from an actual element rather than zero when all values may be negative.

## Question 5 — Reverse order

**Task:** Traverse from the last index down to zero.

```java
int[] values = {1, 2, 3};
for (int i = values.length - 1; i >= 0; i--) { System.out.println(values[i]); }
```

**Expected result:**
```text
3
2
1
```

**Concept:** Reversing traversal need not mutate the array.

## Question 6 — Count a condition

**Task:** Count positive components.

```java
int count = 0;
for (int value : new int[]{-1, 0, 2, 3}) { if (value > 0) { count++; } }
System.out.println(count);
```

**Expected result:**
```text
2
```

**Concept:** Selection and accumulation can share a traversal.

## Question 7 — Update in place

**Task:** Double every component using indices.

```java
int[] values = {1, 2, 3};
for (int i = 0; i < values.length; i++) { values[i] *= 2; }
System.out.println(Arrays.toString(values));
```

**Expected result:**
```text
[2, 4, 6]
```

**Concept:** Indexed assignment replaces stored values.

## Question 8 — Copy by traversal

**Task:** Copy an int array into separately allocated storage.

```java
int[] source = {4, 7}, copy = new int[source.length];
for (int i = 0; i < source.length; i++) { copy[i] = source[i]; }
copy[0] = 9;
System.out.println(source[0] + ":" + copy[0]);
```

**Expected result:**
```text
4:9
```

**Concept:** Explicit element copying can preserve independent primitive storage.

## Question 9 — Empty traversal

**Task:** Count visits to an empty array.

```java
int count = 0;
for (int value : new int[0]) { count++; }
System.out.println(count);
```

**Expected result:**
```text
0
```

**Concept:** Empty arrays are safely traversed with zero iterations.

## Question 10 — Early search

**Task:** Stop at the first component equal to 7.

```java
int[] values = {2, 7, 7}; int found = -1;
for (int i = 0; i < values.length; i++) { if (values[i] == 7) { found = i; break; } }
System.out.println(found);
```

**Expected result:**
```text
1
```

**Concept:** Early termination enforces a first-match policy.

---

## Interview Questions and Answers

### Interview Question 1 — When should an indexed loop be preferred?

**Answer:** When positions, reverse order, neighboring components, or element replacement are required.

### Interview Question 2 — Does enhanced-for assignment change primitive array elements?

**Answer:** No. The loop variable receives each primitive value; changing it does not assign back into the array.

### Interview Question 3 — What is the time complexity of a full array traversal?

**Answer:** O(n) for n components when the per-component work is bounded by a constant.

### Interview Question 4 — How should a maximum operation handle an empty array?

**Answer:** Reject it or return an explicit absence representation. Do not silently invent zero as a maximum unless the domain defines that policy.

### Interview Question 5 — Why initialize a maximum from the first component?

**Answer:** It preserves correctness for negative-only arrays. Initializing to zero would incorrectly return zero for such inputs.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Index loop | Indexed traversal makes positions available. |
| Enhanced loop | Enhanced for is concise when only elements matter. |
| Sum | Traversal can accumulate a summary. |
| Maximum | Seed from an actual element rather than zero when all values may be negative. |
| Reverse order | Reversing traversal need not mutate the array. |

[← 048. Accessing array elements](048_Accessing_array_elements.md) | [Course Index](../README.md) | [050. Multidimensional arrays →](050_Multidimensional_arrays.md)
