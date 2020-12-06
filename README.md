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

In this document you'll find information on how to use the project in terms of how to run it in your local machine, a
brief description of each benchmark's pipeline, and a performance comparison in performance increase relative to the
sequential version of the algorithm.

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

Benchmarks the `flatmap` and the `reduce` operations in conjunction, when performed sequentially or with any number of
threads.

Collection Sizes: [5,50,500,5000,50000,500000,5000000,50000000]
Active Processor Count: [1,2,4,8]

```ignorelang
sequenceOfSequencesOfIntegers -> flatmap() -> reduce(sum)
```

### SlowSin

Benchmarks a private operation
of [Apache Commons Math 3.6](http://home.apache.org/~luc/commons-math-3.6-RC2-site/jacoco/org.apache.commons.math3.util/FastMathCalc.java.html)
in the class "FastMathCalc". The operation is called slowSin and for x between 0 and pi/4 it computes sine using Taylor
expansion.

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

The results presented in the following table are the gain (or loss) attained bye using `Stream.parallel()` when
comparing to sequential processing of the same benchmark using the Stream _API_. In other words, the value of sequential
processing for all benchmarks is 1.0, and all the results presented here are in relation to that basis.

| Benchmark           | Time Complexity | Threads | 5 Elements | 50 Elements | 500 Elements | 5000 Elements | 50000 Elements | 500000 Elements |
| ------------------- | --------------- | ------- | ---------- | ----------- | ------------ | ------------- | -------------- | --------------- |
| All Match           | Linear          | 2       | \-1,0      | \-0,9       | \-0,7        | \-0,5         | 0,4            | 0,7             |
| | | 4                   | \-1,0           | \-1,0   | \-0,9      | \-0,5       | 0,7          | 1,9           |
| | | 8                   | \-1,0           | \-1,0   | \-1,0      | \-0,7       | 0,7          | 2,8           |
||
| Distinct            | Linear          | 2       | \-0,9      | \-0,9       | \-0,9        | \-0,8         | \-0,7          | \-0,9           |
| | | 4                   | \-1,0           | \-1,0   | \-0,8      | \-0,7       | \-0,7        | \-0,8         |
| | | 8                   | \-1,0           | \-1,0   | \-0,8      | \-0,7       | \-0,6        | \-0,8         |
||
| Distinct & Collect  | Linear          | 2       | \-1,0      | \-0,9       | \-0,8        | \-0,8         | \-0,7          | \-0,7           |
| | | 4                   | \-1,0           | \-0,9   | \-0,8      | \-0,7       | \-0,6        | \-0,6         |
| | | 8                   | \-1,0           | \-1,0   | \-0,8      | \-0,7       | \-0,6        | \-0,6         |
||
| First In the Middle | Linear          | 2       | \-1,0      | \-1,0       | \-0,9        | \-0,8         | \-0,5          | \-0,4           |
| | | 4                   | \-1,0           | \-1,0   | \-1,0      | \-0,8       | \-0,3        | 0,1           |
| | | 8                   | \-1,0           | \-1,0   | \-1,0      | \-0,8       | \-0,3        | 0,3           |
||
| First In the End    | Linear          | 2       | \-1,0      | \-0,9       | \-0,8        | \-0,7         | 0,0            | 0,2             |
| | | 4                   | \-1,0           | \-1,0   | \-0,9      | \-0,7       | 0,5          | 1,1           |
| | | 8                   | \-1,0           | \-1,0   | \-1,0      | \-0,8       | 0,5          | 1,3           |
||
| Flatmap & Reduce    | Linear          | 2       | \-0,9      | \-0,7       | \-0,5        | 0,0           | 0,2            | 0,2             |
| | | 4                   | \-1,0           | \-0,9   | \-0,6      | 0,2         | 0,2          | 0,3           |
| | | 8                   | \-1,0           | \-1,0   | \-0,7      | 0,1         | 0,2          | 0,3           |
||
| ArraysMax           | Linear          | 2       | \-0,9      | \-0,9       | \-0,6        | \-0,2         | 0,3            | 0,1             |
| | | 4                   | \-1,0           | \-1,0   | \-0,8      | \-0,1       | 0,4          | 0,2           |
| | | 8                   | \-1,0           | \-1,0   | \-0,9      | \-0,3       | 0,4          | 0,2           |
||
| IntStream Max       | Linear          | 2       | \-1,0      | \-0,9       | \-0,7        | \-0,4         | 0,0            | 0,3             |
| | | 4                   | \-1,0           | \-1,0   | \-0,9      | \-0,7       | 0,1          | 0,5           |
| | | 8                   | \-1,0           | \-1,0   | \-1,0      | \-0,8       | 0,0          | 0,6           |
||
| Slow Sin            | Linear          | 2       | \-0,6      | \-0,2       | 0,7          | 1,0           | 1,0            | 1,0             |
| | | 4                   | \-0,9           | \-0,2   | 1,5        | 2,2         | 2,3          | 2,2           |
| | | 8                   | \-0,9           | \-0,4   | 1,7        | 3,3         | 3,5          | 3,6           |

These benchmarks clearly show that `Stream.parallel()` is not always advantageous, it becomes advantageous the more
elements we are processing, the more computationally heavy processing an element is and the more threads we have,
although it also seems to have a threshold from which point it start to be an advantage.

After further investigating this point we came across an article by Brian Goetz titled
["Optimize stream pipelines for parallel processing"](https://developer.ibm.com/languages/java/articles/j-java-streams-5-brian-goetz/)
, namely the section about "The NQ Model", where N is the number of data elements, and Q is the amount of work performed
per element. This section describes how we are more likely to see performance gains due to parallelization the larger
the product of N*Q, going as far as to say that to see any significant speedups the relation must be N*Q > 10000. With
that said we didn't observe this relation in pipelines where the computational cost per element was too low, in other
words, although it is true that N*Q > 10000, either the threshold is too low for some pipelines, or we are missing some
minimum values for N and for Q.

In regards to the number of threads, we did not see the speedup occurring with lower values of N*Q with the increment of
threads, we did however see a larger speedup the more threads we had working on the pipeline.
