package infoshare.restApi;


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
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by hashcode on 2016/01/04.
 */
public class MediaRestApiTest {
    @Test
    public void testName() throws Exception {


        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

        URL url = this.getClass().getResource("/jobsclassification.xls");
        File f = new File(url.getFile());
        FileInputStream file = new FileInputStream(url.getFile());

        map.add("upload", new FileSystemResource(f));


        CloseableHttpClient httpClient =
                HttpClients.custom()
                        .setSSLHostnameVerifier(new NoopHostnameVerifier())
                        .build();
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);


        RestTemplate restTemplate = new RestTemplate(requestFactory);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        restTemplate.getMessageConverters().add(
                new ByteArrayHttpMessageConverter());

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);

        String urlp = "";

        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        ResponseEntity<FileResults[]> result = restTemplate.exchange(urlp, HttpMethod.POST, request, FileResults[].class);

        System.out.println("The results is " + result.getBody());

    }
}
