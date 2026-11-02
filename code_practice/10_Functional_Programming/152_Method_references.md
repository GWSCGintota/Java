[← 151. Lambda expressions](151_Lambda_expressions.md) | [Course Index](../README.md) | [153. Predicate →](153_Predicate.md)

# Java Method References Practice — 10 Coding Questions and Answers

[← Road Map lesson](../../Road%20Map/10_Functional_Programming/152_Method_references.md)

These exercises follow [152. Method references](../../Road%20Map/10_Functional_Programming/152_Method_references.md), covering the four reference forms, target typing, and receiver timing. Each question includes a model solution and a short explanation.

**Java version:** Java 8+. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports. Run each question independently. Put statements inside `main()` and the nested class in Question 7 inside `Main`. Inputs are non-null unless stated otherwise.

## Question 1 — Reference a Static Method

**Task:** Replace `text -> Integer.parseInt(text)` with a method reference targeting `ToIntFunction<String>`. Parse `42` and `-7`.

```java
ToIntFunction<String> parse = Integer::parseInt;
System.out.println(parse.applyAsInt("42"));
System.out.println(parse.applyAsInt("-7"));
```

**Output:**
```text
42
-7
```

**Concept:** `Type::staticMethod` forwards arguments to a static method. The target interface determines the invocation signature. Invalid integer text still throws `NumberFormatException`.

## Question 2 — Reference a Bound Instance Method

**Task:** Create a predicate referring to `equals` on the string `Java`. Test `Java` and `java`.

```java
String expected = "Java";
Predicate<String> matches = expected::equals;
System.out.println(matches.test("Java"));
System.out.println(matches.test("java"));
```

**Output:**
```text
true
false
```

**Concept:** `object::instanceMethod` binds an existing receiver. Here the predicate's input becomes the argument to `expected.equals(input)`.

## Question 3 — Reference an Unbound Instance Method

**Task:** Represent string length using both `Function<String, Integer>` and `ToIntFunction<String>`. Also create a `BiPredicate` checking whether the first string starts with the second.

```java
Function<String, Integer> boxedLength = String::length;
ToIntFunction<String> primitiveLength = String::length;
BiPredicate<String, String> startsWith = String::startsWith;
System.out.println(boxedLength.apply("Java"));
System.out.println(primitiveLength.applyAsInt("Lambda"));
System.out.println(startsWith.test("Java", "Ja"));
```

**Output:**
```text
4
6
true
```

**Concept:** In an unbound reference, the first functional input supplies the receiver. Remaining inputs supply method arguments. `String::length` is not a static method call.

## Question 4 — Construct Independent Lists

**Task:** Use a constructor reference to supply two new lists. Add `Ada` to the first and show that the second remains empty.

```java
Supplier<List<String>> factory = ArrayList<String>::new;
List<String> first = factory.get();
List<String> second = factory.get();
first.add("Ada");
System.out.println(first);
System.out.println(second);
System.out.println(first == second);
```

**Output:**
```text
[Ada]
[]
false
```

**Concept:** `Type::new` refers to a constructor. This constructor creates a new list on each invocation; the supplier interface alone does not guarantee freshness.

## Question 5 — Construct Arrays

**Task:** Use `String[]::new` as an array factory of length three. Then convert a stream of `Ada` and `Grace` into a string array with the same reference form.

```java
IntFunction<String[]> arrayFactory = String[]::new;
String[] emptySlots = arrayFactory.apply(3);
System.out.println(emptySlots.length);
System.out.println(emptySlots[0]);
String[] names = Arrays.asList("Ada", "Grace").stream()
        .toArray(String[]::new);
System.out.println(Arrays.toString(names));
```

**Output:**
```text
3
null
[Ada, Grace]
```

**Concept:** The functional input supplies the array length. Newly created reference-array slots initially contain null.

## Question 6 — Select Overloads Through Target Types

**Task:** Reference the integer and long overloads of `Math.abs` using suitable primitive operators. Test `-12` and `-20L`.

```java
IntUnaryOperator absoluteInt = Math::abs;
LongUnaryOperator absoluteLong = Math::abs;
System.out.println(absoluteInt.applyAsInt(-12));
System.out.println(absoluteLong.applyAsLong(-20L));
```

**Output:**
```text
12
20
```

**Concept:** Target typing helps select an overload. A bare `var absolute = Math::abs` has no target functional interface and fails. The minimum signed integer and long values are special overflow cases for `abs`.

## Question 7 — Sort Objects Using Getter References

**Task:** Sort people by age and then by name for equal ages. Print each name with its age.

```java
static class Person {
    private final String name;
    private final int age;
    Person(String name, int age) { this.name = name; this.age = age; }
    String getName() { return name; }
    int getAge() { return age; }
}
```

