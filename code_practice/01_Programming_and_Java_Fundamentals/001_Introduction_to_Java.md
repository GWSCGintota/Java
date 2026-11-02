[Course Index](../README.md) | [002. JDK, JRE, JVM →](002_JDK_JRE_JVM.md)

# Java Introduction to Java Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/01_Programming_and_Java_Fundamentals/001_Introduction_to_Java.md)

These exercises follow [001. Introduction to Java](../../Road%20Map/01_Programming_and_Java_Fundamentals/001_Introduction_to_Java.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Hello Java

**Task:** Write a program printing a greeting.

```java
public class Main { public static void main(String[] args) { System.out.println("Hello, Java!"); } }
```

**Expected result:**
```text
Hello, Java!
```

**Concept:** A class contains the program entry point.

## Question 2 — Integer calculation

**Task:** Print the sum of 12 and 8.

```java
System.out.println(12 + 8);
```

**Expected result:**
```text
20
```

**Concept:** Java evaluates a numeric expression before printing its result.

## Question 3 — Named data

**Task:** Store a learner name and print a greeting.

```java
String name = "Ada";
System.out.println("Hello, " + name);
```

**Expected result:**
```text
Hello, Ada
```

**Concept:** Variables give data reusable names.

## Question 4 — Decisions

**Task:** Print Adult when age is 20 and Minor otherwise.

```java
int age = 20;
System.out.println(age >= 18 ? "Adult" : "Minor");
```

**Expected result:**
```text
Adult
```

**Concept:** A boolean condition selects between results.

## Question 5 — Repetition

**Task:** Print the integers from 1 through 3.

```java
for (int i = 1; i <= 3; i++) { System.out.println(i); }
```

**Expected result:**
```text
1
2
3
```

**Concept:** Loops repeat an operation under a condition.

## Question 6 — Objects

**Task:** Create a StringBuilder and append two words.

```java
StringBuilder text = new StringBuilder("Hello");
text.append(" Java");
System.out.println(text);
```

**Expected result:**
```text
Hello Java
```

**Concept:** Objects expose operations through methods.

## Question 7 — Reusable method

**Task:** Define and call a method that doubles an integer.

```java
public class Main { static int twice(int n) { return n * 2; } public static void main(String[] args) { System.out.println(twice(6)); } }
```

**Expected result:**
```text
12
```

**Concept:** Methods encapsulate reusable behavior.

## Question 8 — Library use

**Task:** Sort three integers with the standard library.

```java
int[] values = {3, 1, 2};
Arrays.sort(values);
System.out.println(Arrays.toString(values));
```

**Expected result:**
```text
[1, 2, 3]
```

**Concept:** The Java standard library provides reusable data operations.

## Question 9 — Handle failure

**Task:** Parse invalid integer text and print a deliberate fallback message.

```java
try { Integer.parseInt("oops"); } catch (NumberFormatException e) { System.out.println("Invalid number"); }
```

**Expected result:**
```text
Invalid number
```

**Concept:** Exceptions report failures that callers can handle.

## Question 10 — Portable entry point

**Task:** Print the first command-line argument without hard-coding a platform path.

```java
public class Main { public static void main(String[] args) { System.out.println(args.length == 0 ? "Guest" : args[0]); } }
```

**Expected result:**
```text
Guest
```

**Concept:** With no arguments, the program follows its explicit default branch.

---

## Interview Questions and Answers

### Interview Question 1 — Why is Java called platform independent?

**Answer:** Compilation produces JVM bytecode. A compatible JVM executes that bytecode on its operating system; native libraries and external dependencies can still constrain portability.

### Interview Question 2 — What does the compiler do?

**Answer:** javac checks source types and syntax and produces class files containing bytecode and metadata.

### Interview Question 3 — Is Java purely object-oriented?

**Answer:** Java supports object-oriented programming but also has primitive values and static members, so it is not purely object-oriented in that sense.

### Interview Question 4 — Does garbage collection close files?

**Answer:** No. Garbage collection manages reclaimable memory. Close owned resources explicitly, usually with try-with-resources.

### Interview Question 5 — What should a first Java example demonstrate?

**Answer:** A correctly named source file, a class, a supported main entry point, compilation, execution, and observable output.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Hello Java | A class contains the program entry point. |
| Integer calculation | Java evaluates a numeric expression before printing its result. |
| Named data | Variables give data reusable names. |
| Decisions | A boolean condition selects between results. |
| Repetition | Loops repeat an operation under a condition. |

[Course Index](../README.md) | [002. JDK, JRE, JVM →](002_JDK_JRE_JVM.md)
