package com.achevrier.certificate;

import com.achevrier.generator.IsinGenerator;
import com.achevrier.model.CertificateUpdate;
import com.achevrier.random.RandomNumberGenerator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.util.function.Supplier;

public class CertificateUpdateGenerator {
    private CertificateUpdateGenerator() {
        // Cannot be called as it is an utility class
    }

    public static Supplier<String> CERTIFICATE_GENERATOR = () -> {
        RandomNumberGenerator numberGenerator = RandomNumberGenerator.INSTANCE;
        IsinGenerator isinGenerator = IsinGenerator.INSTANCE;

        CertificateUpdate certificateUpdate = new CertificateUpdate(
            Instant.now(),
            isinGenerator.generate(),
            new BigDecimal(numberGenerator.generateDouble(100.00, 200.00)),
            BigInteger.valueOf(numberGenerator.generate(1000, 5000)),
            new BigDecimal(numberGenerator.generateDouble(100.00, 200.00)),
            BigInteger.valueOf(numberGenerator.generate(1000, 10000))
        );

        return certificateUpdate.toString();
    };
}
