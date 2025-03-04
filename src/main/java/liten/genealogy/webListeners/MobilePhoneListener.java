/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.webListeners;

import liten.genealogy.managers.MobileMoneyManager;
import liten.genealogy.webUtilities.HttpUtils;
import java.io.Serializable;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import liten.genealogy.mainEntities.Client;
import liten.genealogy.mainEntities.MobileOperator;
import liten.genealogy.mainEntities.MobilePhone;
import org.primefaces.event.SelectEvent;
import liten.genealogy.views.AccountModelView;
import liten.genealogy.views.MobileMoneyModelView;
import org.primefaces.PrimeFaces;
import liten.genealogy.views.ClientModelView;

/**
 *
 * @author FACULTY
 */
@Named
@SessionScoped
public class MobilePhoneListener implements Serializable {

    String origURI;
    MobileMoneyModelView mobileMoneyModelView;
    AccountModelView accountModelView;
    ClientModelView clientModelView;
    @Inject
    MobileMoneyManager mobileMoneyManager;

    public MobilePhoneListener() {

    }

    @PostConstruct
    public void init() {
        if (HttpUtils.getSessionMap().get("mobilePhonelManager") == null) {
            HttpUtils.getSessionMap().put("mobileMoneyManager", mobileMoneyManager);
        } else {
            mobileMoneyManager = (MobileMoneyManager) HttpUtils.getSessionMap().get("mobileMoneyManager");
        }
        if (HttpUtils.getSessionMap().get("mobileMoneyModelView") == null) {
            HttpUtils.getSessionMap().put("mobileMoneyModelView", mobileMoneyModelView);
        } else {
            mobileMoneyModelView = (MobileMoneyModelView) HttpUtils.getSessionMap().get("mobileMoneyModelView");
        }
        if (HttpUtils.getSessionMap().get("accountModelView") == null) {
            HttpUtils.getSessionMap().put("accountModelView", accountModelView);
        } else {
            accountModelView = (AccountModelView) HttpUtils.getSessionMap().get("accountModelView");
        }
        if (HttpUtils.getSessionMap().get("clientModelView") == null) {
            HttpUtils.getSessionMap().put("clientModelView", clientModelView);
        } else {
            clientModelView = (ClientModelView) HttpUtils.getSessionMap().get("clientModelView");
        }
    }

    public void setOrigURI(String curURI) {
        this.origURI = curURI;
    }

    public String getOrigURI() {
        return origURI;
    }

    public MobileMoneyManager getMobileMoneyManager() {
        return this.mobileMoneyManager;
    }

    public MobileMoneyModelView getMobileMoneyModelView() {
        return this.mobileMoneyModelView;
    }

    public AccountModelView getAccountModelView() {
        return this.accountModelView;
    }

    public ClientModelView getClientModelView() {
        return this.clientModelView;
    }

    public void addMobilePhone(ActionEvent evt) {
        try {
            if (mobileMoneyModelView != null && mobileMoneyManager != null) {
                Client client = clientModelView.getClient();
                MobilePhone mobilePhone = mobileMoneyModelView.getMobilePhone();
                mobilePhone.setClient(client);

                mobileMoneyManager.addNewMobilePhone(mobilePhone);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void addMobileOperator(ActionEvent evt) {
        try {
            if (mobileMoneyModelView != null && mobileMoneyManager != null) {
                MobileOperator mobileOperator = mobileMoneyModelView.getMobileOperator();

                mobileMoneyManager.addNewMobileOperator(mobileOperator);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMobilePhone(ActionEvent evt) {
        try {
            if (mobileMoneyModelView != null && mobileMoneyManager != null) {
                mobileMoneyManager.updateMobilePhone(mobileMoneyModelView.getMobilePhone());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void updateMobileOperator(ActionEvent evt) {
        try {
            if (mobileMoneyModelView != null && mobileMoneyManager != null) {
                mobileMoneyManager.updateMobileOperator(mobileMoneyModelView.getMobileOperator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearMobilePhoneInfos(ActionEvent evt) {
        try {
            if (mobileMoneyModelView != null && mobileMoneyManager != null) {
                mobileMoneyModelView.getMobilePhone().setMobileNbr("");
                mobileMoneyModelView.setMobileOperator(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectMobilePhone(SelectEvent<MobilePhone> evt) {
        try {
            MobilePhone mobilePhone = evt.getObject();
            mobileMoneyModelView.setMobilePhone(mobilePhone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
