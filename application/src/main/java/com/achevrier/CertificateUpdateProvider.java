package com.achevrier;

import com.achevrier.certificate.CertificateUpdateGenerator;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

public class CertificateUpdateProvider {
    private final int threads;
    private final int quotes;

    public CertificateUpdateProvider(int threads, int quotes) {
        this.threads = threads;
        this.quotes = quotes;
    }

    public Stream<String> generateQuotes() {
        ForkJoinPool customForkJoinPool = new ForkJoinPool(this.threads);
        try {
            return customForkJoinPool.submit(
                () -> Stream.generate(CertificateUpdateGenerator.CERTIFICATE_GENERATOR)
                    .parallel()
                    .limit((long) this.threads * quotes)
            ).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            customForkJoinPool.shutdown();
        }
    }
}
