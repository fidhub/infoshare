package infoshare.app.util.security;


import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.domain.person.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * Created by hashcode on 2015/10/19.
 */
public class GetUserCredentials {
    public static String username() {
        String username = "Anonymous";
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
        }
        return username;
    }

    public static boolean isUserWithRole(String role) {
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains(authority)) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    public static String getRole(){
        return PeopleFacade.getPersonServiceInstance().getPersonById(OrganisationUtil.getCompanyCode(),OrganisationUtil.getPersonID()).getAuthvalue();
    }

    public static Person getUser() {
        return PeopleFacade.getPersonServiceInstance().getUserByEmail(username());
    }
}
