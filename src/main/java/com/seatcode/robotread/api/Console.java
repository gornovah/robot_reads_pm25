package com.seatcode.robotread.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Class for print the reports of measure
 * @author despinosa
 *
 */
public class Console {

    Logger logger = LogManager.getLogger(Console.class);

    public void print(String line) {
        logger.info(line);
    }
}
