package infoshare.services.roles.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.Role;
import infoshare.restapi.Roles.RolesAPI;
import infoshare.restapi.common.demographics.RoleAPI;
import infoshare.services.roles.RoleService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by hashcode on 2015/06/24.
 */

@Service
@SpringComponent
public class RoleServiceImpl implements RoleService{


    @Override
    public Role findById(String s) {
        return RolesAPI.findById(s);
    }

    @Override
    public Role save(Role entity) {
        return RolesAPI.save(entity);
    }

    @Override
    public Role update(Role entity) {
        return RolesAPI.update(entity);
    }

    @Override
    public void delete(Role entity) {
        RolesAPI.update(entity);
    }

    @Override
    public Set<Role> findAll() {
        return RolesAPI.findAll();
    }
}
