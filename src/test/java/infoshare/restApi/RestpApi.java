package infoshare.restApi;

import com.google.gson.Gson;
import infoshare.RestApi.RestApiCon;
import infoshare.RestApi.UrlPath;
import infoshare.client.content.systemValues.models.CategoryModel;
import infoshare.domain.Category;
import org.junit.Test;

import java.util.List;

/**
 * Created by codex on 2015/07/21.
 */

public class RestpApi {

    @Test
    public void testPost() throws Exception {

        CategoryModel model  = new CategoryModel();
        model.setName("kjkjdjk");
        model.setDescription("sddsdsdsdsd");
        RestApiCon.create(UrlPath.CategoryLinks.POST,CategoryModel.class);

       }
    @Test
    public void testGet() throws Exception {
        Gson gson = new Gson();
        Category category = gson.fromJson(RestApiCon.read(UrlPath.CategoryLinks.GET_ID,
                "9673d201a00487f061351d12fe4d7d10"), Category.class);
        System.out.println(
                category.getDescription() + "\n" +
                        category.getName() + "\n" +
                        category.getId()
        );
        List<Category> categories = RestApiCon.readAll(UrlPath.CategoryLinks.GETALL, Category.class);
        System.out.println(categories.isEmpty());
    }
}
