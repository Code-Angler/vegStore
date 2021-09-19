package com.test.vegstore;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Utils {

    public HttpResponse getHttpResponse(String url, String Authorization) throws IOException {

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(url);
        getRequest.addHeader("Authorization", Authorization);
        HttpResponse response = client.execute(getRequest);

        return response;
    }

    public String jsonArrayResponse(HttpResponse response) throws IOException {
        HttpEntity responseEntity = response.getEntity();
        String json_string = EntityUtils.toString(responseEntity);
        return json_string;
    }

    public JSONObject parse(String json_string) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(json_string);
        return jsonObject;
    }
}
