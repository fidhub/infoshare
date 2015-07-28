package infoshare.restApi;

import infoshare.RestApi.RestApiConnectorClass;
import infoshare.RestApi.UrlPath;
import infoshare.client.content.setup.models.RoleModel;
import infoshare.domain.Role;
import junit.framework.TestCase;
import org.junit.*;
import java.util.List;


/**
 * Created by user9 on 2015/07/24.
 */
public class RestApiRole extends TestCase {
    //Id 1: 10c054b7affabf2dd161aeebbdb761a0
    //Id 2: 37a76ed4546bd88115d0d2a65d0cf57c
    @Test
    public void testPost() throws Exception {

        RoleModel roleModel = new RoleModel();
        roleModel.setRoleName("Editor");
        roleModel.setDescription("editor of the system");
        RestApiConnectorClass.create(UrlPath.RoleLinks.POST, roleModel.getClass());


    }
    @Test
    public void testPut() throws Exception {
        Role role = RestApiConnectorClass.read(UrlPath.RoleLinks.GET_ID,"37a76ed4546bd88115d0d2a65d0cf57c",Role.class);
        Assert.assertEquals(role.getRoleName(), "Editor");
    }

    @Test
    public  void testGet() throws Exception {
        Role role = RestApiConnectorClass.read(UrlPath.RoleLinks.GET_ID,"37a76ed4546bd88115d0d2a65d0cf57c",Role.class);
        Role role1 = new Role.Builder(role.getRoleName()).description("System Editor").build();
        Assert.assertEquals(role.getRoleName() , role1.getRoleName());
        Assert.assertNotEquals(role.getDescription(), role1.getDescription());

    }

    @Test
    public void testGetAll() throws Exception {
        List<Role> roles = RestApiConnectorClass.readAll(UrlPath.RoleLinks.GETALL,Role.class);
        Assert.assertFalse(roles.isEmpty());
    }
}
