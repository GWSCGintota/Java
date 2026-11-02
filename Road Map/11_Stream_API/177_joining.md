# 177. joining()

[Coding practice →](../../code_practice/11_Stream_API/177_joining.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Join character sequences with a delimiter, prefix, and suffix.
- Normalize, select, and order values before formatting.
- Handle empty/null inputs and distinguish joining from serialization.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. Joining collector forms

`Collectors.joining()` concatenates `CharSequence` elements. Its overloads accept a delimiter, or delimiter plus prefix and suffix. The collected result is a `String`.

```java
System.out.println(Stream.of("A", "B", "C").collect(Collectors.joining())); // ABC
System.out.println(Stream.of("Ada", "Grace").collect(Collectors.joining(", ")));
// Ada, Grace
System.out.println(Stream.of("Ada", "Grace")
        .collect(Collectors.joining(", ", "[", "]"))); // [Ada, Grace]
```

Delimiters go between elements, not after the final element. Prefix and suffix are added once around the complete result.

## 2. Map values into text

```java
String labels = IntStream.rangeClosed(1, 3).mapToObj(n -> "Item " + n)
        .collect(Collectors.joining(" | "));
System.out.println(labels); // Item 1 | Item 2 | Item 3
String numbers = Stream.of(1, 2, 3).map(String::valueOf)
        .collect(Collectors.joining(","));
System.out.println(numbers); // 1,2,3
```

Joining expects character sequences, so map domain objects or numeric values into an explicit string representation first. Prefer deliberate formatting to relying on an object's diagnostic `toString` when output is a user-facing contract.

## 3. Empty input, missing values, and whitespace

```java
System.out.println("[" + Stream.<String>empty().collect(Collectors.joining(",")) + "]");
// []
System.out.println(Stream.<String>empty().collect(Collectors.joining(",", "[", "]")));
// []
String result = Arrays.asList(" Ada ", null, "", " Grace ").stream()
        .filter(Objects::nonNull).map(String::trim).filter(s -> !s.isEmpty())
        .collect(Collectors.joining(", "));
System.out.println(result); // Ada, Grace
```

Empty input has no element text, but a chosen prefix and suffix remain. Decide whether missing values should be omitted, rejected, or represented by a placeholder; do not let accidental null conversion become the policy. Trimming and removing blank values are separate from delimiter insertion.

## 4. Order and nested reductions

```java
Map<Integer, String> byLength = Stream.of("Ada", "Bob", "Grace")
        .collect(Collectors.groupingBy(String::length, Collectors.joining(" / ")));
System.out.println(byLength.get(3)); // Ada / Bob
System.out.println(Stream.of("Grace", "Ada").sorted()
        .collect(Collectors.joining(", "))); // Ada, Grace
```

For ordered input, joining follows encounter order, including a correct parallel reduction. An unordered source does not promise one textual order. Sort deliberately when a stable lexical order is required. To join extracted object fields inside groups, compose `mapping(fieldGetter, joining(...))`.

## 5. Formatting is not escaping or streaming output

Joining creates one final in-memory string. It does not stream an arbitrarily large response to an output resource, and very large results can consume substantial memory. Use an appropriate writer or streaming format when output size requires it.

Joining with commas is not a complete CSV encoder: fields containing commas, quotes, or line breaks need format-specific escaping. Joining fragments also does not make JSON, HTML, SQL, or shell text safe. Use an encoder or structured API suited to the target format.

For a few known string values, `String.join` can be simpler. `StringJoiner` is useful for explicit incremental construction; a collector is convenient when the input is already a stream.

## 6. Complete runnable example

Save as `StreamLesson177.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson177 {
    public static void main(String[] args) throws Exception {
        List<String> raw = Arrays.asList(" Ada ", null, "", " Grace ");
        String names = raw.stream().filter(Objects::nonNull).map(String::trim)
                .filter(s -> !s.isEmpty()).collect(Collectors.joining(", ", "[", "]"));
        System.out.println(names);
        System.out.println(IntStream.rangeClosed(1, 3).mapToObj(String::valueOf)
                .collect(Collectors.joining("-")));
        System.out.println(Stream.<String>empty().collect(Collectors.joining(",", "[", "]")));
        Map<Integer, String> groups = Stream.of("Ada", "Bob", "Grace")
                .collect(Collectors.groupingBy(String::length, Collectors.joining(" / ")));
        System.out.println(groups.get(3));
    }
}
```

Compile and run:

```shell
javac StreamLesson177.java
java StreamLesson177
```

Expected output:

```text
[Ada, Grace]
1-2-3
[]
Ada / Bob
```

## 7. Common mistakes

| Mistake | Better approach |
|---|---|
| Appending delimiters manually and removing the last one | Use joining's between-element delimiter. |
| Joining non-character values directly | Map to an explicit string representation first. |
| Letting nulls choose output policy accidentally | Omit, reject, or replace them deliberately. |
| Assuming comma joining is valid CSV | Apply format-specific quoting and escaping. |
| Assuming unordered input gives stable text | Define the ordering before joining. |

## 8. Practice exercises

1. Join names with semicolons. **Hint:** joining(";").
2. Wrap joined values in parentheses. **Hint:** Prefix and suffix overload.
3. Join integers from one to five. **Hint:** mapToObj before joining.
4. Normalize nullable names and omit empty values. **Hint:** Guard, trim, filter, join.
5. Group employees by department and join names. **Hint:** mapping downstream of groupingBy.

## 9. Interview questions and answers

### 1. What is returned for empty joining input?

The no-prefix form returns empty text; the prefix/suffix form retains those surrounding strings.

### 2. Does joining escape delimiters contained in values?

No. It only inserts separators; use a real format encoder when field contents require escaping.

### 3. Does parallel joining scramble an ordered source?

A correct joining reduction preserves required encounter order, though unordered sources do not define a stable text order.

## Completion checklist

- [ ] I can choose delimiter and wrapper forms.
- [ ] I can prepare text before joining.
- [ ] I can predict empty results and ordering.
- [ ] I can distinguish formatting from encoding and large-output streaming.

## Official references

- [Collectors API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Collectors.html)
- [Collector contract](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Collector.html)
- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)

---

[← 176. counting()](176_counting.md) | [Course Index](../README.md) | [178. Parallel streams →](178_Parallel_streams.md)
