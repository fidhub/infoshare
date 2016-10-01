/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoshare.services.people.Impl;



import com.vaadin.spring.annotation.SpringComponent;
import infoshare.domain.person.PersonRole;
import infoshare.restapi.people.PersonRoleAPI;
import infoshare.services.people.PersonRoleService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author BONGANI
 */
@Service
@SpringComponent
public class PersonRoleServiceImpl implements PersonRoleService {
    private static PersonRoleServiceImpl personRoleService =null;

    private PersonRoleServiceImpl(){}

    public  static PersonRoleServiceImpl getInstance(){
        if(personRoleService ==null) {
            return new PersonRoleServiceImpl();
        }
        return personRoleService;
    }
    @Override
    public PersonRole save(PersonRole entity) {
        return PersonRoleAPI.save(entity);
    }

    @Override
    public PersonRole update(PersonRole entity) {
        return PersonRoleAPI.save(entity);
    }

    @Override
    public void delete(PersonRole entity) {
        PersonRoleAPI.save(entity);
    }

    @Override
    public PersonRole findById(String personId, String roleId) {
        return PersonRoleAPI.findById(personId,roleId);
    }

    @Override
    public Set<PersonRole> findPersonRoles(String personId) {
        return PersonRoleAPI.findAll(personId);
    }

}
