[← Previous: 48. Common Java Mistakes and Fixes](48-common-java-mistakes-and-fixes.md) | [Main Index](../main.md)

---

# 49. Java Interview Questions

## Beginner questions

### 1. What is Java?
Java is a high-level, object-oriented programming language used to build applications that can run on any platform with a compatible JVM.

### 2. What is the JVM?
The JVM, or Java Virtual Machine, executes Java bytecode and provides runtime services such as memory management and garbage collection.

### 3. What is the JDK?
The JDK, or Java Development Kit, contains the tools needed to develop Java applications, including `javac`, `java`, libraries, and other utilities.

### 4. What is bytecode?
Bytecode is the compiled form of Java source code. It is stored in `.class` files and executed by the JVM.

### 5. Why is Java platform-independent?
Java source code compiles to bytecode, and bytecode can run on different operating systems through compatible JVM implementations.

### 6. What is the main method?
The main method is the entry point of a standard Java application:

```java
public static void main(String[] args)
```

### 7. What are primitive data types?
Primitive types are basic built-in value types such as `int`, `long`, `double`, `char`, and `boolean`.

### 8. What is the difference between primitive and reference types?
Primitive variables store values directly. Reference variables store references to objects.

### 9. What is a class?
A class is a blueprint that defines fields and methods for objects.

### 10. What is an object?
An object is an instance of a class.

---

## OOP questions

### 11. What are the four pillars of OOP?
Encapsulation, inheritance, polymorphism, and abstraction.

### 12. What is encapsulation?
Encapsulation hides internal object state and controls access through methods.

### 13. What is inheritance?
Inheritance allows a class to reuse and extend another class using `extends`.

### 14. What is polymorphism?
Polymorphism allows one parent type or interface to refer to different child implementations.

### 15. What is abstraction?
Abstraction exposes essential behaviour while hiding implementation details.

### 16. What is method overloading?
Method overloading means having multiple methods with the same name but different parameter lists.

### 17. What is method overriding?
Method overriding means a subclass provides a specific implementation of a method defined in a parent class or interface.

### 18. Abstract class vs interface?
An abstract class can hold state and partial implementation. An interface defines a contract and can include abstract, default, static, and private methods.

### 19. What is `this`?
`this` refers to the current object.

### 20. What is `super`?
`super` refers to the parent class and can be used to call parent constructors or methods.

---

## Core Java questions

### 21. What is the difference between `==` and `.equals()`?
`==` compares primitive values or object references. `.equals()` compares object content when properly implemented.

### 22. Why is String immutable?
String immutability improves safety, caching, sharing, security, and thread-safety.

### 23. String vs StringBuilder vs StringBuffer?
`String` is immutable. `StringBuilder` is mutable and not synchronized. `StringBuffer` is mutable and synchronized.

### 24. What is a constructor?
A constructor initializes a new object.

### 25. Can a constructor be overloaded?
Yes. A class can have multiple constructors with different parameter lists.

### 26. What is `static`?
`static` belongs to the class rather than an individual object.

### 27. What is `final`?
`final` prevents reassignment for variables, overriding for methods, and inheritance for classes.

### 28. What are access modifiers?
They control visibility: `private`, package-private, `protected`, and `public`.

### 29. What is a package?
A package organizes related classes and helps avoid naming conflicts.

### 30. What is an enum?
An enum represents a fixed set of constants.

---

## Exception questions

### 31. What is exception handling?
Exception handling manages runtime errors using `try`, `catch`, `finally`, `throw`, and `throws`.

### 32. Checked vs unchecked exceptions?
Checked exceptions must be handled or declared. Unchecked exceptions extend `RuntimeException` and do not require declaration.

### 33. What is `finally`?
`finally` is a block that runs after try/catch, usually for cleanup.

### 34. What is try-with-resources?
It automatically closes resources that implement `AutoCloseable`.

### 35. How do you create a custom exception?
Create a class extending `Exception` or `RuntimeException`.

---

## Collections questions

### 36. What is the Collections Framework?
It is a set of interfaces and classes for storing and processing groups of objects.

