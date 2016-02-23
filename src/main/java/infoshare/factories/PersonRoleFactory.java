/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoshare.factories;


import infoshare.app.util.DomainState;
import infoshare.domain.PersonRole;

/**
 *
 * @author BONGANI
 */
public class PersonRoleFactory {
    public static PersonRole getPersonRole(String personId, String roleId)
    {
        PersonRole personRole = new PersonRole.Builder()
                .state(DomainState.ACTIVE.name())
                .personId(personId)
                .roleId(roleId)               
                .build();
      return personRole;
    
    }
    
}
