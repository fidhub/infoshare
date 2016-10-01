package infoshare.app.facade;


import infoshare.services.Organisation.Impl.OrganisationLogoServiceImpl;
import infoshare.services.Organisation.Impl.OrganisationServiceImpl;
import infoshare.services.Organisation.OrganisationLogoService;
import infoshare.services.Organisation.OrganisationService;

/**
 * Created by garran on 2015/09/13.
 */
public class OrganisationFacade {

    public final static OrganisationService companyService =  OrganisationServiceImpl.getInstance();
    public final static OrganisationLogoService companyLogosService =  OrganisationLogoServiceImpl.getInstance();


}
