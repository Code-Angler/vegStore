package com.test.vegstore.service;


import com.test.vegstore.Utils;
import com.test.vegstore.exception.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FruitsService {

    private final Utils utils;

    public JSONObject selectFruits() throws Exception {

        URL url = new URL("http://fruit.api.postype.net/token");
        BufferedReader bf;
        bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
        String result = bf.readLine();

        return utils.parse(result);

    }

    public List<Object> listFruits(String Authorization) throws ApiRequestException {

        String url = "http://fruit.api.postype.net/product";

        try {
            HttpResponse response = utils.getHttpResponse(url, Authorization);

            String json_string = utils.jsonArrayResponse(response);

            JSONArray jsonArray = new JSONArray(json_string);

            if (response.getStatusLine().getStatusCode() == 200) {
                return jsonArray.toList();
            } else {
                throw new ApiRequestException("요청이 올바르지 않습니다.");
            }
        } catch (Exception e) {
            throw new ApiRequestException("토큰이 올바르지 않습니다.");
        }

    }

    public JSONObject searchFruits(String Authorization, String name) {

        String url = "http://fruit.api.postype.net/product?name=" + name;

        try {
            HttpResponse response = utils.getHttpResponse(url, Authorization);

            String json_string = utils.jsonArrayResponse(response);

            //Response 출력
            if (response.getStatusLine().getStatusCode() == 200) {
                return utils.parse(json_string);
            } else {
                throw new ApiRequestException("요청이 올바르지 않습니다.");
            }
        } catch (Exception e) {
            throw new ApiRequestException("토큰이 올바르지 않습니다.");
        }
    }

}
