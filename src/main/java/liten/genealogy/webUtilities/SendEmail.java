/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.webUtilities;

/**
 *
 * @author Administrator
 */
import java.io.File;
import java.util.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.activation.*;

public class SendEmail {

    private String sender;
    private String receiver;

    public SendEmail() {

    }

    public SendEmail(String sender, String receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return this.sender;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public static boolean buildEmail(String sender, String curPassword, String receiver, String host, String port, String title, String emailBody) {
        boolean isSent = false;
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        //Socket settings
        /*  properties.put("mail.smtp.socketFactory.port", port);
        properties.put("mail.smtp.socketFactory.class", "jakarta.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");*/
        // Setup mail port 
        properties.setProperty("mail.smtp.port", port);

        // Mail Authentication
        properties.put("mail.smtp.auth", "true");

        //enable STARTTLS
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        //SSL Trust 
         properties.put("mail.smtp.ssl.trust", host);

        //String password
        String password = curPassword;
        // Get the default Session object.
        //create Authenticator object to pass in Session.getInstance argument

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        };
        Session session = Session.getInstance(properties, auth);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(sender));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

            // Set Subject: header field
            message.setSubject(title);

            // Send the actual HTML message, as big as you like
            message.setContent(emailBody, "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
            isSent = true;
        } catch (Exception mex) {
            mex.printStackTrace();
        }
        return isSent;
    }

    public static String textFileToString(String fileName) {
        String stringVal = "";
        try {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                stringVal = stringVal + sc.nextLine();
            }
            int firstPos = stringVal.indexOf("<h:body class=" + "flex-main>");
            int secondPos = stringVal.indexOf("/h:body");
            stringVal = stringVal.substring(firstPos + 1, secondPos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringVal;
    }

    public static void main(String[] args) {
        String updatePasswordURI = "http://" + "localhost:8080/managersTransport" + "/account/updatePassword.xhtml";
        String contentMessage = "Dear <b>" + "andre" + " " + "BAYOMOCK" + "</b>, <br/> <br/>"
                + "Please change your password using these credentials <br/>"
                + "<b>Username:</b> " + "acbayo" + "<br/> "
                + "<b>temporary password:</b> " + "A2G5H8" + "<br/> <br/>"
                + "<a href=" + updatePasswordURI + ">Change your password using the temporary password</a>" + "<br/> <br/>"
                + "Thanks for chosing IUGB Transport! <br/> <br/>"
                + "<b>IUGB Transport Direction</b>";
        //buildEmail("contact@liten3c.com", "Felicite1!", "bayomock@hotmail.com", "smtp.gmail.com", "465", "test", "bonjour, java email");
        buildEmail("contact@liten3c.com", "Felicite1!", "bayomock@hotmail.com", "smtp.gmail.com", "465", "test", contentMessage);
    }
}
