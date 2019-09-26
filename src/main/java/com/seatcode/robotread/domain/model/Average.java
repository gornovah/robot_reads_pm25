package com.seatcode.robotread.domain.model;

import com.google.maps.model.LatLng;
/**
 * The entity that represents the average of the last 15 minutes records, with the timestamp,
 * the last location of the interval, the level, and the source that is fixed "robot"
 *
 * @author despinosa
 */
public class Average {

    private final long timestamp;
    private final LatLng location;
    private final int level;
    private final String source;

    public Average(int level, LatLng location, long timestamp, String source) {

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

    public String getSource() {
        return source;
    }

}
