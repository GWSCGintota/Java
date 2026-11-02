[← 003. Installing Java and an IDE](003_Installing_Java_and_an_IDE.md) | [Course Index](../README.md) | [005. main() method →](005_main_method.md)

# Java Creating your first Java program Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/01_Programming_and_Java_Fundamentals/004_Creating_your_first_Java_program.md)

These exercises follow [004. Creating your first Java program](../../Road%20Map/01_Programming_and_Java_Fundamentals/004_Creating_your_first_Java_program.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — First program

**Task:** Create a complete Main.java printing Hello, Java!.

```java
public class Main { public static void main(String[] args) { System.out.println("Hello, Java!"); } }
```

**Expected result:**
```text
Hello, Java!
```

**Concept:** main provides the entry point used by this example.

## Question 2 — Two lines

**Task:** Print Welcome and Ada on separate lines.

```java
System.out.println("Welcome");
System.out.println("Ada");
```

**Expected result:**
```text
Welcome
Ada
```

**Concept:** Each println terminates its line.

## Question 3 — Same line

**Task:** Print Hello and Java on one line separated by a space.

```java
System.out.print("Hello ");
System.out.println("Java");
```

**Expected result:**
```text
Hello Java
```

**Concept:** print does not append a line terminator.

## Question 4 — Store a greeting

**Task:** Store a greeting in a variable and print it.

```java
String greeting = "Good morning";
System.out.println(greeting);
```

**Expected result:**
```text
Good morning
```

**Concept:** A variable can hold a reusable value.

## Question 5 — Simple arithmetic

**Task:** Calculate and print the number of minutes in two hours.

```java
int hours = 2;
System.out.println(hours * 60);
```

**Expected result:**
```text
120
```

**Concept:** Numeric expressions compute values before output.

## Question 6 — Name argument

**Task:** Greet the first argument or Guest when none is provided.

```java
public class Main { public static void main(String[] args) { String name = args.length == 0 ? "Guest" : args[0]; System.out.println("Hello, " + name); } }
```

**Expected result:**
```text
Hello, Guest when launched without arguments.
```

**Concept:** Check the array length before accessing an argument.

## Question 7 — Compile and launch

**Task:** Compile the source from Question 1, then launch it.

```shell
javac Main.java
java Main
```

**Expected result:**
```text
Hello, Java!
```

**Concept:** Compilation and execution are separate steps.

## Question 8 — Output directory

**Task:** Compile Question 1 into out and run from that classpath.

```shell
javac -d out Main.java
java -cp out Main
```

**Expected result:**
```text
Hello, Java!
```

**Concept:** The classpath specifies where compiled classes can be found.

## Question 9 — Fix a syntax error

**Task:** Repair a print statement missing its semicolon.

```java
System.out.println("Correct");
```

**Expected result:**
```text
Correct
```

**Concept:** Most Java statements end with a semicolon.

## Question 10 — Extract a method

**Task:** Put greeting output in a helper and call it from main.

```java
public class Main { static void greet() { System.out.println("Hello"); } public static void main(String[] args) { greet(); } }
```

**Expected result:**
```text
Hello
```

**Concept:** Defining a helper does not execute it; calling it does.

---

## Interview Questions and Answers

### Interview Question 1 — What filename should contain public class Main?

**Answer:** Main.java in normal file-based compilation. Class names and filenames are case-sensitive in the language contract.

### Interview Question 2 — What does javac Main.java produce?

**Answer:** It produces class files containing compiled bytecode when compilation succeeds; additional classes can produce additional class files.

### Interview Question 3 — Why is the standard main method static?

**Answer:** The launcher can invoke it without creating an application instance. This course uses the conventional public static void main(String[] args) form.

### Interview Question 4 — What does System.out represent?

**Answer:** A standard output PrintStream exposed through the System class. A program can use it for console output.

### Interview Question 5 — What should you inspect when a first program fails?

**Answer:** Read the exact error, distinguish compiler errors from launch errors, and check syntax, filename, class name, classpath, and selected JDK.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| First program | main provides the entry point used by this example. |
| Two lines | Each println terminates its line. |
| Same line | print does not append a line terminator. |
| Store a greeting | A variable can hold a reusable value. |
| Simple arithmetic | Numeric expressions compute values before output. |

[← 003. Installing Java and an IDE](003_Installing_Java_and_an_IDE.md) | [Course Index](../README.md) | [005. main() method →](005_main_method.md)
