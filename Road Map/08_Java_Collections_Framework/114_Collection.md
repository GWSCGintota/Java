# 114. Collection

**Section:** Java Collections Framework  
**Level:** Beginner → Intermediate

 ##1. Collection Interface
  - The Collection interface is the interface which is implemented by all the classes in the collection framework.
  - It is the root interface for most collection types.
  ```
 import java.util.*;  
   
 public class CollectionExample {  
     public static void main(String[] args) {  
         Collection<String> list = new ArrayList<>();  
   
         list.add("Rahul");  
         list.add("Priya");  
         list.add("Amit");  
   
         for (String name : list) {  
             System.out.println(name);  
         }  
     }  
 }  
 
 ```
 
 **Out put**
 ```
 Rahul
 Priya
 Amit
 ```

 ##2. Collections
 `Collections` is a utility class.
 
 It contains static methods such as:
 
 ```java
 Collections.sort(list);
 Collections.reverse(list);
 Collections.shuffle(list);
 Collections.max(list);
 Collections.min(list);

---

[← 113. Collections Framework](113_Collections_Framework.md) | [Course Index](../README.md) | [115. List →](115_List.md)
