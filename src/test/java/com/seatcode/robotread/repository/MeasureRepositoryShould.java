package com.seatcode.robotread.repository;

import com.google.maps.model.LatLng;
import com.seatcode.robotread.domain.model.Average;
import com.seatcode.robotread.domain.model.Record;
import com.seatcode.robotread.domain.model.Clock;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.LinkedHashMap;

import static com.seatcode.robotread.MapForAverageGenerator.generateMapForAverage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


public class MeasureRepositoryShould {

    private long id;

    @Before
    public void setUp() {
        id = 1L;
    }

    @Test
    public void save_read_pm25_level() {
        LinkedHashMap<Long, Record> map = new LinkedHashMap<>();
        LatLng position = new LatLng(51.23241, -0.1223);
        int measure = 140;
        long instant = 1528106219;
        Record record = new Record(id, measure, position, instant, "robot");
        Clock clock = mock(Clock.class);
        MeasureRepository measureRepository = new MeasureRepository(map, clock);

        measureRepository.save(record);

        assertThat(map.containsKey(id)).isTrue();
    }

    @Test
    public void load_the_average_of_records() {
        long instant = 1528108020;
        LatLng location = new LatLng(51.23332, -0.1244);
        Average expectedAverage = new Average(130, location, instant, "robot");

        LinkedHashMap<Long, Record> map = generateMapForAverage();
        Clock clock = mock(Clock.class);
        given(clock.instantNow()).willReturn(Instant.ofEpochMilli(1528108020));
        MeasureRepository measureRepository = new MeasureRepository(map, clock);

        Average average = measureRepository.load();

        assertThat(average).isEqualToComparingFieldByField(expectedAverage);
    }



}