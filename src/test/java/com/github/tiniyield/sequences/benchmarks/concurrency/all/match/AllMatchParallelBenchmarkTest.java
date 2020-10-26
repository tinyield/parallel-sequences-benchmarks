package com.github.tiniyield.sequences.benchmarks.concurrency.all.match;

import com.github.tiniyield.sequences.benchmarks.operations.TestDataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class AllMatchParallelBenchmarkTest {

    private AllMatchParallelBenchmark instance;

    @BeforeMethod
    public void setup() {
        instance = new AllMatchParallelBenchmark();
    }

    @Test
    public void testIsEveryEvenSuccess() {
        TestDataProvider<Integer> provider = new TestDataProvider<>(2, 2);
        assertTrue(instance.isEveryEven(provider.asStream()));
    }

    @Test
    public void testIsEveryEvenFailure() {
        TestDataProvider<Integer> provider = new TestDataProvider<>(2, 1);
        assertFalse(instance.isEveryEven(provider.asStream()));
    }
}
