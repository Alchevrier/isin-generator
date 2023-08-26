package com.achevrier;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CertificateUpdateProviderTest {
    @Test
    public void generateQuotes() {
        CertificateUpdateProvider certificateUpdateGenerator = new CertificateUpdateProvider(10, 100);
        Stream<String> quotes = certificateUpdateGenerator.generateQuotes();
        assertNotNull(quotes);
        assertEquals(10 * 100, quotes.count());
    }
}
