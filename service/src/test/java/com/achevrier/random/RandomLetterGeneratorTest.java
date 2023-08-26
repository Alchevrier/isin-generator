package com.achevrier.random;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomLetterGeneratorTest {
    @Test
    public void shouldProvideRandomLetter() {
        RandomLetterGenerator randomLetterGenerator = RandomLetterGenerator.INSTANCE;
        RandomNumberGenerator randomNumberGenerator = RandomNumberGenerator.INSTANCE;
        IntStream.range(0, 100).forEach((i) -> {
            String generatedResult = randomLetterGenerator.generate(randomNumberGenerator::generate);
            assertThat(generatedResult).hasSize(1);
            assertThat(generatedResult).matches("[A-Z]");
        });
    }

    @Test
    public void shouldProvideRandomAlphaNumeric() {
        RandomLetterGenerator randomLetterGenerator = RandomLetterGenerator.INSTANCE;
        RandomNumberGenerator randomNumberGenerator = RandomNumberGenerator.INSTANCE;
        IntStream.range(0, 100).forEach((i) -> {
            String generatedResult = randomLetterGenerator.generateAlphaNumeric(randomNumberGenerator::generate);
            assertThat(generatedResult).hasSize(1);
            assertThat(generatedResult).matches("[A-Z1-9]");
        });
    }
}
