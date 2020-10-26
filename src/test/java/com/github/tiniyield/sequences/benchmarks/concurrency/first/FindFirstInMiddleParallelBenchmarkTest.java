package com.github.tiniyield.sequences.benchmarks.concurrency.first;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.github.tiniyield.sequences.benchmarks.operations.common.SequenceBenchmarkConstants.EVEN;
import static com.github.tiniyield.sequences.benchmarks.operations.common.SequenceBenchmarkConstants.ODD;
import static org.testng.Assert.*;

public class FindFirstInMiddleParallelBenchmarkTest {

    private FindFirstInMiddleParallelBenchmark instance;

    @BeforeMethod
    public void setup() {
        instance = new FindFirstInMiddleParallelBenchmark();
        instance.COLLECTION_SIZE = 10;
        instance.init();
    }

    @Test
    public void testOddInTheEnd() {
        List<Integer> actual = instance.getAllEvenExceptMiddle();

        for (int i = 0; i < actual.size() - 1; i++) {
            if(i == ((actual.size() / 2) - 1)) {
                assertEquals(actual.get(i).intValue(), ODD);
            } else {
                assertEquals(actual.get(i).intValue(), EVEN);
            }
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
