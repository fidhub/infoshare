package infoshare.services.roles.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.RestApi.RestApiConnectorClass;
import infoshare.RestApi.UrlPath;
import infoshare.domain.Role;
import infoshare.services.roles.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hashcode on 2015/06/24.
 */

@Service
@SpringComponent
public class RoleServiceImpl implements RoleService{

    @Override
    public Role find(String s) {
        return RestApiConnectorClass.read(UrlPath.RoleLinks.GET_ID,s,Role.class);
    }

    @Override
    public Role save(Role entity) {
        return RestApiConnectorClass.create(UrlPath.RoleLinks.POST,entity,Role.class);
    }

    @Override
    public Role merge(Role entity) {
        return RestApiConnectorClass.update(UrlPath.RoleLinks.PUT,entity);
    }

    @Override
    public void remove(Role entity) {
    }

    @Override
    public List<Role> findAll() {
        return RestApiConnectorClass.readAll(UrlPath.RoleLinks.GETALL,Role.class);
    }
}
