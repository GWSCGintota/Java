# Multithreading & Concurrency Interview Questions

Use these as revision prompts. Try answering each question aloud before opening the answer.

## 1. Process vs thread?

**Answer guide:** A process has its own memory space; threads share process memory while executing independently.

## 2. What is a race condition?

**Answer guide:** The result depends on unpredictable timing of concurrent accesses to shared mutable state.

## 3. What does synchronized do?

**Answer guide:** It provides mutual exclusion and establishes visibility guarantees around a monitor lock.

## 4. What is volatile?

**Answer guide:** It guarantees visibility of reads/writes to a variable but does not make compound operations atomic.

## 5. What is deadlock?

**Answer guide:** Two or more threads wait forever for locks held by each other.

## 6. What is starvation?

**Answer guide:** A thread is repeatedly denied access to resources or CPU time.

## 7. Runnable vs Callable?

**Answer guide:** Runnable returns no value; Callable can return a value and throw checked exceptions.

## 8. What is ExecutorService?

**Answer guide:** An abstraction for submitting tasks to managed thread pools.

## 9. Future vs CompletableFuture?

**Answer guide:** Future represents a pending result; CompletableFuture supports fluent asynchronous composition.

## 10. What is an atomic class?

**Answer guide:** A class such as AtomicInteger providing lock-free atomic operations for common use cases.

## 11. What is ReentrantLock?

**Answer guide:** An explicit lock with features such as tryLock, interruptible locking, and fairness options.

## 12. What is a semaphore?

**Answer guide:** A concurrency primitive controlling access using a fixed number of permits.

## 13. What are virtual threads?

**Answer guide:** Lightweight JVM-managed threads designed to make high-concurrency blocking workloads easier to scale.

## 14. Does adding more threads always improve performance?

**Answer guide:** No. Contention, scheduling overhead, memory pressure, and workload type matter.

## 15. What is the Java Memory Model?

**Answer guide:** The specification defining visibility, ordering, and synchronization behavior between threads.

---

[← Back to Java Roadmap](../README.md)
