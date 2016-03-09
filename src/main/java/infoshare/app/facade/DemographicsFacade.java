package infoshare.app.facade;

import infoshare.services.demographics.*;
import infoshare.services.demographics.Impl.*;

/**
 * Created by hashcode on 2015/10/16.
 */
public class DemographicsFacade {

    public static final GenderService genderListService = new GenderServiceImpl();
    public static final LanguageService languageService = new LanguageServiceImpl();
    public static final LanguageProficiencyService languageProficiencyService = new LanguageProficiencyServiceImpl();
    public static final RaceService raceListService = new RaceServiceImpl();
    public static final RolesListService rolesListService = new RolesListServiceImpl();

}
