package com.crypto.cryptoassets;

import com.crypto.cryptoassets.asset.application.usecase.AssetUseCase;
import com.crypto.cryptoassets.common.domain.StringUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@SpringBootApplication
@Configuration
public class CryptoAssetsApplication {

	public static void main(String[] args) {
		System.out.println("Now is " + StringUtil.printCurrentTime());

		try {
			String filePath = "src/main/resources/files/example.csv";
			File file = new File(filePath);
			AssetUseCase assetUseCase = new AssetUseCase();
			assetUseCase.processAssetPositions(file.getPath());
		} catch (Exception e) {
			System.out.println("Error in file processing: " + e.getMessage());
		}

		System.out.println("Now is " + StringUtil.printCurrentTime());
	}
}
