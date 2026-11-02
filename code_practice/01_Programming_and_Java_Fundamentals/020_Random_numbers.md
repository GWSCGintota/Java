[← 019. Math functions](019_Math_functions.md) | [Course Index](../README.md) | [021. Boolean expressions →](../02_Control_Flow/021_Boolean_expressions.md)

# Java Random numbers Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/01_Programming_and_Java_Fundamentals/020_Random_numbers.md)

These exercises follow [020. Random numbers](../../Road%20Map/01_Programming_and_Java_Fundamentals/020_Random_numbers.md). Each coding question includes a task, model solution, expected result, and concept explanation.

**Java version:** Java 8+. Run each question independently. Unless a complete class is shown, place statements inside `main()` and helper declarations at class scope. Assume imports from `java.util.*`, `java.util.function.*`, `java.util.stream.*`, `java.io.*`, `java.nio.file.*`, and `java.time.*` when used. Inputs are non-null unless explicitly tested.

## Question 1 — Bounded integer

**Task:** Generate an integer in [0, 10) and verify its range.

```java
Random random = new Random(42);
int value = random.nextInt(10);
System.out.println(value >= 0 && value < 10);
```

**Expected result:**
```text
true
```

**Concept:** nextInt(bound) includes zero and excludes its positive bound.

## Question 2 — Reproducible seed

**Task:** Show that two Random instances with the same seed produce matching first values.

```java
Random first = new Random(42), second = new Random(42);
System.out.println(first.nextInt() == second.nextInt());
```

**Expected result:**
```text
true
```

**Concept:** The same seed and call sequence produce repeatable pseudorandom values.

## Question 3 — Inclusive dice range

**Task:** Generate a six-sided die roll and verify its range.

```java
int roll = new Random(42).nextInt(6) + 1;
System.out.println(roll >= 1 && roll <= 6);
```

**Expected result:**
```text
true
```

**Concept:** An offset converts a zero-based bounded result into the desired small inclusive range.

## Question 4 — Decimal range

**Task:** Generate a double and verify [0, 1).

```java
double value = new Random(42).nextDouble();
System.out.println(value >= 0.0 && value < 1.0);
```

**Expected result:**
```text
true
```

**Concept:** nextDouble returns a pseudorandom value in a half-open unit interval.

## Question 5 — Choose an element

**Task:** Select a random name by index and verify membership.

```java
List<String> names = Arrays.asList("Ada", "Grace", "Alan");
String chosen = names.get(new Random(42).nextInt(names.size()));
System.out.println(names.contains(chosen));
```

**Expected result:**
```text
true
```

**Concept:** Random indices must be bounded by a non-empty collection's size.

## Question 6 — Empty input policy

**Task:** Reject random selection from an empty list before generating an index.

```java
List<String> names = Collections.emptyList();
System.out.println(names.isEmpty() ? "No choices" : names.get(new Random(42).nextInt(names.size())));
```

**Expected result:**
```text
No choices
```

**Concept:** An empty collection has no valid random index.

## Question 7 — Invalid bound

**Task:** Catch a non-positive bound passed to nextInt.

```java
try { new Random(42).nextInt(0); } catch (IllegalArgumentException e) { System.out.println("Bound must be positive"); }
```

**Expected result:**
```text
Bound must be positive
```

**Concept:** Bounded generation validates its bound.

## Question 8 — Shuffle a copy

**Task:** Shuffle a copied list while preserving the source.

```java
List<Integer> source = Arrays.asList(1, 2, 3, 4);
List<Integer> shuffled = new ArrayList<>(source);
Collections.shuffle(shuffled, new Random(42));
Collections.sort(shuffled);
System.out.println(source);
System.out.println(shuffled);
```

**Expected result:**
```text
[1, 2, 3, 4]
[1, 2, 3, 4]
```

**Concept:** Sorting after shuffling verifies the same multiset remains without relying on a particular permutation.

## Question 9 — Generate several values

**Task:** Generate five bounded values and verify all satisfy the range.

```java
int[] values = new Random(42).ints(5, 1, 7).toArray();
System.out.println(values.length);
System.out.println(Arrays.stream(values).allMatch(n -> n >= 1 && n < 7));
```

**Expected result:**
```text
5
true
```

**Concept:** Bounded streams use an inclusive origin and exclusive bound.

## Question 10 — Security-sensitive randomness

**Task:** Generate 16 random bytes with SecureRandom and print only their count.

```java
byte[] bytes = new byte[16];
new java.security.SecureRandom().nextBytes(bytes);
System.out.println(bytes.length);
```

**Expected result:**
```text
16
```

**Concept:** Use a cryptographic generator for security-sensitive randomness; encoding and token design are separate concerns.

---

## Interview Questions and Answers

### Interview Question 1 — Is java.util.Random suitable for security tokens?

**Answer:** No. Use a cryptographically suitable generator such as SecureRandom and an appropriate token design. Random is intended for general pseudorandom computations.

### Interview Question 2 — What does an exclusive upper bound mean?

**Answer:** The bound itself is never returned. nextInt(6) produces values from zero through five.

### Interview Question 3 — Why use a fixed seed in tests?

**Answer:** It makes a pseudorandom sequence reproducible when the generator and call sequence are controlled. It does not establish that every possible input has been tested.

### Interview Question 4 — Does random generation guarantee no duplicates?

**Answer:** No. Independent draws can repeat. Sampling without replacement requires a separate algorithm, such as shuffling a finite set and taking a prefix.

### Interview Question 5 — Why avoid modulo for a bounded random choice?

**Answer:** Direct modulo can introduce bias and negative-result issues. Use the generator's bounded methods, which define the desired range and distribution.

---

## Key Takeaways

| Skill | What to practice |
|---|---|
| Bounded integer | nextInt(bound) includes zero and excludes its positive bound. |
| Reproducible seed | The same seed and call sequence produce repeatable pseudorandom values. |
| Inclusive dice range | An offset converts a zero-based bounded result into the desired small inclusive range. |
| Decimal range | nextDouble returns a pseudorandom value in a half-open unit interval. |
| Choose an element | Random indices must be bounded by a non-empty collection's size. |

[← 019. Math functions](019_Math_functions.md) | [Course Index](../README.md) | [021. Boolean expressions →](../02_Control_Flow/021_Boolean_expressions.md)
