package com.crypto.cryptoassets;

import com.crypto.cryptoassets.asset.application.usecase.AssetUseCase;
import com.crypto.cryptoassets.asset.domain.Asset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CryptoAssetsApplicationTests {

	@Test
	public void submitValidCsfFile() throws IOException {
		String filename = "src/main/resources/files/example.csv";
		AssetUseCase assetUseCase = new AssetUseCase();
		List<Asset> assets = assetUseCase.processAssetPositions(filename);
		List<Asset> assetsExpected = CryptoAssetFixture.assetResponseValid();
		assertEquals(assets.get(0).getOriginalPriceUsd(), assetsExpected.get(0).getOriginalPriceUsd());
	}

}
