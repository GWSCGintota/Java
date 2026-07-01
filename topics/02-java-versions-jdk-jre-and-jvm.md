[← Previous: 1. Introduction to Java](01-introduction-to-java.md) | [Main Index](../main.md) | [Next: 3. Installing Java and Environment Setup →](03-installing-java-and-environment-setup.md)

---

# 2. Java Versions, JDK, JRE, and JVM

## Learning objectives

- Understand Java release types, the JDK, JRE, JVM, bytecode, and why Java is platform-independent.
- Recognize where this topic fits in real Java application development.
- Write small working Java examples related to this topic.
- Avoid the most common mistakes for this topic.

---

## Explanation

**Java Versions, JDK, JRE, and JVM** is an important part of Java programming. A strong Java developer should understand both the syntax and the reason behind the concept.

When learning this topic, focus on:

- What problem the feature solves
- How the syntax works
- When to use it
- When not to use it
- Common mistakes
- How it appears in real projects

---

## Key points

- Java is statically typed, so most variable and expression types are checked at compile time.
- Java code is organized into classes, packages, and modules.
- Good Java code should be readable, testable, maintainable, and safe.
- Prefer clear names and small methods.
- Handle errors intentionally instead of ignoring them.
- Use the Java standard library before writing custom utilities.
- Use modern Java features where they make code simpler and clearer.

---

## Example


```bash
java --version
javac --version
```

```text
Java source code (.java)
        |
        v
javac compiler
        |
        v
Bytecode (.class)
        |
        v
JVM executes bytecode
```


---

## Real-world use

This topic appears frequently in:

- Console applications
- Backend APIs
- Spring Boot applications
- Android applications
- Enterprise systems
- Data-processing applications
- Testing and automation tools

For example, a backend Java application normally uses classes, methods, exceptions, collections, logging, build tools, and tests together rather than as isolated topics.

---

## Common mistakes

- Memorizing syntax without understanding the concept.
- Writing too much logic in one method.
- Ignoring exceptions or printing errors without handling them.
- Using mutable shared state unnecessarily.
- Not writing tests for important logic.
- Using outdated Java approaches when modern alternatives are clearer.

---

## Practice tasks

1. Create a small Java file for this topic.
2. Compile and run it using `javac` and `java`, or run it inside an IDE.
3. Change one value and observe the output.
4. Add one error intentionally and read the compiler or runtime message.
5. Write one sentence explaining the concept in your own words.

---

## Quick revision

Before moving to the next topic, make sure you can answer:

- What is the purpose of this feature?
- What is the basic syntax?
- What is one common mistake?
- How would this be used in a real project?

---

[← Previous: 1. Introduction to Java](01-introduction-to-java.md) | [Main Index](../main.md) | [Next: 3. Installing Java and Environment Setup →](03-installing-java-and-environment-setup.md)
