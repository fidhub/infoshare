package infoshare.restApi;

import infoshare.RestApi.RestApiConnectorClass;
import infoshare.RestApi.UrlPath;
import infoshare.client.content.setup.models.AddressModel;
import infoshare.client.content.setup.models.ContactModel;
import infoshare.client.content.setup.models.UserModel;
import infoshare.domain.Address;
import infoshare.domain.Contact;
import infoshare.domain.Role;
import infoshare.domain.User;
import infoshare.services.roles.Impl.RoleServiceImpl;
import infoshare.services.roles.RoleService;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by user9 on 2015/07/24.
 */
public class RestApiUserTest {
    @Test
    public void testPost() throws Exception {

        UserModel user = new UserModel();
        user.setFirstName("John");
        user.setUsername("dev");
        user.setPassword("pass");
        user.setEnabled(true);
        user.setEmail("hghgh@gmail.com");
        user.setOtherName("haas");
        user.setLastName("jdjdjd");

        Set<String> roles = new HashSet<>();
        RoleService roleServ = new RoleServiceImpl();
        for (Role role : roleServ.findAll()) {
            roles.add(role.getId());
        }
        user.setRole(roles);

        List<String> contacts = new ArrayList<>();
        Contact model = new Contact.Builder("54554544545").id("f737d27fc2d4f303e318f8ac6ef95702").build();
        contacts.add(model.getId());
        user.setContact(contacts);

        List<String> address = new ArrayList<>();
        Address addressModel = new Address.Builder("shjdhsjjhdas")
                .id("f737d27fc2d4f303e318f8ac6ef95702").build();
        address.add(addressModel.getId());
        user.setAddress(address);

        System.out.println(user.getFirstName());
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
