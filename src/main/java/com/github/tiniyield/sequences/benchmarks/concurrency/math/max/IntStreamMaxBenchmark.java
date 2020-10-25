package com.github.tiniyield.sequences.benchmarks.concurrency.math.max;

import com.github.tiniyield.sequences.benchmarks.operations.data.providers.number.IntegerDataProvider;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class IntStreamMaxBenchmark {
    @Param({"10000"})
    private int COLLECTION_SIZE;
    private IntegerDataProvider provider;

    @Setup
    public void setup() {
        provider = new IntegerDataProvider(COLLECTION_SIZE);
    }


    @Benchmark
    public void parallel(Blackhole bh) {
        bh.consume(IntStream.of(provider.unboxed()).parallel().reduce(Integer.MIN_VALUE, Math::max));
    }

    @Benchmark
    public void sequential(Blackhole bh) {
        bh.consume(IntStream.of(provider.unboxed()).reduce(Integer.MIN_VALUE, Math::max));
    }
}
