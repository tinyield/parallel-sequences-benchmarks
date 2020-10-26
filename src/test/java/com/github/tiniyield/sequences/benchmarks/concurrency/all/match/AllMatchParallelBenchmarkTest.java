package com.github.tiniyield.sequences.benchmarks.concurrency.all.match;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class AllMatchParallelBenchmarkTest {

    private AllMatchParallelBenchmark instance;

    @BeforeMethod
    public void setup() {
        instance = new AllMatchParallelBenchmark();
        instance.COLLECTION_SIZE = 10;
        instance.setup();
    }

    @Test
    public void testIsEveryEvenSuccess() {
        assertTrue(instance.isEveryEven(Stream.of(2, 2)));
    }

    @Test
    public void testIsEveryEvenFailure() {
        assertFalse(instance.isEveryEven(Stream.of(2, 1)));
    }
}
