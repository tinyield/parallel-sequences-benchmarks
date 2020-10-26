package com.github.tiniyield.sequences.benchmarks.concurrency.distinct;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DistinctBenchmarkTest {

    private DistinctBenchmark instance;

    @BeforeMethod
    public void setup() {
        instance = new DistinctBenchmark();
        instance.COLLECTION_SIZE = 10;
    }

    @Test()
    public void sameCount() {
        long sequential = instance.getCountSequential();
        long parallel = instance.getCountParallel();
        long parallelUnordered = instance.getCountParallelUnordered();

        assertEquals(sequential, parallel);
        assertEquals(sequential, parallelUnordered);
    }

}
