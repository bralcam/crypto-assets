package com.crypto.cryptoassets.common.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlUtil {

    public static String getJsonResultIntoString(String completeUrl) throws IOException {
        URL url = new URL(completeUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException(httpURLConnection.getResponseMessage());
        } else {
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader response = new BufferedReader(inputStreamReader);
            return StringUtil.convertJsonIntoString(response);
        }
    }

}
