[← 027. Nested if](027_Nested_if.md) | [Course Index](../README.md) | [029. Modern switch expressions →](029_Modern_switch_expressions.md)

# Java switch Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/02_Control_Flow/028_switch.md)

These exercises follow [028. switch](../../Road%20Map/02_Control_Flow/028_switch.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Integer dispatch

**Task:** Use a classic switch to name day 1 as Monday.

```java
int day = 1;
switch (day) { case 1: System.out.println("Monday"); break; default: System.out.println("Other"); }
```

**Expected result:**
```text
Monday
```

**Concept:** A matching case selects where execution begins.

## Question 2 — Default

**Task:** Handle an unknown integer selector.

```java
int day = 9;
switch (day) { case 1: System.out.println("Monday"); break; default: System.out.println("Unknown"); }
```

**Expected result:**
```text
Unknown
```

**Concept:** default handles selectors without a matching case.

## Question 3 — Prevent fallthrough

**Task:** Print only B for selector 2.

```java
switch (2) { case 1: System.out.println("A"); break; case 2: System.out.println("B"); break; default: System.out.println("C"); }
```

**Expected result:**
```text
B
```

**Concept:** break exits a classic switch.

## Question 4 — Intentional fallthrough

**Task:** Demonstrate two consecutive outputs when a break is omitted.

```java
switch (1) { case 1: System.out.println("A"); case 2: System.out.println("B"); break; default: System.out.println("C"); }
```

**Expected result:**
```text
A
B
```

**Concept:** Classic colon cases can fall through into later statements.

## Question 5 — Grouped cases

**Task:** Classify day 6 or 7 as Weekend.

```java
int day = 7;
switch (day) { case 6: case 7: System.out.println("Weekend"); break; default: System.out.println("Weekday"); }
```

**Expected result:**
```text
Weekend
```

**Concept:** Multiple labels can share one body.

## Question 6 — String selector

**Task:** Dispatch a stop command.

```java
String command = "stop";
switch (command) { case "start": System.out.println("Starting"); break; case "stop": System.out.println("Stopping"); break; default: System.out.println("Unknown"); }
```

**Expected result:**
```text
Stopping
```

**Concept:** String switch matches string content and is case-sensitive.

## Question 7 — Character selector

**Task:** Recognize an uppercase vowel.

```java
char letter = 'A';
switch (letter) { case 'A': case 'E': case 'I': case 'O': case 'U': System.out.println("Vowel"); break; default: System.out.println("Other"); }
```

**Expected result:**
```text
Vowel
```

**Concept:** Compatible discrete primitive selectors can use constant labels.

## Question 8 — Assign a result

**Task:** Assign a label in every branch before printing.

```java
int status = 200; String label;
switch (status) { case 200: label = "OK"; break; case 404: label = "Not found"; break; default: label = "Other"; }
System.out.println(label);
```

**Expected result:**
```text
OK
```

**Concept:** Assignment must cover every path reaching the later read.

## Question 9 — Null guard

**Task:** Guard a nullable string before a classic switch.

```java
String command = null;
if (command == null) { System.out.println("Missing"); } else { switch (command) { case "start": System.out.println("Start"); break; default: System.out.println("Other"); } }
```

**Expected result:**
```text
Missing
```

**Concept:** This Java 8 classic switch does not accept a null selector.

## Question 10 — Switch inside a loop

**Task:** Show that break exits the switch rather than its surrounding loop.

```java
for (int i = 1; i <= 2; i++) { switch (i) { case 1: System.out.println("One"); break; default: System.out.println("Other"); } System.out.println("Loop continues"); }
```

**Expected result:**
```text
One
Loop continues
Other
Loop continues
```

**Concept:** Unlabeled break targets the innermost switch or loop.

---

## Interview Questions and Answers

### Interview Question 1 — What is fallthrough in a classic switch?

**Answer:** After entering a colon-labeled case, execution continues into later statements unless control exits with break, return, throw, or another transfer.

### Interview Question 2 — What does break exit?

**Answer:** The innermost enclosing switch or loop, unless a valid label specifies a different enclosing statement.

### Interview Question 3 — Does a classic Java 8 String switch accept null?

**Answer:** No. Evaluate an explicit null guard before switching on a nullable string.

### Interview Question 4 — Can a switch case label be an arbitrary variable?

**Answer:** Classic constant case labels must meet compile-time constant rules for the selector type. Modern pattern switches have additional rules beyond this lesson's Java 8 examples.

### Interview Question 5 — When is default useful?

**Answer:** It states an explicit fallback for unknown selector values and can ensure a result is assigned on every relevant path.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Integer dispatch | A matching case selects where execution begins. |
| Default | default handles selectors without a matching case. |
| Prevent fallthrough | break exits a classic switch. |
| Intentional fallthrough | Classic colon cases can fall through into later statements. |
| Grouped cases | Multiple labels can share one body. |

[← 027. Nested if](027_Nested_if.md) | [Course Index](../README.md) | [029. Modern switch expressions →](029_Modern_switch_expressions.md)
