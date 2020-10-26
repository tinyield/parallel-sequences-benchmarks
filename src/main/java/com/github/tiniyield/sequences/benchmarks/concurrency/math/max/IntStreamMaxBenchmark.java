package com.github.tiniyield.sequences.benchmarks.concurrency.math.max;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class IntStreamMaxBenchmark {
    @Param({"10000"})
    public int COLLECTION_SIZE;

    public IntStream data() {
        int[] numbers = new int[COLLECTION_SIZE];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }
        return IntStream.of(numbers);
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
