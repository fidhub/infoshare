package infoshare.app.facade;

import infoshare.services.demographics.*;
import infoshare.services.demographics.Impl.*;

/**
 * Created by hashcode on 2015/10/16.
 */
public class DemographicsFacade {

    private static GenderService genderService = new GenderServiceImpl();
    private static LanguageService languageService = new LanguageServiceImpl();
    private static LanguageProficiencyService languageProficiencyService = new LanguageProficiencyServiceImpl();
    private static RaceService raceListService = new RaceServiceImpl();
    private static RolesListService rolesListService = new RolesListServiceImpl();

    public static GenderService getGenderInstance(){return genderService;}
    public static LanguageService getLanguageServiceInstance(){return languageService;}
    public static LanguageProficiencyService getLanguageProficiencyServiceInstance(){return languageProficiencyService;}
    public static RaceService getRaceServiceInstance(){return raceListService;}
    public static RolesListService getRolesListServiceInstance(){return rolesListService;}



}
