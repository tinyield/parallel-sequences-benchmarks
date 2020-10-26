package com.github.tiniyield.sequences.benchmarks.concurrency.first;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.github.tiniyield.sequences.benchmarks.Constants.EVEN;
import static com.github.tiniyield.sequences.benchmarks.Constants.ODD;
import static org.testng.Assert.assertEquals;

public class FindFirstInEndParallelBenchmarkTest {

    private FindFirstInEndParallelBenchmark instance;

    @BeforeMethod
    public void setup() {
        instance = new FindFirstInEndParallelBenchmark();
        instance.COLLECTION_SIZE = 10;
        instance.init();
    }

    @Test
    public void testOddInTheEnd() {
        List<Integer> actual = instance.getAllEvenExceptEnd();
        assertEquals(actual.get(actual.size() - 1).intValue(), ODD);

        for (int i = 0; i < actual.size() - 2; i++) {
            assertEquals(actual.get(i).intValue(), EVEN);
        }
    }

    @Test
    public void testFindFirstSequential() {
        assertEquals(instance.findFirstSequential().intValue(), ODD);
    }

    @Test
    public void testFindFirstParallel() {
        assertEquals(instance.findFirstParallel().intValue(), ODD);
    }
}
