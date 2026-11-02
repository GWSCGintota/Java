[← 081. Multiple interfaces](081_Multiple_interfaces.md) | [Course Index](../README.md) | [083. Composition →](083_Composition.md)

# Java Interface default methods Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/082_Interface_default_methods.md)

These exercises follow [082. Interface default methods](../../Road%20Map/05_Object_Oriented_Programming/082_Interface_default_methods.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete Main.java with nested helper types. Compile and run each independently. Inputs are non-null unless explicitly tested; arithmetic uses small values. Nested fixtures keep examples focused and do not replace cross-package access exercises.

## Question 1 — Inherit a default method

**Task:** Inherit a default method.

```java
public class Main {
interface Greeting { String name(); default String greet() { return "Hello, "+name(); } } static class User implements Greeting { final String name; User(String name) { this.name=name; } public String name() { return name; } } static class LoudUser extends User { LoudUser(String name) { super(name); } @Override public String greet() { return super.greet().toUpperCase(java.util.Locale.ROOT); } } interface First { default String label() { return "First"; } } interface Second { default String label() { return "Second"; } } static class Both implements First,Second { public String label() { return First.super.label()+"+"+Second.super.label(); } }
public static void main(String[] args) {
System.out.println(new User("Ada").greet());
}
}
```

**Expected result:**
```text
Hello, Ada
```

**Concept:** A default method provides an interface implementation that a class may inherit.

## Question 2 — Invoke through the interface

**Task:** Invoke through the interface.

```java
public class Main {
interface Greeting { String name(); default String greet() { return "Hello, "+name(); } } static class User implements Greeting { final String name; User(String name) { this.name=name; } public String name() { return name; } } static class LoudUser extends User { LoudUser(String name) { super(name); } @Override public String greet() { return super.greet().toUpperCase(java.util.Locale.ROOT); } } interface First { default String label() { return "First"; } } interface Second { default String label() { return "Second"; } } static class Both implements First,Second { public String label() { return First.super.label()+"+"+Second.super.label(); } }
public static void main(String[] args) {
Greeting value=new User("Grace"); System.out.println(value.greet());
}
}
```

**Expected result:**
```text
Hello, Grace
```

**Concept:** Default methods participate in instance contract calls.

## Question 3 — Call an abstract operation from a default

**Task:** Call an abstract operation from a default.

```java
public class Main {
interface Greeting { String name(); default String greet() { return "Hello, "+name(); } } static class User implements Greeting { final String name; User(String name) { this.name=name; } public String name() { return name; } } static class LoudUser extends User { LoudUser(String name) { super(name); } @Override public String greet() { return super.greet().toUpperCase(java.util.Locale.ROOT); } } interface First { default String label() { return "First"; } } interface Second { default String label() { return "Second"; } } static class Both implements First,Second { public String label() { return First.super.label()+"+"+Second.super.label(); } }
public static void main(String[] args) {
System.out.println(new User("Bob").greet());
}
}
```

**Expected result:**
```text
Hello, Bob
```

**Concept:** A default method can use an operation supplied by the implementing class.

## Question 4 — Override a default in a class

**Task:** Override a default in a class.

```java
public class Main {
interface Greeting { String name(); default String greet() { return "Hello, "+name(); } } static class User implements Greeting { final String name; User(String name) { this.name=name; } public String name() { return name; } } static class LoudUser extends User { LoudUser(String name) { super(name); } @Override public String greet() { return super.greet().toUpperCase(java.util.Locale.ROOT); } } interface First { default String label() { return "First"; } } interface Second { default String label() { return "Second"; } } static class Both implements First,Second { public String label() { return First.super.label()+"+"+Second.super.label(); } }
public static void main(String[] args) {
System.out.println(new LoudUser("Ada").greet());
}
}
```

**Expected result:**
```text
HELLO, ADA
```

**Concept:** A class can specialize inherited default behavior.

## Question 5 — Dispatch to a class override through the interface

**Task:** Dispatch to a class override through the interface.

```java
public class Main {
interface Greeting { String name(); default String greet() { return "Hello, "+name(); } } static class User implements Greeting { final String name; User(String name) { this.name=name; } public String name() { return name; } } static class LoudUser extends User { LoudUser(String name) { super(name); } @Override public String greet() { return super.greet().toUpperCase(java.util.Locale.ROOT); } } interface First { default String label() { return "First"; } } interface Second { default String label() { return "Second"; } } static class Both implements First,Second { public String label() { return First.super.label()+"+"+Second.super.label(); } }
public static void main(String[] args) {
Greeting value=new LoudUser("Grace"); System.out.println(value.greet());
}
}
```

**Expected result:**
```text
HELLO, GRACE
```

**Concept:** Runtime instance dispatch selects the class implementation.

## Question 6 — Resolve two unrelated defaults

**Task:** Resolve two unrelated defaults.

```java
public class Main {
interface Greeting { String name(); default String greet() { return "Hello, "+name(); } } static class User implements Greeting { final String name; User(String name) { this.name=name; } public String name() { return name; } } static class LoudUser extends User { LoudUser(String name) { super(name); } @Override public String greet() { return super.greet().toUpperCase(java.util.Locale.ROOT); } } interface First { default String label() { return "First"; } } interface Second { default String label() { return "Second"; } } static class Both implements First,Second { public String label() { return First.super.label()+"+"+Second.super.label(); } }
public static void main(String[] args) {
System.out.println(new Both().label());
}
}
```

**Expected result:**
```text
First+Second
```

**Concept:** An explicit override resolves the inherited default-method conflict.

## Question 7 — View the resolved method through the first contract

**Task:** View the resolved method through the first contract.

```java
public class Main {
interface Greeting { String name(); default String greet() { return "Hello, "+name(); } } static class User implements Greeting { final String name; User(String name) { this.name=name; } public String name() { return name; } } static class LoudUser extends User { LoudUser(String name) { super(name); } @Override public String greet() { return super.greet().toUpperCase(java.util.Locale.ROOT); } } interface First { default String label() { return "First"; } } interface Second { default String label() { return "Second"; } } static class Both implements First,Second { public String label() { return First.super.label()+"+"+Second.super.label(); } }
public static void main(String[] args) {
First value=new Both(); System.out.println(value.label());
}
}
```

**Expected result:**
```text
First+Second
```

**Concept:** The class override satisfies both views.

## Question 8 — View the resolved method through the second contract

**Task:** View the resolved method through the second contract.

```java
public class Main {
interface Greeting { String name(); default String greet() { return "Hello, "+name(); } } static class User implements Greeting { final String name; User(String name) { this.name=name; } public String name() { return name; } } static class LoudUser extends User { LoudUser(String name) { super(name); } @Override public String greet() { return super.greet().toUpperCase(java.util.Locale.ROOT); } } interface First { default String label() { return "First"; } } interface Second { default String label() { return "Second"; } } static class Both implements First,Second { public String label() { return First.super.label()+"+"+Second.super.label(); } }
public static void main(String[] args) {
Second value=new Both(); System.out.println(value.label());
}
}
```

**Expected result:**
```text
First+Second
```

**Concept:** Interface view does not change the object's chosen override.

## Question 9 — Use a functional interface with a default

**Task:** Use a functional interface with a default.

```java
public class Main {
interface Greeting { String name(); default String greet() { return "Hello, "+name(); } } static class User implements Greeting { final String name; User(String name) { this.name=name; } public String name() { return name; } } static class LoudUser extends User { LoudUser(String name) { super(name); } @Override public String greet() { return super.greet().toUpperCase(java.util.Locale.ROOT); } } interface First { default String label() { return "First"; } } interface Second { default String label() { return "Second"; } } static class Both implements First,Second { public String label() { return First.super.label()+"+"+Second.super.label(); } }
public static void main(String[] args) {
Greeting value=()->"Lambda"; System.out.println(value.greet());
}
}
```

**Expected result:**
```text
Hello, Lambda
```

**Concept:** A default method does not add an abstract operation.

## Question 10 — Traverse inherited and overridden defaults

**Task:** Traverse inherited and overridden defaults.

```java
public class Main {
interface Greeting { String name(); default String greet() { return "Hello, "+name(); } } static class User implements Greeting { final String name; User(String name) { this.name=name; } public String name() { return name; } } static class LoudUser extends User { LoudUser(String name) { super(name); } @Override public String greet() { return super.greet().toUpperCase(java.util.Locale.ROOT); } } interface First { default String label() { return "First"; } } interface Second { default String label() { return "Second"; } } static class Both implements First,Second { public String label() { return First.super.label()+"+"+Second.super.label(); } }
public static void main(String[] args) {
Greeting[] values={new User("Ada"),new LoudUser("Bob")}; for(Greeting value:values) { System.out.println(value.greet()); }
}
}
```

**Expected result:**
```text
Hello, Ada
HELLO, BOB
```

**Concept:** A shared default and class override can coexist under one contract.

---

## Interview Questions and Answers

### Interview Question 1 — What is a default interface method?

**Answer:** An instance method with an implementation declared using default in an interface, available for compatible implementers to inherit.

### Interview Question 2 — Does a default method make a functional interface non-functional?

**Answer:** Not by itself. Functional-interface analysis counts effective abstract operations, not implemented default methods.

### Interview Question 3 — What happens when unrelated interfaces provide conflicting defaults?

**Answer:** A class must resolve the conflict with a compatible explicit override; it can choose or combine permitted superinterface defaults.

### Interview Question 4 — Do class methods or interface defaults take precedence?

**Answer:** Applicable class-method inheritance rules take precedence over interface defaults; detailed compatibility and abstract-method rules still apply.

### Interview Question 5 — Why add defaults carefully?

**Answer:** They support interface evolution but can introduce conflicts or behavioral surprises for existing implementers. Preserve clear contracts and compatibility assumptions.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Inherit a default method | A default method provides an interface implementation that a class may inherit. |
| Invoke through the interface | Default methods participate in instance contract calls. |
| Call an abstract operation from a default | A default method can use an operation supplied by the implementing class. |
| Override a default in a class | A class can specialize inherited default behavior. |
| Dispatch to a class override through the interface | Runtime instance dispatch selects the class implementation. |

[← 081. Multiple interfaces](081_Multiple_interfaces.md) | [Course Index](../README.md) | [083. Composition →](083_Composition.md)
