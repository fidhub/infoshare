package infoshare.restApi;

import infoshare.RestApi.RestApiConnectorClass;
import infoshare.RestApi.UrlPath;
import infoshare.client.content.setup.models.UserModel;
import infoshare.domain.Role;
import infoshare.domain.User;
import infoshare.services.roles.Impl.RoleServiceImpl;
import infoshare.services.roles.RoleService;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by user9 on 2015/07/24.
 */
public class RestApiUserTest {
    @Test
    public void testPost() throws Exception {

        UserModel user = new UserModel();
        user.setOtherName("Emmanuel");
        user.setFirstName("Thulebona");
        user.setLastName("hadebe");
        user.setUsername("thule");
        user.setEnable(true);
        user.setPassword("pass");

        Set<String> roles = new HashSet<>();
        RoleService roleServ = new RoleServiceImpl();
        roles.addAll(roleServ.findAll().stream().map(Role::getId).collect(Collectors.toList()));
        user.setRoles(roles);
        //Todo   id:a699571541acbff8de8708d4b98e74d9   e6325fabbea69788146b73f856a296fb
      /*  List<String> contacts = new ArrayList<>();
        Contact model = new Contact.Builder("54554544545").id("f737d27fc2d4f303e318f8ac6ef95702").build();
        Contact model2 = new Contact.Builder("54554544545").id("f737d27fc2d4f303e318f8ac6ef95701").build();
        contacts.add(model.getId());
        contacts.add(model2.getId());
        user.setContact(contacts);*/

        /*List<String> address = new ArrayList<>();
        Address addressModel = new Address.Builder("shjdhsjjhdas")
                .id("f737d27fc2d4f303e318f8ac6ef95702").build();
        address.add(addressModel.getId());
        user.setAddress(address);*/

        RestApiConnectorClass.create(UrlPath.UserLinks.POST, user,UserModel.class);

    }
    @Test
    public void testPut() throws Exception {
    }

    @Test
    public  void testGet() throws Exception {
    }

    @Test
    public void testGetAll() throws Exception {
        List<User> users = RestApiConnectorClass.readAll(UrlPath.UserLinks.GETALL, User.class);
        Assert.assertTrue(users.isEmpty());
    }
}
