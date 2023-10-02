# **Getting Started**

### **Labseq Challenge**

### **Technologies**

- Java 17
- Quarkus
- Angular

### **Backend Folder Structure**

- `src/main/java/com/cv/api`: API endpoints
- `src/main/java/com/cv/dto`: DTOs
- `src/main/java/com/cv/service`: Service layer.
- `src/test/java`: Unit tests.
- 
### **Start-up**

```
docker compose up
```

### **Instructions**

The Swagger documentation can be accessed at: http://localhost:8080/q/swagger-ui/

The Angular Web GUI can be accessed at: http://localhost:4200/

#### Labseq Resource

```
  GET http://localhost:8080/labseq/10000
  
  curl -X 'GET' 'http://localhost:8080/labseq/10000' -H 'accept: application/json'
```

| Parâmetro | Tipo      | Descrição |
|:----------|:----------|:----------|
| `n`       | `Integer` | Value     |

### **Approach and Decisions**

####  Use of cache (HashMap) with incremental updates:
```
One of the key decisions made in the project was the implementation of a cache, represented by a HashMap, which is initially loaded when the server starts with initial values from 0 to 3.
The cache was introduced to optimize access to the "labseq" sequence values. Loading initial values into the cache allows the system to respond more quickly to initial requests without the need for lengthy calculations.
Whenever a value from the "labseq" sequence is requested, the cache is updated incrementally. This means that the system does not recalculate the entire sequence from scratch for each request but always starts from the maximum value already in the cache.
This approach saves computational resources and speeds up responses, as it avoids complete recalculations of the sequence every time a request is made. 
The cache has a limit of n = 300,000, and for n > 300,000, calculations are done in real-time.
```
####  DTO Response in String format due to exponential value:
```
The "labseq" sequence has the characteristic of growing exponentially as values progress. This means that the resulting numbers can be extremely large in magnitude.
It was decided that the response of the DTO (Data Transfer Object) carrying the values of the "labseq" sequence would be represented as a string instead of using a numeric type such as BigInteger.
The reason for this decision is that BigInteger may not be able to support such large numbers due to memory limitations or the maximum size of variables.
By representing the values as strings, you can accommodate responses of variable size without worrying about potential variable overflows or memory constraints.
Furthermore, using strings allows clients to receive the values in a readable and manipulable format, regardless of how large they are, facilitating the processing and display of results.
```
