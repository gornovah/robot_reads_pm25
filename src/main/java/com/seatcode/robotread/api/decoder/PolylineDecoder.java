package com.seatcode.robotread.api.decoder;

import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.LatLng;

import java.util.List;

public class PolylineDecoder {

    private final String polyline;

    public PolylineDecoder(String polyline) {
        this.polyline = polyline;
    }

    public List<LatLng> decode() {
        return PolylineEncoding.decode(polyline);
    }
}
