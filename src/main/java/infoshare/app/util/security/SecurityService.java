package infoshare.app.util.security;


import infoshare.app.facade.DemographicsFacade;
import infoshare.app.facade.PeopleFacade;
import infoshare.app.facade.UtilFacade;
import infoshare.app.util.email.ComposeEmail;
import infoshare.app.util.email.EmailUtil;
import infoshare.domain.Mail;
import infoshare.domain.Person;
import infoshare.domain.Role;
import infoshare.services.people.PersonService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by hashcode on 2015/10/19.
 */
@Service
public class SecurityService implements UserDetailsService {
    private final PersonService personService = PeopleFacade.personService;

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        Person person = personService.getUserByEmail(emailAddress);
        return new org.springframework.security.core.userdetails.User(
                person.getEmailAddress(),
                person.getAuthvalue(),
                person.getEnabled(),
                person.getAccountNonExpired(),
                person.getCredentialsNonExpired(),
                person.getAccountNonLocked(),
                getAuthorities(personService.getRoles(person.getId())));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(roles));
        return authList;
    }

    public Set<String> getRoles(Set<Role> roles) {
        Set<String> roleValues = new HashSet<>();
        for (Role role : roles) {
            roleValues.add(role.getName());
        }
        return roleValues;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(Set<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    public static Set<Role> getUserRoles() {
        Person person = GetUserCredentials.getUser();
        return PeopleFacade.personService.getRoles(person.getId());
    }

    public static boolean securityCheck(String ro) {
        Role role = DemographicsFacade.rolesListService.getRole(ro);
        return getUserRoles().contains(role);
    }

    public static String generateRandomPassword() {
        int length = 8;
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    public static String getPositionCode() {
        int length = 6;
        boolean useLetters = false;
        boolean useNumbers = true;
        StringBuffer code = new StringBuffer(RandomStringUtils.random(length, useLetters, useNumbers).toUpperCase());
        code.insert(3, "-");
        return code.toString();
    }

    public static void resetValue(Person person) {
        String newvalue = generateRandomPassword();
        Person user = new Person.Builder()
                .copy(person)
                .authvalue(PasswordHash.createEncryptedPassword(newvalue))
                .build();
        PeopleFacade.personService.update(user);
        sendEmail(newvalue, person);

    }

    public static void sendEmail(String password, Person companyAdmin) {
        Mail props = UtilFacade.mailService.findAll().iterator().next();
        ComposeEmail email = new ComposeEmail
                .Builder()
                .addressesTo(new HashSet<>(Arrays.asList(companyAdmin.getEmailAddress())))
                .body(" Your Username is : " + companyAdmin.getEmailAddress() + " And Your Password is : " + password + " Login in at https://hashwork.hash-code.com")
                .from(props.getKey())
                .password(props.getValue())
                .subject("Your Hashwork Details")
                .build();
        EmailUtil.sendSimpleEmail(email);
    }
}