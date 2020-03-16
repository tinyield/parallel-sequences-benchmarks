package com.github.tiniyield.jayield.benchmark.alternative.sequence.jayield.benchmark;

import static com.github.tiniyield.jayield.benchmark.alternative.sequence.jayield.SequenceBenchmarkQueryUtils.TO_ARTISTS_IN_TOP_TEN_WITH_SONGS_IN_TOP_TEN_BY_COUNTRY;
import static com.github.tiniyield.jayield.benchmark.alternative.sequence.jayield.SequenceBenchmarkQueryUtils.TO_DATA_TRIPLET_BY_COUNTRY;
import static com.github.tiniyield.jayield.benchmark.alternative.sequence.jayield.SequenceBenchmarkQueryUtils.TO_TOP_BY_COUNTRY_TRIPLET;
import static com.github.tiniyield.jayield.benchmark.alternative.sequence.jayield.SequenceBenchmarkQueryUtils.getArtists;
import static com.github.tiniyield.jayield.benchmark.alternative.sequence.jayield.SequenceBenchmarkQueryUtils.getTracks;
import static com.github.tiniyield.jayield.benchmark.common.SequenceBenchmarkUtils.distinctByKey;

import java.util.List;

import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.jayield.Query;

import com.github.tiniyield.jayield.benchmark.model.artist.Artist;
import com.github.tiniyield.jayield.benchmark.model.country.Country;
import com.github.tiniyield.jayield.benchmark.model.track.Track;

public class QueryBenchmark {

    public static Query<Triplet<Country, Artist, Track>> zipTopArtistAndTrackByCountry() {
        return getArtists().zip(getTracks(), TO_TOP_BY_COUNTRY_TRIPLET)
                           .filter(distinctByKey(Triplet::getValue1));
    }

    public static Query<Pair<Country, List<Artist>>> artistsInTopTenWithTopTenTracksByCountry() {
        return getArtists().zip(getTracks(), TO_DATA_TRIPLET_BY_COUNTRY)
                           .map(TO_ARTISTS_IN_TOP_TEN_WITH_SONGS_IN_TOP_TEN_BY_COUNTRY);
    }
}
