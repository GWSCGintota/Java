[← 069. protected](069_protected.md) | [Course Index](../README.md) | [071. Packages →](071_Packages.md)

# Java Package-private access Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/070_Package-private_access.md)

These exercises follow [070. Package-private access](../../Road%20Map/05_Object_Oriented_Programming/070_Package-private_access.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each Java solution is a complete Main.java and runs independently. Nested helper types keep the exercise in one file; they do not demonstrate every cross-package access rule. Those distinctions are stated in the interview answers. Reflection here inspects declarations without bypassing access checks.

## Question 1 — Use a package-private helper in the same package

**Task:** Use a package-private helper in the same package.

```java
public class Main {
static class Internal { int value; Internal(int value) { this.value=value; } int read() { return value; } void set(int value) { this.value=value; } }
public static void main(String[] args) throws Exception {
System.out.println(new Internal(7).read());
}
}
```

**Expected result:**
```text
7
```

**Concept:** A type or member with no access keyword can be package-private.

## Question 2 — Read a package-private field

**Task:** Read a package-private field.

```java
public class Main {
static class Internal { int value; Internal(int value) { this.value=value; } int read() { return value; } void set(int value) { this.value=value; } }
public static void main(String[] args) throws Exception {
System.out.println(new Internal(4).value);
}
}
```

**Expected result:**
```text
4
```

**Concept:** Same-package code can access package-private members.

## Question 3 — Write a package-private field

**Task:** Write a package-private field.

```java
public class Main {
static class Internal { int value; Internal(int value) { this.value=value; } int read() { return value; } void set(int value) { this.value=value; } }
public static void main(String[] args) throws Exception {
Internal value=new Internal(1); value.value=9; System.out.println(value.read());
}
}
```

**Expected result:**
```text
9
```

**Concept:** Package-level access still permits direct mutation within the boundary.

## Question 4 — Call a package-private method

**Task:** Call a package-private method.

```java
public class Main {
static class Internal { int value; Internal(int value) { this.value=value; } int read() { return value; } void set(int value) { this.value=value; } }
public static void main(String[] args) throws Exception {
Internal value=new Internal(1); value.set(5); System.out.println(value.read());
}
}
```

**Expected result:**
```text
5
```

**Concept:** Package-private methods can support internal collaborations.

## Question 5 — Inspect the field access mask

**Task:** Inspect the field access mask.

```java
public class Main {
static class Internal { int value; Internal(int value) { this.value=value; } int read() { return value; } void set(int value) { this.value=value; } }
public static void main(String[] args) throws Exception {
int flags=Internal.class.getDeclaredField("value").getModifiers(); int mask=java.lang.reflect.Modifier.PUBLIC|java.lang.reflect.Modifier.PROTECTED|java.lang.reflect.Modifier.PRIVATE; System.out.println((flags&mask)==0);
}
}
```

**Expected result:**
```text
true
```

**Concept:** There is no package-private keyword or dedicated access bit.

## Question 6 — Inspect the constructor access mask

**Task:** Inspect the constructor access mask.

```java
public class Main {
static class Internal { int value; Internal(int value) { this.value=value; } int read() { return value; } void set(int value) { this.value=value; } }
public static void main(String[] args) throws Exception {
int flags=Internal.class.getDeclaredConstructor(int.class).getModifiers(); int mask=java.lang.reflect.Modifier.PUBLIC|java.lang.reflect.Modifier.PROTECTED|java.lang.reflect.Modifier.PRIVATE; System.out.println((flags&mask)==0);
}
}
```

**Expected result:**
```text
true
```

**Concept:** Constructors can also have package-private accessibility.

## Question 7 — Inspect the method access mask

**Task:** Inspect the method access mask.

```java
public class Main {
static class Internal { int value; Internal(int value) { this.value=value; } int read() { return value; } void set(int value) { this.value=value; } }
public static void main(String[] args) throws Exception {
int flags=Internal.class.getDeclaredMethod("read").getModifiers(); int mask=java.lang.reflect.Modifier.PUBLIC|java.lang.reflect.Modifier.PROTECTED|java.lang.reflect.Modifier.PRIVATE; System.out.println((flags&mask)==0);
}
}
```

**Expected result:**
```text
true
```

**Concept:** The lack of an access modifier applies to methods too.

## Question 8 — Compare declared and public field discovery

**Task:** Compare declared and public field discovery.

```java
public class Main {
static class Internal { int value; Internal(int value) { this.value=value; } int read() { return value; } void set(int value) { this.value=value; } }
public static void main(String[] args) throws Exception {
System.out.println(Internal.class.getDeclaredFields().length); System.out.println(Internal.class.getFields().length);
}
}
```

**Expected result:**
```text
1
0
```

**Concept:** Package-private fields are declared fields but not public fields.

## Question 9 — Keep helper instances independent

**Task:** Keep helper instances independent.

```java
public class Main {
static class Internal { int value; Internal(int value) { this.value=value; } int read() { return value; } void set(int value) { this.value=value; } }
public static void main(String[] args) throws Exception {
Internal a=new Internal(1),b=new Internal(2); a.set(9); System.out.println(b.read());
}
}
```

**Expected result:**
```text
2
```

**Concept:** Accessibility and instance ownership are separate concerns.

## Question 10 — Expose internal behavior through an enclosing program

**Task:** Expose internal behavior through an enclosing program.

```java
public class Main {
static class Internal { int value; Internal(int value) { this.value=value; } int read() { return value; } void set(int value) { this.value=value; } }
public static void main(String[] args) throws Exception {
Internal internal=new Internal(3); System.out.println("Value: "+internal.read());
}
}
```

**Expected result:**
```text
Value: 3
```

**Concept:** A public-facing boundary can delegate to non-public implementation details.

---

## Interview Questions and Answers

### Interview Question 1 — What does package-private mean?

**Answer:** Access is permitted within the declaring package when no access modifier is written for an applicable declaration.

### Interview Question 2 — Do subpackages count as the same package?

**Answer:** No. For example, app and app.internal are distinct packages despite their shared naming prefix.

### Interview Question 3 — Is package-private the same as a default interface method?

**Answer:** No. They describe different concepts: accessibility versus an interface method with an implementation.

### Interview Question 4 — Can a subclass in another package inherit package-private access rights?

**Answer:** Inheritance does not grant general access to an inaccessible package-private superclass member across package boundaries.

### Interview Question 5 — When is package-private useful?

**Answer:** For implementation types and collaborators that should remain available within one package without becoming public APIs.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Use a package-private helper in the same package | A type or member with no access keyword can be package-private. |
| Read a package-private field | Same-package code can access package-private members. |
| Write a package-private field | Package-level access still permits direct mutation within the boundary. |
| Call a package-private method | Package-private methods can support internal collaborations. |
| Inspect the field access mask | There is no package-private keyword or dedicated access bit. |

[← 069. protected](069_protected.md) | [Course Index](../README.md) | [071. Packages →](071_Packages.md)
