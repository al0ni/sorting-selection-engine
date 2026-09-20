# DAA Assignment 1

**Student:** Alua Rakhimzhanova  
**Group:** SE-2509

This project contains MergeSort, QuickSort and QuickSelect. It also measures execution time, comparisons and recursion depth.

## Requirements

- Java 17
- Maven

## Run tests

```bash
mvn clean test
```

## Run benchmark

```bash
mvn -q compile exec:java
```

The benchmark tests four array sizes and three input types. Every test is repeated five times. The median results are saved in `results.csv`.

The `plots` folder contains three graphs created from the benchmark results:

- time vs n;
- recursion depth vs n;
- comparison ratio vs n.

QuickSelect uses a zero-based value of `k` and changes the order of the input array.
