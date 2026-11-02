[← 020. Random numbers](../01_Programming_and_Java_Fundamentals/020_Random_numbers.md) | [Course Index](../README.md) | [022. Comparison operators →](022_Comparison_operators.md)

# Java Boolean expressions Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/02_Control_Flow/021_Boolean_expressions.md)

These exercises follow [021. Boolean expressions](../../Road%20Map/02_Control_Flow/021_Boolean_expressions.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Comparison result

**Task:** Assign the result of an adult-age comparison to a boolean.

```java
int age = 20; boolean adult = age >= 18;
System.out.println(adult);
```

**Expected result:**
```text
true
```

**Concept:** A comparison evaluates to true or false.

## Question 2 — Equality

**Task:** Check whether a quantity equals its target.

```java
int count = 3;
System.out.println(count == 3);
```

**Expected result:**
```text
true
```

**Concept:** Equality and assignment use different operators.

## Question 3 — Inequality

**Task:** Check whether a count is nonzero.

```java
int count = 0;
System.out.println(count != 0);
```

**Expected result:**
```text
false
```

**Concept:** != is the negation of primitive equality.

## Question 4 — Inclusive range

**Task:** Check whether 100 lies in the inclusive range 0–100.

```java
int score = 100;
System.out.println(score >= 0 && score <= 100);
```

**Expected result:**
```text
true
```

**Concept:** Both range constraints must hold.

## Question 5 — Outside range

**Task:** Check whether -1 lies outside 0–100.

```java
int score = -1;
System.out.println(score < 0||score > 100);
```

**Expected result:**
```text
true
```

**Concept:** Either invalid boundary makes the value outside the range.

## Question 6 — Negation

**Task:** Negate an enabled flag.

```java
boolean enabled = false;
System.out.println(!enabled);
```

**Expected result:**
```text
true
```

**Concept:** ! reverses a boolean value.

## Question 7 — Divisibility

**Task:** Check whether 12 is divisible by both 2 and 3.

```java
int number = 12;
System.out.println(number % 2 == 0 && number % 3 == 0);
```

**Expected result:**
```text
true
```

**Concept:** Remainder comparisons can form boolean conditions.

## Question 8 — Text presence

**Task:** Check nullable text before reading its length.

```java
String text = null;
System.out.println(text != null && text.length() > 0);
```

**Expected result:**
```text
false
```

**Concept:** Short-circuit evaluation protects dereferences.

## Question 9 — Precedence

**Task:** Group an OR alternative before applying an AND requirement.

```java
boolean member = false, guest = true, open = false;
System.out.println((member||guest) && open);
```

**Expected result:**
```text
false
```

**Concept:** Parentheses make a composite condition's intent explicit.

## Question 10 — Boolean method result

**Task:** Store the result of a text-prefix check.

```java
boolean javaFile = "Main.java".endsWith(".java");
System.out.println(javaFile);
```

**Expected result:**
```text
true
```

**Concept:** Methods can produce boolean expressions directly.

---

## Interview Questions and Answers

### Interview Question 1 — What is a boolean expression?

**Answer:** An expression whose value is true or false, often built from comparisons, boolean variables, or predicate methods.

### Interview Question 2 — Can an integer be used directly as an if condition?

**Answer:** No. Java conditions require boolean or an unboxable Boolean, unlike languages that treat nonzero integers as true.

### Interview Question 3 — How should an inclusive range check be written?

**Answer:** Combine lower and upper comparisons with &&, such as value >= low && value <= high. Chained mathematical comparisons are not Java syntax.

### Interview Question 4 — Why avoid enabled == true?

**Answer:** The boolean itself already expresses the condition. Use enabled or !enabled unless an explicit comparison improves a special case.

### Interview Question 5 — Can a Boolean condition throw?

**Answer:** Yes. Unboxing a null Boolean throws NullPointerException, so nullable wrapper inputs need an explicit policy.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Comparison result | A comparison evaluates to true or false. |
| Equality | Equality and assignment use different operators. |
| Inequality | != is the negation of primitive equality. |
| Inclusive range | Both range constraints must hold. |
| Outside range | Either invalid boundary makes the value outside the range. |

[← 020. Random numbers](../01_Programming_and_Java_Fundamentals/020_Random_numbers.md) | [Course Index](../README.md) | [022. Comparison operators →](022_Comparison_operators.md)
