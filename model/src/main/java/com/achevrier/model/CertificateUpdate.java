package com.achevrier.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;

public class CertificateUpdate {
    private final Instant timestamp;
    private final String isin;
    private final BigDecimal bidPrice;
    private final BigInteger bidSize;
    private final BigDecimal askPrice;
    private final BigInteger askSize;

    public CertificateUpdate(
        Instant timestamp,
        String isin,
        BigDecimal bidPrice,
        BigInteger bidSize,
        BigDecimal askPrice,
        BigInteger askSize
    ) {
        this.timestamp = timestamp;
        this.isin = isin;
        this.bidPrice = bidPrice;
        this.bidSize = bidSize;
        this.askPrice = askPrice;
        this.askSize = askSize;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getIsin() {
        return isin;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public BigInteger getBidSize() {
        return bidSize;
    }

    public BigDecimal getAskPrice() {
        return askPrice;
    }

    public BigInteger getAskSize() {
        return askSize;
    }

    @Override
    public String toString() {
        return String.format(
            "%s,%s,%s,%s,%s,%s",
            this.timestamp.toEpochMilli(), this.isin, this.bidPrice, this.bidSize, this.askPrice, this.askSize
        );
    }
}
