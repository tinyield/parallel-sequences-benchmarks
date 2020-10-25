package com.github.tiniyield.sequences.benchmarks.operations.data.providers.number;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class NestedIntegerDataProvider {

    private final int size;

    public NestedIntegerDataProvider(int size) {
        this.size = size;
    }


    public Stream<Stream<Integer>> asStream() {
        List<Stream<Integer>> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(Stream.of(1));
        }
        return result.stream();
    }

    public Stream<Stream<Integer>> asParallelStream() {
        List<Stream<Integer>> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(Stream.of(1).parallel());
        }
        return result.stream().parallel();
    }

}
