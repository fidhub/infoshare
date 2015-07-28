package infoshare.services.users.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.RestApi.RestApiConnectorClass;
import infoshare.RestApi.UrlPath;
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

    @Override
    public User find(String s) {
        return RestApiConnectorClass.read(UrlPath.UserLinks.GET_ID,s,User.class);
    }

    @Override
    public User save(User entity) {
       return RestApiConnectorClass.create(UrlPath.UserLinks.POST,entity,User.class);
    }

    @Override
    public User merge(User entity) {
        return RestApiConnectorClass.update(UrlPath.UserLinks.PUT,entity);
    }

    @Override
    public void remove(User entity) {

    }

    @Override
    public List<User> findAll() {
        return RestApiConnectorClass.readAll(UrlPath.UserLinks.GETALL,User.class);
    }
}
