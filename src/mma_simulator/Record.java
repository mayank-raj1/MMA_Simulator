package mma_simulator;

import java.util.Arrays;

public record Record(Match match, Fighter winner, int[] score) {
    @Override
    public String toString() {
        return ", winner=" + winner.getName() +
                ", score=" + Arrays.toString(score);
    }
}
