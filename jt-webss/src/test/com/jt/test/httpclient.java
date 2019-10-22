package com.jt.test;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import sun.net.www.http.HttpClient;

import java.io.IOException;

public class httpclient {
    /**
     * Get请求
     */
    @Test
    public void doGet() throws IOException {
        //实例话HttpClinent
        CloseableHttpClient client = HttpClients.createDefault();
        String url = "http://www.baidu.com";
        HttpGet get = new HttpGet(url);
        String method = get.getRequestLine().getMethod();

        CloseableHttpResponse response = client.execute(get);

        if (response.getStatusLine().getStatusCode() == 200) {
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        }


    }
}
