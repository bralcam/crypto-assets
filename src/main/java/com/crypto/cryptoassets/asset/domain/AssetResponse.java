package com.crypto.cryptoassets.asset.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class AssetResponse {
    List<Asset> data;
    Long timestamp;
}
