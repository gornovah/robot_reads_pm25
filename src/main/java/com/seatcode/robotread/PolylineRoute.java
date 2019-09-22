package com.seatcode.robotread;

import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.LatLng;

import java.util.List;

public class PolylineRoute {

    private String polyline;

    public PolylineRoute(String polyline) {
        this.polyline = polyline;
    }

    public List<LatLng> decode() {
        PolylineEncoding polylineEncoding = new PolylineEncoding();
        return polylineEncoding.decode(polyline);
    }
}
