[← 002. JDK, JRE, JVM](002_JDK_JRE_JVM.md) | [Course Index](../README.md) | [004. Creating your first Java program →](004_Creating_your_first_Java_program.md)

# Java Installing Java and an IDE Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/01_Programming_and_Java_Fundamentals/003_Installing_Java_and_an_IDE.md)

These exercises follow [003. Installing Java and an IDE](../../Road%20Map/01_Programming_and_Java_Fundamentals/003_Installing_Java_and_an_IDE.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Use an installed JDK, a plain Java IDE project, and a disposable example directory. Windows and POSIX command examples are labeled separately. Exact installation paths and version banners vary.

## Question 1 — Check compiler

**Task:** Verify that a compiler is available before configuring an IDE.

```shell
javac -version
```

**Expected result:**
```text
A compiler version is shown when the JDK is correctly installed and reachable.
```

**Concept:** Install and select a JDK rather than assuming the runtime includes development tools.

## Question 2 — Check runtime

**Task:** Verify the launcher independently.

```shell
java -version
```

**Expected result:**
```text
A runtime version banner is shown.
```

**Concept:** Compare the selected runtime and compiler versions.

## Question 3 — Locate commands on Windows

**Task:** Find which Java commands Windows resolves.

```shell
where.exe java
where.exe javac
```

**Expected result:**
```text
Paths to matching executables on PATH, or a not-found diagnostic.
```

**Concept:** Multiple installations can compete on PATH.

## Question 4 — Locate commands on Unix

**Task:** Find resolved commands in a POSIX shell.

```shell
command -v java
command -v javac
```

**Expected result:**
```text
Paths or shell resolution information for each installed command.
```

**Concept:** Shell lookup determines which tools run.

## Question 5 — Create an IDE project

**Task:** Create a plain Java project using the installed JDK and run this class.

```java
public class Main { public static void main(String[] args) { System.out.println("IDE ready"); } }
```

**Expected result:**
```text
IDE ready
```

**Concept:** Select a project SDK that matches the code requirements.

## Question 6 — Compile outside the IDE

**Task:** Compile and run the class from Question 5 in a terminal.

```shell
javac Main.java
java Main
```

**Expected result:**
```text
IDE ready
```

**Concept:** A terminal check separates installation problems from IDE configuration.

## Question 7 — Set a breakpoint

**Task:** Run this example in the debugger and stop before printing result.

```java
int input = 6;
int result = input * 2;
System.out.println(result);
```

**Expected result:**
```text
12; the debugger shows input=6 and result=12 after the assignment.
```

**Concept:** Breakpoints inspect execution without changing application behavior.

## Question 8 — Inspect arguments

**Task:** Configure the IDE run arguments as Ada and Java.

```java
public class Main { public static void main(String[] args) { System.out.println(java.util.Arrays.toString(args)); } }
```

**Expected result:**
```text
[Ada, Java]
```

**Concept:** Run configurations pass application arguments separately from JVM options.

## Question 9 — Check working directory

**Task:** Print the application's working directory.

```java
System.out.println(System.getProperty("user.dir"));
```

**Expected result:**
```text
The absolute working directory selected by the IDE or terminal.
```

**Concept:** Relative file paths resolve from the working directory rather than the source file location.

## Question 10 — Repair a filename mismatch

**Task:** A file named Demo.java declares public class Main. Provide the correction.

```java
// Save as Main.java
public class Main { public static void main(String[] args) { System.out.println("Fixed"); } }
```

**Expected result:**
```text
Fixed after compiling Main.java and running Main.
```

**Concept:** A public top-level class must match its source filename in ordinary file-based compilation.

---

## Interview Questions and Answers

### Interview Question 1 — Why choose an SDK in the IDE?

**Answer:** The project SDK controls compilation tools and platform APIs. An IDE's own runtime and the project runtime can be different installations.

### Interview Question 2 — What is the difference between PATH and JAVA_HOME?

**Answer:** PATH controls command lookup. JAVA_HOME is a convention used by many tools to locate a Java installation; setting it alone does not change command resolution.

### Interview Question 3 — Why does code run in an IDE but fail in a terminal?

**Answer:** Common causes include different JDKs, classpaths, working directories, environment variables, or application arguments.

### Interview Question 4 — What should you check before reinstalling Java?

**Answer:** Inspect java and javac versions, resolved executable paths, the project SDK, and the exact compiler or launcher error.

### Interview Question 5 — What does debugging add beyond printing?

**Answer:** A debugger can pause at breakpoints, inspect values and stack frames, and step through control flow without adding permanent diagnostic output.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Check compiler | Install and select a JDK rather than assuming the runtime includes development tools. |
| Check runtime | Compare the selected runtime and compiler versions. |
| Locate commands on Windows | Multiple installations can compete on PATH. |
| Locate commands on Unix | Shell lookup determines which tools run. |
| Create an IDE project | Select a project SDK that matches the code requirements. |

[← 002. JDK, JRE, JVM](002_JDK_JRE_JVM.md) | [Course Index](../README.md) | [004. Creating your first Java program →](004_Creating_your_first_Java_program.md)
