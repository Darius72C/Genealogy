/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.managers;

import liten.genealogy.utilitiesDB .KeyGeneration;
import liten.genealogy.utilitiesDB.DataManager;
import liten.genealogy.utilitiesDB.MainDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import liten.genealogy.mainEntities.MobileOperator;
import liten.genealogy.mainEntities.MobilePhone;

/**
 *
 * @author FACULTY
 */
@Named
@SessionScoped
public class MobileMoneyManager implements Serializable {

    public MobileMoneyManager() {

    }

    public void addNewMobileOperator(MobileOperator mobileOperator) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            if (mobileOperator.getMobileOperatorID()==null){
                 String mobileOperatorID = KeyGeneration.generatePrimaryKey(Class.forName("entitesPrincipales.MobileOperator"));
                 mobileOperator.setMobileOperatorID(mobileOperatorID);
            }
            connectionServiceFacade.mergeEntity(mobileOperator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        public void addNewMobilePhone(MobilePhone mobilePhone) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            if (mobilePhone.getMobilePhoneID()==null){
                 String mobilePhoneID = KeyGeneration.generatePrimaryKey(Class.forName("entitesPrincipales.MobilePhone"));
                 mobilePhone.setMobilePhoneID(mobilePhoneID);
            }
            connectionServiceFacade.mergeEntity(mobilePhone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMobilePhone(MobilePhone mobilePhone) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            MobilePhone mobilePhoneDB = MainDAO.findMobilePhone(mobilePhone.getMobilePhoneID());
            if (mobilePhoneDB != null && !mobilePhone.equals(mobilePhoneDB)) {
                mobilePhone = connectionServiceFacade.mergeEntity(mobilePhone);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMobileOperator(MobileOperator mobileOperator) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            MobileOperator mobileOperatorDB = MainDAO.findMobileOperator(mobileOperator.getMobileOperatorID());
            if (mobileOperatorDB != null && !mobileOperator.equals(mobileOperatorDB)) {
                mobileOperator = connectionServiceFacade.mergeEntity(mobileOperator);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteMobilePhone(String mobilePhoneID) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            MobilePhone mobilePhoneDB = MainDAO.findMobilePhone(mobilePhoneID);
            if (mobilePhoneDB != null) {
                connectionServiceFacade.removeEntity(mobilePhoneDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteMobileOperator(String mobileOperatorID) {
        try {
            final DataManager connectionServiceFacade = DataManager.getInstance();
            MobileOperator mobileOperatorDB = MainDAO.findMobileOperator(mobileOperatorID);
            if (mobileOperatorDB != null) {
                connectionServiceFacade.removeEntity(mobileOperatorDB);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MobilePhone findMobilePhone(String mobilePhoneID) {
        MobilePhone mobilePhone = null;
        try {
            mobilePhone = MainDAO.findMobilePhone(mobilePhoneID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mobilePhone;
    }

    public MobileOperator findMobileOperator(String mobileOperatorID) {
        MobileOperator mobileOperator = null;
        try {
            mobileOperator = MainDAO.findMobileOperator(mobileOperatorID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mobileOperator;
    }

    public List<MobilePhone> findAllMobilePhonePerMobileNbr(String mobileNbr) {
        List<MobilePhone> mobilePhoneList = MainDAO.findAllMobilePhonePerMobileNbr(mobileNbr);
        if (mobilePhoneList == null) {
            mobilePhoneList = new ArrayList<MobilePhone>();
        }
        return mobilePhoneList;
    }

    public List<MobileOperator> findAllMobileOperator() {
        return MainDAO.findAllMobileOperator();
    }

}
