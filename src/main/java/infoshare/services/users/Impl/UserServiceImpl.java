package infoshare.services.users.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.services.users.UserService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by hashcode on 2015/06/24.
 */
@Service
@SpringComponent
public class UserServiceImpl implements UserService{

    @Override
    public User findById(String s) {
        return null;
    }

    @Override
    public User save(User entity) {
        return null;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public Set<User> findAll() {
        return null;
    }
}
