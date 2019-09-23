package com.seatcode.robotread;

import com.google.maps.model.LatLng;

import java.util.StringJoiner;

public class Record {
    private final long timestamp;
    private final LatLng location;
    private final String level;
    private final String source;

    public Record(String level, LatLng location, long timestamp, String source) {

        this.level = level;
        this.location = location;
        this.timestamp = timestamp;
        this.source = source;
    }

    public String getLevel() {
        return level;
    }

    public LatLng getLocation() {
        return location;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getSource() {
        return source;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "{", "}")
                .add("timestamp:" + timestamp)
                .add("location:{lat:" + location.lat + "," + "lng:" + location.lng + "}")
                .add("level:" + level + "'")
                .add("source:'" + source + "'")
                .toString();
    }
}
