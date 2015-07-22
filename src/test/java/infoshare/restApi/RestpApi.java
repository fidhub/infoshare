package infoshare.restApi;

import infoshare.RestApi.RestApiCon;
import infoshare.RestApi.UrlPath;
import infoshare.client.content.systemValues.models.CategoryModel;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 * Created by codex on 2015/07/21.
 */

public class RestpApi {

    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testPost() throws Exception {



        CategoryModel model  = new CategoryModel();

        model.setName("kjkjdjk");
        model.setDescription("sddsdsdsdsd");

        RestApiCon.create(UrlPath.CategoryLinks.POST,CategoryModel.class);


       }

    @Test
    public void testGet() throws Exception {
        System.out.println(
                RestApiCon.read(UrlPath.CategoryLinks.GET_ID, "9673d201a00487f061351d12fe4d7d10")
        );
        System.out.println(RestApiCon.read(UrlPath.CategoryLinks.GET_ID, "9673d201a00487f061351d12fe4d7d10"));
    }
}
