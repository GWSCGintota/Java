[← 028. switch](028_switch.md) | [Course Index](../README.md) | [030. for loops →](030_for_loops.md)

# Java Modern switch expressions Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/02_Control_Flow/029_Modern_switch_expressions.md)

These exercises follow [029. Modern switch expressions](../../Road%20Map/02_Control_Flow/029_Modern_switch_expressions.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 17, no preview features. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Arrow expression

**Task:** Map day 1 to Monday with a switch expression.

```java
int day = 1;
String label = switch (day) { case 1 -> "Monday"; default -> "Other"; };
System.out.println(label);
```

**Expected result:**
```text
Monday
```

**Concept:** A switch expression produces a value.

## Question 2 — Multiple labels

**Task:** Map day 7 to Weekend with a comma-separated case label.

```java
int day = 7;
String label = switch (day) { case 6, 7 -> "Weekend"; default -> "Weekday"; };
System.out.println(label);
```

**Expected result:**
```text
Weekend
```

**Concept:** Arrow rules can share a result across several constants.

## Question 3 — Block and yield

**Task:** Compute a value in a case block and yield it.

```java
int value = 3;
int result = switch (value) { case 3 -> { int square = value * value; yield square; } default -> 0; };
System.out.println(result);
```

**Expected result:**
```text
9
```

**Concept:** yield supplies the value of a block in a switch expression.

## Question 4 — String selector

**Task:** Translate stop to Stopping with an expression.

```java
String command = "stop";
System.out.println(switch (command) { case "start" -> "Starting"; case "stop" -> "Stopping"; default -> "Unknown"; });
```

**Expected result:**
```text
Stopping
```

**Concept:** String matching remains case-sensitive.

## Question 5 — No fallthrough

**Task:** Use arrow rules to print only A for selector 1.

```java
switch (1) { case 1 -> System.out.println("A"); case 2 -> System.out.println("B"); default -> System.out.println("Other"); }
```

**Expected result:**
```text
A
```

**Concept:** Arrow rules do not fall through into the next rule.

## Question 6 — Reject an invalid selector

**Task:** Throw for an unsupported operation.

```java
String operation = "bad";
try { int result = switch (operation) { case "add" -> 2 + 3; default -> throw new IllegalArgumentException("Unknown operation"); }; System.out.println(result); } catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
```

**Expected result:**
```text
Unknown operation
```

**Concept:** A rule can complete abruptly by throwing instead of producing a value.

## Question 7 — Expression in calculation

**Task:** Use a switch-produced fee in a total.

```java
int shippingType = 2;
int total = 100 + switch (shippingType) { case 1 -> 5; case 2 -> 10; default -> 0; };
System.out.println(total);
```

**Expected result:**
```text
110
```

**Concept:** Switch expressions can appear where their result type is expected.

## Question 8 — Default boundary

**Task:** Handle an unmatched number with an explicit default.

```java
int month = 13;
String result = switch (month) { case 1 -> "January"; default -> "Unsupported month"; };
System.out.println(result);
```

**Expected result:**
```text
Unsupported month
```

**Concept:** A switch expression must cover all possible selector values under its rules.

## Question 9 — Null policy

**Task:** Guard a nullable selector before a Java 17 string switch expression.

```java
String command = null;
String result = command == null ? "Missing" : switch (command) { case "start" -> "Start"; default -> "Other"; };
System.out.println(result);
```

**Expected result:**
```text
Missing
```

**Concept:** These Java 17 non-preview examples use an explicit null guard.

## Question 10 — Yield is not method return

**Task:** Compute a switch value and continue afterward.

```java
int result = switch (1) { case 1 -> { yield 7; } default -> 0; };
System.out.println(result);
System.out.println("Continued");
```

**Expected result:**
```text
7
Continued
```

**Concept:** yield exits the switch expression's value-producing block, not the enclosing method.

---

## Interview Questions and Answers

### Interview Question 1 — How does a switch expression differ from a switch statement?

**Answer:** An expression produces a value and must satisfy exhaustiveness rules. A statement controls actions without itself producing a value.

### Interview Question 2 — Do arrow rules fall through?

**Answer:** No. An arrow rule executes its selected expression, block, or throw action without falling into the following rule.

### Interview Question 3 — When is yield needed?

**Answer:** A block used by a switch expression needs yield to supply a value on a normal result-producing path.

### Interview Question 4 — Is yield the same as return?

**Answer:** No. yield supplies a switch-expression value, while return exits the enclosing method or lambda body.

### Interview Question 5 — What Java release do these examples require?

**Answer:** Switch expressions became a permanent language feature in Java 14. These exercises target Java 17 without preview features; pattern matching for switch is a separate feature with different release requirements.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Arrow expression | A switch expression produces a value. |
| Multiple labels | Arrow rules can share a result across several constants. |
| Block and yield | yield supplies the value of a block in a switch expression. |
| String selector | String matching remains case-sensitive. |
| No fallthrough | Arrow rules do not fall through into the next rule. |

[← 028. switch](028_switch.md) | [Course Index](../README.md) | [030. for loops →](030_for_loops.md)
