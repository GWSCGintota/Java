[← 033. Enhanced for loop](033_Enhanced_for_loop.md) | [Course Index](../README.md) | [035. continue →](035_continue.md)

# Java break Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/02_Control_Flow/034_break.md)

These exercises follow [034. break](../../Road%20Map/02_Control_Flow/034_break.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Stop at a value

**Task:** Print integers before the first 3.

```java
for (int i = 1; i <= 5; i++) { if (i == 3) { break; } System.out.println(i); }
```

**Expected result:**
```text
1
2
```

**Concept:** break exits the innermost enclosing loop.

## Question 2 — First match

**Task:** Find the first even value in an array.

```java
int found = -1;
for (int value : new int[]{1, 5, 8, 10}) { if (value % 2 == 0) { found = value; break; } }
System.out.println(found);
```

**Expected result:**
```text
8
```

**Concept:** Early exit avoids scanning after the required match.

## Question 3 — No match

**Task:** Preserve an explicit absence value if no match is found.

```java
int found = -1;
for (int value : new int[]{1, 3, 5}) { if (value % 2 == 0) { found = value; break; } }
System.out.println(found);
```

**Expected result:**
```text
-1
```

**Concept:** A sentinel policy must fit the domain of valid results.

## Question 4 — While exit

**Task:** Exit an otherwise unbounded while after three iterations.

```java
int count = 0;
while (true) { count++; if (count == 3) { break; } }
System.out.println(count);
```

**Expected result:**
```text
3
```

**Concept:** A loop with true condition needs another termination mechanism.

## Question 5 — Sentinel processing

**Task:** Sum values before zero.

```java
int total = 0;
for (int value : new int[]{2, 3, 0, 9}) { if (value == 0) { break; } total += value; }
System.out.println(total);
```

**Expected result:**
```text
5
```

**Concept:** Position the exit before accumulation when the sentinel is excluded.

## Question 6 — Nearest loop

**Task:** Break only the inner loop of a nested traversal.

```java
for (int row = 1; row <= 2; row++) { for (int column = 1; column <= 3; column++) { System.out.println(row + ":" + column); break; } }
```

**Expected result:**
```text
1:1
2:1
```

**Concept:** An unlabeled break does not exit every enclosing loop.

## Question 7 — Labeled break

**Task:** Stop an entire matrix search at value 3.

```java
int[][] matrix = {{1, 2}, {3, 4}}; int foundRow = -1;
search: for (int row = 0; row < matrix.length; row++) { for (int value : matrix[row]) { if (value == 3) { foundRow = row; break search; } } }
System.out.println(foundRow);
```

**Expected result:**
```text
1
```

**Concept:** A labeled break exits the named enclosing statement.

## Question 8 — Switch exit

**Task:** Prevent classic switch fallthrough.

```java
switch (1) { case 1: System.out.println("One"); break; default: System.out.println("Other"); }
System.out.println("After switch");
```

**Expected result:**
```text
One
After switch
```

**Concept:** break can exit a switch as well as a loop.

## Question 9 — Composite detection

**Task:** Stop divisor checks once a factor of 15 is found.

```java
int number = 15; boolean composite = false;
for (int divisor = 2; divisor <= number / divisor; divisor++) { if (number % divisor == 0) { composite = true; break; } }
System.out.println(composite);
```

**Expected result:**
```text
true
```

**Concept:** One factor is sufficient evidence of compositeness for this positive input.

## Question 10 — Break does not return

**Task:** Break a loop and continue with the next statement.

```java
for (int i = 0; i < 3; i++) { break; }
System.out.println("Method continues");
```

**Expected result:**
```text
Method continues
```

**Concept:** Loop exit differs from returning from the enclosing method.

---

## Interview Questions and Answers

### Interview Question 1 — What does an unlabeled break target?

**Answer:** The innermost enclosing switch, while, do-while, or for statement.

### Interview Question 2 — How does break differ from continue?

**Answer:** break exits the target loop or switch; continue advances to the target loop's next iteration logic.

### Interview Question 3 — How does break differ from return?

**Answer:** break exits an enclosing statement, while return exits the method or lambda invocation that contains it.

### Interview Question 4 — When is a labeled break useful?

**Answer:** For an intentional exit from multiple nested statements, such as a matrix search. Extracting a helper with a return may sometimes be clearer.

### Interview Question 5 — Does break skip a finally block?

**Answer:** Not ordinarily. A finally block runs during the control transfer, and if it completes abruptly it can replace the pending transfer.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Stop at a value | break exits the innermost enclosing loop. |
| First match | Early exit avoids scanning after the required match. |
| No match | A sentinel policy must fit the domain of valid results. |
| While exit | A loop with true condition needs another termination mechanism. |
| Sentinel processing | Position the exit before accumulation when the sentinel is excluded. |

[← 033. Enhanced for loop](033_Enhanced_for_loop.md) | [Course Index](../README.md) | [035. continue →](035_continue.md)
