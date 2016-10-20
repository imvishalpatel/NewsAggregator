package util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

    static private String username = "sunilom.04";
    static private String fromID = "sunilom.04@gmail.com";
    static private String password = "OM~SWEET~OM**";
    static private String host = "smtp.gmail.com";

    static private Properties getProperties() {

        Properties proterties = new Properties();
        proterties.put("mail.smtp.auth", "true");
        proterties.put("mail.smtp.starttls.enable", "true");
        proterties.put("mail.smtp.host", host);
        proterties.put("mail.smtp.port", "587");

        return proterties;
    }

    static private Session getSession(Properties properties) {

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        }
        );

        return session;
    }

    static public boolean send(String toID, String subject, String messageContent) {

        boolean mailStatus = false;

        //Get Properties
        Properties properties = getProperties();

        //Get Session
        Session session = getSession(properties);

        //Sending Mail
        try {

            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(fromID));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toID));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(messageContent);

            // Send message
            Transport.send(message);

            mailStatus = true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return mailStatus;
    }

    public static void main(String[] args) {
        System.out.println(send(fromID, "Hello Sunil", "Hey! \r\n This is just a trial message!"));
    }
}
