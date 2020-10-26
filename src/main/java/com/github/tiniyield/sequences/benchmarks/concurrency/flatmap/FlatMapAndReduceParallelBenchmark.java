package com.github.tiniyield.sequences.benchmarks.concurrency.flatmap;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class FlatMapAndReduceParallelBenchmark {

    @Param({"10000"})
    public int COLLECTION_SIZE;

    public Stream<Stream<Integer>> getStream() {
        List<Stream<Integer>> result = new ArrayList<>(COLLECTION_SIZE);
        for (int i = 0; i < COLLECTION_SIZE; i++) {
            result.add(Stream.of(1));
        }
        return result.stream();
    }

    public Stream<Stream<Integer>> getParallelStream() {
        List<Stream<Integer>> result = new ArrayList<>(COLLECTION_SIZE);
        for (int i = 0; i < COLLECTION_SIZE; i++) {
            result.add(Stream.of(1).parallel());
        }
        return result.stream().parallel();
    }

    public Integer flatMapAndReduce(Stream<Stream<Integer>> input) {
        return input.flatMap(i -> i).reduce(Integer::sum).orElseThrow(RuntimeException::new);
    }

    @Benchmark
    public void parallel(Blackhole bh) {
        bh.consume(flatMapAndReduce(getParallelStream()));
    }

    @Benchmark
    public void sequential(Blackhole bh) {
        bh.consume(flatMapAndReduce(getStream()));
    }

}
