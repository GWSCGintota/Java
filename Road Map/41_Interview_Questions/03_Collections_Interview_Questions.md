# Collections Interview Questions

Use these as revision prompts. Try answering each question aloud before opening the answer.

## 1. List vs Set vs Map?

**Answer guide:** List is ordered and allows duplicates; Set stores unique elements; Map stores key-value pairs.

## 2. ArrayList vs LinkedList?

**Answer guide:** ArrayList is usually better for random access; LinkedList can be useful for frequent insertions/removals at known positions but has higher overhead.

## 3. HashMap vs TreeMap?

**Answer guide:** HashMap provides average O(1) lookup without ordering; TreeMap provides sorted keys with O(log n) operations.

## 4. HashSet vs TreeSet?

**Answer guide:** HashSet is hash-based and unordered; TreeSet is sorted and tree-based.

## 5. How does HashMap work internally?

**Answer guide:** It hashes the key, chooses a bucket, then uses equality checks to find the matching entry; collisions are handled within buckets.

## 6. Why must equals() and hashCode() agree?

**Answer guide:** Equal objects must have the same hash code so hash-based collections can locate them correctly.

## 7. What is load factor?

**Answer guide:** A threshold controlling when a hash-based collection resizes; HashMap commonly defaults to 0.75.

## 8. What is ConcurrentHashMap?

**Answer guide:** A thread-safe map designed for concurrent access without locking the whole map for most operations.

## 9. What is fail-fast iteration?

**Answer guide:** An iterator may throw `ConcurrentModificationException` when the collection is structurally modified during iteration.

## 10. Comparable vs Comparator?

**Answer guide:** Comparable defines natural ordering inside the class; Comparator defines external/custom ordering.

## 11. Queue vs Deque?

**Answer guide:** Queue usually follows FIFO; Deque supports insertion/removal at both ends.

## 12. What is PriorityQueue?

**Answer guide:** A heap-based queue that retrieves elements according to priority/order rather than insertion order.

## 13. Can HashMap contain null?

**Answer guide:** Yes, one null key and multiple null values are allowed.

## 14. Can TreeMap contain null keys?

**Answer guide:** Usually no when natural ordering is used, because keys must be comparable.

## 15. What are immutable collections?

**Answer guide:** Collections that cannot be structurally modified after creation, such as those made with `List.of(...)`.

---

[← Back to Java Roadmap](../README.md)
