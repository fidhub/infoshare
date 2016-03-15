/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoshare.app.facade;



import infoshare.services.people.Impl.*;
import infoshare.services.people.*;

public class PeopleFacade {

    public final static PersonRoleService personRoleService =  PersonRoleServiceImpl.getInstance();
    public final static PersonService personService =  PersonServiceImpl.getInstance();
    public final static PersonAddressService personAddressService =  PersonAddressServiceImpl.getInstance();
    public final static PersonContactService personContactService =  PersonContactServiceImpl.getInstance();
    public final static PersonDemographicsService personDemographicsService =  PersonDemographicsServiceImpl.getInstance();
    public final static PersonImagesService personImagesService =  PersonImagesServiceImpl.getInstance();
    public final static PersonLanguageService personLanguageService =  PersonLanguageServiceImpl.getInstance();

}
