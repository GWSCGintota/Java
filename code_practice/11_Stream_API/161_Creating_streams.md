[← 160. Streams](160_Streams.md) | [Course Index](../README.md) | [162. filter() →](162_filter.md)

# Java Creating streams Practice — 10 Coding Questions and 5 Interview Q&As

[← Road Map lesson](../../Road%20Map/11_Stream_API/161_Creating_streams.md)

These exercises follow the corresponding Road Map lesson, progressing from basic operations to composition, boundary cases, and safe execution. Each coding question includes a model solution, expected output, and concept explanation.

**Java version:** Java 8+. Short snippets are independent and belong inside `main()`; assume imports from `java.util.*`, `java.util.function.*`, and `java.util.stream.*`. Complete programs include their helper types and should be saved as `Main.java`. Run questions separately. Inputs are non-null unless explicitly handled, and arithmetic uses small values that do not overflow. For checked I/O, `main()` must declare `throws Exception` or handle the failure.

## Question 1 — Collection source

**Task:** Create a stream from [Ada, Grace] and collect uppercase names.

```java
List<String> names = Arrays.asList("Ada", "Grace");
System.out.println(names.stream().map(s -> s.toUpperCase(Locale.ROOT)).collect(Collectors.toList()));
```

**Output:**
```text
[ADA, GRACE]
```

**Concept:** Collection.stream creates a sequential stream source.

## Question 2 — Object array source

**Task:** Stream the array ["A", "B", "C"] and join values with hyphens.

```java
String[] values = {"A", "B", "C"};
System.out.println(Arrays.stream(values).collect(Collectors.joining("-")));
```

**Output:**
```text
A-B-C
```

**Concept:** Arrays.stream exposes reference-array components individually.

## Question 3 — Primitive-array trap

**Task:** Compare Arrays.stream and Stream.of for int[] {1, 2, 3}: print the sum and the object-stream count.

```java
int[] values = {1, 2, 3};
System.out.println(Arrays.stream(values).sum());
System.out.println(Stream.of(values).count());
```

**Output:**
```text
6
1
```

**Concept:** Stream.of(intArray) contains one array object; Arrays.stream creates an IntStream.

## Question 4 — Exclusive and inclusive ranges

**Task:** Collect range(1, 4) and rangeClosed(1, 4) into boxed lists.

```java
System.out.println(IntStream.range(1, 4).boxed().collect(Collectors.toList()));
System.out.println(IntStream.rangeClosed(1, 4).boxed().collect(Collectors.toList()));
```

**Output:**
```text
[1, 2, 3]
[1, 2, 3, 4]
```

**Concept:** range excludes the upper endpoint while rangeClosed includes it.

## Question 5 — Repeated generated values

**Task:** Generate four copies of Java with Stream.generate.

```java
System.out.println(Stream.generate(() -> "Java").limit(4).collect(Collectors.toList()));
```

**Output:**
```text
[Java, Java, Java, Java]
```

**Concept:** generate obtains values from a supplier and needs a bound here.

## Question 6 — Seeded iteration

**Task:** Generate the first six powers of two starting from one.

```java
System.out.println(Stream.iterate(1, n -> n * 2).limit(6).collect(Collectors.toList()));
```

**Output:**
```text
[1, 2, 4, 8, 16, 32]
```

**Concept:** iterate includes the seed before repeatedly applying the next-value function.

## Question 7 — Builder and concatenation

**Task:** Build a stream of Ada and Grace, concatenate Alan, and collect the result.

```java
Stream.Builder<String> builder = Stream.builder();
builder.add("Ada").add("Grace");
System.out.println(Stream.concat(builder.build(), Stream.of("Alan")).collect(Collectors.toList()));
```

**Output:**
```text
[Ada, Grace, Alan]
```

**Concept:** A builder produces one stream; concat links two sources.

## Question 8 — Nullable value on Java 8

**Task:** For null and Ada, create an empty or one-element stream without Java 9 APIs and print the counts.

```java
for (String value : Arrays.asList(null, "Ada")) {
    Stream<String> stream = value == null ? Stream.empty() : Stream.of(value);
    System.out.println(stream.count());
}
```

**Output:**
```text
0
1
```

**Concept:** An explicit branch provides Java 8 empty-or-present creation.

## Question 9 — Fresh stream factory

**Task:** Use a Supplier to count [1, 2, 3], then sum it using another stream instance.

```java
Supplier<Stream<Integer>> factory = () -> Stream.of(1, 2, 3);
System.out.println(factory.get().count());
System.out.println(factory.get().mapToInt(Integer::intValue).sum());
```

**Output:**
```text
3
6
```

**Concept:** A reusable factory must create a fresh stream per invocation.

## Question 10 — File-backed source

**Task:** Write two UTF-8 lines to a temporary file, count them with Files.lines, close the stream, and delete the file.

```java
java.nio.file.Path path = java.nio.file.Files.createTempFile("stream-practice-", ".txt");
try {
    java.nio.file.Files.write(path, Arrays.asList("Ada", "Grace"), java.nio.charset.StandardCharsets.UTF_8);
    try (Stream<String> lines = java.nio.file.Files.lines(path, java.nio.charset.StandardCharsets.UTF_8)) {
        System.out.println(lines.count());
    }
} finally {
    java.nio.file.Files.deleteIfExists(path);
}
```

**Output:**
```text
2
```

**Concept:** Resource-backed streams need closure even after terminal completion; checked failures must be handled or declared.

---

## Interview Questions and Answers

### Interview Question 1 — Why does Stream.of(new int[]{1, 2}) have count one?

**Answer:** Its single reference element is the primitive array object. It does not unpack primitive components.

### Interview Question 2 — How can a stream calculation be repeated?

**Answer:** Keep reusable data or a supplier that creates a genuinely fresh stream, then build a new pipeline.

### Interview Question 3 — Must every stream be explicitly closed?

**Answer:** Resource-backed sources must be closed according to ownership. Ordinary in-memory collection streams usually do not require explicit closure.

### Interview Question 4 — Is Stream.generate ordered?

**Answer:** Its contract is unordered, whereas seed-based iterate defines an encounter sequence. Do not derive an ordering requirement from incidental sequential generator behavior.

### Interview Question 5 — When may an ArrayList stream observe source changes?

**Answer:** Its normal late-binding traversal can observe changes before traversal begins. Mutations during traversal can violate non-interference; stream creation is not an eager snapshot.

---

## Key Takeaways

| Skill | What to remember |
|---|---|
| Collection source | Collection.stream creates a sequential stream source. |
| Object array source | Arrays.stream exposes reference-array components individually. |
| Primitive-array trap | Stream.of(intArray) contains one array object; Arrays.stream creates an IntStream. |
| Exclusive and inclusive ranges | range excludes the upper endpoint while rangeClosed includes it. |
| Repeated generated values | generate obtains values from a supplier and needs a bound here. |

[← Road Map lesson](../../Road%20Map/11_Stream_API/161_Creating_streams.md)

[← 160. Streams](160_Streams.md) | [Course Index](../README.md) | [162. filter() →](162_filter.md)
