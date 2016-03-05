package infoshare.app.facade;

import infoshare.services.organisation.Impl.OrganisationLogoServiceImpl;
import infoshare.services.organisation.Impl.OrganisationServiceImpl;
import infoshare.services.organisation.OrganisationLogoService;
import infoshare.services.organisation.OrganisationService;



/**
 * Created by garran on 2015/09/13.
 */
public class OrganisationFacade {

    public final static OrganisationService companyService = new OrganisationServiceImpl();


    public final static OrganisationLogoService companyLogosService = new OrganisationLogoServiceImpl();


}
