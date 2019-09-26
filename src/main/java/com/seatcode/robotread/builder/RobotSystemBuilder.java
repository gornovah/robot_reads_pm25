package com.seatcode.robotread.builder;

import com.seatcode.robotread.actions.RobotSystem;

/**
 * Interface of a builder of {@link RobotSystem} instance.
 * @author despinosa
 */
@FunctionalInterface
public interface RobotSystemBuilder {
    RobotSystem build();
}
