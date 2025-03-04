/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.views;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import liten.genealogy.mainEntities.Account;
import liten.genealogy.mainEntities.Client;
import liten.genealogy.managers.AccountManager;
import org.primefaces.PrimeFaces;
import liten.genealogy.utilitiesDB.ConstantValues;
import liten.genealogy.utilitiesDB.MainDAO;
import liten.genealogy.webUtilities.HttpUtils;

/**
 *
 * @author darre
 */
@Named
@SessionScoped
public class AccountModelView implements Serializable{
    private String origURI = "";
    private String accountID;
    private Account account;
    private List<Account> accountList;
    private String userName;
    private String password;
    private String newPassword;
    private String accountType = null;
    private String selectedRole;
    //String clientTypeName;
    boolean authenticated = false;
    boolean isMessageSent = false;
   // Client client;
    int maxNbrOfTries = 6;
    boolean renderAuthentication = false;
    boolean renderAuthenticateState = false;
    private static Map<String, HttpSession> logins = null;
    private long duration;
    private String sender;
    private String receiver;
    private FacesMessage message;

    public AccountModelView() {

    }

    public void init() {
        account = new Account();
/*        client = new Client();
        ClientType clientType = new ClientType();
        client.setClientType(clientType);
        account.setClient(client);*/
        accountType = null;
        AccountManager accountManager = new AccountManager();
        accountList = accountManager.findAllAccount();
        if (accountList == null) {
            accountList = new ArrayList<Account>();
        }

    }

    public void setOrigURI(String origURI) {
        this.origURI = origURI;
    }

