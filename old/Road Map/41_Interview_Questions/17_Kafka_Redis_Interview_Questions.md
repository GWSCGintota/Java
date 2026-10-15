# Kafka & Redis Interview Questions

Use these as revision prompts. Try answering each question aloud before opening the answer.

## 1. What is Kafka?

**Answer guide:** A distributed event-streaming platform based on durable ordered logs.

## 2. What is a Kafka topic?

**Answer guide:** A named stream of records.

## 3. What is a partition?

**Answer guide:** An ordered shard of a Kafka topic that enables scaling and parallelism.

## 4. What is a consumer group?

**Answer guide:** A set of consumers sharing topic partitions so each partition is processed by one group member at a time.

## 5. What is an offset?

**Answer guide:** The position of a record within a partition.

## 6. At-most-once vs at-least-once delivery?

**Answer guide:** At-most-once may lose messages but avoids retries; at-least-once retries and may produce duplicates.

## 7. What is Redis?

**Answer guide:** An in-memory data store commonly used for caching, counters, sessions, queues, and more.

## 8. What is TTL?

**Answer guide:** Time-to-live, after which a cached key expires.

## 9. What is cache invalidation?

**Answer guide:** Removing or updating stale cached data when the source of truth changes.

## 10. What is cache-aside?

**Answer guide:** The application checks cache first, loads from the database on a miss, then populates the cache.

---

[← Back to Java Roadmap](../README.md)
