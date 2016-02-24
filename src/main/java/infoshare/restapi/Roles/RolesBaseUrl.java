package infoshare.restapi.Roles;

import infoshare.app.conf.RestUtil;

/**
 * Created by user9 on 2016/02/23.
 */
public class RolesBaseUrl {
    public static class Role{
      public static final String POST   =RestUtil.URL+"/api/role/create"; // @controllers.RoleController.create
      public static final String GET    =RestUtil.URL+"/api/role/"; //ontrollers.RoleController.getRole(id)
      public static final String PUT    =RestUtil.URL+"/api/role/update"; // @controllers.RoleController.update
      public static final String GET_ALL=RestUtil.URL+"/api/role/get/roles"; //       @controllers.RoleController.getAll
    }
}
