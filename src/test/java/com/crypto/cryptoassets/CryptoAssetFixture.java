package com.crypto.cryptoassets;

import com.crypto.cryptoassets.asset.domain.Asset;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class CryptoAssetFixture {

    private static final String cryptoList = "[{\n" +
            "            \"id\": \"bitcoin\",\n" +
            "            \"rank\": \"1\",\n" +
            "            \"symbol\": \"BTC\",\n" +
            "            \"originalPriceUsd\": 37870.5058 \n " +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"ethereum\",\n" +
            "            \"rank\": \"2\",\n" +
            "            \"symbol\": \"ETH\",\n" +
            "            \"originalPriceUsd\": 2004.9774 \n " +
            "        }]";

    public static List<Asset> assetResponseValid() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(cryptoList, new TypeReference<List<Asset>>(){});
    }
}
