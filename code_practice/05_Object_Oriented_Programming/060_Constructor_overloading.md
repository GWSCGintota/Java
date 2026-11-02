[← 059. Constructors](059_Constructors.md) | [Course Index](../README.md) | [061. this →](061_this.md)

# Java Constructor overloading Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/060_Constructor_overloading.md)

These exercises follow [060. Constructor overloading](../../Road%20Map/05_Object_Oriented_Programming/060_Constructor_overloading.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete, independent Main.java. Helper types are nested to keep examples in one file. Compile and run each question separately. Inputs are non-null unless explicitly tested, and arithmetic uses small values.

## Question 1 — Call a no-argument constructor

**Task:** Call a no-argument constructor.

```java
public class Main {
static class User { final String name; final int age; User() { this("Guest",0); } User(String name) { this(name,0); } User(String name,int age) { this.name=name; this.age=age; } String label() { return name+":"+age; } }
public static void main(String[] args) {
System.out.println(new User().label());
}
}
```

**Expected result:**
```text
Guest:0
```

**Concept:** A no-argument overload can supply defaults.

## Question 2 — Call a one-argument constructor

**Task:** Call a one-argument constructor.

```java
public class Main {
static class User { final String name; final int age; User() { this("Guest",0); } User(String name) { this(name,0); } User(String name,int age) { this.name=name; this.age=age; } String label() { return name+":"+age; } }
public static void main(String[] args) {
System.out.println(new User("Ada").label());
}
}
```

**Expected result:**
```text
Ada:0
```

**Concept:** Overloads can progressively expose configuration.

## Question 3 — Call a two-argument constructor

**Task:** Call a two-argument constructor.

```java
public class Main {
static class User { final String name; final int age; User() { this("Guest",0); } User(String name) { this(name,0); } User(String name,int age) { this.name=name; this.age=age; } String label() { return name+":"+age; } }
public static void main(String[] args) {
System.out.println(new User("Ada",20).label());
}
}
```

**Expected result:**
```text
Ada:20
```

**Concept:** Parameter signatures distinguish constructor overloads.

## Question 4 — Verify shared initialization

**Task:** Verify shared initialization.

```java
public class Main {
static class User { final String name; final int age; User() { this("Guest",0); } User(String name) { this(name,0); } User(String name,int age) { this.name=name; this.age=age; } String label() { return name+":"+age; } }
public static void main(String[] args) {
User user=new User("Grace"); System.out.println(user.age);
}
}
```

**Expected result:**
```text
0
```

**Concept:** Constructor chaining centralizes state assignment.

## Question 5 — Use explicit zero age

**Task:** Use explicit zero age.

```java
public class Main {
static class User { final String name; final int age; User() { this("Guest",0); } User(String name) { this(name,0); } User(String name,int age) { this.name=name; this.age=age; } String label() { return name+":"+age; } }
public static void main(String[] args) {
System.out.println(new User("Ada",0).label());
}
}
```

**Expected result:**
```text
Ada:0
```

**Concept:** An explicit value can equal a default without changing the selected overload.

## Question 6 — Permit empty names under the current contract

**Task:** Permit empty names under the current contract.

```java
public class Main {
static class User { final String name; final int age; User() { this("Guest",0); } User(String name) { this(name,0); } User(String name,int age) { this.name=name; this.age=age; } String label() { return name+":"+age; } }
public static void main(String[] args) {
System.out.println("["+new User("").name+"]");
}
}
```

**Expected result:**
```text
[]
```

**Concept:** Overloading alone does not validate arguments.

## Question 7 — Inspect a supplied null under the current contract

**Task:** Inspect a supplied null under the current contract.

```java
public class Main {
static class User { final String name; final int age; User() { this("Guest",0); } User(String name) { this(name,0); } User(String name,int age) { this.name=name; this.age=age; } String label() { return name+":"+age; } }
public static void main(String[] args) {
System.out.println(new User((String)null).name);
}
}
```

**Expected result:**
```text
null
```

**Concept:** This fixture deliberately stores nullable names; choose validation if the domain forbids them.

## Question 8 — Compare two default objects

**Task:** Compare two default objects.

```java
public class Main {
static class User { final String name; final int age; User() { this("Guest",0); } User(String name) { this(name,0); } User(String name,int age) { this.name=name; this.age=age; } String label() { return name+":"+age; } }
public static void main(String[] args) {
System.out.println(new User()==new User());
}
}
```

**Expected result:**
```text
false
```

**Concept:** Delegation does not turn constructors into shared-instance factories.

## Question 9 — Create mixed overloads in an array

**Task:** Create mixed overloads in an array.

```java
public class Main {
static class User { final String name; final int age; User() { this("Guest",0); } User(String name) { this(name,0); } User(String name,int age) { this.name=name; this.age=age; } String label() { return name+":"+age; } }
public static void main(String[] args) {
User[] users={new User(),new User("Ada"),new User("Bob",30)}; for(User user:users) { System.out.println(user.label()); }
}
}
```

**Expected result:**
```text
Guest:0
Ada:0
Bob:30
```

**Concept:** Call sites can choose the overload that communicates their known inputs.

## Question 10 — Compare overloaded results

**Task:** Compare overloaded results.

```java
public class Main {
static class User { final String name; final int age; User() { this("Guest",0); } User(String name) { this(name,0); } User(String name,int age) { this.name=name; this.age=age; } String label() { return name+":"+age; } }
public static void main(String[] args) {
System.out.println(new User("Ada").label().equals(new User("Ada",0).label()));
}
}
```

**Expected result:**
```text
true
```

**Concept:** Distinct overloads can intentionally produce equivalent state.

---

## Interview Questions and Answers

### Interview Question 1 — What is constructor overloading?

**Answer:** Multiple constructors with distinct parameter signatures in one class.

### Interview Question 2 — Why use this(...) constructor delegation?

**Answer:** It centralizes initialization and validation rather than duplicating them across overloads.

### Interview Question 3 — Can constructor delegation be cyclic?

**Answer:** No. Direct or indirect recursive constructor invocation is a compile-time error.

### Interview Question 4 — Can constructors differ only by parameter names?

**Answer:** No. Parameter names do not distinguish signatures; parameter types and count must distinguish overloads.

### Interview Question 5 — When might factories or builders be clearer?

**Answer:** When many same-type parameters or combinations of optional values make constructor calls ambiguous or hard to read.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Call a no-argument constructor | A no-argument overload can supply defaults. |
| Call a one-argument constructor | Overloads can progressively expose configuration. |
| Call a two-argument constructor | Parameter signatures distinguish constructor overloads. |
| Verify shared initialization | Constructor chaining centralizes state assignment. |
| Use explicit zero age | An explicit value can equal a default without changing the selected overload. |

[← 059. Constructors](059_Constructors.md) | [Course Index](../README.md) | [061. this →](061_this.md)
