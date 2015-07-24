package infoshare.restApi;

import infoshare.RestApi.RestApiConnectorClass;
import infoshare.RestApi.UrlPath;
import infoshare.client.content.setup.models.UserModel;
import infoshare.client.content.systemValues.models.CategoryModel;
import infoshare.domain.Category;
import infoshare.domain.Role;
import infoshare.services.roles.Impl.RoleServiceImpl;
import infoshare.services.roles.RoleService;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by codex on 2015/07/21.
 */

public class RestApiCategoryTest {
    // Category id1: 9673d201a00487f061351d12fe4d7d10
    // Category id2: 52b67c6cd00b98f53caf61bd227bfb7e
    // Category id3: eb6888b9f53a17508e5e95673e1010c6

    @Test
    public void testPost() throws Exception {

        CategoryModel model  = new CategoryModel();
        model.setName("kjkjdjk");
        model.setDescription("sddsdsdsdsd");
        RestApiConnectorClass.create(UrlPath.CategoryLinks.POST, model);

       }
    @Test
    public void testPut() throws Exception {

        Category model  = new Category.Builder("Hiv treatment")
                .description("how to take your daily treatment").id("52b67c6cd00b98f53caf61bd227bfb7e").build();
        RestApiConnectorClass.update(UrlPath.CategoryLinks.PUT, model);

       }

    @Test
    public  void testGet() throws Exception {
     Category category =   RestApiConnectorClass.read(UrlPath.CategoryLinks.GET_ID,
             "9673d201a00487f061351d12fe4d7d10", Category.class);
         System.out.println(category.getDescription() + "\n" + category.getName() + "\n" + category.getId());

    }

    @Test
    public void testGetAll() throws Exception {
        List<Category> categories = RestApiConnectorClass.readAll(UrlPath.CategoryLinks.GETALL, Category.class);
        System.out.println(categories.isEmpty());
    }
}
