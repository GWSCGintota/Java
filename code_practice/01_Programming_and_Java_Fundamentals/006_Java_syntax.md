[← 005. main() method](005_main_method.md) | [Course Index](../README.md) | [007. Comments →](007_Comments.md)

# Java Java syntax Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/01_Programming_and_Java_Fundamentals/006_Java_syntax.md)

These exercises follow [006. Java syntax](../../Road%20Map/01_Programming_and_Java_Fundamentals/006_Java_syntax.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Declarations

**Task:** Declare and print an integer.

```java
int count = 3;
System.out.println(count);
```

**Expected result:**
```text
3
```

**Concept:** A local declaration names a typed value.

## Question 2 — Blocks

**Task:** Use braces to group two statements under one condition.

```java
if (true) { System.out.println("First"); System.out.println("Second"); }
```

**Expected result:**
```text
First
Second
```

**Concept:** Braces define a block.

## Question 3 — Case sensitivity

**Task:** Declare age and Age as distinct variables and print both.

```java
int age = 18;
int Age = 21;
System.out.println(age + ":" + Age);
```

**Expected result:**
```text
18:21
```

**Concept:** Java identifiers are case-sensitive, though such similar names harm readability.

## Question 4 — Statement terminators

**Task:** Repair a missing statement terminator.

```java
int total = 2 + 3;
System.out.println(total);
```

**Expected result:**
```text
5
```

**Concept:** A declaration statement ends with a semicolon.

## Question 5 — String literals

**Task:** Print text containing quotation marks.

```java
System.out.println("Say \"Hello\"");
```

**Expected result:**
```text
Say "Hello"
```

**Concept:** Escape sequences represent special characters inside literals.

## Question 6 — Character literals

**Task:** Store a character and print its numeric code unit.

```java
char letter = 'A';
System.out.println((int) letter);
```

**Expected result:**
```text
65
```

**Concept:** Character literals use single quotes; char stores a UTF-16 code unit.

## Question 7 — Expression grouping

**Task:** Show how parentheses alter arithmetic precedence.

```java
System.out.println(2 + 3 * 4);
System.out.println((2 + 3) * 4);
```

**Expected result:**
```text
14
20
```

**Concept:** Parentheses override ordinary operator precedence.

## Question 8 — Legal identifiers

**Task:** Replace an illegal identifier starting with a digit.

```java
int item2 = 7;
System.out.println(item2);
```

**Expected result:**
```text
7
```

**Concept:** An identifier cannot begin with a digit or be a reserved keyword.

## Question 9 — Empty statement trap

**Task:** Write a loop that actually prints each index, without a stray semicolon.

```java
for (int i = 0; i < 3; i++) { System.out.println(i); }
```

**Expected result:**
```text
0
1
2
```

**Concept:** A stray semicolon can create an empty loop body.

## Question 10 — Method shape

**Task:** Write a complete class with a correctly formed method declaration and call.

```java
public class Main { static int sum(int a, int b) { return a + b; } public static void main(String[] args) { System.out.println(sum(2, 5)); } }
```

**Expected result:**
```text
7
```

**Concept:** Methods declare return types, names, parameters, and bodies.

---

## Interview Questions and Answers

### Interview Question 1 — Are whitespace and indentation part of Java block structure?

**Answer:** Braces and syntax determine blocks. Whitespace usually separates tokens and improves readability; it matters inside string and character literals.

### Interview Question 2 — What is an expression compared with a statement?

**Answer:** An expression computes a value or denotes a variable. A statement is an executable language construct, such as a declaration, conditional, or supported expression statement.

### Interview Question 3 — Why do class and variable naming conventions matter?

**Answer:** They help readers distinguish types, methods, fields, and constants. Conventions improve clarity even when the compiler accepts another legal spelling.

### Interview Question 4 — What does a semicolon after an if condition do?

**Answer:** It creates an empty controlled statement, so a following block runs independently. This often causes a logic bug despite compiling.

### Interview Question 5 — Are Java keywords legal identifiers?

**Answer:** Reserved keywords are not. Some contextual keywords have rules depending on their position and the Java release, so use clear conventional names.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Declarations | A local declaration names a typed value. |
| Blocks | Braces define a block. |
| Case sensitivity | Java identifiers are case-sensitive, though such similar names harm readability. |
| Statement terminators | A declaration statement ends with a semicolon. |
| String literals | Escape sequences represent special characters inside literals. |

[← 005. main() method](005_main_method.md) | [Course Index](../README.md) | [007. Comments →](007_Comments.md)
