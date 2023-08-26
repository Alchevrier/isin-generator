package com.achevrier.random;

import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

public enum RandomNumberGenerator {
    INSTANCE;

    public int generate(int lower, int upper) {
        return ThreadLocalRandom.current().nextInt(lower, upper);
    }

    public String generateDouble(double lower, double upper) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(ThreadLocalRandom.current().nextDouble(lower, upper));
    }
}
