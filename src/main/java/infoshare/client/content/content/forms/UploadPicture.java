package infoshare.client.content.content.forms;

import com.vaadin.ui.Upload;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * Created by THULEH on 2016/03/24.
 */
public class UploadPicture implements Upload.SucceededListener, Upload.Receiver {
    public Upload upload ;
    private File file;

    public UploadPicture() {
        this.upload = new Upload("Upload:", this);
        this.upload.addSucceededListener(this);
    }


    @Override
    public OutputStream receiveUpload(String filename, String MIMEType) {
        try {
            file = File.createTempFile(filename,".jpg");
            file.deleteOnExit();
            return new FileOutputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent event) {
        try {

           /* URL url = this.getClass().getResource("src/main/webapp/VAADIN/themes/mytheme/favicon.ico");
            File f = new File(url.getFile());
            FileInputStream file = new FileInputStream(url.getFile());*/
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
            map.add("upload", new FileSystemResource("/home/user9/upload.png"));



            CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
            HttpComponentsClientHttpRequestFactory requestFactory =
                    new HttpComponentsClientHttpRequestFactory();
            requestFactory.setHttpClient(httpClient);

            RestTemplate restTemplate = new RestTemplate(requestFactory);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);

            restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            ResponseEntity<FileResults[]> result = restTemplate.exchange(UploadBaseURL.Media.POST, HttpMethod.POST, request, FileResults[].class);

            System.out.println("The results is " + result.getBody());

/*


            RestTemplate restTemplate = new RestTemplate();
            LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

            map.add("file", new FileSystemResource("/home/user9/upload.png"));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

            ResponseEntity<FileResults[]> result = restTemplate.exchange(UploadBaseURL.Media.POST, HttpMethod.POST, requestEntity, FileResults[].class);
            System.out.print(result.getBody());*/
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

