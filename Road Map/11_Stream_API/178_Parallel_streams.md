# 178. Parallel streams

[Coding practice →](../../code_practice/11_Stream_API/178_Parallel_streams.md)

**Section:** Stream API

**Level:** Beginner → Advanced

**Version:** Runnable examples use Java 8-compatible APIs. Later additions are labeled explicitly.

## Learning goals

- Decide whether parallel execution fits a workload.
- Use associative reduction and isolated accumulation correctly.
- Distinguish encounter order, thread identity, and result determinism.

**Example conventions:** Short snippets are independent and belong inside a method. Assume `java.util.*`, `java.util.function.*`, and `java.util.stream.*` imports unless shown otherwise. Inputs are non-null unless a snippet handles null explicitly. Numeric examples use small values that do not overflow.

## 1. Execution mode rather than a new data structure

A parallel stream permits a pipeline to split source work into partitions and combine their results. `collection.parallelStream()` requests this mode; `stream.parallel()` changes a stream's mode. A sequential stream uses ordinary sequential processing.

```java
long sequential = LongStream.rangeClosed(1, 1000).sum();
long parallel = LongStream.rangeClosed(1, 1000).parallel().sum();
System.out.println(sequential); // 500500
System.out.println(parallel);   // 500500
```

Parallel mode does not guarantee more speed, one task per element, or a particular thread count. The mode applies to pipeline execution, not separately scheduled regions between each call to `parallel()` and `sequential()`. Use `isParallel()` to inspect the selected mode.

## 2. Correct reductions and collectors

```java
List<String> result = Arrays.asList("Ada", "Grace", "Alan").parallelStream()
        .map(s -> s.toUpperCase(Locale.ROOT))
        .collect(Collectors.toList());
System.out.println(result); // [ADA, GRACE, ALAN]
```

The standard list collector combines separate partial containers and respects the ordered source. This is different from capturing one shared ordinary `ArrayList` and appending to it from `forEach`.

Reduction operators must be associative and use a neutral identity; three-argument reduction additionally needs compatible accumulation and combining rules. Subtraction, a non-neutral starting offset, and mutation of a shared reduce identity can fail those contracts. Floating-point rounding can make regrouped numerical results differ.

## 3. Ordering is not worker identity

```java
IntStream.rangeClosed(1, 3).parallel()
        .forEachOrdered(System.out::println); // 1, then 2, then 3
boolean found = IntStream.rangeClosed(1, 100).parallel()
        .anyMatch(n -> n == 42);
System.out.println(found); // true
```

Parallel `forEach` does not preserve encounter order. `forEachOrdered` orders terminal actions when an encounter order exists, but upstream work may remain parallel and actions need not run on one named worker.

`findFirst` preserves the required first result on ordered streams; `findAny` permits any matching element and can allow more flexibility. `unordered()` removes an ordering requirement; it does not shuffle values. Stateful ordered stages such as `sorted`, `distinct`, `limit`, and `skip` can need coordination or buffering.

## 4. Shared state, blocking, and failure

Prefer stateless, non-interfering callbacks. Do not modify the source during traversal, assume a captured mutable object is safe, or rely on `ThreadLocal` values being identical across workers. Making one counter atomic protects that counter operation, not a larger algorithm or ordering requirement.

Common JDK implementations use fork/join execution facilities for parallel streams, often involving the common pool and potentially the calling thread. Do not treat that as a dedicated application executor contract. Blocking I/O can occupy shared workers and interfere with other work; use an explicitly managed concurrency design when scheduling, backpressure, resource limits, or cancellation are central requirements.

If one callback fails, other tasks may already have produced effects. Parallel stream processing is not a transaction and supplies no automatic rollback or exactly-once side-effect guarantee.

## 5. Evaluate performance honestly

Parallel execution is more promising when input is large enough, partitioning is efficient, per-element work is substantial, and combining results is relatively cheap. Contention, boxing, poor source splitting, stateful ordering stages, and small inputs can erase benefits.

Measure representative workloads with an appropriate benchmark process, warmup, realistic data, and correct consumption of results. One wall-clock timing around a tiny example is not reliable evidence. Choose sequential code when it is clearer and already meets the requirement. Prefer an explicit task API when the workload is fundamentally asynchronous or blocking rather than a partitionable data reduction.

## 6. Complete runnable example

Save as `StreamLesson178.java`.

```java
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamLesson178 {
    public static void main(String[] args) throws Exception {
        long sequential = LongStream.rangeClosed(1, 1000).sum();
        long parallel = LongStream.rangeClosed(1, 1000).parallel().sum();
        System.out.println(sequential);
        System.out.println(parallel);
        System.out.println(Arrays.asList("Ada", "Grace", "Alan").parallelStream()
                .map(s -> s.toUpperCase(Locale.ROOT)).collect(Collectors.toList()));
        IntStream.rangeClosed(1, 3).parallel().forEachOrdered(System.out::println);
        System.out.println(IntStream.rangeClosed(1, 100).parallel().anyMatch(n -> n == 42));
    }
}
```

Compile and run:

```shell
javac StreamLesson178.java
java StreamLesson178
```

Expected output:

```text
500500
500500
[ADA, GRACE, ALAN]
1
2
3
true
```

## 7. Common mistakes

| Mistake | Better approach |
|---|---|
| Assuming parallel always means faster | Benchmark a representative workload. |
| Mutating a captured shared ArrayList | Use a correct collector or another explicit concurrency design. |
| Using non-associative reduction | Verify the algebraic contract. |
| Expecting parallel forEach order | Use forEachOrdered when order is required. |
| Treating a parallel stream as a dedicated executor | Use an explicit task/executor API when that control is needed. |

## 8. Practice exercises

1. Compare sequential and parallel sums for a bounded long range. **Hint:** Use fresh pipelines and equal inputs.
2. Collect uppercase names in source order. **Hint:** Standard ordered list collection.
3. Demonstrate ordered terminal actions without asserting thread names. **Hint:** forEachOrdered.
4. Explain why a shared list and a collector differ. **Hint:** Container ownership during accumulation.
5. Identify a workload unsuitable for automatic parallelization. **Hint:** Consider blocking work, small inputs, or strong ordering requirements.

## 9. Interview questions and answers

### 1. Does parallel mode promise a speedup?

No. Splitting, scheduling, buffering, combining, and contention have costs.

### 2. Does a parallel collector require every partial container to be thread-safe?

Not for a correct ordinary non-concurrent reduction with isolated containers. Shared or concurrent accumulation has different requirements.

### 3. Does forEachOrdered serialize the whole pipeline?

No. It orders terminal actions; upstream stages can still execute in parallel.

## Completion checklist

- [ ] I can choose execution mode deliberately.
- [ ] I can explain reduction and collector correctness.
- [ ] I can distinguish order from worker identity.
- [ ] I can evaluate shared-state, blocking, failure, and performance tradeoffs.

## Official references

- [Stream API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html)
- [Stream package guide](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html)

---

[← 177. joining()](177_joining.md) | [Course Index](../README.md) | [179. Lazy evaluation →](179_Lazy_evaluation.md)
