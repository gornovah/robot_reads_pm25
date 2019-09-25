package com.seatcode.robotread.domain.services;

import com.seatcode.robotread.api.ReportFormatter;
import com.seatcode.robotread.domain.model.Average;
import com.seatcode.robotread.api.Console;

import java.util.Arrays;

public class ReportPrinter {

    private final Console console;
    private final ReportFormatter reportFormatter;

    public ReportPrinter(Console console, ReportFormatter reportFormatter) {
        this.console = console;
        this.reportFormatter = reportFormatter;
    }

    public void report(Average average) {
        LevelPm25 levelPm25 = new LevelPm25();
        String value = levelPm25.fromLevelToRangeValue(average.getLevel());
        String report = reportFormatter.parseToJson(average, value);
        console.print(report);
    }

    private enum Range {
        GOOD(0, 50),
        MODERATE(51, 100),
        USG(101, 150),
        UNHEALTHY(151, -1),
        OTHER(0, -1);


        private final int minValue;
        private final int maxValue;

        Range(int min, int max) {
            this.minValue = min;
            this.maxValue = max;
        }

        private static Range getFrom(int score) {
            return Arrays.asList(Range.values()).stream()
                    .filter(t -> score >= t.minValue && score <= t.maxValue)
                    .findAny()
                    .orElse(OTHER);
        }
    }

    private class LevelPm25 {

        private String fromLevelToRangeValue(int level) {
            switch (Range.getFrom(level)) {
                case GOOD:
                    return "GOOD";
                case MODERATE:
                    return "MODERATE";
                case USG:
                    return "USG";
                case UNHEALTHY:
                    return "UNHEALTHY";
                case OTHER:
                    return "OTHER";
                default:
                    break;
            }

            return null;
        }
    }
}
