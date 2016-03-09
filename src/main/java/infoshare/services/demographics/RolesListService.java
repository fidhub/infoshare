package infoshare.services.demographics;


import infoshare.domain.demographics.Role;
import infoshare.services.Services;
import org.springframework.stereotype.Component;

/**
 * Created by hashcode on 2015/08/18.
 */
@Component
public interface RolesListService extends Services<Role, String> {
    Role getRole(String role);
}
