package infoshare.app.facade;

import infoshare.services.demographics.*;
import infoshare.services.demographics.Impl.*;

/**
 * Created by hashcode on 2015/10/16.
 */
public class DemographicsFacade {

    public static final GenderService genderListService =  GenderServiceImpl.getInstance();
    public static final LanguageService languageService =  LanguageServiceImpl.getInstance();
    public static final LanguageProficiencyService languageProficiencyService =  LanguageProficiencyServiceImpl.getInstance();
    public static final RaceService raceListService =  RaceServiceImpl.getInstance();
    public static final RolesListService rolesListService =  RolesListServiceImpl.getInstance();

}
