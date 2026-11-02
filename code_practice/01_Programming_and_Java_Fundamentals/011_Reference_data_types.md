[← 010. Primitive data types](010_Primitive_data_types.md) | [Course Index](../README.md) | [012. Type casting →](012_Type_casting.md)

# Java Reference data types Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/01_Programming_and_Java_Fundamentals/011_Reference_data_types.md)

These exercises follow [011. Reference data types](../../Road%20Map/01_Programming_and_Java_Fundamentals/011_Reference_data_types.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — String reference

**Task:** Declare and print a string reference.

```java
String language = "Java";
System.out.println(language);
```

**Expected result:**
```text
Java
```

**Concept:** A reference variable refers to an object or holds null.

## Question 2 — Aliasing

**Task:** Share a mutable builder between two references.

```java
StringBuilder a = new StringBuilder("A");
StringBuilder b = a;
b.append("B");
System.out.println(a);
```

**Expected result:**
```text
AB
```

**Concept:** Reference assignment can create aliases for one object.

## Question 3 — Rebinding

**Task:** Reassign one alias without changing the other reference.

```java
StringBuilder a = new StringBuilder("A");
StringBuilder b = a;
b = new StringBuilder("B");
System.out.println(a + ":" + b);
```

**Expected result:**
```text
A:B
```

**Concept:** Reassignment changes the variable, not the original object.

## Question 4 — Null guard

**Task:** Read a nullable string's length safely with a deliberate zero fallback.

```java
String value = null;
System.out.println(value == null ? 0 : value.length());
```

**Expected result:**
```text
0
```

**Concept:** A null policy should be explicit.

## Question 5 — Content equality

**Task:** Compare two separately constructed strings using identity and content equality.

```java
String a = new String("Java"), b = new String("Java");
System.out.println(a == b);
System.out.println(a.equals(b));
```

**Expected result:**
```text
false
true
```

**Concept:** == compares reference identity; String.equals compares content.

## Question 6 — Object supertype

**Task:** Store a string in Object and inspect its runtime class.

```java
Object value = "Java";
System.out.println(value.getClass().getSimpleName());
```

**Expected result:**
```text
String
```

**Concept:** A reference's declared type can differ from the object's runtime type.

## Question 7 — Array reference

**Task:** Share an array and update an element through one alias.

```java
int[] a = {1, 2};
int[] b = a;
b[0] = 9;
System.out.println(Arrays.toString(a));
```

**Expected result:**
```text
[9, 2]
```

**Concept:** Arrays are objects even when their elements are primitives.

## Question 8 — Wrapper reference

**Task:** Store an Integer and unbox it.

```java
Integer boxed = 7;
int primitive = boxed;
System.out.println(primitive);
```

**Expected result:**
```text
7
```

**Concept:** Boxing and unboxing adapt wrapper references and primitive values.

## Question 9 — Null-safe equality

**Task:** Compare nullable references with Objects.equals.

```java
String first = null;
System.out.println(Objects.equals(first, "Java"));
System.out.println(Objects.equals(first, null));
```

**Expected result:**
```text
false
true
```

**Concept:** Objects.equals handles a null first argument.

## Question 10 — Reference array defaults

**Task:** Create a string array and inspect the initial element.

```java
String[] names = new String[1];
System.out.println(names[0]);
```

**Expected result:**
```text
null
```

**Concept:** Reference array elements initially contain null.

---

## Interview Questions and Answers

### Interview Question 1 — What does a reference variable contain?

**Answer:** A reference value identifying an object, or null. Do not treat the JVM's internal representation as a portable raw memory address.

### Interview Question 2 — Does assigning an object reference clone the object?

**Answer:** No. It copies the reference value. Both variables may then refer to the same mutable object.

### Interview Question 3 — How do declared and runtime types differ?

**Answer:** The declared type controls accessible operations at compile time. The runtime class determines the object's actual type and overridden instance-method behavior.

### Interview Question 4 — What does null mean?

**Answer:** The absence of an object reference. Dereferencing null through an instance member usually throws NullPointerException.

### Interview Question 5 — Is Java pass-by-reference for objects?

**Answer:** No. Java passes a copy of the reference value. A callee can mutate the shared object, but reassigning its parameter does not rebind the caller's variable.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| String reference | A reference variable refers to an object or holds null. |
| Aliasing | Reference assignment can create aliases for one object. |
| Rebinding | Reassignment changes the variable, not the original object. |
| Null guard | A null policy should be explicit. |
| Content equality | == compares reference identity; String.equals compares content. |

[← 010. Primitive data types](010_Primitive_data_types.md) | [Course Index](../README.md) | [012. Type casting →](012_Type_casting.md)
