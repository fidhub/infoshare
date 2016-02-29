/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoshare.services.people.Impl;



import infoshare.app.facade.DemographicsFacade;
import infoshare.app.facade.PeopleFacade;
import infoshare.domain.person.Person;
import infoshare.domain.person.PersonRole;
import infoshare.domain.Role;
import infoshare.restapi.people.PersonAPI;
import infoshare.services.people.PersonService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author BONGANI
 */
public class PersonServiceImpl implements PersonService {

    @Override
    public Person save(Person entity) {
        return PersonAPI.save(entity);
    }

    @Override
    public Person update(Person entity) {
        return PersonAPI.save(entity);
    }

    @Override
    public void delete(Person entity) {
        PersonAPI.save(entity);
    }


    @Override
    public Person getUserByEmail(String email) {
        return PersonAPI.findByEmail(email);
    }

    @Override
    public Person getPersonById(String company, String id) {
        return PersonAPI.findById(company, id);
    }

    @Override
    public Set<Role> getRoles(String userId) {
        Set<Role> rolesList = new HashSet<>();
        Set<PersonRole> roles = PeopleFacade.personRoleService.findPersonRoles(userId);
        rolesList.addAll(roles.stream().map(role -> DemographicsFacade.rolesListService.findById(role.getRoleId())).collect(Collectors.toList()));
        return rolesList;
    }

    @Override
    public Set<Person> getPersonsWithRole(String company, String role) {
        return PersonAPI.findAll(company)
                .parallelStream()
                .filter(person -> isThisPersonInThis(person.getId(), role))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Person> getPersonByCompany(String company) {
        return PersonAPI.findAll(company);
    }

    @Override
    public boolean isThisPersonInThis(String personId, String role) {
        Role roleList = DemographicsFacade.rolesListService.getRole(role);
        Set<PersonRole> personRoles = PeopleFacade.personRoleService.findPersonRoles(personId);
        Set<String> roleIds = personRoles.parallelStream().map(r -> r.getRoleId()).collect(Collectors.toSet());
        return roleIds.contains(roleList.getId());
    }


}
