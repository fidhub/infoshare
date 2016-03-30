package infoshare.client.content.content.forms;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Upload;
import infoshare.app.conf.RestUtil;
import infoshare.domain.storage.FileResults;
import infoshare.factories.storage.FileResultsFactory;
import infoshare.restapi.ContentFiles.Media.MediaBaseURL;
import infoshare.restapi.storage.FileResultsAPI;
import infoshare.restapi.storage.UploadBaseURL;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.poi.ss.formula.functions.T;
import org.atmosphere.util.FilterConfigImpl;
import org.mozilla.javascript.tools.idswitch.FileBody;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.swing.ImageIconUIResource;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * Created by THULEH on 2016/03/24.
 */
public class UploadPicture implements Upload.SucceededListener, Upload.Receiver {
    public Upload upload = new Upload();
    private File file;

    public UploadPicture() {
        this.upload = new Upload("Upload:", this);
        this.upload.addSucceededListener(this);
    }


    @Override
    public OutputStream receiveUpload(String filename, String MIMEType) {
        try {
            file = File.createTempFile(filename, "jpg");
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
            RestTemplate restTemplate = new RestTemplate();
            LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
            map.add("file", new FileSystemResource(event.getSource().toString()));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

            ResponseEntity<String> result = restTemplate.exchange(UploadBaseURL.Media.POST, HttpMethod.POST, requestEntity, String.class);
            System.out.print(result);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

