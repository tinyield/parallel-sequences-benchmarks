package com.github.tiniyield.sequences.benchmarks.concurrency.first;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static com.github.tiniyield.sequences.benchmarks.Constants.EVEN;
import static com.github.tiniyield.sequences.benchmarks.Constants.ODD;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class FindFirstInEndParallelBenchmark {

    @Param({"10000"})
    public int COLLECTION_SIZE;
    private List<Integer> data;

    public List<Integer> getAllEvenExceptEnd() {
        Integer[] numbers = new Integer[COLLECTION_SIZE];
        Arrays.fill(numbers, EVEN);
        numbers[numbers.length - 1] = ODD;
        return Arrays.asList(numbers);
    }

    public boolean isOdd(Integer value) {
        return value % 2 != 0;
    }

    public Optional<Integer> findFirst(Stream<Integer> numbers) {
        return numbers.filter(this::isOdd).findFirst();
    }

    @Setup
    public void init() {
        data = getAllEvenExceptEnd();
    }

    public Integer findFirstSequential() {
        return findFirst(data.stream()).orElseThrow();
    }

    public Integer findFirstParallel() {
        return findFirst(data.stream().parallel()).orElseThrow();
    }

    @Benchmark
    public void parallel(Blackhole bh) { // With Auxiliary Function
        bh.consume(findFirstParallel());
    }

    @Benchmark
    public void sequential(Blackhole bh) { // With Auxiliary Function
        bh.consume(findFirstSequential());
    }

}
