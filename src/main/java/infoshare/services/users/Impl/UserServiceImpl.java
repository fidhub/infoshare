package infoshare.services.users.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.RestApi.RestApiConnectorClass;
import infoshare.RestApi.UrlPath;
import infoshare.client.content.setup.models.UserModel;
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
    public User save(User user) {
        return  RestApiConnectorClass.create(UrlPath.UserLinks.POST, user, User.class);
    }

    @Override
    public User merge(User user) {
        return RestApiConnectorClass.update(UrlPath.UserLinks.PUT,user);
    }

    @Override
    public void remove(User user) {

    }

    @Override
    public List<User> findAll() {
        return RestApiConnectorClass.readAll(UrlPath.UserLinks.GETALL,User.class);
    }
}
