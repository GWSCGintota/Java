[← 030. for loops](030_for_loops.md) | [Course Index](../README.md) | [032. do-while →](032_do-while.md)

# Java while loops Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/02_Control_Flow/031_while_loops.md)

These exercises follow [031. while loops](../../Road%20Map/02_Control_Flow/031_while_loops.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Count upward

**Task:** Print 1 through 3 with while.

```java
int i = 1;
while (i <= 3) { System.out.println(i); i++; }
```

**Expected result:**
```text
1
2
3
```

**Concept:** A while loop tests before each body execution.

## Question 2 — Zero iterations

**Task:** Demonstrate a false initial condition.

```java
int count = 0;
while (count > 0) { count--; }
System.out.println(count);
```

**Expected result:**
```text
0
```

**Concept:** The body can execute zero times.

## Question 3 — Countdown

**Task:** Print 3, 2, and 1.

```java
int remaining = 3;
while (remaining > 0) { System.out.println(remaining); remaining--; }
```

**Expected result:**
```text
3
2
1
```

**Concept:** Update the state that controls termination.

## Question 4 — Digit count

**Task:** Count decimal digits in positive integer 1234.

```java
int number = 1234, digits = 0;
while (number > 0) { digits++; number /= 10; }
System.out.println(digits);
```

**Expected result:**
```text
4
```

**Concept:** Repeated integer division removes the last positive decimal digit.

## Question 5 — Digit sum

**Task:** Sum the digits of positive integer 123.

```java
int number = 123, sum = 0;
while (number > 0) { sum += number % 10; number /= 10; }
System.out.println(sum);
```

**Expected result:**
```text
6
```

**Concept:** Remainder extracts the last digit while division advances the state.

## Question 6 — Halving

**Task:** Count divisions by two until positive value 16 reaches one.

```java
int value = 16, steps = 0;
while (value > 1) { value /= 2; steps++; }
System.out.println(steps);
```

**Expected result:**
```text
4
```

**Concept:** A progress measure can help explain loop termination.

## Question 7 — Linear search

**Task:** Find the first index of 7 in an array.

```java
int[] values = {3, 7, 9}; int index = 0;
while (index < values.length && values[index] != 7) { index++; }
System.out.println(index < values.length ? index : -1);
```

**Expected result:**
```text
1
```

**Concept:** Order guards so array bounds are checked before element access.

## Question 8 — Sentinel input

**Task:** Sum values before the first zero in a fixed array.

```java
int[] values = {2, 3, 0, 9}; int index = 0, total = 0;
while (index < values.length && values[index] != 0) { total += values[index++]; }
System.out.println(total);
```

**Expected result:**
```text
5
```

**Concept:** A sentinel can terminate processing before the data source ends.

## Question 9 — Iterator traversal

**Task:** Traverse names using an iterator's condition.

```java
Iterator<String> iterator = Arrays.asList("Ada", "Grace").iterator();
while (iterator.hasNext()) { System.out.println(iterator.next()); }
```

**Expected result:**
```text
Ada
Grace
```

**Concept:** hasNext checks availability and next advances the iterator.

## Question 10 — Repeated subtraction

**Task:** Count how many complete groups of three fit in 10.

```java
int remaining = 10, groups = 0;
while (remaining >= 3) { remaining -= 3; groups++; }
System.out.println(groups + ":" + remaining);
```

**Expected result:**
```text
3:1
```

**Concept:** Maintain a clear invariant between processed groups and the remaining quantity.

---

## Interview Questions and Answers

### Interview Question 1 — How does while differ from do-while?

**Answer:** while checks before the body and can run zero times; do-while checks afterward and runs the body at least once.

### Interview Question 2 — What makes a loop terminate?

**Answer:** Its state must eventually make the condition false or reach an explicit control transfer. A condition alone does not guarantee progress.

### Interview Question 3 — Why check bounds first in a search condition?

**Answer:** && evaluates left to right and short-circuits, so the element access is skipped once the index reaches the array length.

### Interview Question 4 — What is a sentinel?

**Answer:** A distinguished data value indicating termination or another control event. Decide whether the sentinel belongs to the data result.

### Interview Question 5 — What is a loop invariant?

**Answer:** A property maintained before and after iterations that helps establish correctness, such as the total representing exactly the values processed so far.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Count upward | A while loop tests before each body execution. |
| Zero iterations | The body can execute zero times. |
| Countdown | Update the state that controls termination. |
| Digit count | Repeated integer division removes the last positive decimal digit. |
| Digit sum | Remainder extracts the last digit while division advances the state. |

[← 030. for loops](030_for_loops.md) | [Course Index](../README.md) | [032. do-while →](032_do-while.md)
