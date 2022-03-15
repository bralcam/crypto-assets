package com.crypto.cryptoassets.asset.domain.repository;

import com.crypto.cryptoassets.asset.domain.Asset;
import com.crypto.cryptoassets.asset.domain.AssetResponse;
import com.crypto.cryptoassets.common.domain.UrlUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class AssetRepository {

    public Asset findAssets(String url) throws IOException {
        if (!Strings.isBlank(url)) {
            String resultIntoJson = UrlUtil.getJsonResultIntoString(url);
            ObjectMapper objectMapper = new ObjectMapper();
            AssetResponse response = objectMapper.readValue(resultIntoJson, AssetResponse.class);
            return response.getData().get(0);
        }
        return null;
    }
}
