package com.seatcode.robotread;

import com.google.maps.model.LatLng;

public class Record {
    private final String measure;
    private final LatLng position;
    private final long instant;

    public Record(String measure, LatLng position, long instant) {

        this.measure = measure;
        this.position = position;
        this.instant = instant;
    }

}
