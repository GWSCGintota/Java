[← 024. if](024_if.md) | [Course Index](../README.md) | [026. else-if →](026_else-if.md)

# Java if-else Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/02_Control_Flow/025_if-else.md)

These exercises follow [025. if-else](../../Road%20Map/02_Control_Flow/025_if-else.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Adult or minor

**Task:** Classify age 16 using if/else.

```java
int age = 16;
if (age >= 18) { System.out.println("Adult"); } else { System.out.println("Minor"); }
```

**Expected result:**
```text
Minor
```

**Concept:** Exactly one branch executes.

## Question 2 — Parity

**Task:** Classify 7 as even or odd.

```java
int number = 7;
if (number % 2 == 0) { System.out.println("Even"); } else { System.out.println("Odd"); }
```

**Expected result:**
```text
Odd
```

**Concept:** Remainder can distinguish parity for negative values too.

## Question 3 — Nonnegative boundary

**Task:** Classify zero as nonnegative.

```java
int value = 0;
if (value >= 0) { System.out.println("Nonnegative"); } else { System.out.println("Negative"); }
```

**Expected result:**
```text
Nonnegative
```

**Concept:** Equality belongs to the branch whose operator includes it.

## Question 4 — Maximum

**Task:** Select the larger of 4 and 9.

```java
int a = 4, b = 9, maximum;
if (a >= b) { maximum = a; } else { maximum = b; }
System.out.println(maximum);
```

**Expected result:**
```text
9
```

**Concept:** Assigning both branches satisfies definite assignment.

## Question 5 — Null policy

**Task:** Print Missing for a null string and its length otherwise.

```java
String text = null;
if (text == null) { System.out.println("Missing"); } else { System.out.println(text.length()); }
```

**Expected result:**
```text
Missing
```

**Concept:** Mutually exclusive branches define a null policy.

## Question 6 — Empty input

**Task:** Distinguish an empty string from a supplied value.

```java
String text = "";
if (text.isEmpty()) { System.out.println("Empty"); } else { System.out.println(text); }
```

**Expected result:**
```text
Empty
```

**Concept:** Empty is a valid String state separate from null.

## Question 7 — Stock policy

**Task:** Reject a request exceeding stock.

```java
int stock = 2, requested = 3;
if (requested > 0 && requested <= stock) { System.out.println("Accepted"); } else { System.out.println("Rejected"); }
```

**Expected result:**
```text
Rejected
```

**Concept:** A domain decision can combine several constraints.

## Question 8 — Login result

**Task:** Report Access denied for a false authentication result.

```java
boolean authenticated = false;
if (authenticated) { System.out.println("Welcome"); } else { System.out.println("Access denied"); }
```

**Expected result:**
```text
Access denied
```

**Concept:** Boolean flags can be used directly as conditions.

## Question 9 — Discount choice

**Task:** Calculate a member or guest price.

```java
boolean member = true; int price;
if (member) { price = 90; } else { price = 100; }
System.out.println(price);
```

**Expected result:**
```text
90
```

**Concept:** Alternative branches can initialize a later result.

## Question 10 — Case-sensitive match

**Task:** Compare a command's content rather than reference identity.

```java
String command = new String("start");
if ("start".equals(command)) { System.out.println("Starting"); } else { System.out.println("Unknown"); }
```

**Expected result:**
```text
Starting
```

**Concept:** String decisions should use an intentional content-comparison policy.

---

## Interview Questions and Answers

### Interview Question 1 — How many branches of an if/else execute?

**Answer:** Exactly one when the condition completes normally. The true branch executes for true and the else branch for false.

### Interview Question 2 — Can else have its own condition?

**Answer:** else alone has no condition; else if is an else containing another if.

### Interview Question 3 — What is the dangling-else rule?

**Answer:** An else associates with the nearest unmatched if allowed by the syntax. Braces remove ambiguity for readers.

### Interview Question 4 — Why assign a local in both branches?

**Answer:** A later read requires definite assignment on every path reaching it.

### Interview Question 5 — When is a conditional expression preferable?

**Answer:** For a short, clear value choice. Use if/else for multi-statement actions or when the expression becomes difficult to read.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Adult or minor | Exactly one branch executes. |
| Parity | Remainder can distinguish parity for negative values too. |
| Nonnegative boundary | Equality belongs to the branch whose operator includes it. |
| Maximum | Assigning both branches satisfies definite assignment. |
| Null policy | Mutually exclusive branches define a null policy. |

[← 024. if](024_if.md) | [Course Index](../README.md) | [026. else-if →](026_else-if.md)
