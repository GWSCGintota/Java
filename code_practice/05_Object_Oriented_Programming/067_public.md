[← 066. Access modifiers](066_Access_modifiers.md) | [Course Index](../README.md) | [068. private →](068_private.md)

# Java public Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/067_public.md)

These exercises follow [067. public](../../Road%20Map/05_Object_Oriented_Programming/067_public.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each Java solution is a complete Main.java and runs independently. Nested helper types keep the exercise in one file; they do not demonstrate every cross-package access rule. Those distinctions are stated in the interview answers. Reflection here inspects declarations without bypassing access checks.

## Question 1 — Construct through a public no-argument constructor

**Task:** Construct through a public no-argument constructor.

```java
public class Main {
public static class Api { public static final int LIMIT=3; public int value; public Api() { this(0); } public Api(int value) { this.value=value; } public int get() { return value; } public void add(int amount) { value+=amount; } public static int twice(int value) { return value*2; } }
public static void main(String[] args) throws Exception {
System.out.println(new Api().get());
}
}
```

**Expected result:**
```text
0
```

**Concept:** A public constructor can be called wherever the enclosing type is accessible.

## Question 2 — Use a public argument constructor

**Task:** Use a public argument constructor.

```java
public class Main {
public static class Api { public static final int LIMIT=3; public int value; public Api() { this(0); } public Api(int value) { this.value=value; } public int get() { return value; } public void add(int amount) { value+=amount; } public static int twice(int value) { return value*2; } }
public static void main(String[] args) throws Exception {
System.out.println(new Api(7).get());
}
}
```

**Expected result:**
```text
7
```

**Concept:** Constructor access can be part of the API boundary.

## Question 3 — Read a public field

**Task:** Read a public field.

```java
public class Main {
public static class Api { public static final int LIMIT=3; public int value; public Api() { this(0); } public Api(int value) { this.value=value; } public int get() { return value; } public void add(int amount) { value+=amount; } public static int twice(int value) { return value*2; } }
public static void main(String[] args) throws Exception {
System.out.println(new Api(4).value);
}
}
```

**Expected result:**
```text
4
```

**Concept:** Public fields expose direct representation access.

## Question 4 — Write a public field

**Task:** Write a public field.

```java
public class Main {
public static class Api { public static final int LIMIT=3; public int value; public Api() { this(0); } public Api(int value) { this.value=value; } public int get() { return value; } public void add(int amount) { value+=amount; } public static int twice(int value) { return value*2; } }
public static void main(String[] args) throws Exception {
Api api=new Api(); api.value=9; System.out.println(api.get());
}
}
```

**Expected result:**
```text
9
```

**Concept:** Public mutable fields let callers bypass setter validation.

## Question 5 — Invoke a public method

**Task:** Invoke a public method.

```java
public class Main {
public static class Api { public static final int LIMIT=3; public int value; public Api() { this(0); } public Api(int value) { this.value=value; } public int get() { return value; } public void add(int amount) { value+=amount; } public static int twice(int value) { return value*2; } }
public static void main(String[] args) throws Exception {
Api api=new Api(3); api.add(2); System.out.println(api.get());
}
}
```

**Expected result:**
```text
5
```

**Concept:** Public methods can expose focused behavior.

## Question 6 — Read a public constant

**Task:** Read a public constant.

```java
public class Main {
public static class Api { public static final int LIMIT=3; public int value; public Api() { this(0); } public Api(int value) { this.value=value; } public int get() { return value; } public void add(int amount) { value+=amount; } public static int twice(int value) { return value*2; } }
public static void main(String[] args) throws Exception {
System.out.println(Api.LIMIT);
}
}
```

**Expected result:**
```text
3
```

**Concept:** A public static final value can be shared configuration.

## Question 7 — Call a public static utility

**Task:** Call a public static utility.

```java
public class Main {
public static class Api { public static final int LIMIT=3; public int value; public Api() { this(0); } public Api(int value) { this.value=value; } public int get() { return value; } public void add(int amount) { value+=amount; } public static int twice(int value) { return value*2; } }
public static void main(String[] args) throws Exception {
System.out.println(Api.twice(6));
}
}
```

**Expected result:**
```text
12
```

**Concept:** Static API calls are class-qualified.

## Question 8 — Inspect the public nested type

**Task:** Inspect the public nested type.

```java
public class Main {
public static class Api { public static final int LIMIT=3; public int value; public Api() { this(0); } public Api(int value) { this.value=value; } public int get() { return value; } public void add(int amount) { value+=amount; } public static int twice(int value) { return value*2; } }
public static void main(String[] args) throws Exception {
System.out.println(java.lang.reflect.Modifier.isPublic(Api.class.getModifiers()));
}
}
```

**Expected result:**
```text
true
```

**Concept:** A nested type can itself be public.

## Question 9 — Inspect a public constructor

**Task:** Inspect a public constructor.

```java
public class Main {
public static class Api { public static final int LIMIT=3; public int value; public Api() { this(0); } public Api(int value) { this.value=value; } public int get() { return value; } public void add(int amount) { value+=amount; } public static int twice(int value) { return value*2; } }
public static void main(String[] args) throws Exception {
System.out.println(java.lang.reflect.Modifier.isPublic(Api.class.getConstructor(int.class).getModifiers()));
}
}
```

**Expected result:**
```text
true
```

**Concept:** Public-constructor discovery uses getConstructor.

## Question 10 — Use a public method reference

**Task:** Use a public method reference.

```java
public class Main {
public static class Api { public static final int LIMIT=3; public int value; public Api() { this(0); } public Api(int value) { this.value=value; } public int get() { return value; } public void add(int amount) { value+=amount; } public static int twice(int value) { return value*2; } }
public static void main(String[] args) throws Exception {
java.util.function.IntUnaryOperator operator=Api::twice; System.out.println(operator.applyAsInt(5));
}
}
```

**Expected result:**
```text
10
```

**Concept:** Public methods can implement compatible functional behavior.

---

## Interview Questions and Answers

### Interview Question 1 — Does public always mean accessible from everywhere?

**Answer:** Access also requires an accessible declaring type and applicable module readability and export rules. A public member inside an inaccessible type is not a universal API.

### Interview Question 2 — Why avoid public mutable fields?

**Answer:** They expose representation and allow callers to bypass invariants, making later changes harder.

### Interview Question 3 — Can public methods be overridden?

**Answer:** Accessible non-final instance methods can participate in overriding; static methods are hidden and private methods are not overridden.

### Interview Question 4 — What is part of a public API contract?

**Answer:** Signatures, null and failure policies, visible effects, ownership rules, and documented behavior, not merely the public keyword.

### Interview Question 5 — Must all helper classes be public?

**Answer:** No. Keep implementation details non-public unless consumers need to reference them.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Construct through a public no-argument constructor | A public constructor can be called wherever the enclosing type is accessible. |
| Use a public argument constructor | Constructor access can be part of the API boundary. |
| Read a public field | Public fields expose direct representation access. |
| Write a public field | Public mutable fields let callers bypass setter validation. |
| Invoke a public method | Public methods can expose focused behavior. |

[← 066. Access modifiers](066_Access_modifiers.md) | [Course Index](../README.md) | [068. private →](068_private.md)
