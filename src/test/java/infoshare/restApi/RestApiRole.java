package infoshare.restApi;

import infoshare.RestApi.RestApiConnectorClass;
import infoshare.RestApi.UrlPath;
import infoshare.client.content.setup.models.RoleModel;
import infoshare.domain.Role;
import org.junit.Test;


/**
 * Created by user9 on 2015/07/24.
 */
public class RestApiRole {
    //Id 1: 10c054b7affabf2dd161aeebbdb761a0
    //Id 2: 37a76ed4546bd88115d0d2a65d0cf57c
    @Test
    public void testPost() throws Exception {

        RoleModel roleModel = new RoleModel();
        roleModel.setRoleName("Editor");
        roleModel.setDescription("editor of the system");
        RestApiConnectorClass.create(UrlPath.RoleLinks.POST, roleModel);


    }
    @Test
    public void testPut() throws Exception {
        Role role = RestApiConnectorClass.read(UrlPath.RoleLinks.GET_ID,"37a76ed4546bd88115d0d2a65d0cf57c",Role.class);

        System.out.println(role.getRoleName()+"\n"+role.getDescription());
    }

    @Test
    public  void testGet() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {

    }
}
