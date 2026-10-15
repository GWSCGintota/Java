# JVM Interview Questions

Use these as revision prompts. Try answering each question aloud before opening the answer.

## 1. What are the main JVM memory areas?

**Answer guide:** Heap, thread stacks, metaspace, PC registers, and native method stacks.

## 2. Stack vs heap?

**Answer guide:** Stacks hold method frames and local variables per thread; heap stores shared objects.

## 3. What is garbage collection?

**Answer guide:** Automatic reclamation of heap objects that are no longer reachable.

## 4. What is a memory leak in Java?

**Answer guide:** Objects remain reachable unintentionally, preventing garbage collection.

## 5. What is a class loader?

**Answer guide:** A component responsible for loading classes into the JVM.

## 6. What is JIT compilation?

**Answer guide:** Runtime compilation of frequently executed bytecode into optimized native machine code.

## 7. What is metaspace?

**Answer guide:** Native memory used mainly for class metadata.

## 8. Strong vs weak reference?

**Answer guide:** Strong references keep objects alive; weak references allow collection when no strong references remain.

## 9. What is a GC pause?

**Answer guide:** A period in which application threads may be stopped so the JVM can perform certain garbage-collection work.

## 10. What is profiling?

**Answer guide:** Measuring runtime behavior such as CPU usage, allocations, memory, threads, and hotspots.

---

[← Back to Java Roadmap](../README.md)
