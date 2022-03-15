package com.crypto.cryptoassets.asset.domain;

import lombok.Getter;
import java.util.Date;

@Getter
public class AssetHistory {
    String priceUsd;
    Long time;
    Date date;
}
