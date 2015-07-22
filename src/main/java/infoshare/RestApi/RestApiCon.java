package infoshare.RestApi;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import infoshare.client.content.systemValues.models.CategoryModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by codex on 2015/06/30.
 */
public class RestApiCon{

    private static URLConnection openConnection(String fetchUrl){
        URLConnection urlConnection = null;
        try {
            urlConnection =  new URL(fetchUrl).openConnection();
            urlConnection.connect();
        } catch (IOException e) {
             e.getMessage();
        }
        return urlConnection;
    }
    public static <T> List<T> readAll(String fetchUrl,Class<T> classType) {

        List<T> list = new ArrayList<>();
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(openConnection(fetchUrl).getInputStream()));
            JsonParser jsonParser = new JsonParser();
            JsonArray json = jsonParser.parse(reader).getAsJsonArray();
            Gson myGson = new Gson();
            for (JsonElement Element : json) {
                classType = (Class<T>) myGson.fromJson(Element, classType);
                list.add((T) classType);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return list;
    }
    public static CategoryModel read(String fetchUrl, String ID){
        CategoryModel n = null;
        try {

            JsonParser jsonParser = new JsonParser();
            Gson myGson = new Gson();

            JsonReader reader = new JsonReader(new InputStreamReader(openConnection(fetchUrl+ID).getInputStream()));
            JsonElement element = jsonParser.parse(reader);
            n  = myGson.fromJson(element, CategoryModel.class);

        }catch (Exception e){
            e.getMessage();
        }
        return n;

    }
    public static <T> void create(String url,Class<T> classType){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> entity = new HttpEntity<>((T) classType,headers);

        String  n= restTemplate.postForObject(url, entity, String.class);

        System.out.println(n);
    }
}
