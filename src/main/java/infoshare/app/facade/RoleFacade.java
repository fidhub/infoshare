package infoshare.app.facade;

import infoshare.services.roles.Impl.RoleServiceImpl;
import infoshare.services.roles.RoleService;

/**
 * Created by user9 on 2016/02/23.
 */
public class RoleFacade {
    private static RoleService roleService = new RoleServiceImpl();

    public static RoleService getRoleServiceInstance(){return roleService;}
}
