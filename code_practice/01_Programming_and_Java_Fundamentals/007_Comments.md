[← 006. Java syntax](006_Java_syntax.md) | [Course Index](../README.md) | [008. Variables →](008_Variables.md)

# Java Comments Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/01_Programming_and_Java_Fundamentals/007_Comments.md)

These exercises follow [007. Comments](../../Road%20Map/01_Programming_and_Java_Fundamentals/007_Comments.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Line comment

**Task:** Annotate a variable with a line comment without changing its output.

```java
int age = 25; // Age in years
System.out.println(age);
```

**Expected result:**
```text
25
```

**Concept:** A line comment ends at the line terminator.

## Question 2 — Block comment

**Task:** Add a multiline explanation before calculating a total.

```java
/* Combine the two item counts.
   Values are small in this example. */
int total = 2 + 3;
System.out.println(total);
```

**Expected result:**
```text
5
```

**Concept:** Block comments span lines and end at the first closing delimiter.

## Question 3 — Javadoc

**Task:** Document a method with parameter and return descriptions.

```java
public class Main { /** Doubles a value. @param value input @return twice the input */ static int twice(int value) { return value * 2; } public static void main(String[] args) { System.out.println(twice(4)); } }
```

**Expected result:**
```text
8
```

**Concept:** Documentation comments can be processed by the Javadoc tool.

## Question 4 — Commented-out code

**Task:** Temporarily disable one print and keep the second.

```java
// System.out.println("Hidden");
System.out.println("Visible");
```

**Expected result:**
```text
Visible
```

**Concept:** Commented source is not executed.

## Question 5 — Comment markers in strings

**Task:** Print a URL containing two slash characters.

```java
System.out.println("https://example.com");
```

**Expected result:**
```text
https://example.com
```

**Concept:** Comment delimiters inside a string literal are ordinary string content.

## Question 6 — Explain intent

**Task:** Add a useful comment explaining why a bound is inclusive.

```java
// Include the final numbered day in this three-day report.
for (int day = 1; day <= 3; day++) { System.out.println(day); }
```

**Expected result:**
```text
1
2
3
```

**Concept:** Good comments explain a non-obvious decision rather than repeat the syntax.

## Question 7 — Repair stale documentation

**Task:** Update the comment to match an 18-year eligibility boundary.

```java
// Eligibility begins at age 18.
int age = 18;
System.out.println(age >= 18);
```

**Expected result:**
```text
true
```

**Concept:** Comments are not checked against the code by the compiler.

## Question 8 — Avoid nested block comments

**Task:** Replace a nested-comment attempt with separate line comments.

```java
// Outer explanation.
// Inner detail.
System.out.println("Safe comments");
```

**Expected result:**
```text
Safe comments
```

**Concept:** Java block comments do not nest.

## Question 9 — Generate documentation

**Task:** Generate API documentation for a public Main.java with a documented public class.

```shell
javadoc -d docs Main.java
```

**Expected result:**
```text
Documentation is generated in docs when source and tool requirements are satisfied; warnings depend on the file.
```

**Concept:** Javadoc processes documentation comments separately from program execution.

## Question 10 — Remove redundant noise

**Task:** Replace an obvious comment with a meaningful variable name and print the result.

```java
int remainingAttempts = 3;
System.out.println(remainingAttempts);
```

**Expected result:**
```text
3
```

**Concept:** Clear names often eliminate the need for explanatory comments.

---

## Interview Questions and Answers

### Interview Question 1 — What comment forms does Java support?

**Answer:** Line comments begin with //, block comments use /* ... */, and documentation comments use /** ... */.

### Interview Question 2 — Do block comments nest?

**Answer:** No. The first closing delimiter ends a block comment. Use separate line comments when disabling code containing block comments.

### Interview Question 3 — What is Javadoc for?

**Answer:** It generates API documentation from documentation comments and declarations, using tags such as @param, @return, and @throws.

### Interview Question 4 — When is a comment useful?

**Answer:** When it explains intent, a constraint, a non-obvious tradeoff, or an externally imposed rule that clear code alone cannot express.

### Interview Question 5 — Why can stale comments be dangerous?

**Answer:** Readers may trust an explanation that no longer matches behavior. Update or remove comments when changing the relevant code.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Line comment | A line comment ends at the line terminator. |
| Block comment | Block comments span lines and end at the first closing delimiter. |
| Javadoc | Documentation comments can be processed by the Javadoc tool. |
| Commented-out code | Commented source is not executed. |
| Comment markers in strings | Comment delimiters inside a string literal are ordinary string content. |

[← 006. Java syntax](006_Java_syntax.md) | [Course Index](../README.md) | [008. Variables →](008_Variables.md)
