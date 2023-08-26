package com.achevrier.generator;

import com.achevrier.random.RandomLetterGenerator;
import com.achevrier.random.RandomNumberGenerator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum IsinGenerator {
    INSTANCE(RandomLetterGenerator.INSTANCE, RandomNumberGenerator.INSTANCE);

    private static final Map<String, Integer> CONVERSION_CHARACTER;

    static {
        CONVERSION_CHARACTER = new HashMap<>();
        CONVERSION_CHARACTER.put("A", 10);
        CONVERSION_CHARACTER.put("B", 11);
        CONVERSION_CHARACTER.put("C", 12);
        CONVERSION_CHARACTER.put("D", 13);
        CONVERSION_CHARACTER.put("E", 14);
        CONVERSION_CHARACTER.put("F", 15);
        CONVERSION_CHARACTER.put("G", 16);
        CONVERSION_CHARACTER.put("H", 17);
        CONVERSION_CHARACTER.put("I", 18);
        CONVERSION_CHARACTER.put("J", 19);
        CONVERSION_CHARACTER.put("K", 20);
        CONVERSION_CHARACTER.put("L", 21);
        CONVERSION_CHARACTER.put("M", 22);
        CONVERSION_CHARACTER.put("N", 23);
        CONVERSION_CHARACTER.put("O", 24);
        CONVERSION_CHARACTER.put("P", 25);
        CONVERSION_CHARACTER.put("Q", 26);
        CONVERSION_CHARACTER.put("R", 27);
        CONVERSION_CHARACTER.put("S", 28);
        CONVERSION_CHARACTER.put("T", 29);
        CONVERSION_CHARACTER.put("U", 30);
        CONVERSION_CHARACTER.put("V", 31);
        CONVERSION_CHARACTER.put("W", 32);
        CONVERSION_CHARACTER.put("X", 33);
        CONVERSION_CHARACTER.put("Y", 34);
        CONVERSION_CHARACTER.put("Z", 35);
    }

    private final RandomLetterGenerator randomLetterGenerator;
    private final RandomNumberGenerator randomNumberGenerator;

    IsinGenerator(RandomLetterGenerator randomLetterGenerator, RandomNumberGenerator randomNumberGenerator) {
        this.randomLetterGenerator = randomLetterGenerator;
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public String generate() {
        StringBuilder result = new StringBuilder();
        result.append(this.nextLetter());
        result.append(this.nextLetter());

        result.append(this.nextAlphaNumeric());
        result.append(this.nextAlphaNumeric());
        result.append(this.nextAlphaNumeric());
        result.append(this.nextAlphaNumeric());
        result.append(this.nextAlphaNumeric());
        result.append(this.nextAlphaNumeric());
        result.append(this.nextAlphaNumeric());
        result.append(this.nextAlphaNumeric());
        result.append(this.nextAlphaNumeric());

        result.append(this.checkDigit(result.toString()));

        return result.toString();
    }

    String checkDigit(String isinToCheck) {
        String isinAsDigits = toOnlyDigitsStream(isinToCheck);
        String oddlyDoubledDigitsStream = oddlyDoubledDigitsStream(isinAsDigits);
        int sum = sumByIndividualDigits(oddlyDoubledDigitsStream);
        int closestMultipleOfTen = findClosestUpperDigitMultipleOfTen(sum);
        return Integer.toString(closestMultipleOfTen - sum);
    }

    private String toOnlyDigitsStream(String isinToCheck) {
        return Arrays.stream(isinToCheck.split(""))
            .map(nextChar -> {
                try {
                    return Integer.valueOf(nextChar);
                } catch (NumberFormatException e) {
                    return CONVERSION_CHARACTER.get(nextChar);
                }
            }).map(Object::toString).collect(Collectors.joining());
    }

    private String oddlyDoubledDigitsStream(String isinOnlyDigits) {
        return IntStream.range(0, isinOnlyDigits.length())
            .map(i -> isinOnlyDigits.length() - i - 1)
            .mapToObj(i -> {
                char currentDigit = isinOnlyDigits.charAt(i);
                if (i % 2 == 0) {
                    return String.valueOf(currentDigit);
                }
                return String.valueOf(currentDigit * 2);
            }).collect(Collectors.joining());
    }

    private int sumByIndividualDigits(String oddlyDoubledDigitsStream) {
        return Arrays.stream(oddlyDoubledDigitsStream.split(""))
            .map(Integer::valueOf)
            .reduce(0, Integer::sum);
    }

    private int findClosestUpperDigitMultipleOfTen(Integer restOfSum) {
        return 10 * (int) (Math.ceil((float) restOfSum / 10));
    }

    private String nextLetter() {
        return this.randomLetterGenerator.generate(randomNumberGenerator::generate);
    }

    private String nextAlphaNumeric() {
        return this.randomLetterGenerator.generateAlphaNumeric(randomNumberGenerator::generate);
    }
}
