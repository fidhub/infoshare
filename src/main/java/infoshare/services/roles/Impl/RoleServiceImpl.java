package infoshare.services.roles.Impl;

import com.vaadin.spring.annotation.SpringComponent;
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

    static Map<String, Role>  rolesValues =null;


    public RoleServiceImpl() {

        if(rolesValues ==null) {
            rolesValues = new HashMap<>();

            Role role0 = new Role.Builder("Nurse").description("Nursing Office").id("1").build();
            Role role1 = new Role.Builder("Admin").description("SystemAdmin").id("2").build();
            Role role2 = new Role.Builder("Editor").description("Content Editor").id("3").build();
            Role role3 = new Role.Builder("CareGiver").description("Care Giver").id("4").build();
            rolesValues.put(role0.getId(), role0);
            rolesValues.put(role1.getId(), role1);
            rolesValues.put(role2.getId(), role2);
            rolesValues.put(role3.getId(), role3);
        }
    }
    @Override
    public Role find(String s) {
        return rolesValues.get(s);
    }

    @Override
    public Role save(Role entity) {
        rolesValues.put(entity.getId(),entity);
        return rolesValues.get(entity.getId());
    }

    @Override
    public Role merge(Role entity) {
        rolesValues.put(entity.getId(),entity);
        return rolesValues.get(entity.getId());
    }

    @Override
    public void remove(Role entity) {
        rolesValues.remove(entity.getId());
    }

    @Override
    public List<Role> findAll() {
        return new ArrayList<>(rolesValues.values());
    }
}
