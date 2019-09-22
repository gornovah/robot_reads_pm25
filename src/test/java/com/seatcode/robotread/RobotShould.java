package com.seatcode.robotread;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RobotShould {

    @Test
    public void move_through_polyline() {

        PolylineRoute polylineRoute = new PolylineRoute("{bp{F}vbLgIeP");
        Robot robot = new Robot();
        robot.start(polylineRoute);

        String position = robot.obtainPositon().toString();
        assertThat(position).isEqualTo("41.37698000,2.15186000");
    }

}