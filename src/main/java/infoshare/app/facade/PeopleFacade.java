/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoshare.app.facade;


import infoshare.services.people.Impl.PersonRoleServiceImpl;
import infoshare.services.people.Impl.PersonServiceImpl;
import infoshare.services.people.PersonRoleService;
import infoshare.services.people.PersonService;

public class PeopleFacade {


    public final static PersonRoleService personRoleService = new PersonRoleServiceImpl();
    public final static PersonService personService = new PersonServiceImpl();






}
