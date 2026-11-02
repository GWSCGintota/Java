[← 029. Modern switch expressions](029_Modern_switch_expressions.md) | [Course Index](../README.md) | [031. while loops →](031_while_loops.md)

# Java for loops Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/02_Control_Flow/030_for_loops.md)

These exercises follow [030. for loops](../../Road%20Map/02_Control_Flow/030_for_loops.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Count upward

**Task:** Print 1 through 3 with a for loop.

```java
for (int i = 1; i <= 3; i++) { System.out.println(i); }
```

**Expected result:**
```text
1
2
3
```

**Concept:** Initialization, condition, and update control an ordinary for loop.

## Question 2 — Count downward

**Task:** Print 3 through 1.

```java
for (int i = 3; i >= 1; i--) { System.out.println(i); }
```

**Expected result:**
```text
3
2
1
```

**Concept:** A decreasing counter needs a compatible condition and update.

## Question 3 — Sum a range

**Task:** Sum integers 1 through 5.

```java
int total = 0;
for (int i = 1; i <= 5; i++) { total += i; }
System.out.println(total);
```

**Expected result:**
```text
15
```

**Concept:** Initialize an accumulator before repeated updates.

## Question 4 — Step size

**Task:** Print positive even values up to 6 using a step of two.

```java
for (int i = 2; i <= 6; i += 2) { System.out.println(i); }
```

**Expected result:**
```text
2
4
6
```

**Concept:** The update expression can use a custom step.

## Question 5 — Zero iterations

**Task:** Start with a false condition and print Done afterward.

```java
for (int i = 5; i < 3; i++) { System.out.println(i); }
System.out.println("Done");
```

**Expected result:**
```text
Done
```

**Concept:** A for loop tests its condition before its first body execution.

## Question 6 — Array indices

**Task:** Print each value with its array index.

```java
int[] values = {4, 7};
for (int i = 0; i < values.length; i++) { System.out.println(i + ":" + values[i]); }
```

**Expected result:**
```text
0:4
1:7
```

**Concept:** Valid indices run from zero through length minus one.

## Question 7 — Factorial

**Task:** Calculate 5 factorial with a loop.

```java
int factorial = 1;
for (int i = 2; i <= 5; i++) { factorial *= i; }
System.out.println(factorial);
```

**Expected result:**
```text
120
```

**Concept:** Multiplicative accumulation starts from one; larger inputs need an overflow policy.

## Question 8 — Nested grid

**Task:** Print a 2-by-2 grid of coordinates.

```java
for (int row = 0; row < 2; row++) { for (int column = 0; column < 2; column++) { System.out.println(row + ":" + column); } }
```

**Expected result:**
```text
0:0
0:1
1:0
1:1
```

**Concept:** The inner loop completes for each outer iteration.

## Question 9 — Two counters

**Task:** Move two indices toward each other.

```java
for (int left = 0, right = 3; left < right; left++, right--) { System.out.println(left + ":" + right); }
```

**Expected result:**
```text
0:3
1:2
```

**Concept:** A for header can initialize and update compatible multiple counters.

## Question 10 — Reverse traversal

**Task:** Print an array in reverse without modifying it.

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

**Concept:** Index-based traversal supports reverse order and handles an empty array with zero iterations.

---

## Interview Questions and Answers

### Interview Question 1 — When does a for loop evaluate its condition?

**Answer:** Before every body execution, including the first. Its update runs after each normally completed body iteration and after an unlabeled continue targeting that loop.

### Interview Question 2 — Where is a variable declared in the for initializer visible?

**Answer:** In the loop condition, update, and body, not after the loop statement.

### Interview Question 3 — What causes an off-by-one error?

**Answer:** A boundary includes or excludes one unintended position, commonly using <= array.length instead of < array.length.

### Interview Question 4 — Can parts of a for header be omitted?

**Answer:** Yes. An omitted condition acts as true; the programmer must still provide a deliberate termination policy when termination is required.

### Interview Question 5 — When is an enhanced for loop clearer?

**Answer:** When only each element is needed and no explicit index, reverse order, or index-based update is required.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Count upward | Initialization, condition, and update control an ordinary for loop. |
| Count downward | A decreasing counter needs a compatible condition and update. |
| Sum a range | Initialize an accumulator before repeated updates. |
| Step size | The update expression can use a custom step. |
| Zero iterations | A for loop tests its condition before its first body execution. |

[← 029. Modern switch expressions](029_Modern_switch_expressions.md) | [Course Index](../README.md) | [031. while loops →](031_while_loops.md)
