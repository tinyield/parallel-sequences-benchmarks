package com.github.tiniyield.sequences.benchmarks.concurrency.math.max;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class IntStreamMaxBenchmarkTest {

    private IntStreamMaxBenchmark instance;

    @BeforeMethod
    public void setup() {
        instance = new IntStreamMaxBenchmark();
        instance.COLLECTION_SIZE = 10;
    }

    @Test()
    public void sameMax() {
        long sequential = instance.getMaxSequential();
        long parallel = instance.getMaxParallel();

        assertEquals(sequential, parallel);
    }
}
