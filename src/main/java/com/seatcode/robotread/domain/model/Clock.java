package com.seatcode.robotread.domain.model;

import java.time.Instant;
/**
 * The entity that represents the clock, to manage the timestamp for the records and
 * average
 *
 * @author despinosa
 */
public class Clock {
    public String timestampAsString() {
        throw new UnsupportedOperationException();
    }

    public Instant instantNow(){
        return Instant.now();
    }
}
