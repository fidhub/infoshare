package infoshare.services.users.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.app.conf.RestUtil;
import infoshare.restapi.RestApiConnectorClass;
import infoshare.domain.User;
import infoshare.restapi.common.CommonBaseURI;
import infoshare.services.users.UserService;
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
        return RestUtil.getById(CommonBaseURI.).read(UrlPath.UserLinks.GET_ID,s,User.class);
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
