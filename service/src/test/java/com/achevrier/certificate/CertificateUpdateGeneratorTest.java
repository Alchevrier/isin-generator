package com.achevrier.certificate;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

public class CertificateUpdateGeneratorTest {
    @Test
    public void shouldGenerateAValidCertificationUpdate() {
        Supplier<String> generator = CertificateUpdateGenerator.CERTIFICATE_GENERATOR;
        try {
            String certificateUpdate = generator.get();
            String[] cUComponents = certificateUpdate.split(",");

            assertThat(Instant.ofEpochSecond(Long.parseLong(cUComponents[0]))).isNotNull();

            assertThat(String.valueOf(cUComponents[1].charAt(0))).matches("[A-Z]");
            assertThat(String.valueOf(cUComponents[1].charAt(1))).matches("[A-Z]");

            String bidPrice = cUComponents[2];
            assertThat(bidPrice).matches("[1-2][0-9]{2}\\.[0-9]{2}");

            String bidSize = cUComponents[3];
            assertThat(bidSize).matches("[1-5][0-9]{3}");

            String askPrice = cUComponents[4];
            assertThat(askPrice).matches("[1-2][0-9]{2}\\.[0-9]{2}");

            String askSize = cUComponents[5];
            assertThat(askSize).matches("[1-9][0-9]{3}[0-9]*");
            assertThat(Integer.valueOf(askSize)).isLessThan(10000);

            System.out.println(certificateUpdate);
        } catch (Exception e) {
            assertThat(false).isEqualTo(true);
        }
    }
}
