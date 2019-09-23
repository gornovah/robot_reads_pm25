package com.seatcode.robotread.repository;

import com.seatcode.robotread.Record;

import java.util.HashMap;

public class MeasureRepository {

    private final HashMap<Long, Record> records;

    public MeasureRepository(HashMap<Long, Record> records) {

        this.records = records;
    }

    public void save(Record record) {
        records.put(record.getId(), record);
    }

    public Record load() {
        throw new UnsupportedOperationException();
    }
}
