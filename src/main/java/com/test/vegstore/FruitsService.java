package com.test.vegstore;


import com.test.vegstore.exception.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FruitsService {


    public JSONObject selectFruits()throws Exception {


        URL url = new URL("http://fruit.api.postype.net/token");
        BufferedReader bf;
        bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

        String result = bf.readLine();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(result);

        return jsonObject;


    }

    public List<Object> listFruits(String Authorization) throws ApiRequestException {
        String url = "http://fruit.api.postype.net/product";

        try {
            CloseableHttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
            HttpGet getRequest = new HttpGet(url);
            getRequest.addHeader("Authorization", Authorization);
            HttpResponse response = client.execute(getRequest);

            HttpEntity responseEntity = response.getEntity();
            String json_string = EntityUtils.toString(responseEntity);

            JSONArray jsonArray = new JSONArray(json_string);
//            System.out.println(jsonArray);

            //Response 출력
            if (response.getStatusLine().getStatusCode() == 200) {
                return jsonArray.toList();

            } else {
                throw new ApiRequestException("토큰이 올바르지 않습니다.");
            }

        } catch (Exception e){
            throw new ApiRequestException("요청이 올바르지 않습니다.");
        }

    }

    public JSONObject searchFruits(String Authorization, String name) {
        String url = "http://fruit.api.postype.net/product?name=" + name;

        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet getRequest = new HttpGet(url);
            getRequest.addHeader("Authorization", Authorization);
            HttpResponse response = client.execute(getRequest);

            HttpEntity responseEntity = response.getEntity();
            String json_string = EntityUtils.toString(responseEntity);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(json_string);

            //Response 출력
            if (response.getStatusLine().getStatusCode() == 200) {
                return jsonObject;

            } else {
                throw new ApiRequestException("토큰이 올바르지 않습니다.");
            }

        } catch (Exception e){
            throw new ApiRequestException("요청이 올바르지 않습니다.");
        }
    }

}
