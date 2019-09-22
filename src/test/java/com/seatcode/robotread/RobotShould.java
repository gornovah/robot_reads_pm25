package com.seatcode.robotread;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RobotShould {

    @Test
    public void move_through_polyline() {
        PolylineRoute polylineRoute = new PolylineRoute("{bp{F}vbLgIeP");
        ReadLevel level = mock(ReadLevel.class);
        MeasureRepository measureRepository = mock(MeasureRepository.class);

        Robot robot = new Robot(level, measureRepository);

        robot.start(polylineRoute);

        String position = robot.position().toString();
        assertThat(position).isEqualTo("41.37698000,2.15186000");
    }

    @Test
    public void read_pm25_level() {
        PolylineRoute polylineRoute = new PolylineRoute("{bp{F}vbLgIeP");
        ReadLevel level = mock(ReadLevel.class);
        MeasureRepository measureRepository = mock(MeasureRepository.class);
        Robot robot = new Robot(level, measureRepository);

        robot.start(polylineRoute);

        robot.readPm25Level();
        verify(level).execute();
    }
    
    @Test
    public void save_reading_pm25_level() {
        PolylineRoute polylineRoute = new PolylineRoute("{bp{F}vbLgIeP");
        ReadLevel readLevel = mock(ReadLevel.class);
        MeasureRepository measureRepository = mock(MeasureRepository.class);
        Robot robot = new Robot(readLevel, measureRepository);

        robot.start(polylineRoute);
        robot.readPm25Level();
        ArgumentCaptor<Record> argumentCaptor = ArgumentCaptor.forClass(Record.class);

        verify(measureRepository).save(argumentCaptor.capture());
    }

}