package infoshare.restapi.Roles;

/**
 * Created by user9 on 2016/02/23.
 */
public class RolesBaseUrl {
    public static class Role{
      public static final String POST   ="/api/role/create"; // @controllers.RoleController.create
      public static final String GET    ="/api/role/"; //ontrollers.RoleController.getRole(id)
      public static final String PUT    ="/api/role/update"; // @controllers.RoleController.update
      public static final String GET_ALL="/api/role/get/roles"; //       @controllers.RoleController.getAll
    }
}
