package com.seatcode.robotread.repository;

import com.google.maps.model.LatLng;
import com.seatcode.robotread.domain.exceptions.RobotException;
import com.seatcode.robotread.domain.model.Average;
import com.seatcode.robotread.domain.model.Record;
import com.seatcode.robotread.domain.model.Clock;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The class that simulate the repository to
 * save the records, in this case we use a LinkedHashMap
 * to insert the records in order of reads
 *
 * @author despinosa
 */
public class MeasureRepository {

    private final LinkedHashMap<Long, Record> records;
    private final Clock clock;

    public MeasureRepository(Map<Long, Record> records, Clock clock) {

        this.records = (LinkedHashMap<Long, Record>) records;
        this.clock = clock;
    }

    public void save(Record record) {
        records.put(record.getId(), record);
    }

    public Average load() {
        Instant instantNow = clock.instantNow();
        Instant fifteenMinutesFromInstantNow = instantNow.minus(15, ChronoUnit.MINUTES);
        if (records.isEmpty()) {
            throw new RobotException("We haven't load any data. System stopped");
        }
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
