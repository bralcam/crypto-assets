package com.crypto.cryptoassets.asset.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class AssetHistoryResponse {
    List<AssetHistory> data;
    Long timestamp;
}
