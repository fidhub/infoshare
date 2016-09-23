package infoshare.services.demographics.Impl;


import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.demographics.Role;
import infoshare.restapi.common.demographics.RoleAPI;
import infoshare.services.demographics.RolesListService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by hashcode on 2015/08/18.
 */
@SpringComponent
@Service
public class RolesListServiceImpl implements RolesListService {
    private static RolesListServiceImpl rolesListService=null;

    private RolesListServiceImpl(){}

    public  static RolesListServiceImpl getInstance(){
        if(rolesListService==null) {
            return new RolesListServiceImpl();
        }
        return rolesListService;
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
