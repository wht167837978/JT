package com.jt.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.jt.web.pojo.TbItem;
import com.jt.web.pojo.TbItemDesc;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class itemserviceImpl implements itemservice {
//    @Autowired
//    private HttpClientService httpClientService;
//    @Autowired
//    private RequestConfig requestConfig;
    private static ObjectMapper objectMapper=new ObjectMapper();
    public TbItem findTtemById(Long itemId) {
        System.out.println("service:itemId:"+itemId);
        TbItem tbItem=null;
        try {
            String url="http://manage.jt.com/web/item/"+itemId;
            //实例话HttpClinent
            CloseableHttpClient client = HttpClients.createDefault();

            HttpGet get = new HttpGet(url);
//            String method = get.getRequestLine().getMethod();
//            get.setConfig(requestConfig);

            CloseableHttpResponse response = client.execute(get);

            if (response.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(response.getEntity());
                tbItem = objectMapper.readValue(result, TbItem.class);
                System.out.println("service::"+tbItem);
            }
//            String result = httpClientService.doGet(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbItem;
    }

    public TbItemDesc findTemdescById(Long itemid) {
        TbItemDesc tbItemDesc=null;
        try {
            String url="http://manage.jt.com/web/item/desc/"+itemid;
            //实例话HttpClinent
            CloseableHttpClient client = HttpClients.createDefault();

            HttpGet get = new HttpGet(url);
//            String method = get.getRequestLine().getMethod();
//            get.setConfig(requestConfig);

            CloseableHttpResponse response = client.execute(get);

            if (response.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(response.getEntity());
                tbItemDesc = objectMapper.readValue(result, TbItemDesc.class);

            }
//            String result = httpClientService.doGet(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbItemDesc;
    }
}
