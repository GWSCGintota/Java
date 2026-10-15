# 253. Virtual threads

**Section:** Concurrency API  
**Level:** Intermediate

## Learning goal

Understand **Virtual threads**, why it matters in Java development, and how it connects to the topics around it.

## Key things to learn

- What **Virtual threads** means.
- The Java syntax, API, pattern, or concept associated with it.
- When you should use it in a real Java program.
- Common beginner mistakes and how to avoid them.
- How it relates to earlier and later topics in this roadmap.

## Starter note

Do not only memorize the definition. For this topic, aim to be able to **recognize it, explain it, and use it in a small example**.

## Example / syntax hint

```java
Thread.startVirtualThread(() -> System.out.println("Hello"));
```

> The snippet above is only a starting hint. Expand it into a complete working example while studying the topic.

## Practice

Write or sketch a small concurrent task that demonstrates **Virtual threads**, then identify one possible concurrency risk.

## Completion checklist

- [ ] I can explain **Virtual threads** in my own words.
- [ ] I can recognize it in Java code.
- [ ] I can write or modify a small example that uses it.
- [ ] I know at least one common mistake related to it.
- [ ] I understand why this topic is useful before moving on.

---

[← 252. Fork/Join framework](252_ForkJoin_framework.md) | [Course Index](../README.md) | [254. JVM architecture →](../18_JVM_Internals/254_JVM_architecture.md)
