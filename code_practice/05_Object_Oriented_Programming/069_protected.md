[← 068. private](068_private.md) | [Course Index](../README.md) | [070. Package-private access →](070_Package-private_access.md)

# Java protected Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/069_protected.md)

These exercises follow [069. protected](../../Road%20Map/05_Object_Oriented_Programming/069_protected.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each Java solution is a complete Main.java and runs independently. Nested helper types keep the exercise in one file; they do not demonstrate every cross-package access rule. Those distinctions are stated in the interview answers. Reflection here inspects declarations without bypassing access checks.

## Question 1 — Initialize protected superclass state

**Task:** Initialize protected superclass state.

```java
public class Main {
static class Base { protected int value; protected Base(int value) { this.value=value; } protected int doubled() { return value*2; } } static class Child extends Base { Child(int value) { super(value); } int read() { return value; } void set(int next) { value=next; } int readDouble() { return doubled(); } }
public static void main(String[] args) throws Exception {
System.out.println(new Child(7).read());
}
}
```

**Expected result:**
```text
7
```

**Concept:** A subclass constructor can invoke an accessible protected superclass constructor.

## Question 2 — Read an inherited protected field

**Task:** Read an inherited protected field.

```java
public class Main {
static class Base { protected int value; protected Base(int value) { this.value=value; } protected int doubled() { return value*2; } } static class Child extends Base { Child(int value) { super(value); } int read() { return value; } void set(int next) { value=next; } int readDouble() { return doubled(); } }
public static void main(String[] args) throws Exception {
Child child=new Child(4); System.out.println(child.read());
}
}
```

**Expected result:**
```text
4
```

**Concept:** Subclass instance code can access inherited protected state under the applicable rules.

## Question 3 — Update an inherited protected field

**Task:** Update an inherited protected field.

```java
public class Main {
static class Base { protected int value; protected Base(int value) { this.value=value; } protected int doubled() { return value*2; } } static class Child extends Base { Child(int value) { super(value); } int read() { return value; } void set(int next) { value=next; } int readDouble() { return doubled(); } }
public static void main(String[] args) throws Exception {
Child child=new Child(4); child.set(9); System.out.println(child.read());
}
}
```

**Expected result:**
```text
9
```

**Concept:** Protected state can be manipulated by permitted subclass code.

## Question 4 — Invoke a protected superclass helper

**Task:** Invoke a protected superclass helper.

```java
public class Main {
static class Base { protected int value; protected Base(int value) { this.value=value; } protected int doubled() { return value*2; } } static class Child extends Base { Child(int value) { super(value); } int read() { return value; } void set(int next) { value=next; } int readDouble() { return doubled(); } }
public static void main(String[] args) throws Exception {
System.out.println(new Child(4).readDouble());
}
}
```

**Expected result:**
```text
8
```

**Concept:** Inheritance can expose protected implementation hooks.

## Question 5 — Keep separate subclass objects

**Task:** Keep separate subclass objects.

```java
public class Main {
static class Base { protected int value; protected Base(int value) { this.value=value; } protected int doubled() { return value*2; } } static class Child extends Base { Child(int value) { super(value); } int read() { return value; } void set(int next) { value=next; } int readDouble() { return doubled(); } }
public static void main(String[] args) throws Exception {
Child a=new Child(1),b=new Child(2); a.set(9); System.out.println(b.read());
}
}
```

**Expected result:**
```text
2
```

**Concept:** Protected does not make a field shared or static.

## Question 6 — Use protected state through a superclass view in the same package

**Task:** Use protected state through a superclass view in the same package.

```java
public class Main {
static class Base { protected int value; protected Base(int value) { this.value=value; } protected int doubled() { return value*2; } } static class Child extends Base { Child(int value) { super(value); } int read() { return value; } void set(int next) { value=next; } int readDouble() { return doubled(); } }
public static void main(String[] args) throws Exception {
Base value=new Child(3); System.out.println(value.value);
}
}
```

**Expected result:**
```text
3
```

**Concept:** Protected also permits access from the declaring package; this single-file example stays in one package.

## Question 7 — Inspect a protected field declaration

**Task:** Inspect a protected field declaration.

```java
public class Main {
static class Base { protected int value; protected Base(int value) { this.value=value; } protected int doubled() { return value*2; } } static class Child extends Base { Child(int value) { super(value); } int read() { return value; } void set(int next) { value=next; } int readDouble() { return doubled(); } }
public static void main(String[] args) throws Exception {
System.out.println(java.lang.reflect.Modifier.isProtected(Base.class.getDeclaredField("value").getModifiers()));
}
}
```

**Expected result:**
```text
true
```

**Concept:** Reflection reports the declared access modifier.

## Question 8 — Inspect a protected method declaration

**Task:** Inspect a protected method declaration.

```java
public class Main {
static class Base { protected int value; protected Base(int value) { this.value=value; } protected int doubled() { return value*2; } } static class Child extends Base { Child(int value) { super(value); } int read() { return value; } void set(int next) { value=next; } int readDouble() { return doubled(); } }
public static void main(String[] args) throws Exception {
System.out.println(java.lang.reflect.Modifier.isProtected(Base.class.getDeclaredMethod("doubled").getModifiers()));
}
}
```

**Expected result:**
```text
true
```

**Concept:** A helper can be protected without being public.

## Question 9 — Inspect a protected constructor declaration

**Task:** Inspect a protected constructor declaration.

```java
public class Main {
static class Base { protected int value; protected Base(int value) { this.value=value; } protected int doubled() { return value*2; } } static class Child extends Base { Child(int value) { super(value); } int read() { return value; } void set(int next) { value=next; } int readDouble() { return doubled(); } }
public static void main(String[] args) throws Exception {
System.out.println(java.lang.reflect.Modifier.isProtected(Base.class.getDeclaredConstructor(int.class).getModifiers()));
}
}
```

**Expected result:**
```text
true
```

**Concept:** Constructor accessibility follows its declaration and invocation context.

## Question 10 — Expose a narrow subclass operation

**Task:** Expose a narrow subclass operation.

```java
public class Main {
static class Base { protected int value; protected Base(int value) { this.value=value; } protected int doubled() { return value*2; } } static class Child extends Base { Child(int value) { super(value); } int read() { return value; } void set(int next) { value=next; } int readDouble() { return doubled(); } }
public static void main(String[] args) throws Exception {
Child child=new Child(5); child.set(6); System.out.println(child.readDouble());
}
}
```

**Expected result:**
```text
12
```

**Concept:** A subclass can wrap inherited details in a smaller API.

---

## Interview Questions and Answers

### Interview Question 1 — Who can access a protected member?

**Answer:** Code in the declaring package and qualifying subclass code outside that package, subject to detailed protected-access rules.

### Interview Question 2 — What is the cross-package receiver restriction?

**Answer:** Within an outside-package subclass, access to a protected instance member through an expression is restricted by the qualifying expression's type; arbitrary superclass instances are not freely accessible.

### Interview Question 3 — Can an overriding method reduce protected access to private?

**Answer:** No. An override cannot reduce the accessibility of the inherited method.

### Interview Question 4 — Can a top-level class be protected?

**Answer:** No. protected is available for applicable members and nested types, not ordinary top-level classes.

### Interview Question 5 — Why prefer private fields even in extensible classes?

**Answer:** Protected mutable representation couples subclasses to implementation details. Protected methods can provide narrower extension points.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Initialize protected superclass state | A subclass constructor can invoke an accessible protected superclass constructor. |
| Read an inherited protected field | Subclass instance code can access inherited protected state under the applicable rules. |
| Update an inherited protected field | Protected state can be manipulated by permitted subclass code. |
| Invoke a protected superclass helper | Inheritance can expose protected implementation hooks. |
| Keep separate subclass objects | Protected does not make a field shared or static. |

[← 068. private](068_private.md) | [Course Index](../README.md) | [070. Package-private access →](070_Package-private_access.md)
