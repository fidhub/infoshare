package infoshare.restApi;

import infoshare.RestApi.RestApiConnectorClass;
import infoshare.RestApi.UrlPath;
import infoshare.client.content.setup.models.AddressModel;
import infoshare.client.content.setup.models.ContactModel;
import infoshare.client.content.setup.models.UserModel;
import infoshare.domain.Address;
import infoshare.domain.Contact;
import infoshare.domain.Role;
import infoshare.services.roles.Impl.RoleServiceImpl;
import infoshare.services.roles.RoleService;
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
        for (Role role:roleServ.findAll() )
        {
            roles.add(role.getId());
        }
        user.setRole(roles);

        List<String> contacts = new ArrayList<>();
        ContactModel model = new ContactModel();
        model.setPhone("222");
        model.setEmail("111");
        model.setContactType("jhdjyhuwe");
        contacts.add(model.getPhone());
        user.setContact(contacts);

        List<String> address = new ArrayList<>();
        AddressModel addressModel = new AddressModel();
        addressModel.setAddressType("4547");
        addressModel.setPhysicalAddress("4455555");
        addressModel.setPostalAddress("djhf");
        addressModel.setPostalCode("35656");
        address.add(addressModel.getPhysicalAddress());
        user.setAddress(address);

        RestApiConnectorClass.create(UrlPath.UserLinks.POST, user);


    }
    @Test
    public void testPut() throws Exception {


    }

    @Test
    public  void testGet() throws Exception {


    }

    @Test
    public void testGetAll() throws Exception {

    }
}
