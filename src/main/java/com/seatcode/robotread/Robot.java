package com.seatcode.robotread;

import com.google.maps.model.LatLng;

import java.util.List;

public class Robot {

    private PolylineRoute polylineRoute;

    public Robot(PolylineRoute polylineRoute) {
        this.polylineRoute = polylineRoute;
    }

    public void reportMeasure() {
        throw new UnsupportedOperationException();
    }

    public void readLevel(Position position, Clock timestamp) {
        throw new UnsupportedOperationException();
    }

    public List<LatLng> obtainPositon() {
        return polylineRoute.decode();
    }
}
