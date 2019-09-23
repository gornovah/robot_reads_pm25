package com.seatcode.robotread.domain.model;

import java.time.Instant;

public class Clock {
    public String timestampAsString() {
        throw new UnsupportedOperationException();
    }

    public Instant instantNow(){
        return Instant.now();
    }
}
