package com.seatcode.robotread;

import com.google.maps.model.LatLng;
import com.seatcode.robotread.domain.model.Record;

import java.util.LinkedHashMap;

public class MapForAverageGenerator {

    public static LinkedHashMap<Long, Record> generateMapForAverage() {
        Long id = 1L;
        LinkedHashMap<Long, Record> map = new LinkedHashMap<>();
        LatLng position = new LatLng(51.23241, -0.1223);
        int measure = 120;
        long instant = 1528106219;
        Record record = new Record(id, measure, position, instant, "robot");
        map.put(1L, record);

        position = new LatLng(51.23242, -0.1224);
        measure = 130;
        instant = 1528106220;
        record = new Record(id++, measure, position, instant, "robot");
        map.put(2L, record);

        position = new LatLng(51.23332, -0.1244);
        measure = 140;
        instant = 1528108020;
        record = new Record(id++, measure, position, instant, "robot");
        map.put(3L, record);

        return map;
    }
}
