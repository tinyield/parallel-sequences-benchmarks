package com.github.tiniyield.sequences.benchmarks.concurrency.math.sine;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SlowSinBenchmarkTest {

    private SlowSinBenchmark instance;

    @BeforeMethod
    public void setup() {
        instance = new SlowSinBenchmark();
        instance.COLLECTION_SIZE = 10;
    }

    @Test()
    public void sameSine() {
        double sequential = instance.getSlowSinSequential();
        double parallel = instance.getSlowSinParallel();

        assertEquals(sequential, parallel);
    }
}
