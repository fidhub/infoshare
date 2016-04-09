package infoshare.restApi;

import infoshare.app.facade.PeopleFacade;
import infoshare.app.facade.UtilFacade;
import infoshare.app.util.email.ComposeEmail;
import infoshare.app.util.email.EmailUtil;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.app.util.security.PasswordHash;
import infoshare.app.util.security.RolesValues;
import infoshare.app.util.security.SecurityService;
import infoshare.domain.person.Person;
import infoshare.domain.person.PersonRole;
import infoshare.domain.util.Mail;
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
        props.put("mail.smtp.user", "infoshare@cput.ac.za"); //TODo get user
        props.put("mail.smtp.auth", "false"); // TODO set it to true once its live
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.socketFactory.port", "25");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        return props;
    }

    /*TODO USERS LIST
         user:thulehadebe@outlook.com;
         authval:FQE0kcW3
         role:role_admin

         user:2leradebe@gmail.com;
         authval:PJHV2u8X
         role:Org_Admin

         user:2lehadebe@gmail.com;
         authval:7YWPbIbn
         role:Role_Editor


     */
    @Test
    public void testUser() throws Exception {
        final String password = SecurityService.generateRandomPassword();
        System.out.println(password);
        Map<String, String> stringVals = new HashMap<>();
        stringVals.put("firstName", "Sanele");
        stringVals.put("middleName", "");
        stringVals.put("lastName", "Hadebe");
            stringVals.put("authvalue", PasswordHash.createEncryptedPassword(password));
        stringVals.put("emailAddress", "2lehadebe@gmail.com");
        stringVals.put("org", "CPUT");

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
                .addressesTo(new HashSet<>(emails))
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
            PersonRole role = PersonRoleFactory.getPersonRole(companyAdmin.getId(), RolesValues.ROLE_EDITOR.name());
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
        // Sender's email ID needs to be mentioned
        final String from = email.getFrom();
        final String password = email.getPassword();

     /*   Set<Mail> mailprops = UtilFacade.mailService.findAll("dut");
        Mail properties = mailprops.iterator().next();*/
        // -- Attaching to default Session, or we could start a new one --
        Session session = Session.getInstance(getProperties());
       /* Session session = Session.getDefaultInstance(getProperties(), new Authenticator() {//TODO use Authenticator once it's live
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });*/

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            try {
                // Set From: header field of the header.
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
