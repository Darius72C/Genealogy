/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.webListeners;

/**
 *
 * @author bayomock.a
 */
/**
 *
 * @author FACULTY
 */
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import javax.crypto.SecretKey;
import liten.genealogy.managers.AccountManager;
import liten.genealogy.managers.ClientManager;
import liten.genealogy.utilitiesDB.ConstantValues;
import liten.genealogy.views.AccountModelView;
import liten.genealogy.views.ClientModelView;
import liten.genealogy.webUtilities.HttpUtils;
import liten.genealogy.mainEntities.*;
import liten.genealogy.utilitiesDB.AesService;
import liten.genealogy.utilitiesDB.MainDAO;
import liten.genealogy.webUtilities.SendEmail;
import org.primefaces.PrimeFaces;

@Named
@SessionScoped
public class AccountListener implements Serializable {

    ClientModelView clientModelView;
    AccountModelView accountModelView;
    @Inject
    AccountManager accountManager;
    @Inject
    ClientManager clientManager;
    String origURI = "";
    FacesMessage message;
    boolean isCreated = false;
    HttpSession session;

    public AccountListener() {

    }

    @PostConstruct
    public void init() {
        try {
            System.out.println("Dans init");
            if (HttpUtils.getSessionMap().get("accountModelView") == null) {
                accountModelView = new AccountModelView();
                HttpUtils.getSessionMap().put("accountModelView", accountModelView);
            } else {
                System.out.println("Dans init 2");
                accountModelView = (AccountModelView) HttpUtils.getSessionMap().get("accountModelView");
            }
            if (HttpUtils.getSessionMap().get("clientModelView") == null) {
                clientModelView = new ClientModelView();
                HttpUtils.getSessionMap().put("clientModelView", clientModelView);
            } else {
                clientModelView = (ClientModelView) HttpUtils.getSessionMap().get("clientModelView");
            }
            if (HttpUtils.getApplicationMap().get("AES_KEY_BYTE") == null) {
                HashMap keyMap = new HashMap<String, byte[]>();
                byte[] aesKeyBytes = ConstantValues.AES_KEY.getBytes();
                keyMap.put("AES_KEY_BYTE", aesKeyBytes);
                AesService.keyMap = keyMap;
                HttpUtils.getApplicationMap().put("KEY_MAP", keyMap);
            } else {
                AesService.keyMap = (HashMap<String, byte[]>) HttpUtils.getApplicationMap().get("KEY_MAP");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOrigURI(String curURI) {
        this.origURI = curURI;
    }

    public String getOrigURI() {
        return origURI;
    }

    public HttpSession getSession() {
        return session;
    }

    public FacesMessage getMessage() {
        return message;
    }

    public AccountModelView getAccountModelView() {
        return accountModelView;
    }

    public ClientModelView getClientModelView() {
        return clientModelView;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public ClientManager getClientManager() {
        return clientManager;
    }

    public void addAccount(ActionEvent evt) {
        String uri = "";
        try {
            String contextPath = HttpUtils.getFacesExternalContext().getRequestContextPath();
            String formID = HttpUtils.getRequestFormID();
            String email = clientModelView.getEmail();
            Client client = clientManager.findClientPerEmail(email);
            Account account = accountModelView.getAccount();
            if (client != null) {
                Account accountDB = MainDAO.findAccountPerClient(client.getClientID());
                if (accountDB == null) {
                    ClientType clientType = clientModelView.getClientType();
                    String accountType = accountModelView.getAccountType();
                    account.setAccountType(accountType);
                    account.setCreatedDate(new Date());
                    account.setModifiedDate(new Date());
                    account.setAccountType(accountType);
                    account.setActivated(true);
                    account.setStatus("Submitted");
                    account.setPassword(AesService.encryptField(account.getPassword()));
                    account.setTemporaryPassword("");
                    account.setClient(client);
                    account = accountManager.addNewAccount(account);

                    String sender = ConstantValues.EMAIL_ADDR;
                    String password = ConstantValues.EMAIL_PASSWORD;
                    String receiver = client.getEmail();
                    String host = ConstantValues.SMTP_HOST;
                    String port = ConstantValues.SMTP_PORT;
                    String title = "Compte créé pour: " + client.getPerson().getLastName() + " " + client.getPerson().getFirstName();
                    String verifyAccountURI = "http://localhost:8080/" + contextPath + "/account/clientAccountVerified.xhtml?accountID=" + account.getAccountID();
                    String contentMessage = "Cher <b>" + client.getPerson().getFirstName() + " " + client.getPerson().getLastName() + "</b>, <br/> <br/>"
                            + "Bienvenue au site web genealogie XXXX, votre compte est créé et préapprouvé. Vous avez un délai de 48h pout confirmer la validité de votre email. Prière de cliquer sur le lien ci-après afin de compléter la vérification du compte: <br/>"
                            + "<a href=" + verifyAccountURI + ">Vérifier votre compte</a> <br/> <br/>"
                            + "Merci d'avoir choisi genealogie XXXX! <br/> <br/>"
                            + "<b>La Direction</b>";
                    SendEmail.buildEmail(sender, password, receiver, host, port, title, contentMessage);

                    uri = HttpUtils.getRequestURI();
                    message = new FacesMessage("Client créé avec succès", "SVP confirmer la réception du email de création de compte!");
                    HttpUtils.getFacesContext().addMessage("Client created!", message);
                    HttpUtils.getFacesExternalContext().getFlash().setKeepMessages(true);
                    HttpUtils.getFacesExternalContext().redirect(uri);
                } else {
                    message = new FacesMessage("Nom usager/mot de passe existant...Prière de modifier vos identifiant d'accès au compte ou de vous connecter!");
                    HttpUtils.getFacesContext().addMessage("Vérifer nom usager/mot de passe!", message);
                }
            } else {
                // uri = contextPath +"/user/ajouterClient.xhtml";
                message = new FacesMessage("Les informations du client non existant, prière de verifier votre email ou de créer un client!");
                HttpUtils.getFacesContext().addMessage("Compte non existant!", message);
                //  HttpUtils.getFacesExternalContext().redirect(uri);
            }
            PrimeFaces.current().ajax().update(formID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAccountClient(ActionEvent evt) {
        String uri = "";
        try {
            String contextPath = HttpUtils.getFacesExternalContext().getRequestContextPath();
            String formID = HttpUtils.getRequestFormID();
            Client client = clientModelView.getClient();
            Account account = accountModelView.getAccount();
            if (clientManager.findClientPerEmail(client.getEmail()) == null) {
                if (clientManager.findClientPerUsername(account.getUserName()) == null) {
                    ClientType clientType = clientModelView.getClientType();
                    String accountType = accountModelView.getAccountType();
                    account.setAccountType(accountType);
                    client.setCreatedDate(new Date());
                    client.setModifiedDate(client.getCreatedDate());
                    client.setClientType(clientType);
                    client = clientManager.addNewClient(client);
                    account.setCreatedDate(new Date());
                    account.setModifiedDate(new Date());
                    account.setActivated(true);
                    account.setAccountType(accountType);
                    account.setStatus("Submitted");
                    account.setPassword(AesService.encryptField(account.getPassword()));
                    account.setTemporaryPassword("");
                    account.setClient(client);
                    account = accountManager.addNewAccount(account);

                    String sender = ConstantValues.EMAIL_ADDR;
                    String password = ConstantValues.EMAIL_PASSWORD;
                    String receiver = client.getEmail();
                    String host = ConstantValues.SMTP_HOST;
                    String port = ConstantValues.SMTP_PORT;
                    String title = "Compte créé pour: " + client.getPerson().getLastName() + " " + client.getPerson().getFirstName();
                    String verifyAccountURI = "http://localhost:8080/" + contextPath + "/account/clientAccountVerified.xhtml?accountID=" + account.getAccountID();
                    String contentMessage = "Cher <b>" + client.getPerson().getFirstName() + " " + client.getPerson().getLastName() + "</b>, <br/> <br/>"
                            + "Bienvenue au site web de genealogie XXXX, votre compte est créé et préapprouvé. Vous avez un délai de 48h pout confirmer la validité de votre email. Prière de cliquer sur le lien ci-après afin de compléter la vérification du compte: <br/>"
                            + "<a href=" + verifyAccountURI + ">Vérifier votre compte</a> <br/> <br/>"
                            + "Merci d'avoir choisi genealogie XXXX! <br/> <br/>"
                            + "<b>La Direction</b>";
                    SendEmail.buildEmail(sender, password, receiver, host, port, title, contentMessage);

                    uri = HttpUtils.getRequestURI();
                    message = new FacesMessage("Client créé avec succès", "SVP confirmer la réception du email de création de compte!");
                    HttpUtils.getFacesContext().addMessage("Client created!", message);
                    HttpUtils.getFacesExternalContext().getFlash().setKeepMessages(true);
                    HttpUtils.getFacesExternalContext().redirect(uri);
                } else {
                    message = new FacesMessage("Nom usager/mot de passe existant...Prière de modifier vos identifiant d'accès au compte, puis s'enregistrer à nouveau!");
                    HttpUtils.getFacesContext().addMessage("Vérifer nom usager/mot de passe!", message);
                }
            } else {
                message = new FacesMessage("Les informations du client et du compte existent...Prière se connecter ou modifier votre profil d'accès!");
                HttpUtils.getFacesContext().addMessage("Compte existant!", message);
            }
            PrimeFaces.current().ajax().update(formID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAccount(ActionEvent evt) {
        String uri = "";
        try {
            String contextPath = HttpUtils.getFacesExternalContext().getRequestContextPath();
            FacesContext fc = FacesContext.getCurrentInstance();
            origURI = "/account/updateAccount.xhtml";
            Account account = accountModelView.getAccount();
            accountManager.updateAccount(account);
            uri = contextPath + "/bienvenue.xhtml";
            message = new FacesMessage("Compte créé!");
            HttpUtils.addMessage(message);
            HttpUtils.getFacesExternalContext().redirect(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchAccountPerUsername(ActionEvent evt) {
        try {
            String userName = accountModelView.getUserName();
            Account account = MainDAO.findAccountPerUsername(userName);
            if (account != null) {
                accountModelView.setAccount(account);
                String uri = HttpUtils.getRequestURI();
                HttpUtils.getFacesExternalContext().redirect(uri);
            } else {
                message = new FacesMessage("Compte n'existe pas...Réessayez SVP!");
                HttpUtils.addMessage(message);
                String uri = HttpUtils.getRequestURI();
                HttpUtils.getFacesExternalContext().redirect(uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void login(ActionEvent evt) {
        try {
            String formID = HttpUtils.getRequestFormID();
            accountModelView = (AccountModelView) HttpUtils.getSessionMap().get("accountModelView");
            int maxNbrOfTries = accountModelView.getMaxNbrOfTries();
            String contextPath = HttpUtils.getContextPath();
            if (maxNbrOfTries != 0) {
                String userName = (String) HttpUtils.getRequestParamsMap().get(formID + ":userName");
                System.out.println(userName);
                String password = (String) HttpUtils.getRequestParamsMap().get(formID + ":password");
                System.out.println(password);
                if (userName != null && password != null) {
                    Account accountExistence = MainDAO.findAccountPerUsername(userName);
                    System.out.println("Existence de compte= " + accountExistence);
                    if (accountExistence != null) {
                        String decryptedPassword = AesService.decryptField(accountExistence.getPassword());
                        Account account = null;
                        if (password.equals(decryptedPassword)) {
                            account = accountExistence;
                        }
                        System.out.println("Account= " + account);
                        if (account != null && (account.getStatus().equals("Verified") || account.getStatus().equals("Approved")) && account.getClient() != null && account.getClient().getClientID() != null) {
                            accountModelView.setAccount(account);
                            accountModelView.setAuthenticated(true);
                            accountModelView.setUserName(userName);
                            accountModelView.setPassword(password);
                            clientModelView.setClient(account.getClient());
                            clientModelView.setClientTypeID(account.getClient().getClientType().getClientTypeID());
                            if (HttpUtils.getSession() == null) {
                                session = HttpUtils.initiateHttpSession();
                            } else {
                                session = HttpUtils.getHttpSession();
                            }
                            accountModelView.getLogins().put(account.getClient().getClientID(), session);
                            message = new FacesMessage("Authentification: Succès!", "Parfait! Vous ètes authentifié!");
                            HttpUtils.addMessage(message);
                            String uri = HttpUtils.getRequestURI();
                            if (formID.equals("authenticationFormID")) {
                                contextPath = HttpUtils.getContextPath();
                                uri = contextPath + "/bienvenue.xhtml";
                            }
                            HttpUtils.getFacesExternalContext().redirect(uri);
                        } else {
                            accountModelView.setMaxNbrOfTries(--maxNbrOfTries);
                            accountModelView.setUserName(userName);
                            accountModelView.setPassword(password);
                            System.out.println("Remaining Tries= " + maxNbrOfTries);
                            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ré-essayez", "Informations erronées sur le compte...Ré-essayez SVP!");;
                            if (maxNbrOfTries == 0 || (accountExistence != null && accountExistence.getStatus().equals("Blocked"))) {
                                accountExistence.setStatus("Blocked");
                                accountModelView.setAccount(accountExistence);
                                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Compte bloqué!", "SVP, Fournir un email valide, afin de modifier votre mot ...");
                                HttpUtils.addMessage(message);
                                String uri = contextPath + "/account/requestClientPassword.xhml";
                                HttpUtils.getFacesExternalContext().redirect(uri);
                            }
                            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Informations erronées sur le nom d'usager/mot de passe!", "Vérifiez les informations fournies et réessayez...");
                            HttpUtils.addMessage(message);
                            PrimeFaces.current().ajax().update(formID);
                        }
                    } else {
                        accountModelView.setMaxNbrOfTries(--maxNbrOfTries);
                        accountModelView.setUserName(userName);
                        accountModelView.setPassword(password);
                        accountModelView.setAccount(null);
                        System.out.println("Essais restants= " + maxNbrOfTries);
                        message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Réessayez", "Informations erronnées sur le nom d'usager/mot de passe...");
                        HttpUtils.addMessage(message);
                    }
                    PrimeFaces.current().ajax().update(formID);
                }
            } else {
                System.out.println("Essais restants= " + maxNbrOfTries);
                accountModelView.setAccount(null);
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre d'essais épuisé!", "Compte bloqué! Fournir le email utilisé lors de la creaion du compte pour le ré-initialiser!");
                String uri = contextPath + "/account/requestClientPassword.xhml";
                HttpUtils.getFacesExternalContext().redirect(uri);
                PrimeFaces.current().ajax().update(formID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginUpdatePassword(ActionEvent evt) {
        try {
            String formID = HttpUtils.getRequestFormID();
            String uri = accountModelView.getOrigURI();
            int maxNbrOfTries = accountModelView.getMaxNbrOfTries();
            String contextPath = HttpUtils.getContextPath();
            if (maxNbrOfTries != 0) {
                String userName = accountModelView.getUserName();
                System.out.println(userName);
                String password = accountModelView.getPassword();
                System.out.println(password);
                String newPassword = accountModelView.getNewPassword();
                System.out.println(newPassword);
                if (userName != null && password != null) {
                    Account accountExistence = accountManager.findAccountPerUsername(userName);
                    if (accountExistence != null) {
                        Account account = accountManager.findAccountPerCredentials(userName, password);
                        if (account != null) {
                            account.setPassword(newPassword);
                            account.setTemporaryPassword("");
                            account = accountManager.updateAccount(account);
                            message = new FacesMessage("Update sucessfully...Please login and enjoy!");
                            if (account.getStatus().equals("Blocked")) {
                                if (account.getAccountType().equals("Client")) {
                                    Client client = account.getClient();
                                    account.setStatus("Accepted");
                                    message = new FacesMessage("Update sucessfully...Please login and enjoy!");
                                }
                            }
                            HttpUtils.addMessage(message);
                            uri = contextPath + "/bienvenue.xhtml";
                            HttpUtils.getFacesExternalContext().redirect(uri);
                            PrimeFaces.current().ajax().update(formID);
                        } else {
                            accountModelView.setMaxNbrOfTries(--maxNbrOfTries);
                            accountModelView.setUserName(userName);
                            accountModelView.setPassword(password);
                            if (maxNbrOfTries == 0 || (accountExistence != null && accountExistence.getStatus().equals("Blocked"))) {
                                accountExistence.setStatus("Blocked");
                                accountModelView.setAccount(accountExistence);
                                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Compte bloqué!", "Fournir un email valide, puis modifier votre mot de passe...");
                                HttpUtils.addMessage(message);
                                uri = contextPath + "/account/requestClientPassword.xhtml";
                                HttpUtils.getFacesExternalContext().redirect(uri);
                            }
                            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Réessayez", "Fournir les paramètres de connexion valides ... Puis réessayer....");
                            HttpUtils.addMessage(message);
                            PrimeFaces.current().ajax().update(formID);
                        }
                    } else {
                        accountModelView.setMaxNbrOfTries(--maxNbrOfTries);
                        accountModelView.setUserName(userName);
                        accountModelView.setPassword(password);
                        if (maxNbrOfTries == 0) {
                            accountExistence.setStatus("Blocked");
                            accountModelView.setAccount(accountExistence);
                            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Réessayez", "Fournir les paramètres de connexion valides ... Puis réessayer....");
                            HttpUtils.addMessage(message);
                            uri = contextPath + "/welcome.xhtml";
                            HttpUtils.getFacesExternalContext().redirect(uri);
                        }
                        message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Informations erronées!", "Réessayer!");
                        HttpUtils.addMessage(message);
                        PrimeFaces.current().ajax().update(formID);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearLoginAccount(ActionEvent evt) {
        try {
            accountModelView.setUserName("");
            accountModelView.setPassword("");
            accountModelView.setNewPassword("");
            String uri = HttpUtils.getRequestURI();
            HttpUtils.getFacesExternalContext().redirect(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearAccount(ActionEvent evt) {
        try {
            accountModelView = new AccountModelView();
            HttpUtils.getSessionMap().put("accountModelView", accountModelView);
            String uri = HttpUtils.getRequestURI();
            HttpUtils.getFacesExternalContext().redirect(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent evt) {
        try {
            HttpUtils.getSession().invalidate();
            String uri = "/welcome.xhtml";
            HttpUtils.getFacesExternalContext().redirect(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void requestClientPassword(ActionEvent evt) {
        String formID = HttpUtils.getRequestFormID();
        String email = clientModelView.getClient().getEmail();
        Client client = clientManager.findClientPerEmail(email);
        Account account = MainDAO.findAccountPerClient(client.getClientID());
        String contextPath = HttpUtils.getContextPath();
        System.out.println(contextPath);
        System.out.println(formID);
        accountModelView.changePasswordEmailBlockedClient(account, client, contextPath);
    }

    public void selectAccount(AjaxBehaviorEvent event) {
        try {
            String accountID = accountModelView.getAccountID();
            Account account = MainDAO.findAccount(accountID);
            if (account != null && account.getAccountID() != null) {
                accountModelView.setAccountID(account.getAccountID());
                accountModelView.setAccount(account);
                accountModelView.setAccountType(account.getAccountType());
            }
            String uri = HttpUtils.getRequestURI();
            System.out.println(uri);
            HttpUtils.getFacesExternalContext().redirect(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectAccountType(AjaxBehaviorEvent event) {
        try {
            String accountType = accountModelView.getAccountType();
            accountModelView.setAccountType(accountType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
