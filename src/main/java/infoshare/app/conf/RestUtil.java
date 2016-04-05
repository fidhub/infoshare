package infoshare.app.conf;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import infoshare.domain.storage.FileResults;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hashcode on 2015/11/10.
 */
public class RestUtil {
    public static final String URL = "http://10.47.2.247:9000";

    private static URLConnection openConnection(String fetchUrl) {
        URLConnection urlConnection = null;
        try {
            urlConnection = new URL(fetchUrl).openConnection();
            urlConnection.connect();
        } catch (IOException e) {
            e.getMessage();
        }
        return urlConnection;
    }

    public static <T> Set<T> getAll(String fetchUrl, Class<T> classType) {
        Set<T> list = new HashSet<>();
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(openConnection(fetchUrl).getInputStream()));
            JsonParser jsonParser = new JsonParser();
            JsonArray json = jsonParser.parse(reader).getAsJsonArray();
            Gson gson = new GsonBuilder().registerTypeAdapter(Date.class,
                    (JsonDeserializer<Date>) (jsonElement, type,
                                              jsonDeserializationContext) -> new Date(jsonElement.getAsJsonPrimitive().getAsLong()))
                    .create();

            for (JsonElement element : json) {
                list.add(gson.fromJson(element, classType));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    public static <T> Set<T> getFileResults(String fetchUrl,Class<T> tClass) throws IOException {
        Set<T> fileResults = new HashSet<>();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("upload", new FileSystemResource("/home/user9/upload.png"));

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);

        ResponseEntity<FileResults[]> result = restTemplate.exchange(fetchUrl, HttpMethod.POST, request, FileResults[].class);

        return fileResults;
    }
    public static <T> T getById(String fetchUrl, String ID, Class<T> classType) {
        try {
            JsonParser jsonParser = new JsonParser();
            JsonReader reader = new JsonReader(new InputStreamReader(openConnection(fetchUrl + ID).getInputStream()));
            JsonElement element = jsonParser.parse(reader);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (jsonElement, type, jsonDeserializationContext) ->
                            new Date(jsonElement.getAsJsonPrimitive().getAsLong()))
                    .create();

            return gson.fromJson(element, classType);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    public static <T> T save(String url, T classTypeObject, Class<T> classType) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> body = new HttpEntity<>(classTypeObject, headers);
        JsonElement element = new JsonParser().parse(restTemplate.postForObject(url, body, String.class))
                .getAsJsonObject();
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class,
                   (JsonDeserializer<Date>)
                   (jsonElement, type, jsonDeserializationContext) ->
                    new Date(jsonElement.getAsJsonPrimitive().getAsLong()))
                .create();
        return gson.fromJson(element, classType);
    }

    public static <T> T update(String url, T classType) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> body = new HttpEntity<>(classType, headers);
        restTemplate.put(url, body);
        return classType;
    }




}
