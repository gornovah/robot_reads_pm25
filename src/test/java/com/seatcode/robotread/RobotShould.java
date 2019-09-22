package com.seatcode.robotread;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RobotShould {

    @Test
    public void start_in_the_first_position_polyline() {

        PolylineRoute polylineRoute = new PolylineRoute("{bp{F}vbLgIeP");
        Robot robot = new Robot(polylineRoute);
        String position = robot.obtainPositon().get(0).toString();
        assertThat(position).isEqualTo("41.37534000,2.14911000");
    }

}