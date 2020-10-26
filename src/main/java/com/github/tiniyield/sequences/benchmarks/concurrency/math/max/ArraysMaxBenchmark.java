package com.github.tiniyield.sequences.benchmarks.concurrency.math.max;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class ArraysMaxBenchmark {
    @Param({"10000"})
    public int COLLECTION_SIZE;

    public Stream<Integer> data() {
        Integer[] numbers = new Integer[COLLECTION_SIZE];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }
        return Arrays.stream(numbers);
    }

    public Integer getMaxParallel() {
        return data().parallel().reduce(Integer.MIN_VALUE, Math::max);
    }


    public Integer getMaxSequential() {
        return data().reduce(Integer.MIN_VALUE, Math::max);
    }

    @Benchmark
    public void parallel(Blackhole bh) {
        bh.consume(getMaxParallel());
    }
    @Benchmark
    public void sequential(Blackhole bh) {
        bh.consume(getMaxSequential());
    }
}
