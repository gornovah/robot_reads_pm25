package com.seatcode.robotread;

import com.google.maps.model.LatLng;

import java.util.List;

public class Robot {

    private LatLng position;

    public void start(PolylineRoute polylineRoute) {
        move(polylineRoute);
    }

    public void reportMeasure() {
        throw new UnsupportedOperationException();
    }

    public void readLevel(Position position, Clock timestamp) {
        throw new UnsupportedOperationException();
    }

    public LatLng obtainPositon() {
        return position;
    }

    private void move(PolylineRoute polylineRoute) {
        List<LatLng> decodedPolyline = polylineRoute.decode();
        for (LatLng latLng : decodedPolyline) {
            position = latLng;
        }

    }
}
