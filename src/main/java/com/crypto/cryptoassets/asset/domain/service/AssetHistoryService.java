package com.crypto.cryptoassets.asset.domain.service;

import com.crypto.cryptoassets.asset.domain.AssetHistory;
import com.crypto.cryptoassets.asset.domain.repository.AssetHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class AssetHistoryService {

    private String urlHistory = "/assets/{id}/history?interval={interval}&start={start}&end={end}";
    private final String intervalParam = "d1";
    private final String startDateParam = "1617753600000";
    private final String endDateParam = "1617753601000";
    private final AssetHistoryRepository assetHistoryRepository;

    public AssetHistoryService() {
        this.assetHistoryRepository = new AssetHistoryRepository();
    }

    public List<AssetHistory> findAssetHistories(String urlBase, String id) {
        if (!Strings.isBlank(id)) {
            try {
                urlHistory = urlHistory
                        .replace("{id}", id)
                        .replace("{interval}", intervalParam)
                        .replace("{start}", startDateParam)
                        .replace("{end}", endDateParam);
                return assetHistoryRepository.findAssetHistories(urlBase + urlHistory);
            } catch (IOException exception) {
                log.info("Error in Asset History HTTP request connection: " + exception.getMessage());
            }
        }
        return null;
    }

}