### 37. List vs Set vs Map?
`List` allows ordered duplicates. `Set` prevents duplicates. `Map` stores key-value pairs.

### 38. ArrayList vs LinkedList?
`ArrayList` is backed by an array and is efficient for random access. `LinkedList` is node-based and can be efficient for frequent insertions/removals at ends.

### 39. HashMap vs TreeMap?
`HashMap` is hash-based and unordered. `TreeMap` is sorted by key.

### 40. HashSet vs TreeSet?
`HashSet` is unordered and hash-based. `TreeSet` stores elements in sorted order.

### 41. What is ConcurrentHashMap?
A thread-safe map designed for concurrent access.

### 42. What is an Iterator?
An Iterator is used to traverse a collection safely.

### 43. What are generics?
Generics provide compile-time type safety for classes, methods, and collections.

### 44. What is autoboxing?
Autoboxing automatically converts a primitive to its wrapper type.

### 45. Comparable vs Comparator?
`Comparable` defines natural ordering inside the class. `Comparator` defines external custom ordering.

---

## Modern Java questions

### 46. What is a lambda expression?
A lambda is a concise way to implement a functional interface.

### 47. What is a functional interface?
An interface with exactly one abstract method.

### 48. What is the Stream API?
The Stream API processes data sequences using operations such as `filter`, `map`, `sorted`, and `collect`.

### 49. What is Optional?
`Optional` represents a value that may or may not be present.

### 50. What is a record?
A record is a concise immutable data carrier.

### 51. What is a switch expression?
A switch expression returns a value and supports concise arrow syntax.

### 52. What are virtual threads?
Virtual threads are lightweight JVM-managed threads useful for high-concurrency blocking I/O workloads.

### 53. What is the Java module system?
It organizes code into modules with explicit dependencies and exported packages.

### 54. What is the Date and Time API?
The `java.time` API provides modern date and time classes such as `LocalDate`, `LocalDateTime`, and `ZonedDateTime`.

### 55. What is pattern matching?
Pattern matching simplifies checking and extracting values based on type or structure.

---

## Concurrency questions

### 56. What is a thread?
A thread is an independent path of execution inside a process.

### 57. Thread vs Runnable?
`Thread` represents an execution thread. `Runnable` represents a task that can be run by a thread.

### 58. What is synchronization?
Synchronization controls access to shared mutable data across threads.

### 59. What is a race condition?
A race condition occurs when program results depend on unpredictable thread timing.

### 60. What is a deadlock?
A deadlock occurs when threads wait forever for locks held by each other.

### 61. What is ExecutorService?
It manages task execution using thread pools.

### 62. What is Callable?
`Callable` is like `Runnable`, but it can return a value and throw checked exceptions.

### 63. What is Future?
A `Future` represents a result that may become available later.

### 64. What is CompletableFuture?
It supports asynchronous programming and chaining dependent tasks.

### 65. When should virtual threads be used?
They are useful for many concurrent blocking I/O tasks, such as server request handling or network calls.

---

## Advanced questions

### 66. What is garbage collection?
Garbage collection automatically removes unreachable objects from memory.

### 67. What is a memory leak in Java?
A memory leak occurs when objects are no longer needed but remain reachable, preventing garbage collection.

### 68. What is reflection?
Reflection allows runtime inspection and manipulation of classes, fields, methods, and constructors.

### 69. What are annotations?
Annotations provide metadata for the compiler, tools, frameworks, or runtime processing.

### 70. What is JDBC?
JDBC is Java's standard API for connecting to relational databases.

### 71. Why use PreparedStatement?
It helps prevent SQL injection and allows efficient parameterized queries.

### 72. What is serialization?
Serialization converts an object into a byte stream.

### 73. Heap vs stack?
The heap stores objects. The stack stores method frames, local variables, and call information.

### 74. What is immutability?
An immutable object cannot be changed after creation.

### 75. What is dependency injection?
Dependency injection supplies required dependencies from outside an object, improving testability and loose coupling.

---

[← Previous: 48. Common Java Mistakes and Fixes](48-common-java-mistakes-and-fixes.md) | [Main Index](../main.md)
