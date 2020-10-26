package com.github.tiniyield.sequences.benchmarks.operations;

import com.github.tiniyield.sequences.benchmarks.operations.common.SequenceBenchmarkUtils;

import java.util.Optional;
import java.util.stream.Stream;

public class StreamOperations {

    public boolean isEveryEven(Stream<Integer> numbers) {
        return numbers.allMatch(SequenceBenchmarkUtils::isEven);
    }

    public Optional<Integer> findFirst(Stream<Integer> numbers) {
        return numbers.filter(SequenceBenchmarkUtils::isOdd).findFirst();
    }


}
