[← 070. Package-private access](070_Package-private_access.md) | [Course Index](../README.md) | [072. Inheritance →](072_Inheritance.md)

# Java Packages Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/071_Packages.md)

These exercises follow [071. Packages](../../Road%20Map/05_Object_Oriented_Programming/071_Packages.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each Java example independently. Packaged examples show explicit compile and launch commands in the expected result. CLI exercises use a disposable directory and build on Question 1 unless otherwise stated; they do not alter the repository or publish an archive.

## Question 1 — Declare a package

**Task:** Create a packaged program and document its launch command.

```java
package demo;
public class Main { public static void main(String[] args) { System.out.println("Packaged"); } }
```

**Expected result:**
```text
Compile with javac -d out Main.java; java -cp out demo.Main prints Packaged.
```

**Concept:** The package declaration establishes the qualified class name.

## Question 2 — Compile to package directories

**Task:** Compile the source from Question 1 into out.

```shell
javac -d out Main.java
```

**Expected result:**
```text
The compiler writes out/demo/Main.class when compilation succeeds.
```

**Concept:** -d builds the package directory structure under the chosen output root.

## Question 3 — Launch by qualified name

**Task:** Launch the class produced in Question 2.

```shell
java -cp out demo.Main
```

**Expected result:**
```text
Packaged
```

**Concept:** The launcher uses a qualified class name, not a source path.

## Question 4 — Import a standard-library class

**Task:** Import ArrayList explicitly in a packaged class.

```java
package demo;
import java.util.ArrayList;
public class Main { public static void main(String[] args) { System.out.println(new ArrayList<String>().size()); } }
```

**Expected result:**
```text
Compile with javac -d out Main.java; java -cp out demo.Main prints 0.
```

**Concept:** Imports allow simple names without changing the underlying package.

## Question 5 — Use a fully qualified type

**Task:** Use java.util.ArrayList without an import.

```java
public class Main { public static void main(String[] args) { System.out.println(new java.util.ArrayList<String>().size()); } }
```

**Expected result:**
```text
0
```

**Concept:** A qualified type name can avoid an import.

## Question 6 — Resolve two types with the same simple name

**Task:** Print the names of both Date classes using qualification.

```java
System.out.println(java.util.Date.class.getName());
System.out.println(java.sql.Date.class.getName());
```

**Expected result:**
```text
java.util.Date
java.sql.Date
```

**Concept:** Qualification resolves simple-name conflicts.

## Question 7 — Inspect a package name

**Task:** Print the package name of a standard class.

```java
System.out.println(String.class.getPackage().getName());
```

**Expected result:**
```text
java.lang
```

**Concept:** Runtime type metadata includes its package identity.

## Question 8 — Use a static import

**Task:** Import Math.max statically in a complete program.

```java
import static java.lang.Math.max;
public class Main { public static void main(String[] args) { System.out.println(max(3, 7)); } }
```

**Expected result:**
```text
7
```

**Concept:** A static import makes selected static members available by simple name.

## Question 9 — Show that imports do not load subpackages

**Task:** Use a concurrent type by its qualified name rather than assuming java.util.* imports it.

```java
java.util.concurrent.atomic.AtomicInteger count = new java.util.concurrent.atomic.AtomicInteger(7);
System.out.println(count.get());
```

**Expected result:**
```text
7
```

**Concept:** Wildcard imports do not recursively import subpackages.

## Question 10 — Build a packaged application archive

**Task:** Package and run the compiled class from Question 2 using a JDK.

```shell
jar cfe demo.jar demo.Main -C out .
java -jar demo.jar
```

**Expected result:**
```text
Packaged
```

**Concept:** The archive entry point names the qualified main class.

---

## Interview Questions and Answers

### Interview Question 1 — What is a package?

**Answer:** A namespace and access boundary for Java types. Package names contribute to fully qualified type names.

### Interview Question 2 — Does an import copy or load class code?

**Answer:** No. It helps resolve source names at compile time. Runtime class loading follows separate rules.

### Interview Question 3 — Are subpackages imported by a wildcard?

**Answer:** No. java.util.* does not import java.util.concurrent or java.util.concurrent.atomic.

### Interview Question 4 — Where should the package declaration appear?

**Answer:** Before imports and type declarations, apart from comments, whitespace, and applicable package annotations.

### Interview Question 5 — Why avoid the unnamed package in larger projects?

**Answer:** Named packages provide organized namespaces and usable access boundaries; types in the unnamed package also have restrictions on use from named-package code.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Declare a package | The package declaration establishes the qualified class name. |
| Compile to package directories | -d builds the package directory structure under the chosen output root. |
| Launch by qualified name | The launcher uses a qualified class name, not a source path. |
| Import a standard-library class | Imports allow simple names without changing the underlying package. |
| Use a fully qualified type | A qualified type name can avoid an import. |

[← 070. Package-private access](070_Package-private_access.md) | [Course Index](../README.md) | [072. Inheritance →](072_Inheritance.md)
