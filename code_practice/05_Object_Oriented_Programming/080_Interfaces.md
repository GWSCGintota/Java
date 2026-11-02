[← 079. Abstract methods](079_Abstract_methods.md) | [Course Index](../README.md) | [081. Multiple interfaces →](081_Multiple_interfaces.md)

# Java Interfaces Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/080_Interfaces.md)

These exercises follow [080. Interfaces](../../Road%20Map/05_Object_Oriented_Programming/080_Interfaces.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete Main.java with nested helper types. Compile and run each independently. Inputs are non-null unless explicitly tested; arithmetic uses small values. Nested fixtures keep examples focused and do not replace cross-package access exercises.

## Question 1 — Implement an interface

**Task:** Implement an interface.

```java
public class Main {
interface Named { String name(); } static class User implements Named { private final String name; User(String name) { this.name=name; } public String name() { return name; } } static String greet(Named value) { return "Hello, "+value.name(); }
public static void main(String[] args) {
System.out.println(new User("Ada").name());
}
}
```

**Expected result:**
```text
Ada
```

**Concept:** A class supplies the interface's required operation.

## Question 2 — Use an interface reference

**Task:** Use an interface reference.

```java
public class Main {
interface Named { String name(); } static class User implements Named { private final String name; User(String name) { this.name=name; } public String name() { return name; } } static String greet(Named value) { return "Hello, "+value.name(); }
public static void main(String[] args) {
Named value=new User("Grace"); System.out.println(value.name());
}
}
```

**Expected result:**
```text
Grace
```

**Concept:** A reference can depend on the contract rather than the implementing class.

## Question 3 — Pass a contract to a method

**Task:** Pass a contract to a method.

```java
public class Main {
interface Named { String name(); } static class User implements Named { private final String name; User(String name) { this.name=name; } public String name() { return name; } } static String greet(Named value) { return "Hello, "+value.name(); }
public static void main(String[] args) {
System.out.println(greet(new User("Ada")));
}
}
```

**Expected result:**
```text
Hello, Ada
```

**Concept:** Interface parameters decouple callers from a particular implementation.

## Question 4 — Use a lambda for a functional interface

**Task:** Use a lambda for a functional interface.

```java
public class Main {
interface Named { String name(); } static class User implements Named { private final String name; User(String name) { this.name=name; } public String name() { return name; } } static String greet(Named value) { return "Hello, "+value.name(); }
public static void main(String[] args) {
Named value=()->"Lambda"; System.out.println(value.name());
}
}
```

**Expected result:**
```text
Lambda
```

**Concept:** An interface with one effective abstract operation can target a lambda.

## Question 5 — Use an anonymous implementation

**Task:** Use an anonymous implementation.

```java
public class Main {
interface Named { String name(); } static class User implements Named { private final String name; User(String name) { this.name=name; } public String name() { return name; } } static String greet(Named value) { return "Hello, "+value.name(); }
public static void main(String[] args) {
Named value=new Named() { public String name() { return "Anonymous"; } }; System.out.println(value.name());
}
}
```

**Expected result:**
```text
Anonymous
```

**Concept:** A class body can implement an interface directly at a creation site.

## Question 6 — Traverse mixed implementations

**Task:** Traverse mixed implementations.

```java
public class Main {
interface Named { String name(); } static class User implements Named { private final String name; User(String name) { this.name=name; } public String name() { return name; } } static String greet(Named value) { return "Hello, "+value.name(); }
public static void main(String[] args) {
Named[] values={new User("Ada"),()->"Grace"}; for(Named value:values) { System.out.println(greet(value)); }
}
}
```

**Expected result:**
```text
Hello, Ada
Hello, Grace
```

**Concept:** The same contract can have class-based and lambda-based implementations.

## Question 7 — Inspect interface metadata

**Task:** Inspect interface metadata.

```java
public class Main {
interface Named { String name(); } static class User implements Named { private final String name; User(String name) { this.name=name; } public String name() { return name; } } static String greet(Named value) { return "Hello, "+value.name(); }
public static void main(String[] args) {
System.out.println(Named.class.isInterface());
}
}
```

**Expected result:**
```text
true
```

**Concept:** An interface defines a reference type and contract.

## Question 8 — Check implementation membership

**Task:** Check implementation membership.

```java
public class Main {
interface Named { String name(); } static class User implements Named { private final String name; User(String name) { this.name=name; } public String name() { return name; } } static String greet(Named value) { return "Hello, "+value.name(); }
public static void main(String[] args) {
System.out.println(new User("Ada") instanceof Named);
}
}
```

**Expected result:**
```text
true
```

**Concept:** An implementing object satisfies the interface type relationship.

## Question 9 — Bind an interface operation as a supplier

**Task:** Bind an interface operation as a supplier.

```java
public class Main {
interface Named { String name(); } static class User implements Named { private final String name; User(String name) { this.name=name; } public String name() { return name; } } static String greet(Named value) { return "Hello, "+value.name(); }
public static void main(String[] args) {
Named value=new User("Ada"); java.util.function.Supplier<String> name=value::name; System.out.println(name.get());
}
}
```

**Expected result:**
```text
Ada
```

**Concept:** Interface calls can supply compatible functional behavior.

## Question 10 — Guard a nullable contract reference

**Task:** Guard a nullable contract reference.

```java
public class Main {
interface Named { String name(); } static class User implements Named { private final String name; User(String name) { this.name=name; } public String name() { return name; } } static String greet(Named value) { return "Hello, "+value.name(); }
public static void main(String[] args) {
Named value=null; System.out.println(value==null ? "Missing" : greet(value));
}
}
```

**Expected result:**
```text
Missing
```

**Concept:** An interface reference can still be null.

---

## Interview Questions and Answers

### Interview Question 1 — What does implements declare?

**Answer:** That a class satisfies one or more interface contracts, completing required abstract methods if the class is concrete.

### Interview Question 2 — What access must a class implementation of a public interface method have?

**Answer:** It must be public; reducing the interface method's accessibility is not allowed.

### Interview Question 3 — Can interfaces contain state fields?

**Answer:** Interface fields are implicitly public static final, not per-instance mutable fields.

### Interview Question 4 — Can interfaces contain implemented methods?

**Answer:** They can declare default and static methods; modern releases also support private interface helper methods. Release requirements must match the code.

### Interview Question 5 — Can interfaces be instantiated directly?

**Answer:** Not as plain interface objects. A new expression can create a concrete implementing class or an anonymous implementation.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Implement an interface | A class supplies the interface's required operation. |
| Use an interface reference | A reference can depend on the contract rather than the implementing class. |
| Pass a contract to a method | Interface parameters decouple callers from a particular implementation. |
| Use a lambda for a functional interface | An interface with one effective abstract operation can target a lambda. |
| Use an anonymous implementation | A class body can implement an interface directly at a creation site. |

[← 079. Abstract methods](079_Abstract_methods.md) | [Course Index](../README.md) | [081. Multiple interfaces →](081_Multiple_interfaces.md)
