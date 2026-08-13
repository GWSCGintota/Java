# Lambdas, Streams & Optional Interview Questions

Use these as revision prompts. Try answering each question aloud before opening the answer.

## 1. What is a functional interface?

**Answer guide:** An interface with exactly one abstract method.

## 2. What is a lambda expression?

**Answer guide:** A concise implementation of a functional interface.

## 3. What is a method reference?

**Answer guide:** A shorthand such as `System.out::println` when an existing method matches the required function shape.

## 4. Intermediate vs terminal stream operations?

**Answer guide:** Intermediate operations return another stream; terminal operations produce a result or side effect.

## 5. What does map() do?

**Answer guide:** Transforms each element into another value.

## 6. What does flatMap() do?

**Answer guide:** Transforms each element into a stream-like value and flattens the results.

## 7. What does filter() do?

**Answer guide:** Keeps elements matching a predicate.

## 8. What is lazy evaluation in streams?

**Answer guide:** Intermediate operations are not executed until a terminal operation is requested.

## 9. What does reduce() do?

**Answer guide:** Combines stream elements into one result.

## 10. When should Optional be used?

**Answer guide:** Primarily as a return type when absence is a valid result.

## 11. orElse() vs orElseGet()?

**Answer guide:** `orElse()` eagerly evaluates its fallback; `orElseGet()` evaluates lazily.

## 12. Are streams reusable?

**Answer guide:** No. A stream should be considered consumed after a terminal operation.

---

[← Back to Java Roadmap](../README.md)
