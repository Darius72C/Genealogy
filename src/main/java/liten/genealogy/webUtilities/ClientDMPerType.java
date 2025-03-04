/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.webUtilities;

import liten.genealogy.mainEntities.Client;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jakarta.persistence.Query;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import liten.genealogy.utilitiesDB.LazyDataModelImpl;
import liten.genealogy.utilitiesDB.MainDAO;

/**
 *
 * @author FACULTY
 */
public class ClientDMPerType extends LazyDataModel<Client> implements Serializable {

    LazyDataModelImpl<Client> clientDM;
    private List<Client> clientList;

    public ClientDMPerType() {
        clientDM = new LazyDataModelImpl<Client>();
        clientList = new ArrayList<Client>();
    }

    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        int totalRowCount = MainDAO.findAllClientsPerTypeQueryCount(filterBy);
        clientDM.setTotalRowCount(totalRowCount);
        return totalRowCount;
    }

    @Override
    public List<Client> load(int first, int pageSize, Map<String, SortMeta> sortMap, Map<String, FilterMeta> filters) {
        String clientTypeID= (String) filters.get("clientTypeID").getFilterValue();
        filters = MainDAO.findAllClientsPerTypeQueries(filters);
        clientDM.setQueryList((Query) filters.get("queryList").getFilterValue());
        clientDM.setQueryCountRows((Query) filters.get("queryCountRows").getFilterValue());
        clientDM.setWrappedData(clientList);
        clientDM.setTotalRowCount((int) filters.get("totalRowCount").getFilterValue());
        clientDM.setRowCount(clientDM.getTotalRowCount());
        return clientDM.load(first, pageSize, sortMap, filters);
    }
    
    public void setClientDM(LazyDataModelImpl<Client> clientDM) {
        this.clientDM = clientDM;
    }

    public LazyDataModelImpl<Client> getClientDM() {
        return clientDM;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }
}
