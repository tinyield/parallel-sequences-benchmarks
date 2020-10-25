package com.github.tiniyield.sequences.benchmarks.operations;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertTrue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.tiniyield.sequences.benchmarks.operations.model.artist.Artist;
import com.github.tiniyield.sequences.benchmarks.operations.model.country.Country;
import com.github.tiniyield.sequences.benchmarks.operations.model.track.Track;
import com.github.tiniyield.sequences.benchmarks.operations.model.wrapper.Value;


public class StreamOperationsTest {

    private StreamOperations instance;
    private Stream<Pair<Country, Stream<Artist>>> artists;
    private Stream<Pair<Country, Stream<Track>>> tracks;
    private MockData mockData;
    private Stream<Value> values;
    private Stream<Integer> numbers;
    private Stream<Integer> otherNumbers;

    @BeforeMethod
    public void setup() {
        mockData = new MockData();
        instance = new StreamOperations();
        artists = mockData.getCountries().stream().map(c -> Pair.with(c, mockData.getArtists().stream()));
        tracks = mockData.getCountries().stream().map(c -> Pair.with(c, mockData.getTracks().stream()));
        values = mockData.getValues().stream();
        numbers = mockData.getNumbers().stream();
        otherNumbers = mockData.getNumbers().stream().sorted((t0, t1) -> t1 - t0);
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
