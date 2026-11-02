[← 089. hashCode()](089_hashCode.md) | [Course Index](../README.md) | [091. Enums →](../06_Enums_Records_and_Modern_Java_Types/091_Enums.md)

# Java instanceof Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/090_instanceof.md)

These exercises follow [090. instanceof](../../Road%20Map/05_Object_Oriented_Programming/090_instanceof.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete Main.java with nested helper types. Run each question independently. Examples define their own ownership and null policies; Java references alone do not enforce UML lifecycle relationships.

## Question 1 — Test a concrete runtime type

**Task:** Test a concrete runtime type.

```java
public class Main {
interface Named { String name(); } static class Animal { } static class Dog extends Animal implements Named { public String name() { return "Rex"; } } static class Cat extends Animal { }
public static void main(String[] args) throws Exception {
Animal value=new Dog(); System.out.println(value instanceof Dog);
}
}
```

**Expected result:**
```text
true
```

**Concept:** instanceof checks compatibility with the runtime value.

## Question 2 — Test a superclass

**Task:** Test a superclass.

```java
public class Main {
interface Named { String name(); } static class Animal { } static class Dog extends Animal implements Named { public String name() { return "Rex"; } } static class Cat extends Animal { }
public static void main(String[] args) throws Exception {
Object value=new Dog(); System.out.println(value instanceof Animal);
}
}
```

**Expected result:**
```text
true
```

**Concept:** A subtype instance also satisfies its superclass type.

## Question 3 — Test an implemented interface

**Task:** Test an implemented interface.

```java
public class Main {
interface Named { String name(); } static class Animal { } static class Dog extends Animal implements Named { public String name() { return "Rex"; } } static class Cat extends Animal { }
public static void main(String[] args) throws Exception {
Object value=new Dog(); System.out.println(value instanceof Named);
}
}
```

**Expected result:**
```text
true
```

**Concept:** Interface membership is part of runtime type compatibility.

## Question 4 — Test a different subtype

**Task:** Test a different subtype.

```java
public class Main {
interface Named { String name(); } static class Animal { } static class Dog extends Animal implements Named { public String name() { return "Rex"; } } static class Cat extends Animal { }
public static void main(String[] args) throws Exception {
Animal value=new Cat(); System.out.println(value instanceof Dog);
}
}
```

**Expected result:**
```text
false
```

**Concept:** Sibling subtypes are not interchangeable just because they share a parent.

## Question 5 — Test null

**Task:** Test null.

```java
public class Main {
interface Named { String name(); } static class Animal { } static class Dog extends Animal implements Named { public String name() { return "Rex"; } } static class Cat extends Animal { }
public static void main(String[] args) throws Exception {
Animal value=null; System.out.println(value instanceof Dog);
}
}
```

**Expected result:**
```text
false
```

**Concept:** instanceof returns false for null.

## Question 6 — Guard a downcast

**Task:** Guard a downcast.

```java
public class Main {
interface Named { String name(); } static class Animal { } static class Dog extends Animal implements Named { public String name() { return "Rex"; } } static class Cat extends Animal { }
public static void main(String[] args) throws Exception {
Animal value=new Dog(); if(value instanceof Dog) { System.out.println(((Dog)value).name()); }
}
}
```

**Expected result:**
```text
Rex
```

**Concept:** A positive type test can establish a safe downcast under this unchanged local reference.

## Question 7 — Guard an interface cast

**Task:** Guard an interface cast.

```java
public class Main {
interface Named { String name(); } static class Animal { } static class Dog extends Animal implements Named { public String name() { return "Rex"; } } static class Cat extends Animal { }
public static void main(String[] args) throws Exception {
Object value=new Dog(); if(value instanceof Named) { System.out.println(((Named)value).name()); }
}
}
```

**Expected result:**
```text
Rex
```

**Concept:** A verified interface view exposes its declared operations.

## Question 8 — Classify a mixed array

**Task:** Classify a mixed array.

```java
public class Main {
interface Named { String name(); } static class Animal { } static class Dog extends Animal implements Named { public String name() { return "Rex"; } } static class Cat extends Animal { }
public static void main(String[] args) throws Exception {
Animal[] values={new Dog(),new Cat()}; for(Animal value:values) { System.out.println(value instanceof Dog ? "Dog" : "Other"); }
}
}
```

**Expected result:**
```text
Dog
Other
```

**Concept:** Type checks can support a deliberate classification task.

## Question 9 — Contrast exact class equality

**Task:** Contrast exact class equality.

```java
public class Main {
interface Named { String name(); } static class Animal { } static class Dog extends Animal implements Named { public String name() { return "Rex"; } } static class Cat extends Animal { }
public static void main(String[] args) throws Exception {
Animal value=new Dog(); System.out.println(value.getClass()==Animal.class); System.out.println(value instanceof Animal);
}
}
```

**Expected result:**
```text
false
true
```

**Concept:** Exact runtime class and subtype compatibility answer different questions.

## Question 10 — Reject an unsafe downcast explicitly

**Task:** Reject an unsafe downcast explicitly.

```java
public class Main {
interface Named { String name(); } static class Animal { } static class Dog extends Animal implements Named { public String name() { return "Rex"; } } static class Cat extends Animal { }
public static void main(String[] args) throws Exception {
Animal value=new Cat(); if(!(value instanceof Dog)) { System.out.println("Not a dog"); }
}
}
```

**Expected result:**
```text
Not a dog
```

**Concept:** Check compatibility before a cast rather than assuming every parent instance has one subtype.

---

## Interview Questions and Answers

### Interview Question 1 — What does instanceof test?

**Answer:** Whether a non-null value is compatible with the specified reference type under its runtime type relationship.

### Interview Question 2 — What is the result for null?

**Answer:** false, without throwing solely because the tested value is null.

### Interview Question 3 — How does getClass comparison differ?

**Answer:** getClass equality tests exact runtime-class identity, while instanceof includes compatible subclasses and interface implementations.

### Interview Question 4 — Does a successful type check convert the object?

**Answer:** No. It establishes a compatible view. A cast changes the compile-time view rather than creating a different object.

### Interview Question 5 — When should repeated instanceof branches be reconsidered?

**Answer:** When ordinary behavior could be expressed through a common contract and dynamic dispatch. Keep type inspection when classification itself is the domain requirement.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Test a concrete runtime type | instanceof checks compatibility with the runtime value. |
| Test a superclass | A subtype instance also satisfies its superclass type. |
| Test an implemented interface | Interface membership is part of runtime type compatibility. |
| Test a different subtype | Sibling subtypes are not interchangeable just because they share a parent. |
| Test null | instanceof returns false for null. |

[← 089. hashCode()](089_hashCode.md) | [Course Index](../README.md) | [091. Enums →](../06_Enums_Records_and_Modern_Java_Types/091_Enums.md)
