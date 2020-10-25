package com.github.tiniyield.sequences.benchmarks.operations.data.providers;

import java.util.stream.Stream;

public interface ISequenceBenchmarkDataProvider<T> {
    Stream<T> asStream();
}
