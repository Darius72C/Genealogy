/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.managers;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import liten.genealogy.mainEntities.Client;
import liten.genealogy.mainEntities.ClientType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import liten.genealogy.utilitiesDB.DataManager;
import liten.genealogy.utilitiesDB.KeyGeneration;
import liten.genealogy.utilitiesDB.MainDAO;

@Named
@SessionScoped
public class ClientManager implements Serializable {

    public ClientManager() {

    }

    public Client addNewClient(Client client) {
        Client clientDB = null;
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            if (client != null && client.getClientID() == null) {
                String clientID = KeyGeneration.generatePrimaryKey(Class.forName("mainEntities.Client"));
                clientDB.setClientID(clientID);
            }
            clientDB = connectionServiceFacade.mergeEntity(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientDB;
    }

    public ClientType addNewClientType(ClientType clientType) {
        ClientType clientTypeDB = null;
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            if (clientType != null && clientType.getClientTypeID() == null) {
                String clientTypeID = KeyGeneration.generatePrimaryKey(Class.forName("mainEntities.ClientType"));
                clientTypeDB.setClientTypeID(clientTypeID);
            }
            clientTypeDB = connectionServiceFacade.mergeEntity(clientType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientTypeDB;
    }

    public void updateClient(Client client) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            Client clientDB = MainDAO.findClient(client.getClientID());
            if (clientDB != null) {
                clientDB = connectionServiceFacade.mergeEntity(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateClientType(ClientType clientType) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            ClientType clientTypeDB = MainDAO.findClientType(clientType.getClientTypeID());
            if (clientTypeDB != null) {
                clientTypeDB = connectionServiceFacade.mergeEntity(clientType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(String clientID) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            Client clientDB = MainDAO.findClient(clientID);
            if (clientDB != null) {
                connectionServiceFacade.removeEntity(clientDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteClientType(String clientTypeID) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            ClientType clientTypeDB = MainDAO.findClientType(clientTypeID);
            if (clientTypeDB != null) {
                connectionServiceFacade.removeEntity(clientTypeDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Client findClient(String clientID) {
        Client client = null;
        try {
            client = MainDAO.findClient(clientID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }

    public Client findClientPerEmail(String email) {
        Client client = null;
        try {
            client = MainDAO.findClientPerEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }
    
    public Client findClientPerUsername(String username) {
        Client client = null;
        try {
            client = MainDAO.findClientPerUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }

    public ClientType findClientType(String clientTypeID) {
        ClientType clientType = null;
        try {
            clientType = MainDAO.findClientType(clientTypeID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientType;
    }

    public List<Client> findAllClient() {
        List<Client> myClientList = MainDAO.findAllClient();
        if (myClientList == null) {
            myClientList = new ArrayList<Client>();
        }
        return myClientList;
    }

    public List<ClientType> findAllClientType() {
        List<ClientType> myClientTypeList = MainDAO.findAllClientType();
        if (myClientTypeList == null) {
            myClientTypeList = new ArrayList<ClientType>();
        }
        return myClientTypeList;
    }
}