    public String getOrigURI() {
        return this.origURI;
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

    public boolean getIsMessageSent() {
        return this.isMessageSent;
    }

    public void setIsMessageSent(boolean isMessageSent) {
        this.isMessageSent = isMessageSent;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAccountID() {
        return this.accountID;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return this.accountType;
    }
/*
    public void setClientTypeName(String clientTypeName) {
        this.clientTypeName = clientTypeName;
    }

    public String getClientTypeName() {
        return this.clientTypeName;
    }
*/
    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public FacesMessage getMessage() {
        return message;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public List<Account> getAccountList() {
        return this.accountList;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }

    public void setMaxNbrOfTries(int maxNbrOfTries) {
        this.maxNbrOfTries = maxNbrOfTries;
    }
/*
    public void setClient(Client client) {
        this.client = client;
    }
*/
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public String getSelectedRole() {
        return this.selectedRole;
    }

    public boolean getAuthenticated() {
        return this.authenticated;
    }
/*
    public Client getClient() {
        return this.client;
    }
*/
    public int getMaxNbrOfTries() {
        return maxNbrOfTries;
    }

    public boolean getRenderAuthentication() {
        if (maxNbrOfTries != 3) {
            renderAuthentication = true;
        }
        return renderAuthentication;
    }

    public boolean getRenderAuthenticateState() {
        if (maxNbrOfTries != 3 || authenticated) {
            renderAuthenticateState = true;
        } else {
            renderAuthenticateState = false;
        }
        return renderAuthenticateState;
    }

    public static Map<String, HttpSession> getLogins() {
        if (logins == null) {
            logins = new ConcurrentHashMap<>();
        }
        return logins;
    }

    public void clientAccountVerification() {
        accountID = (String) HttpUtils.getRequestParamsMap().get("accountID");
        account = MainDAO.findAccount(accountID);
        System.out.println(account.getUserName());
        Date curDate = new Date();
        duration = curDate.getTime() - account.getCreatedDate().getTime() - 172800000;
        System.out.println("duration = " + duration);
        String formID = HttpUtils.getRequestFormID();
        if (account != null && account.getStatus().equals("Submitted")) {
            if (duration <= 0) {
                account.setStatus("Verified");
                System.out.println("Verified");
                account.setModifiedDate(curDate);
                AccountManager accountManager = new AccountManager();
                accountManager.updateAccount(account);
                Client client = account.getClient();
                //account.getClient();
                ClientModelView clientModelView = (ClientModelView) HttpUtils.getSessionMap().get("clientModelView");
                clientModelView.setClient(client);
                clientModelView.setClientID(client.getPerson().getLastName());
                System.out.println("Last name = " + client.getPerson().getLastName());
            } else {
                account.setStatus("Blocked");
                String contextPath = HttpUtils.getContextPath();
                changePasswordEmailBlockedClient(account, account.getClient(), contextPath);
            }
            PrimeFaces.current().ajax().update(formID);
        }
    }

    public void changePasswordEmailBlockedClient(Account account, Client client, String contextPath) {
        String sender = ConstantValues.EMAIL_ADDR;
        System.out.println(sender);
        String password = ConstantValues.EMAIL_PASSWORD;
        System.out.println(password);
        String receiver = client.getEmail();
        System.out.println(receiver);
        String host = ConstantValues.SMTP_HOST;
        System.out.println(host);
        String port = ConstantValues.SMTP_PORT;
        System.out.println(port);
        String title = "Created Account for: " + client.getPerson().getLastName() + " " + client.getPerson().getFirstName();
        account.setTemporaryPassword(generateTemporaryPassword());
        System.out.println(account.getTemporaryPassword());
        String updatePasswordURI = "http://localhost:8080/" + contextPath + "/account/updatePassword.xhtml";
        String contentMessage = "Dear <b>" + client.getPerson().getFirstName() + " " + client.getPerson().getLastName() + "</b>, <br/> <br/>"
                + "Please change your password using these credentials" + "<br/>"
                + "<b>Username:</b> " + account.getUserName() + "<br/>"
                + "<b>temporary password:</b> " + account.getTemporaryPassword() + "<br/> <br/>"
                + "<a href=" + updatePasswordURI + ">Change your password using the temporary password</a>" + "<br/> <br/>"
                + "Thanks for chosing IUGB Transport!" + "<br/> <br/>"
                + "<b>IUGB Transport Direction</b>";
        isMessageSent = buildEmail(sender, password, receiver, host, port, title, contentMessage);
        String formID = HttpUtils.getRequestFormID();
        System.out.println(formID);
        if (isMessageSent) {
            account.setPassword(account.getTemporaryPassword());
            AccountManager acctMgr = new AccountManager();
            acctMgr.updateAccount(account);
            message = new FacesMessage("Email sent, please check your email, then update your password!");
        } else {
            message = new FacesMessage("Fail to send email message...Please check your email address!!");
        }
        PrimeFaces.current().ajax().update(formID);
    }

    public String generateTemporaryPassword() {
        String tempPass = "";
        Random rand = new Random();

        for (int i = 1; i <= 6; i++) {
            String val = "";
            int intVal = 0;
            if (i % 2 != 0) {
                while (intVal == 0) {
                    intVal = rand.nextInt(26);
                }
                switch (intVal) {
                    case 1:
                        val = "A";
                        break;
                    case 2:
                        val = "B";
                        break;
                    case 3:
                        val = "C";
                        break;
                    case 4:
                        val = "D";
                        break;
                    case 5:
                        val = "E";
                        break;
                    case 6:
                        val = "F";
                        break;
                    case 7:
                        val = "G";
                        break;
                    case 8:
                        val = "H";
                        break;
                    case 9:
                        val = "I";
                        break;
                    case 10:
                        val = "J";
                        break;
                    case 11:
                        val = "K";
                        break;
                    case 12:
                        val = "L";
                        break;
                    case 13:
                        val = "M";
                        break;
                    case 14:
                        val = "N";
                        break;
                    case 15:
                        val = "O";
                        break;
                    case 16:
                        val = "P";
                        break;
                    case 17:
                        val = "Q";
                        break;
                    case 18:
                        val = "R";
                        break;
                    case 19:
                        val = "S";
                        break;
                    case 20:
                        val = "T";
                        break;
                    case 21:
                        val = "U";
                        break;
                    case 22:
                        val = "V";
                        break;
                    case 23:
                        val = "W";
                        break;
                    case 24:
                        val = "X";
                        break;
                    case 25:
                        val = "Y";
                        break;
                    case 26:
                        val = "Z";
                        break;
                    default:
                        System.out.println("Wrong value....");
                        break;
                }
                tempPass += val;
            } else {
                intVal = rand.nextInt(9);
                tempPass = tempPass + intVal;
            }
        }
        return tempPass;
    }

    public boolean buildEmail(String sender, String curPassword, String receiver, String host, String port, String title, String emailBody) {
        boolean isSent = false;
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        //Socket settings
        properties.put("mail.smtp.socketFactory.port", port);
        properties.put("mail.smtp.socketFactory.class", "jakarta.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");

        // Setup mail port 
        properties.setProperty("mail.smtp.port", port);

        // Mail Authentication
        properties.put("mail.smtp.auth", "true");

        //enable STARTTLS
        properties.put("mail.smtp.starttls.enable", "true");

        //SSL Trust 
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

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
            jakarta.mail.Transport.send(message);
            System.out.println("Sent message successfully....");
            String formID = HttpUtils.getRequestFormID();
            isSent = true;
        } catch (Exception mex) {
            mex.printStackTrace();
        }
        return isSent;
    }
    
}
