[← 034. break](034_break.md) | [Course Index](../README.md) | [036. Creating methods →](../03_Methods/036_Creating_methods.md)

# Java continue Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/02_Control_Flow/035_continue.md)

These exercises follow [035. continue](../../Road%20Map/02_Control_Flow/035_continue.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Skip one value

**Task:** Print 1 and 3 while skipping 2.

```java
for (int i = 1; i <= 3; i++) { if (i == 2) { continue; } System.out.println(i); }
```

**Expected result:**
```text
1
3
```

**Concept:** continue skips the rest of the current iteration.

## Question 2 — Skip negatives

**Task:** Sum only nonnegative values.

```java
int total = 0;
for (int value : new int[]{-2, 3, -1, 4}) { if (value < 0) { continue; } total += value; }
System.out.println(total);
```

**Expected result:**
```text
7
```

**Concept:** Early iteration rejection keeps the main processing path simple.

## Question 3 — Skip odd values

**Task:** Print only even values.

```java
for (int i = 1; i <= 6; i++) { if (i % 2 != 0) { continue; } System.out.println(i); }
```

**Expected result:**
```text
2
4
6
```

**Concept:** A for loop still runs its update after continue.

## Question 4 — Null guard

**Task:** Skip null strings before printing lengths.

```java
for (String text : Arrays.asList("Ada", null, "Grace")) { if (text == null) { continue; } System.out.println(text.length()); }
```

**Expected result:**
```text
3
5
```

**Concept:** A guard can skip invalid elements before dereferencing them.

## Question 5 — Blank lines

**Task:** Print only nonblank input lines after trimming.

```java
for (String line : Arrays.asList("", " Java ", "  ", "Ada")) { String trimmed = line.trim(); if (trimmed.isEmpty()) { continue; } System.out.println(trimmed); }
```

**Expected result:**
```text
Java
Ada
```

**Concept:** Normalization before validation can clarify the intended blank policy.

## Question 6 — While progress

**Task:** Skip 2 in a while loop without losing the state update.

```java
int i = 0;
while (i < 3) { i++; if (i == 2) { continue; } System.out.println(i); }
```

**Expected result:**
```text
1
3
```

**Concept:** Ensure continue cannot bypass the update required for termination.

## Question 7 — Do-while condition

**Task:** Skip even output while counting 1 through 3 with do-while.

```java
int i = 0;
do { i++; if (i % 2 == 0) { continue; } System.out.println(i); } while (i < 3);
```

**Expected result:**
```text
1
3
```

**Concept:** continue in do-while proceeds to the trailing condition.

## Question 8 — Nested loop

**Task:** Skip column 2 separately for each row.

```java
for (int row = 1; row <= 2; row++) { for (int column = 1; column <= 3; column++) { if (column == 2) { continue; } System.out.println(row + ":" + column); } }
```

**Expected result:**
```text
1:1
1:3
2:1
2:3
```

**Concept:** Unlabeled continue targets the innermost loop.

## Question 9 — Labeled continue

**Task:** Skip the rest of an outer row when it contains zero.

```java
int[][] rows = {{1, 0}, {2, 3}};
outer: for (int[] row : rows) { for (int value : row) { if (value == 0) { continue outer; } } System.out.println(Arrays.toString(row)); }
```

**Expected result:**
```text
[2, 3]
```

**Concept:** A labeled continue must target an enclosing loop.

## Question 10 — Skip duplicates

**Task:** Print only the first occurrence of each word.

```java
Set<String> seen = new HashSet<>();
for (String word : Arrays.asList("Java", "Ada", "Java")) { if (!seen.add(word)) { continue; } System.out.println(word); }
```

**Expected result:**
```text
Java
Ada
```

**Concept:** A set membership result can control whether processing continues for an element.

---

## Interview Questions and Answers

### Interview Question 1 — What does continue skip?

**Answer:** The remaining statements of the current target-loop iteration, then control follows that loop's update or condition rules.

### Interview Question 2 — Does continue skip a for-loop update?

**Answer:** No. The ordinary for update executes before the next condition test.

### Interview Question 3 — Why can continue cause an infinite while loop?

**Answer:** If it bypasses the state update required to change the condition, the same rejected state may repeat forever.

### Interview Question 4 — Can continue target a switch?

**Answer:** Not by itself. It must target a loop, though a continue inside a switch nested in a loop can continue that loop.

### Interview Question 5 — When is a guard with continue clearer than nested if?

**Answer:** When many elements are rejected early and the remaining body can then read as one straight processing path. Avoid scattering transfers so heavily that the loop becomes hard to follow.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Skip one value | continue skips the rest of the current iteration. |
| Skip negatives | Early iteration rejection keeps the main processing path simple. |
| Skip odd values | A for loop still runs its update after continue. |
| Null guard | A guard can skip invalid elements before dereferencing them. |
| Blank lines | Normalization before validation can clarify the intended blank policy. |

[← 034. break](034_break.md) | [Course Index](../README.md) | [036. Creating methods →](../03_Methods/036_Creating_methods.md)
