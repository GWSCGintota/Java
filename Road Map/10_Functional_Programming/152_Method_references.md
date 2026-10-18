[← 151. Lambda expressions](151_Lambda_expressions.md) | [Course Index](../README.md) | [153. Predicate →](153_Predicate.md)

---

# 152. Method references

**Section:** Java Functional Programming

**Level:** Beginner → Advanced

**Requires:** Java 8 or later. Examples use Java 8-compatible APIs.

## Learning goals

- Read and write static, bound instance, unbound instance, and constructor references.
- Match a reference to a functional interface using parameter and return types.
- Explain receiver binding, execution timing, null handling, and checked exceptions.
- Use references in collections, streams, comparators, and factories.
- Choose a lambda when it communicates the behavior more clearly.

## 1. What is a method reference?

A **method reference** uses `::` to supply an existing method or constructor as the implementation of a compatible functional interface. It is useful when a lambda simply forwards arguments.

```java
Consumer<String> lambda = text -> System.out.println(text);
Consumer<String> reference = System.out::println;
reference.accept("Hello, Java!"); // Prints Hello, Java!
```

A reference is not a method call. The referenced operation runs when the functional interface's abstract method is invoked. Evaluating the receiver expression of a bound reference can still perform work immediately; see section 8.

Method references were introduced with lambdas in Java 8. Read [151. Lambda expressions](151_Lambda_expressions.md) first if functional interfaces and target typing are unfamiliar.

## 2. The four main forms

| Kind | Syntax | Representative target | Equivalent forwarding lambda |
| --- | --- | --- | --- |
| Static method | `Type::staticMethod` | `Function<String, Integer>` for `Integer::parseInt` | `s -> Integer.parseInt(s)` |
| Bound instance method | `object::instanceMethod` | `Predicate<String>` for `prefix::equals` | `s -> prefix.equals(s)` |
| Unbound instance method | `Type::instanceMethod` | `ToIntFunction<String>` for `String::length` | `s -> s.length()` |
| Constructor | `Type::new` | `Supplier<ArrayList<String>>` for `ArrayList<String>::new` | `() -> new ArrayList<String>()` |

The bound-instance lambda comparison assumes a stable, non-null receiver. Timing and receiver binding can differ when expressions or reassignment are involved.

Array constructors, such as `String[]::new`, are another constructor-reference form.

## 3. Static method references

Use the declaring type followed by `::` and the method name.

```java
Function<String, Integer> parse = Integer::parseInt;
System.out.println(parse.apply("42")); // 42

IntBinaryOperator maximum = Math::max;
System.out.println(maximum.applyAsInt(7, 12)); // 12
```

The functional interface's arguments are passed to the static method in order. There is no receiver input.

Primitive interfaces avoid unnecessary boxing:

```java
ToIntFunction<String> parsePrimitive = Integer::parseInt;
int value = parsePrimitive.applyAsInt("42");
```

Here `Function<String, Integer>` boxes the `int` result; `ToIntFunction<String>` returns an `int` directly. Parsing malformed text can still throw `NumberFormatException` when invoked.

## 4. Bound instance method references

A bound reference fixes a particular receiver when the reference expression is evaluated.

```java
String prefix = "Java";
Predicate<String> matches = prefix::equals;
System.out.println(matches.test("Java")); // true
System.out.println(matches.test("Python")); // false

Consumer<String> print = System.out::println;
print.accept("Learning method references");
```

The receiver is already supplied, so the functional interface only provides the method's ordinary arguments.

```java
StringBuilder builder = new StringBuilder();
Consumer<String> append = builder::append;
append.accept("Java");
append.accept(" 8+");
System.out.println(builder); // Java 8+
```

`append` returns a `StringBuilder`, but its result is discarded for the `void`-returning `Consumer` target. Binding an object does not make it immutable or thread-safe.

## 5. Unbound instance method references

An unbound reference does not select a particular receiver up front. The functional interface's **first argument becomes the receiver**, and remaining arguments are passed to the method.

```java
ToIntFunction<String> length = String::length;
System.out.println(length.applyAsInt("Java")); // 4

BiPredicate<String, String> startsWith = String::startsWith;
System.out.println(startsWith.test("Java", "Ja")); // true

Comparator<String> ignoreCase = String::compareToIgnoreCase;
System.out.println(ignoreCase.compare("java", "JAVA")); // 0
```

Read these as:

```java
text -> text.length()
(text, prefix) -> text.startsWith(prefix)
(first, second) -> first.compareToIgnoreCase(second)
```

