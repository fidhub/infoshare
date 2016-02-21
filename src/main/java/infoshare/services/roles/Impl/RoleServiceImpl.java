package infoshare.services.roles.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.restapi.RestApiConnectorClass;
import infoshare.restapi.UrlPath;
import infoshare.domain.Role;
import infoshare.services.roles.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by hashcode on 2015/06/24.
 */

@Service
@SpringComponent
public class RoleServiceImpl implements RoleService{


    @Override
    public Role findById(String s) {
        return null;
    }

    @Override
    public Role save(Role entity) {
        return null;
    }

    @Override
    public Role update(Role entity) {
        return null;
    }

    @Override
    public void delete(Role entity) {

    }

    @Override
    public Set<Role> findAll() {
        return null;
    }
}
