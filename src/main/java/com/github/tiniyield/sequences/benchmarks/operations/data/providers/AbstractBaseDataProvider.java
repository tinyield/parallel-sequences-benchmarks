package com.github.tiniyield.sequences.benchmarks.operations.data.providers;

import java.util.stream.Stream;

public abstract class AbstractBaseDataProvider<T> implements ISequenceBenchmarkDataProvider<T> {

    protected abstract T[] data();

    @Override
    public Stream<T> asStream() {
        return Stream.of(data());
    }
}
