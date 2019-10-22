package com.jt.sso.vo;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HttpClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientService.class);

    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig requestConfig;

    /**
     * httpClient get请求方式
     */
    public String doGet(String url, Map<String, String> params, String charsat) {
        String result = null;
        if (StringUtils.isEmpty(charsat)) {
            charsat = "UTF-8";
        }

        try {
            if (params != null) {
                URIBuilder builder = new URIBuilder(url);
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    builder.addParameter(entry.getKey(), entry.getValue());
                }
                url = builder.build().toString();
            }
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);
            CloseableHttpResponse httpRespinse = httpClient.execute(httpGet);
            System.out.println(httpRespinse);
            if (httpRespinse.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(httpRespinse.getEntity(), charsat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }


    public String doGet(String url) {
        return doGet(url, null, null);
    }

    public String doGet(String url, Map<String, String> params) {
        return doGet(url, params, null);
    }

    /**
     * @param url
     * @param charset
     * @return
     */
    public String doPost(String url, Map<String, String> params, String charset) {

        String result=null;
        //1.判断字符集编码是否为null；
        if (StringUtils.isEmpty(charset)) {
            charset = "utf-8";
        }
        //2.准备请求对象
        HttpPost httpPost = new HttpPost(url);

        httpPost.setConfig(requestConfig);

        try {
            //3.判断参数是否为null
            if (params != null) {
                List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    BasicNameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
                    list.add(nameValuePair);
                }
                //封装实体对象
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                System.out.println("entity"+entity.toString());
                httpPost.setEntity(entity);
                //将实体对象添加到请求中

            }
            //发送post请求
            System.out.println("httpPost"+httpPost);
            CloseableHttpResponse httpRespones = httpClient.execute(httpPost);
            System.out.println("httpRespones"+httpRespones);
            if(httpRespones.getStatusLine().getStatusCode()==200){
                result=EntityUtils.toString(httpRespones.getEntity(),charset);
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

      return result;

}

    public String doPost(String url, Map<String, String> params) {
        return doPost(url, params, null);
    }
}
