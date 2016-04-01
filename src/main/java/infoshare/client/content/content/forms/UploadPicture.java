package infoshare.client.content.content.forms;

import com.vaadin.ui.Upload;
import infoshare.restapi.storage.UploadBaseURL;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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

