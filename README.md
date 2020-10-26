# Glossary
1. [Overview](#overview)
2. [Usage](#usage)
3. [Benchmarks Overview](#benchmarks-overview)
    1. [All Match](#all-match)
    2. [Distinct](#distinct)
    3. [First](#find-first)
    4. [FlatMap and Reduce](#flatmap-and-reduce)
    5. [SlowSin](#slow-sin)
    6. [Max](#max)
4. [Performance Comparison](#performance-comparison)

# Overview
In this document you'll find information on how to use the project in terms of how to run it in your local machine, a brief description of each benchmark's pipeline, and a performance comparison in performance increase relative to the sequential version of the algorithm.

# Usage
To run these benchmarks on you local machine just run:
```
mvn clean install
```
And then:
```
java -jar target/benchmarks.jar
```
# Benchmarks Overview
### All Match - `allMatch(sequence, predicate)`
Benchmarks the `allMatch()` operation when performed sequentially or with any number of threads.

Collection Sizes: [5,50,500,5000,50000,500000,5000000,50000000]
Active Processor Count: [1,2,4,8]

Pipeline:
```ignorelang
sequenceOfEvenIntegers -> allMatch(isEven)
```
### Distinct - `distinct(sequence)`
Benchmarks the `distinct` operation when performed sequentially or with any number of threads.

Collection Sizes: [5,50,500,5000,50000,500000]
Active Processor Count: [1,2,4,8]

Pipelines:
* Distinct:
```ignorelang
sequenceOfEvenIntegers -> distinct() -> count()
```
* Distinct Parallel Collectors:
```ignorelang
sequenceOfEvenIntegers -> distinct() -> collect() -> size()
```
### First - `first(sequence, predicate)`
Benchmarks the usage of the `findFirst()` operator. This benchmark was run with two types of Sequences:
1. One where the match would be found in the middle.
1. One where the match would be found in the last element.

Collection Sizes: [5,50,500,5000,50000,500000,5000000,50000000]
Active Processor Count: [1,2,4,8]

Pipeline:
```ignorelang
sequenceOfAllButOneEvenIntegers -> filter(isOdd) -> findFirst()
```
### FlatMap and Reduce
Benchmarks the `flatmap` and the `reduce` operations in conjunction, when performed sequentially or with any number of threads.

Collection Sizes: [5,50,500,5000,50000,500000,5000000,50000000]
Active Processor Count: [1,2,4,8]

```ignorelang
sequenceOfSequencesOfIntegers -> flatmap() -> reduce(sum)
```
### SlowSin
Benchmarks a private operation of [Apache Commons Math 3.6](http://home.apache.org/~luc/commons-math-3.6-RC2-site/jacoco/org.apache.commons.math3.util/FastMathCalc.java.html) in the class "FastMathCalc". The operation is called slowSin and for x between 0 and pi/4 it computes sine using Taylor expansion.


Collection Sizes: [5,50,500,5000,50000,500000,5000000,50000000]
Active Processor Count: [1,2,4,8]

```ignorelang
sequenceOfSequencesOfIntegers -> mapToDouble(slowSin) -> reduce(max)
```
### Max
Benchmarks the use of the operation `reduce` in conjunction with `Integer::max`, using as sources
`Arrays.stream()` and `IntStream.of`.

Collection Sizes: [5,50,500,5000,50000,500000,5000000,50000000]
Active Processor Count: [1,2,4,8]

```ignorelang
sourceOfIntegers -> reduce(max)
```
# Performance Comparison
[Under Construction]

