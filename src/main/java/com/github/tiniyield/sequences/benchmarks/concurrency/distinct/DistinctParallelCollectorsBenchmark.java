package com.github.tiniyield.sequences.benchmarks.concurrency.distinct;

import com.pivovarit.collectors.ParallelCollectors;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class DistinctParallelCollectorsBenchmark {
    @Param({"10000"})
    public int COLLECTION_SIZE;
    @Param({"2"})
    public int PARALLELISM;
    private ExecutorService executor;

    public Stream<Integer> data() {
        Integer[] numbers = new Integer[COLLECTION_SIZE];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }
        return Arrays.stream(numbers);
    }

    public int getSizeSequential() {
        return data()
                .distinct()
                .map(String::valueOf)
                .collect(Collectors.toSet())
                .size();
    }

    public CompletableFuture<Set<String>> getParallelCollectors() {
        return data().distinct()
                .collect(ParallelCollectors.parallel(String::valueOf, toSet(), executor, PARALLELISM));
    }

    public int getSizeParallel() {
        return data()
                .parallel()
                .distinct()
                .map(String::valueOf)
                .collect(Collectors.toSet())
                .size();
    }

    @Setup
    public void setup() {
        executor = Executors.newFixedThreadPool(PARALLELISM);
    }

    @TearDown
    public void tearDown() throws InterruptedException {
        executor.shutdown();
        executor.awaitTermination(60, TimeUnit.SECONDS);
    }


    @Benchmark
    public void parallelCollectors(Blackhole bh) {
        getParallelCollectors()
                .thenAccept(set -> bh.consume(set.size()));
    }

    @Benchmark
    public void parallel(Blackhole bh) {
        bh.consume(getSizeParallel());
    }

    @Benchmark
    public void sequential(Blackhole bh) {
        bh.consume(getSizeSequential());
    }
}
