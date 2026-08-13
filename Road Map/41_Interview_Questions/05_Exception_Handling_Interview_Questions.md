# Exception Handling Interview Questions

Use these as revision prompts. Try answering each question aloud before opening the answer.

## 1. Checked vs unchecked exceptions?

**Answer guide:** Checked exceptions must be handled or declared; unchecked exceptions are subclasses of RuntimeException.

## 2. throw vs throws?

**Answer guide:** `throw` actually throws an exception; `throws` declares possible exceptions in a method signature.

## 3. What is finally?

**Answer guide:** A block intended for cleanup that normally runs whether or not an exception occurs.

## 4. Can finally be skipped?

**Answer guide:** Yes in abnormal termination scenarios such as `System.exit()` or JVM failure.

## 5. What is try-with-resources?

**Answer guide:** A construct that automatically closes AutoCloseable resources.

## 6. Can you have multiple catch blocks?

**Answer guide:** Yes, ordered from more specific to more general exception types.

## 7. What is exception propagation?

**Answer guide:** An uncaught exception moves up the call stack until handled or until it terminates the thread.

## 8. Can you catch Error?

**Answer guide:** Technically yes, but serious JVM errors are generally not meant to be handled like normal application exceptions.

## 9. What is a custom exception?

**Answer guide:** An application-specific exception type created by extending Exception or RuntimeException.

## 10. Should exceptions be used for normal control flow?

**Answer guide:** Generally no; they are intended for exceptional situations.

---

[← Back to Java Roadmap](../README.md)
