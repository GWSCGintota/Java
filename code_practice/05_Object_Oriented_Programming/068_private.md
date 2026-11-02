[← 067. public](067_public.md) | [Course Index](../README.md) | [069. protected →](069_protected.md)

# Java private Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/068_private.md)

These exercises follow [068. private](../../Road%20Map/05_Object_Oriented_Programming/068_private.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each Java solution is a complete Main.java and runs independently. Nested helper types keep the exercise in one file; they do not demonstrate every cross-package access rule. Those distinctions are stated in the interview answers. Reflection here inspects declarations without bypassing access checks.

## Question 1 — Construct through a controlled factory

**Task:** Construct through a controlled factory.

```java
public class Main {
static class Vault { private int value; private Vault(int value) { this.value=value; } static Vault create(int value) { return new Vault(value); } private int doubled() { return value*2; } int read() { return value; } int readDouble() { return doubled(); } void set(int value) { if(value<0) { throw new IllegalArgumentException("Negative"); } this.value=value; } }
public static void main(String[] args) throws Exception {
System.out.println(Vault.create(7).read());
}
}
```

**Expected result:**
```text
7
```

**Concept:** A private constructor can be invoked by an allowed factory inside its class.

## Question 2 — Read hidden state through an accessor

**Task:** Read hidden state through an accessor.

```java
public class Main {
static class Vault { private int value; private Vault(int value) { this.value=value; } static Vault create(int value) { return new Vault(value); } private int doubled() { return value*2; } int read() { return value; } int readDouble() { return doubled(); } void set(int value) { if(value<0) { throw new IllegalArgumentException("Negative"); } this.value=value; } }
public static void main(String[] args) throws Exception {
System.out.println(Vault.create(4).read());
}
}
```

**Expected result:**
```text
4
```

**Concept:** Private state can be exposed selectively through methods.

## Question 3 — Invoke a private helper indirectly

**Task:** Invoke a private helper indirectly.

```java
public class Main {
static class Vault { private int value; private Vault(int value) { this.value=value; } static Vault create(int value) { return new Vault(value); } private int doubled() { return value*2; } int read() { return value; } int readDouble() { return doubled(); } void set(int value) { if(value<0) { throw new IllegalArgumentException("Negative"); } this.value=value; } }
public static void main(String[] args) throws Exception {
System.out.println(Vault.create(4).readDouble());
}
}
```

**Expected result:**
```text
8
```

**Concept:** Private helpers implement internal behavior.

## Question 4 — Apply a validated state change

**Task:** Apply a validated state change.

```java
public class Main {
static class Vault { private int value; private Vault(int value) { this.value=value; } static Vault create(int value) { return new Vault(value); } private int doubled() { return value*2; } int read() { return value; } int readDouble() { return doubled(); } void set(int value) { if(value<0) { throw new IllegalArgumentException("Negative"); } this.value=value; } }
public static void main(String[] args) throws Exception {
Vault vault=Vault.create(1); vault.set(5); System.out.println(vault.read());
}
}
```

**Expected result:**
```text
5
```

**Concept:** Methods control the mutation boundary.

## Question 5 — Reject an invalid change

**Task:** Reject an invalid change.

```java
public class Main {
static class Vault { private int value; private Vault(int value) { this.value=value; } static Vault create(int value) { return new Vault(value); } private int doubled() { return value*2; } int read() { return value; } int readDouble() { return doubled(); } void set(int value) { if(value<0) { throw new IllegalArgumentException("Negative"); } this.value=value; } }
public static void main(String[] args) throws Exception {
Vault vault=Vault.create(1); try { vault.set(-1); } catch(IllegalArgumentException e) { System.out.println(e.getMessage()); }
}
}
```

**Expected result:**
```text
Negative
```

**Concept:** A private field plus validation can protect an invariant.

## Question 6 — Preserve state after rejection

**Task:** Preserve state after rejection.

```java
public class Main {
static class Vault { private int value; private Vault(int value) { this.value=value; } static Vault create(int value) { return new Vault(value); } private int doubled() { return value*2; } int read() { return value; } int readDouble() { return doubled(); } void set(int value) { if(value<0) { throw new IllegalArgumentException("Negative"); } this.value=value; } }
public static void main(String[] args) throws Exception {
Vault vault=Vault.create(3); try { vault.set(-1); } catch(IllegalArgumentException ignored) { } System.out.println(vault.read());
}
}
```

**Expected result:**
```text
3
```

**Concept:** Check before assignment when failures should leave state unchanged.

## Question 7 — Inspect the field access level

**Task:** Inspect the field access level.

```java
public class Main {
static class Vault { private int value; private Vault(int value) { this.value=value; } static Vault create(int value) { return new Vault(value); } private int doubled() { return value*2; } int read() { return value; } int readDouble() { return doubled(); } void set(int value) { if(value<0) { throw new IllegalArgumentException("Negative"); } this.value=value; } }
public static void main(String[] args) throws Exception {
System.out.println(java.lang.reflect.Modifier.isPrivate(Vault.class.getDeclaredField("value").getModifiers()));
}
}
```

**Expected result:**
```text
true
```

**Concept:** Declaration discovery is distinct from value access.

## Question 8 — Inspect the helper access level

**Task:** Inspect the helper access level.

```java
public class Main {
static class Vault { private int value; private Vault(int value) { this.value=value; } static Vault create(int value) { return new Vault(value); } private int doubled() { return value*2; } int read() { return value; } int readDouble() { return doubled(); } void set(int value) { if(value<0) { throw new IllegalArgumentException("Negative"); } this.value=value; } }
public static void main(String[] args) throws Exception {
System.out.println(java.lang.reflect.Modifier.isPrivate(Vault.class.getDeclaredMethod("doubled").getModifiers()));
}
}
```

**Expected result:**
```text
true
```

**Concept:** Private methods stay outside the ordinary external method contract.

## Question 9 — Inspect the constructor access level

**Task:** Inspect the constructor access level.

```java
public class Main {
static class Vault { private int value; private Vault(int value) { this.value=value; } static Vault create(int value) { return new Vault(value); } private int doubled() { return value*2; } int read() { return value; } int readDouble() { return doubled(); } void set(int value) { if(value<0) { throw new IllegalArgumentException("Negative"); } this.value=value; } }
public static void main(String[] args) throws Exception {
System.out.println(java.lang.reflect.Modifier.isPrivate(Vault.class.getDeclaredConstructor(int.class).getModifiers()));
}
}
```

**Expected result:**
```text
true
```

**Concept:** Constructor visibility can restrict construction paths.

## Question 10 — Keep independent hidden state

**Task:** Keep independent hidden state.

```java
public class Main {
static class Vault { private int value; private Vault(int value) { this.value=value; } static Vault create(int value) { return new Vault(value); } private int doubled() { return value*2; } int read() { return value; } int readDouble() { return doubled(); } void set(int value) { if(value<0) { throw new IllegalArgumentException("Negative"); } this.value=value; } }
public static void main(String[] args) throws Exception {
Vault a=Vault.create(1),b=Vault.create(2); a.set(9); System.out.println(b.read());
}
}
```

**Expected result:**
```text
2
```

**Concept:** Private instance state still belongs to each separate receiver.

---

## Interview Questions and Answers

### Interview Question 1 — What does private restrict?

**Answer:** Source access is restricted to the enclosing top-level nest permitted by Java rules, rather than all classes in the package.

### Interview Question 2 — Are private methods overridden by subclasses?

**Answer:** No. A same-named subclass method is a separate declaration, not an override of the private method.

### Interview Question 3 — Can a class use a private constructor?

**Answer:** Yes. Methods or allowed nested code can construct instances, enabling controlled factories and similar designs.

### Interview Question 4 — Does private make sensitive data cryptographically secure?

**Answer:** No. It is an access-control mechanism in the language, not encryption or a complete trust boundary against all runtime capabilities.

### Interview Question 5 — Why use private helper methods?

**Answer:** To name internal operations and keep implementation details out of the caller-facing API.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Construct through a controlled factory | A private constructor can be invoked by an allowed factory inside its class. |
| Read hidden state through an accessor | Private state can be exposed selectively through methods. |
| Invoke a private helper indirectly | Private helpers implement internal behavior. |
| Apply a validated state change | Methods control the mutation boundary. |
| Reject an invalid change | A private field plus validation can protect an invariant. |

[← 067. public](067_public.md) | [Course Index](../README.md) | [069. protected →](069_protected.md)
