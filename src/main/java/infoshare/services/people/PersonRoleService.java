/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoshare.services.people;



import infoshare.domain.person.PersonRole;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface PersonRoleService {
    Set<PersonRole> findPersonRoles(String personId);

    PersonRole save(PersonRole entity);

    PersonRole update(PersonRole entity);

    void delete(PersonRole entity);
}
