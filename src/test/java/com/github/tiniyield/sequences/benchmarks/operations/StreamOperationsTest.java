package com.github.tiniyield.sequences.benchmarks.operations;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;


public class StreamOperationsTest {

    private StreamOperations instance;

    @BeforeMethod
    public void setup() {
        instance = new StreamOperations();
    }

    @Test
    public void testFindFirstSuccess() {
        TestDataProvider<Integer> provider = new TestDataProvider<>(0, 1);
        assertTrue(instance.findFirst(provider.asStream()).isPresent());
    }

    @Test
    public void testFindFirstFailure() {
        TestDataProvider<Integer> provider = new TestDataProvider<>(2, 2);
        assertFalse(instance.findFirst(provider.asStream()).isPresent());
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
