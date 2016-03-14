package infoshare.app.util.email;


import infoshare.app.facade.UtilFacade;
import infoshare.app.util.organisation.OrganisationUtil;
import infoshare.domain.util.Mail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by hashcode on 2015/11/29.
 */
public class EmailUtil {

    private static Properties getProperties() {
        Set<Mail> mailprops = UtilFacade.getMailServiceInstance().findAll(OrganisationUtil.getCompanyCode());
        Mail properties = mailprops.iterator().next();
        Properties props = new Properties();
        props.put("mail.smtp.host", properties.getHost());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");
        props.put("mail.smtp.port", properties.getPort());
        return props;
    }


    public static void sendSimpleEmail(ComposeEmail email) {// Recipient's email ID needs to be mentioned.
        // Sender's email ID needs to be mentioned
        final String from = email.getFrom();
        final String password = email.getPassword();

        // -- Attaching to default Session, or we could start a new one --
        Session session = Session.getDefaultInstance(getProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            try {
                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from, "Hashwork HR"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(EmailUtil.class.getName()).log(Level.SEVERE, null, ex);
                message.setFrom(new InternetAddress(from));
            }


            // Set To: header field of the header. Compulsary
            if (email.getAddressesTo() != null) {
                if (!email.getAddressesTo().isEmpty()) {
                    Address[] addressTo = new Address[email.getAddressesTo().size()];
                    int i = 0;
                    for (String s : email.getAddressesTo()) {
                        addressTo[i++] = new InternetAddress(s);
//                        System.out.println("Added TO: " + s);
                    }
                    message.addRecipients(Message.RecipientType.TO, addressTo);
                }
            }

            //Set CC: header filed of the header. Not compulsary
            if (email.getAddressesCC() != null) {
                if (!email.getAddressesCC().isEmpty()) {
                    Address[] addressCC = new Address[email.getAddressesCC().size()];
                    int i = 0;
                    for (String s : email.getAddressesCC()) {
                        addressCC[i++] = new InternetAddress(s);
//                        System.out.println("Added CC: " + s);
                    }
                    message.addRecipients(Message.RecipientType.CC, addressCC);
                }
            }

            //Set BCC: header filed of the header. Not compulsary
            if (email.getAddressesBCC() != null) {
                if (!email.getAddressesBCC().isEmpty()) {
                    Address[] addressBCC = new Address[email.getAddressesBCC().size()];
                    int i = 0;
                    for (String s : email.getAddressesBCC()) {
                        addressBCC[i++] = new InternetAddress(s);
//                        System.out.println("Added BCC: " + s);
                    }
                    message.addRecipients(Message.RecipientType.BCC, addressBCC);
                }
            }


            // Set Subject: header field
            message.setSubject(email.getSubject());

            // Now set the actual message
            message.setText(email.getBody());

            // Send message
            Transport.send(message);

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public static void sendWithAttachments(ComposeEmail email, DataSource attachment, String filename) {

        // Sender's email ID needs to be mentioned
        final String from = email.getFrom();
        final String password = email.getPassword();

        // -- Attaching to default Session, or we could start a new one --
        Session session = Session.getDefaultInstance(getProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));


            // Set To: header field of the header. Compulsary
            if (email.getAddressesTo() != null) {
                if (!email.getAddressesTo().isEmpty()) {
                    Address[] addressTo = new Address[email.getAddressesTo().size()];
                    int i = 0;
                    for (String s : email.getAddressesTo()) {
                        addressTo[i++] = new InternetAddress(s);
//                        System.out.println("Added TO: " + s);
                    }
                    message.addRecipients(Message.RecipientType.TO, addressTo);
                }
            }

            //Set CC: header filed of the header. Not compulsary
            if (email.getAddressesCC() != null) {
                if (!email.getAddressesCC().isEmpty()) {
                    Address[] addressCC = new Address[email.getAddressesCC().size()];
                    int i = 0;
                    for (String s : email.getAddressesCC()) {
                        addressCC[i++] = new InternetAddress(s);
//                        System.out.println("Added CC: " + s);
                    }
                    message.addRecipients(Message.RecipientType.CC, addressCC);
                }
            }

            //Set BCC: header filed of the header. Not compulsary
            if (email.getAddressesBCC() != null) {
                if (!email.getAddressesBCC().isEmpty()) {
                    Address[] addressBCC = new Address[email.getAddressesBCC().size()];
                    int i = 0;
                    for (String s : email.getAddressesBCC()) {
                        addressBCC[i++] = new InternetAddress(s);
//                        System.out.println("Added BCC: " + s);
                    }
                    message.addRecipients(Message.RecipientType.BCC, addressBCC);
                }
            }


            // Set Subject: header field
            message.setSubject(email.getSubject());

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setContent(email.getBody(), "text/html");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setDataHandler(new DataHandler(attachment));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}