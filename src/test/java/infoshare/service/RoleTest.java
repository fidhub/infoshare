package infoshare.service;

import infoshare.domain.Role;
import infoshare.services.roles.Impl.RoleServiceImpl;
import infoshare.services.roles.RoleService;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Songezo on 2015-10-02.
 */
public class RoleTest extends TestCase{

    private RoleService roleService = new RoleServiceImpl();

    @Test
    public void testCreate() throws Exception {
        List<Role> roles = new ArrayList<>();
        Role role = new Role.Builder().description("System Adminstrator").build();
        Assert.assertNotNull(roles.addAll(roles));
    }

    @Test
    public void testReadAll() throws Exception {
        Set<Role> roleList = roleService.findAll();
        Assert.assertTrue(!roleList.isEmpty());
    }

    @Test
    public void testUpdate() throws Exception {
        Role role1 = roleService.findById("5f6d90347283ecc51bbcec07706d5261");
        Role roleUpdate = new Role.Builder().copy(role1).description("edits the content").build();

        roleService.update(roleUpdate);
        Role roleUpdated = roleService.findById("5f6d90347283ecc51bbcec07706d5261");
        Assert.assertEquals(role1.getDescription(), roleUpdated.getDescription());
    }
}
