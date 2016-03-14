/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoshare.app.facade;



import infoshare.services.people.Impl.*;
import infoshare.services.people.*;

public class PeopleFacade {

    private static PersonRoleService personRoleService = new PersonRoleServiceImpl();
    private static PersonService personService = new PersonServiceImpl();
    private static PersonAddressService personAddressService = new PersonAddressServiceImpl();
    private static PersonContactService personContactService = new PersonContactServiceImpl();
    private static PersonDemographicsService personDemographicsService = new PersonDemographicsServiceImpl();
    private static PersonImagesService personImagesService = new PersonImagesServiceImpl();
    private static PersonLanguageService personLanguageService = new PersonLanguageServiceImpl();

    public static PersonRoleService getPersonRoleServiceInstance(){return personRoleService ;}
    public static PersonService getPersonServiceInstance(){return personService;}
    public static PersonAddressService getPersonAddressServiceInstance(){return personAddressService ;}
    public static PersonContactService getPersonContactServiceInstance(){return personContactService;}
    public static PersonDemographicsService getPersonDemographicsServiceInstance(){return personDemographicsService;}
    public static PersonImagesService getPersonImagesServiceInstance(){return personImagesService;}
    public static PersonLanguageService getPersonLanguageServiceInstance(){return personLanguageService;}


}
