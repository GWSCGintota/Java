[← 025. if-else](025_if-else.md) | [Course Index](../README.md) | [027. Nested if →](027_Nested_if.md)

# Java else-if Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/02_Control_Flow/026_else-if.md)

These exercises follow [026. else-if](../../Road%20Map/02_Control_Flow/026_else-if.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Sign

**Task:** Classify -4 as positive, zero, or negative.

```java
int n = -4;
if (n > 0) { System.out.println("Positive"); } else if (n == 0) { System.out.println("Zero"); } else { System.out.println("Negative"); }
```

**Expected result:**
```text
Negative
```

**Concept:** A chain stops after its first matching condition.

## Question 2 — Grades

**Task:** Assign A for 90+, B for 80+, and C otherwise; test 85.

```java
int score = 85;
if (score >= 90) { System.out.println("A"); } else if (score >= 80) { System.out.println("B"); } else { System.out.println("C"); }
```

**Expected result:**
```text
B
```

**Concept:** Order overlapping threshold checks from most restrictive to least.

## Question 3 — Grade boundary

**Task:** Check score 90 against the same descending thresholds.

```java
int score = 90;
if (score >= 90) { System.out.println("A"); } else if (score >= 80) { System.out.println("B"); } else { System.out.println("C"); }
```

**Expected result:**
```text
A
```

**Concept:** Exact thresholds belong to inclusive branches.

## Question 4 — Invalid score

**Task:** Reject a score outside 0–100 before assigning a grade.

```java
int score = 120;
if (score < 0||score > 100) { System.out.println("Invalid"); } else if (score >= 90) { System.out.println("A"); } else { System.out.println("Below A"); }
```

**Expected result:**
```text
Invalid
```

**Concept:** Validate the domain before classification.

## Question 5 — Age bands

**Task:** Classify age 70 as child, adult, or senior.

```java
int age = 70;
if (age < 18) { System.out.println("Child"); } else if (age < 65) { System.out.println("Adult"); } else { System.out.println("Senior"); }
```

**Expected result:**
```text
Senior
```

**Concept:** Earlier failures narrow the remaining possible range.

## Question 6 — Text states

**Task:** Distinguish null, empty, and supplied strings in safe order.

```java
String text = "";
if (text == null) { System.out.println("Missing"); } else if (text.isEmpty()) { System.out.println("Empty"); } else { System.out.println("Supplied"); }
```

**Expected result:**
```text
Empty
```

**Concept:** Guard null before invoking instance methods.

## Question 7 — Temperature

**Task:** Classify 0 degrees as freezing, mild, or hot with a freezing threshold of zero.

```java
int temperature = 0;
if (temperature <= 0) { System.out.println("Freezing"); } else if (temperature < 30) { System.out.println("Mild"); } else { System.out.println("Hot"); }
```

**Expected result:**
```text
Freezing
```

**Concept:** Boundary choices encode domain rules.

## Question 8 — Shipping bands

**Task:** Compute shipping of 0 for orders 100+, 5 for 50+, and 10 otherwise.

```java
int order = 60, shipping;
if (order >= 100) { shipping = 0; } else if (order >= 50) { shipping = 5; } else { shipping = 10; }
System.out.println(shipping);
```

**Expected result:**
```text
5
```

**Concept:** An else fallback can complete result assignment.

## Question 9 — Command dispatch

**Task:** Handle start, stop, and unknown commands using content comparisons.

```java
String command = "stop";
if ("start".equals(command)) { System.out.println("Starting"); } else if ("stop".equals(command)) { System.out.println("Stopping"); } else { System.out.println("Unknown"); }
```

**Expected result:**
```text
Stopping
```

**Concept:** Literal-side equals handles nullable commands safely.

## Question 10 — Exclusive outcomes

**Task:** Show that the first matching branch suppresses later true conditions.

```java
int n = 12;
if (n > 10) { System.out.println("Above ten"); } else if (n > 0) { System.out.println("Positive"); } else { System.out.println("Other"); }
```

**Expected result:**
```text
Above ten
```

**Concept:** An else-if chain differs from independent if statements.

---

## Interview Questions and Answers

### Interview Question 1 — How are conditions evaluated in an else-if chain?

**Answer:** In source order until the first true condition; later conditions and branches are skipped.

### Interview Question 2 — Why do threshold checks need careful ordering?

**Answer:** A broad early condition can consume values intended for a narrower later branch, making that branch unreachable in practice.

### Interview Question 3 — Is the final else required?

**Answer:** No, but without it no branch may execute. Later variable reads still must satisfy definite-assignment rules.

### Interview Question 4 — When is switch clearer?

**Answer:** For discrete alternatives based on one selector. Relational ranges often remain clearer as ordered if/else-if conditions.

### Interview Question 5 — How should invalid inputs be handled?

**Answer:** Validate them before ordinary classification or include a deliberate invalid branch so they are not silently placed into a normal category.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Sign | A chain stops after its first matching condition. |
| Grades | Order overlapping threshold checks from most restrictive to least. |
| Grade boundary | Exact thresholds belong to inclusive branches. |
| Invalid score | Validate the domain before classification. |
| Age bands | Earlier failures narrow the remaining possible range. |

[← 025. if-else](025_if-else.md) | [Course Index](../README.md) | [027. Nested if →](027_Nested_if.md)
