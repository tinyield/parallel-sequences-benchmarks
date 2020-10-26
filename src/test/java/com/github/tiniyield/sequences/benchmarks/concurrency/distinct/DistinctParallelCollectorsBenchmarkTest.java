package com.github.tiniyield.sequences.benchmarks.concurrency.distinct;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static org.testng.Assert.*;

public class DistinctParallelCollectorsBenchmarkTest {

    private DistinctParallelCollectorsBenchmark instance;

    @BeforeMethod
    public void setup() {
        instance = new DistinctParallelCollectorsBenchmark();
        instance.COLLECTION_SIZE = 10;
        instance.PARALLELISM = 1;
        instance.setup();
    }

    @Test()
    public void sameCount() {
        int sequential = instance.getSizeSequential();
        int parallel = instance.getSizeParallel();
        CompletableFuture<Set<String>> parallelCollectors = instance.getParallelCollectors();

        assertEquals(sequential, parallel);
        parallelCollectors.thenAccept(set -> assertEquals(sequential, set.size()));
    }

}