This explains why `String::length` takes one functional input although `length()` declares no parameters. The input supplies the object on which to call the method.

### Bound versus unbound

```java
String text = "Java";
Supplier<Integer> bound = text::length;          // Receiver already fixed
Function<String, Integer> unbound = String::length; // Receiver supplied later
System.out.println(bound.get());          // 4
System.out.println(unbound.apply("Hello")); // 5
```

## 6. Constructor references and factories

Use `Type::new` to create objects when the functional interface is invoked. The target signature selects a compatible constructor.

```java
Supplier<ArrayList<String>> emptyList = ArrayList<String>::new;
Function<Integer, ArrayList<String>> sizedList = ArrayList<String>::new;

ArrayList<String> first = emptyList.get();
ArrayList<String> second = emptyList.get();
System.out.println(first == second); // false: two constructor invocations

ArrayList<String> reserved = sizedList.apply(10);
System.out.println(reserved.size()); // 0: capacity is not element count
```

For a custom class:

```java
class Person {
    private final String name;
    private final int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

// Inside a method:
BiFunction<String, Integer, Person> factory = Person::new;
Person ada = factory.apply("Ada", 36);
```

Interfaces and abstract classes cannot be instantiated through constructor references. Constructors must be accessible, and invalid constructor arguments can fail when invoked. A general `Supplier` does not guarantee a new object; this constructor-based supplier does.

### Array constructor references

```java
IntFunction<String[]> arrayFactory = String[]::new;
String[] names = arrayFactory.apply(3);
System.out.println(names.length); // 3
System.out.println(names[0]);     // null

String[] result = Arrays.asList("Ada", "Grace").stream()
        .toArray(String[]::new);
```

The functional input is the array length. Negative lengths throw `NegativeArraySizeException` at invocation. Primitive arrays also work, for example `IntFunction<int[]> numbers = int[]::new;`.

## 7. Target typing and compatibility

A method reference needs a target functional interface. Java checks the callable's parameters, receiver requirements, result, accessibility, and checked exceptions against that interface.

```java
Function<String, Integer> boxed = String::length;
ToIntFunction<String> primitive = String::length;
Consumer<String> discardResult = String::length;

// var length = String::length;    // Invalid: no target type
// Object length = String::length; // Invalid: Object is not functional
```

Compatible conversions may include boxing, unboxing, and widening. Exact textual equality between the method's signature and the functional interface is not required. Unboxing a null argument can still throw at invocation.

### Overloaded methods

The target signature helps choose the overload:

```java
IntUnaryOperator absoluteInt = Math::abs;
LongUnaryOperator absoluteLong = Math::abs;
```

Overloaded APIs accepting different functional interfaces can make a method-reference argument ambiguous. Use a typed variable, a cast, or a clearer lambda to disambiguate. A type-qualified reference may also be ambiguous when static and unbound-instance candidates both fit.

### Generic methods

Usually Java infers the type arguments:

```java
Supplier<List<String>> empty = Collections::emptyList;
```

Explicit method type arguments can be placed after `::`:

```java
Supplier<List<String>> emptyExplicit = Collections::<String>emptyList;
```

## 8. Receiver binding, evaluation timing, and null

The distinction between creating the behavior and invoking it matters.

```java
String text = null;
// Supplier<Integer> immediateFailure = text::length;
// NullPointerException occurs while creating the bound reference.

Supplier<Integer> delayedFailure = () -> text.length();
// Creation succeeds. delayedFailure.get() would throw later.

ToIntFunction<String> length = String::length;
// Creation succeeds. length.applyAsInt(null) would throw later.
```

A bound receiver expression is evaluated once, when the method reference is created:

```java
List<String> original = new ArrayList<>();
List<String> target = original;
Consumer<String> add = target::add;
target = new ArrayList<>();
add.accept("Ada");
System.out.println(original); // [Ada]
System.out.println(target);   // []
```

The reference retains the original object. Unlike a lambda that captures local `target`, this reference does not require `target` to remain effectively final: the receiver expression has already been evaluated.

Similarly, `getService()::run` calls `getService()` once during reference creation, while `() -> getService().run()` calls it on each invocation. `System.out::println` binds the current output stream; `s -> System.out.println(s)` reads `System.out` when invoked.

## 9. Checked exceptions

A reference does not bypass checked-exception rules.

