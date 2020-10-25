package com.github.tiniyield.sequences.benchmarks;

import com.github.tiniyield.sequences.benchmarks.operations.StreamOperations;

public abstract class AbstractSequenceOperationsBenchmark {
    protected StreamOperations stream;

    protected void init() {
        stream = new StreamOperations();
    }
}
