[← 087. toString()](087_toString.md) | [Course Index](../README.md) | [089. hashCode() →](089_hashCode.md)

# Java equals() Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/05_Object_Oriented_Programming/088_equals.md)

These exercises follow [088. equals()](../../Road%20Map/05_Object_Oriented_Programming/088_equals.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Each solution is a complete Main.java with nested helper types. Run each question independently. Examples define their own ownership and null policies; Java references alone do not enforce UML lifecycle relationships.

## Question 1 — Compare equal values

**Task:** Compare equal values.

```java
public class Main {
static final class Point { final int x,y; Point(int x,int y) { this.x=x; this.y=y; } @Override public boolean equals(Object other) { if(this==other) { return true; } if(!(other instanceof Point)) { return false; } Point point=(Point)other; return x==point.x && y==point.y; } @Override public int hashCode() { return java.util.Objects.hash(x,y); } }
public static void main(String[] args) throws Exception {
System.out.println(new Point(1,2).equals(new Point(1,2)));
}
}
```

**Expected result:**
```text
true
```

**Concept:** Value equality compares the chosen logical state.

## Question 2 — Compare different values

**Task:** Compare different values.

```java
public class Main {
static final class Point { final int x,y; Point(int x,int y) { this.x=x; this.y=y; } @Override public boolean equals(Object other) { if(this==other) { return true; } if(!(other instanceof Point)) { return false; } Point point=(Point)other; return x==point.x && y==point.y; } @Override public int hashCode() { return java.util.Objects.hash(x,y); } }
public static void main(String[] args) throws Exception {
System.out.println(new Point(1,2).equals(new Point(1,3)));
}
}
```

**Expected result:**
```text
false
```

**Concept:** A differing equality-relevant field makes these points unequal.

## Question 3 — Check reflexivity

**Task:** Check reflexivity.

```java
public class Main {
static final class Point { final int x,y; Point(int x,int y) { this.x=x; this.y=y; } @Override public boolean equals(Object other) { if(this==other) { return true; } if(!(other instanceof Point)) { return false; } Point point=(Point)other; return x==point.x && y==point.y; } @Override public int hashCode() { return java.util.Objects.hash(x,y); } }
public static void main(String[] args) throws Exception {
Point point=new Point(1,2); System.out.println(point.equals(point));
}
}
```

**Expected result:**
```text
true
```

**Concept:** Every non-null value must equal itself.

## Question 4 — Check symmetry

**Task:** Check symmetry.

```java
public class Main {
static final class Point { final int x,y; Point(int x,int y) { this.x=x; this.y=y; } @Override public boolean equals(Object other) { if(this==other) { return true; } if(!(other instanceof Point)) { return false; } Point point=(Point)other; return x==point.x && y==point.y; } @Override public int hashCode() { return java.util.Objects.hash(x,y); } }
public static void main(String[] args) throws Exception {
Point a=new Point(1,2),b=new Point(1,2); System.out.println(a.equals(b)==b.equals(a));
}
}
```

**Expected result:**
```text
true
```

**Concept:** Equality must be symmetric.

## Question 5 — Check transitivity

**Task:** Check transitivity.

```java
public class Main {
static final class Point { final int x,y; Point(int x,int y) { this.x=x; this.y=y; } @Override public boolean equals(Object other) { if(this==other) { return true; } if(!(other instanceof Point)) { return false; } Point point=(Point)other; return x==point.x && y==point.y; } @Override public int hashCode() { return java.util.Objects.hash(x,y); } }
public static void main(String[] args) throws Exception {
Point a=new Point(1,2),b=new Point(1,2),c=new Point(1,2); System.out.println(a.equals(b) && b.equals(c) && a.equals(c));
}
}
```

**Expected result:**
```text
true
```

**Concept:** Equal chains must remain transitive.

## Question 6 — Compare with null

**Task:** Compare with null.

```java
public class Main {
static final class Point { final int x,y; Point(int x,int y) { this.x=x; this.y=y; } @Override public boolean equals(Object other) { if(this==other) { return true; } if(!(other instanceof Point)) { return false; } Point point=(Point)other; return x==point.x && y==point.y; } @Override public int hashCode() { return java.util.Objects.hash(x,y); } }
public static void main(String[] args) throws Exception {
System.out.println(new Point(1,2).equals(null));
}
}
```

**Expected result:**
```text
false
```

**Concept:** A non-null instance must not equal null.

## Question 7 — Compare an unrelated type

**Task:** Compare an unrelated type.

```java
public class Main {
static final class Point { final int x,y; Point(int x,int y) { this.x=x; this.y=y; } @Override public boolean equals(Object other) { if(this==other) { return true; } if(!(other instanceof Point)) { return false; } Point point=(Point)other; return x==point.x && y==point.y; } @Override public int hashCode() { return java.util.Objects.hash(x,y); } }
public static void main(String[] args) throws Exception {
System.out.println(new Point(1,2).equals("1,2"));
}
}
```

**Expected result:**
```text
false
```

**Concept:** Type compatibility is part of this final value class's equality rule.

## Question 8 — Check hash compatibility

**Task:** Check hash compatibility.

```java
public class Main {
static final class Point { final int x,y; Point(int x,int y) { this.x=x; this.y=y; } @Override public boolean equals(Object other) { if(this==other) { return true; } if(!(other instanceof Point)) { return false; } Point point=(Point)other; return x==point.x && y==point.y; } @Override public int hashCode() { return java.util.Objects.hash(x,y); } }
public static void main(String[] args) throws Exception {
Point a=new Point(1,2),b=new Point(1,2); System.out.println(a.equals(b)); System.out.println(a.hashCode()==b.hashCode());
}
}
```

**Expected result:**
```text
true
true
```

**Concept:** Equal objects must have equal hash codes.

## Question 9 — Deduplicate equal values

**Task:** Deduplicate equal values.

```java
public class Main {
static final class Point { final int x,y; Point(int x,int y) { this.x=x; this.y=y; } @Override public boolean equals(Object other) { if(this==other) { return true; } if(!(other instanceof Point)) { return false; } Point point=(Point)other; return x==point.x && y==point.y; } @Override public int hashCode() { return java.util.Objects.hash(x,y); } }
public static void main(String[] args) throws Exception {
java.util.Set<Point> values=new java.util.HashSet<>(); values.add(new Point(1,2)); values.add(new Point(1,2)); System.out.println(values.size());
}
}
```

**Expected result:**
```text
1
```

**Concept:** Hash-based collections rely on compatible equals and hashCode behavior.

## Question 10 — Use an equal map key

**Task:** Use an equal map key.

```java
public class Main {
static final class Point { final int x,y; Point(int x,int y) { this.x=x; this.y=y; } @Override public boolean equals(Object other) { if(this==other) { return true; } if(!(other instanceof Point)) { return false; } Point point=(Point)other; return x==point.x && y==point.y; } @Override public int hashCode() { return java.util.Objects.hash(x,y); } }
public static void main(String[] args) throws Exception {
java.util.Map<Point,String> values=new java.util.HashMap<>(); values.put(new Point(1,2),"Found"); System.out.println(values.get(new Point(1,2)));
}
}
```

**Expected result:**
```text
Found
```

**Concept:** A logically equal key can retrieve the stored value.

---

## Interview Questions and Answers

### Interview Question 1 — What is the equals contract?

**Answer:** It requires reflexivity, symmetry, transitivity, consistency while relevant state is unchanged, and false when a non-null object is compared with null.

### Interview Question 2 — Why override hashCode with equals?

**Answer:** Hash-based collections require equal objects to have equal hash codes. Overriding only equals can break lookup and deduplication behavior.

### Interview Question 3 — Do equal hash codes prove equality?

**Answer:** No. Different objects may collide. equals must still determine logical equality.

### Interview Question 4 — Why use a final value class here?

**Answer:** It avoids equality complications from subclasses adding new equality-relevant state or changing symmetry expectations.

### Interview Question 5 — Should equality-relevant state be mutable in map keys?

**Answer:** Avoid changing it while the key is stored. Such mutation can make lookups inconsistent with the location chosen from the original hash.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Compare equal values | Value equality compares the chosen logical state. |
| Compare different values | A differing equality-relevant field makes these points unequal. |
| Check reflexivity | Every non-null value must equal itself. |
| Check symmetry | Equality must be symmetric. |
| Check transitivity | Equal chains must remain transitive. |

[← 087. toString()](087_toString.md) | [Course Index](../README.md) | [089. hashCode() →](089_hashCode.md)
