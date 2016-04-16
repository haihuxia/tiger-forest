package com.xhh.demo.spring.boot.client;

import retrofit.http.GET;

/**
 * ApiClient
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/7/1 上午9:50
 */
public class ApiClient {

    public interface InventoryClient {

        @GET("/v2/store/inventory")
        Inventory inventory();
    }

}
