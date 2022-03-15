package com.crypto.cryptoassets.asset.application.usecase;

import com.crypto.cryptoassets.asset.domain.Asset;
import com.crypto.cryptoassets.asset.domain.AssetHistory;
import com.crypto.cryptoassets.asset.domain.service.AssetHistoryService;
import com.crypto.cryptoassets.asset.domain.service.AssetService;
import com.crypto.cryptoassets.common.domain.StringUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

public class AssetExecutor implements Callable<Asset> {

    private String urlBase = "https://api.coincap.io/v2";

    private final AssetService assetService;
    private final AssetHistoryService assetHistoryService;
    private final List<String> line;

    public AssetExecutor(AssetService assetService, AssetHistoryService assetHistoryService, List<String> line) {
        this.assetService = assetService;
        this.assetHistoryService = assetHistoryService;
        this.line = line;
    }

    @Override
    public Asset call() {
        if (Objects.nonNull(line) && (line.size() > 0)) {
            Asset asset = assetService.findAssets(urlBase, line.get(0));
            if (Objects.nonNull(asset)) {
                asset.setQuantity(new BigDecimal(line.get(1)));
                asset.setOriginalPriceUsd(new BigDecimal(line.get(2)));
                List<AssetHistory> assetHistory = assetHistoryService.findAssetHistories(urlBase, asset.getId());
                if (Objects.nonNull(assetHistory)) {
                    asset.setActualPriceUsd(new BigDecimal(assetHistory.get(0).getPriceUsd()));
                }
            }
            System.out.println("Submitted request " + Thread.currentThread() + " at " + StringUtil.printCurrentTime());
            return asset;
        }
        return null;
    }
}
