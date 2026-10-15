# Core Java Interview Questions

Use these as revision prompts. Try answering each question aloud before opening the answer.

## 1. What are the main features of Java?

**Answer guide:** Platform independence, object-oriented design, automatic memory management, strong typing, multithreading, and a large standard library.

## 2. What is the difference between JDK, JRE, and JVM?

**Answer guide:** JDK is the development kit, JRE is the runtime environment, and JVM executes Java bytecode.

## 3. Why is Java platform independent?

**Answer guide:** Java source is compiled into bytecode, which runs on a JVM available for each supported platform.

## 4. What is bytecode?

**Answer guide:** The intermediate instruction format produced by the Java compiler and executed by the JVM.

## 5. What is the difference between == and equals()?

**Answer guide:** `==` compares primitive values or object references; `equals()` is intended for logical/content equality when overridden.

## 6. Is Java pass-by-reference?

**Answer guide:** No. Java is always pass-by-value. For objects, the copied value is the object reference.

## 7. What is the difference between primitive and reference types?

**Answer guide:** Primitive variables store values directly; reference variables store references to objects.

## 8. What does final mean in Java?

**Answer guide:** A final variable cannot be reassigned, a final method cannot be overridden, and a final class cannot be extended.

## 9. What is static?

**Answer guide:** A static member belongs to the class rather than to individual objects.

## 10. Can main() be overloaded?

**Answer guide:** Yes, but the JVM entry point must still have the standard `public static void main(String[] args)` signature.

## 11. What is autoboxing and unboxing?

**Answer guide:** Autoboxing converts primitives to wrapper objects automatically; unboxing converts wrapper objects back to primitives.

## 12. What are wrapper classes?

**Answer guide:** Object counterparts of primitives such as `Integer`, `Double`, `Boolean`, and `Character`.

## 13. What is String immutability?

**Answer guide:** A String object cannot be changed after creation; operations create new String objects.

## 14. Why is String immutable?

**Answer guide:** It improves security, thread safety, caching, and hash-based collection behavior.

## 15. String vs StringBuilder vs StringBuffer?

**Answer guide:** String is immutable; StringBuilder is mutable and not synchronized; StringBuffer is mutable and synchronized.

---

[← Back to Java Roadmap](../README.md)
