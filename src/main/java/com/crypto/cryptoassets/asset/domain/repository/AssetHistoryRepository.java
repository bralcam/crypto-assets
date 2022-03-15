package com.crypto.cryptoassets.asset.domain.repository;

import com.crypto.cryptoassets.asset.domain.AssetHistory;
import com.crypto.cryptoassets.asset.domain.AssetHistoryResponse;
import com.crypto.cryptoassets.common.domain.UrlUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class AssetHistoryRepository {

    public List<AssetHistory> findAssetHistories(String url) throws IOException {
        if (!Strings.isBlank(url)) {
            String resultIntoJson = UrlUtil.getJsonResultIntoString(url);
            ObjectMapper objectMapper = new ObjectMapper();
            AssetHistoryResponse response = objectMapper.readValue(resultIntoJson, AssetHistoryResponse.class);
            return response.getData();
        }
        return null;
    }

}
