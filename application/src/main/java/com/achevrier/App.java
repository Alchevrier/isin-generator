package com.achevrier;

public class App {
    public static void main( String[] args ) {
        if (args.length >= 2) {
            int threads = Integer.parseInt(args[0]);
            int quotes = Integer.parseInt(args[1]);

            CertificateUpdateProvider certificateUpdateGenerator = new CertificateUpdateProvider(threads, quotes);
            certificateUpdateGenerator.generateQuotes().forEach(System.out::println);
        } else {
            throw new RuntimeException("Expect at least number of threads and number of quotes. But got: " + args);
        }
    }
}
