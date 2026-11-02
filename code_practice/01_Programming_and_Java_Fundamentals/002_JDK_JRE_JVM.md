[← 001. Introduction to Java](001_Introduction_to_Java.md) | [Course Index](../README.md) | [003. Installing Java and an IDE →](003_Installing_Java_and_an_IDE.md)

# Java JDK, JRE, JVM Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/01_Programming_and_Java_Fundamentals/002_JDK_JRE_JVM.md)

These exercises follow [002. JDK, JRE, JVM](../../Road%20Map/01_Programming_and_Java_Fundamentals/002_JDK_JRE_JVM.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Questions use an installed JDK and a terminal. Commands run in a disposable example directory; create Main.java where requested. CLI banners and failures are environment-dependent. Question 9 requires JDK 9+; other examples use Java 8-compatible source.

## Question 1 — Runtime version

**Task:** Inspect the Java runtime selected by your shell.

```shell
java -version
```

**Expected result:**
```text
A version banner is written; the exact version and vendor depend on the installed runtime.
```

**Concept:** java launches the runtime rather than compiling source.

## Question 2 — Compiler version

**Task:** Inspect the selected compiler.

```shell
javac -version
```

**Expected result:**
```text
A javac version banner, or a command-not-found error if no compiler is on PATH.
```

**Concept:** A JDK provides development tools such as javac.

## Question 3 — Compile source

**Task:** Save a public Main class that prints Ready and compile it.

```java
// Main.java
public class Main { public static void main(String[] args) { System.out.println("Ready"); } }
```

**Expected result:**
```text
javac Main.java succeeds and creates Main.class when a compatible JDK is installed.
```

**Concept:** Source compilation produces class-file bytecode.

## Question 4 — Launch bytecode

**Task:** Run the Main.class produced in Question 3 from its directory.

```shell
java -cp . Main
```

**Expected result:**
```text
Ready
```

**Concept:** The launcher takes the class name without the .class extension.

## Question 5 — Inspect bytecode

**Task:** Inspect the instructions for the Main class from Question 3.

```shell
javap -c Main
```

**Expected result:**
```text
Disassembly includes main and an invocation of PrintStream.println; offsets can vary.
```

**Concept:** javap is a development inspection tool, not the application runtime.

## Question 6 — Separate output

**Task:** Compile Main.java into an output directory.

```shell
javac -d out Main.java
java -cp out Main
```

**Expected result:**
```text
Ready
```

**Concept:** Compiler output and runtime classpath must agree.

## Question 7 — Package lookup

**Task:** Create a packaged class and document its launch command.

```java
package demo;
public class Main { public static void main(String[] args) { System.out.println("Packaged"); } }
```

**Expected result:**
```text
javac -d out Main.java, then java -cp out demo.Main prints Packaged.
```

**Concept:** Qualified class names reflect their packages.

## Question 8 — Inspect properties

**Task:** Print the vendor and runtime version using standard properties.

```java
System.out.println(System.getProperty("java.vendor"));
System.out.println(System.getProperty("java.version"));
```

**Expected result:**
```text
Two environment-dependent values: the runtime vendor and Java version.
```

**Concept:** Runtime properties describe the executing JVM, not necessarily the compiler used earlier.

## Question 9 — Target an older release

**Task:** On JDK 9+, compile Main.java for Java 8 using the supported release option.

```shell
javac --release 8 -d out Main.java
```

**Expected result:**
```text
Compilation succeeds for Java 8-compatible source and APIs.
```

**Concept:** --release checks the selected language level and supported platform API surface.

## Question 10 — Class file location

**Task:** Ask the launcher to find Main in an empty directory, then correct the classpath.

```shell
java -cp empty Main
java -cp out Main
```

**Expected result:**
```text
The first launch fails to locate Main if empty contains no class; the second prints Ready after Question 6.
```

**Concept:** Class loading searches the configured classpath.

---

## Interview Questions and Answers

### Interview Question 1 — How do JDK, JRE, and JVM differ?

**Answer:** The JVM executes bytecode. A runtime environment includes the JVM and supporting libraries. A JDK adds development tools such as javac; modern distribution packaging varies.

### Interview Question 2 — Does every machine running an application need javac?

**Answer:** No. A compatible runtime is enough for already compiled code, provided the application does not itself require compiler tools.

### Interview Question 3 — What causes an unsupported class version error?

**Answer:** The runtime is too old to load a class-file version produced for a newer Java release. Use a compatible runtime or rebuild for a supported target.

### Interview Question 4 — What is a classpath?

**Answer:** A set of roots or archives used to locate classes and resources in classpath-based applications. The Java module path is a separate mechanism.

### Interview Question 5 — Why can java and javac report different versions?

**Answer:** PATH or explicit executable paths can select tools from different installations. Inspect both commands and configure the intended JDK consistently.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Runtime version | java launches the runtime rather than compiling source. |
| Compiler version | A JDK provides development tools such as javac. |
| Compile source | Source compilation produces class-file bytecode. |
| Launch bytecode | The launcher takes the class name without the .class extension. |
| Inspect bytecode | javap is a development inspection tool, not the application runtime. |

[← 001. Introduction to Java](001_Introduction_to_Java.md) | [Course Index](../README.md) | [003. Installing Java and an IDE →](003_Installing_Java_and_an_IDE.md)
