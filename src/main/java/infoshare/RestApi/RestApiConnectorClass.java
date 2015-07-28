package infoshare.RestApi;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
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
import java.util.Objects;

/**
 * Created by codex on 2015/06/30.
 */
public class RestApiConnectorClass {

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
                list.add(myGson.fromJson(Element, classType));
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return list;
    }
    public static <T> T read(String fetchUrl, String ID, Class<T> classType){
        try
        {
            JsonParser jsonParser = new JsonParser();
            JsonReader reader = new JsonReader(new InputStreamReader(openConnection(fetchUrl+ID).getInputStream()));
            JsonElement element = jsonParser.parse(reader);
            Gson gson = new Gson();
            return (gson.fromJson(element,classType));
        }
        catch (Exception e)
        {
            e.getMessage();
            return null;
        }
    }

    public static <T> T create(String url,T classTypeObject, Class<T> classType){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> entity = new HttpEntity<>(classTypeObject,headers);

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(restTemplate.postForObject(url, entity, String.class)).getAsJsonObject();
        Gson gson = new Gson();

        return gson.fromJson(element,classType);
    }
    public static <T> T update(String url, T classType){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> entity = new HttpEntity<>( classType,headers);
        
        restTemplate.put(url, entity);

        return classType;
    }

}
