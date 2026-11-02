[← 004. Creating your first Java program](004_Creating_your_first_Java_program.md) | [Course Index](../README.md) | [006. Java syntax →](006_Java_syntax.md)

# Java main() method Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/01_Programming_and_Java_Fundamentals/005_main_method.md)

These exercises follow [005. main() method](../../Road%20Map/01_Programming_and_Java_Fundamentals/005_main_method.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Empty argument list

**Task:** Print how many application arguments main receives when none are supplied.

```java
public class Main { public static void main(String[] args) { System.out.println(args.length); } }
```

**Expected result:**
```text
0
```

**Concept:** The launcher supplies an array, which can be empty.

## Question 2 — Print all arguments

**Task:** Write an entry point that prints each argument; run with Ada Java.

```java
public class Main { public static void main(String[] args) { for (String arg : args) { System.out.println(arg); } } }
```

**Expected result:**
```text
Ada
Java
```

**Concept:** Application arguments are strings supplied after the class name.

## Question 3 — Safe first argument

**Task:** Print Guest if the argument array is empty.

```java
public class Main { public static void main(String[] args) { System.out.println(args.length == 0 ? "Guest" : args[0]); } }
```

**Expected result:**
```text
Guest when launched without arguments.
```

**Concept:** Array access requires an explicit length check.

## Question 4 — Parse an argument

**Task:** Parse one integer argument, reporting bad or missing input.

```java
public class Main { public static void main(String[] args) { if (args.length != 1) { System.out.println("Provide one integer"); return; } try { System.out.println(Integer.parseInt(args[0]) * 2); } catch (NumberFormatException e) { System.out.println("Invalid integer"); } } }
```

**Expected result:**
```text
With argument 6: 12. With no arguments: Provide one integer. With x: Invalid integer.
```

**Concept:** Input validation belongs at the application boundary.

## Question 5 — Return from main

**Task:** Stop early when no arguments are present.

```java
public class Main { public static void main(String[] args) { if (args.length == 0) { System.out.println("Nothing to do"); return; } System.out.println("Processing"); } }
```

**Expected result:**
```text
Nothing to do when launched without arguments.
```

**Concept:** A bare return ends this invocation of a void method.

## Question 6 — Varargs entry point

**Task:** Use varargs syntax for the standard argument array.

```java
public class Main { public static void main(String... args) { System.out.println(args.length); } }
```

**Expected result:**
```text
0 when launched without arguments.
```

**Concept:** String... has the array parameter shape required by this conventional entry point.

## Question 7 — Call a static helper

**Task:** Invoke a static method from main.

```java
public class Main { static String greeting() { return "Ready"; } public static void main(String[] args) { System.out.println(greeting()); } }
```

**Expected result:**
```text
Ready
```

**Concept:** Static code can directly call another accessible static method.

## Question 8 — Call an instance helper

**Task:** Create an instance before calling an instance method.

```java
public class Main { String greeting() { return "Instance ready"; } public static void main(String[] args) { System.out.println(new Main().greeting()); } }
```

**Expected result:**
```text
Instance ready
```

**Concept:** An instance method needs a receiver object.

## Question 9 — Overload main

**Task:** Define main(int) and invoke it explicitly from the entry point.

```java
public class Main { static void main(int value) { System.out.println(value); } public static void main(String[] args) { main(7); } }
```

**Expected result:**
```text
7
```

**Concept:** Overloading does not make every overload a launcher entry point.

## Question 10 — Separate JVM and program arguments

**Task:** Enable assertions and pass Ada to the program from Question 2.

```shell
java -ea Main Ada
```

**Expected result:**
```text
Ada
```

**Concept:** JVM options precede the main class name; application arguments follow it.

---

## Interview Questions and Answers

### Interview Question 1 — What is the conventional Java entry-point signature?

**Answer:** public static void main(String[] args), also expressible with String... args. This course uses that portable conventional form.

### Interview Question 2 — Can main be overloaded?

**Answer:** Yes, ordinary overload resolution applies to explicit calls. The launcher still needs an entry point supported by the selected Java release.

### Interview Question 3 — Why can args[0] fail?

**Answer:** The array can be empty. Access without checking its length throws ArrayIndexOutOfBoundsException.

### Interview Question 4 — Does return from main necessarily terminate all threads?

**Answer:** No. Returning ends the main thread's entry invocation, but other non-daemon threads can keep the JVM running.

### Interview Question 5 — Can main throw an exception?

**Answer:** Yes. Uncaught failures are reported by the thread's uncaught-exception mechanism. Validate expected user-input errors and manage resources deliberately.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Empty argument list | The launcher supplies an array, which can be empty. |
| Print all arguments | Application arguments are strings supplied after the class name. |
| Safe first argument | Array access requires an explicit length check. |
| Parse an argument | Input validation belongs at the application boundary. |
| Return from main | A bare return ends this invocation of a void method. |

[← 004. Creating your first Java program](004_Creating_your_first_Java_program.md) | [Course Index](../README.md) | [006. Java syntax →](006_Java_syntax.md)
