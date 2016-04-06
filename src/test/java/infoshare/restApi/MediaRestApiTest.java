package infoshare.restApi;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import infoshare.app.conf.RestUtil;
import infoshare.domain.storage.FileResults;
import infoshare.restapi.storage.UploadBaseURL;
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
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hashcode on 2016/01/04.
 */
public class MediaRestApiTest {
    @Test
    public void testName() throws Exception {
/*
        URL url =  this.getClass().getResource("src/main/webapp/VAADIN/themes/mytheme/favicon.ico");
        File f = new File(url.getFile());
        FileInputStream file = new FileInputStream(url.getFile());*/

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

        ResponseEntity<String> result = restTemplate.exchange(UploadBaseURL.Media.POST, HttpMethod.POST, request, String.class);
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(result.getBody().toString());
        List<FileResults> list = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            list.add(gson.fromJson(element, FileResults.class));
        }
        for (FileResults fileResults: list){
            System.out.println("The results is " + fileResults.getId());
            System.out.println("The results is " + fileResults.getUrl());
        }

    }


    @Test
    public void testRest() throws Exception {

       for (FileResults fileResults : RestUtil.getFileResults(UploadBaseURL.Media.POST,"/home/user9/upload.png", FileResults.class))
       {
           System.out.println("The results is " + fileResults.getId());
           System.out.println("The results is " + fileResults.getUrl());
       }

    }
}