**Example calls:**

```java
List<Person> people = new ArrayList<>(Arrays.asList(
        new Person("Bob", 30), new Person("Grace", 25), new Person("Ada", 30)));
people.sort(Comparator.comparingInt(Person::getAge).thenComparing(Person::getName));
people.forEach(person -> System.out.println(person.getName() + ":" + person.getAge()));
```

**Output:**
```text
Grace:25
Ada:30
Bob:30
```

**Concept:** Getter references provide reusable key extractors. Keep a lambda where printing requires extra formatting logic.

## Question 8 — Observe Receiver Binding

**Task:** Bind `add` to an existing list, reassign the local variable to a new list, and invoke the bound action. Print both lists.

```java
List<String> original = new ArrayList<>();
List<String> target = original;
Consumer<String> add = target::add;
target = new ArrayList<>();
add.accept("Ada");
System.out.println(original);
System.out.println(target);
```

**Output:**
```text
[Ada]
[]
```

**Concept:** A bound method reference evaluates its receiver when created. It retains the original list even if the local variable is reassigned. A lambda capturing that reassigned local variable would violate effectively final capture rules.

## Question 9 — Compare Null Failure Timing

**Task:** Compare a bound reference and a lambda when the receiver is null. Catch each failure and identify when it happens.

```java
String text = null;
try {
    Supplier<Integer> bound = text::length;
} catch (NullPointerException exception) {
    System.out.println("Bound reference: creation failed");
}
Supplier<Integer> delayed = () -> text.length();
System.out.println("Lambda created");
try {
    delayed.get();
} catch (NullPointerException exception) {
    System.out.println("Lambda: invocation failed");
}
```

**Output:**
```text
Bound reference: creation failed
Lambda created
Lambda: invocation failed
```

**Concept:** Replacing a lambda with a bound reference can change evaluation timing. A null receiver fails while creating the bound reference; this lambda dereferences it when invoked.

## Question 10 — Reference a Method That Throws a Checked Exception

**Task:** Use `StringReader::new` as a factory and a bound reference to `read` targeting `Callable<Integer>`. Read one character from `A` while the reader remains open.

```java
Function<String, java.io.StringReader> factory = java.io.StringReader::new;
try (java.io.StringReader reader = factory.apply("A")) {
    java.util.concurrent.Callable<Integer> read = reader::read;
    System.out.println((char) read.call().intValue());
} catch (Exception exception) {
    throw new RuntimeException(exception);
}
```

**Output:**
```text
A
```

**Concept:** `Callable.call()` permits checked exceptions from `read()`. `Supplier<Integer>` does not directly permit this method reference. Invoke resource-dependent behavior within the resource's lifetime.

---

## Key Takeaways

| Form or API | Purpose | Example |
|---|---|---|
| `Type::staticMethod` | Reference a static operation | `Integer::parseInt` |
| `object::instanceMethod` | Bind an existing receiver | `expected::equals` |
| `Type::instanceMethod` | Receive the receiver as an input | `String::length` |
| `Type::new` | Reference a constructor | `ArrayList<String>::new` |
| `Type[]::new` | Reference an array constructor | `String[]::new` |
| Target typing | Select a compatible signature | `IntUnaryOperator` for `Math::abs` |
| `comparingInt()` | Build a comparator from an integer key | `Person::getAge` |
| `Callable<V>` | Permit checked failures | `reader::read` |

**Reminder:** References do not bypass exception rules or automatically execute the referenced method. Keep lambdas when you need argument adjustment, extra logic, or explicit locale selection such as `text -> text.toUpperCase(Locale.ROOT)`.

---

## Interview Questions and Answers

### Interview Question 1 — What are the four main method-reference forms?

**Answer:** Static method references, bound instance references, unbound instance references, and constructor references. Array constructor references are a constructor form.

### Interview Question 2 — What is the receiver in String::length?

**Answer:** The functional input supplies the String receiver. For example, ToIntFunction<String> invokes length on its input rather than calling a static String method.

### Interview Question 3 — When is a bound receiver evaluated?

**Answer:** When the method reference is created. Reassigning the variable later does not change the captured receiver; a null bound receiver fails during reference creation.

### Interview Question 4 — Can method references discard return values?

**Answer:** Yes, when compatible with a void target. For example, Consumer<String> add = list::add discards the boolean returned by List.add.

### Interview Question 5 — Are method references always interchangeable with lambdas?

**Answer:** No. Target typing, checked exceptions, overload resolution, receiver evaluation timing, and additional argument logic all matter. Use whichever form preserves the required behavior and is clear.


[← 151. Lambda expressions](151_Lambda_expressions.md) | [Course Index](../README.md) | [153. Predicate →](153_Predicate.md)
