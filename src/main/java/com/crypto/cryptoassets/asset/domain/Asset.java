package com.crypto.cryptoassets.asset.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class Asset {

    String id;
    String rank;
    String symbol;
    String name;
    String supply;
    String maxSupply;
    String marketCapUsd;
    String volumeUsd24Hr;
    String priceUsd;
    String changePercent24Hr;
    String vwap24Hr;
    String explorer;

    BigDecimal quantity;
    BigDecimal originalPriceUsd;
    BigDecimal actualPriceUsd;

    public BigDecimal getTotal() {
        return this.quantity.multiply(this.actualPriceUsd);
    }

    public BigDecimal getPerformance() {
        return this.actualPriceUsd.divide(this.originalPriceUsd, 10, RoundingMode.HALF_UP);
    }


}
