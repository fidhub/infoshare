package infoshare.restApi;

import infoshare.app.facade.PeopleFacade;
import infoshare.app.util.email.ComposeEmail;
import infoshare.app.util.email.EmailUtil;
import infoshare.app.util.security.PasswordHash;
import infoshare.app.util.security.RolesValues;
import infoshare.app.util.security.SecurityService;
import infoshare.domain.person.Person;
import infoshare.domain.person.PersonRole;
import infoshare.factories.person.PersonFactory;
import infoshare.factories.person.PersonRoleFactory;
import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by THULEH on 2016/03/23.
 */
public class CreateUserTest {
    private static Properties getProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "10.68.1.7");
        props.put("mail.user", "infoshare@cput.ac.za"); //TODo get user
        props.put("mail.smtp.auth", "false"); // TODO set it to true once its live
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.socketFactory.port", "25");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "true");
        return props;
    }

    /*TODO USERS LIST
         user:thulehadebe@outlook.com;
         authval:FQE0kcW3 // test
         role:role_admin

         user:2leradebe@gmail.com;
         authval:PJHV2u8X
         role:Org_Admin

         user:ca@test.com;
         authval:tcK9aIy8
         role:Org_Admin

         user:2lehadebe@gmail.com;
         authval:7YWPbIbn
         role:Role_Editor

         user:songz@test.com;
         authval:20SKEOse
         role:Role_Editor


     */
    @Test
    public void testUser() throws Exception {
        final String password = SecurityService.generateRandomPassword();
        System.out.println(password);
        Map<String, String> stringVals = new HashMap<>();
        stringVals.put("firstName", "camagu");
        stringVals.put("middleName", "");
        stringVals.put("lastName", "dlisani");
            stringVals.put("authvalue", PasswordHash.createEncryptedPassword(password));
        stringVals.put("emailAddress", "ca@test.com");
        stringVals.put("org", "dut");

        Map<String, Boolean> boolVals = new HashMap<>();
        boolVals.put("enabled", Boolean.TRUE);
        boolVals.put("accountNonExpired", Boolean.TRUE);
        boolVals.put("accountNonLocked", Boolean.TRUE);
        boolVals.put("credentialsNonExpired", Boolean.TRUE);
        Person companyAdmin = createAccount(stringVals, boolVals);
        PeopleFacade.personService.save(companyAdmin);
    }

    @Test
    public void testEmail() throws Exception {
        final String password = SecurityService.generateRandomPassword();
        Set<String> emails = new HashSet<>();
        emails.add("2leradebe@gmail.com");
        ComposeEmail email = new ComposeEmail
                .Builder()
                .addressesTo(emails)
                .body(" Your Username is : " + emails + " And Your Password is : "
                        + PasswordHash.createEncryptedPassword(password) + " Login in at https://hashwork.hash-code.com")
                .from("infoshare@cput.ac.za"/*props.getKey()*/)
                .password("")
                .subject("Your Infoshare Details")
                .build();
        sendSimpleEmail(email);

    }

    private Person createAccount(Map<String, String> stringVals, Map<String, Boolean> boolVals) {
        Person companyAdmin = PersonFactory.getPerson(stringVals, boolVals);
        try {
            PeopleFacade.personService.save(companyAdmin);
            PersonRole role = PersonRoleFactory.getPersonRole(companyAdmin.getId(), RolesValues.ORG_ADMIN.name());
            PeopleFacade.personRoleService.save(role);
        }catch (Exception e){
            e.fillInStackTrace();
        }
        return companyAdmin;
    }

    public static void sendEmail(String password, Person companyAdmin) {
        ComposeEmail email = new ComposeEmail
                .Builder()
                .addressesTo(new HashSet<>(Arrays.asList(companyAdmin.getEmailAddress())))
                .body(" Your Username is : " + companyAdmin.getEmailAddress() + " And Your Password is : " + password + " Login in at https://hashwork.hash-code.com")
                .from("2leradebe@gmail.com"/*props.getKey()*/)
                .password("2lehadebe")
                .subject("Your Infoshare Details")
                .build();
        sendSimpleEmail(email);
    }
    public static void sendSimpleEmail(ComposeEmail email) {// Recipient's email ID needs to be mentioned.
        Session session = Session.getInstance(getProperties());
        try {
            MimeMessage message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress("infoshare@cput.ac.za", "Infoshare Team"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(EmailUtil.class.getName()).log(Level.SEVERE, null, ex);
                message.setFrom(new InternetAddress("infoshare@cput.ac.za"));
            }

            if (email.getAddressesTo() != null) {
                if (!email.getAddressesTo().isEmpty()) {
                    Address[] addressTo = new Address[email.getAddressesTo().size()];
                    int i = 0;
                    for (String s : email.getAddressesTo()) {
                        addressTo[i++] = new InternetAddress(s);
                    }
                    message.addRecipients(Message.RecipientType.TO, addressTo);
                }
            }
            if (email.getAddressesCC() != null) {
                if (!email.getAddressesCC().isEmpty()) {
                    Address[] addressCC = new Address[email.getAddressesCC().size()];
                    int i = 0;
                    for (String s : email.getAddressesCC()) {
                        addressCC[i++] = new InternetAddress(s);
                    }
                    message.addRecipients(Message.RecipientType.CC, addressCC);
                }
            }
            if (email.getAddressesBCC() != null) {
                if (!email.getAddressesBCC().isEmpty()) {
                    Address[] addressBCC = new Address[email.getAddressesBCC().size()];
                    int i = 0;
                    for (String s : email.getAddressesBCC()) {
                        addressBCC[i++] = new InternetAddress(s);
                    }
                    message.addRecipients(Message.RecipientType.BCC, addressBCC);
                }
            }
            message.setSubject(email.getSubject());
            message.setText(email.getBody());
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();

        }
    }
}
