package infoshare.service;

import infoshare.domain.User;
import infoshare.services.users.Impl.UserServiceImpl;
import infoshare.services.users.UserService;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Songezo on 2015-10-01.
 */
public class UsersTest extends TestCase {

    private UserService userService = new UserServiceImpl();

    @Test
    public void testCreate() throws Exception {
        List<User> users = new ArrayList<User>();
        User user = new User.Builder(getName()).othername("Songz").username("smkumatela")
                .enable(true).password("Sora-manizo").build();
//    User user1 = new User.Builder(getName()).othername("Thules").username("thadebe")
//            .enable(true).password("2lehHD").build();
        Assert.assertNotNull(users.addAll(users));
    }

    @Test
    public void testReadAll() throws Exception {
        List<User> userss = userService.findAll();
        System.out.print(userss.get(0).getId());
        Assert.assertTrue(!userss.isEmpty());
    }

    @Test
    public void testUpdate() throws Exception {
        User user = userService.find("efc53f54b95151428c15195800572a0e");
        User userUpdate = new User.Builder(getName()).copy(user).username("mimi").build();

        userService.merge(userUpdate);
        User checkUpdated = userService.find("efc53f54b95151428c15195800572a0e");
        Assert.assertEquals(user.getUsername(), checkUpdated.getUsername());
    }
}
