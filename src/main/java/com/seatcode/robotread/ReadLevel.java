package com.seatcode.robotread;

import java.util.Arrays;
import java.util.Random;

public class ReadLevel {

    public String execute() {
        LevelPm25 levelPm25 = new LevelPm25();
        return levelPm25.obtainValue();
    }

    private enum Range {
        GOOD(0, 50),
        MODERATE(51, 100),
        USG(101, 150),
        UNHEALTHY(151, -1),
        OTHER(0, -1);


        private final int minValue;
        private final int maxValue;

        private Range(int min, int max) {
            this.minValue = min;
            this.maxValue = max;
        }

        public static Range getFrom(int score) {
            return Arrays.asList(Range.values()).stream()
                    .filter(t -> (score >= t.minValue && score <= t.maxValue))
                    .findAny()
                    .orElse(OTHER);
        }
    }

    private class LevelPm25 {

        public String obtainValue() {
            int level = getRandomNumber();
            switch (Range.getFrom(level)){
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

        private int getRandomNumber (){
            Random r = new Random();
            return r.ints(0, 151).findFirst().getAsInt();
        }
    }
}
