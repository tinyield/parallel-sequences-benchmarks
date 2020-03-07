package com.github.tiniyield.jayield.benchmark.stream.benchmark;

import static com.codepoetics.protonpack.StreamUtils.zip;
import static com.github.tiniyield.jayield.benchmark.stream.SequenceBenchmarkStreamUtils.TO_ARTISTS_IN_TOP_TEN_WITH_SONGS_IN_TOP_TEN_BY_COUNTRY;
import static com.github.tiniyield.jayield.benchmark.stream.SequenceBenchmarkStreamUtils.TO_DATA_TRIPLET_BY_COUNTRY;
import static com.github.tiniyield.jayield.benchmark.stream.SequenceBenchmarkStreamUtils.TO_TOP_BY_COUNTRY_TRIPLET;

import java.util.List;
import java.util.stream.Stream;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import com.github.tiniyield.jayield.benchmark.common.SequenceBenchmarkUtils;
import com.github.tiniyield.jayield.benchmark.model.artist.Artist;
import com.github.tiniyield.jayield.benchmark.model.country.Country;
import com.github.tiniyield.jayield.benchmark.model.track.Track;
import com.github.tiniyield.jayield.benchmark.stream.SequenceBenchmarkStreamUtils;

public class ProtonpackBenchmark {

    public static Stream<Triplet<Country, Artist, Track>> zipTopArtistAndTrackByCountry() {
        return zip(
                SequenceBenchmarkStreamUtils.getArtists(),
                SequenceBenchmarkStreamUtils.getTracks(),
                TO_TOP_BY_COUNTRY_TRIPLET
        ).filter(SequenceBenchmarkUtils.distinctByKey(Triplet::getValue1));
    }

    public static Stream<Pair<Country, List<Artist>>> artistsInTopTenWithTopTenTracksByCountry() {
        return zip(
                SequenceBenchmarkStreamUtils.getArtists(),
                SequenceBenchmarkStreamUtils.getTracks(),
                TO_DATA_TRIPLET_BY_COUNTRY
        ).map(TO_ARTISTS_IN_TOP_TEN_WITH_SONGS_IN_TOP_TEN_BY_COUNTRY);
    }
}