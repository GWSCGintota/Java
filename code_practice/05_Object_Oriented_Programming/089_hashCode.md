[← 088. equals()](088_equals.md) | [Course Index](../README.md) | [090. instanceof →](090_instanceof.md)

# Java hashCode() Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/089_hashCode.md)

These exercises follow [089. hashCode()](../../Road%20Map/05_Object_Oriented_Programming/089_hashCode.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete Main.java with nested helper types. Run each question independently. Examples define their own ownership and null policies; Java references alone do not enforce UML lifecycle relationships.

## Question 1 — Compute a deterministic value hash

**Task:** Compute a deterministic value hash.

```java
public class Main {
static final class Key { final int value; Key(int value) { this.value=value; } @Override public boolean equals(Object other) { return other instanceof Key && ((Key)other).value==value; } @Override public int hashCode() { return Integer.hashCode(value); } } static final class CollidingKey { final int value; CollidingKey(int value) { this.value=value; } @Override public boolean equals(Object other) { return other instanceof CollidingKey && ((CollidingKey)other).value==value; } @Override public int hashCode() { return 1; } }
public static void main(String[] args) throws Exception {
System.out.println(new Key(7).hashCode());
}
}
```

**Expected result:**
```text
7
```

**Concept:** A hash function derives an integer from equality-relevant state.

## Question 2 — Check equal-key hash compatibility

**Task:** Check equal-key hash compatibility.

```java
public class Main {
static final class Key { final int value; Key(int value) { this.value=value; } @Override public boolean equals(Object other) { return other instanceof Key && ((Key)other).value==value; } @Override public int hashCode() { return Integer.hashCode(value); } } static final class CollidingKey { final int value; CollidingKey(int value) { this.value=value; } @Override public boolean equals(Object other) { return other instanceof CollidingKey && ((CollidingKey)other).value==value; } @Override public int hashCode() { return 1; } }
public static void main(String[] args) throws Exception {
System.out.println(new Key(7).hashCode()==new Key(7).hashCode());
}
}
```

**Expected result:**
```text
true
```

**Concept:** Equal values must yield equal hashes.

## Question 3 — Check repeated hashing

**Task:** Check repeated hashing.

```java
public class Main {
static final class Key { final int value; Key(int value) { this.value=value; } @Override public boolean equals(Object other) { return other instanceof Key && ((Key)other).value==value; } @Override public int hashCode() { return Integer.hashCode(value); } } static final class CollidingKey { final int value; CollidingKey(int value) { this.value=value; } @Override public boolean equals(Object other) { return other instanceof CollidingKey && ((CollidingKey)other).value==value; } @Override public int hashCode() { return 1; } }
public static void main(String[] args) throws Exception {
Key key=new Key(7); System.out.println(key.hashCode()==key.hashCode());
}
}
```

**Expected result:**
```text
true
```

**Concept:** Unchanged equality-relevant state supports consistent hashing.

## Question 4 — Retrieve through an equal key

**Task:** Retrieve through an equal key.

```java
public class Main {
static final class Key { final int value; Key(int value) { this.value=value; } @Override public boolean equals(Object other) { return other instanceof Key && ((Key)other).value==value; } @Override public int hashCode() { return Integer.hashCode(value); } } static final class CollidingKey { final int value; CollidingKey(int value) { this.value=value; } @Override public boolean equals(Object other) { return other instanceof CollidingKey && ((CollidingKey)other).value==value; } @Override public int hashCode() { return 1; } }
public static void main(String[] args) throws Exception {
java.util.Map<Key,String> values=new java.util.HashMap<>(); values.put(new Key(7),"Ada"); System.out.println(values.get(new Key(7)));
}
}
```

**Expected result:**
```text
Ada
```

**Concept:** Hashing and equality jointly support map lookup.

## Question 5 — Replace a value under an equal key

**Task:** Replace a value under an equal key.

```java
public class Main {
static final class Key { final int value; Key(int value) { this.value=value; } @Override public boolean equals(Object other) { return other instanceof Key && ((Key)other).value==value; } @Override public int hashCode() { return Integer.hashCode(value); } } static final class CollidingKey { final int value; CollidingKey(int value) { this.value=value; } @Override public boolean equals(Object other) { return other instanceof CollidingKey && ((CollidingKey)other).value==value; } @Override public int hashCode() { return 1; } }
public static void main(String[] args) throws Exception {
java.util.Map<Key,String> values=new java.util.HashMap<>(); values.put(new Key(7),"A"); values.put(new Key(7),"B"); System.out.println(values.size()); System.out.println(values.get(new Key(7)));
}
}
```

**Expected result:**
```text
1
B
```

**Concept:** A logically equal key updates an existing map entry.

## Question 6 — Demonstrate a legal collision

**Task:** Demonstrate a legal collision.

```java
public class Main {
static final class Key { final int value; Key(int value) { this.value=value; } @Override public boolean equals(Object other) { return other instanceof Key && ((Key)other).value==value; } @Override public int hashCode() { return Integer.hashCode(value); } } static final class CollidingKey { final int value; CollidingKey(int value) { this.value=value; } @Override public boolean equals(Object other) { return other instanceof CollidingKey && ((CollidingKey)other).value==value; } @Override public int hashCode() { return 1; } }
public static void main(String[] args) throws Exception {
System.out.println(new CollidingKey(1).hashCode()==new CollidingKey(2).hashCode()); System.out.println(new CollidingKey(1).equals(new CollidingKey(2)));
}
}
```

**Expected result:**
```text
true
false
```

**Concept:** Equal hashes are permitted for unequal values.

## Question 7 — Retain unequal colliding set entries

**Task:** Retain unequal colliding set entries.

```java
public class Main {
static final class Key { final int value; Key(int value) { this.value=value; } @Override public boolean equals(Object other) { return other instanceof Key && ((Key)other).value==value; } @Override public int hashCode() { return Integer.hashCode(value); } } static final class CollidingKey { final int value; CollidingKey(int value) { this.value=value; } @Override public boolean equals(Object other) { return other instanceof CollidingKey && ((CollidingKey)other).value==value; } @Override public int hashCode() { return 1; } }
public static void main(String[] args) throws Exception {
java.util.Set<CollidingKey> values=new java.util.HashSet<>(); values.add(new CollidingKey(1)); values.add(new CollidingKey(2)); System.out.println(values.size());
}
}
```

**Expected result:**
```text
2
```

**Concept:** A collision does not make unequal elements duplicates.

## Question 8 — Retrieve a colliding map entry correctly

**Task:** Retrieve a colliding map entry correctly.

```java
public class Main {
static final class Key { final int value; Key(int value) { this.value=value; } @Override public boolean equals(Object other) { return other instanceof Key && ((Key)other).value==value; } @Override public int hashCode() { return Integer.hashCode(value); } } static final class CollidingKey { final int value; CollidingKey(int value) { this.value=value; } @Override public boolean equals(Object other) { return other instanceof CollidingKey && ((CollidingKey)other).value==value; } @Override public int hashCode() { return 1; } }
public static void main(String[] args) throws Exception {
java.util.Map<CollidingKey,String> values=new java.util.HashMap<>(); values.put(new CollidingKey(1),"A"); values.put(new CollidingKey(2),"B"); System.out.println(values.get(new CollidingKey(2)));
}
}
```

**Expected result:**
```text
B
```

**Concept:** Equality distinguishes keys sharing a hash.

## Question 9 — Remove through an equal key

**Task:** Remove through an equal key.

```java
public class Main {
static final class Key { final int value; Key(int value) { this.value=value; } @Override public boolean equals(Object other) { return other instanceof Key && ((Key)other).value==value; } @Override public int hashCode() { return Integer.hashCode(value); } } static final class CollidingKey { final int value; CollidingKey(int value) { this.value=value; } @Override public boolean equals(Object other) { return other instanceof CollidingKey && ((CollidingKey)other).value==value; } @Override public int hashCode() { return 1; } }
public static void main(String[] args) throws Exception {
java.util.Map<Key,String> values=new java.util.HashMap<>(); values.put(new Key(7),"A"); System.out.println(values.remove(new Key(7))); System.out.println(values.size());
}
}
```

**Expected result:**
```text
A
0
```

**Concept:** Removal also depends on the equality and hash contract.

## Question 10 — Keep hash state immutable

**Task:** Keep hash state immutable.

```java
public class Main {
static final class Key { final int value; Key(int value) { this.value=value; } @Override public boolean equals(Object other) { return other instanceof Key && ((Key)other).value==value; } @Override public int hashCode() { return Integer.hashCode(value); } } static final class CollidingKey { final int value; CollidingKey(int value) { this.value=value; } @Override public boolean equals(Object other) { return other instanceof CollidingKey && ((CollidingKey)other).value==value; } @Override public int hashCode() { return 1; } }
public static void main(String[] args) throws Exception {
System.out.println(java.lang.reflect.Modifier.isFinal(Key.class.getDeclaredField("value").getModifiers()));
}
}
```

**Expected result:**
```text
true
```

**Concept:** Immutable equality-relevant state avoids accidental stored-key mutation.

---

## Interview Questions and Answers

### Interview Question 1 — What must hashCode guarantee for equal objects?

**Answer:** Equal objects must have equal hash codes. Unequal objects are allowed to share a hash.

### Interview Question 2 — Is a constant hashCode legal?

**Answer:** It can satisfy the equality contract, but poor distribution can significantly degrade hash-based collection performance.

### Interview Question 3 — Must hash codes stay the same across program executions?

**Answer:** The general Object contract does not require cross-execution stability. Specific value-class implementations may define deterministic values.

### Interview Question 4 — Why are mutable map keys risky?

**Answer:** Changing equality-relevant state after insertion can change the hash or equality behavior used for lookup, making entries difficult to retrieve reliably.

### Interview Question 5 — Is hashCode a secure hash?

**Answer:** No. It is not a cryptographic digest or a secure identifier and should not be used as one.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Compute a deterministic value hash | A hash function derives an integer from equality-relevant state. |
| Check equal-key hash compatibility | Equal values must yield equal hashes. |
| Check repeated hashing | Unchanged equality-relevant state supports consistent hashing. |
| Retrieve through an equal key | Hashing and equality jointly support map lookup. |
| Replace a value under an equal key | A logically equal key updates an existing map entry. |

[← 088. equals()](088_equals.md) | [Course Index](../README.md) | [090. instanceof →](090_instanceof.md)
