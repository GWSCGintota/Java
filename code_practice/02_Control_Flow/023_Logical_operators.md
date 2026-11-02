[← 022. Comparison operators](022_Comparison_operators.md) | [Course Index](../README.md) | [024. if →](024_if.md)

# Java Logical operators Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/02_Control_Flow/023_Logical_operators.md)

These exercises follow [023. Logical operators](../../Road%20Map/02_Control_Flow/023_Logical_operators.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — AND

**Task:** Require both paid and available.

```java
boolean paid = true, available = false;
System.out.println(paid && available);
```

**Expected result:**
```text
false
```

**Concept:** && is true only when both operands are true.

## Question 2 — OR

**Task:** Permit either a member or a guest pass.

```java
boolean member = false, guestPass = true;
System.out.println(member||guestPass);
```

**Expected result:**
```text
true
```

**Concept:** || is true when at least one operand is true.

## Question 3 — NOT

**Task:** Reverse a closed flag.

```java
boolean closed = true;
System.out.println(!closed);
```

**Expected result:**
```text
false
```

**Concept:** ! reverses one boolean result.

## Question 4 — Null guard

**Task:** Avoid dereferencing null with short-circuit AND.

```java
String text = null;
System.out.println(text != null && !text.isEmpty());
```

**Expected result:**
```text
false
```

**Concept:** The right operand is skipped when the left is false.

## Question 5 — Safe division

**Task:** Check for a zero divisor before division.

```java
int divisor = 0;
System.out.println(divisor != 0 && 10 / divisor > 1);
```

**Expected result:**
```text
false
```

**Concept:** Short-circuit ordering can prevent an invalid operation.

## Question 6 — OR short circuit

**Task:** Avoid evaluating a failing alternative after true.

```java
int divisor = 0;
System.out.println(true||10 / divisor > 1);
```

**Expected result:**
```text
true
```

**Concept:** The right side of logical OR is skipped when the left is true.

## Question 7 — Exclusive OR

**Task:** Accept exactly one of two boolean choices.

```java
boolean first = true, second = false;
System.out.println(first ^ second);
```

**Expected result:**
```text
true
```

**Concept:** Boolean ^ is true when its operands differ.

## Question 8 — Eager AND

**Task:** Show that boolean & evaluates the right operand.

```java
int count = 0;
boolean result = false & (++count > 0);
System.out.println(result + ":" + count);
```

**Expected result:**
```text
false:1
```

**Concept:** Boolean & has no short-circuit behavior.

## Question 9 — Grouped alternatives

**Task:** Require an open venue and either ticket type.

```java
boolean open = true, standard = false, premium = true;
System.out.println(open && (standard||premium));
```

**Expected result:**
```text
true
```

**Concept:** Grouping makes alternative requirements explicit.

## Question 10 — De Morgan equivalence

**Task:** Compare two equivalent ways to reject either of two failures.

```java
boolean firstFailed = true, secondFailed = false;
System.out.println(!(firstFailed||secondFailed));
System.out.println(!firstFailed && !secondFailed);
```

**Expected result:**
```text
false
false
```

**Concept:** Negating OR turns it into AND of the negated operands.

---

## Interview Questions and Answers

### Interview Question 1 — Which logical operators short-circuit?

**Answer:** && and ||. The right operand is evaluated only if it can affect the result.

### Interview Question 2 — How does boolean & differ from &&?

**Answer:** Both compute conjunction, but & always evaluates both operands while && can skip the second.

### Interview Question 3 — What does boolean ^ mean?

**Answer:** Exclusive OR: true when exactly one operand is true. Both operands are evaluated.

### Interview Question 4 — What are De Morgan's laws?

**Answer:** !(a && b) equals !a || !b, and !(a || b) equals !a && !b. Side effects can complicate refactoring even when boolean results are equivalent.

### Interview Question 5 — Why put a null check first in a conjunction?

**Answer:** It lets short-circuit evaluation skip a later dereference when the value is null.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| AND | && is true only when both operands are true. |
| OR | \|\| is true when at least one operand is true. |
| NOT | ! reverses one boolean result. |
| Null guard | The right operand is skipped when the left is false. |
| Safe division | Short-circuit ordering can prevent an invalid operation. |

[← 022. Comparison operators](022_Comparison_operators.md) | [Course Index](../README.md) | [024. if →](024_if.md)
