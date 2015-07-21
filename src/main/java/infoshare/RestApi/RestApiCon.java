package infoshare.RestApi;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.vaadin.ui.Notification;
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
public class RestApiCon {

    public static URLConnection openCon(String fetchUrl){
        URLConnection urlConnection = null;
        try {
            urlConnection =  new URL(fetchUrl).openConnection();
            urlConnection.connect();
        } catch (IOException e) {
            Notification.show("Connection could not be opened", Notification.Type.HUMANIZED_MESSAGE);
        }
        return urlConnection;
    }
    public static <T> List<T> read(String fetchUrl,Class<T> classType) {

        List<T> list = new ArrayList<>();
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(openCon(fetchUrl).getInputStream()));
            JsonParser jsonParser = new JsonParser();
             JsonArray json = jsonParser.parse(reader).getAsJsonArray();

            Gson myGson = new Gson();
            for (JsonElement Element : json) {
                classType = (Class<T>) myGson.fromJson(Element, classType);
                list.add((T) classType);
            }
        } catch (Exception e) {
            Notification.show(e.getMessage(), Notification.Type.TRAY_NOTIFICATION);

        }
        return list;
    }
    public static <T> Class<T> create(String url,Class<T> classType){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> entity = new HttpEntity<>((T) classType,headers);

        classType = restTemplate.postForObject(url, entity, String.class);

        return classType;
    }
}