```java
// Imports: java.nio.file.Files, java.nio.file.Path,
//          java.io.IOException
@FunctionalInterface
interface FileReader {
    byte[] read(Path path) throws IOException;
}

// Inside a method:
FileReader reader = Files::readAllBytes;
// Calling reader.read(path) requires handling or declaring IOException.
```

`Function<Path, byte[]> reader = Files::readAllBytes;` does not compile because `Function.apply` does not declare `IOException`.

Use a suitable throwing interface or a lambda/helper method that catches the exception and handles or wraps it meaningfully. A method reference cannot contain its own `try/catch` block. Unchecked exceptions propagate normally.

## 10. Collections, streams, and comparators

These snippets use `java.util` and `java.util.stream` types:

```java
List<String> names = new ArrayList<>(Arrays.asList("Grace", "Ada", "Alan"));
names.sort(Comparator.comparingInt(String::length)
        .thenComparing(Comparator.naturalOrder()));
names.forEach(System.out::println); // Ada, Alan, Grace

List<Integer> lengths = names.stream()
        .map(String::length)
        .collect(Collectors.toList());
System.out.println(lengths); // [3, 4, 5]

int total = Arrays.asList(2, 4, 6).stream().reduce(0, Integer::sum);
System.out.println(total); // 12
```

Use `mapToInt(String::length)` when a primitive stream is more appropriate. Intermediate stream processing remains lazy until a terminal operation. Method references do not change stream ordering, exception behavior, or thread-safety requirements.

Useful patterns include:

```java
names.removeIf(String::isEmpty);
names.replaceAll(String::trim);
Predicate<String> missing = Objects::isNull;
BiConsumer<String, Integer> printEntry = (key, value) ->
        System.out.println(key + "=" + value);
```

Keep the final lambda because it combines two inputs into a formatted message. A method reference cannot directly insert arbitrary formatting logic.

## 11. References to this and super

Inside an instance method, `this::method` binds the current object. `super::method` can deliberately select an inherited implementation instead of an override.

```java
class Parent {
    void greet() {
        System.out.println("Parent greeting");
    }
}

class Child extends Parent {
    @Override
    void greet() {
        System.out.println("Child greeting");
    }

    void demonstrate() {
        Runnable current = this::greet;
        Runnable inherited = super::greet;
        current.run();   // Child greeting
        inherited.run(); // Parent greeting
    }
}
```

These forms require an appropriate instance context. An accessible default interface method can also be referenced with `InterfaceName.super::method` where Java permits a qualified interface-super call.

## 12. When a lambda is clearer or necessary

Use a lambda when you need to adapt arguments, add constants, branch, chain calls, or handle exceptions locally.

```java
Function<String, String> uppercase = text -> text.toUpperCase(Locale.ROOT);
Function<String, Integer> trimmedLength = text -> text.trim().length();
Predicate<Integer> adult = age -> age >= 18;
BiFunction<String, String, String> reversedConcat = (a, b) -> b.concat(a);
```

`String::toUpperCase` for a one-input function would call the no-argument method, which uses the default locale. It is not equivalent to explicitly using `Locale.ROOT`.

There is no syntax such as `String::toUpperCase(Locale.ROOT)` to bind an ordinary argument. A bound reference binds the **receiver**, not arbitrary method parameters. A named helper method can provide such adaptation if reuse improves clarity.

## 13. Complete runnable example

