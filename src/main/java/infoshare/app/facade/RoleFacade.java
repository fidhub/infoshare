package infoshare.app.facade;

import infoshare.services.roles.Impl.RoleServiceImpl;
import infoshare.services.roles.RoleService;

/**
 * Created by user9 on 2016/02/23.
 */
public class RoleFacade {
    public final static RoleService roleSrvice = new RoleServiceImpl();
}
