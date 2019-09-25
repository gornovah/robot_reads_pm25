package com.seatcode.robotread.domain.model;

import com.google.maps.model.LatLng;

public class Record {
    private final Long id;
    private final long timestamp;
    private final LatLng location;
    private final int level;
    private final String source;

    public Record(Long id, int level, LatLng location, long timestamp, String source) {
        this.id = id;
        this.level = level;
        this.location = location;
        this.timestamp = timestamp;
        this.source = source;
    }

    public int getLevel() {
        return level;
    }

    public LatLng getLocation() {
        return location;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Long getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

}
