package com.github.tiniyield.sequences.benchmarks.concurrency.all.match;

import com.github.tiniyield.sequences.benchmarks.AbstractSequenceOperationsBenchmark;
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

import static com.github.tiniyield.sequences.benchmarks.operations.common.SequenceBenchmarkConstants.EVEN;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class AllMatchParallelBenchmark extends AbstractSequenceOperationsBenchmark {

    @Param({"10000"})
    private int COLLECTION_SIZE;
    private List<Integer> data;

    private static Integer[] getAllEvenArray(int size) {
        Integer[] numbers = new Integer[size];
        Arrays.fill(numbers, EVEN);
        return numbers;
    }

    @Setup
    public void setup() {
        super.init();
        data = Arrays.asList(getAllEvenArray(COLLECTION_SIZE));
    }

    private boolean getStream() {
        return stream.isEveryEven(data.stream());
    }

    private boolean getStreamParallel() {
        return stream.isEveryEven(data.stream().parallel());
    }

    @Benchmark
    public void parallel(Blackhole bh) { // With Auxiliary Function
        bh.consume(getStreamParallel());
    }

    @Benchmark
    public void sequential(Blackhole bh) { // With Auxiliary Function
        bh.consume(getStream());
    }

}
