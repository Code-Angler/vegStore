package com.test.vegstore.service;

import com.test.vegstore.Utils;
import com.test.vegstore.dto.HeaderDto;
import com.test.vegstore.exception.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VegetablesService {

    private final Utils utils;

    public HeaderDto selectVegetables() throws Exception {

        URL url = new URL("http://vegetable.api.postype.net/token");
        URLConnection urlConnection = url.openConnection();
        Map<String, List<String>> headers = urlConnection.getHeaderFields();
        List<String> setCookie = headers.get("Set-Cookie");
        String stringCookie = setCookie.get(0);
        String accessToken = stringCookie.substring(stringCookie.indexOf("=") + 1, stringCookie.lastIndexOf(";"));
        HeaderDto headerDto = new HeaderDto(accessToken);
        return headerDto;

    }

    public List<Object> listVegetables(String Authorization) throws ApiRequestException {

        String url = "http://vegetable.api.postype.net/item";

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

    public JSONObject searchVegetables(String Authorization, String name) {

        String url = "http://vegetable.api.postype.net/item?name=" + name;

        try {
            HttpResponse response = utils.getHttpResponse(url, Authorization);

            String json_string = utils.jsonArrayResponse(response);

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
