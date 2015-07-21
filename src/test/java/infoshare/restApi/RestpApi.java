package infoshare.restApi;

import infoshare.client.content.setup.models.RoleModel;
import infoshare.domain.Content;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.ContentModel;

/**
 * Created by codex on 2015/07/21.
 */

public class RestpApi {

    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testRest() throws Exception {

        String url = "http://10.47.2.247:9000/api/role/create";
        ContentModel contentModel  = new ContentModel();
        role.setRolename("thule");
        role.setDescription("ddddd");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RoleModel> entity = new HttpEntity<>(role,headers);

        String cont = restTemplate.postForObject(url, entity, String.class);
        System.out.println(cont);
       }

}
