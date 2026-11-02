[← 065. Getters and setters](065_Getters_and_setters.md) | [Course Index](../README.md) | [067. public →](067_public.md)

# Java Access modifiers Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/066_Access_modifiers.md)

These exercises follow [066. Access modifiers](../../Road%20Map/05_Object_Oriented_Programming/066_Access_modifiers.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each Java solution is a complete Main.java and runs independently. Nested helper types keep the exercise in one file; they do not demonstrate every cross-package access rule. Those distinctions are stated in the interview answers. Reflection here inspects declarations without bypassing access checks.

## Question 1 — Inspect a public field declaration

**Task:** Inspect a public field declaration.

```java
public class Main {
static class Access { public int visible=1; protected int inherited=2; int local=3; private int hidden=4; public int read() { return hidden; } }
public static void main(String[] args) throws Exception {
int flags=Access.class.getDeclaredField("visible").getModifiers(); System.out.println(java.lang.reflect.Modifier.isPublic(flags));
}
}
```

**Expected result:**
```text
true
```

**Concept:** public declares broad source-level member accessibility, subject to its enclosing type and module boundary.

## Question 2 — Inspect a private field declaration

**Task:** Inspect a private field declaration.

```java
public class Main {
static class Access { public int visible=1; protected int inherited=2; int local=3; private int hidden=4; public int read() { return hidden; } }
public static void main(String[] args) throws Exception {
int flags=Access.class.getDeclaredField("hidden").getModifiers(); System.out.println(java.lang.reflect.Modifier.isPrivate(flags));
}
}
```

**Expected result:**
```text
true
```

**Concept:** private restricts source access to the permitted enclosing nest.

## Question 3 — Inspect a protected field declaration

**Task:** Inspect a protected field declaration.

```java
public class Main {
static class Access { public int visible=1; protected int inherited=2; int local=3; private int hidden=4; public int read() { return hidden; } }
public static void main(String[] args) throws Exception {
int flags=Access.class.getDeclaredField("inherited").getModifiers(); System.out.println(java.lang.reflect.Modifier.isProtected(flags));
}
}
```

**Expected result:**
```text
true
```

**Concept:** protected includes package access and qualified subclass access rules.

## Question 4 — Inspect a package-private declaration

**Task:** Inspect a package-private declaration.

```java
public class Main {
static class Access { public int visible=1; protected int inherited=2; int local=3; private int hidden=4; public int read() { return hidden; } }
public static void main(String[] args) throws Exception {
int flags=Access.class.getDeclaredField("local").getModifiers(); int access=java.lang.reflect.Modifier.PUBLIC|java.lang.reflect.Modifier.PROTECTED|java.lang.reflect.Modifier.PRIVATE; System.out.println((flags&access)==0);
}
}
```

**Expected result:**
```text
true
```

**Concept:** No access modifier means package-private rather than public.

## Question 5 — Read a public field

**Task:** Read a public field.

```java
public class Main {
static class Access { public int visible=1; protected int inherited=2; int local=3; private int hidden=4; public int read() { return hidden; } }
public static void main(String[] args) throws Exception {
System.out.println(new Access().visible);
}
}
```

**Expected result:**
```text
1
```

**Concept:** The enclosing type must also be accessible for an external public member access.

## Question 6 — Read private state through a method

**Task:** Read private state through a method.

```java
public class Main {
static class Access { public int visible=1; protected int inherited=2; int local=3; private int hidden=4; public int read() { return hidden; } }
public static void main(String[] args) throws Exception {
System.out.println(new Access().read());
}
}
```

**Expected result:**
```text
4
```

**Concept:** An accessor can expose information without exposing direct field assignment.

## Question 7 — List only public fields through getFields

**Task:** List only public fields through getFields.

```java
public class Main {
static class Access { public int visible=1; protected int inherited=2; int local=3; private int hidden=4; public int read() { return hidden; } }
public static void main(String[] args) throws Exception {
java.lang.reflect.Field[] fields=Access.class.getFields(); System.out.println(fields.length); System.out.println(fields[0].getName());
}
}
```

**Expected result:**
```text
1
visible
```

**Concept:** getFields reports public fields, unlike getDeclaredFields.

## Question 8 — Count all declared fields

**Task:** Count all declared fields.

```java
public class Main {
static class Access { public int visible=1; protected int inherited=2; int local=3; private int hidden=4; public int read() { return hidden; } }
public static void main(String[] args) throws Exception {
System.out.println(Access.class.getDeclaredFields().length);
}
}
```

**Expected result:**
```text
4
```

**Concept:** Reflection can describe non-public declarations without granting permission to read or modify them.

## Question 9 — Inspect a public method

**Task:** Inspect a public method.

```java
public class Main {
static class Access { public int visible=1; protected int inherited=2; int local=3; private int hidden=4; public int read() { return hidden; } }
public static void main(String[] args) throws Exception {
System.out.println(java.lang.reflect.Modifier.isPublic(Access.class.getDeclaredMethod("read").getModifiers()));
}
}
```

**Expected result:**
```text
true
```

**Concept:** Methods have their own access declarations.

## Question 10 — Read package-private state from the same enclosing source

**Task:** Read package-private state from the same enclosing source.

```java
public class Main {
static class Access { public int visible=1; protected int inherited=2; int local=3; private int hidden=4; public int read() { return hidden; } }
public static void main(String[] args) throws Exception {
Access value=new Access(); value.local=9; System.out.println(value.local);
}
}
```

**Expected result:**
```text
9
```

**Concept:** This same-package example permits access without a public declaration.

---

## Interview Questions and Answers

### Interview Question 1 — What are the four member access levels?

**Answer:** public, protected, package-private with no access keyword, and private. Enclosing-type accessibility and module boundaries also affect access.

### Interview Question 2 — Can an ordinary top-level class be private or protected?

**Answer:** No. Ordinary top-level classes use public or package-private access; nested type declarations have additional options.

### Interview Question 3 — Is protected equivalent to public for subclasses?

**Answer:** No. Outside the declaring package, subclass access has restrictions, including receiver-qualification rules for protected instance members.

### Interview Question 4 — Does getDeclaredFields bypass access control?

**Answer:** No. Discovering declaration metadata does not itself grant permission to access a field's value.

### Interview Question 5 — What is the least-privilege approach to access?

**Answer:** Expose the narrowest useful contract, normally hiding representation details and widening access only when callers require it.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Inspect a public field declaration | public declares broad source-level member accessibility, subject to its enclosing type and module boundary. |
| Inspect a private field declaration | private restricts source access to the permitted enclosing nest. |
| Inspect a protected field declaration | protected includes package access and qualified subclass access rules. |
| Inspect a package-private declaration | No access modifier means package-private rather than public. |
| Read a public field | The enclosing type must also be accessible for an external public member access. |

[← 065. Getters and setters](065_Getters_and_setters.md) | [Course Index](../README.md) | [067. public →](067_public.md)
