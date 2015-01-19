package com.xhh.demo.http.practice.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * ConcurrentNAV
 *
 * @author tiger
 * @version 1.0.0 createTime: 14-5-25
 * @since 1.6
 */
public class ConcurrentNAV {

    public double computeNetAssetValue(final Map<String, Integer> stocks) throws InterruptedException,
            ExecutionException{
        //获取可用的处理器核心数目
        final int numberOfCores = Runtime.getRuntime().availableProcessors();
        final double blockingCoefficient = 0.9;
        final int poolSize = (int)(numberOfCores / (1 - blockingCoefficient));
        System.out.println("Number of Cores availabel is " + numberOfCores);
        final List<Callable<Double>> partitions = new ArrayList<Callable<Double>>();
        for(final String ticker : stocks.keySet()) {
            partitions.add(new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    return stocks.get(ticker) * 5.01; //TODO call method
                }
            });
        }

        final ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        final List<Future<Double>> valueOfStocks = executorService.invokeAll(partitions, 10000, TimeUnit.SECONDS);

        //合并每个线程得到的值
        double netAssetVlue = 0.0;
        for(final Future<Double> valueOfAStock : valueOfStocks) {
            netAssetVlue += valueOfAStock.get();
        }

        executorService.shutdown();
        return netAssetVlue;
    }
}

