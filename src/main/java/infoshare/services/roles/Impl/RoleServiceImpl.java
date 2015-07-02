package infoshare.services.roles.Impl;

import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.Role;
import infoshare.services.roles.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hashcode on 2015/06/24.
 */

@Service
@SpringComponent
public class RoleServiceImpl implements RoleService{
    List<Role> roles = new ArrayList<>();

    public void addValue(){

        Role role0 = new Role.Builder("Nurse").description("Nursing Office").id("1").build();
        Role role1 = new Role.Builder("Admin").description("SystemAdmin").id("2").build();
        Role role2 = new Role.Builder("Editor").description("Content Editor").id("3").build();
        Role role3 = new Role.Builder("CareGiver").description("Care Giver").id("4").build();
        roles.add(role0);
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);
    }
    @Override
    public Role find(String s) {
        addValue();
        Role role = null;
        for (Role role1: roles)
            if(role1.getId().equals(s))
                    role = role1;
        return role;
    }

    @Override
    public Role save(Role entity) {
        return null;
    }

    @Override
    public Role merge(Role entity) {
        return null;
    }

    @Override
    public void remove(Role entity) {

    }

    @Override
    public List<Role> findAll() {
        addValue();
        return roles;
    }
}
