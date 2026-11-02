[← 050. Multidimensional arrays](050_Multidimensional_arrays.md) | [Course Index](../README.md) | [052. Arrays utility class →](052_Arrays_utility_class.md)

# Java Arrays of objects Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/04_Arrays/051_Arrays_of_objects.md)

These exercises follow [051. Arrays of objects](../../Road%20Map/04_Arrays/051_Arrays_of_objects.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — String objects

**Task:** Create and print a string array.

```java
String[] names = {"Ada", "Grace"};
System.out.println(Arrays.toString(names));
```

**Expected result:**
```text
[Ada, Grace]
```

**Concept:** A reference array stores references to objects.

## Question 2 — Default references

**Task:** Allocate two builder slots without constructing builders.

```java
StringBuilder[] values = new StringBuilder[2];
System.out.println(Arrays.toString(values));
```

**Expected result:**
```text
[null, null]
```

**Concept:** Reference-array allocation does not instantiate its components.

## Question 3 — Initialize elements

**Task:** Construct each builder explicitly.

```java
StringBuilder[] values = new StringBuilder[2];
for (int i = 0; i < values.length; i++) { values[i] = new StringBuilder("Item " + i); }
System.out.println(Arrays.toString(values));
```

**Expected result:**
```text
[Item 0, Item 1]
```

**Concept:** Each component can hold an independently created object.

## Question 4 — Mutate an element object

**Task:** Append text to a builder stored in an array.

```java
StringBuilder[] values = {new StringBuilder("A")};
values[0].append("B");
System.out.println(values[0]);
```

**Expected result:**
```text
AB
```

**Concept:** Component access yields a reference through which mutation is possible.

## Question 5 — Replace a reference

**Task:** Replace an array component without changing the old object.

```java
StringBuilder original = new StringBuilder("A");
StringBuilder[] values = {original};
values[0] = new StringBuilder("B");
System.out.println(original + ":" + values[0]);
```

**Expected result:**
```text
A:B
```

**Concept:** Replacing a slot and mutating an object are different actions.

## Question 6 — Shallow clone

**Task:** Clone a builder array and mutate its shared element.

```java
StringBuilder[] original = {new StringBuilder("A")}, copy = original.clone();
copy[0].append("B");
System.out.println(original[0]);
```

**Expected result:**
```text
AB
```

**Concept:** Cloning a reference array copies references rather than nested mutable objects.

## Question 7 — Copy nested content

**Task:** Create independent builders while copying an array.

```java
StringBuilder[] original = {new StringBuilder("A")}, copy = new StringBuilder[original.length];
for (int i = 0; i < original.length; i++) { copy[i] = new StringBuilder(original[i].toString()); }
copy[0].append("B");
System.out.println(original[0] + ":" + copy[0]);
```

**Expected result:**
```text
A:AB
```

**Concept:** An explicit copy policy can isolate nested mutable state for this non-null fixture.

## Question 8 — Array covariance

**Task:** Catch an incompatible store through an Object[] view of a String[].

```java
Object[] values = new String[1];
try { values[0] = Integer.valueOf(7); } catch (ArrayStoreException e) { System.out.println("Incompatible element"); }
```

**Expected result:**
```text
Incompatible element
```

**Concept:** Arrays retain their runtime component type and check stores.

## Question 9 — Custom objects

**Task:** Store two User instances and print their names.

```java
public class Main { static class User { final String name; User(String name) { this.name = name; } } public static void main(String[] args) { User[] users = {new User("Ada"), new User("Grace")}; for (User user : users) { System.out.println(user.name); } } }
```

**Expected result:**
```text
Ada
Grace
```

**Concept:** A custom reference type can be an array component type.

## Question 10 — Nullable elements

**Task:** Count only initialized reference slots.

```java
String[] names = {"Ada", null, "Grace"}; int count = 0;
for (String name : names) { if (name != null) { count++; } }
System.out.println(count);
```

**Expected result:**
```text
2
```

**Concept:** Array existence does not imply every reference component is non-null.

---

## Interview Questions and Answers

### Interview Question 1 — Does new Employee[5] call an Employee constructor?

**Answer:** No. It allocates five reference slots initialized to null; Employee instances must be constructed separately.

### Interview Question 2 — What is array covariance?

**Answer:** A reference array type can be assigned to an array reference of a compatible supertype, such as String[] to Object[]. Runtime store checks preserve component-type safety.

### Interview Question 3 — What causes ArrayStoreException?

**Answer:** Assigning an object incompatible with the array's runtime reference component type, even when the receiving variable has a broader declared array type.

### Interview Question 4 — Does copying an object array clone its objects?

**Answer:** Ordinary array cloning and copying copy reference values. Independent nested state requires a defined deeper-copy operation.

### Interview Question 5 — How can null slots be handled?

**Answer:** Initialize every slot, skip missing entries, reject them, or model absence explicitly. The array type alone does not enforce a non-null component contract.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| String objects | A reference array stores references to objects. |
| Default references | Reference-array allocation does not instantiate its components. |
| Initialize elements | Each component can hold an independently created object. |
| Mutate an element object | Component access yields a reference through which mutation is possible. |
| Replace a reference | Replacing a slot and mutating an object are different actions. |

[← 050. Multidimensional arrays](050_Multidimensional_arrays.md) | [Course Index](../README.md) | [052. Arrays utility class →](052_Arrays_utility_class.md)
