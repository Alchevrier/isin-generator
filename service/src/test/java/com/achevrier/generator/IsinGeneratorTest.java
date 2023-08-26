package com.achevrier.generator;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class IsinGeneratorTest {
    @Test
    public void shouldGenerateValidISIN() {
        IsinGenerator generator = IsinGenerator.INSTANCE;
        IntStream.rangeClosed(0, 150).forEach(i -> {
            String generatedIsin = generator.generate();
            assertThat(generatedIsin).hasSize(12);

            String firstCharacter = Character.toString(generatedIsin.charAt(0));
            assertThat(firstCharacter).matches("[A-Z]");

            String secondCharacter = Character.toString(generatedIsin.charAt(1));
            assertThat(secondCharacter).matches("[A-Z]");

            IntStream.rangeClosed(2, 9).forEach(j -> {
                String currentExpectedNumChar = Character.toString(generatedIsin.charAt(j));
                assertThat(currentExpectedNumChar).matches("[A-Z0-9]");
            });

            String checkDigit = Character.toString(generatedIsin.charAt(generatedIsin.length() - 1));
            assertThat(checkDigit).matches("[0-9]");
        });
    }

    @Test
    public void shouldCorrectlyCheckDigit() {
        IsinGenerator generator = IsinGenerator.INSTANCE;
        assertThat(generator.checkDigit("DE123456789")).isEqualTo("6");
    }
}
