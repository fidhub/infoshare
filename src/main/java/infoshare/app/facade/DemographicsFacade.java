package infoshare.app.facade;

import infoshare.services.demographics.*;
import infoshare.services.demographics.Impl.*;

/**
 * Created by hashcode on 2015/10/16.
 */
public class DemographicsFacade {

    public static final GenderListService genderListService = new GenderListServiceImpl();
    public static final LanguageService languageService = new LanguageServiceImpl();
    public static final LanguageProficiencyService languageProficiencyService = new LanguageProficiencyServiceImpl();
    public static final RaceListService raceListService = new RaceListServiceImpl();
    public static final RolesListService rolesListService = new RolesListServiceImpl();

}
