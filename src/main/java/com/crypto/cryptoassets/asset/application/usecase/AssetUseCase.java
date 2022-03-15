package com.crypto.cryptoassets.asset.application.usecase;

import com.crypto.cryptoassets.asset.domain.Asset;
import com.crypto.cryptoassets.asset.domain.parser.AssetParser;
import com.crypto.cryptoassets.asset.domain.service.AssetHistoryService;
import com.crypto.cryptoassets.asset.domain.service.AssetService;
import com.crypto.cryptoassets.common.domain.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class AssetUseCase {

    private final AssetService assetService;
    private final AssetHistoryService assetHistoryService;
    private final int threadsNumber = 3;

    public AssetUseCase() {
        assetService = new AssetService();
        assetHistoryService = new AssetHistoryService();
    }

    public List<Asset> processAssetPositions(String filename) {
        ExecutorService executorService = null;
        try {
            AssetParser assetParser = new AssetParser(filename);
            if (Objects.nonNull(assetParser.getFileData())) {
                List<AssetExecutor> assetExecutors = new ArrayList<>();
                int i = 0;
                for (List<String> line: assetParser.getFileData()) {
                    if (i > 0) {
                        assetExecutors.add(new AssetExecutor(assetService, assetHistoryService, line));
                    }
                    i++;
                }
                executorService = Executors.newFixedThreadPool(threadsNumber);
                List<Future<Asset>> futureResultList = executorService.invokeAll(assetExecutors);
                for (Future<Asset> futureResult: futureResultList) {
                    assetService.addAsset(futureResult.get());
                }
                executorService.shutdown();
                executorService.awaitTermination(5, TimeUnit.SECONDS);
                assetService.verifyAssetsReturned();
                prepareFinalResult();
                return assetService.getAssets();
            }
        } catch (InterruptedException interruptedException) {
            if (Objects.nonNull(executorService)) {
                executorService.shutdownNow();
            }
            Thread.currentThread().interrupt();
        } catch (Exception exception) {
            log.info("Error in process CSV file content: " + exception.getMessage());
        }
        return null;
    }

    private void prepareFinalResult() {
        String finalResult = "total=" + StringUtil.formatBigDecimalNumber(assetService.getTotalAssets()) + "," +
                             "best_asset=" + assetService.getBestAsset().getSymbol() + "," +
                             "best_performance=" + StringUtil.formatBigDecimalNumber(assetService.getBestAsset().getPerformance()) + "," +
                             "worst_asset=" + assetService.getWorstAsset().getSymbol() + "," +
                             "worst_performance=" + StringUtil.formatBigDecimalNumber(assetService.getWorstAsset().getPerformance());
        System.out.println(finalResult);
    }

}
