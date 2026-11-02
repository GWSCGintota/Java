[← 026. else-if](026_else-if.md) | [Course Index](../README.md) | [028. switch →](028_switch.md)

# Java Nested if Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/02_Control_Flow/027_Nested_if.md)

These exercises follow [027. Nested if](../../Road%20Map/02_Control_Flow/027_Nested_if.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Nested eligibility

**Task:** Require adulthood before checking membership.

```java
int age = 20; boolean member = true;
if (age >= 18) { if (member) { System.out.println("Eligible member"); } }
```

**Expected result:**
```text
Eligible member
```

**Concept:** An inner decision runs only when the outer path is taken.

## Question 2 — Outer failure

**Task:** Show that a failed outer age check skips the inner decision.

```java
int age = 16; boolean member = true;
if (age >= 18) { if (member) { System.out.println("Eligible"); } }
System.out.println("Checked");
```

**Expected result:**
```text
Checked
```

**Concept:** Inner checks cannot override a failed outer condition.

## Question 3 — Nested alternatives

**Task:** Distinguish adult guests from adult members.

```java
int age = 20; boolean member = false;
if (age >= 18) { if (member) { System.out.println("Member"); } else { System.out.println("Guest"); } }
```

**Expected result:**
```text
Guest
```

**Concept:** The inner else belongs to the inner if.

## Question 4 — Null then length

**Task:** Check presence before testing string length.

```java
String text = "Java";
if (text != null) { if (text.length() >= 4) { System.out.println("Long enough"); } }
```

**Expected result:**
```text
Long enough
```

**Concept:** An outer guard can establish safety for inner operations.

## Question 5 — Array then element

**Task:** Check array length before reading its first value.

```java
int[] values = {7};
if (values.length > 0) { if (values[0] > 0) { System.out.println("Positive first"); } }
```

**Expected result:**
```text
Positive first
```

**Concept:** Nested guards can protect indexed access.

## Question 6 — Two-dimensional decision

**Task:** Validate a row before reading its first column.

```java
int[][] matrix = {{9}};
if (matrix.length > 0) { if (matrix[0] != null && matrix[0].length > 0) { System.out.println(matrix[0][0]); } }
```

**Expected result:**
```text
9
```

**Concept:** A non-empty outer array does not guarantee a usable inner row.

## Question 7 — Stock and payment

**Task:** Check sufficient stock, then payment success.

```java
int stock = 3; boolean paid = false;
if (stock > 0) { if (paid) { System.out.println("Dispatch"); } else { System.out.println("Await payment"); } } else { System.out.println("Out of stock"); }
```

**Expected result:**
```text
Await payment
```

**Concept:** Separate branches explain distinct decision failures.

## Question 8 — Braces clarify association

**Task:** Bind the outer else explicitly using braces.

```java
boolean outer = false, inner = true;
if (outer) { if (inner) { System.out.println("Both"); } } else { System.out.println("Outer false"); }
```

**Expected result:**
```text
Outer false
```

**Concept:** Braces make else association explicit.

## Question 9 — Flatten equivalent guards

**Task:** Replace two nested true requirements with one conjunction.

```java
boolean authenticated = true, authorized = true;
if (authenticated && authorized) { System.out.println("Allowed"); }
```

**Expected result:**
```text
Allowed
```

**Concept:** Flattening can reduce nesting when branch-specific handling is unnecessary.

## Question 10 — Multiple inner outcomes

**Task:** First validate a score, then distinguish pass from fail.

```java
int score = 45;
if (score >= 0 && score <= 100) { if (score >= 50) { System.out.println("Pass"); } else { System.out.println("Fail"); } } else { System.out.println("Invalid"); }
```

**Expected result:**
```text
Fail
```

**Concept:** Validate before applying the inner classification rule.

---

## Interview Questions and Answers

### Interview Question 1 — When does a nested condition execute?

**Answer:** Only if control reaches its enclosing branch. A failed outer condition can skip the entire inner decision.

### Interview Question 2 — What causes a dangling-else misunderstanding?

**Answer:** Readers may associate an else with the wrong if. The language associates it with the nearest unmatched if; braces make intent visible.

### Interview Question 3 — When can nested if statements be flattened?

**Answer:** When the inner action requires all conditions and no distinct alternative handling is needed. Preserve evaluation order and side effects.

### Interview Question 4 — Why can deep nesting hurt readability?

**Answer:** It increases the number of conditions a reader must retain. Named conditions, guard clauses, and extracted methods can reduce that burden.

### Interview Question 5 — Does a non-empty outer array guarantee a valid first row?

**Answer:** No. Reference-array elements can be null and inner arrays can be empty, so each required structural condition needs validation.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Nested eligibility | An inner decision runs only when the outer path is taken. |
| Outer failure | Inner checks cannot override a failed outer condition. |
| Nested alternatives | The inner else belongs to the inner if. |
| Null then length | An outer guard can establish safety for inner operations. |
| Array then element | Nested guards can protect indexed access. |

[← 026. else-if](026_else-if.md) | [Course Index](../README.md) | [028. switch →](028_switch.md)
