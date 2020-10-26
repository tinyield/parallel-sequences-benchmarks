package com.github.tiniyield.sequences.benchmarks.concurrency.flatmap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;

public class FlatMapAndReduceParallelBenchmarkTest {

    private FlatMapAndReduceParallelBenchmark instance;

    @BeforeMethod
    public void setup() {
        instance = new FlatMapAndReduceParallelBenchmark();
    }

    @Test()
    public void sameInputData() {
        List<Stream<Integer>> sequential = instance.getStream().collect(Collectors.toList());
        List<Stream<Integer>> parallel = instance.getParallelStream().collect(Collectors.toList());

        assertEquals(sequential.size(), parallel.size());
        for (int i = 0; i < sequential.size(); i++) {
            List<Integer> s = sequential.get(i).collect(Collectors.toList());
            List<Integer> p = parallel.get(i).collect(Collectors.toList());

            assertEquals(s.size(), p.size());
            for (int j = 0; j < s.size(); j++) {
                assertEquals(s.get(j), p.get(j));
            }
        }
    }

    @Test()
    public void testFlatMapAndReduce() {
        Stream<Stream<Integer>> input = Stream.of(
                Stream.of(1, 2),
                Stream.of(3, 1),
                Stream.of(0, 7)
        );
        Integer expected = 14;
        Integer actual = instance.flatMapAndReduce(input);

        assertEquals(actual, expected);
    }

}
