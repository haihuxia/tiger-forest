package com.xhh.demo.spring.boot.client;

import lombok.ToString;
import retrofit.RestAdapter;
import retrofit.http.GET;

/**
 * 调用 Petstore 接口的例子
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/6/26 下午2:58
 */
public class PetstoreClient {

    private static final String API_URL = "http://petstore.swagger.io";

    @ToString
    class Inventory {
        int confused;
        int string;
        int available;
    }

    interface PetstoreService {
        @GET("/v2/store/inventory")
        Inventory inventory();
    }

    public static void main(String[] args) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build();
        PetstoreService petstoreService = restAdapter.create(PetstoreService.class);
        // Fetch and print a list of the contributors to this library.
        Inventory inventory = petstoreService.inventory();
        System.out.println("resulnt: " + inventory);
    }
}
