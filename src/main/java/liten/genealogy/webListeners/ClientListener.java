/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.webListeners;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Date;
import liten.genealogy.mainEntities.Account;
import liten.genealogy.mainEntities.Client;
import liten.genealogy.mainEntities.Country;
import liten.genealogy.managers.AccountManager;
import liten.genealogy.managers.AddressManager;
import liten.genealogy.managers.ClientManager;
import org.primefaces.PrimeFaces;
import liten.genealogy.utilitiesDB.ConstantValues;
import liten.genealogy.utilitiesDB.MainDAO;
import liten.genealogy.views.AccountModelView;
import liten.genealogy.views.AddressModelView;
import liten.genealogy.views.ClientModelView;
import liten.genealogy.webUtilities.HttpUtils;
import liten.genealogy.webUtilities.SendEmail;

/**
 *
 * @author bayomock.a
 */
@Named
@SessionScoped
public class ClientListener implements Serializable {

    AccountModelView accountModelView;
    AddressModelView addressModelView;
    ClientModelView clientModelView;
    @Inject
    AccountManager accountManager;
    @Inject
    ClientManager clientManager;
    String origURI = "";
    FacesMessage message;

    public ClientListener() {

    }

    @PostConstruct
    public void init() {
        try {
            System.out.println("Dans init");
            if (HttpUtils.getSessionMap().get("accountModelView") == null) {
                accountModelView = new AccountModelView();
                accountModelView.init();
                HttpUtils.getSessionMap().put("accountModelView", accountModelView);
            } else {
                System.out.println("Dans init 2");
                accountModelView = (AccountModelView) HttpUtils.getSessionMap().get("accountModelView");
            }
            if (HttpUtils.getSessionMap().get("addressModelView") == null) {
                addressModelView = new AddressModelView();
                HttpUtils.getSessionMap().put("addressModelView", addressModelView);
            } else {
                addressModelView = (AddressModelView) HttpUtils.getSessionMap().get("addressModelView");
            }
            if (HttpUtils.getSessionMap().get("clientModelView") == null) {
                clientModelView = new ClientModelView();
                HttpUtils.getSessionMap().put("clientModelView", clientModelView);
            } else {
                clientModelView = (ClientModelView) HttpUtils.getSessionMap().get("clientModelView");
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

    public FacesMessage getMessage() {
        return message;
    }

    public AccountModelView getAccountModelView() {
        return accountModelView;
    }

    public AddressModelView getAddressModelView() {
        return addressModelView;
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

    public void addClient(ActionEvent evt) {
        String uri = "";
        try {
            String contextPath = HttpUtils.getFacesExternalContext().getRequestContextPath();
            String formID = HttpUtils.getRequestFormID();
            FacesContext fc = FacesContext.getCurrentInstance();
            origURI = "/client/ajouterClient.xhtml";
            Country country = addressModelView.getCountry();
            Client client = clientModelView.getClient();
            Account account = accountModelView.getAccount();
            if (MainDAO.findClientPerEmail(client.getEmail()) == null) {
                if (MainDAO.findClientPerUsername(account.getUserName()) == null) {
                    client.setCreatedDate(new Date());
                    client.setModifiedDate(client.getCreatedDate());
                    client = clientManager.addNewClient(client);
                    //clientManager.addNewClientAddress(clientAddress);
                    account.setCreatedDate(new Date());
                    account.setModifiedDate(new Date());
                    account.setStatus("Submitted");
                    account.setTemporaryPassword("");
                    account.setClient(client);
                    accountManager.addNewAccount(account);

                    String sender = ConstantValues.EMAIL_ADDR;
                    String password = ConstantValues.EMAIL_PASSWORD;
                    String receiver = client.getEmail();
                    String host = ConstantValues.SMTP_HOST;
                    String port = ConstantValues.SMTP_PORT;
                    String title = "Created Account for: " + client.getPerson().getLastName() + " " + client.getPerson().getFirstName();
                    String verifyAccountURI = "http://localhost:8080/" + contextPath + "/account/clientAccountVerified.xhtml?accountID=" + account.getAccountID();
                    String contentMessage = "Dear <b>" + client.getPerson().getFirstName() + " " + client.getPerson().getLastName() + "</b>, <br/> <br/>"
                            + "Welcome to IUGB Transport, your account has been preapproved. You  have 48 hours to proceed the account verification. Please click on the link below to complete your account verification: <br/>"
                            + "<a href=" + verifyAccountURI + ">Verify your account</a> <br/> <br/>"
                            + "Thanks for chosing IUGB Transport! <br/> <br/>"
                            + "<b>IUGB Transport Direction</b>";
                    SendEmail.buildEmail(sender, password, receiver, host, port, title, contentMessage);

                    uri = HttpUtils.getRequestURI();
                    message = new FacesMessage("Compte client créé avec succès", "Lire votre email et confirmer la création du compte!");
                    HttpUtils.getFacesContext().addMessage("Compte créé!", message);
                    HttpUtils.getFacesExternalContext().redirect(uri);
                } else {
                    message = new FacesMessage("Nom usager/Mot de passe existent...SVP Modifiez si necessaire votre nom usager/mot de passe!");
                    HttpUtils.getFacesContext().addMessage("Changer votre nom usager/mot de passe!", message);
                }
            } else {
                message = new FacesMessage("Client et compte existent...SVP connectez-vous pour modifier votre profil!");
                HttpUtils.getFacesContext().addMessage("Changer votre nom usager/mot de passe!", message);
            }
            PrimeFaces.current().ajax().update(formID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateClient(ActionEvent evt) {
        try {
            Client client = clientModelView.getClient();
            Client clientDB = clientManager.findClient(client.getClientID());
            if (clientDB!=null) {
                clientManager.updateClient(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
