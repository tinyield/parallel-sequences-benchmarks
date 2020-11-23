package com.github.tiniyield.sequences.benchmarks.concurrency.all.match;

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
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static com.github.tiniyield.sequences.benchmarks.Constants.EVEN;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class AllMatchParallelBenchmark {

    @Param({"10000"})
    public int COLLECTION_SIZE;
    private List<Integer> data;

    private static Integer[] getAllEvenArray(int size) {
        Integer[] numbers = new Integer[size];
        Arrays.fill(numbers, EVEN);
        return numbers;
    }

    @Setup
    public void setup() {
        data = Arrays.asList(getAllEvenArray(COLLECTION_SIZE));
    }

    public boolean isEven(Integer value) {
        return value % 2 == 0;
    }

    public boolean isEveryEven(Stream<Integer> numbers) {
        return numbers.allMatch(this::isEven);
    }

    @Benchmark
    public void parallel(Blackhole bh) { // With Auxiliary Function
        bh.consume(isEveryEven(data.stream().parallel()));
    }

    @Benchmark
    public void sequential(Blackhole bh) { // With Auxiliary Function
        bh.consume(isEveryEven(data.stream()));
    }

}
