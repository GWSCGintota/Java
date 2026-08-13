# Generics Interview Questions

Use these as revision prompts. Try answering each question aloud before opening the answer.

## 1. Why use generics?

**Answer guide:** They provide compile-time type safety and reduce explicit casts.

## 2. What is type erasure?

**Answer guide:** Most generic type information is removed during compilation for JVM compatibility.

## 3. What is a generic class?

**Answer guide:** A class declared with type parameters, such as `class Box<T>`.

## 4. What is a generic method?

**Answer guide:** A method that declares its own type parameters.

## 5. What does ? mean in generics?

**Answer guide:** An unknown type wildcard.

## 6. What does ? extends T mean?

**Answer guide:** An upper-bounded wildcard used when reading values as T or its subtype.

## 7. What does ? super T mean?

**Answer guide:** A lower-bounded wildcard used when inserting T values safely.

## 8. What is PECS?

**Answer guide:** Producer Extends, Consumer Super.

## 9. Can you create new T()?

**Answer guide:** Not directly, because type erasure removes the concrete runtime type.

## 10. Can generics use primitives?

**Answer guide:** No. Use wrapper classes such as Integer instead of int.

---

[← Back to Java Roadmap](../README.md)
