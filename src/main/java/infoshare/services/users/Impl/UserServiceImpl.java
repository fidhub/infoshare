package infoshare.services.users.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.Role;
import infoshare.domain.User;
import infoshare.services.roles.Impl.RoleServiceImpl;
import infoshare.services.roles.RoleService;
import infoshare.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by hashcode on 2015/06/24.
 */
@Service
@SpringComponent
public class UserServiceImpl implements UserService{

    static Map<String,User> users = null ;
    @Autowired
    private RoleService roleService = new RoleServiceImpl();

    public UserServiceImpl() {
        Set<Role> roles = new HashSet<>();
            roles.add(roleService.find("1"));
            roles.add(roleService.find("2"));

        if(users == null) {
            users = new HashMap<>();
            User user = new User.Builder("Hadebe")
                    .firstname("Thulebona")
                    .othername("Emmanuel")
                    .role(roles)
                    .id("1")
                    .enable(true)
                    .username("thuleh")
                    .build();
            users.put(user.getId(),user);
        }
    }
    @Override
    public User find(String s) {
        return users.get(s);
    }

    @Override
    public User save(User entity) {
        users.put(entity.getId(),entity);
        return users.get(entity.getId());
    }

    @Override
    public User merge(User entity) {
        users.put(entity.getId(),entity);
        return users.get(entity.getId());
    }

    @Override
    public void remove(User entity) {
        users.remove(entity.getId());
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
