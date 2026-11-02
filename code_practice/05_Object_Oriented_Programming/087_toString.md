[← 086. Object class](086_Object_class.md) | [Course Index](../README.md) | [088. equals() →](088_equals.md)

# Java toString() Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/087_toString.md)

These exercises follow [087. toString()](../../Road%20Map/05_Object_Oriented_Programming/087_toString.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete Main.java with nested helper types. Run each question independently. Examples define their own ownership and null policies; Java references alone do not enforce UML lifecycle relationships.

## Question 1 — Print a custom representation

**Task:** Print a custom representation.

```java
public class Main {
static class User { final String name; final int age; User(String name,int age) { this.name=name; this.age=age; } @Override public String toString() { return "User{name="+name+", age="+age+"}"; } }
public static void main(String[] args) throws Exception {
System.out.println(new User("Ada",20));
}
}
```

**Expected result:**
```text
User{name=Ada, age=20}
```

**Concept:** println(Object) uses the object's string representation.

## Question 2 — Call toString explicitly

**Task:** Call toString explicitly.

```java
public class Main {
static class User { final String name; final int age; User(String name,int age) { this.name=name; this.age=age; } @Override public String toString() { return "User{name="+name+", age="+age+"}"; } }
public static void main(String[] args) throws Exception {
System.out.println(new User("Grace",25).toString());
}
}
```

**Expected result:**
```text
User{name=Grace, age=25}
```

**Concept:** Overriding toString gives a deliberate diagnostic format.

## Question 3 — Concatenate an object with text

**Task:** Concatenate an object with text.

```java
public class Main {
static class User { final String name; final int age; User(String name,int age) { this.name=name; this.age=age; } @Override public String toString() { return "User{name="+name+", age="+age+"}"; } }
public static void main(String[] args) throws Exception {
System.out.println("Value: "+new User("Ada",20));
}
}
```

**Expected result:**
```text
Value: User{name=Ada, age=20}
```

**Concept:** String concatenation uses object-to-string conversion.

## Question 4 — Print a collection of objects

**Task:** Print a collection of objects.

```java
public class Main {
static class User { final String name; final int age; User(String name,int age) { this.name=name; this.age=age; } @Override public String toString() { return "User{name="+name+", age="+age+"}"; } }
public static void main(String[] args) throws Exception {
System.out.println(java.util.Arrays.asList(new User("Ada",20),new User("Bob",30)));
}
}
```

**Expected result:**
```text
[User{name=Ada, age=20}, User{name=Bob, age=30}]
```

**Concept:** Collection diagnostics commonly use each element's representation.

## Question 5 — Print an object array readably

**Task:** Print an object array readably.

```java
public class Main {
static class User { final String name; final int age; User(String name,int age) { this.name=name; this.age=age; } @Override public String toString() { return "User{name="+name+", age="+age+"}"; } }
public static void main(String[] args) throws Exception {
System.out.println(java.util.Arrays.toString(new User[]{new User("Ada",20)}));
}
}
```

**Expected result:**
```text
[User{name=Ada, age=20}]
```

**Concept:** Arrays.toString delegates element representations while formatting the array.

## Question 6 — Represent null explicitly

**Task:** Represent null explicitly.

```java
public class Main {
static class User { final String name; final int age; User(String name,int age) { this.name=name; this.age=age; } @Override public String toString() { return "User{name="+name+", age="+age+"}"; } }
public static void main(String[] args) throws Exception {
User user=null; System.out.println(String.valueOf(user));
}
}
```

**Expected result:**
```text
null
```

**Concept:** String.valueOf(Object) handles null without invoking a method on it.

## Question 7 — Keep repeated representation stable for immutable state

**Task:** Keep repeated representation stable for immutable state.

```java
public class Main {
static class User { final String name; final int age; User(String name,int age) { this.name=name; this.age=age; } @Override public String toString() { return "User{name="+name+", age="+age+"}"; } }
public static void main(String[] args) throws Exception {
User user=new User("Ada",20); System.out.println(user.toString().equals(user.toString()));
}
}
```

**Expected result:**
```text
true
```

**Concept:** Deterministic output follows from this fixture's fixed fields and implementation.

## Question 8 — Show that a representation is not identity

**Task:** Show that a representation is not identity.

```java
public class Main {
static class User { final String name; final int age; User(String name,int age) { this.name=name; this.age=age; } @Override public String toString() { return "User{name="+name+", age="+age+"}"; } }
public static void main(String[] args) throws Exception {
User first=new User("Ada",20),second=new User("Ada",20); System.out.println(first.toString().equals(second.toString())); System.out.println(first==second);
}
}
```

**Expected result:**
```text
true
false
```

**Concept:** Matching printed text does not establish object identity.

## Question 9 — Use the inherited method through Object

**Task:** Use the inherited method through Object.

```java
public class Main {
static class User { final String name; final int age; User(String name,int age) { this.name=name; this.age=age; } @Override public String toString() { return "User{name="+name+", age="+age+"}"; } }
public static void main(String[] args) throws Exception {
Object user=new User("Ada",20); System.out.println(user.toString());
}
}
```

**Expected result:**
```text
User{name=Ada, age=20}
```

**Concept:** Instance dispatch still selects the User override.

## Question 10 — State an empty-name representation

**Task:** State an empty-name representation.

```java
public class Main {
static class User { final String name; final int age; User(String name,int age) { this.name=name; this.age=age; } @Override public String toString() { return "User{name="+name+", age="+age+"}"; } }
public static void main(String[] args) throws Exception {
System.out.println(new User("",0));
}
}
```

**Expected result:**
```text
User{name=, age=0}
```

**Concept:** Diagnostic formatting should define how unusual but permitted values appear.

---

## Interview Questions and Answers

### Interview Question 1 — What is toString for?

**Answer:** A readable representation useful for diagnostics and human inspection. Its contract should not be confused with a stable serialization format.

### Interview Question 2 — Why use @Override on toString?

**Answer:** It verifies the exact inherited method signature and catches accidental overloads such as toString(int).

### Interview Question 3 — Should toString expose secrets?

**Answer:** No. Keep passwords, credentials, tokens, and other sensitive values out of routine representations and logs.

### Interview Question 4 — Is toString suitable as a parser contract?

**Answer:** Usually not. Use an explicit structured format with defined escaping, versioning, and parsing behavior when machine interchange is required.

### Interview Question 5 — What can go wrong with recursive relationships?

**Answer:** Naively printing both sides of cyclic object links can recurse indefinitely or produce enormous output. Limit depth or print stable identifiers.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Print a custom representation | println(Object) uses the object's string representation. |
| Call toString explicitly | Overriding toString gives a deliberate diagnostic format. |
| Concatenate an object with text | String concatenation uses object-to-string conversion. |
| Print a collection of objects | Collection diagnostics commonly use each element's representation. |
| Print an object array readably | Arrays.toString delegates element representations while formatting the array. |

[← 086. Object class](086_Object_class.md) | [Course Index](../README.md) | [088. equals() →](088_equals.md)
