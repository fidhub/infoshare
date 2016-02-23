package infoshare.app.facade;

import infoshare.services.demographics.Impl.RolesListServiceImpl;
import infoshare.services.demographics.RolesListService;

/**
 * Created by hashcode on 2015/10/16.
 */
public class DemographicsFacade {

    public static final RolesListService rolesListService = new RolesListServiceImpl();

}
