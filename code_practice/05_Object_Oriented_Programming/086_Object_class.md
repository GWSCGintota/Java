[← 085. Association](085_Association.md) | [Course Index](../README.md) | [087. toString() →](087_toString.md)

# Java Object class Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/086_Object_class.md)

These exercises follow [086. Object class](../../Road%20Map/05_Object_Oriented_Programming/086_Object_class.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete Main.java with nested helper types. Run each question independently. Examples define their own ownership and null policies; Java references alone do not enforce UML lifecycle relationships.

## Question 1 — Use Object as a supertype

**Task:** Use Object as a supertype.

```java
public class Main {
static class Value { final int number; Value(int number) { this.number=number; } }
public static void main(String[] args) throws Exception {
Object value=new Value(7); System.out.println(value instanceof Value);
}
}
```

**Expected result:**
```text
true
```

**Concept:** Ordinary class instances can be viewed through Object.

## Question 2 — Inspect the runtime class

**Task:** Inspect the runtime class.

```java
public class Main {
static class Value { final int number; Value(int number) { this.number=number; } }
public static void main(String[] args) throws Exception {
System.out.println(new Value(7).getClass().getSimpleName());
}
}
```

**Expected result:**
```text
Value
```

**Concept:** getClass returns the runtime class metadata.

## Question 3 — Check identity equality

**Task:** Check identity equality.

```java
public class Main {
static class Value { final int number; Value(int number) { this.number=number; } }
public static void main(String[] args) throws Exception {
Value value=new Value(7); System.out.println(value.equals(value));
}
}
```

**Expected result:**
```text
true
```

**Concept:** Object.equals uses identity for this class.

## Question 4 — Compare separate objects with the same fields

**Task:** Compare separate objects with the same fields.

```java
public class Main {
static class Value { final int number; Value(int number) { this.number=number; } }
public static void main(String[] args) throws Exception {
System.out.println(new Value(7).equals(new Value(7)));
}
}
```

**Expected result:**
```text
false
```

**Concept:** Inheriting Object.equals does not provide value equality.

## Question 5 — Compare against null

**Task:** Compare against null.

```java
public class Main {
static class Value { final int number; Value(int number) { this.number=number; } }
public static void main(String[] args) throws Exception {
System.out.println(new Value(7).equals(null));
}
}
```

**Expected result:**
```text
false
```

**Concept:** A non-null object's equality comparison with null must be false under the equals contract.

## Question 6 — Check repeated identity hashes

**Task:** Check repeated identity hashes.

```java
public class Main {
static class Value { final int number; Value(int number) { this.number=number; } }
public static void main(String[] args) throws Exception {
Value value=new Value(7); System.out.println(value.hashCode()==value.hashCode());
}
}
```

**Expected result:**
```text
true
```

**Concept:** Default hash consistency applies while the relevant execution and object conditions hold.

## Question 7 — Inspect the direct root relationship

**Task:** Inspect the direct root relationship.

```java
public class Main {
static class Value { final int number; Value(int number) { this.number=number; } }
public static void main(String[] args) throws Exception {
System.out.println(Value.class.getSuperclass()==Object.class);
}
}
```

**Expected result:**
```text
true
```

**Concept:** An ordinary class without an explicit superclass extends Object.

## Question 8 — Use a null-safe equality helper

**Task:** Use a null-safe equality helper.

```java
public class Main {
static class Value { final int number; Value(int number) { this.number=number; } }
public static void main(String[] args) throws Exception {
System.out.println(java.util.Objects.equals(null,null));
}
}
```

**Expected result:**
```text
true
```

**Concept:** Objects.equals handles null before delegating to an object's equality implementation.

## Question 9 — Inspect the default string representation prefix

**Task:** Inspect the default string representation prefix.

```java
public class Main {
static class Value { final int number; Value(int number) { this.number=number; } }
public static void main(String[] args) throws Exception {
Value value=new Value(7); System.out.println(value.toString().startsWith(value.getClass().getName()+"@"));
}
}
```

**Expected result:**
```text
true
```

**Concept:** Object.toString has a class-name and hexadecimal-hash representation, not a portable memory-address display.

## Question 10 — Use an array through Object

**Task:** Use an array through Object.

```java
public class Main {
static class Value { final int number; Value(int number) { this.number=number; } }
public static void main(String[] args) throws Exception {
Object value=new int[]{1,2}; System.out.println(value.getClass().isArray());
}
}
```

**Expected result:**
```text
true
```

**Concept:** Arrays are objects with runtime class metadata.

---

## Interview Questions and Answers

### Interview Question 1 — What does Object provide to ordinary classes?

**Answer:** Core methods such as equals, hashCode, toString, getClass, and monitor-related operations, subject to their individual contracts.

### Interview Question 2 — Does Object.equals compare all fields?

**Answer:** No. Its default implementation compares identity. Value semantics require an appropriate override.

### Interview Question 3 — Is hashCode a memory address?

**Answer:** The contract does not promise that. It is an integer hash with equality and consistency requirements.

### Interview Question 4 — Does every object support public clone?

**Answer:** No. Object.clone is protected and cloning has additional requirements; an arbitrary object does not expose a usable public copy operation.

### Interview Question 5 — When can wait and notify be called?

**Answer:** Their monitor operations require ownership of the corresponding object's monitor and careful condition-loop design; they are not general-purpose unsynchronized signaling methods.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Use Object as a supertype | Ordinary class instances can be viewed through Object. |
| Inspect the runtime class | getClass returns the runtime class metadata. |
| Check identity equality | Object.equals uses identity for this class. |
| Compare separate objects with the same fields | Inheriting Object.equals does not provide value equality. |
| Compare against null | A non-null object's equality comparison with null must be false under the equals contract. |

[← 085. Association](085_Association.md) | [Course Index](../README.md) | [087. toString() →](087_toString.md)
