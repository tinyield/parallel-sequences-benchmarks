package com.github.tiniyield.sequences.benchmarks.concurrency.math.sine;

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
public class SlowSinBenchmark {

    @Param({"10000"})
    public int COLLECTION_SIZE;

    public Stream<Integer> data() {
        Integer[] numbers = new Integer[COLLECTION_SIZE];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }
        return Arrays.stream(numbers);
    }

    public double getSlowSinParallel() {
        return data()
                .parallel()
                .mapToDouble(Sine::slowSin)
                .reduce(Double.MIN_VALUE, Math::max);
    }

    public double getSlowSinSequential() {
        return data()
                .mapToDouble(Sine::slowSin)
                .reduce(Double.MIN_VALUE, Math::max);
    }


    @Benchmark
    public void parallel(Blackhole bh) {
        bh.consume(getSlowSinParallel());
    }

    @Benchmark
    public void sequential(Blackhole bh) {
        bh.consume(getSlowSinSequential());
    }
}
