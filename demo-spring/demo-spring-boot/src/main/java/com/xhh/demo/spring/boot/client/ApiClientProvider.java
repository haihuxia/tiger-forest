package com.xhh.demo.spring.boot.client;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import lombok.extern.slf4j.Slf4j;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import com.xhh.demo.spring.boot.client.ApiClient.InventoryClient;

/**
 * ApiClientProvider
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/7/1 上午9:53
 */
@Slf4j
public class ApiClientProvider {

    private RestAdapter.LogLevel level = RestAdapter.LogLevel.FULL;

    public static String BASEURL = "http://petstore.swagger.io";

    private static ApiClientProvider provider = new ApiClientProvider();

    private OkClient okClient;

    private InventoryClient inventoryClient;

    public static ApiClientProvider getInstance() {
        return provider;
    }

    public OkClient okClient() {
        if (okClient == null) {
            OkHttpClient client = new OkHttpClient();
            client.setConnectTimeout(60, TimeUnit.SECONDS);
            client.setReadTimeout(60, TimeUnit.SECONDS);
            SSLContext sslContext;
            try {
                sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null,
                        new TrustManager[]{new MyTrustManager()},
                        new SecureRandom());
                client.setSslSocketFactory(sslContext.getSocketFactory());
                okClient = new OkClient(client);
            } catch (KeyManagementException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return okClient;
    }

    private class MyTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    private RestAdapter.Log apiLog = s -> log.info("Api", s);

    /**
     * 获取库存信息接口
     *
     * @return InventoryClient
     */
    public InventoryClient getInventoryClient() {
        if (inventoryClient == null) {
            RestAdapter adapter = new RestAdapter.Builder()
                    .setConverter(new retrofit.converter.GsonConverter(new Gson()))
                    .setClient(okClient())
                    .setRequestInterceptor(requestInterceptor)
                    .setEndpoint(BASEURL).setLogLevel(level).setLog(apiLog)
                    .build();
            inventoryClient = adapter.create(InventoryClient.class);
        }
        return inventoryClient;
    }

    RequestInterceptor requestInterceptor = requestFacade -> {
        requestFacade.addEncodedQueryParam("ENCRYPT", "");
        requestFacade.addEncodedQueryParam("SIG", "");
        requestFacade.addEncodedQueryParam("SYSTEM", "android");
    };

    public static void main(String[] args) {
        ApiClientProvider apiClientProvider  = ApiClientProvider.getInstance();
        InventoryClient inventoryClient = apiClientProvider.getInventoryClient();
        System.out.println(inventoryClient.inventory());
    }

}
