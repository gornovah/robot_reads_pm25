package com.seatcode.robotread.domain.services;

import com.google.maps.model.LatLng;
import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;

import java.util.List;
/**
 * The route service calculate within the position in route, the position in
 * the polyline compared with the acumulated distance and the distance
 * traveled for the robot with a velocity of 3m/s in 100m
 *
 * @author despinosa
 */
public class RouteService {

    public LatLng withinRoute(List<LatLng> decodedPolyline, Double distanceTraveled) {

        double accumulatedDistance = 0;

        for (int i = 0; i < decodedPolyline.size() - 1; i++) {
            accumulatedDistance = getAccumulatedDistance(decodedPolyline, accumulatedDistance, i);

            if (accumulatedDistance >= distanceTraveled) {
                return decodedPolyline.get(Math.max(0, i - 1));
            }
        }
        return null;
    }

    private double getAccumulatedDistance(List<LatLng> decodedPolyline, double accumulatedDistance, int actual) {
        LatLng currentLatLng = decodedPolyline.get(actual);

        Coordinate currentCoordinateLat = Coordinate.fromDegrees(currentLatLng.lat);
        Coordinate currentCoordinateLong = Coordinate.fromDegrees(currentLatLng.lng);

        Point currentPoint = Point.at(currentCoordinateLat, currentCoordinateLong);

        LatLng nextLatLng = decodedPolyline.get(actual + 1);
        Coordinate nextLat = Coordinate.fromDegrees(nextLatLng.lat);
        Coordinate nextLng = Coordinate.fromDegrees(nextLatLng.lng);
        Point nextPoint = Point.at(nextLat, nextLng);
        double distance = EarthCalc.gcdDistance(currentPoint, nextPoint);

        accumulatedDistance += distance;
        return accumulatedDistance;
    }
}
