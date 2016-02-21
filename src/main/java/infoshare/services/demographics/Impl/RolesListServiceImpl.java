package infoshare.services.demographics.Impl;


import infoshare.domain.Role;
import infoshare.restapi.common.demographics.RoleAPI;
import infoshare.services.demographics.RolesListService;

import java.util.Set;

/**
 * Created by hashcode on 2015/08/18.
 */
public class RolesListServiceImpl implements RolesListService {

    @Override
    public Role getRole(String role) {
        return null;
    }

    @Override
    public Role findById(String s) {
        return RoleAPI.findById(s);
    }

    @Override
    public Role save(Role entity) {
        return RoleAPI.save(entity);
    }

    @Override
    public Role update(Role entity) {
        return RoleAPI.save(entity);
    }

    @Override
    public void delete(Role entity) {
        RoleAPI.save(entity);

    }

    @Override
    public Set<Role> findAll() {
        return RoleAPI.findAll();
    }
}
