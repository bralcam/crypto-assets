package com.crypto.cryptoassets.asset.domain.service;

import com.crypto.cryptoassets.asset.domain.Asset;
import com.crypto.cryptoassets.asset.domain.repository.AssetRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Getter
@Slf4j
public class AssetService {

    private String urlAsset = "/assets?search={search}";
    private final AssetRepository assetRepository;
    private List<Asset> assets;
    private Asset bestAsset;
    private Asset worstAsset;
    private BigDecimal totalAssets;

    public AssetService() {
        assets = new ArrayList<>();
        assets = Collections.synchronizedList(assets);
        this.assetRepository = new AssetRepository();
        totalAssets = new BigDecimal(0);
    }

    public Asset findAssets(String urlBase, String symbol) {
        try {
            urlAsset = urlAsset.replace("{search}", symbol);
            return assetRepository.findAssets(urlBase + urlAsset);
        } catch (IOException exception) {
            log.info("Error in Asset HTTP request connection: " + exception.getMessage());
        }
        return null;
    }

    public void addAsset(Asset asset) {
        assets.add(asset);
    }

    public void verifyAssetsReturned() {
        for (Asset assetFound: assets) {
            if (Objects.isNull(bestAsset)) {
                bestAsset = assetFound;
            } else {
                if (bestAsset.getPerformance().compareTo(assetFound.getPerformance()) < 0) {
                    bestAsset = assetFound;
                }
            }
            if (Objects.isNull(worstAsset)) {
                worstAsset = assetFound;
            } else {
                if (worstAsset.getPerformance().compareTo(assetFound.getPerformance()) > 0) {
                    worstAsset = assetFound;
                }
            }
            totalAssets = totalAssets.add(assetFound.getTotal());
        }
    }
}
