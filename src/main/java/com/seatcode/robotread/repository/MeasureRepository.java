package com.seatcode.robotread.repository;

import com.google.maps.model.LatLng;
import com.seatcode.robotread.domain.model.Average;
import com.seatcode.robotread.domain.model.Record;
import com.seatcode.robotread.domain.model.Clock;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class MeasureRepository {

    private final LinkedHashMap<Long, Record> records;
    private Clock clock;

    public MeasureRepository(LinkedHashMap<Long, Record> records, Clock clock) {

        this.records = records;
        this.clock = clock;
    }

    public void save(Record record) {
        records.put(record.getId(), record);
    }

    public Average load() {
        Instant instantNow = clock.instantNow();
        Instant fifteenMinutesFromInstantNow = instantNow.minus(15, ChronoUnit.MINUTES);

        List<Map.Entry<Long, Record>> collect = records.entrySet()
                .stream()
                .filter(longRecordEntry -> longRecordEntry.getValue().getTimestamp() > fifteenMinutesFromInstantNow.toEpochMilli())
                .collect(Collectors.toList());
        OptionalDouble optionalAverage = collect
                .stream()
                .mapToInt(value -> value.getValue().getLevel()).average();
        Long orderKey = new ArrayList<>(records.keySet()).get(records.size() - 1);
        LatLng location = records.get(orderKey).getLocation();
        int averageLevel = (int) optionalAverage.getAsDouble();
        return new Average(averageLevel, location, instantNow.toEpochMilli(), "robot");

    }
}
