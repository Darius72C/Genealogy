/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.views;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import liten.genealogy.mainEntities.Client;
import liten.genealogy.mainEntities.ClientType;

/**
 *
 * @author darre
 */
@Named
@SessionScoped
public class ClientModelView implements Serializable{

    String clientID;
    String clientTypeID;
    private String email;
    Client client;
    ClientType clientType;
    List<Client> clientList;
    List<ClientType> clientTypeList;

    public ClientModelView() {
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientTypeID() {
        return clientTypeID;
    }

    public void setClientTypeID(String clientTypeID) {
        this.clientTypeID = clientTypeID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public List<ClientType> getClientTypeList() {
        return clientTypeList;
    }

    public void setClientTypeList(List<ClientType> clientTypeList) {
        this.clientTypeList = clientTypeList;
    }

}