Save as `MethodReferenceDemo.java`:

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class MethodReferenceDemo {
    static class Person {
        private final String name;
        private final int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        String getName() {
            return name;
        }

        int getAge() {
            return age;
        }
    }

    public static void main(String[] args) {
        // Static method
        ToIntFunction<String> parse = Integer::parseInt;
        System.out.println(parse.applyAsInt("42"));

        // Bound instance method
        Consumer<String> print = System.out::println;
        print.accept("Hello, references!");

        // Unbound instance method
        ToIntFunction<String> length = String::length;
        System.out.println(length.applyAsInt("Java"));

        // Constructor reference
        BiFunction<String, Integer, Person> personFactory = Person::new;
        Supplier<List<Person>> listFactory = ArrayList::new;
        List<Person> people = listFactory.get();
        people.add(personFactory.apply("Grace", 30));
        people.add(personFactory.apply("Ada", 25));
        people.add(personFactory.apply("Alan", 30));

        people.sort(Comparator.comparingInt(Person::getAge)
                .thenComparing(Person::getName));
        List<String> names = people.stream()
                .map(Person::getName)
                .collect(Collectors.toList());
        print.accept(names.toString());

        // Array constructor reference
        IntFunction<String[]> arrayFactory = String[]::new;
        System.out.println(arrayFactory.apply(3).length);
        String[] nameArray = names.stream().toArray(String[]::new);
        print.accept(Arrays.toString(nameArray));
    }
}
```

Compile and run:

```shell
javac MethodReferenceDemo.java
java MethodReferenceDemo
```

Expected output:

```text
42
Hello, references!
4
[Ada, Alan, Grace]
3
[Ada, Alan, Grace]
```

## 14. Common mistakes and advanced boundaries

| Mistake | Explanation or fix |
| --- | --- |
| Writing `Integer::parseInt()` | Reference syntax has no call parentheses: `Integer::parseInt` |
| Treating `String::length` as a static call | The functional input supplies the instance receiver |
| Omitting the target type | Declare a compatible functional interface |
| Assuming creation invokes the referenced method | Invoke the interface method; receiver expressions are evaluated earlier |
| Binding a null receiver | Bound instance reference creation throws immediately |
| Ignoring checked exceptions | The target interface must permit them or a wrapper must handle them |
| Replacing a lambda without checking evaluation timing | Bound receiver selection can change behavior |
| Assuming method references are faster | Choose readability; performance depends on the implementation and workload |
| Using stateful references in parallel streams | Bound objects can still contain unsafe shared mutable state |

Additional boundaries:

- A reference follows ordinary accessibility rules; `::` does not grant access to private methods from unrelated classes.
- Instance-method dispatch normally still honors overriding. A reference to a parent type does not generally freeze the implementation to the parent; explicit `super` has different semantics.
- Overload selection occurs at compile time using the target type; ordinary virtual dispatch occurs when the chosen instance method is invoked.
- A bound reference retains its receiver, potentially extending that object's lifetime.
- Do not depend on object identity for reference expressions, or use them as locks. A fresh functional object is not guaranteed for every evaluation.
- A reference can target a serializable functional interface, but captured state and long-term compatibility still matter. Avoid treating serialized behavior as durable application data.
- A reference is not reflection and is not an untyped function pointer. It implements a statically checked functional-interface contract.

## 15. Practice questions and hints

1. Replace `s -> Integer.parseInt(s)` with a reference targeting `ToIntFunction<String>`.
2. Represent `s -> s.length()` using both `Function<String, Integer>` and `ToIntFunction<String>`.
3. Create a `BiPredicate<String, String>` for checking whether the first string starts with the second.
4. Use a constructor reference to create a new list on every supplier invocation.
5. Convert a stream of names into a `String[]` using an array constructor reference.
6. Sort people by age, then name, using getter references.
7. Explain why `Supplier<Integer> length = String::length;` fails.
8. Compare the failure timing of `text::length` and `() -> text.length()` when `text` is null.
9. Explain why `Files::readAllBytes` cannot directly target `Function<Path, byte[]>`.
10. Keep locale-independent uppercase conversion explicit. Explain why `String::toUpperCase` is not a direct replacement.

**Hints:** 1. `Integer::parseInt`; 2. `String::length`; 3. `String::startsWith`; 4. `ArrayList::new`; 5. `String[]::new`; 6. `comparingInt(...).thenComparing(...)`; 7. An unbound receiver needs an input; 8. Bound creation versus later invocation; 9. Checked exception mismatch; 10. The no-argument method uses the default locale.

## Completion checklist

- [ ] I can identify and write all four main forms.
- [ ] I understand how unbound references receive their receiver.
- [ ] I can select a functional interface with matching inputs and result.
- [ ] I understand constructor and array constructor references.
- [ ] I can explain binding time, null behavior, and checked exceptions.
- [ ] I can use references in sorting, stream mapping, and factories.
- [ ] I can recognize when a lambda is clearer or has different semantics.
- [ ] I can explain and run the complete example.

## Official references

- [Dev.java: Writing lambda expressions as method references](https://dev.java/learn/language/fp/lambdas/method-references/) — guided examples.
- [Oracle Java Tutorials: Method references](https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html) — introduction and the main forms.
- [Java Language Specification, section 15.13](https://docs.oracle.com/javase/specs/jls/se25/html/jls-15.html#jls-15.13) — method-reference typing and evaluation rules.
- [Java API: java.util.function](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/function/package-summary.html) — functional-interface contracts.

---

[← 151. Lambda expressions](151_Lambda_expressions.md) | [Course Index](../README.md) | [153. Predicate →](153_Predicate.md)
