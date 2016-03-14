package infoshare.app.facade;


import infoshare.services.Organisation.Impl.OrganisationLogoServiceImpl;
import infoshare.services.Organisation.Impl.OrganisationServiceImpl;
import infoshare.services.Organisation.OrganisationLogoService;
import infoshare.services.Organisation.OrganisationService;

/**
 * Created by garran on 2015/09/13.
 */
public class OrganisationFacade {

    private static OrganisationService companyService = new OrganisationServiceImpl();
    private static OrganisationLogoService companyLogosService = new OrganisationLogoServiceImpl();

    public static OrganisationService getOrganisationServiceInstance(){return companyService ;}
    public static OrganisationLogoService getOrganisationLogoServiceInstance(){return companyLogosService ;}


}
