package com.crypto.cryptoassets.asset.domain.parser;

import lombok.Getter;
import org.beanio.stream.delimited.DelimitedReader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class AssetParser {

    private final List<List<String>> fileData;

    public AssetParser(String filename) throws IOException {
        fileData = new ArrayList<>();
        getFileData(filename);
    }

    private void getFileData(String filename) throws IOException {
        char charDelimiter = ',';
        FileInputStream fileInputStream = new FileInputStream(filename);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        DelimitedReader delimitedReader = new DelimitedReader(bufferedReader, charDelimiter);
        String[] line;
        while ((line = delimitedReader.read()) != null) {
            List<String> formattedList = Arrays.asList(line);
            fileData.add(formattedList);
        }
    }
}
