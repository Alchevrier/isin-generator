package com.achevrier.random;

import java.util.function.ToIntBiFunction;

public enum RandomLetterGenerator {
    INSTANCE;

    public String generate(ToIntBiFunction<Integer, Integer> numGen) {
        int randomNum = numGen.applyAsInt(1, 26);
        return String.valueOf((char) (randomNum + 64)).toUpperCase();
    }

    public String generateAlphaNumeric(ToIntBiFunction<Integer, Integer> numGen) {
        int randomNum = numGen.applyAsInt(0, 10);
        return randomNum < 5 ? Integer.toString(numGen.applyAsInt(1, 10)) : this.generate(numGen);
    }
}
