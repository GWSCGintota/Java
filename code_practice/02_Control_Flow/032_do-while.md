[← 031. while loops](031_while_loops.md) | [Course Index](../README.md) | [033. Enhanced for loop →](033_Enhanced_for_loop.md)

# Java do-while Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/02_Control_Flow/032_do-while.md)

These exercises follow [032. do-while](../../Road%20Map/02_Control_Flow/032_do-while.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Run once

**Task:** Show that a false initial condition still permits one do-while body execution.

```java
int count = 0;
do { count++; } while (false);
System.out.println(count);
```

**Expected result:**
```text
1
```

**Concept:** The condition is tested after the body.

## Question 2 — Count upward

**Task:** Print 1 through 3.

```java
int i = 1;
do { System.out.println(i); i++; } while (i <= 3);
```

**Expected result:**
```text
1
2
3
```

**Concept:** The update must move the loop toward termination.

## Question 3 — Countdown

**Task:** Print 3 through 1.

```java
int remaining = 3;
do { System.out.println(remaining); remaining--; } while (remaining > 0);
```

**Expected result:**
```text
3
2
1
```

**Concept:** Post-test loops can express work followed by a repeat decision.

## Question 4 — Zero digit count

**Task:** Count zero as a one-digit number.

```java
int number = 0, digits = 0;
do { digits++; number /= 10; } while (number > 0);
System.out.println(digits);
```

**Expected result:**
```text
1
```

**Concept:** At-least-once execution can naturally handle the zero representation.

## Question 5 — Digit extraction

**Task:** Print digits of positive 120 from right to left.

```java
int number = 120;
do { System.out.println(number % 10); number /= 10; } while (number > 0);
```

**Expected result:**
```text
0
2
1
```

**Concept:** The first extraction occurs before deciding whether more digits remain.

## Question 6 — Retry simulation

**Task:** Count attempts until a fixed success on attempt three.

```java
int attempts = 0; boolean success;
do { attempts++; success = attempts == 3; } while (!success);
System.out.println(attempts);
```

**Expected result:**
```text
3
```

**Concept:** A result from the body can control repetition.

## Question 7 — Bounded retry

**Task:** Stop after three failed simulated attempts.

```java
int attempts = 0; boolean success = false;
do { attempts++; } while (!success && attempts < 3);
System.out.println(attempts);
```

**Expected result:**
```text
3
```

**Concept:** Bound retries explicitly rather than relying on eventual success.

## Question 8 — Validation sequence

**Task:** Skip negative candidates and accept the first positive value.

```java
int[] candidates = {-2, -1, 5}; int index = 0, value;
do { value = candidates[index++]; } while (value <= 0 && index < candidates.length);
System.out.println(value);
```

**Expected result:**
```text
5
```

**Concept:** This fixed non-empty fixture has a valid candidate; general input needs an exhausted-source policy.

## Question 9 — Break after one

**Task:** Exit a do-while from inside its body.

```java
int count = 0;
do { count++; if (count == 1) { break; } } while (true);
System.out.println(count);
```

**Expected result:**
```text
1
```

**Concept:** break bypasses the trailing repeat decision.

## Question 10 — Contrast with while

**Task:** Compare body counts for false conditions.

```java
int before = 0, after = 0;
while (before < 0) { before++; }
do { after++; } while (after < 0);
System.out.println(before + ":" + after);
```

**Expected result:**
```text
0:1
```

**Concept:** Pre-test and post-test loops differ at the first iteration.

---

## Interview Questions and Answers

### Interview Question 1 — How many times does a do-while body execute at minimum?

**Answer:** Once, unless entering the statement itself is prevented by surrounding control flow. The condition is evaluated afterward.

### Interview Question 2 — Is a semicolon required after do-while?

**Answer:** Yes. The statement ends with while (condition);, unlike the usual while loop with a block body.

### Interview Question 3 — When is do-while useful?

**Answer:** When the first attempt or processing step must happen before deciding whether to repeat, such as a validated read or bounded retry.

### Interview Question 4 — What should a retry loop define?

**Answer:** Maximum attempts, success detection, failure handling, and whether the operation is safe to repeat. Production retries may also need delay and cancellation policies.

### Interview Question 5 — Where does continue go in a do-while loop?

**Answer:** It proceeds to evaluation of the trailing condition, rather than jumping directly to the body start.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Run once | The condition is tested after the body. |
| Count upward | The update must move the loop toward termination. |
| Countdown | Post-test loops can express work followed by a repeat decision. |
| Zero digit count | At-least-once execution can naturally handle the zero representation. |
| Digit extraction | The first extraction occurs before deciding whether more digits remain. |

[← 031. while loops](031_while_loops.md) | [Course Index](../README.md) | [033. Enhanced for loop →](033_Enhanced_for_loop.md)
