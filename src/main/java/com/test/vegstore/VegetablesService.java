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

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VegetablesService {

    public HeaderDto selectVegetables()throws Exception {

        URL url = new URL("http://vegetable.api.postype.net/token");
        URLConnection urlConnection = url.openConnection();
        Map<String, List<String>> headers = urlConnection.getHeaderFields();
//        Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();
        List<String> cookie = headers.get("Set-Cookie");
        String strCookie = cookie.get(0);
        String last = strCookie.substring(strCookie.indexOf("=") + 1, strCookie.lastIndexOf(";"));
        HeaderDto headerDto = new HeaderDto(last);
        return headerDto;
//        System.out.println(cookie.get(0));
//        System.out.println(entrySet.toString());
//        for (Map.Entry<String, List<String>> entry : entrySet) {
//            String headerName = entry.getKey();
//            System.out.println("Header Name:" + headerName);
//            List<String> headerValues = entry.getValue();
//            for (String value : headerValues) {
//                System.out.print("Header value:" + value);
//            }
//            System.out.println();
//            System.out.println();
//        }
//        BufferedReader bf;
//        bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
//
//        String result = bf.readLine();
//
//        JSONParser jsonParser = new JSONParser();
//        JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
//
//        return jsonObject;

//        String url = "http://vegetable.api.postype.net/token";
//
//
//        try {
//            CloseableHttpClient client = HttpClientBuilder.create().build();
//            HttpGet getRequest = new HttpGet(url);
//            System.out.println(getRequest);
//            HttpResponse response = client.execute(getRequest);
//            CloseableHttpResponse httpResponse = client.execute(getRequest);
////            System.out.println(EntityUtils.toString(response.getHeaders("Date"));
//            System.out.println(response.getHeaders("Set-Cookie").toString());
//            System.out.println(httpResponse);
//            System.out.println("GET Response Status");
//            System.out.println(httpResponse.getStatusLine().getStatusCode());
//            String json = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
//
//
//            HttpEntity responseEntity = response.getEntity();
//            System.out.println("======================");
//            System.out.println(EntityUtils.toString(responseEntity));
//            String json_string = EntityUtils.toString(responseEntity);
//            JSONParser jsonParser = new JSONParser();
//            JSONObject jsonObject = (JSONObject) jsonParser.parse(json_string);
//
//            //Response 출력
//            if (response.getStatusLine().getStatusCode() == 200) {
//                return jsonObject;
//
//            } else {
//                throw new ApiRequestException("토큰이 올바르지 않습니다.");
//            }
//
//        } catch (Exception e){
//            throw new ApiRequestException("요청이 올바르지 않습니다.");
//        }


    }

    public List<Object> listVegetables(String Authorization) throws ApiRequestException {
        String url = "http://vegetable.api.postype.net/item";

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

    public JSONObject searchVegetables(String Authorization, String name) {
        String url = "http://vegetable.api.postype.net/item?name=" + name;

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
