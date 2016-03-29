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
import org.mozilla.javascript.tools.idswitch.FileBody;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.swing.ImageIconUIResource;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * Created by THULEH on 2016/03/24.
 */
public class UploadPicture implements Upload.SucceededListener, Upload.Receiver {
    public Upload upload = new Upload("Choose File", this);
    public final Embedded image = new Embedded("Uploaded Image");
    private File file;
    private String mime;

    public UploadPicture() {
        this.upload = new Upload("Upload:", this);
        this.upload.addSucceededListener(this);
        image.setVisible(false);
    }


    @Override
    public OutputStream receiveUpload(String filename, String MIMEType) {
        mime = MIMEType;
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
            Resource resource = new FileSystemResource(file.getPath());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
            //parts.add("Content-Type", "image/jpeg");
            parts.add("file", resource);

            HttpEntity<MultiValueMap<String, Object>> body = new HttpEntity<>(parts,headers);
            FileResults fileResults= restTemplate.postForObject(UploadBaseURL.Media.POST, body, FileResults.class);
            System.out.println(fileResults.getUrl());

        }catch (Exception e) {
            e.printStackTrace();
        }
      // restTemplate.postForLocation(UploadBaseURL.Media.POST, HttpMethod.POST, parts, FileResults.class);

  /*
            try {

                File destinationFile = new File("/api/static/"+event.getFilename());
                if(!destinationFile.exists()){
                    destinationFile.createNewFile();
                }
                URL url = new URL(RestUtil.URL);
                FileUtils.copyURLToFile(url,destinationFile);
                Map<String,String> stringHashMap = new HashMap<>();
                stringHashMap.put("url",destinationFile.getPath()+"/"+event.getFilename());
                stringHashMap.put("mime",mime);
                Set<String> size = new HashSet<>();
                size.add("Original");
                FileResults fileResults = FileResultsFactory.getFileResults(stringHashMap,size);
                FileResultsAPI.save(fileResults);
                // TODO read and parse destinationFile
            } catch (IOException e) {
                e.printStackTrace();
            }*/

    }
}

